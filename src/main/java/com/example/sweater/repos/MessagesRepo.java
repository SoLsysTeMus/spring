package com.example.sweater.repos;

import com.example.sweater.domain.Message;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MessagesRepo extends CrudRepository<Message, Long> {

    List<Message> findByText(String text);
}
