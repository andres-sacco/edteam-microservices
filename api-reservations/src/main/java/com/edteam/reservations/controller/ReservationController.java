package com.edteam.reservations.controller;

import com.edteam.reservations.dto.ReservationDTO;
import com.edteam.reservations.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        ReservationDTO response = service.save(reservation);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReservationDTO> update(@PathVariable Long id, @RequestBody ReservationDTO reservation) {
        ReservationDTO response = service.update(id, reservation);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
