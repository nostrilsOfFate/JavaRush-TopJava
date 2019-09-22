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
import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;

import static com.space.util.ShipHelper.validateId;
import static com.space.util.ShipHelper.validateShip;

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
            validateShip(shipDto);
            Ship shipCreated = shipService.create(EntityMapper.fromShipDto(shipDto));
            return new ResponseEntity<>(EntityMapper.toShipDto(shipCreated), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/count")
    public ResponseEntity<Long> getCount(@RequestParam(value = "name", required = false) String name,
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
        log.info("Count ship.");
        ViewShipFilter filter = new ViewShipFilter(name, planet, shipType,
                after, before, isUsed, minSpeed, maxSpeed, minCrewSize,
                maxCrewSize, minRating, maxRating, order, pageNumber, pageSize);
        Long s = shipService.getCount(filter);
        return new ResponseEntity<>(s, HttpStatus.OK);
    }

@DeleteMapping(value = "/{id}")
public ResponseEntity<String> deleteShip(@PathVariable("id") @NonNull Object id){
        log.info("Delete ship by id: {}",id);
        try{
        validateId(id);
        shipService.delete(Long.parseLong((String)id));
        return new ResponseEntity<>(HttpStatus.OK); //200
        }catch(IllegalArgumentException e){//400
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch(ShipNotFoundException|ClassCastException e){ //404
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        }

@GetMapping(value = "/{id}") //
public ResponseEntity<ShipDto> getShip(@PathVariable("id") Object id){
        log.info("Get ship by id: {}",id);
        try{
        validateId(id);
        Ship ship=shipService.get(Long.parseLong((String)id));
        return new ResponseEntity<>(EntityMapper.toShipDto(ship),HttpStatus.OK); //EntityMapper.toShipDto(shipService.get(id));
        }catch(IllegalArgumentException ignored){
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);//400
        }catch(ShipNotFoundException|ClassCastException e){
        return new ResponseEntity<>(HttpStatus.NOT_FOUND); //404
        }
        }

@GetMapping()
public ResponseEntity<List<ShipDto>>getAllShips(
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
@RequestParam(value = "pageSize", required = false) Integer pageSize){
        log.info("Get ships by:");
        ViewShipFilter filter=new ViewShipFilter(name,planet,shipType,
        after,before,isUsed,minSpeed,maxSpeed,minCrewSize,
        maxCrewSize,minRating,maxRating,order,pageNumber,pageSize);

        List<ShipDto> shipsCreated=shipService.getAll(filter)
        .stream()
        .map(EntityMapper::toShipDto)
        .collect(Collectors.toList());
        return new ResponseEntity<>(shipsCreated,HttpStatus.OK);
        }

@PostMapping(value = "/{id}")
public ResponseEntity<ShipDto> updateShip(@PathVariable("id") Long id,@RequestBody ShipDto shipDto){
        log.info("Update ship");
        try{
        validateId(shipDto.getId());
        validateShip(shipDto);
        Ship ship=shipService.update(EntityMapper.fromShipDto(shipDto));
        return new ResponseEntity<>(EntityMapper.toShipDto(ship),HttpStatus.OK);
        }catch(IllegalArgumentException|ParseException e){
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch(ShipNotFoundException e){
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        }
        }
