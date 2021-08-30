package com.xdcplus.biz.service.impl;

import com.xdcplus.biz.generator.impl.BidDetailTemplateBaseServiceImpl;
import com.xdcplus.biz.mapper.BidDetailTemplateMapper;
import com.xdcplus.biz.common.pojo.entity.BidDetailTemplate;
import com.xdcplus.biz.common.pojo.vo.BidDetailTemplateVO;
import com.xdcplus.biz.service.BidDetailTemplateService;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

/**
 * 内容模板(BidDetailTemplate)表服务实现类
 *
 * @author Fish.Fei
 * @since 2021-08-19 16:23:18
 */
@Slf4j
@Service("bidDetailTemplateService")
public class BidDetailTemplateServiceImpl extends BidDetailTemplateBaseServiceImpl<BidDetailTemplate, BidDetailTemplateVO, BidDetailTemplate, BidDetailTemplateMapper> implements BidDetailTemplateService {


}
