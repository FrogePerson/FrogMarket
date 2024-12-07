package Krab.Base.repository;

import org.springframework.stereotype.Repository;

import Krab.Base.Models.Frog;

import java.util.List;
import java.util.ArrayList;
import java.util.stream.IntStream;

@Repository
public class InMemory {
    private final List<Frog> FROGS = new ArrayList<>();
    
    public List<Frog> getFrogs() {
        return FROGS;
    }


    public Frog SeveFrog(Frog Frog) {
        FROGS.add(Frog);
        return Frog;
    }


    public Frog FindByName(String name) {
        return FROGS.stream()
                .filter(element -> element.getName().equals(name))
                .findFirst()
                .orElse(null);
    }


    public Frog UpdateFrog(Frog Frog) {
        var FrogId = IntStream.range(0,FROGS.size())
                .filter(id -> FROGS.get(id).getName().equals(Frog.getName()))
                .findFirst()
                .orElse(-1);
        if (FrogId > -1){
        FROGS.set(FrogId, Frog);
        return Frog;
        }
        return null;
    }

    public void DeleteFrog(String name) {
        var Frog = FindByName(name);
        if (Frog != null){
        FROGS.remove(Frog);
        }
    }
}
