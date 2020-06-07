package com.janiszewski.MSSQLConnection.RestController;

import com.janiszewski.MSSQLConnection.entity.Location;
import com.janiszewski.MSSQLConnection.entity.Payment;
import com.janiszewski.MSSQLConnection.repository.PaymentRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/payments")
@AllArgsConstructor
public class PaymentRestController {

    private final PaymentRepository paymentRepo;

    @GetMapping/*("/payments")*/
    public List<Payment> findAll() {
        List<Payment> paymentList = paymentRepo.findAll();
        return paymentList;
    }

    // http://localhost:8080/payments/1
    @GetMapping("/{locationId}")
    public List<Payment> findAllByLocationId(@PathVariable(name = "locationId") Long locId) {
        List<Payment> paymentList = paymentRepo.findAllByLocationId(locId);
        List<Payment> paymentList2 = paymentRepo.findAllByLocationIdAndTenantId(locId, 1L);
        return paymentList2;
    }


    // http://localhost:8080/payments/1/1
    @GetMapping("/{locationId}/{tenantId}")
    public List<Payment> findAllByLocationIdAndTenantId(@PathVariable(name = "locationId") Long locId,
                                                     @PathVariable(name = "tenantId") Long tenantId) {
        List<Payment> paymentList = paymentRepo.findAllByLocationIdAndTenantIdOrderByDate(locId, tenantId);
        return paymentList;
    }
}
