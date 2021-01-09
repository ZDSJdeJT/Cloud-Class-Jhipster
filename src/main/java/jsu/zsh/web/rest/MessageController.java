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
import java.util.List;

@RestController
@RequestMapping("/api/message")
public class MessageController {
    @Autowired
    MessageMapper messageMapper;

    @GetMapping("/getDynamic")
    List<DynamicDTO> getDynamic(){
        List<DynamicDTO> dynamicDTOList = messageMapper.findDynamic();
        for (DynamicDTO item:dynamicDTOList) {
            item.setTagsCount(messageMapper.findTagsCount(item.getId()));
            item.setCommentsCount(messageMapper.findCommentsCount(item.getId()));
        }
        return dynamicDTOList;
    }

    @GetMapping("/getNotice")
    List<NoticeDTO> getNotice(){
        return getNoticeORTask(5);
    }

    @GetMapping("/getTask")
    List<NoticeDTO> getTask(){
        return getNoticeORTask(4);
    }

   List<NoticeDTO> getNoticeORTask(int type){
       List<NoticeDTO> data = messageMapper.findNotice(type);
       for (NoticeDTO item:data) {
           item.setTagsCount(messageMapper.findTagsCount(item.getId()));
           item.setCommentsCount(messageMapper.findCommentsCount(item.getId()));
           item.setForCrowd(messageMapper.findForCrowd(item.getId()));
       }
       return data;
    }

    @GetMapping("/getComment")
    List<CommentDTO> getComment(@RequestParam(value = "id")long id){
        List<CommentDTO> commentDTOS = messageMapper.findComment(id);
        for (CommentDTO item:commentDTOS) {
            item.setComments(messageMapper.findCComment(item.getId()));
            item.setcCommentsCount(item.getComments().size());
        }
        return commentDTOS;
    }

    @PostMapping("/addDynamic")
    long addDynamic(@Valid @RequestBody String content,@RequestParam(value = "postUserId",required = true)long postUserId){
        Message dynamic =new Dynamic(content,postUserId);
        messageMapper.saveDynamic(dynamic);
        return dynamic.getId();
    }

    @PostMapping("/addNotice")
    boolean addNotice(@Valid @RequestBody String content
        ,@RequestParam(value = "postUserId",required = true)long postUserId){
        return false;
    }

    @PostMapping("/addComment")
    boolean addComment(@Valid @RequestBody Message message){
        return messageMapper.saveComment(message)>0;
    }

    @PostMapping("/addCComment")
    boolean addCComment(@Valid @RequestBody Message message){
        return messageMapper.saveCComment(message)>0;
    }

    @PostMapping("/addTask")
    boolean addTask(@Valid @RequestBody Notice notice){
        return messageMapper.saveTask(notice)>0;
    }

    @PostMapping("/updateText")
    boolean updateText(@RequestParam(value = "text")String text,
                       @RequestParam(value = "id")long id){
        return messageMapper.updateText(text,id)>0;
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
}
