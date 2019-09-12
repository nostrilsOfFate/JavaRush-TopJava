package com.space.controller;

import com.space.service.ShipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

@Controller
public class MainController {

    private ShipService shipService;

@Autowired
@Qualifier(value = "shipService")
    public void setShipService(ShipService shipService) {
        this.shipService = shipService;
    }


}
