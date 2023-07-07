package pl.coderslab.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.acls.model.NotFoundException;
import org.springframework.stereotype.Service;
import pl.coderslab.entity.Authorization;
import pl.coderslab.entity.Offer;
import pl.coderslab.repository.AuthorizationRepository;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthorizationService {
    @Autowired
    private final AuthorizationRepository authorizationRepository;

    public Authorization findByCustomerId(Long id) {
        Optional<Authorization> authorization = authorizationRepository.getAuthorizationsByCustomer_Id(id);
        if (authorization.isEmpty()) {
            throw new NotFoundException("Authorization not found");
        }
        return authorization.get();
    }

    public void save(Authorization authorization) {
        authorizationRepository.save(authorization);
    }

    public Authorization findByOffer(Offer offer) {
        Optional<Authorization> authorization = authorizationRepository.findByOffer(offer);
        if (authorization.isEmpty()) {
            throw new NotFoundException("Authorization not found");
        }
        return authorization.get();
    }

    public boolean existsByOffer(Offer offer) {
        return authorizationRepository.existsByOffer(offer);
    }

}
