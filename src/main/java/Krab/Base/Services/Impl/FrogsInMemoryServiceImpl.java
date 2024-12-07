package Krab.Base.Services.Impl;

import org.springframework.stereotype.Service;

import Krab.Base.Models.Frog;
import Krab.Base.Services.FrogService;
import Krab.Base.repository.InMemory;

import java.util.List;

@Service
public class FrogsInMemoryServiceImpl implements FrogService{
    private final InMemory repository;

    @Override
    public List<Frog> getFrogs() {
        /*Frog Frog0 = new Frog(0,"Pablo",1580);
        Frog Frog1 = new Frog(1,"Pepitto",1770);
        Frog Frog2 = new Frog(2,"Katawunga",1520);
        Frog Frog3 = new Frog(3,"Mui",1980);
        Frog Frog4 = new Frog(4,"Mui Stack",980);
        */
        
        return repository.getFrogs();
    }

    public FrogsInMemoryServiceImpl(InMemory repository) {
        this.repository = repository;
    }

    @Override
    public Frog SeveFrog(Frog Frog) {
        return repository.SeveFrog(Frog);
    }

    @Override
    public Frog FindByName(String name) {
        return repository.FindByName(name);
    }

    @Override
    public Frog UpdateFrog(Frog Frog) {
        return repository.UpdateFrog(Frog);
    }
    @Override
    public void DeleteFrog(String name) {
        repository.DeleteFrog(name);
    }
    
    
    
}
