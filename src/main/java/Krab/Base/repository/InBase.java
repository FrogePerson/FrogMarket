package Krab.Base.repository;

import org.springframework.stereotype.Repository;

import Krab.Base.Models.Frog;
import Krab.Base.BaseConnection.*;

import java.util.List;

@Repository
public class InBase {
    public List<Frog> getFrogs() {
        return Select.getFrogs();
    }


    public Frog SaveFrog(Frog Frog) {
        Insert.addFrog(Frog.getName(), Frog.getCost());
        return Frog;
    }


    public Frog FindByName(String name) {
        return null;
    }


    public Frog UpdateFrog(Frog Frog) {
        return null;
    }

    public void deleteFrogByName(String name) {
        Delete.deleteFrogByName(name);
    }
    
    public void addColumn(String columnName, String columnType) {
        Alter.addColumn(columnName,columnType);
    }
    
    public void delleteColumn(String columnName) {
        Alter.delleteColumn(columnName);
    }
}
