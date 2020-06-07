package com.janiszewski.MSSQLConnection.repository;

import com.janiszewski.MSSQLConnection.entity.Bill;
import com.janiszewski.MSSQLConnection.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationRepository extends JpaRepository<Location,Long> {
    List<Location> findAllByShortName(String shortName);
}
