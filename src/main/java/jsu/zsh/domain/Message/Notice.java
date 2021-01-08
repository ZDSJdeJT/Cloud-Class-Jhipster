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
}
