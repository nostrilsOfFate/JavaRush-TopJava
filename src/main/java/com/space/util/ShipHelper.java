package com.space.util;

import com.space.controller.ShipOrder;
import com.space.model.Ship;
import com.space.model.ViewShipFilter;

import java.util.ArrayList;
import java.util.List;

public class ShipHelper {
    private ShipHelper() {
    }

    public static Double countRating(Ship ship) {
        int thisYear = 3019;
        int yearOfBorn = ship.getProdDate().getYear();
        double a = (ship.getUsed()) ? 0.5 : 1;
        Double rating = (80 * ship.getSpeed() * a) / (thisYear - yearOfBorn + 1);
        Double roundOff = Math.round(rating * 100.0) / 100.0;
        return roundOff;
    }

    public static List<Ship> getList(ViewShipFilter filter, List<Ship> ships) {
        // есть ли ограничение по имени
        if (filter.getName() != null) {
            for (int i = ships.size() - 1; i >= 1; i--) {//для каждого элемента списка
                if (ships.get(i).getName().contains(filter.getName())) {
                } //если совпаадет пропускай
                else { //иначе удаляй
                    ships.remove(i);
                }
            }
        }

        // есть ли ограничение по планете
        if (filter.getPlanet() != null) {
            for (int i = ships.size() - 1; i >= 1; i--) {
                if (ships.get(i).getPlanet().contains(filter.getPlanet())) {
                } else {
                    ships.remove(i);
                }
            }
        }

        // есть ли ограничение по типу корабля
        if (filter.getShipType() != null) {//        private ShipType shipType;
            if (filter.getShipType().equals("TRANSPORT")) {
                for (int i = ships.size() - 1; i >= 1; i--) {
                    if (ships.get(i).getShipType().equals(filter.getShipType())) {
                    } else {
                        ships.remove(i);
                    }
                }
            } else if (filter.getShipType().equals("MILITARY")) {
                for (int i = ships.size() - 1; i >= 1; i--) {
                    if (ships.get(i).getShipType().equals(filter.getShipType())) {
                    } else {
                        ships.remove(i);
                    }
                }
            } else if (filter.getShipType().equals("MERCHANT")) {
                for (int i = ships.size() - 1; i >= 1; i--) {
                    if (ships.get(i).getShipType().equals(filter.getShipType())) {
                    } else {
                        ships.remove(i);
                    }
                }
            }
        }

        // есть ли ограничение по времени после
        if (filter.getAfter() != null) {
            for (int i = ships.size() - 1; i >= 1; i--) {
                if (ships.get(i).getProdDate().getTime() >= filter.getAfter()) {
                    ships.remove(i);
                }
            }
        }

        // есть ли ограничение по времени до
        if (filter.getBefore() != null) {
            for (int i = ships.size() - 1; i >= 1; i--) {
                if (ships.get(i).getProdDate().getTime() <= filter.getBefore()) {
                    ships.remove(i);
                }
            }
        }

        // есть ли ограничение по использованию
        if (filter.getIsUsed() != null && filter.getIsUsed()) { // если тру
            for (int i = ships.size() - 1; i >= 1; i--) {
                if (!ships.get(i).getUsed()) {
                    ships.remove(i);
                } //даляем то что тру
            }
        }
        if (filter.getIsUsed() != null && !filter.getIsUsed()) { // если фолс
            for (int i = ships.size() - 1; i >= 1; i--) {
                if (ships.get(i).getUsed()) {// удаляем то что фолс
                    ships.remove(i);
                }
            }
        }

        // есть ли ограничение по мин скорости
        if (filter.getMinSpeed() != null) {
            for (int i = ships.size() - 1; i >= 1; i--) {
                if (ships.get(i).getSpeed() <= filter.getMinSpeed()) {
                    ships.remove(i);
                }
            }
        }
        // есть ли ограничение по мах скорости
        if (filter.getMinSpeed() != null) {
            for (int i = ships.size() - 1; i >= 1; i--) {
                if (ships.get(i).getSpeed() >= filter.getMaxSpeed()) {
                    ships.remove(i);
                }
            }
        }
        // есть ли ограничение по мин команде
        if (filter.getMinCrewSize() != null) {
            for (int i = ships.size() - 1; i >= 1; i--) {
                if (ships.get(i).getCrewSize() <= filter.getMinCrewSize()) {
                    ships.remove(i);
                }
            }
        }
        // есть ли ограничение по мах команде
        if (filter.getMaxCrewSize() != null) {
            for (int i = ships.size() - 1; i >= 1; i--) {
                if (ships.get(i).getCrewSize() >= filter.getMaxCrewSize()) {
                    ships.remove(i);
                }
            }
        }
        // есть ли ограничение по мин рейтингу
        if (filter.getMinRating() != null) {
            for (int i = ships.size() - 1; i >= 1; i--) {
                if (ships.get(i).getRating() <= filter.getMinRating()) {
                    ships.remove(i);
                }
            }
        }
        // есть ли ограничение по мах рейтингу
        if (filter.getMaxRating() != null) {
            for (int i = ships.size() - 1; i >= 1; i--) {
                if (ships.get(i).getRating() >= filter.getMaxRating()) {
                    ships.remove(i);
                }
            }
        }

        if (filter.getOrder() != null) {
            sortShip(ships, filter);
        }

        //int skip = pageNumber * pageSize;
        //        List<ShipInfoTest> result = new ArrayList<>();
        //        for (int i = skip; i < Math.min(skip + pageSize, ships.size()); i++) {
        //            result.add(ships.get(i));
        //        }
        //        return result;

        if (filter.getPageNumber() != null) {
            if (filter.getPageSize() != null) {
                List<Ship> result = new ArrayList<>();
                int skip = filter.getPageNumber() * filter.getPageSize();//обьем прошедний до данной страницы вкл
                for (int i = skip; i < Math.min(skip + filter.getPageSize(), ships.size()); i++) {//перечисление страниц
                    result.add(ships.get(i));
                }
            } else {
                int pageSize = 3;
                List<Ship> result = new ArrayList<>();
                int skip = filter.getPageNumber() * filter.getPageSize();//обьем прошедний до данной страницы вкл
                for (int i = skip; i < Math.min(skip + filter.getPageSize(), ships.size()); i++) {//перечисление страниц
                    result.add(ships.get(i));
                }
            }

        } else {
            int pageNumber = 0;
            if (filter.getPageSize() != null) {

            } else {
                int pageSize = 3;

            }

        }
        return ships;
    }

    private static List<Ship> sortShip(List<Ship> ships, ViewShipFilter filter) {
        if (filter.getOrder() == ShipOrder.ID) {
            ships.sort((o1, o2) -> (int) (o1.getId() - o2.getId()));
        } else if (filter.getOrder() == ShipOrder.DATE) {
            ships.sort((o1, o2) -> (int) (o1.getProdDate().getTime() - o2.getProdDate().getTime()));
        } else if (filter.getOrder() == ShipOrder.SPEED) {
            ships.sort((o1, o2) -> {
                if (o1.getSpeed() > o2.getSpeed())
                    return 1;
                else if (o1.getSpeed().equals(o2.getSpeed()))
                    return 0;
                else
                    return -1;
            });
        } else if (filter.getOrder() == ShipOrder.RATING) {
            ships.sort((o1, o2) -> {
                if (o1.getRating() > o2.getRating())
                    return 1;
                else if (o1.getRating().equals(o2.getRating()))
                    return 0;
                else
                    return -1;
            });
        }
        return ships;
    }

}
