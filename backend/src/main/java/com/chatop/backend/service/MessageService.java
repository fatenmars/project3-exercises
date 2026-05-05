package com.chatop.backend.service;

import java.sql.Timestamp;
import org.springframework.stereotype.Service;
import com.chatop.backend.dto.CreateMessageRequest;
import com.chatop.backend.entity.Message;
import com.chatop.backend.repository.MessageRepository;

@Service
public class MessageService {

    private final MessageRepository messageRepository;

    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public Message createMessage(CreateMessageRequest request) {
        Message msg = new Message();
        msg.setRentalId(request.getRentalId());
        msg.setUserId(request.getUserId());
        msg.setMessage(request.getMessage());
        msg.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        msg.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        return messageRepository.save(msg);
    }

}
