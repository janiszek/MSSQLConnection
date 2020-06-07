package com.janiszewski.MSSQLConnection.repository;

import com.janiszewski.MSSQLConnection.entity.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface BillRepository extends JpaRepository<Bill,Long> {
    List<Bill> findAllByLocationIdOrderByDateAscBillGroupAsc(Long locationId);
    List<Bill> findAllByLocationIdAndBillGroupIdOrderByDate(Long locationId, Long groupId);
}
