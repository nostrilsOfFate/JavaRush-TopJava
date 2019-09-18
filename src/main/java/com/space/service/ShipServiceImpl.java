package com.space.service;

import com.space.controller.ShipOrder;
import com.space.model.Ship;
import com.space.model.ShipType;
import com.space.model.ViewShipFilter;
import com.space.repository.ShipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
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
        return shipRepository.save(ship);
    }

    @Override
    public void delete(Long id) throws IllegalArgumentException {
        shipRepository.deleteById(id);
    }

    @Override
    public List<Ship> getAll(ViewShipFilter filter) {
        List<Ship> ships = shipRepository.findAll();

        // есть ли ограничение по имени
        if (filter.getName() != null) {
            for (int i = ships.size()-1; i >= 1; i--) {//для каждого элемента списка
                if (ships.get(i).getName().contains(filter.getName())) {
                } //если совпаадет пропускай
                else { //иначе удаляй
                    ships.remove(i);
                }
            }
        }

        // есть ли ограничение по планете
        if (filter.getPlanet() != null) {
            for (int i = ships.size()-1; i >= 1; i--) {
                if (ships.get(i).getPlanet().contains(filter.getPlanet())) {
                } else {
                    ships.remove(i);
                }
            }
        }

        // есть ли ограничение по типу корабля
        if (filter.getShipType() != null) {//        private ShipType shipType;
            if (filter.getShipType().equals("TRANSPORT")) {
                for (int i = ships.size()-1; i >= 1; i--) {
                    if (ships.get(i).getShipType().equals(filter.getShipType())) {
                    } else {
                        ships.remove(i);
                    }
                }
            } else if (filter.getShipType().equals("MILITARY")) {
                for (int i = ships.size()-1; i >= 1; i--) {
                    if (ships.get(i).getShipType().equals(filter.getShipType())) {
                    } else {
                        ships.remove(i);
                    }
                }
            } else if (filter.getShipType().equals("MERCHANT")) {
                for (int i = ships.size()-1; i >= 1; i--) {
                    if (ships.get(i).getShipType().equals(filter.getShipType())) {
                    } else {
                        ships.remove(i);
                    }
                }
            }
        }

        // есть ли ограничение по времени после
        if (filter.getAfter() != 0) {
            for (int i = ships.size()-1; i >= 1; i--) {
                if (ships.get(i).getProdDate().getTime() >= filter.getAfter()) {
                    ships.remove(i);
                }
            }
        }

        // есть ли ограничение по времени до
        if (filter.getBefore() != 0) {
            for (int i = ships.size()-1; i >= 1; i--) {
                if (ships.get(i).getProdDate().getTime() <= filter.getBefore()) {
                    ships.remove(i);
                }
            }
        }

        // есть ли ограничение по использованию
        if (filter.getIsUsed()) { // если тру
            for (int i = ships.size()-1; i >= 1; i--) {
                if (!ships.get(i).getUsed()) {
                    ships.remove(i);
                } //даляем то что тру
            }
        }
        if (!filter.getIsUsed() || (filter.getIsUsed() == null)) { // если фолс
            for (int i = ships.size()-1; i >= 1; i--) {
                if (ships.get(i).getUsed()) {// удаляем то что фолс
                    ships.remove(i);
                }
            }
        }

        // есть ли ограничение по мин скорости
        if (filter.getMinSpeed() != null) {
            for (int i = ships.size()-1; i >= 1; i--) {
                if (ships.get(i).getSpeed() <= filter.getMinSpeed()) {
                    ships.remove(i);
                }
            }
        }
        // есть ли ограничение по мах скорости
        if (filter.getMinSpeed() != null) {
            for (int i = ships.size()-1; i >= 1; i--) {
                if (ships.get(i).getSpeed() >= filter.getMaxSpeed()) {
                    ships.remove(i);
                }
            }
        }
        // есть ли ограничение по мин команде
        if (filter.getMinCrewSize() != null) {
            for (int i = ships.size()-1; i >= 1; i--) {
                if (ships.get(i).getCrewSize() <= filter.getMinCrewSize()) {
                    ships.remove(i);
                }
            }
        }
        // есть ли ограничение по мах команде
        if (filter.getMaxCrewSize() != null) {
            for (int i = ships.size()-1; i >= 1; i--) {
                if (ships.get(i).getCrewSize() >= filter.getMaxCrewSize()) {
                    ships.remove(i);
                }
            }
        }
        // есть ли ограничение по мин рейтингу
        if (filter.getMinRating() != null) {
            for (int i = ships.size()-1; i >= 1; i--) {
                if (ships.get(i).getRating() <= filter.getMinRating()) {
                    ships.remove(i);
                }
            }
        }
        // есть ли ограничение по мах рейтингу
        if (filter.getMaxRating() != null) {
            for (int i = ships.size()-1; i >= 1; i--) {
                if (ships.get(i).getRating() >= filter.getMaxRating()) {
                    ships.remove(i);
                }
            }
        }
//int i = 0; i < ships.size(); i++

        //        private ShipOrder order;
//        private Integer pageNumber;
//        private Integer pageSize;
        // есть ли ограничение по запросу
        if (filter.getOrder() != null) {

        }
        if (filter.getPageNumber() != null) {
            // есть ли ограничение по номерам страниц
            //    private Integer pageNumber;
        }
        if (filter.getPageSize() != null) {
            // есть ли ограничение по количеству на странице
            //    private Integer pageSize;
        }
        return ships;
    }

    @Override
    public Long getCount() {
        return shipRepository.count();
    }

    @Override
    public Ship get(Long id) {
        Ship ship = shipRepository.findById(id).orElse(null);
        return ship;
    }

    @Override
    @org.springframework.transaction.annotation.Transactional
    public Ship update(Ship ship) {
        return shipRepository.save(ship);
    }
}
