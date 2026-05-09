package com.chatop.backend.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.chatop.backend.dto.message.MessageResponse;
import com.chatop.backend.dto.rental.CreateRentalRequest;
import com.chatop.backend.dto.rental.RentalResponse;
import com.chatop.backend.dto.rental.RentalsResponse;
import com.chatop.backend.service.RentalService;

import io.swagger.v3.oas.annotations.Operation;

import java.io.IOException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api/rentals")
public class RentalController {

    private final RentalService rentalService;

    public RentalController(RentalService rentalService) {
        this.rentalService = rentalService;
    }

    @GetMapping
    @Operation(summary = "Récupère la liste de toutes les locations disponibles")
    public ResponseEntity<RentalsResponse> getAllRentals() {
        return ResponseEntity.ok(rentalService.getAllRentals());

    }

    @PostMapping
    @Operation(summary = "Crée une nouvelle location et retourne une confirmation")
    public ResponseEntity<MessageResponse> createRental(@ModelAttribute CreateRentalRequest request,
            @RequestParam("picture") MultipartFile picture) throws IOException {
        rentalService.createRental(request, picture);
        return ResponseEntity.ok(new MessageResponse("Rental created!"));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Récupère les détails d'une location spécifique")
    public ResponseEntity<RentalResponse> getRentalById(@PathVariable Long id) {
        return ResponseEntity.ok(rentalService.getRentalById(id));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Met à jour les informations d'une location existante et retourne une confirmation")
    public ResponseEntity<MessageResponse> updateRental(@PathVariable Long id,
            @ModelAttribute CreateRentalRequest request) {
        rentalService.updateRental(id, request);
        return ResponseEntity.ok(new MessageResponse("Rental updated!"));
    }

}
