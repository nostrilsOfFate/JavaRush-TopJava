package com.space.service;

import com.space.model.Ship;

import java.util.List;

public interface ShipService {
    public Ship create(Ship ship);

    public Boolean delete(Long id);

    public List<Ship> getAll();

    public Integer getCount();

    public Ship get(Long id);

    public Ship update(Ship ship);
}
