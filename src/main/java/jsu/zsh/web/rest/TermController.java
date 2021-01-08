package jsu.zsh.web.rest;

import jsu.zsh.domain.Term;
import jsu.zsh.service.mapper.TermMapper;
import jsu.zsh.web.rest.vm.LoginVM;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/term")
public class TermController {
    TermMapper termMapper;

    @GetMapping("/getTerms")
    List<Term> getTerms(){
        return termMapper.findAll();
    }

    @GetMapping("/findById")
    Term findById(@RequestParam(value = "Id") long id){
        return termMapper.findById(id).get();
    }

    @PostMapping("/update")
    boolean update(@Valid @RequestBody Term term)
    {
        return termMapper.update(term)>0;
    }

    @GetMapping("/delete")
    boolean delete(@RequestParam(value = "Id") long id){
        return termMapper.delete(id)>0;
    }
}
