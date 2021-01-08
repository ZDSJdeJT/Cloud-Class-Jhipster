package jsu.zsh.web.rest;

import jsu.zsh.domain.Course;
import jsu.zsh.service.mapper.CourseMapper;
import jsu.zsh.service.mapper.TermMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/course")
public class CourseController {
    @Autowired
    CourseMapper courseMapper;
    @Autowired
    TermMapper termMapper;

    @GetMapping("/getPresentCourse")
    List<Course> getPresentCourse(){
        int nowTermId =  termMapper.findByNow().get().getId();

        return courseMapper.findByTermId(nowTermId);
    }

    @GetMapping("/deleteById")
    boolean deleteById(@RequestParam(value = "Id")long id){
        return courseMapper.deleteById(id)>0;
    }

    @PostMapping("/addCourse")
    boolean addCourse(@Valid @RequestBody Course course){
        return courseMapper.save(course)>0;
    }

    @PostMapping("/upDateCourse")
    boolean upDateCourse(@Valid @RequestBody Course course){
        return courseMapper.upDate(course)>0;
    }


}
