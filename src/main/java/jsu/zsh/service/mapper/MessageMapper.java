package jsu.zsh.service.mapper;


import jsu.zsh.domain.Message.Message;
import jsu.zsh.domain.Message.Notice;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;

@Mapper
public interface MessageMapper {

    @Insert("insert into 消息表(内容,创建时间,发表者学号,消息类型) values(#{content},now(),#{postUserId},1)")
    @Options(useGeneratedKeys = true)
    int saveDynamic(Message message);

    @Insert("insert into 消息表(标题,内容,创建时间,截至时间,发表者学号,消息类型) values(#{title},#{content},now(),#{cutTime},#{postUserId},5)")
    @Options(useGeneratedKeys = true)
    int saveNotice(Notice notice);

    @Insert("insert into 消息表(内容,创建时间,发表者学号,回复评论id,消息类型) values(#{title},#{content},now(),#{cutTime},#{postUserId},#{messageId},2)")
    @Options(useGeneratedKeys = true)
    int saveComment(Message message);


    @Insert("insert into 消息表(内容,创建时间,发表者学号,回复评论id,消息类型) values(#{title},#{content},now(),#{cutTime},#{postUserId},#{commentId},3)")
    @Options(useGeneratedKeys = true)
    int saveCComment(Message message);

    @Insert("insert into 消息表(标题,内容,创建时间,截至时间,发表者学号,消息类型) values(#{title},#{content},now(),#{cutTime},#{postUserId},4)")
    @Options(useGeneratedKeys = true)
    int saveTask(Notice notice);

    @Select("select * from 消息表 where 截至时间>now() and 消息类型 = 5 and 逻辑删除 = 0")
    @Results({
        @Result(id =true,column ="id",property = "id"),
        @Result(column = "创建时间",property = "createTime")
    })
    List<Notice> findNotice();

    @Select("select * from 消息表 where 截至时间>now() and 消息类型 = 4 and 逻辑删除 = 0")
    @Results({
        @Result(id =true,column ="id",property = "id"),
        @Result(column = "创建时间",property = "createTime")
    })
    List<Notice> findTask();

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
        @Result(column = "创建时间",property = "createTime")

    })
    List<Message> findMsByType(@Param("type") int type);

    //查询
    @Select("select * from 消息表 where 消息类型 = 2 or 消息类型 = 3 and 逻辑删除 = 0")
    @Results({
        @Result(id =true,column ="id",property = "id"),
        @Result(column = "创建时间",property = "createTime")

    })
    List<Message> findComment();


    @Delete("delete from 消息表 where id = #{id}")
    int trueDelete(@Param("id") long id);


    @Update("update 消息表 set 逻辑删除 = 1 where id = #{id}")
    int falseDelete(@Param("id") long id);


    @Update("update 消息表 set 标题 = #{title} where id = #{id}")
    int updateTitle(@Param("title") String title,@Param("id") long id);

    @Update("update 消息表 set 内容 = #{text} where id = #{id}")
    int updateText(@Param("text") String text,@Param("id") long id);

    @Update("update 消息表 set 截至时间 = #{eTime} where id = #{id}")
    int updateETime(@Param("eTime") Date eTime,@Param("id") long id);

    @Update("update 消息表 set 标题 = #{title},内容 =  #{text},截至时间 = #{eTime} where id = #{id}")
    int updateNotice(Notice notice);

}
