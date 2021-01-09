package jsu.zsh.web.rest;

import jsu.zsh.domain.Message.Dynamic;
import jsu.zsh.domain.Message.Message;
import jsu.zsh.domain.Message.Notice;
import jsu.zsh.service.dto.CommentDTO;
import jsu.zsh.service.dto.DynamicDTO;
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
        return messageMapper.findDynamic();
    }

    @GetMapping("/getNotice")
    List<Notice> getNotice(){
        return messageMapper.findNotice();
    }

    @GetMapping("/getTask")
    List<Notice> getTask(){
        return messageMapper.findTask();
    }

    @GetMapping("/getComment")
    List<CommentDTO> getComment(long id){
        List<CommentDTO> commentDTOS = messageMapper.findComment(id);
        for (CommentDTO item:commentDTOS) {
            item.setComments(messageMapper.findCComment(item.getId()));
            item.setcCommentsCount(item.getComments().size());
        }
        return commentDTOS;
    }

    @PostMapping("/addDynamic")
    long addDynamic(@Valid @RequestBody String content,long postUserId){
        Message dynamic =new Dynamic(content,postUserId);
        messageMapper.saveDynamic(dynamic);
        return dynamic.getId();
    }

    @PostMapping("/addNotice")
    boolean addNotice(@Valid @RequestBody Notice notice){
        return messageMapper.saveNotice(notice)>0;
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
