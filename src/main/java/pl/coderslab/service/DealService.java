package pl.coderslab.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.acls.model.NotFoundException;
import org.springframework.stereotype.Service;
import pl.coderslab.entity.Deal;
import pl.coderslab.repository.DealRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DealService {

    @Autowired
    DealRepository dealRepository;

    public void save(Deal deal) {
        dealRepository.save(deal);
    }

    public Deal getByCustomerId(Long id) {
        Optional<Deal> dealOptional=dealRepository.getDealByCustomer_Id(id);
        if(dealOptional.isEmpty()){
            throw new NotFoundException("Deal not found");
        }
        return dealOptional.get();
    }

}
