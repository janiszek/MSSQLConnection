package com.janiszewski.MSSQLConnection.repository;

import com.janiszewski.MSSQLConnection.entity.Contract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface ContractRepository extends JpaRepository<Contract,Long> {
    List<Contract> findByLocationIdAndDateToAfterOrderByDateToDesc(Long locationId, Date currentDate);

}
