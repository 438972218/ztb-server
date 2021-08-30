package com.xdcplus.workflow.common.pojo.query;

import com.xdcplus.workflow.common.pojo.dto.MailDeliveryFilterDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 邮件发送查询
 *
 * @author Rong.Jia
 * @date 2021/08/10 15:28:35
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class MailDeliveryQuery extends MailDeliveryFilterDTO implements Serializable {

    private static final long serialVersionUID = 1986064453418930581L;







}
