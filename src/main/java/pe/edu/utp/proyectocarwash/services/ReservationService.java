package pe.edu.utp.proyectocarwash.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.utp.proyectocarwash.dto.ReservationDTO;
import pe.edu.utp.proyectocarwash.entities.*;
import pe.edu.utp.proyectocarwash.mapper.ReservationMapper;
import pe.edu.utp.proyectocarwash.repositories.ReservationRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ReservationService {
    @Autowired
    private ReservationRepository reservationRepository;


    public void schedulereservation(User user, Partner partner, List<PartnerTypeCatalog> services, LocalDateTime dateTime, Double totalPrice) {
        // Crea una nueva cita
        Reservation reservation = new Reservation();
        reservation.setUser(user);
        reservation.setPartner(partner);
        reservation.setDateTime(dateTime);

        // Asocia los servicios con la cita
        reservation.setServices(services);

        // Establece el precio total proporcionado desde la interfaz de usuario
        reservation.setTotalPrice(totalPrice);
        reservation.setStatus(ReservationStatus.CONFIRMADA);
        // Guarda la cita en la base de datos
        reservationRepository.save(reservation);
    }



    public List<ReservationDTO> findReservationsByUser(User user) {
        List<Reservation> reservations = reservationRepository.findByUser(user);
        return reservations.stream()
                .map(ReservationMapper::mapToReservationDto)
                .collect(Collectors.toList());
    }


//    public List<ReservationDTO> getReservationsByPartner(Partner partner) {
//        List<Reservation> reservations = reservationRepository.findByPartner(partner);
//        return ReservationMapper.mapToReservationDtoList(reservations);
//    }

//    public List<ReservationDTO> getReservationsByPartner(Partner partner) {
//        List<Reservation> reservations = reservationRepository.findByPartner(partner);
//        return reservations.stream().map(ReservationMapper::mapToReservationDto).collect(Collectors.toList());
//    }


    public List<ReservationDTO> getReservationsByPartner(Partner partner) {
        // Obtener todas las reservaciones asociadas al partner dado
        List<Reservation> reservations = reservationRepository.findByPartner(partner);

        // Convertir las reservaciones a DTOs
        List<ReservationDTO> reservationDTOs = new ArrayList<>();
        for (Reservation reservation : reservations) {
            ReservationDTO reservationDTO = ReservationMapper.mapToReservationDto(reservation);
            reservationDTOs.add(reservationDTO);
        }

        return reservationDTOs;
    }


    public void updateReservationStatus(Long reservationId, ReservationStatus newStatus) {
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new IllegalArgumentException("Reserva no encontrada con ID: " + reservationId));

        reservation.setStatus(newStatus);
        reservationRepository.save(reservation);
    }



}
