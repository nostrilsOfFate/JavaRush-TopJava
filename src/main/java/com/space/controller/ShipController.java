package com.space.controller;

import com.space.dto.EntityMapper;
import com.space.dto.ShipDto;
import com.space.exceptions.ShipNotFoundException;
import com.space.model.Ship;
import com.space.model.ShipType;
import com.space.model.ViewShipFilter;
import com.space.service.ShipService;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/rest/ships")
@Transactional
public class ShipController {

    private ShipService shipService;

    @Autowired
    public ShipController(ShipService shipService) {
        this.shipService = shipService;
    }

    @PostMapping(value = "/")
    public ResponseEntity<ShipDto> createShip(@RequestBody ShipDto shipDto) {
        log.info("Create ship.");
        try {
            Ship ship = EntityMapper.fromShipDto(shipDto);
            Ship shipCreated = shipService.create(ship);
            return new ResponseEntity<>(EntityMapper.toShipDto(shipCreated), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/count")
    public ResponseEntity<Long> getCount() {
        log.info("Count ship.");
        try {
            Long s = shipService.getCount();
            return new ResponseEntity<>(s, HttpStatus.OK);
        } catch (Exception e) {
            return null;
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deleteShip(@PathVariable("id") @NonNull Object id) {
        log.info("Delete ship by id: {}", id);
        try {
            checkId(id);
            shipService.delete(Long.parseLong((String) id));
            return new ResponseEntity<>(HttpStatus.OK); //200
        } catch (IllegalArgumentException e) {//400
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (ShipNotFoundException | ClassCastException e) { //404
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/{id}") //
    public ResponseEntity<ShipDto> getShip(@PathVariable("id") Object id) {
        log.info("Get ship by id: {}", id);
        try {
            checkId(id);
            Ship ship = shipService.get(Long.parseLong((String) id));
            return new ResponseEntity<>(EntityMapper.toShipDto(ship), HttpStatus.OK); //EntityMapper.toShipDto(shipService.get(id));
        } catch (IllegalArgumentException ignored) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);//400
        } catch (ShipNotFoundException | ClassCastException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); //404
        }
    }

    @GetMapping()
    public List<ShipDto> getAllShips(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "planet", required = false) String planet,
            @RequestParam(value = "shipType", required = false) ShipType shipType,
            @RequestParam(value = "after", required = false) Long after,
            @RequestParam(value = "before", required = false) Long before,
            @RequestParam(value = "isUsed", required = false, defaultValue = "false") Boolean isUsed,
            @RequestParam(value = "minSpeed", required = false) Double minSpeed,
            @RequestParam(value = "maxSpeed", required = false) Double maxSpeed,
            @RequestParam(value = "minCrewSize", required = false) Integer minCrewSize,
            @RequestParam(value = "maxCrewSize", required = false) Integer maxCrewSize,
            @RequestParam(value = "minRating", required = false) Double minRating,
            @RequestParam(value = "maxRating", required = false) Double maxRating,
            @RequestParam(value = "order", required = false) ShipOrder order,
            @RequestParam(value = "pageNumber", required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", required = false) Integer pageSize) {
        log.info("Get ships by ");
        ViewShipFilter filter = new ViewShipFilter(name, planet, shipType,
                after, before, isUsed, minSpeed, maxSpeed, minCrewSize,
                maxCrewSize, minRating, maxRating, order, pageNumber, pageSize);

        return shipService.getAll(filter)
                .stream()
                .map(EntityMapper::toShipDto)
                .collect(Collectors.toList());
    }

    @PutMapping(value = "/{id}")
    public void updateShip(@RequestBody ShipDto shipDto, @PathVariable("id") Long id) {
        log.info("Update ship");
        Ship ship = EntityMapper.fromShipDto(shipDto);
        shipService.update(ship);
    }

    private void checkId(Object id) {
        long l;
        try {
            l = Long.parseLong((String) id);
            if (l <= 0) { // меньше или равно 0
                throw new IllegalArgumentException();
            }
        } catch (NumberFormatException e) {
            //выкидывает при нул и любом НЕ лонге(дабл и прочие не числ.форматы)
            throw new IllegalArgumentException();
        }
    }
}
