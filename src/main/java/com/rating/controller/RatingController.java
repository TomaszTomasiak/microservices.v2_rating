package com.rating.controller;


import com.rating.domain.RatingDto;
import com.rating.exceptions.NotFoundException;
import com.rating.mapper.RatingMapper;
import com.rating.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/ratings")
public class RatingController {

    @Autowired
    RatingService service;

    @Autowired
    RatingMapper mapper;

    @GetMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<RatingDto> findAllRatings() {
        return mapper.mapToRatingDtoList(service.findAllRatings());
    }

    @GetMapping(path = "/{ratingId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public RatingDto getRating(@PathVariable Long ratingId) throws NotFoundException {
        return mapper.mapToRatingDto(service.getRatingById(ratingId).orElseThrow(NotFoundException::new));
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public void addRating(@RequestBody RatingDto ratingDto) {
        service.addRating(mapper.mapToRating(ratingDto));
    }

    @DeleteMapping(path = "{ratingId")
    public void deleteRating(@PathVariable Long ratingId){
        service.deleteRatingById(ratingId);
    }

    @PutMapping(path = "/{ratingId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public RatingDto updateRating(@PathVariable Long ratingId, @RequestBody RatingDto ratingDto) {
        return mapper.mapToRatingDto(service.addRating(mapper.mapToRating(ratingDto)));
    }

    @PatchMapping(path = "{movieId", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public RatingDto updateMovie(@RequestBody Map<String, String> updates, @PathVariable Long movieId) {
        return mapper.mapToRatingDto(service.updateRating(updates, movieId));
    }
}
