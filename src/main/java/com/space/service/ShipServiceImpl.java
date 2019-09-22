package com.space.service;

import com.space.exceptions.ShipNotFoundException;
import com.space.model.Ship;
import com.space.model.ViewShipFilter;
import com.space.repository.ShipRepository;
import com.space.util.ShipHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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
        if (ships.size() != 0) {
            return ShipHelper.getResultFilteredShipList(filter, ShipHelper.getFilteredShipList(filter, ships));
        } else
            return Collections.emptyList();
    }

    @Override
    public Long getCount(ViewShipFilter filter) {
        List<Ship> ships = shipRepository.findAll();
        List<Ship> ships1 = ShipHelper.getFilteredShipList(filter, ships);
//                .stream()
//                .filter(s -> !s.getIsUsed())
//                .collect(Collectors.toList());

        return (long) ships1.size();
    }

    @Override
    public Ship get(Long id) throws ShipNotFoundException {
        if (isExist(id)) {
            return shipRepository.findShipById(id);
        } else throw new ShipNotFoundException();
    }

    @Override
    public Ship update(Ship ship) throws ShipNotFoundException {
        if (isExist(ship.getId())) {
            return shipRepository.save(ship);
        } else
            throw new ShipNotFoundException();
    }

    @Override
    public boolean isExist(Long id) {
        return shipRepository.existsById(id);
    }


}
