package com.xdcplus.workflow.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xdcplus.workflow.common.pojo.entity.DiscussGroup;
import com.xdcplus.workflow.mapper.DiscussGroupMapper;
import com.xdcplus.workflow.service.DiscussGroupService;
import com.xdcplus.ztb.common.tool.enums.ResponseEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 讨论组服务实现类
 *
 * @author Rong.Jia
 * @since 2021-08-18
 */
@Slf4j
@Service
public class DiscussGroupServiceImpl extends ServiceImpl<DiscussGroupMapper, DiscussGroup> implements DiscussGroupService {

    @Autowired
    private DiscussGroupMapper discussGroupMapper;

    @Override
    public List<DiscussGroup> findDiscussionGroup(Long requestId, String currentUser) {

        Assert.notNull(requestId, ResponseEnum.THE_ID_CANNOT_BE_EMPTY.getMessage());

        return discussGroupMapper.findDiscussGroupByRequestId(requestId, currentUser);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Long saveDiscussGroup(Long requestId, String subject, String currentUser) {

        DiscussGroup discussGroup = new DiscussGroup();
        discussGroup.setCreatedTime(DateUtil.current());
        discussGroup.setCreatedUser(currentUser);
        discussGroup.setRequestId(requestId);
        discussGroup.setSubject(subject);

        this.save(discussGroup);

        return discussGroup.getId();
    }
}
