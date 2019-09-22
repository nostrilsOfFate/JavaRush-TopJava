package com.space.service;

import com.space.model.Ship;
import com.space.model.ViewShipFilter;

import java.util.List;

public interface ShipService {
    Ship create(Ship ship);

    void delete(Long id);

    List<Ship> getAll(ViewShipFilter filter);

    Long getCount(ViewShipFilter filter);

    Ship get(Long id);

    Ship update(Ship ship);

    boolean isExist(Long id);
}
