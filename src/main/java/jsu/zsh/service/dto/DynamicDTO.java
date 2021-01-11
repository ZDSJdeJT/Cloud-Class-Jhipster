package jsu.zsh.service.dto;

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
    private String userHasTags;

    public DynamicDTO(String content, long postUserId){
        this.content = content;
        this.postUserId = postUserId;
    }

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


    public boolean getUserHasTags(){
        return userHasTags != null && !userHasTags.equals("");
    }

    public void setTagsCount(Integer tagsCount) {
        this.tagsCount = tagsCount;
    }

    public void setCommentsCount(Integer commentsCount) {
        this.commentsCount = commentsCount;
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

    public Date getCreateTime() {
        return createTime;
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
