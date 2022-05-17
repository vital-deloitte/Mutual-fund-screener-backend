package Mutualfundscreenercom.example.Mutualfundapp.controller;

import Mutualfundscreenercom.example.Mutualfundapp.entities.MutualFund;
import Mutualfundscreenercom.example.Mutualfundapp.service.MutualFundService;
import Mutualfundscreenercom.example.Mutualfundapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private MutualFundService mutualFundService;

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/createMF", method = RequestMethod.POST)
    public ResponseEntity<?> createMutualFundController(@RequestBody MutualFund[] mutualFund) {
        System.out.println("create end point");
        return mutualFundService.addMutualFundService(mutualFund);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/updateMF", method = RequestMethod.PUT)
    public ResponseEntity<?> updateMutualFundController(@RequestBody MutualFund[] mutualFund) {
        return mutualFundService.updateMutualFundService(mutualFund);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/delete-account/{userId}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteUserAccountController(@PathVariable Long userId) {
        return userService.deleteAccountService(userId);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/delete-Mutual-Fund/{mutualFundId}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteMutualFundController(@PathVariable Long mutualFundId) {
        return mutualFundService.deleteMutualFundService(mutualFundId);
    }
}
