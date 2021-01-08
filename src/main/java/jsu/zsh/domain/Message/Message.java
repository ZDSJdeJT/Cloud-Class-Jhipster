package jsu.zsh.domain.Message;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Message implements Tags,Comment{
    private long id;
    private String content;
    private Date createTime;
    private boolean logicallyDelete;
    private long postUserId;
    public boolean giveALike(){
        return true;
    };
    public static boolean giveALikeById(int msId){
        return true;
    }
    public static int commentById(int msId){
        return msId;
    }
    public Message comment(){
        return this;
    }
    public Message comment(Comment Comment){
        Message r = new Message();

        return r;
    }
}
