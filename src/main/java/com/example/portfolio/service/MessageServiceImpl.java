package com.example.portfolio.service;

import com.example.portfolio.entity.Message;
import com.example.portfolio.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    MessageRepository messageRepository;


    @Override
    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }

    @Override
    public Message getMessageById(Long id) {
        return messageRepository.findById(id).
                orElseThrow(() -> new RuntimeException("Message not found with id: " + id));
    }

    @Override
    public Message createMessage(Message message) {
        return messageRepository.save(message);
    }

    @Override
    public Message updateMessage(Long id, Message message) {
        Message existingMessage = getMessageById(id);
        existingMessage.setSender(message.getSender());
        existingMessage.setRecipient(message.getRecipient());
        existingMessage.setContent(message.getContent());
        existingMessage.setSendAt(message.getSendAt());
        return messageRepository.save(existingMessage);
    }

    @Override
    public void deleteMessage(Long id) {
        if (!messageRepository.existsById(id)) {
            throw new RuntimeException("Message not found with id: " + id);
        }
        messageRepository.deleteById(id);
    }
}
