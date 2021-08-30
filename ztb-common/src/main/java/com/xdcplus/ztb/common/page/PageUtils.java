package com.xdcplus.ztb.common.page;

import cn.hutool.core.util.PageUtil;
import com.xdcplus.ztb.common.tool.pojo.vo.PageVO;

import java.util.List;

public class PageUtils {

    public static PageVO getPageVO(List<?> list, int pageNum, int pageSize){
        PageVO pageVO = new PageVO();

        if(pageNum==-1){
            pageVO.setPageSize(pageSize);//页大小
            pageVO.setCurrentPage(pageNum);//当前页码
            pageVO.setTotal(list.size());//数据总数
            pageVO.setRecords(list);//分页结果集

            return pageVO;
        }
        //总页数
        int pages = PageUtil.totalPage(list.size(), pageSize);
        //开始位置和结束位置
        int[] startEnd = PageUtil.transToStartEnd(pageNum-1, pageSize);
        //分页后的结果集
        List<?> pageList = null;
        if (startEnd[1] < list.size()){
            pageList = list.subList(startEnd[0], startEnd[1]);
        }else {
            pageList = list.subList(startEnd[0], list.size());
        }

        pageVO.setPageSize(pageSize);//页大小
        pageVO.setCurrentPage(pageNum);//当前页码
        pageVO.setTotalPages(pages);//总页数
        pageVO.setTotal(list.size());//数据总数
        pageVO.setRecords(pageList);//分页结果集

        return pageVO;
    }


}
