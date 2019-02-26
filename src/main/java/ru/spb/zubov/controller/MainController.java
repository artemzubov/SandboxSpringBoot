package ru.spb.zubov.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.spb.zubov.domain.Message;
import ru.spb.zubov.repos.MessageRepo;

import java.util.Map;

@Controller
public class MainController {

    @Autowired
    private MessageRepo messageRepo;

    @GetMapping("/")
    public String greeting() {
        return "greeting";
    }

    @GetMapping("/main")
    public String main(Map<String, Object> model){

        Iterable<Message> messages = messageRepo.findAll();

        model.put("messages", messages);
        return "main";
    }

    @PostMapping("/main")
    public String add(@RequestParam String text,
                      @RequestParam String tag,
                      Map<String, Object> model){

        Message message = new Message(text, tag);
        messageRepo.save(message);

        Iterable<Message> messages = messageRepo.findAll();
        model.put("messages", messages);

        return "main";
    }

    @PostMapping("/filter")
    public String add(@RequestParam String filter,
                      Map<String, Object> model){

        Iterable<Message> byTag;
        if (filter == null || filter.isEmpty()){
            byTag = messageRepo.findAll();
        }
        else {
            byTag = messageRepo.findByTag(filter);
        }

        model.put("messages", byTag);

        return "main";
    }
}