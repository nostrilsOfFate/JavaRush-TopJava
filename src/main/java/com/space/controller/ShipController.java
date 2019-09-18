package com.space.controller;

import com.space.dto.EntityMapper;
import com.space.dto.ShipDto;
import com.space.model.Ship;
import com.space.model.ShipType;
import com.space.model.ViewShipFilter;
import com.space.service.ShipService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
//@EnableJpaRepositories(repositoryBaseClass = ShipServiceImpl.class)
@RequestMapping("/rest/ships")
@Slf4j
public class ShipController {

    private ShipService shipService;

    @Autowired
    public ShipController(ShipService shipService) {
        this.shipService = shipService;
    }

//    @GetMapping("/rest/ships")
//    public List<ShipDto> getAll() {
//        return null;
//    }

    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public ShipDto createShip(@RequestBody ShipDto shipDto) {
        log.info("Create ship.");
        Ship ship = EntityMapper.fromShipDto(shipDto);
        Ship shipCreated = shipService.create(ship);
        return EntityMapper.toShipDto(shipCreated);
    }


    //    //@RequestMapping(produces = APPLICATION_JSON_VALUE, method = RequestMethod.DELETE)
    //    public EnvelopeView deleteTempFile(@RequestParam String url) {
    //        log.info("delete temp file request received");
    //        fileService.deleteTempFile(url);
    //        return new EnvelopeView(OK);
    //    }
    @DeleteMapping(value = "/{id}")
    public void deleteShip(@PathVariable("id") @RequestParam Long shipId) {
        log.info("Delete ship by id: {}", shipId);
        shipService.delete(shipId);
    }

    @GetMapping(value = "/count")
    public Long getCount() {
        return shipService.getCount();
    }

    @GetMapping(value = "/{id}") //, produces = APPLICATION_JSON_VALUE
    public ShipDto getShip(@PathVariable("id") @RequestParam Long id) {
        log.info("Get ship by id: {}", id);
        return EntityMapper.toShipDto(shipService.get(id));
    }

    //преобразование даты в наш тип данных
    //(@RequestParam("from") @DateTimeFormat(pattern="yyyy-MM-dd") Date fromDate)

    //@RequestMapping (value = "/submit/id/{id}", method = RequestMethod.GET,
    // produces="text/xml")
    //public String showLoginWindow(@PathVariable("id") String id,
    //                              @RequestParam(value = "logout") Optional<String> logout,
    //                              @RequestParam("name") Optional<String> username,
    //                              @RequestParam("password") Optional<String> password,
    //                              @ModelAttribute("submitModel") SubmitModel model,
    //                              BindingResult errors) throws LoginException {...}

//    @RequestParam(value = "pageNo", required = false) Integer pageNo, //Optional<String>

    @GetMapping()
    @ResponseBody
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


    @PutMapping
//    @ResponseStatus(HttpStatus.OK)
    public void updateShip(@RequestBody ShipDto shipDto) {
        log.info("Update ship");
        Ship ship = EntityMapper.fromShipDto(shipDto);
        shipService.update(ship);
    }

}
