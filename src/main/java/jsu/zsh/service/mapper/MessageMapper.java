package jsu.zsh.service.mapper;


import jsu.zsh.domain.Message.Message;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;

@Mapper
public interface MessageMapper {
    @Insert("insert into 消息表(内容,创建时间,发表者学号) values(#{content},now(),#{postUserId})")
    @Options(useGeneratedKeys = true)
    int save(Message message);

    @Select("select * from 消息表 where 创建时间> #{sTime} and 创建时间 < #{eTime}")
    @Results({
        @Result(id =true,column ="id",property = "id"),
        @Result(column = "创建时间",property = "createTime")

    })
    Message findByTime(@Param("sTime") Date sTime, @Param("eTime") Date eTime);

    @Select("select * from 消息表")
    @Results({
        @Result(id =true,column ="id",property = "id"),
        @Result(column = "创建时间",property = "createTime")

    })
    List<Message> findAll();
}
