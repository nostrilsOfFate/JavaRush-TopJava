package com.space.dto;

import com.space.model.Ship;

import java.util.Date;

import static com.space.util.ShipHelper.countRating;

public class EntityMapper {

    private EntityMapper() {
    }

    public static Ship fromShipDto(ShipDto shipDto) {
        Ship ship = new Ship();
        ship.setId(shipDto.getId());
        ship.setName(shipDto.getName());
        ship.setPlanet(shipDto.getPlanet());
        ship.setShipType(shipDto.getShipType());
        ship.setProdDate(new Date(shipDto.getProdDate()));
        ship.setIsUsed(shipDto.getIsUsed() == null ? false : shipDto.getIsUsed());
        ship.setSpeed(shipDto.getSpeed());
        ship.setCrewSize(shipDto.getCrewSize());
        ship.setRating(countRating(shipDto));
        return ship;
    }

    public static ShipDto toShipDto(Ship ship) {
        ShipDto dto = new ShipDto();
        dto.setId(ship.getId());
        dto.setName(ship.getName());
        dto.setPlanet(ship.getPlanet());
        dto.setShipType(ship.getShipType());
        dto.setProdDate(ship.getProdDate().getTime());
        dto.setIsUsed(ship.getIsUsed());
        dto.setSpeed(ship.getSpeed());
        dto.setCrewSize(ship.getCrewSize());
        dto.setRating(ship.getRating());
        return dto;
    }
}
