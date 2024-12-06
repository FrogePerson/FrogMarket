package Krab.Base.Controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;

import Krab.Base.Models.Frog;

import java.util.List;



@RestController
@RequestMapping("/Frogs")
public class Controller {
    @GetMapping
    public List<Frog> getFrogs(){
        
        Frog Frog0 = new Frog("Pablo",1580);
        Frog Frog1 = new Frog("Pepitto",1770);
        
        return List.of(Frog0,Frog1);
    }
}
