package com.rating.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RatingDto {
    private Long id;
    private Long movieId;
    private Integer stars;
}
