package com.space.repository;

import com.space.model.Ship;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

//@Slf4j
//@Repository
//public class ShipRepositoryImpl implements ShipRepository {
//
//    private DataSource dataSource;
//
//    private JdbcTemplate jdbcTemplate;
//
//    @Autowired
//    public void setDataSource(DataSource dataSource) {
//        this.dataSource = dataSource;
//        this.jdbcTemplate = new JdbcTemplate(dataSource);
//    }
//
//
//    @Override
//    public Ship create(Ship ship) {
//        PreparedStatementCreator preparedStatementCreator = connection -> {
//            PreparedStatement ps = connection.prepareStatement(
//                    "insert into cosmoport.ship (name,planet,shipType,prodDate,isUsed,speed,crewSize) values(?,?,?,?,?,?,?)",
//                    new String[]{ship.getId().toString(),
//                            ship.getName(),
//                            ship.getPlanet(),
//                            ship.getShipType().toString(),
//                            ship.getProdDate().toString(),
//                            ship.isUsed.toString(),
//                            //ship.getSpeed(),
//                            ship.getCrewSize().toString()});
//            ps.setString(1, ship.getName());
//            ps.setString(2, ship.getPlanet());
//            ps.setString(3, ship.getShipType().toString());
//            ps.setString(4, ship.getProdDate().toString());
//            ps.setString(5, ship.isUsed.toString());
//          //  ps.setString(6, ship.getSpeed());
//            ps.setString(7, ship.getCrewSize().toString());
//            return ps;
//        };
//        KeyHolder holder = new GeneratedKeyHolder();
//        jdbcTemplate.update(preparedStatementCreator, holder);
//        ship.setId(holder.getKey().longValue());
//        return ship;
//    }
//
//    @Override
//    public Boolean delete(Long id) {
//        return jdbcTemplate.update("delete from cosmoport.ship where id = ?", id) != 0;
//    }
//
//    @Override
//    public List<Ship> getAll() {
//        return jdbcTemplate.query("SELECT * FROM cosmoport.ship", (rs, rowNum) -> setFields(rs));
//    }
//
//    @Override
//    public Integer getCount() {
//        return null;
//    }
//
//    @Override
//    public Ship get(Long id) {
//        Object[] objects = new Object[]{id};
//        Ship ship = null;
//        try {
//            ship = jdbcTemplate.queryForObject("select * from cosmoport.ship where id= ?", objects, (rs, arg) -> setFields(rs));
//        } catch (EmptyResultDataAccessException e) {
//            log.info("Empty result in getting by id");
//        }
//        return ship;
//    }
//
//    @Override
//    public Ship update(Ship ship) {
//        log.info("Update book: {}", ship.toString());
//        int i;
//        try {
//            Object[] objects = new Object[]{
//                    ship.getId(),
//                    ship.getName(),
//                    ship.getPlanet(),
//                    ship.getShipType(),
//                    ship.getProdDate(),
//                    ship.isUsed,
//                    ship.getSpeed(),
//                    ship.getCrewSize(),
//                    ship.getRating()
//            };
//            i = jdbcTemplate.update("UPDATE cosmoport.ship SET name=?, writtenYear=?, description=?,  where id=?)",
//                    objects);
//        } catch (DataAccessException e) {
//            i = 0;
//        }
//        return (i != 0) ? ship : null;
//    }
//
//    private Ship setFields(ResultSet rs) throws SQLException {
//        Ship ship1 = new Ship();
//        ship1.setId(rs.getLong("id"));
//        ship1.setName(rs.getString("name"));
//        ship1.setPlanet(rs.getString("planet"));
////TODO осознать верность заполнения
//        // ship1.setProdDate(rs.getLong("prodDate"));
//        ship1.setIsUsed(rs.getBoolean("isUsed"));
//        ship1.setSpeed(rs.getDouble("speed"));
//        ship1.setCrewSize(rs.getInt("crewSize"));
//        ship1.setRating(rs.getDouble("rating"));
//
//        return ship1;
//    }
//}
