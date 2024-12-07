package Krab.Base.Services.Impl;

import org.springframework.stereotype.Service;

import Krab.Base.Models.Frog;
import Krab.Base.Services.FrogService;
import java.util.List;

@Service
public class FrogsInMemoryServiceImpl implements FrogService{

    @Override
    public List<Frog> getFrogs() {
        Frog Frog0 = new Frog("Pablo",1580);
        Frog Frog1 = new Frog("Pepitto",1770);
        Frog Frog2 = new Frog("Katawunga",1520);
        Frog Frog3 = new Frog("Mui",1980);
        Frog Frog4 = new Frog("Mui Stack",980);
        
        return List.of(Frog0,Frog1,Frog2,Frog3,Frog4);
    }
    
}
