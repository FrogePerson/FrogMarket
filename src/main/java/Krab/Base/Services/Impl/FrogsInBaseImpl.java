package Krab.Base.Services.Impl;

import Krab.Base.Models.Frog;
import Krab.Base.Services.FrogService;
import Krab.Base.repository.InBase;

import java.util.List;
import org.springframework.stereotype.Service;

@Service("FrogsInBaseImpl")
public class FrogsInBaseImpl implements FrogService{
     private final InBase repository;

    @Override
    public List<Frog> getFrogs() {
        return this.repository.getFrogs();
    }

    public FrogsInBaseImpl(InBase repository) {
        this.repository = repository;
    }

    @Override
    public Frog SaveFrog(Frog Frog) {
        return repository.SaveFrog(Frog);
    }

    @Override
    public Frog FindByName(String name) {
        return this.repository.FindByName(name);
    }

    @Override
    public Frog UpdateFrog(Frog Frog) {
        return this.repository.UpdateFrog(Frog);
    }
    @Override
    public void DeleteFrog(String name) {
        this.repository.deleteFrogByName(name);
    }

    @Override
    public void addColumn(String columnName, String columnType) {
        this.repository.addColumn(columnName,columnType);
    }

    @Override
    public void delleteColumn(String columnName) {
        this.repository.delleteColumn(columnName);
    }
    
    
}
