package com.example.portfolio.service;

import com.example.portfolio.entity.Message;

import java.util.List;

public interface MessageService {

    List<Message> getAllMessages();

    Message getMessageById(Long id);

    Message createMessage(Message message);

    Message updateMessage(Long id, Message message);

    void deleteMessage(Long id);

}
