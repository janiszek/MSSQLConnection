package com.janiszewski.MSSQLConnection.repository;

import com.janiszewski.MSSQLConnection.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository<Payment,Long> {
    List<Payment> findAllByLocationId(Long locationId);
    List<Payment> findAllByLocationIdAndTenantId(Long locationId, Long tenantId);
    List<Payment> findAllByLocationIdAndTenantIdOrderByDate(Long locationId, Long tenantId);
}
