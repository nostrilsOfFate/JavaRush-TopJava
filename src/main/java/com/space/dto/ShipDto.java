package com.space.dto;

import com.space.model.ShipType;
import lombok.*;

@Data
public class ShipDto {
    public Long id;
    public String name;
    public String planet;
    public ShipType shipType;
    public Long prodDate;
    public Boolean isUsed = false;
    public Double speed;
    public Integer crewSize;
    public Double rating;
}
