package com.edteam.reservations.controller.resource;

import com.edteam.reservations.dto.ReservationDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Reservation", description = "Operations about the reservation entity")
public interface ReservationResource {

    @Operation(description = "Get the information of all the reservations", responses = {
            @ApiResponse(responseCode = "200",
                    description = "Return the information of all the reservations",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = List.class)))})
    public ResponseEntity<List<ReservationDTO>> getReservations();


    @Operation(description = "Get the information about one reservation", responses = {
            @ApiResponse(responseCode = "200",
                    description = "Return the information of one reservation",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ReservationDTO.class)))},
            parameters = {
                    @Parameter(in = ParameterIn.PATH, name = "id", description = "Id of the reservation to search")
            }
    )
    public ResponseEntity<ReservationDTO> getReservationById(@Min(1) @PathVariable Long id);

    @Operation(description = "Create one reservation", responses = {
            @ApiResponse(responseCode = "200",
                    description = "Return the created reservation",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ReservationDTO.class)))})

    public ResponseEntity<ReservationDTO> save(@RequestBody @Valid ReservationDTO reservation);


    @Operation(description = "Update one reservation", responses = {
            @ApiResponse(responseCode = "200",
                    description = "Return the updated reservation",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ReservationDTO.class)))},
            parameters = {
                    @Parameter(in = ParameterIn.PATH, name = "id", description = "Id of the reservation to update")
            })
    public ResponseEntity<ReservationDTO> update(@Min(1) @PathVariable Long id, @RequestBody @Valid ReservationDTO reservation);

    @Operation(description = "Delete one reservation", responses = {
            @ApiResponse(responseCode = "200",
                    description = "Return nothing",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Void.class)))},
            parameters = {
                    @Parameter(in = ParameterIn.PATH, name = "id", description = "Id of the reservation to delete")
            })
    public ResponseEntity<Void> delete(@Min(1) @PathVariable Long id);
}
