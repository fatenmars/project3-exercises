package com.chatop.backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.chatop.backend.dto.message.CreateMessageRequest;
import com.chatop.backend.dto.message.MessageResponse;
import com.chatop.backend.service.MessageService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/messages")
public class MessageController {

    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping
    @Operation(summary = "Envoie un message d'un utilisateur à un propriétaire et retourne une confirmation")
    public ResponseEntity<MessageResponse> createMessage(@RequestBody CreateMessageRequest request) {
        messageService.createMessage(request);
        return ResponseEntity.ok(new MessageResponse("Message send with success"));

    }

}
