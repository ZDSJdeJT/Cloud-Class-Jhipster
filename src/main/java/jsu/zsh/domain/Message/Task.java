package jsu.zsh.domain.Message;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Task extends Notice{
    private HashSet<outCome> outcomes = new HashSet<>();
    private HashSet<Comment> comments = new HashSet<>();
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    class outCome{
        private int id;
        private int taskId;
        private int stuId;
        private String filePath;
        private String text;
    }
}
