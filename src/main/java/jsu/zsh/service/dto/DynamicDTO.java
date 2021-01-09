package jsu.zsh.service.dto;

import jsu.zsh.service.util.dateUtil;

import java.util.Date;

public class DynamicDTO
{
    private long id;
    private String content;
    private String petName;
    private Date createTime;
    private long postUserId;
    private Integer tagsCount;
    private Integer commentsCount;
    private String postUserHeadPortraitUri;

    public long getId() {
        return id;
    }

    public Integer getTagsCount() {
        return tagsCount;
    }


    public String getPetName() {
        return petName;
    }

    public String getPostUserHeadPortraitUri() {
        return postUserHeadPortraitUri;
    }

    public void setTagsCount(Integer tagsCount) {
        this.tagsCount = tagsCount;
    }

    public Integer getCommentsCount() {
        return commentsCount;
    }

    public void setPetName(String petName) {
        this.petName = petName;
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
