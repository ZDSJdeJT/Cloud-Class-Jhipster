package jsu.zsh.web.rest;

import jsu.zsh.domain.Message.Notice;
import jsu.zsh.service.dto.CommentDTO;
import jsu.zsh.service.dto.DynamicDTO;
import jsu.zsh.service.dto.NoticeDTO;
import jsu.zsh.service.dto.TaskDTO;
import jsu.zsh.service.mapper.MessageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/message")
public class MessageController {
    @Autowired
    MessageMapper messageMapper;

    @GetMapping("/getDynamic")
    List<DynamicDTO> getDynamic(@RequestParam(value = "stuID")long stuID,@RequestParam(value = "page")int page){
        System.out.println(messageMapper.findDynamic(stuID,page*10));
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

    @GetMapping("/getTaskByID")
    TaskDTO getTaskByID(@RequestParam(value = "stuID")long stuID,
                        @RequestParam(value = "msID")long msID){
        TaskDTO task = messageMapper.findTaskById(msID,stuID);
        task.setTagsCount(messageMapper.findTagsCount(task.getId()));
        task.setCommentsCount(messageMapper.findCommentsCount(task.getId()));
        task.setForCrowd(messageMapper.findForCrowdName(task.getId()));
        task.setFinishStu(messageMapper.findFinishStu(task.getId()));
        return task;
    }


    List<NoticeDTO> getNoticeORTask(int type,long stuID){
       List<NoticeDTO> data = messageMapper.findNotice(type,stuID);
       for (NoticeDTO item:data) {
           item.setTagsCount(messageMapper.findTagsCount(item.getId()));
           item.setCommentsCount(messageMapper.findCommentsCount(item.getId()));
       }
       return data;
    }
    List<NoticeDTO> getAllNoticeORTask(int type){
        List<NoticeDTO> data = messageMapper.findAllNotice(type);
        for (NoticeDTO item:data) {
            item.setTagsCount(messageMapper.findTagsCount(item.getId()));
            item.setCommentsCount(messageMapper.findCommentsCount(item.getId()));
            item.setForCrowd(messageMapper.findForCrowd(item.getId()));
        }
        return data;
    }

    @GetMapping("/getAllNotice")
    List<NoticeDTO> getAllNotice(){
        return getAllNoticeORTask(5);
    }

    @GetMapping("/getAllTask")
    List<NoticeDTO> getAllTask(){
        return getAllNoticeORTask(4);
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
        DynamicDTO dynamic =new DynamicDTO();
        dynamic.setContent(content);
        dynamic.setPostUserId(postUserId);
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
                        @RequestParam(value = "replyId") long replyId,
                        @RequestParam(value = "msID")long msID){
        return messageMapper.saveCComment(content,postUserId,replyId,commentId,msID)>0;
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

    @GetMapping("/trueDeleteNotice")
    boolean trueDeleteNotice(@RequestParam(value = "id")long id){
        messageMapper.deleteForCrowd(id);
        return messageMapper.trueDelete(id)>1;
    }

    @PostMapping("/upTask")
    public String upTask(@RequestBody MultipartFile file,
                         @RequestParam(required = false,value = "taskText",defaultValue = "") String text,
                         @RequestParam(value = "taskID") long taskID,
                         @RequestParam(value = "stuID")long stuID){
        if (file.isEmpty()) {
            return "上传失败，请选择文件";
        }
        StringBuilder taskPath = new StringBuilder();
        taskPath.append("C:/taskFiles/").append(taskID).append("/").append(file.getOriginalFilename());
        File dest = new File(taskPath.toString());
        try {
            if(!dest.getParentFile().exists()){
                dest.getParentFile().mkdirs();
            }
            if(dest.exists()){
                dest.delete();
            }
            else{
                messageMapper.saveTaskState(taskID,stuID,taskPath.toString(),text);
                messageMapper.deleteForCrowdByStuID(taskID,stuID);
            }
            file.transferTo(dest);
            return "上传成功";
        } catch (IOException e) {
            System.out.println(e);
        }
        return "上传失败！";
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
