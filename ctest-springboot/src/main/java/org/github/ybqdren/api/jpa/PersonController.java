package org.github.ybqdren.api.jpa;

import org.github.ybqdren.entity.Person;
import org.github.ybqdren.entity.User;
import org.github.ybqdren.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Wen(Joan) Zhao
 * @time 2022/1/18 12:28
 * @package org.github.ybqdren.api
 * @description {@link org.springframework.data.repository.CrudRepository} 实现接口测试
 **/



@RestController
@RequestMapping(path = "/person")
public class PersonController {
    @Autowired
    private PersonRepository personRepository;

    // ---------------- PersonRepository 测试 ----------------------------------
    @GetMapping(path = "/info")
    public List<Person> getAllByPerson(@RequestParam Long id){
        return personRepository.findByLastnameIgnoreCase("赵");
    }


}
