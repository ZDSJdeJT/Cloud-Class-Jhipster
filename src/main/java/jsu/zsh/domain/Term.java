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
}
