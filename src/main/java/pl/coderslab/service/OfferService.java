package pl.coderslab.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.acls.model.NotFoundException;
import org.springframework.stereotype.Service;
import pl.coderslab.entity.Customer;
import pl.coderslab.entity.Event;
import pl.coderslab.entity.Offer;
import pl.coderslab.repository.OfferRepository;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OfferService {
    @Autowired
    private final OfferRepository offerRepository;

    public List<Offer> findAllByDesignerId(Long id) {
        return offerRepository.findAllByDesignerId(id);
    }

    public void save(Offer offer) {
        offerRepository.save(offer);
    }

    public void delete(Offer offer) {
        offerRepository.delete(offer);
    }

    public Offer findById(Long id) {
        Optional<Offer> offer = offerRepository.findById(id);
        if (offer.isEmpty()) {
            throw new NotFoundException("Offer not found");
        }
        return offer.get();
    }

    public List<Offer> findByEvent(Event event) {
        return offerRepository.findAllByEvents(event);
    }

    public List<Offer> findByDesignerAndTemplate(Long id) {
        return offerRepository.findAllByDesignerIdAndAndTemplateIsTrue(id);
    }

    public Offer findByCustomer(Customer customer) {
        return offerRepository.findByCustomer(customer);
    }
}
