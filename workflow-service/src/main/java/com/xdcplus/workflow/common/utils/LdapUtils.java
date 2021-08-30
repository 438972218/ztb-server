package com.xdcplus.workflow.common.utils;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.xdcplus.workflow.common.pojo.vo.FieldsThatVO;
import com.xdcplus.workflow.common.pojo.vo.LdapVO;
import com.xdcplus.ztb.common.tool.constants.NumberConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ldap.core.AttributesMapper;
import org.springframework.ldap.core.AuthenticationSource;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.LdapContextSource;
import org.springframework.ldap.query.LdapQuery;
import org.springframework.ldap.query.LdapQueryBuilder;

import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.SearchControls;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * ldap工具类
 *
 * @author Rong.Jia
 * @date 2021/06/25
 */
@Slf4j
public class LdapUtils {

    private static LdapTemplate ldapAD;

    private static final String OBJECT_GUID = "objectGUID";
    private static final String WHEN_CREATED = "whenCreated";
    private static final String WHEN_CHANGED = "whenChanged";
    private static final String OBJECT_CLASS = "objectclass";
    public static final String ORGANIZATIONAL_UNIT = "organizationalUnit";
    public static final String DISTINGUISHED_NAME = "distinguishedName";
    private static final String OU_EQUAL = "OU=";
    private static final String OU = "ou";
    public static final String LEVEL = "level";
    private static final String NAME = "name";
    public static final String PARENT = "parent";
    public static final String DEPT = "dept";

    private static SimpleDateFormat YYYY_MM_DD_HH_MM_SS_Z = new SimpleDateFormat("yyyyMMddHHmmssZ");

    /**
     * 初始化Ldap AD
     *
     * @param ldapVO ldap属性
     */
    private static void initLdapAD(LdapVO ldapVO) {

        LdapContextSource ldapContextSource = new LdapContextSource();
        ldapContextSource.setUrls(new String[]{ldapVO.getAddress()});
        ldapContextSource.setPassword(ldapVO.getPassword());
        ldapContextSource.setUserDn(ldapVO.getPassword());
        ldapContextSource.setBase(ldapVO.getDomain());
        ldapContextSource.setAuthenticationSource(new AuthenticationSource() {
            @Override
            public String getPrincipal() {
                return ldapVO.getUsername();
            }

            @Override
            public String getCredentials() {
                return ldapVO.getPassword();
            }
        });

        ldapContextSource.afterPropertiesSet();
        ldapAD = new LdapTemplate(ldapContextSource);
        ldapAD.setIgnorePartialResultException(true);

    }

    /**
     * 获取条件
     *
     * @param type 类型（AD：1，OpenLdap: 2, SUN ONE :3 ）
     * @return {@link String} 条件
     */
    public static String getConditions(Byte type) {

        // AD域默认为(&(objectCategory=person)(objectClass=user))，其他默认为objectclass=person

        String objectClass = null;
        switch ((int) type) {
            case 1:
                objectClass = "(&(objectClass=organizationalPerson)(objectClass=user))";
                break;
            case 2:
            case 3:
                objectClass = "person";
                break;
            default:
                break;
        }

        return objectClass;
    }

    /**
     * 获取Ldap部门集合
     *
     * @param objectClass      同步类别
     * @param ldapVO           Ldap信息
     * @param fieldsThatVOList 字段对应集合
     * @return 部门信息
     */
    public static List<Map<String, Object>> getADDepartments(LdapVO ldapVO, String objectClass,
                                                             List<FieldsThatVO> fieldsThatVOList) {
        initLdapAD(ldapVO);

        objectClass = getObjectClass(objectClass);

        List<Map<String, Object>> departments = CollectionUtil.newArrayList();

        getADDepartments(departments, ldapVO, fieldsThatVOList, objectClass, ldapVO.getBaseOu(), null);

        return departments;
    }

