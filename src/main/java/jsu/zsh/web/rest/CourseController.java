package jsu.zsh.web.rest;

import jsu.zsh.domain.Course;
import jsu.zsh.service.mapper.CourseMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/course")
public class CourseController {
    CourseMapper courseMapper;

    @GetMapping("/getCourse")
    List<Course> getCourses(){
        return courseMapper.findAll();
    }
}
