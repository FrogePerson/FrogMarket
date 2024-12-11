package Krab.Base.Services.Impl;

import Krab.Base.Models.Frog;
import Krab.Base.Services.FrogService;
import Krab.Base.repository.InMemory;
import Krab.Base.BaseConnection.*;

import java.util.List;
import org.springframework.stereotype.Service;

@Service("FrogsInBaseImpl")
public class FrogsInBaseImpl implements FrogService{

    @Override
    public List<Frog> getFrogs() {
        /*Frog Frog0 = new Frog(0,"Pablo",1580);
        Frog Frog1 = new Frog(1,"Pepitto",1770);
        Frog Frog2 = new Frog(2,"Katawunga",1520);
        Frog Frog3 = new Frog(3,"Mui",1980);
        Frog Frog4 = new Frog(4,"Mui Stack",980);
        */
        
        return null;
    }

    public FrogsInBaseImpl(InMemory repository) {
        
    }

    @Override
    public Frog SaveFrog(Frog Frog) {
        Insert.addFrog(Frog.getName(), Frog.getCost());
        return Frog;
    }

    @Override
    public Frog FindByName(String name) {
        return null;
    }

    @Override
    public Frog UpdateFrog(Frog Frog) {
        return null;
    }
    @Override
    public void DeleteFrog(String name) {
        Delete.deleteFrogByName(name);
    }
}
