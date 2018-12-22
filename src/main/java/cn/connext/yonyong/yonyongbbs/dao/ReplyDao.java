package cn.connext.yonyong.yonyongbbs.dao;

import cn.connext.yonyong.yonyongbbs.entity.Reply;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ReplyDao {

    /**
     * 不分页查询指定文章的所有评论
     * @param article_id
     * @return
     */
    List<Reply> selectAllReplyByArticleID(int article_id);

    /**
     * 分页查询指定文章的所有评论
     * @return
     */
    List<Reply> selectAllReply(@Param("article_id") int article_id,@Param("replypage") int replypage);

    /**
     * 添加评论
     * @param article_id
     * @param replyer
     * @param date
     * @param reply
     */
    void addReply(@Param("article_id") int article_id,@Param("replyer") String replyer,@Param("date") Date date,@Param("reply") String reply);

    /**
     * 删除评论
     * @param id
     */
    void deleteReply(int id);
}
