package jsu.zsh.service.dto;

import jsu.zsh.service.util.dateUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CommentDTO {
    private long id;
    private String content;
    private String petName;
    private Date createTime;
    private long postUserId;
    private Integer tagsCount;
    private Integer cCommentsCount;
    private String postUserHeadPortraitUri;
    private String userHasTags;
    private List<cComment> comments;

    public String getPostUserHeadPortraitUri() {
        return postUserHeadPortraitUri;
    }

    public Integer getcCommentsCount() {
        return cCommentsCount;
    }

    public Integer getTagsCount() {
        return tagsCount;
    }

    public boolean getUserHasTags(){
        return userHasTags != null && !userHasTags.equals("");
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public void setcCommentsCount(Integer cCommentsCount) {
        this.cCommentsCount = cCommentsCount;
    }

    public void setTagsCount(Integer tagsCount) {
        this.tagsCount = tagsCount;
    }

    public String getPetName() {
        return petName;
    }

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

    public List<cComment> getComments() {
        return comments;
    }

    public void setComments(List<cComment> comments) {
        this.comments = comments;
    }

    public void setPostUserHeadPortraitUri(String postUserHeadPortraitUri) {
        this.postUserHeadPortraitUri = postUserHeadPortraitUri;
    }
}
