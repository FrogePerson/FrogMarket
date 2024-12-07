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
        
        return List.of(Frog0,Frog1);
    }
    
}
