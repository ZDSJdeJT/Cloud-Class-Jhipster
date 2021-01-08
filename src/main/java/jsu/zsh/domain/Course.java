package jsu.zsh.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.HashSet;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Course {
    private int id;
    private String name;
    private Date sTime;
    private Date eTime;
    private Boolean isSingle;
    private HashSet<CourseTime> courseTimes = new HashSet<>();
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    class CourseTime{
        private int id;
        private int courseId;
        private int day;
        private int[] sequence;
    }
}
