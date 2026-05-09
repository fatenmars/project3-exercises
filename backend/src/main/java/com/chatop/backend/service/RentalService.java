package com.chatop.backend.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.chatop.backend.repository.UserRepository;
import com.chatop.backend.dto.rental.CreateRentalRequest;
import com.chatop.backend.dto.rental.RentalResponse;
import com.chatop.backend.dto.rental.RentalsResponse;
import com.chatop.backend.entity.Rental;
import com.chatop.backend.entity.User;
import com.chatop.backend.repository.RentalRepository;

@Service
public class RentalService {

    private final RentalRepository rentalRepository;
    private final UserRepository userRepository;

    public RentalService(RentalRepository rentalRepository, UserRepository userRepository) {
        this.rentalRepository = rentalRepository;
        this.userRepository = userRepository;
    }

    public RentalsResponse getAllRentals() {
        List<Rental> rentals = rentalRepository.findAll();
        List<RentalResponse> rentalResponses = rentals.stream()
                .map(rental -> new RentalResponse(
                        rental.getId(),
                        rental.getName(),
                        rental.getSurface(),
                        rental.getPrice(),
                        rental.getPicture(),
                        rental.getDescription(),
                        rental.getOwnerId(),
                        rental.getCreatedAt(),
                        rental.getUpdatedAt()

                ))
                .toList();
        return new RentalsResponse(rentalResponses);
    }

    public void createRental(CreateRentalRequest request, MultipartFile picture) throws IOException {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        User owner = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));

        String fileName = System.currentTimeMillis() + "_" + picture.getOriginalFilename();

        Path uploadPath = Paths.get("uploads/" + fileName);
        Files.write(uploadPath, picture.getBytes());

        String pictureUrl = "http://localhost:8080/uploads/" + fileName;

        Rental rental = new Rental();
        rental.setName(request.getName());
        rental.setSurface(request.getSurface());
        rental.setPrice(request.getPrice());
        rental.setPicture(pictureUrl);
        rental.setDescription(request.getDescription());
        rental.setOwnerId(owner.getId());
        rental.setCreatedAt(new java.sql.Timestamp(System.currentTimeMillis()));
        rental.setUpdatedAt(new java.sql.Timestamp(System.currentTimeMillis()));
        rentalRepository.save(rental);
    }

    public RentalResponse getRentalById(Long id) {
        Rental rental = rentalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Location non trouvée"));
        return new RentalResponse(
                rental.getId(),
                rental.getName(),
                rental.getSurface(),
                rental.getPrice(),
                rental.getPicture(),
                rental.getDescription(),
                rental.getOwnerId(),
                rental.getCreatedAt(),
                rental.getUpdatedAt());
    }

    public void updateRental(Long id, CreateRentalRequest request) {
        Rental rental = rentalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Location non trouvée"));
        rental.setName(request.getName());
        rental.setSurface(request.getSurface());
        rental.setPrice(request.getPrice());
        rental.setDescription(request.getDescription());
        rental.setUpdatedAt(new java.sql.Timestamp(System.currentTimeMillis()));
        rentalRepository.save(rental);
    }
}
