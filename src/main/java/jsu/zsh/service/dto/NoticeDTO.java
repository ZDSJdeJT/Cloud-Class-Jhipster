package jsu.zsh.service.dto;

import java.util.Date;
import java.util.List;

public class NoticeDTO {
    private long id;
    private String title;
    private String content;
    private String petName;
    private Date createTime;
    private Date cutTime;
    private long postUserId;
    private Integer tagsCount;
    private Integer commentsCount;
    private String postUserHeadPortraitUri;
    List<Long> forCrowd;

    public void setCommentsCount(Integer commentsCount) {
        this.commentsCount = commentsCount;
    }

    public void setTagsCount(Integer tagsCount) {
        this.tagsCount = tagsCount;
    }


    public List<Long> getForCrowd() {
        return forCrowd;
    }

    public void setForCrowd(List<Long> forCrowd) {
        this.forCrowd = forCrowd;
    }

    public Integer getCommentsCount() {
        return commentsCount;
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

    public long getId() {
        return id;
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

    public Date getCutTime() {
        return cutTime;
    }

    public String getTitle() {
        return title;
    }


}

