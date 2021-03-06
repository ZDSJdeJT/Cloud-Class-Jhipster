package jsu.zsh.domain.Message;

import jsu.zsh.service.util.dateUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Dynamic extends Message{
    private List<Comment> Comments = new ArrayList<>();

    public void setComments(List<Comment> comments) {
        Comments = comments;
    }

    public Dynamic(String context, long postUserId){
        this.setContent(context);
        this.setPostUserId(postUserId);
    }
    @Override
    public String getFormatCreateTime() {
        return dateUtil.sdf.format(super.getCreateTime());
    }
}
