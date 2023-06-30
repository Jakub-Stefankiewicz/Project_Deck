package pl.coderslab.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.entity.Offer;
import pl.coderslab.repository.OfferRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OfferService {
    @Autowired
    private final OfferRepository offerRepository;

    public List<Offer> findAll(){
        return offerRepository.findAll();
    }

    public List<Offer> findAllByDesignerId(Long id){
        return offerRepository.findAllByDesignerId(id);
    }

    public void save(Offer offer){
        offerRepository.save(offer);
    }

    public void delete(Offer offer){
        offerRepository.delete(offer);
    }
    public Offer findById(Long id){
        return offerRepository.findById(id).get();
    }
}
