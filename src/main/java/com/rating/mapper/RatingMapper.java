package com.rating.mapper;


import com.rating.domain.Rating;
import com.rating.domain.RatingDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RatingMapper {

    public Rating mapToRating(final RatingDto ratingDto) {
        return new Rating(
                ratingDto.getId(),
                ratingDto.getMovieId(),
                ratingDto.getStars());
    }

    public RatingDto mapToRatingDto(final Rating rating) {
        return new RatingDto(
                rating.getId(),
                rating.getMovieId(),
                rating.getStars());
    }

    public List<RatingDto> mapToRatingDtoList(final List<Rating> ratingList) {
        return ratingList.stream()
                .map(this::mapToRatingDto)
                .collect(Collectors.toList());
    }
}

