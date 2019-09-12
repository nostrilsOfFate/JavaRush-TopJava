package com.space.service;

import com.space.model.Ship;
import com.space.repository.ShipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@org.springframework.transaction.annotation.Transactional(readOnly = true)
public class ShipServiceImpl implements ShipService {

   private final ShipRepository shipRepository;

    @Autowired
    public ShipServiceImpl(ShipRepository shipRepository) {
        this.shipRepository = shipRepository;
    }


    @Override
    @org.springframework.transaction.annotation.Transactional
    public Ship create(Ship ship) {
        return shipRepository.create(ship);
    }

    @Override
    public Boolean delete(Long id) {
        return shipRepository.delete(id);
    }

    @Override
    public List<Ship> getAll() {
        return shipRepository.getAll();
    }

    @Override
    public Integer getCount() {
        return shipRepository.getCount();
    }

    @Override
    public Ship get(Long id) {
        return shipRepository.getShipById(id);
    }

    @Override
    @org.springframework.transaction.annotation.Transactional
    public Ship update(Ship ship) {
        return shipRepository.update(ship);
    }
}
