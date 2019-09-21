package com.space.service;

import com.space.exceptions.ShipNotFoundException;
import com.space.model.Ship;
import com.space.model.ViewShipFilter;
import com.space.repository.ShipRepository;
import com.space.util.ShipHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("shipService")
public class ShipServiceImpl implements ShipService {

    private final ShipRepository shipRepository;

    @Autowired
    public ShipServiceImpl(ShipRepository shipRepository) {
        this.shipRepository = shipRepository;
    }

    @Override
    public Ship create(Ship ship) {
        return shipRepository.save(ship);
    }

    @Override
    public void delete(Long id) throws ShipNotFoundException, IllegalArgumentException {
        if (isExist(id)) {
            shipRepository.deleteById(id);
        } else throw new ShipNotFoundException();
    }

    @Override
    public List<Ship> getAll(ViewShipFilter filter) {
        List<Ship> ships = shipRepository.findAll();
        return ShipHelper.getList(filter, ships);
    }

    @Override
    public Long getCount() {
        return shipRepository.count();
    }

    @Override
    public Ship get(Long id) throws ShipNotFoundException {
        if (isExist(id)){
            return shipRepository.findShipById(id);
        } else throw new ShipNotFoundException();
    }

    @Override
    public Ship update(Ship ship) {
        return shipRepository.save(ship);
    }

    @Override
    public boolean isExist(Long id) {
        return shipRepository.existsById(id);
    }


}
