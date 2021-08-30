package com.xdcplus.biz.service.impl;

import com.xdcplus.biz.generator.impl.BidSpecialistBaseServiceImpl;
import com.xdcplus.biz.mapper.BidSpecialistMapper;
import com.xdcplus.biz.common.pojo.entity.BidSpecialist;
import com.xdcplus.biz.common.pojo.vo.BidSpecialistVO;
import com.xdcplus.biz.service.BidSpecialistService;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

/**
 * 专家(BidSpecialist)表服务实现类
 *
 * @author Fish.Fei
 * @since 2021-08-24 16:23:00
 */
@Slf4j
@Service("bidSpecialistService")
public class BidSpecialistServiceImpl extends BidSpecialistBaseServiceImpl<BidSpecialist, BidSpecialistVO, BidSpecialist, BidSpecialistMapper> implements BidSpecialistService {


}
