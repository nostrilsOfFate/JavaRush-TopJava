package com.space.model;

import com.space.controller.ShipOrder;
import com.space.model.ShipType;
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

//    public ViewShipFilter(Ship ship) {
//        this.name = ship.getName();
//        this.planet = ship.getPlanet();
//        this.shipType = ship.getShipType();
//        this.after = 0L;
//        this.before = ship.getProdDate().getTime();
//        this.isUsed = ship.getUsed();
//        this.minSpeed = 0.01;
//        this.maxSpeed = ship.getSpeed();
//        this.minCrewSize = 1;
//        this.maxCrewSize = ship.getCrewSize();
//      //  this.minRating = ;
////        this.maxRating = ;
//      //  this.order = new ShipOrder(ship.getRating) ;
////        this.pageNumber =  ;
////        this.pageSize = ;
//    }
}
