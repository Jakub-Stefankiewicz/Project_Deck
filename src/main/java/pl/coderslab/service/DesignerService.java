package pl.coderslab.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.acls.model.NotFoundException;
import org.springframework.stereotype.Service;
import pl.coderslab.entity.Designer;
import pl.coderslab.entity.User;
import pl.coderslab.repository.DesignerRepository;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class DesignerService {
    private final DesignerRepository designerRepository;

    public void save(Designer designer) {
        designerRepository.save(designer);
    }

    public Designer loadDesignerById(Long id) {
        Optional<Designer> optionalDesigner = designerRepository.findById(id);
        if (optionalDesigner.isEmpty()) {
            throw new NotFoundException("Designer not found");
        }
        return optionalDesigner.get();
    }
    public Designer findByUser(User user){
        return designerRepository.findByUser(user);
    }

}
