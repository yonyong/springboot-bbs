package cn.connext.yonyong.yonyongbbs.service;

import cn.connext.yonyong.yonyongbbs.entity.Reply;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface ReplyService {
    /**
     * 查询指定文章的所有评论
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
    void addReply(int article_id,String replyer,Date date,String reply);

    /**
     * 删除评论
     * @param id
     */
    void deleteReply(int id);
}
