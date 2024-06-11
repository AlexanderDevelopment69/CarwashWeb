package pe.edu.utp.proyectocarwash.mapper;

import pe.edu.utp.proyectocarwash.dto.RatingDTO;
import pe.edu.utp.proyectocarwash.entities.Rating;

public class RatingMapper {

    public static RatingDTO mapToRatingDTO(Rating rating) {
        RatingDTO ratingDTO = new RatingDTO();
        ratingDTO.setId(rating.getId());
        ratingDTO.setScore(rating.getScore());
        ratingDTO.setComment(rating.getComment());
        ratingDTO.setDate(rating.getDate());
        ratingDTO.setPartner(rating.getPartner());
        ratingDTO.setUser(rating.getUser());
        return ratingDTO;
    }

    public static Rating mapToRating(RatingDTO ratingDTO) {
        Rating rating = new Rating();
        rating.setId(ratingDTO.getId());
        rating.setScore(ratingDTO.getScore());
        rating.setComment(ratingDTO.getComment());
        rating.setDate(ratingDTO.getDate());
        rating.setPartner(ratingDTO.getPartner());
        rating.setUser(ratingDTO.getUser());
        return rating;
    }
}
