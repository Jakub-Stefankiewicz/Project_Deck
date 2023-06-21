package pl.coderslab.service;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.coderslab.entity.Designer;
import pl.coderslab.repository.DesignerRepository;

@Service
@RequiredArgsConstructor
public class DesignerService {

    private final DesignerRepository designerRepository;

    public void save(Designer designer){
        designerRepository.save(designer);
    }
}
