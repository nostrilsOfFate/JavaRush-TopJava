package com.space.dto;

import com.space.model.ShipType;
import lombok.*;

@Data
public class ShipDto {
//    private static final SimpleDateFormat dateFormat
//            = new SimpleDateFormat("yyyy-MM-dd");

    public Long id;
    public String name;
    public String planet;
    public ShipType shipType;
    public Long prodDate;
    public Boolean isUsed;
    public Double speed;
    public Integer crewSize;
    public Double rating;


//Метод для возврата даты из строки
//    public Date getDateConverted(String timezone) throws ParseException {
//        dateFormat.format(prodDate);
//        dateFormat.setTimeZone(TimeZone.getTimeZone(timezone));
//        return dateFormat.parse(prodDate.toString());
//}
//@Override
//public boolean equals(Object o) {
//    if (this == o) return true;
//    if (o == null || getClass() != o.getClass()) return false;
//    ShipDto shipDto = (ShipDto) o;
//    return Objects.equals(id, shipDto.id) &&
//            Objects.equals(name, shipDto.name) &&
//            Objects.equals(planet, shipDto.planet) &&
//            shipType == shipDto.shipType &&
//            Objects.equals(new Date(prodDate).getYear(), new Date(prodDate).getYear()) &&
//            Objects.equals(isUsed, shipDto.isUsed) &&
//            Objects.equals(speed, shipDto.speed) &&
//            Objects.equals(crewSize, shipDto.crewSize) &&
//            Objects.equals(rating, shipDto.rating);
//}
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(id, name, planet, shipType, prodDate, isUsed, speed, crewSize, rating);
//    }

}
