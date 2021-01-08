package jsu.zsh.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Student {
    enum Power{
        ADMIN,USER,
    }

    private long id;
    private String name;
    private String petName;
    private Boolean sex;
    private String passWord;
    private Power power;
    private String belongsClass;
    private String headPortrait;


}
