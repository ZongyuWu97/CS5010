package sanctuary;

import java.util.List;

/**
 * Represents a species and its corresponding location.

 * @author ZongyuWu
 *
 */
public class SpeciesLocation {
  public List<Species> species;
  public List<List<Housing>> location;
  
  public SpeciesLocation(List<Species> specie, List<List<Housing>> location) {
    this.species = specie;
    this.location = location;
  }
}
