package jsu.zsh.domain.Message;

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
public class Notice extends Message{
    private String title;
    private Date cutTime;
    private HashSet<Long> forCrowd = new HashSet<>();
    private HashSet<Comment> comments = new HashSet<>();

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCutTime(Date cutTime) {
        this.cutTime = cutTime;
    }

    public Date getCutTime() {
        return cutTime;
    }

    public HashSet<Long> getForCrowd() {
        return forCrowd;
    }

    public void setComments(HashSet<Comment> comments) {
        this.comments = comments;
    }

    public void setForCrowd(HashSet<Long> forCrowd) {
        this.forCrowd = forCrowd;
    }

    public HashSet<Comment> getComments() {
        return comments;
    }
}
