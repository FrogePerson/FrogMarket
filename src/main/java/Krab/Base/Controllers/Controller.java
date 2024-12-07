package Krab.Base.Controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;

import Krab.Base.Services.FrogService;

import Krab.Base.Models.Frog;

import java.util.List;



@RestController
@RequestMapping("/Frogs")
public class Controller {
    private FrogService FrogService;

    public Controller(FrogService FrogService) {
        this.FrogService = FrogService;
    }
    
    @GetMapping
    public List<Frog> getFrogs(){
        
        //todo
        
        return FrogService.getFrogs();
    }
}
