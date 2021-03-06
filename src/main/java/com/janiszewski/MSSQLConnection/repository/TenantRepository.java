package com.janiszewski.MSSQLConnection.repository;

import com.janiszewski.MSSQLConnection.entity.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TenantRepository extends JpaRepository<Tenant,Long> {
}
