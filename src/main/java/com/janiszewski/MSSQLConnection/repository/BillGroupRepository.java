package com.janiszewski.MSSQLConnection.repository;

import com.janiszewski.MSSQLConnection.entity.BillGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BillGroupRepository extends JpaRepository<BillGroup,Long> {
    List<BillGroup> findAllByDescription(String description);
}
