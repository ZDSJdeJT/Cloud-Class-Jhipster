package jsu.zsh.service.mapper;


import jsu.zsh.domain.Message.Message;
import jsu.zsh.domain.Message.Notice;
import jsu.zsh.service.dto.CommentDTO;
import jsu.zsh.service.dto.DynamicDTO;
import jsu.zsh.service.dto.NoticeDTO;
import jsu.zsh.service.dto.cComment;
import jsu.zsh.service.provider.messageProvider;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;

@Mapper
public interface MessageMapper {

    @Insert("insert into 消息表(内容,创建时间,发表者学号,消息类型) values(#{content},now(),#{postUserId},1)")
    @Options(useGeneratedKeys = true,keyProperty ="id")
    int saveDynamic(Message message);

    @Insert("insert into 消息表(标题,内容,创建时间,截至时间,发表者学号,消息类型) values(#{title},#{content},now(),#{cutTime},#{postUserId},#{type})")
    @Options(useGeneratedKeys = true,keyProperty ="id")
    int saveNotice(NoticeDTO notice,@Param("type")int type);

    @InsertProvider(type = messageProvider.class, method = "insertForCrowd")
    Integer saveForCrowd(List<Long> list,long id);


    @Insert("insert into 消息表(内容,创建时间,发表者学号,评论消息id,消息类型) values(#{context},now(),#{postUserID},#{msID},2)")
    @Options(useGeneratedKeys = true)
    int saveComment(@Param("content")String content,@Param("postUserID")long postUserID,@Param("msID")long msID);


    @Insert("insert into 消息表(内容,创建时间,发表者学号,回复评论id,评论评论id,评论消息id,消息类型) values(#{context},now(),#{postUserID},#{replyId},#{commentId}#{msID},3)")
    @Options(useGeneratedKeys = true)
    int saveCComment(@Param("content")String content,@Param("postUserID")long postUserID,@Param("replyId")Long replyId,@Param("commentId")long commentId,@Param("msID")long msID);

    @Insert("insert into 消息表(标题,内容,创建时间,截至时间,发表者学号,消息类型) values(#{title},#{content},now(),#{cutTime},#{postUserId},4)")
    @Options(useGeneratedKeys = true)
    int saveTask(Notice notice);

    @Select("select id,标题,内容,创建时间,截至时间,发表者学号,first_name,image_url from 消息表,jhi_user where 截至时间>now() and 消息类型 = #{type} and 逻辑删除 = 0")
    @Results({
        @Result(id =true,column ="id",property = "id"),
        @Result(column = "标题",property = "title"),
        @Result(column = "内容",property = "content"),
        @Result(column = "创建时间",property = "createTime"),
        @Result(column = "截至时间",property = "cutTime"),
        @Result(column = "发表者学号",property = "postUserId"),
        @Result(column = "first_name",property = "petName"),
        @Result(column = "image_url",property = "postUserHeadPortraitUri"),
    })
    List<NoticeDTO> findNotice(@Param("type")long type);

    @Select("select 学号 from 面向人群表 where 消息Id =  #{msID}")
    @Results({
        @Result(column ="学号",property = "id"),
    })
    List<Long> findForCrowd(@Param("msID")long msID);

    @Select("select * from 消息表 where 创建时间> #{sTime} and 创建时间 < #{eTime} and 逻辑删除 = 0")
    @Results({
        @Result(id =true,column ="id",property = "id"),
        @Result(column = "创建时间",property = "createTime")
    })
    Message findByTime(@Param("sTime") Date sTime, @Param("eTime") Date eTime);


    @Select("select * from 消息表 where 消息类型 = #{type} and 创建时间> #{sTime} and 创建时间 < #{eTime} and 逻辑删除 = 0")
    @Results({
        @Result(id =true,column ="id",property = "id"),
        @Result(column = "创建时间",property = "createTime")
    })
    Message findByTimeAndType(@Param("sTime") Date sTime, @Param("eTime") Date eTime,@Param("type") int type);


