package pl.coderslab.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.entity.Authorization;
import pl.coderslab.repository.AuthorizationRepository;

@Service
@RequiredArgsConstructor
public class AuthorizationService {
    @Autowired
    private final AuthorizationRepository authorizationRepository;

    public Authorization findByCustomerId(Long id) {
        return authorizationRepository.getAuthorizationsByCustomer_Id(id).get();
    }

    public void save(Authorization authorization) {
        authorizationRepository.save(authorization);
    }

}
