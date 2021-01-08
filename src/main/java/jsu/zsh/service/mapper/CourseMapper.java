package jsu.zsh.service.mapper;

import jsu.zsh.domain.Course;
import org.apache.ibatis.annotations.*;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Mapper
public interface CourseMapper {
    @Insert("insert into 课程表(课程名称,课程起始周,课程结束周,是否单双周,学期Id,备注) values(#{content},now(),#{postUserId})")
    @Options(useGeneratedKeys = true)
    int save(Course course);

    @Select("select * from 课程表")
    List<Course> findAll();

    @Select("select * from 课程表 where 学期Id = #{id}")
    List<Course> findByTermId(@RequestParam(value = "Id")long id);

    @Delete("delete from 课程表 where id=#{id}")
    int deleteById(@RequestParam(value = "Id")long id);


    @Update("update 课程表 set 课程名称 = #{name},课程结束周 = #{eTime},课程起始周 = #{sTime},是否单双周 = #{isSingle},学期Id = #{termId}")
    int upDate(Course course);


}
