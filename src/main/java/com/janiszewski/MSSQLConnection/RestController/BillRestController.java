package com.janiszewski.MSSQLConnection.RestController;

import com.janiszewski.MSSQLConnection.entity.Bill;
import com.janiszewski.MSSQLConnection.repository.BillRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/bills")
@AllArgsConstructor
public class BillRestController {
    private final BillRepository billRepo;

    // http://localhost:8080/bills
    @GetMapping
    public List<Bill> billFindAll() {
        List<Bill> billList = billRepo.findAll();
        return billList;
    }

    // http://localhost:8080/bills/1
    @GetMapping("/{locationId}")
    public List<Bill> findAllByLocationId(@PathVariable(name = "locationId") Long locId) {
        //List<Bill> billList = billRepo.findAllByLocationIdOrderByDate(locId);
        List<Bill> billList = billRepo.findAllByLocationIdOrderByDateAscBillGroupAsc(locId);
        return billList;
    }

    // http://localhost:8080/bills/1/1
    @GetMapping("/{locationId}/{groupId}")
    public List<Bill> findAllByLocationIdAndBillGroupId(@PathVariable(name = "locationId") Long locId,
                                                      @PathVariable(name = "groupId") Long groupId) {
        List<Bill> billList = billRepo.findAllByLocationIdAndBillGroupIdOrderByDate(locId, groupId);
        return billList;
    }

    //add a record
    /*@PostMapping()
    public Bill InsertBill(@ModelAttribute Bill bill){
        //System.out.println(bill);
        Optional<Bill> bill1 = billRepo.findById((bill.getId()));
        if (bill1.isPresent()!=true) {
            return billRepo.save(bill);
        }
        else
            return null;
    }*/

    //add a record
    @ResponseBody
    @PostMapping()
    public Bill InsertBill(@RequestBody Bill bill) {
        Optional<Bill> bill1 = billRepo.findById((bill.getId()));
        if (bill1.isPresent()!=true) {
            return billRepo.save(bill);
        }
        else
            return null;
    }

    //edit a record
    @PutMapping()
    public Bill UpdateBill(@ModelAttribute Bill bill){
        //System.out.println(bill);
        Optional<Bill> bill1 = billRepo.findById((bill.getId()));
        if (bill1.isPresent()==true) {
            return billRepo.save(bill);
        }
        else
            return null;
    }

    //delete a record
    @DeleteMapping("/{id}")
    public void DeleteBill(@PathVariable Long id) {
        billRepo.deleteById(id);
        //TODO how to inform about the successful operation?
    }

}
