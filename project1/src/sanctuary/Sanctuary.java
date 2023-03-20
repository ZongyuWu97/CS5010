package sanctuary;

import java.util.List;

/**
 * A sanctuary contains many isolations and enclosures. Each isolation contain one monkey,
 * and each enclosure contain a species of monkey.
 * The Sanctuary should start with room for n animals in isolation and m troops of monkeys. 
 * While these are defined when the Sanctuary is constructed, the Sanctuary would like the 
 * flexibility of expanding should the needs and funds allow.

 * @author ZongyuWu
 *
 */
public interface Sanctuary {
  
  /**
   * Increase the isolation in the sanctuary by 1.
   */
  public void increaseIsolation();
  
  /**
   * Increase the enclosure in the sanctuary by 1.

   * @param size Size of the increased enclosure.
   * @param species Species of the increased enclosure.
   */
  public void increaseEnclosure(int size, Species species);
  
  /**
   * Report the species that are currently being housed in alphabetical order. 
   * The list should include where in the sanctuary each species is (both in 
   * enclosures and in isolation).
   * SpeciesLocation.species is the species housed, SpeciesLocation.location 
   * contains locations corresponding to each species.

   * @return A SpeciesLocation object.
   */
  public SpeciesLocation report();
  
  /**
   * Look up where a particular species is currently housed. If none of a 
   * particular species is currently being housed, it should report this fact.

   * @param species The name of the species to look up.
   * @return The location.
   */
  public List<Housing> lookFor(Species species);
  
  /**
   * Produce an alphabetical list (by name) of all of the monkeys housed in the 
   * Sanctuary. This information should include where each monkey can be found.
   * NameLocation.name = List of name in alphabetical order.
   * NameLocation.location = list of locations corresponding to each name.

   * @return List of NameLocation objects.
   */
  public List<NameLocation> nameList();
  
  /**
   * Produce a shopping list of the favorite foods of the inhabitants of the Sanctuary.
   * For each food listed, the quantity needed should also be listed. Large monkeys 
   * should receive 500 grams of their favorite food while medium and small monkeys 
   * require 300 grams and 100 grams, respectively.
   * Item.name = name of the food.
   * Item.quantity = quantity needed for this food.

   * @return List of Item.
   */
  public List<Item> shoppingList();
      
  /**
   * Get the isolations in the sanctuary.

   * @return List of isolations.
   */
  public List<Housing> getIsolations();
  
  /**
   * Get the enclosures in the sanctuary.

   * @return List of enclosures.
   */
  public List<Housing> getEnclosures();
}
