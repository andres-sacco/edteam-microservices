package com.edteam.reservations.mapper;

import com.edteam.reservations.dto.ReservationDTO;
import com.edteam.reservations.model.Reservation;
import org.springframework.core.convert.converter.Converter;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface ReservationMapper extends Converter<Reservation, ReservationDTO> {

    @Override
    ReservationDTO convert(Reservation source);

}