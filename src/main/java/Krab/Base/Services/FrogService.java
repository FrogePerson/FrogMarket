package Krab.Base.Services;

import Krab.Base.Models.Frog;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class FrogService {
   public List<Frog> getFrogs(){
        
        Frog Frog0 = new Frog("Pablo",1580);
        Frog Frog1 = new Frog("Pepitto",1770);

        return List.of(Frog0,Frog1);
    }
}
