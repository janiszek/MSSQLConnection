package com.janiszewski.MSSQLConnection.controller;

import com.janiszewski.MSSQLConnection.component.BillFilterComponent;
import com.janiszewski.MSSQLConnection.entity.*;
import com.janiszewski.MSSQLConnection.enums.BillType;
import com.janiszewski.MSSQLConnection.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.sql.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/bills")
@AllArgsConstructor
public class BillController {

    private final LocationRepository locationRepo;
    private final BillGroupRepository billGroupRepo;
    private final BillRepository billRepo;
    private final ContractRepository contractRepo;
    private final TenantRepository tenantRepo;
    private final TenantDetailsRepository tenantDetailsRepo;

    private final BillFilterComponent billFilter;
    private final String ALLBILLGROUPS = "<All>";

    private void InitializeLocationAndBillGroup(){
        List<Location> loc1 = locationRepo.findAll();
        billFilter.setLocationSelected(loc1.get(0));
        billFilter.setBillGroupSelected(null);
    }

    private Location FindLocationSelected(String locationSel) {
        List<Location> loc1;
        if (locationSel != null) {
            loc1 = locationRepo.findAllByShortName(locationSel);
        } else {
            loc1 = locationRepo.findAll();
        }
        return loc1.get(0);
    }

    private BillGroup FindBillGroupSelected(String billGroupSel) {
        if (billGroupSel==null || billGroupSel.equals(ALLBILLGROUPS)) {
            /*group1 = billGroupRepo.findAll();*/
            return null;
        } else {
            List<BillGroup> group1 = billGroupRepo.findAllByDescription(billGroupSel);
            return group1.get(0);
        }

    }

    private void AddModelAttributes(Model model){
        //load all locations and BillGroups to the select boxes
        model.addAttribute("Locations", locationRepo.findAll());
        model.addAttribute("LocationSelected", billFilter.getLocationSelected());
        model.addAttribute("BillGroups", billGroupRepo.findAll());
        model.addAttribute("BillGroupSelectedDescription", (billFilter.getBillGroupSelected()!=null?billFilter.getBillGroupSelected().getDescription():""));
        //load the bills according to the filters on the page
        if (billFilter.getBillGroupSelected()!=null)
            model.addAttribute("Bills", billRepo.findAllByLocationIdAndBillGroupIdOrderByDate(billFilter.getLocationSelected().getId(),billFilter.getBillGroupSelected().getId()));
        else
            model.addAttribute("Bills", billRepo.findAllByLocationIdOrderByDateAscBillGroupAsc(billFilter.getLocationSelected().getId()));
        //find the current contract (dateTo is bigger than today) for this location
        List<Contract> contractList = contractRepo.findByLocationIdAndDateToAfterOrderByDateToDesc(billFilter.getLocationSelected().getId(),new Date(System.currentTimeMillis()));
        if (contractList.size()>0){
            //get the latest valid contract
            Contract currentContract = contractList.get(0);
            //get the tenant who signed this contract
            model.addAttribute("Contract", currentContract);
            Tenant curTen = currentContract.getTenant();
            model.addAttribute("Tenant", curTen);
            model.addAttribute("TenantDetails", curTen.getDetails());
        }
        else{
            // if no valid contact then no valid tenant
            model.addAttribute("Contract", new Contract());
            model.addAttribute("Tenant", new Tenant());
            model.addAttribute("TenantDetails", new TenantDetails());
        }
    }

    //ANY localhost:8080/bills/show
    @RequestMapping(value={"/","/show"})
    public String showAll(Model model, @RequestParam(value = "location", required = false) String location,
                                        @RequestParam(value = "billgroup", required = false) String billgroup) {
        //if called first time in the session, then initialize the billFilter component
        if (billFilter.getLocationSelected() == null)
            InitializeLocationAndBillGroup();
        //record the passed values if the params requested are set in the POST call
        if (location != null) {
            billFilter.setLocationSelected(FindLocationSelected(location));
            billFilter.setBillGroupSelected(FindBillGroupSelected(billgroup));
        }
        AddModelAttributes(model);
        return "bills-show";
    }

    @PostMapping("/bill-update")
    public String BillUpdateGet(@RequestParam("id") Long id, Model model)
            throws Exception {
        model.addAttribute("Bills", billRepo.findById(id).orElse(null));
        return "bill-update";
    }

    private void InitializeNewBill(Bill bill){
        bill.setLocation(billFilter.getLocationSelected());
        bill.setBillGroup(billFilter.getBillGroupSelected());
        if (bill.getBillGroup()==null)
            bill.setBillGroup(billGroupRepo.findById(1L).orElse(null));
    }

    @PostMapping("/bill-add")
    public String BillAddGet(Model model) throws Exception {
        Bill bill1=new Bill();
        InitializeNewBill(bill1);
        model.addAttribute("Bills", bill1);
        return "bill-update";
    }

    @PostMapping("/bill-update-submit")
    public String BillUpdatePost(Model model, @RequestParam Map<String,String> allRequestParams){
        String strId = allRequestParams.get("id");
        Bill bill1 = null;
        if (!strId.isEmpty())
            bill1 = billRepo.findById(Long.parseLong(allRequestParams.get("id"))).orElse(null);

        //if we are adding a new record, initialize the relationship fields from the filter
        if (bill1==null){
            bill1 = new Bill();
            InitializeNewBill(bill1);
        }
        //update the record with values received from the form
        bill1.setDate(Date.valueOf(allRequestParams.get("date")));
        bill1.setAmount(Float.valueOf(allRequestParams.get("amount")));
        bill1.setStatus(BillType.valueOf(allRequestParams.get("status")));

        billRepo.save(bill1);
        AddModelAttributes(model);
        return"bills-show";
    }

    @PostMapping("/bill-delete")
    public String BillDeleteGet(@RequestParam("id") Long id, Model model)
            throws Exception {
        billRepo.deleteById(id);
        AddModelAttributes(model);
        return "bills-show";
    }



    // POST http://localhost:8080/bills/show/1
    /*@PostMapping("/show/{locationId}")
    public String ShowAllByLocationId(Model model, @PathVariable(name = "locationId") Long locId) {
        AddModelAttributes(model);
        return "bills-show";
    }*/

}
