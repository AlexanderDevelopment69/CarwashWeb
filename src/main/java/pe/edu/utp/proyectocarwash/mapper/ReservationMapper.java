package pe.edu.utp.proyectocarwash.mapper;

import pe.edu.utp.proyectocarwash.dto.ReservationDTO;
import pe.edu.utp.proyectocarwash.entities.Reservation;

import java.util.List;
import java.util.stream.Collectors;

public class ReservationMapper {

    public static ReservationDTO mapToReservationDto(Reservation reservation) {
        ReservationDTO reservationDTO = new ReservationDTO();
        reservationDTO.setId(reservation.getId());
        reservationDTO.setUser(UserMapper.mapToUserDto(reservation.getUser())); // Suponiendo que tienes un mapper para User
        reservationDTO.setPartner(PartnerMapper.mapToPartnerDTO(reservation.getPartner())); // Suponiendo que tienes un mapper para Partner
        reservationDTO.setServices(reservation.getServices());
        reservationDTO.setDateTime(reservation.getDateTime());
        reservationDTO.setTotalPrice(reservation.getTotalPrice());
        reservationDTO.setStatus(reservation.getStatus());
        return reservationDTO;
    }

    public static List<ReservationDTO> mapToReservationDtoList(List<Reservation> reservations) {
        return reservations.stream()
                .map(ReservationMapper::mapToReservationDto)
                .collect(Collectors.toList());
    }

    public static Reservation mapToReservation(ReservationDTO reservationDTO) {
        Reservation reservation = new Reservation();
        reservation.setId(reservationDTO.getId());
        reservation.setUser(UserMapper.mapToUser(reservationDTO.getUser())); // Suponiendo que tienes un mapper inverso para User
        reservation.setPartner(PartnerMapper.mapToPartner(reservationDTO.getPartner())); // Suponiendo que tienes un mapper inverso para Partner
        reservation.setServices(reservationDTO.getServices());
        reservation.setDateTime(reservationDTO.getDateTime());
        reservation.setTotalPrice(reservationDTO.getTotalPrice());
        reservation.setStatus(reservationDTO.getStatus());
        return reservation;
    }


}
