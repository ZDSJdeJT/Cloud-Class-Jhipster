package jsu.zsh.service.mapper;

import jsu.zsh.domain.Course;
import jsu.zsh.domain.Term;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Optional;

@Mapper
public interface TermMapper {
    @Insert("insert into 学期表(学期名称,学期开始时间,学期结束时间) values(#{name},#{sTime},#{eTime})")
    @Options(useGeneratedKeys = true)
    int save(Term term);

    @Select("select * from 学期表 where id = #{id}")
    Optional<Term> findById(@Param("id")long id);

    @Select("select * from 学期表")
    List<Term> findAll();

    @Update("update 学期表 set 学期名称 = #{name},学期开始时间 =  #{sTime},学期结束时间= #{eTime}")
    int update(Term term);

    @Delete("delete from 学期表 where id=#{id}")
    int delete(@Param("id")long id);
}
