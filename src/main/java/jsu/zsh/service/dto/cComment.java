package jsu.zsh.service.dto;

import java.util.Date;

public class cComment{
    private long id;
    private String content;
    private String petName;
    private Date createTime;
    private long postUserId;
    private Integer tagsCount;
    private String replyPetName;
    private String userHasTags;
    private String postUserHeadPortraitUri;
    private int commentId;

    public Date getCreateTime() {
        return createTime;
    }

   public boolean getUserHasTags(){
        return userHasTags != null && !userHasTags.equals("");
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

    public String getPetName() {
        return petName;
    }

    public Integer getTagsCount() {
        return tagsCount;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }


    public void setTagsCount(Integer tagsCount) {
        this.tagsCount = tagsCount;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }
}

