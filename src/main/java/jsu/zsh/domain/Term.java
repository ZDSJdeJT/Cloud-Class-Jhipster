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
public class Term {
    private int id;
    private String name;
    private Date sTime;
    private Date eTime;
    private HashSet<Course> courses = new HashSet<>();

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getsTime() {
        return sTime;
    }

    public void setsTime(Date sTime) {
        this.sTime = sTime;
    }

    public Date geteTime() {
        return eTime;
    }

    public void seteTime(Date eTime) {
        this.eTime = eTime;
    }

    public HashSet<Course> getCourses() {
        return courses;
    }

    public void setCourses(HashSet<Course> courses) {
        this.courses = courses;
    }
}
