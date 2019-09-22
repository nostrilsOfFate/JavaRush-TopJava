package com.space.util;

import com.space.controller.ShipOrder;
import com.space.dto.ShipDto;
import com.space.model.Ship;
import com.space.model.ShipType;
import com.space.model.ViewShipFilter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class ShipHelper {
    private ShipHelper() {
    }

    public static Double countRating(ShipDto ship) {
        int thisYear = 3019;
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(new Date(ship.getProdDate()));
        int prodDateYear = calendar.get(Calendar.YEAR);
        double k = (ship.getIsUsed()) ? 0.5 : 1;
        double rating = (80 * ship.getSpeed() * k) / (thisYear - prodDateYear + 1);
        return (Math.round(rating * 100) / 100.00);
    }

    public static List<Ship> getFilteredShipList(ViewShipFilter filter, List<Ship> ships) {
        // есть ли ограничение по имени
        if (filter.getName() != null) {
            Iterator iterator = ships.iterator();
            while (iterator.hasNext()) {
                Ship ship = (Ship) iterator.next();
                if (ship.getName().toLowerCase().contains(filter.getName().toLowerCase())) {
                } else
                    iterator.remove();
            }
        }

        // есть ли ограничение по планете
        if (filter.getPlanet() != null) {
            Iterator iterator = ships.iterator();
            while (iterator.hasNext()) {
                Ship ship = (Ship) iterator.next();
                if (ship.getPlanet().toLowerCase().contains(filter.getPlanet().toLowerCase())) {
                } else iterator.remove();
            }
        }

        // есть ли ограничение по типу корабля
        if (filter.getShipType() != null) {//        private ShipType shipType;
            if (filter.getShipType().equals(ShipType.TRANSPORT)) {
                Iterator iterator = ships.iterator();
                while (iterator.hasNext()) {
                    Ship ship = (Ship) iterator.next();
                    if (ship.getShipType().equals(filter.getShipType())) {
                    } else iterator.remove();
                }
            } else if (filter.getShipType().equals(ShipType.MILITARY)) {
                Iterator iterator = ships.iterator();
                while (iterator.hasNext()) {
                    Ship ship = (Ship) iterator.next();
                    if (ship.getShipType().equals(filter.getShipType())) {
                    } else iterator.remove();
                }
            } else if (filter.getShipType().equals(ShipType.MERCHANT)) {
                Iterator iterator = ships.iterator();
                while (iterator.hasNext()) {
                    Ship ship = (Ship) iterator.next();
                    if (ship.getShipType().equals(filter.getShipType())) {
                    } else iterator.remove();
                }
            }
        }

        // есть ли ограничение по времени после
        if (filter.getAfter() != null) {
            Iterator iterator = ships.iterator();
            while (iterator.hasNext()) {
                Ship ship = (Ship) iterator.next();
                if (ship.getProdDate().getTime() < filter.getAfter()) {
                    iterator.remove();
                }
            }
        }

        // есть ли ограничение по времени до
        if (filter.getBefore() != null) {
            Iterator iterator = ships.iterator();
            while (iterator.hasNext()) {
                Ship ship = (Ship) iterator.next();
                if (ship.getProdDate().getTime() > filter.getBefore()) {
                    iterator.remove();
                }
            }
        }


        // есть ли ограничение по мин скорости
        if (filter.getMinSpeed() != null) {
            Iterator iterator = ships.iterator();
            while (iterator.hasNext()) {
                Ship ship = (Ship) iterator.next();
                if (ship.getSpeed() < filter.getMinSpeed()) {
                    iterator.remove();
                }
            }
        }

        // есть ли ограничение по мах скорости
        if (filter.getMaxSpeed() != null) {
            Iterator iterator = ships.iterator();
            while (iterator.hasNext()) {
                Ship ship = (Ship) iterator.next();
                if (ship.getSpeed() > filter.getMaxSpeed()) {
                    iterator.remove();
                }
            }
        }

        // есть ли ограничение по мин команде
        if (filter.getMinCrewSize() != null) {
            Iterator iterator = ships.iterator();
            while (iterator.hasNext()) {
                Ship ship = (Ship) iterator.next();
                if (ship.getCrewSize() < filter.getMinCrewSize()) {
                    iterator.remove();
                }
            }
        }

        // есть ли ограничение по мах команде
        if (filter.getMaxCrewSize() != null) {
            Iterator iterator = ships.iterator();
            while (iterator.hasNext()) {
                Ship ship = (Ship) iterator.next();
                if (ship.getCrewSize() > filter.getMaxCrewSize()) {
                    iterator.remove();
                }
            }
        }

        // есть ли ограничение по мин рейтингу
        if (filter.getMinRating() != null) {
            Iterator iterator = ships.iterator();
            while (iterator.hasNext()) {
                Ship ship = (Ship) iterator.next();
                if (ship.getRating() < filter.getMinRating()) {
                    iterator.remove();
                }
            }
        }
        // есть ли ограничение по мах рейтингу
        if (filter.getMaxRating() != null) {
            Iterator iterator = ships.iterator();
            while (iterator.hasNext()) {
                Ship ship = (Ship) iterator.next();
                if (ship.getRating() > filter.getMaxRating()) {
                    iterator.remove();
                }
            }
        }

        if (filter.getOrder() != null) {
            sortShip(ships, filter);
        }

        if (filter.getIsUsed() != null) {
            if (filter.getIsUsed()) {
                Iterator iterator = ships.iterator();
                while (iterator.hasNext()) {
                    Ship ship = (Ship) iterator.next();
                    if (!ship.getIsUsed()) {
                        iterator.remove();
                    }
                }
            } else {
                Iterator iterator = ships.iterator();
                while (iterator.hasNext()) {
                    Ship ship = (Ship) iterator.next();
                    if (ship.getIsUsed()) {
                        iterator.remove();
                    }
                }
            }
        }


        return ships;
    }

    public static List<Ship> getResultFilteredShipList(ViewShipFilter filter, List<Ship> ships) {
        List<Ship> result = new ArrayList<>();
        if (filter.getPageNumber() != null) {
            if (filter.getPageSize() != null) {
                int skip = filter.getPageNumber() * filter.getPageSize();//обьем прошедний до данной страницы вкл
                for (int i = skip; i < Math.min(skip + filter.getPageSize(),
                        ships.size()); i++) {//перечисление страниц
                    result.add(ships.get(i));
                }
            } else {
                int pageSize = 3;
                int skip = filter.getPageNumber() * pageSize;//обьем прошедний до данной страницы вкл
                for (int i = skip; i < Math.min(skip + pageSize, ships.size()); i++) {//перечисление страниц
                    result.add(ships.get(i));
                }
            }

        } else {
            int pageNumber = 0;
            if (filter.getPageSize() != null) {
                int skip = pageNumber * filter.getPageSize();//обьем прошедний до данной страницы вкл
                for (int i = skip; i < Math.min(skip + filter.getPageSize(), ships.size()); i++) {//перечисление страниц
                    result.add(ships.get(i));
                }
            } else {
                int pageSize = 3;
                int skip = pageNumber * pageSize;//обьем прошедний до данной страницы вкл
                for (int i = skip; i < Math.min(skip + pageSize,
                        ships.size()); i++) {//перечисление страниц
                    result.add(ships.get(i));
                }
            }
        }
        return result;
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

    public static Long validateId(Object id) throws IllegalArgumentException {
        long shipId;
        try {
            shipId = Long.parseLong((String) id);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException();
        }
        if (shipId <= 0) {
            throw new IllegalArgumentException();
        }
        return shipId;
    }

    public static void validateShipFields(ShipDto dto) throws IllegalArgumentException, ParseException {
        // ограничение, т 2800 до текущего 3019
        Date min = new SimpleDateFormat("yyyy-MM-dd").parse("2800-01-01");
        Date max = new SimpleDateFormat("yyyy-MM-dd").parse("3019-12-31");

        Date prodDate;
        if (dto.getProdDate() != null) {
            prodDate = new Date(dto.getProdDate());

            if ((prodDate.getTime() < min.getTime()) || (prodDate.getTime() > max.getTime())) {
                throw new IllegalArgumentException("Invalid time range of production date of ship (2800-3019)");
            }
        }

        if (dto.getName() != null) {
            if (dto.getName().isEmpty())
                throw new IllegalArgumentException("Empty name");
        }

        if (dto.getPlanet() != null) {
            if (dto.getPlanet().length() > 50)
                throw new IllegalArgumentException("Planet length is longer than 50");
        }

        if (dto.getCrewSize() != null) {
            if ((dto.getCrewSize() < 1) || (dto.getCrewSize() > 9999)) {
                throw new IllegalArgumentException("Invalid CrewSize of ship (not 1..9999)");
            }
        }

        if (dto.getSpeed() != null) {
            if (dto.getSpeed() < 0.01 || dto.getSpeed() > 0.99) {
                throw new IllegalArgumentException("Invalid speed range of ship (0.01-0.99)");
            }
        }
    }

    public static boolean validateShip(ShipDto dto) throws ParseException {
        if (dto.getName() == null &
                dto.getPlanet() == null &
                dto.getSpeed() == null &
                dto.getRating() == null &
                dto.getCrewSize() == null &
                dto.getProdDate() == null &
                dto.getShipType() == null &
                (dto.getIsUsed() == null || !dto.getIsUsed())
        ) {
            return false;
        }
        validateShipFields(dto);
        return true;
    }

    public static Ship updateFields(ShipDto dto, Ship ship) {
        if (dto.getName() != null)
            ship.setName(dto.getName());
        if (dto.getPlanet() != null)
            ship.setPlanet(dto.getPlanet());
        if (dto.getShipType() != null)
            ship.setShipType(dto.getShipType());
        if (dto.getProdDate() != null)
            ship.setProdDate(new Date(dto.getProdDate()));
        if (dto.getIsUsed() != null)
            ship.setIsUsed(dto.getIsUsed());
        if (dto.getSpeed() != null)
            ship.setSpeed(dto.getSpeed());
        if (dto.getCrewSize() != null)
            ship.setCrewSize(dto.getCrewSize());
        return ship;
    }

}

