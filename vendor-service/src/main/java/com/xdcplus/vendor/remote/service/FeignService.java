package com.xdcplus.vendor.remote.service;

import com.xdcplus.ztb.common.remote.domain.sourcing.PaidSheetInfoDTO;
import com.xdcplus.ztb.common.remote.domain.sourcing.PaidSheetInfoVO;
import com.xdcplus.ztb.common.remote.domain.perm.vo.SysUserInfoVO;

import java.util.List;

/**
 * feign 调用处理
 *
 * @author Rong.Jia
 * @date 2021/08/17 15:09:46
 */
public interface FeignService {

    /**
     * 查询用户信息通过用户的名字
     *
     * @param userName 用户名
     * @return {@link SysUserInfoVO}
     */
    SysUserInfoVO findUserInfoByUserName(String userName);

    /**
     * 查询竞价单根据表单ID
     *
     * @param requestId 表单ID
     * @return {@link PaidSheetInfoVO}
     */
    PaidSheetInfoVO findPaidInvitationByRequestId(Long requestId);

    /**
     * 查询竞价单竞价方向
     *
     * @param requestId 表单ID
     * @return {@link Boolean} true: 正向，false: 反向
     */
    Boolean findPaidInvitationPaidDirection(Long requestId);

    /**
     * 获取sys角色马克通过用户的名字
     *
     * @param userName 用户名
     * @return {@link Boolean} true: 内部，false: 外部
     */
    Boolean getSysRoleMarkByUserName(String userName);

    /**
     * 查询竞价信息
     *
     * @return {@link PaidSheetInfoVO}
     */
    List<PaidSheetInfoVO> findPaidSheetInfos();

    /**
     * 修改支付表
     * 修改竞价信息
     *
     * @param paidSheetDTO 支付表DTO
     * @return {@link Boolean} 更新是否成功
     */
    Boolean updatePaidSheet(PaidSheetInfoDTO paidSheetDTO);



}
