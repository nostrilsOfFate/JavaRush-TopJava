package com.space.model;

import com.space.controller.ShipOrder;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class ViewShipFilter {

    private String name;
    private String planet;
    private ShipType shipType;
    private Long after;
    private Long before;
    private Boolean isUsed;
    private Double minSpeed;
    private Double maxSpeed;
    private Integer minCrewSize;
    private Integer maxCrewSize;
    private Double minRating;
    private Double maxRating;
    private ShipOrder order;
    private Integer pageNumber;
    private Integer pageSize;

}
