package com.edteam.reservations.mapper;

import com.edteam.reservations.dto.ReservationDTO;
import com.edteam.reservations.model.Reservation;
import org.mapstruct.Mapper;
import org.springframework.core.convert.converter.Converter;

@Mapper(componentModel = "spring")
public interface ReservationDTOMapper extends Converter<ReservationDTO, Reservation> {

    @Override
    Reservation convert(ReservationDTO source);

}