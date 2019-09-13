package com.space.dto;

import com.space.model.ShipType;
import lombok.*;

import javax.persistence.Column;
import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ShipDto {
    private Long id;
    private String name;

    private String planet;

    private ShipType shipType;

    private Date prodDate;

    private Boolean isUsed = false;

    private double speed;

    private Integer crewSize;

    private Double rating;
}
