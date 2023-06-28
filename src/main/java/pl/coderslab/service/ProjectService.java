package pl.coderslab.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.entity.Project;
import pl.coderslab.repository.ProjectRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectService {
    @Autowired
    private final ProjectRepository projectRepository;

    public List<Project> findAll(){
        return projectRepository.findAll();
    }

    public List<Project> findAllByDesignerId(Long id){
        return projectRepository.findAllByDesignerId(id);
    }

    public void save(Project project){
        projectRepository.save(project);
    }

    public void delete(Project project){
        projectRepository.delete(project);
    }
    public Project findById(Long id){
        return projectRepository.findById(id).get();
    }
}
