package com.edteam.reservations.controller;

import com.edteam.reservations.dto.PassengerDTO;
import com.edteam.reservations.dto.ReservationDTO;
import com.edteam.reservations.exception.EdteamException;
import com.edteam.reservations.model.Passenger;
import com.edteam.reservations.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/reservation")
public class ReservationController {
    private ReservationService service;

    @Autowired
    public ReservationController(ReservationService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<ReservationDTO>> getReservations() {
        List<ReservationDTO> response = service.getReservations();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<ReservationDTO> getReservationById(@PathVariable Long id) {
        ReservationDTO response = service.getReservationById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ReservationDTO> save(@RequestBody ReservationDTO reservation) {
        validateSave(reservation);
        ReservationDTO response = service.save(reservation);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReservationDTO> update(@PathVariable Long id, @RequestBody ReservationDTO reservation) {
        validateUpdate(reservation);
        ReservationDTO response = service.update(id, reservation);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    private void validateSave(ReservationDTO reservation) {
        if(Objects.nonNull(reservation.getId()) ||
                Objects.isNull(reservation.getItinerary()) ||
                Objects.isNull(reservation.getPassengers())) {
            throw new EdteamException("Something is missing");
        }
        validatePassengers(reservation.getPassengers());
    }

    private void validateUpdate(ReservationDTO reservation) {
        if(Objects.isNull(reservation.getId()) ||
                Objects.isNull(reservation.getItinerary()) ||
                Objects.isNull(reservation.getPassengers())) {
            throw new EdteamException("Something is missing");
        }
        validatePassengers(reservation.getPassengers());
    }

    private void validatePassengers(List<PassengerDTO> passengers) {
        for (PassengerDTO passenger: passengers) {
           if (Objects.isNull(passenger.getFirstName()) ||
                   passenger.getFirstName().isEmpty() ||
                   passenger.getFirstName().length() > 30) {
               throw new EdteamException("Firstname is wrong");
           }
        }
    }

}
