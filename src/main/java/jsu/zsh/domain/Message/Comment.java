package jsu.zsh.domain.Message;

public interface Comment {
    Integer messageId = 0;
    Integer commentId = 0;
    //评论动态 作业 公告
    Message comment();
    //评论 评论
    Message comment(Comment comment);
}
