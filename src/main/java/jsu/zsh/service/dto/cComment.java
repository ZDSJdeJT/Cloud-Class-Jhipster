package jsu.zsh.service.dto;

import java.util.Date;

public class cComment{
    private long id;
    private String content;
    private Date createTime;
    private long postUserId;
    private String postUserHeadPortraitUri;
    private int commentId = 0;

    public Date getCreateTime() {
        return createTime;
    }

    public String getContent() {
        return content;
    }

    public long getPostUserId() {
        return postUserId;
    }

    public long getId() {
        return id;
    }

    public int getCommentId() {
        return commentId;
    }

    public String getPostUserHeadPortraitUri() {
        return postUserHeadPortraitUri;
    }
}
