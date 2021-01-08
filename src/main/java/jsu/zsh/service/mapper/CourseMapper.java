package jsu.zsh.service.mapper;

import jsu.zsh.domain.Course;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CourseMapper {
    @Insert("insert into 课程表(课程名称,课程起始周,课程结束周,是否单双周,学期Id,备注) values(#{content},now(),#{postUserId})")
    @Options(useGeneratedKeys = true)
    int save(Course course);

    @Select("select * from 课程表")
    List<Course> findAll();

    @Select("SELECT * from 课程表 where 学期Id = #{}")
    List<Course> findByTermId();

}
