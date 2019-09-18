package com.space.dto;

import com.space.model.ShipType;
import lombok.*;

import javax.persistence.Column;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ShipDto {
//    private static final SimpleDateFormat dateFormat
//            = new SimpleDateFormat("yyyy-MM-dd");

    private Long id;

    private String name;

    private String planet;

    private ShipType shipType;

    private Long prodDate;

    private Boolean isUsed = false;

    private Double speed;

    private Integer crewSize;

    private Double rating;



//Метод для возврата даты из строки
//    public Date getDateConverted(String timezone) throws ParseException {
//        dateFormat.format(prodDate);
//        dateFormat.setTimeZone(TimeZone.getTimeZone(timezone));
//        return dateFormat.parse(prodDate.toString());
//}


}