    /**
     * 获取Ldap子级部门
     *
     * @param objectClass      同步类别
     * @param ldapVO           Ldap信息
     * @param fieldsThatVOList 字段对应集合
     * @param ou               AD OU
     * @return 部门信息
     */
    public static List<Map<String, Object>> getADDepartments(LdapVO ldapVO, String objectClass, String ou,
                                                             List<FieldsThatVO> fieldsThatVOList) {
        initLdapAD(ldapVO);

        objectClass = getObjectClass(objectClass);
        LdapQuery query = LdapQueryBuilder.query().where(OBJECT_CLASS).is(objectClass);
        if (StrUtil.isBlank(ou)) {
            ou = query.base().toString();
        }

        return ldapAD.search(ou, query.filter().encode(), SearchControls.ONELEVEL_SCOPE,
                (AttributesMapper<Map<String, Object>>) attributes -> {
                    Map<String, Object> objectMap = copyProperties(attributes, fieldsThatVOList);
                    setSpecialData(objectMap, attributes, ldapVO.getDomain());
                    return objectMap;
                });

    }

    /**
     * 查询 AD Ldap 用户信息
     *
     * @param ldapVO             ldap 信息
     * @param adDepartments      AD 部门信息
     * @param userFieldsThatList 用户字段列表
     * @return {@link List<Map<String, Object>>} 用户信息
     */
    public static List<Map<String, Object>> getADUsers(LdapVO ldapVO, List<Map<String, Object>> adDepartments,
                                                       List<FieldsThatVO> userFieldsThatList) {

        initLdapAD(ldapVO);

        String conditions = ldapVO.getConditions();
        if (StrUtil.isBlank(conditions)) {
            conditions = getConditions(ldapVO.getType());
        }

        List<Map<String, Object>> adUsers = CollectionUtil.newArrayList();
        getADUsers(adUsers, ldapVO, userFieldsThatList, adDepartments, conditions, ldapVO.getBaseOu(), null);

        return adUsers;
    }

    /**
     * 获取Ldap 子级用户
     *
     * @param conditions       同步类别
     * @param ldapVO           Ldap信息
     * @param ou               Ldap OU
     * @param fieldsThatVOList 字段对应集合
     * @return 部门信息
     */
    public static List<Map<String, Object>> getADUsers(LdapVO ldapVO, String conditions, String ou,
                                                       List<FieldsThatVO> fieldsThatVOList) {
        initLdapAD(ldapVO);

        LdapQuery query = LdapQueryBuilder.query().where(OBJECT_CLASS).is(conditions);
        if (StrUtil.isBlank(ou)) {
            ou = query.base().toString();
        }

        return ldapAD.search(ou, query.filter().encode(), SearchControls.ONELEVEL_SCOPE,
                (AttributesMapper<Map<String, Object>>) attributes -> copyProperties(attributes, fieldsThatVOList));
    }

    /**
     * 获取部门信息
     *
     * @param ldapVO           ldapVO
     * @param ou               OU
     * @param fieldsThatVOList 字段对应信息
     * @return {@link Map<String, Object>} 部门信息
     */
    public static Map<String, Object> getADDepartment(LdapVO ldapVO, String ou, List<FieldsThatVO> fieldsThatVOList) {

        initLdapAD(ldapVO);
        return ldapAD.lookup(ou, (AttributesMapper<Map<String, Object>>) attributes -> {
            Map<String, Object> objectMap = copyProperties(attributes, fieldsThatVOList);
            setSpecialData(objectMap, attributes, ldapVO.getDomain());

            return objectMap;
        });
    }

    /**
     * set 特殊数据
     *
     * @param objectMap  对象映射
     * @param attributes 属性
     * @param domain     同步域
     */
    private static void setSpecialData(Map<String, Object> objectMap, Attributes attributes, String domain) {

        if (MapUtil.isNotEmpty(objectMap)) {
            objectMap.put(LEVEL, getFieldValue(OBJECT_GUID, attributes));
            objectMap.put(DISTINGUISHED_NAME,
                    StrUtil.removeSuffixIgnoreCase(Convert.toStr(getFieldValue(DISTINGUISHED_NAME, attributes)),
                            StrUtil.COMMA + domain));
        }

    }

