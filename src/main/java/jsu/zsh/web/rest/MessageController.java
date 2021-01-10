package jsu.zsh.web.rest;

import jsu.zsh.domain.Message.Dynamic;
import jsu.zsh.domain.Message.Message;
import jsu.zsh.domain.Message.Notice;
import jsu.zsh.service.dto.CommentDTO;
import jsu.zsh.service.dto.DynamicDTO;
import jsu.zsh.service.dto.NoticeDTO;
import jsu.zsh.service.mapper.MessageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/message")
public class MessageController {
    @Autowired
    MessageMapper messageMapper;

    @GetMapping("/getDynamic")
    List<DynamicDTO> getDynamic(@RequestParam(value = "stuID")long stuID,@RequestParam(value = "page")int page){
        List<DynamicDTO> dynamicDTOList = messageMapper.findDynamic(stuID,page*10);
        for (DynamicDTO item:dynamicDTOList) {
            item.setTagsCount(messageMapper.findTagsCount(item.getId()));
            item.setCommentsCount(messageMapper.findCommentsCount(item.getId()));
        }
        return dynamicDTOList;
    }

    @GetMapping("/getNotice")
    List<NoticeDTO> getNotice(@RequestParam(value = "stuID")long stuID){
        return getNoticeORTask(5,stuID);
    }

    @GetMapping("/getTask")
    List<NoticeDTO> getTask(@RequestParam(value = "stuID")long stuID){
        return getNoticeORTask(4,stuID);
    }

   List<NoticeDTO> getNoticeORTask(int type,long stuID){
       List<NoticeDTO> data = messageMapper.findNotice(type,stuID);
       for (NoticeDTO item:data) {
           item.setTagsCount(messageMapper.findTagsCount(item.getId()));
           item.setCommentsCount(messageMapper.findCommentsCount(item.getId()));
           item.setForCrowd(messageMapper.findForCrowd(item.getId()));
       }
       return data;
    }

    @GetMapping("/getComment")
    List<CommentDTO> getComment(@RequestParam(value = "id")long id,@RequestParam(value = "stuID")long stuID){
        List<CommentDTO> commentDTOS = messageMapper.findComment(id,stuID);
        for (CommentDTO item:commentDTOS) {
            item.setComments(messageMapper.findCComment(item.getId(),stuID));
            item.setcCommentsCount(item.getComments().size());
        }
        return commentDTOS;
    }

    @PostMapping("/addDynamic")
    long addDynamic(@Valid @RequestBody String content,@RequestParam(value = "postUserId")long postUserId){
        Message dynamic =new Dynamic(content,postUserId);
        messageMapper.saveDynamic(dynamic);
        return dynamic.getId();
    }

    @PostMapping("/addNotice")
    Long addNotice(@Valid @RequestBody NoticeDTO notice){
        messageMapper.saveNotice(notice,4);
        return messageMapper.saveForCrowd(notice.getForCrowd(),notice.getId())>0?notice.getId():-1;
    }

    @PostMapping("/addComment")
    boolean addComment(@Valid @RequestBody String content,
                       @RequestParam(value = "postUserId")long postUserId,
                       @RequestParam(value = "msID")long msID){
        return messageMapper.saveComment(content, postUserId, msID)>0;
    }

    @PostMapping("/addCComment")
    boolean addCComment(@Valid @RequestBody String content,
                        @RequestParam(value = "postUserId")long postUserId,
                        @RequestParam(value = "commentId")long commentId,
                        @RequestParam(value = "replyId") Long replyId,
                        @RequestParam(value = "msID")long msID){
        return messageMapper.saveCComment(content,postUserId,commentId,replyId,msID)>0;
    }

    @PostMapping("/addTags")
    boolean addTags(@Valid @RequestBody long stuID,@RequestParam(value = "msID")long msID){
        return messageMapper.saveTags(msID,stuID)>0;
    }

    @PostMapping("/addTask")
    Long addTask(@Valid @RequestBody NoticeDTO notice){
        messageMapper.saveNotice(notice,4);
        return messageMapper.saveForCrowd(notice.getForCrowd(),notice.getId())>0?notice.getId():-1;
    }
    @PostMapping("/updateText")
    boolean updateText(@RequestParam(value = "text")String text,
                       @RequestParam(value = "id")long id){
        return messageMapper.updateText(text,id)>0;
    }

    @PostMapping("/updateForCrowd")
    boolean updateForCrowd(@Valid @RequestBody List<Long> crowd,@RequestParam(value = "msID")long msID){
        if(messageMapper.deleteForCrowd(msID) <=0) return false;
        return messageMapper.saveForCrowd(crowd,msID)>0;
    }

    @PostMapping("/updateNotice")
    boolean updateNotice(@Valid @RequestBody Notice notice){
        return messageMapper.updateNotice(notice)>0;
    }

    @GetMapping("/trueDeleteMs")
    boolean trueDeleteMs(@RequestParam(value = "id")long id){

        return messageMapper.trueDelete(id)>1;
    }

    @GetMapping("/falseDeleteMs")
    boolean falseDeleteMs(@RequestParam(value = "id")long id){
        return messageMapper.falseDelete(id)>0;
    }

    @PostMapping("/updateETime")
    boolean updateETime(@Valid @RequestBody Date eTime, @RequestParam(value = "msID")long msID){
        return messageMapper.updateETime(eTime,msID)>0;
    }

    @PostMapping("/updateTitle")
    boolean updateTitle(@Valid @RequestBody String title, @RequestParam(value = "msID")long msID){
        return messageMapper.updateTitle(title,msID)>0;
    }
}
