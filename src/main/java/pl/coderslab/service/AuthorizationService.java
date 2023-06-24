package pl.coderslab.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.acls.model.NotFoundException;
import org.springframework.stereotype.Service;
import pl.coderslab.entity.Authorization;
import pl.coderslab.repository.AuthorizationRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthorizationService {
    @Autowired
    AuthorizationRepository authorizationRepository;

    public void save(Authorization authorization){
        authorizationRepository.save(authorization);
    }
    public Authorization getByCustomerId(Long id) {
        Optional<Authorization> authorizationOptional=authorizationRepository.getAuthorizationsByCustomer_Id(id);
        if(authorizationOptional.isEmpty()){
            throw new NotFoundException("Deal not found");
        }
        return authorizationOptional.get();
    }
}
