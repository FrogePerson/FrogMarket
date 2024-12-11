package Krab.Base.Services;

import Krab.Base.Models.Frog;
import java.util.List;

public interface FrogService {
   List<Frog> getFrogs();
   Frog SaveFrog(Frog Frog);
   Frog FindByName(String name);
   Frog UpdateFrog(Frog Frog);
   void DeleteFrog(String name);
   void addColumn(String columnName, String columnType);
   void delleteColumn(String columnName);
}
