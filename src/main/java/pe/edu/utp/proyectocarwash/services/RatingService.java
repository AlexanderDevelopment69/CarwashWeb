package pe.edu.utp.proyectocarwash.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.utp.proyectocarwash.entities.Partner;
import pe.edu.utp.proyectocarwash.entities.Rating;
import pe.edu.utp.proyectocarwash.entities.User;
import pe.edu.utp.proyectocarwash.repositories.RatingRepository;

import java.util.Date;
import java.util.List;

@Service
public class RatingService {
    @Autowired
    private RatingRepository ratingRepository;



    public List<Rating> getAllRatings() {
        return ratingRepository.findAll();
    }

    public Rating saveRating(User user, Partner partner, int score, String comment) {
        Rating rating = new Rating();
        rating.setUser(user);
        rating.setPartner(partner);
        rating.setScore(score);
        rating.setComment(comment);
        rating.setDate(new Date());

        return ratingRepository.save(rating);
    }


//    public double calculateAverageRating(Partner partner) {
//        List<Rating> ratings = ratingRepository.findByPartner(partner);
//        if (ratings.isEmpty()) {
//            return 0;
//        }
//        double total = 0;
//        for (Rating rating : ratings) {
//            total += rating.getScore();
//        }
//        return total / ratings.size();
//    }

//    public double calculateAverageRating(Partner partner) {
//        List<Rating> ratings = ratingRepository.findByPartner(partner);
//        return ratings.stream()
//                .mapToInt(Rating::getScore)
//                .average()
//                .orElse(0.0);
//    }

}