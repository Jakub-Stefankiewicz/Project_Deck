package pl.coderslab.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.entity.Tree;
import pl.coderslab.repository.TreeRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TreeService {
    @Autowired
    private TreeRepository treeRepository;

    public void save(Tree tree) {
        treeRepository.save(tree);
    }

    public List<Tree> findAll() {
        return treeRepository.findAll();
    }

    public Tree findById(Long id) {
        return treeRepository.findById(id).get();
    }

    public void delete(Tree tree){
        treeRepository.delete(tree);
    }

}
