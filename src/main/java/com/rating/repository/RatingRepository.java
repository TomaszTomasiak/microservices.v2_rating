package com.rating.repository;


import com.rating.domain.Rating;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public interface RatingRepository extends CrudRepository<Rating, Long> {

    Rating findRatingById(Long aLong);

    List<Rating> findAllByMovieId(Long movieId);

    @Override
    List<Rating> findAll();
}
