package com.space.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "cosmoport.ship")
public class Ship {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    public Long id;

    @Column(name = "name")
    public String name;

    @Column(name = "planet")
    public String planet;

    @Column(name = "shipType")
    public ShipType shipType;

    @Column(name = "prodDate")
    public Date prodDate; //2800-3018 диапазон, но дата передается в формате лонг в милисекундах

    @Column(name = "isUsed")
    public Boolean isUsed = false;

    @Column(name = "speed")
    public double speed; //0.01-0.99 исп математич округление до сотых

    @Column(name = "crewSize")
    public Integer crewSize; //1..9999

    @Column(name = "rating")
    public Double rating;// исп математич округление до сотых

    public Ship() {

    }

    public Ship( String name, String planet, ShipType shipType, Date prodDate, Boolean isUsed, double speed, Integer crewSize) {
        this.name = name;
        this.planet = planet;
        this.shipType = shipType;
        this.prodDate = prodDate;
        this.isUsed = isUsed;
        this.speed = speed;
        this.crewSize = crewSize;
    }

}