    //查询
    @Select("select * from 消息表 where 消息类型 = #{type}  and 逻辑删除 = 0")
    @Results({
        @Result(id =true,column ="id",property = "id"),
        @Result(column = "内容",property = "content"),
        @Result(column = "创建时间",property = "createTime"),
        @Result(column = "发表者学号",property = "postUserId")

    })
    List<Message> findMsByType(@Param("type") int type);

    @Select("select count(点赞人学号) from 点赞表 where 消息id = #{msID}")
    Integer findTagsCount(long msID);

    @Select("select count(评论消息id) from 消息表 where 评论消息id = #{msID}")
    Integer findCommentsCount(long msID);

    @Select("select 消息表.id,内容,创建时间,发表者学号,first_name,image_url from 消息表,jhi_user where login = 发表者学号 and 消息类型 = 1 and 逻辑删除 = 0")
    @Results({
        @Result(id =true,column ="id",property = "id"),
        @Result(column = "内容",property = "content"),
        @Result(column = "创建时间",property = "createTime"),
        @Result(column = "发表者学号",property = "postUserId"),
        @Result(column = "first_name",property = "petName"),
        @Result(column = "image_url",property = "postUserHeadPortraitUri")
    })
    List<DynamicDTO> findDynamic();

    @Select("select 消息表.id,内容,创建时间,发表者学号,first_name,image_url from 消息表,jhi_user where login = 发表者学号 and 消息类型 = 2 and 评论消息id=#{id} and 逻辑删除 = 0")
    @Results({
        @Result(id =true,column ="id",property = "id"),
        @Result(column = "内容",property = "content"),
        @Result(column = "创建时间",property = "createTime"),
        @Result(column = "发表者学号",property = "postUserId"),
        @Result(column = "first_name",property = "petName"),
        @Result(column = "image_url",property = "postUserHeadPortraitUri")
    })
    List<CommentDTO> findComment(@Param("id") long parentId);

    @Select("SELECT 消息表.id,内容,创建时间,发表者学号,回复人名表.回复人名,first_name,image_url" +
        " FROM 消息表,jhi_user," +
        "(SELECT 回复人.first_name AS 回复人名,回复.id as 回复id FROM jhi_user AS 回复人,消息表 AS 回复 WHERE 回复人.login = 回复.发表者学号)" +
        " AS 回复人名表 WHERE login = 发表者学号 AND 回复id = 评论消息id AND 消息类型 = 3 AND 评论评论id = 3 and 逻辑删除 = 0")
    @Results({
        @Result(id =true,column ="id",property = "id"),
        @Result(column = "内容",property = "content"),
        @Result(column = "创建时间",property = "createTime"),
        @Result(column = "发表者学号",property = "postUserId"),
        @Result(column = "回复人名",property = "replyPetName"),
        @Result(column = "first_name",property = "petName"),
        @Result(column = "image_url",property = "postUserHeadPortraitUri")

    })
    List<cComment> findCComment(@Param("id") long parentId);

    @Delete("delete from 消息表 where id = #{id}")
    int trueDelete(@Param("id") long id);

    @Update("update 消息表 set 逻辑删除 = 1 where id = #{id}")
    int falseDelete(@Param("id") long id);

    @Delete("delete from 面向人群表 where 消息Id = #{msId}")
    int deleteForCrowd(@Param("msId") long msId);

    @Update("update 消息表 set 标题 = #{title} where id = #{id}")
    int updateTitle(@Param("title") String title,@Param("id") long id);

    @Update("update 消息表 set 内容 = #{text} where id = #{id}")
    int updateText(@Param("text") String text,@Param("id") long id);

    @Update("update 消息表 set 截至时间 = #{eTime} where id = #{id}")
    int updateETime(@Param("eTime") Date eTime,@Param("id") long id);

    @Update("update 消息表 set 标题 = #{title},内容 =  #{text},截至时间 = #{eTime} where id = #{id}")
    int updateNotice(Notice notice);


}
