package com.chatop.backend.service;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import com.chatop.backend.dto.UserResponse;
import com.chatop.backend.entity.User;
import com.chatop.backend.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponse getCurrentUser() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));
        return new UserResponse(user.getId(), user.getName(), user.getEmail(), user.getCreatedAt(),
                user.getUpdatedAt());
    }

}
