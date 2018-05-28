package com.example.sweater.controller;

import com.example.sweater.domain.Message;
import com.example.sweater.repos.MessagesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class MainController {

    @Autowired
    private MessagesRepo messagesRepo;

    @GetMapping("/")
    public String greeting(Map<String, Object> model) {
        return "greeting";
    }

    @GetMapping("/main")
    public String main(Map<String, Object> model){
        Iterable<Message> messages = messagesRepo.findAll();
        model.put("messages", messages);
        return "main";
    }

    @PostMapping("/main")
    public String addMessage(@RequestParam String text, @RequestParam String tag, Map<String, Object> model){
        Message message = new Message(text, tag);
        messagesRepo.save(message);
        Iterable<Message> messages = messagesRepo.findAll();
        model.put("messages", messages);
        return "main";
    }

    @PostMapping("filter")
    public String searchMessages(@RequestParam String filter, Map<String, Object> model){
        Iterable<Message> messages = null;
        if (filter != null && !filter.isEmpty()) {
            messages = messagesRepo.findByText(filter);
        } else {
            messages = messagesRepo.findAll();
        }
        model.put("messages", messages);
        return "main";
    }

}