    /**
     * 查询 AD Ldap 用户信息
     *
     * @param adUsers        AD 用户信息集合
     * @param ldapVO         Ldap 信息
     * @param fieldsThatList 对应字段集合
     * @param adDepartments  Ldap部门信息
     * @param conditions     条件
     * @param baseOu         顶级OU
     * @param deptLevel      部门级别
     */
    private static void getADUsers(List<Map<String, Object>> adUsers, LdapVO ldapVO,
                                   List<FieldsThatVO> fieldsThatList, List<Map<String, Object>> adDepartments,
                                   String conditions, String baseOu, String deptLevel) {

        List<String> ouList = getNextOu(ldapVO, baseOu);

        if (CollectionUtil.isNotEmpty(ouList)) {
            for (String ou : ouList) {

                String level = null;
                for (Map<String, Object> adDepartment : adDepartments) {
                    for (Map.Entry<String, Object> entry : adDepartment.entrySet()) {
                        if (ObjectUtil.equal(ou, entry.getValue())) {
                            level = (String) adDepartment.get(LEVEL);
                        }
                    }
                }

                String nextOu = OU_EQUAL + ou + StrUtil.COMMA + baseOu;

                List<Map<String, Object>> adUserList = getADUsers(ldapVO, conditions, nextOu, fieldsThatList);
                if (CollectionUtil.isNotEmpty(adUserList)) {
                    adUserList.forEach(a -> a.put(DEPT, deptLevel));

                    adUsers.addAll(adUserList);
                }

                getADUsers(adUsers, ldapVO, fieldsThatList, adDepartments, conditions, nextOu, level);

            }
        }
    }

    /**
     * 查询下一个层级部门
     *
     * @param ldapVO ldapVO
     * @param ou     层级
     * @return {@link List<String>}
     */
    private static List<String> getNextOu(LdapVO ldapVO, String ou) {

        initLdapAD(ldapVO);
        String objectClass = getObjectClass(null);

        LdapQuery query = LdapQueryBuilder.query().where(OBJECT_CLASS).is(objectClass);
        if (StrUtil.isBlank(ou)) {
            ou = query.base().toString();
        }

        return ldapAD.search(ou, query.filter().encode(), SearchControls.ONELEVEL_SCOPE,
                (AttributesMapper<String>) attributes -> Convert.toStr(getFieldValue(OU, attributes)));
    }

    /**
     * 获取AD 部门信息
     *
     * @param departments      部门信息
     * @param ldapVO           ldapVO
     * @param fieldsThatVOList 对应字段集合
     * @param objectClass      对象类
     * @param ou               查询层级
     * @param parent           父级层级标识
     */
    private static void getADDepartments(List<Map<String, Object>> departments, LdapVO ldapVO,
                                         List<FieldsThatVO> fieldsThatVOList, String objectClass, String ou, String parent) {

        List<Map<String, Object>> adDepartments = getADDepartments(ldapVO, objectClass, ou, fieldsThatVOList);

        if (CollectionUtil.isNotEmpty(adDepartments)) {

            FieldsThatVO fieldsThatVO = fieldsThatVOList.stream()
                    .filter(a -> StrUtil.equalsAnyIgnoreCase(OU, a.getLdapName())
                            || StrUtil.equalsAnyIgnoreCase(NAME, a.getLdapName()))
                    .findAny().orElse(null);

            if (ObjectUtil.isNotNull(fieldsThatVO)) {
                for (Map<String, Object> adDepartmentMap : adDepartments) {

                    adDepartmentMap.put(PARENT, StrUtil.isBlank(parent) ? NumberConstant.ZERO : parent);
                    departments.add(adDepartmentMap);

                    String nextOu = OU_EQUAL + Convert.toStr(adDepartmentMap.get(fieldsThatVO.getFieldName())) + StrUtil.COMMA + ou;
                    getADDepartments(departments, ldapVO, fieldsThatVOList, objectClass, nextOu, Convert.toStr(adDepartmentMap.get(LEVEL)));
                }
            }
        }
    }

    /**
     * 获取对象类
     *
     * @param objectClass 对象类
     * @return {@link String}  对象类
     */
    private static String getObjectClass(String objectClass) {

        if (StrUtil.isBlank(objectClass)) {
            objectClass = ORGANIZATIONAL_UNIT;
        }
        return objectClass;
    }

    /**
     * 复制属性
     *
     * @param attributes       属性
     * @param fieldsThatVOList 字段 List
     * @return {@link Map<String,  Object>} key: 字段名，值
     */
    private static Map<String, Object> copyProperties(Attributes attributes, List<FieldsThatVO> fieldsThatVOList) {

        if (CollectionUtil.isNotEmpty(fieldsThatVOList) && ObjectUtil.isNotNull(attributes)) {

            // key: Ldap字段名，value: 字段名
            Map<String, String> fieldsThatMap = fieldsThatVOList.stream()
                    .collect(Collectors.toMap(FieldsThatVO::getLdapName, FieldsThatVO::getFieldName));

            Map<String, Object> objectMap = MapUtil.newHashMap();

            fieldsThatMap.forEach((key, value) -> Optional.ofNullable(getFieldValue(key, attributes)).ifPresent(a -> objectMap.put(value, a)));

            return objectMap;
        }

        return null;
    }

