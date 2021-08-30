package com.xdcplus.workflow.remote.fallback;

import com.xdcplus.workflow.remote.PermRemote;
import com.xdcplus.ztb.common.remote.domain.perm.dto.SysDepartmentDTO;
import com.xdcplus.ztb.common.remote.domain.perm.dto.SysEmployeeDTO;
import com.xdcplus.ztb.common.remote.domain.perm.dto.SysUserInfoDTO;
import com.xdcplus.ztb.common.remote.domain.perm.vo.SysDepartmentVO;
import com.xdcplus.ztb.common.remote.domain.perm.vo.SysUserInfoVO;
import com.xdcplus.ztb.common.tool.pojo.vo.ResponseVO;
import com.xdcplus.ztb.common.tool.validator.ValidList;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
import java.util.List;

/**
 * 权限收放降级处理
 *
 * @author Rong.Jia
 * @date 2021/08/03 16:27:47
 */
@Slf4j
@Component
public class PermRemoteFallbackFactory implements FallbackFactory<PermRemote> {

    @Override
    public PermRemote create(Throwable cause) {
        return new PermRemote() {
            @Override
            public ResponseVO<SysUserInfoVO> getGeneralManagerSysUser() {

                log.error("getGeneralManagerSysUser {}", cause.getMessage());

                return ResponseVO.success();
            }

            @Override
            public ResponseVO<SysUserInfoVO> getSysUserMangerByUserIdOrUserName(SysUserInfoDTO sysUserInfoDTO) {

                log.error("getSysUserMangerByUserIdOrUserName {}", cause.getMessage());

                return ResponseVO.success();
            }

            @Override
            public ResponseVO<List<SysDepartmentVO>> getSysDepartmentList() {
                log.error("getSysDepartmentList {}", cause.getMessage());

                return ResponseVO.success();
            }

            @Override
            public ResponseVO<SysUserInfoVO> getSysUserManagerByDepartmentId(Long departmentId) {
                log.error("getSysUserManagerByDepartmentId {}", cause.getMessage());

                return ResponseVO.success();
            }

            @Override
            public ResponseVO<SysUserInfoVO> queryByUserName(String userName) {
                log.error("queryByUserName {}", cause.getMessage());

                return ResponseVO.success();
            }

            @Override
            public ResponseVO batchSyncInsert(@Valid ValidList<SysDepartmentDTO> sysDepartmentDtoList) {

                log.error("batchSyncInsert {}", cause.getMessage());

                return ResponseVO.success();
            }

            @Override
            public ResponseVO batchSyncInsertSysEmployee(@Valid ValidList<SysEmployeeDTO> sysEmployeeDtoList) {

                log.error("batchSyncInsertSysEmployee {}", cause.getMessage());

                return ResponseVO.success();
            }
        };
    }


}
