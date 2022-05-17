package Mutualfundscreenercom.example.Mutualfundapp.batch;

import Mutualfundscreenercom.example.Mutualfundapp.entities.MutualFund;
import Mutualfundscreenercom.example.Mutualfundapp.repository.MutualFundRepository;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DBWriter implements ItemWriter<MutualFund> {

    @Autowired
    private final MutualFundRepository mutualFundRepository;

    @Autowired
    public DBWriter(MutualFundRepository mutualFundRepository) {
        this.mutualFundRepository = mutualFundRepository;
    }

    @Override
    public void write(List<? extends MutualFund> mutualFunds) throws Exception {
        System.out.println(mutualFunds);
        mutualFundRepository.saveAll(mutualFunds);
    }
}