    /**
     * 特殊性质
     *
     * @param fieldName 字段名
     * @param object    值
     * @return {@link Object}
     */
    private static Object specialProperties(String fieldName, Object object) {

        Object value;

        switch (fieldName) {

            case OBJECT_GUID:
                value = guid2Str(object);
                break;
            case WHEN_CREATED:
            case WHEN_CHANGED:
                value = strToDate(Convert.toStr(object));
                break;
            default:
                value = object;
                break;

        }
        return value;
    }

    private static String addLeadingZero(int k) {
        return (k <= 0xF) ? "0" + Integer.toHexString(k) : Integer.toHexString(k);
    }

    /**
     * 对象转数组
     *
     * @param obj 对象
     * @return 数组
     */
    private static byte[] toByteArray(Object obj) {
        byte[] bytes = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(obj);
            oos.flush();
            bytes = bos.toByteArray();
            oos.close();
            bos.close();
        } catch (IOException ex) {
            log.error("toByteArray {}", ex.getMessage());
        }
        return bytes;
    }

    /**
     * guid 转字符串
     *
     * @param objectGuid 对象guid
     * @return {@link String} 字符串
     */
    private static String guid2Str(Object objectGuid) {

        byte[] guid = toByteArray(objectGuid);

        String strGUID = StrUtil.EMPTY;

        strGUID = strGUID + addLeadingZero((int) guid[3] & 0xFF);
        strGUID = strGUID + addLeadingZero((int) guid[2] & 0xFF);
        strGUID = strGUID + addLeadingZero((int) guid[1] & 0xFF);
        strGUID = strGUID + addLeadingZero((int) guid[0] & 0xFF);
        strGUID = strGUID + "-";
        strGUID = strGUID + addLeadingZero((int) guid[5] & 0xFF);
        strGUID = strGUID + addLeadingZero((int) guid[4] & 0xFF);
        strGUID = strGUID + "-";
        strGUID = strGUID + addLeadingZero((int) guid[7] & 0xFF);
        strGUID = strGUID + addLeadingZero((int) guid[6] & 0xFF);
        strGUID = strGUID + "-";
        strGUID = strGUID + addLeadingZero((int) guid[8] & 0xFF);
        strGUID = strGUID + addLeadingZero((int) guid[9] & 0xFF);
        strGUID = strGUID + "-";
        strGUID = strGUID + addLeadingZero((int) guid[10] & 0xFF);
        strGUID = strGUID + addLeadingZero((int) guid[11] & 0xFF);
        strGUID = strGUID + addLeadingZero((int) guid[12] & 0xFF);
        strGUID = strGUID + addLeadingZero((int) guid[13] & 0xFF);
        strGUID = strGUID + addLeadingZero((int) guid[14] & 0xFF);
        strGUID = strGUID + addLeadingZero((int) guid[15] & 0xFF);
        return strGUID;
    }

    /**
     * 查询字段值
     *
     * @param fieldName  字段名
     * @param attributes 属性
     * @return {@link Object} 值
     */
    private static Object getFieldValue(String fieldName, Attributes attributes) {

        try {
            Attribute attribute = attributes.get(fieldName);
            if (ObjectUtil.isNotNull(attribute)) {
                return specialProperties(fieldName, attribute.get());
            }

        } catch (NamingException e) {
            log.error("The value of corresponding field {} failed to be obtained {}", fieldName, e.getMessage());
        }

        return null;
    }

    /**
     * 时间格式转换
     *
     * @param strDate 时间
     *                "20140208083320.0Z"转为时间戳
     * @return 时间戳
     */

    private static Long strToDate(String strDate) {
        if (StrUtil.isBlank(strDate)) {
            return null;
        }

        strDate = strDate.substring(0, strDate.lastIndexOf(".")) + "+0000";
        return DateUtil.parse(strDate, YYYY_MM_DD_HH_MM_SS_Z).getTime();
    }


}
