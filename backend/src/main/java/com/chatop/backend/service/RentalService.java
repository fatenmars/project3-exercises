package com.chatop.backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.chatop.backend.dto.rental.RentalResponse;
import com.chatop.backend.dto.rental.RentalsResponse;
import com.chatop.backend.entity.Rental;
import com.chatop.backend.repository.RentalRepository;

@Service
public class RentalService {

    private final RentalRepository rentalRepository;

    public RentalService(RentalRepository rentalRepository) {
        this.rentalRepository = rentalRepository;
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
}
