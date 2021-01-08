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
    private long termId;
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

        public int getId() {
            return id;
        }

        public int getCourseId() {
            return courseId;
        }

        public int getDay() {
            return day;
        }

        public int[] getSequence() {
            return sequence;
        }

        public void setCourseId(int courseId) {
            this.courseId = courseId;
        }

        public void setDay(int day) {
            this.day = day;
        }

        public void setSequence(int[] sequence) {
            this.sequence = sequence;
        }
    }

    public int getId() {
        return id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getSingle() {
        return isSingle;
    }

    public void setSingle(Boolean single) {
        isSingle = single;
    }

    public Date geteTime() {
        return eTime;
    }

    public void seteTime(Date eTime) {
        this.eTime = eTime;
    }

    public Date getsTime() {
        return sTime;
    }

    public void setsTime(Date sTime) {
        this.sTime = sTime;
    }

    public long getTermId() {
        return termId;
    }

    public void setTermId(long termId) {
        this.termId = termId;
    }

    public HashSet<CourseTime> getCourseTimes() {
        return courseTimes;
    }

    public void setCourseTimes(HashSet<CourseTime> courseTimes) {
        this.courseTimes = courseTimes;
    }
}
