package jsu.zsh.service.mapper;

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
    int saveDynamic(DynamicDTO message);

    @Insert("insert into 消息表(标题,内容,创建时间,截至时间,发表者学号,消息类型) values(#{title},#{content},now(),#{cutTime},#{postUserId},#{type})")
    @Options(useGeneratedKeys = true,keyProperty ="id")
    int saveNotice(NoticeDTO notice,@Param("type")int type);

    @InsertProvider(type = messageProvider.class, method = "insertForCrowd")
    Integer saveForCrowd(List<Long> list,long id);

    @Insert("insert into 点赞表(消息id, 点赞人学号) value (#{msID},#{stuID})")
    Integer saveTags(@Param("msID")long msID,@Param("stuID")long stuID);


    @Insert("insert into 消息表(内容,创建时间,发表者学号,评论消息id,消息类型) values(#{content},now(),#{postUserID},#{msID},2)")
    int saveComment(@Param("content")String content,@Param("postUserID")long postUserID,@Param("msID")long msID);


    @Insert("insert into 消息表(内容,创建时间,发表者学号,回复评论id,评论评论id,评论消息id,消息类型) values(#{content},now(),#{postUserID},#{replyId},#{commentId},#{msID},3)")
    int saveCComment(@Param("content")String content,@Param("postUserID")long postUserID,@Param("replyId")Long replyId,@Param("commentId")long commentId,@Param("msID")long msID);

    @Insert("insert into 消息表(标题,内容,创建时间,截至时间,发表者学号,消息类型) values(#{title},#{content},now(),#{cutTime},#{postUserId},4)")
    int saveTask(Notice notice);

    @Select("select * from (select 消息表.id as 作业表id,标题,内容,创建时间,截至时间,发表者学号,first_name,image_url from 消息表,jhi_user,面向人群表 where jhi_user.login = 发表者学号 and 面向人群表.消息Id = 消息表.id and 面向人群表.学号 = #{stuID}  and  截至时间>now() and 消息类型 = #{type} and 逻辑删除 = 0) as 作业表 LEFT JOIN 点赞表 on 点赞表.消息id = 作业表.作业表id and 点赞表.点赞人学号 = #{stuID}")
    @Results({
        @Result(id =true,column ="作业表id",property = "id"),
        @Result(column = "标题",property = "title"),
        @Result(column = "内容",property = "content"),
        @Result(column = "创建时间",property = "createTime"),
        @Result(column = "截至时间",property = "cutTime"),
        @Result(column = "发表者学号",property = "postUserId"),
        @Result(column = "first_name",property = "petName"),
        @Result(column = "image_url",property = "postUserHeadPortraitUri"),
    })
    List<NoticeDTO> findNotice(@Param("type")long type,@Param("stuID")long stuID);

    @Select("select 消息表.id as 作业表id,标题,内容,创建时间,截至时间,发表者学号,first_name,image_url from 消息表,jhi_user where jhi_user.login = 发表者学号 and 消息类型 = #{type}")
    @Results({
        @Result(id =true,column ="作业表id",property = "id"),
        @Result(column = "标题",property = "title"),
        @Result(column = "内容",property = "content"),
        @Result(column = "创建时间",property = "createTime"),
        @Result(column = "截至时间",property = "cutTime"),
        @Result(column = "发表者学号",property = "postUserId"),
        @Result(column = "first_name",property = "petName"),
        @Result(column = "image_url",property = "postUserHeadPortraitUri"),
    })
    List<NoticeDTO> findAllNotice(@Param("type")long type);

    @Select("select 学号 from 面向人群表 where 消息Id =  #{msID}")
    @Results({
        @Result(column ="学号",property = "id"),
    })
    List<Long> findForCrowd(@Param("msID")long msID);


    @Select("select count(点赞人学号) from 点赞表 where 消息id = #{msID}")
    Integer findTagsCount(@Param("msID") long msID);

    @Select("select count(评论消息id) from 消息表 where 评论消息id = #{msID}")
    Integer findCommentsCount(@Param("msID") long msID);

    @Select("select * from (select 消息表.id as 消息表id,内容,创建时间,发表者学号,first_name,image_url from 消息表,jhi_user where login = 发表者学号 and 消息类型 = 1 and 逻辑删除 = 0 ORDER BY 消息表id desc) as 动态表 LEFT JOIN 点赞表 on 点赞表.消息id = 动态表.消息表id and 点赞表.点赞人学号 = #{stuID} LIMIT #{Index},10")
    @Results({
        @Result(id =true,column ="消息表id",property = "id"),
        @Result(column = "内容",property = "content"),
        @Result(column = "创建时间",property = "createTime"),
        @Result(column = "发表者学号",property = "postUserId"),
        @Result(column = "first_name",property = "petName"),
        @Result(column = "image_url",property = "postUserHeadPortraitUri"),
        @Result(column = "点赞人学号",property = "userHasTags")
    })
    List<DynamicDTO> findDynamic(@Param("stuID") long stuID,@Param("Index") int index);

    @Select("select * from (select 消息表.id as 消息表id,内容,创建时间,发表者学号,first_name,image_url from 消息表,jhi_user where login = 发表者学号 and 消息类型 = 2 and 评论消息id=#{id} and 逻辑删除 = 0) as 动态表 LEFT JOIN 点赞表 on 点赞表.消息id = 动态表.消息表id and 点赞表.点赞人学号 = #{stuID}")
    @Results({
        @Result(id =true,column ="消息表id",property = "id"),
        @Result(column = "内容",property = "content"),
        @Result(column = "创建时间",property = "createTime"),
        @Result(column = "发表者学号",property = "postUserId"),
        @Result(column = "first_name",property = "petName"),
        @Result(column = "image_url",property = "postUserHeadPortraitUri"),
        @Result(column = "点赞人学号",property = "userHasTags")
    })
    List<CommentDTO> findComment(@Param("id") long parentId,@Param("stuID") long stuID);

    @Select("select * from (SELECT 消息表.id as 消息表id,内容,创建时间,发表者学号,回复人名表.回复人名,first_name,image_url FROM 消息表,jhi_user,(SELECT 回复人.first_name AS 回复人名,回复.id as 回复id FROM jhi_user AS 回复人,消息表 AS 回复 WHERE 回复人.login = 回复.发表者学号)\n" +
        "AS 回复人名表 WHERE login = 发表者学号 AND 回复id = 评论消息id AND 消息类型 = 3 AND 评论评论id = #{id} and 逻辑删除 = 0) as 评论回复表 LEFT JOIN 点赞表 on 点赞表.消息id = 评论回复表.消息表id and 点赞表.点赞人学号 = #{stuID}")
    @Results({
        @Result(id =true,column ="消息表id",property = "id"),
        @Result(column = "内容",property = "content"),
        @Result(column = "创建时间",property = "createTime"),
        @Result(column = "发表者学号",property = "postUserId"),
        @Result(column = "回复人名",property = "replyPetName"),
        @Result(column = "first_name",property = "petName"),
        @Result(column = "image_url",property = "postUserHeadPortraitUri"),
        @Result(column = "点赞人学号",property = "userHasTags")

    })
    List<cComment> findCComment(@Param("id") long parentId,@Param("stuID") long stuID);

    @Delete("delete from 消息表 where id = #{id}")
    int trueDelete(@Param("id") long id);

    @Update("update 消息表 set 逻辑删除 = 1 where id = #{id}")
    int falseDelete(@Param("id") long id);

    @Delete("delete from 面向人群表 where 消息Id = #{msId}")
    int deleteForCrowd(@Param("msId") long msId);

    @Delete("delete from 面向人群表 where 消息Id = #{msId}  and 学号 = #{stuId}")
    int deleteForCrowdByStuID(@Param("msId") long msId,@Param("stuId") long stuId);

    @Update("update 消息表 set 标题 = #{title} where id = #{id}")
    int updateTitle(@Param("title") String title,@Param("id") long id);

    @Update("update 消息表 set 内容 = #{text} where id = #{id}")
    int updateText(@Param("text") String text,@Param("id") long id);

    @Update("update 消息表 set 截至时间 = #{eTime} where id = #{id}")
    int updateETime(@Param("eTime") Date eTime,@Param("id") long id);

    @Update("update 消息表 set 标题 = #{title},内容 =  #{text},截至时间 = #{eTime} where id = #{id}")
    int updateNotice(Notice notice);

    @Insert("insert into 作业提交表(作业id, 提交人学号, 文件路径, 文本内容) value (#{taskID},#{stuID},#{filePath},#{taskText})")
    Integer saveTaskState(@Param("taskID")long taskID,@Param("stuID")long stuID,@Param("filePath")String filePath,@Param("taskText")String text);

}
