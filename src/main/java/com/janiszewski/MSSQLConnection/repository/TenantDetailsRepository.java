package com.janiszewski.MSSQLConnection.repository;

import com.janiszewski.MSSQLConnection.entity.TenantDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TenantDetailsRepository extends JpaRepository<TenantDetails,Long> {
}
