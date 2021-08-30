package com.xdcplus.permission.service.impl;

import cn.hutool.core.date.DateUtil;
import com.github.pagehelper.PageInfo;
import com.xdcplus.ztb.common.exceptions.ZtbWebException;
import com.xdcplus.ztb.common.mp.service.impl.BaseServiceImpl;
import com.xdcplus.permission.common.enums.IsDeletedEnum;
import com.xdcplus.permission.dao.SysDictionaryDao;
import com.xdcplus.permission.common.pojo.dto.sysdictionary.SysDictionaryDto;
import com.xdcplus.permission.common.pojo.dto.sysdictionary.SysDictionaryFilterDto;
import com.xdcplus.permission.common.pojo.entity.SysDictionary;
import com.xdcplus.permission.common.pojo.query.sysdictionary.SysDictionaryFilterQuery;
import com.xdcplus.permission.common.pojo.vo.sysdictionary.SysDictionaryVo;
import com.xdcplus.permission.service.SysDictionaryService;
import com.xdcplus.ztb.common.tool.pojo.vo.PageVO;
import com.xdcplus.ztb.common.tool.utils.PageableUtils;
import com.xdcplus.ztb.common.tool.utils.PropertyUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 字典表(SysDictionary)表服务实现类
 *
 * @author Bullion.Yan
 * @since 2021-06-28 13:10:12
 */
@Service("sysDictionaryService")
public class SysDictionaryServiceImpl
        extends BaseServiceImpl<SysDictionary, SysDictionaryVo, SysDictionary, SysDictionaryDao>
        implements SysDictionaryService {
    @Resource
    private SysDictionaryDao sysDictionaryDao;

    @Override
    public PageVO<SysDictionaryVo> getSysDictionaryPageByCondition(SysDictionaryFilterDto sysDictionaryFilterDto) {
        PageVO<SysDictionaryVo> pageVo = new PageVO<>();
        //1.设置查询对象
        SysDictionaryFilterQuery sysDictionaryFilterQuery = new SysDictionaryFilterQuery();
        //2.设置分页
        PageableUtils.basicPage(sysDictionaryFilterDto);
        //3.将dto对象转换为query对象
        org.springframework.beans.BeanUtils.copyProperties(sysDictionaryFilterDto, sysDictionaryFilterQuery);
        //4.查询
        List<SysDictionary> sysDictionaryList = sysDictionaryDao.getSysDictionaryPageByCondition(sysDictionaryFilterQuery);
        PageInfo pageInfo = new PageInfo<>(sysDictionaryList);
        PropertyUtils.copyProperties(pageInfo, pageVo);
        pageVo.setRecords(this.objectConversion(sysDictionaryList));
        return pageVo;
    }

    @Override
    public SysDictionaryVo queryById(Long id) {
        SysDictionary sysDictionary = this.getById(id);
        if (sysDictionary != null && IsDeletedEnum.NO_DELETED.getCode().equals(sysDictionary.getDeleted())) {
            SysDictionaryVo sysDictionaryVo = this.objectConversion(sysDictionary);
            return sysDictionaryVo;
        }
        return null;
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void insert(SysDictionaryDto sysDictionaryDto,String loginUser) {
        SysDictionary sysDictionary = new SysDictionary();
        org.springframework.beans.BeanUtils.copyProperties(sysDictionaryDto, sysDictionary);
        long currentTime=DateUtil.current();
        sysDictionary.setUpdatedTime(currentTime);
        sysDictionary.setCreatedTime(currentTime);
        sysDictionary.setUpdatedUser(loginUser);
        sysDictionary.setCreatedUser(loginUser);
        sysDictionary.setDeleted(IsDeletedEnum.NO_DELETED.getCode());
        this.save(sysDictionary);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void updateById(SysDictionaryDto sysDictionaryDto,String loginUser) {
        SysDictionary sysDictionary = this.getById(sysDictionaryDto.getId());
        if (sysDictionary == null) {
            throw new ZtbWebException(1,
                    "更新的数据不存在");
        }
        org.springframework.beans.BeanUtils.copyProperties(sysDictionaryDto, sysDictionary);
        long currentTime=DateUtil.current();
        sysDictionary.setUpdatedTime(currentTime);
        sysDictionary.setUpdatedUser(loginUser);
        this.updateById(sysDictionary);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void deleteById(Long id,String loginUser) {
        SysDictionary sysDictionary = this.getById(id);
        if (sysDictionary == null) {
            throw new ZtbWebException(1,
                    "删除的数据不存在");
        }
        long currentTime= DateUtil.current();
        sysDictionary.setUpdatedTime(currentTime);
        sysDictionary.setUpdatedUser(loginUser);
        sysDictionary.setDeleted(IsDeletedEnum.DELETED.getCode());//删除
        this.updateById(sysDictionary);
    }


}
