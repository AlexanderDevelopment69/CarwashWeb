package pe.edu.utp.proyectocarwash.dto;

import lombok.Data;
import pe.edu.utp.proyectocarwash.entities.Partner;
import pe.edu.utp.proyectocarwash.entities.User;

import java.util.Date;

@Data
public class RatingDTO {
    private Long id;
    private int score;
    private String comment;
    private Date date;
    private Partner partner;
    private User user;
}
