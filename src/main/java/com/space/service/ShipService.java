package com.space.service;

import com.space.dto.ShipDto;
import com.space.model.Ship;
import com.space.model.ViewShipFilter;

import java.util.List;

public interface ShipService {
    Ship create(Ship ship);

    void delete(Long id);

    List<Ship> getAll(ViewShipFilter filter);

    Long getCount(ViewShipFilter filter);

    Ship get(Long id);

    Ship update(ShipDto dto);

    boolean isExist(Long id);
}
