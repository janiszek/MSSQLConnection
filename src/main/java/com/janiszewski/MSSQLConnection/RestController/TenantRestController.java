package com.janiszewski.MSSQLConnection.RestController;

import com.janiszewski.MSSQLConnection.entity.Tenant;
import com.janiszewski.MSSQLConnection.repository.TenantRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class TenantRestController {

    private final TenantRepository tenantRepo;

    @GetMapping("/tenants")
    public List<Tenant> tenantFindAll() {
        List<Tenant> tenantList = tenantRepo.findAll();
        return tenantList;
    }

}
