package com.space.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


@Entity
@Table(name = "ship")
@ToString
public class Ship {

    //TODO валидность айди, где ее определять

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id; //

    @Column(name = "name")
    private String name;

    @Column(name = "planet")
    private String planet;

    @Column(name = "shipType")
    @Enumerated(EnumType.STRING)
    private ShipType shipType;

    @Column(name = "prodDate")
    private Date prodDate; //2800-3018 диапазон, но дата передается в формате лонг в милисекундах

    @Column(name = "isUsed")
    private Boolean isUsed = false;

    @Column(name = "speed")
    private Double speed; //0.01-0.99 исп математич округление до сотых

    @Column(name = "crewSize")
    private Integer crewSize;

    @Column(name = "rating")
    private Double rating;// исп математич округление до сотых

    public Ship() {}

    public Ship(String name, String planet, ShipType shipType, Date prodDate, Boolean isUsed, Double speed, Integer crewSize) {
        this.name = name;
        this.planet = planet;
        this.shipType = shipType;
        this.prodDate = prodDate;
        this.isUsed = isUsed;
        this.speed = speed;
        this.crewSize = crewSize;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlanet() {
        return planet;
    }

    public void setPlanet(String planet) {
        this.planet = planet;
    }

    public ShipType getShipType() {
        return shipType;
    }

    public void setShipType(ShipType shipType) {
        this.shipType = shipType; //ShipType.valueOf(shipType);
    }

    public Date getProdDate() {
        return prodDate;
    }

    public void setProdDate(Date prodDate) {
//        try {
//            Date d =  new SimpleDateFormat("yyyy-MM-dd").parse("2800-01-01");
//            if ((prodDate<d)||())
//                this.prodDate = prodDate;
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
        // ограничение, т 2800 до текущего 3019

    }

    public Boolean getUsed() {
        return isUsed;
    }

    public void setUsed(Boolean used) {
        isUsed = used;
    }

    public Double getSpeed() {
        return speed;
    }

    public void setSpeed(Double speed) {
        double roundOff = Math.round(speed * 100.0) / 100.0;
        if ((roundOff > 0.01) && (roundOff < 0.99)) {
            this.speed = roundOff;
        } else {
            System.out.println("Invalid speed range  of ship (0.01-0.99), try again");
        }
    }

    public Integer getCrewSize() {
        return crewSize;
    }

    public void setCrewSize(Integer crewSize) {//1..9999
        if ((crewSize >= 1) && (crewSize <= 9999)) {
            this.crewSize = crewSize;
        } else {
            System.out.println("Invalid CrewSize of ship (not 1..9999), try again");
        }

    }

    public Double getRating() {
        int thisYear = 3019;
        int yearOfBorn = this.getProdDate().getYear();
        double a =(this.getUsed()) ? 0.5 : 1;
        Double rating = (80 * this.getSpeed()*a)/(thisYear-yearOfBorn+1);
        Double roundOff = Math.round(rating * 100.0) / 100.0;
        return roundOff;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

}
