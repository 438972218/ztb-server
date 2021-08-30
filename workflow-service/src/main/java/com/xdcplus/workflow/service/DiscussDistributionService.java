package com.xdcplus.workflow.service;

import com.xdcplus.workflow.common.pojo.dto.DiscussGroupFilterDTO;
import com.xdcplus.workflow.common.pojo.dto.PostDiscussionsDTO;
import com.xdcplus.workflow.common.pojo.dto.ReplyDiscussDTO;
import com.xdcplus.workflow.common.pojo.vo.DiscussGroupVO;
import com.xdcplus.workflow.common.pojo.vo.DiscussRecordVO;
import com.xdcplus.ztb.common.tool.pojo.vo.PageVO;

import java.util.List;

/**
 * 讨论分发服务
 *
 * @author Rong.Jia
 * @date 2021/08/19 09:44:55
 */
public interface DiscussDistributionService {

    /**
     * 发起讨论
     *
     * @param postDiscussionsDTO 帖子讨论DTO
     */
    void postDiscussions(PostDiscussionsDTO postDiscussionsDTO);

    /**
     * 回复讨论
     *
     * @param replyDiscussDTO 回复讨论DTO
     */
    void replyDiscuss(ReplyDiscussDTO replyDiscussDTO);

    /**
     * 查询讨论组
     *
     * @param requestId 表单ID
     * @return {@link List <DiscussGroupVO>}
     */
    List<DiscussGroupVO> findDiscussGroup(Long requestId);

    /**
     * 查询讨论组
     *
     * @param discussGroupFilterDTO 讨论集组过滤器DTO
     * @return {@link PageVO<DiscussGroupVO>}
     */
    PageVO<DiscussGroupVO> findDiscussGroup(DiscussGroupFilterDTO discussGroupFilterDTO);

    /**
     * 查询讨论记录
     *
     * @param groupId 组id
     * @return {@link List<DiscussRecordVO>}
     */
    List<DiscussRecordVO> findDiscussRecordsByGroupId(Long groupId);

    /**
     * 查询讨论记录
     *
     * @param requestId 表单ID
     * @return {@link List<DiscussRecordVO>}
     */
    List<DiscussRecordVO> findDiscussRecordsByRequestId(Long requestId);
















}
