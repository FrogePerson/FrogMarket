package Krab.Base.Controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import Krab.Base.GLOBAL;

import Krab.Base.Services.FrogService;

import Krab.Base.Models.Frog;

import java.util.List;


@RestController
@RequestMapping("/Frogs")
public class Controller {
    private FrogService FrogService;

    @Autowired
    public Controller(@Qualifier(GLOBAL.FROG_SERVICE)FrogService FrogService) {
        this.FrogService = FrogService;
    }
    
    @GetMapping
    public List<Frog> getFrogs(){
        
        return FrogService.getFrogs();
    }
    
    @PostMapping("saveFrog")
    public String saveFrog(@RequestBody Frog Frog){
        FrogService.SaveFrog(Frog);
        return "OK: Frog SAVED";
    }
    
    @GetMapping("/{name}")
    public Frog FindByName(@PathVariable String name){
        return FrogService.FindByName(name);
    }
    
    @PutMapping("UpdateFrog")
    public Frog UpdateFrog(@RequestBody Frog Frog){
        return FrogService.UpdateFrog(Frog);
    }
    
    @DeleteMapping("DeleteFrog/{name}")
    public void DeleteFrog(@PathVariable String name){
        FrogService.DeleteFrog(name);
    }
    
    @PutMapping("addColumn/{columnName}/{columnType}")
    public void addColumn(@PathVariable String columnName, @PathVariable String columnType){
        FrogService.addColumn(columnName,columnType);
    }
    
    @PutMapping("delleteColumn/{columnName}")
    public void delleteColumn(@PathVariable String columnName){
        FrogService.delleteColumn(columnName);
    }
}
