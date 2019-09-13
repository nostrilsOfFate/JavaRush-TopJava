package com.space.service;

import com.space.model.Ship;

import java.util.List;

public interface ShipService {
    Ship create(Ship ship);

    void delete(Long id);

    List<Ship> getAll();

    Long getCount();

    Ship get(Long id);

    Ship update(Ship ship);
}
