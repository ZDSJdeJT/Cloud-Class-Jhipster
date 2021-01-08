package jsu.zsh.service.mapper;

import jsu.zsh.domain.Student;
import org.apache.ibatis.annotations.*;

import java.util.HashSet;

@Mapper
public interface StudentMapper {
    @Select("select * from 用户表")
    HashSet<Student> findAll();

    @Select("select * from 用户表 where 学号 = #{id}")
    Student findById(@Param("id")long id);

    @Insert("insert into 用户表(学号,姓名,昵称,性别,密码,权限,班级,头像路径)" +
        "values(#{id},#{name},#{petName},#{sex},#{passWord},#{power.ordinal()},#{belongsClass},#{headPortrait})")
    int save(Student student);

}
