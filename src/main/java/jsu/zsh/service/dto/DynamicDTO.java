package jsu.zsh.service.dto;

import jsu.zsh.service.util.dateUtil;

import java.util.Date;

public class DynamicDTO
{
    private long id;
    private String content;
    private Date createTime;
    private long postUserId;
    private String postUserHeadPortraitUri;

    public long getId() {
        return id;
    }

    public void setPostUserId(long postUserId) {
        this.postUserId = postUserId;
    }

    public long getPostUserId() {
        return postUserId;
    }

    public String getContent() {
        return content;
    }

    public String getCreateTime() {
        return dateUtil.sdf.format(createTime);
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setPostUserHeadPortraitUri(String postUserHeadPortraitUri) {
        this.postUserHeadPortraitUri = postUserHeadPortraitUri;
    }
}
