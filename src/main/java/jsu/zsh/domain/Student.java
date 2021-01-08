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

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public Boolean getSex() {
        return sex;
    }

    public void setSex(Boolean sex) {
        this.sex = sex;
    }

    public String getPassWord() {
        return passWord;
    }

    public int getPower() {
        return power.ordinal();
    }

    public void setPower(Power power) {
        this.power = power;
    }

    public String getBelongsClass() {
        return belongsClass;
    }

    public void setBelongsClass(String belongsClass) {
        this.belongsClass = belongsClass;
    }

    public String getHeadPortrait() {
        return headPortrait;
    }

    public void setHeadPortrait(String headPortrait) {
        this.headPortrait = headPortrait;
    }
}
