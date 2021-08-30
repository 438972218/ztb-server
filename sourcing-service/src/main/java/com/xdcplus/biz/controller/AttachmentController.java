package com.xdcplus.biz.controller;


import com.xdcplus.biz.common.pojo.vo.AttachmentVO;
import com.xdcplus.ztb.common.mp.controller.AbstractController;
import com.xdcplus.ztb.common.tool.pojo.vo.ResponseVO;
import io.minio.ObjectWriteResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import plus.ojbk.minio.core.MinioTemplate;


/**
 * 附件(BidAttachment)服务控制层
 *
 * @author Fish.Fei
 * @since 2021-07-06 15:16:52
 */
@Api(tags = "附件(Attachment)")
@Slf4j
@Validated
@RestController
@AllArgsConstructor
@RequestMapping("attachment")
public class AttachmentController extends AbstractController {

    @Autowired
    private MinioTemplate minioTemplate;

    @ApiOperation("上传附件")
    @PostMapping(value = "/uploadfile", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO uploadFile(@RequestParam("uploadFile") MultipartFile uploadFile) {

        String originalFilename = uploadFile.getOriginalFilename();
        String attachmentName = originalFilename.substring(0, originalFilename.lastIndexOf("."));
        String attachmentType = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);

        ObjectWriteResponse res = minioTemplate.putObject(uploadFile);
        String attachmentUrl = res.object();

        AttachmentVO attachmentVO = new AttachmentVO();
        attachmentVO.setAttachmentName(attachmentName);
        attachmentVO.setAttachmentType(attachmentType);
        attachmentVO.setAttachmentUrl(attachmentUrl);

        return ResponseVO.success(attachmentVO);
    }

    @ApiOperation("获取附件")
    @GetMapping("/getfile")
    public ResponseVO getfile(@RequestParam("path") String object) {
        //默认 1 小时
        String url = minioTemplate.getObject(object);
        //自定义时间
        //String url = minioTemplate.getObject(name, 10, TimeUnit.MINUTES);
        return ResponseVO.success(url);
    }

}
