package com.chatop.backend.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.chatop.backend.dto.message.MessageResponse;
import com.chatop.backend.dto.rental.CreateRentalRequest;
import com.chatop.backend.dto.rental.RentalsResponse;
import com.chatop.backend.service.RentalService;
import java.io.IOException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping("/api/rentals")
public class RentalController {

    private final RentalService rentalService;

    public RentalController(RentalService rentalService) {
        this.rentalService = rentalService;
    }

    @GetMapping
    public ResponseEntity<RentalsResponse> getAllRentals() {
        return ResponseEntity.ok(rentalService.getAllRentals());

    }

    @PostMapping
    public ResponseEntity<MessageResponse> createRental(@ModelAttribute CreateRentalRequest request,
            @RequestParam("picture") MultipartFile picture) throws IOException {
        rentalService.createRental(request, picture);
        return ResponseEntity.ok(new MessageResponse("Rental created!"));
    }

}
