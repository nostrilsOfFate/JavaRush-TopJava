package com.space.dto;

import com.space.model.Ship;
import lombok.experimental.UtilityClass;

@UtilityClass
public class EntityMapper {

    public Ship fromShipDto(ShipDto shipDto) {
        Ship ship = new Ship();
        ship.setId(shipDto.getId());
        ship.setName(shipDto.getName());
        ship.setPlanet(shipDto.getPlanet());
        ship.setShipType(shipDto.getShipType());
        ship.setProdDate(shipDto.getProdDate());
        ship.setIsUsed(shipDto.getIsUsed());
        ship.setSpeed(shipDto.getSpeed());
        ship.setCrewSize(shipDto.getCrewSize());
        ship.setRating(shipDto.getRating());
        return ship;
    }

    public ShipDto toShipDto(Ship ship) {
        ShipDto dto = new ShipDto();
        dto.setId(ship.getId());
        dto.setPlanet(ship.getPlanet());
        dto.setShipType(ship.getShipType());
        dto.setProdDate(ship.getProdDate());
        dto.setIsUsed(ship.getIsUsed());
        dto.setSpeed(ship.getSpeed());
        dto.setCrewSize(ship.getCrewSize());
        dto.setRating(ship.getRating());
        return dto;
    }
}
