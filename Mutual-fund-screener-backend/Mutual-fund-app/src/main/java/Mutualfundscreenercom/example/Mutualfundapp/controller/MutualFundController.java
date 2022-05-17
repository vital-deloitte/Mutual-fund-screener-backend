package Mutualfundscreenercom.example.Mutualfundapp.controller;

import Mutualfundscreenercom.example.Mutualfundapp.entities.MutualFund;
import Mutualfundscreenercom.example.Mutualfundapp.service.MutualFundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/mutual-fund")
public class MutualFundController {
    @Autowired
    MutualFundService mutualFundService;

    @RequestMapping(value = "/all-mutual-funds", method = RequestMethod.GET)
    public List<MutualFund> getAllMutualFundController() {
        return mutualFundService.getAllMutualFundsService();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getMutualFundByIdController(@PathVariable("id") Long id) {
        return mutualFundService.getMutualFundService(id);
    }
}
