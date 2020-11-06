package com.rating.service;

import com.rating.domain.Rating;
import com.rating.repository.RatingRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RatingService {
    private final Logger log = LoggerFactory.getLogger(RatingService.class);

    @Autowired
    private RatingRepository repository;

    public List<Rating> findAllRatings() {
        log.debug("Request to get all ratings");
        return repository.findAll();
    }

    public Optional<Rating> getRatingById(final Long id) {
        log.debug("Request to get rating with id: {}", id);
        return repository.findById(id);
    }

    public Rating addRating( Rating rating){
        log.debug("Request to save rating: {}", rating);
        return repository.save(rating);
    }

    public void deleteRatingById(final Long id) {
        log.debug("Request to remove rating with id: {}", id);
        repository.deleteById(id);
    }

    public Rating updateRating(Map<String, String> updates, Long ratingId) {

        Rating updatedRating = repository.findRatingById(ratingId);

        List<String> keys = updates.entrySet().stream()
                .map(k -> k.getKey())
                .collect(Collectors.toList());
        List<String> values = updates.entrySet().stream()
                .map(k -> k.getValue())

                .collect(Collectors.toList());

        if (keys.get(0).equals("movieId")){
            Long updatedMovieId = Long.parseLong(values.get(0));
            updatedRating.setMovieId(updatedMovieId);

        } else {
            Integer updatedStars = Integer.parseInt(values.get(0));
            updatedRating.setStars(updatedStars);
        }

        addRating(updatedRating);
        return updatedRating;
    }

    public List<Rating> getListOfRatingsByMovieId(final Long movieId) {
        log.debug("Request to get list of ratings of movie with id: {}", movieId);

//        return repository.findAllByMovieId(movieId);
        return findAllRatings().stream()
                .filter(r -> r.getMovieId().equals(movieId))
                .collect(Collectors.toList());
    }

    public BigDecimal averageRatingByMovieId(final Long movieId) {
        log.debug("Request to get avarage rating of movie with id: {}", movieId);
        double average = getListOfRatingsByMovieId(movieId).stream()
                .mapToDouble(Rating::getStars)
                .average().getAsDouble();

        return BigDecimal.valueOf(average).setScale(1, BigDecimal.ROUND_HALF_UP);
    }
}
