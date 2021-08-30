package com.xdcplus.workflow.mapper;

import com.xdcplus.workflow.common.pojo.entity.BulletinBoard;
import com.xdcplus.workflow.common.pojo.query.BulletinBoardQuery;
import com.xdcplus.ztb.common.mp.mapper.IBaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 公告牌信息Mapper 接口
 *
 * @author Rong.Jia
 * @since 2021-08-16
 */
public interface BulletinBoardMapper extends IBaseMapper<BulletinBoard> {

    /**
     * 查询公告板通过的名字
     *
     * @param name 的名字
     * @return {@link BulletinBoard} 公告板
     */
    BulletinBoard findBulletinBoardByName(@Param("name") String name);

    /**
     * 查询公告板
     *
     * @param query 查询
     * @return {@link List<BulletinBoard>}
     */
    List<BulletinBoard> findBulletinBoard(BulletinBoardQuery query);


}
