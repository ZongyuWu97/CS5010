package sanctuary;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * A demo to show how to use the Sanctuary, Housing and Monkey classes.
 * Generate monkeys and a sanctuary. Put each monkey first into an
 * isolation then pop it to a corresponding enclosure.

 * @author ZongyuWu
 *
 */
public class Demo {
  /**
   * A demo. Also shows the required functions like report, lookup, sign,
   * name list and shopping list.

   * @param arg No input needed.
   */
  public static void main(String[] arg) {
    List<Monkey> monkeys = generateMonkeys();
    Sanctuary sanctuary = generateSanctuary();
    
    /*
     * Push monkeys into sanctuary until there is no room.
     */
    int beforeNumMonkey = monkeys.size();
    int afterNumMonkey = 0;
    
    while (beforeNumMonkey != afterNumMonkey) {
      beforeNumMonkey = monkeys.size();
      
      /*
       * Add monkey to isolation.
       */

      for (Housing iso : sanctuary.getIsolations()) {

        if (monkeys.size() > 0 && iso.isEmpty()) {
          Monkey monkey = monkeys.get(monkeys.size() - 1);
          monkeys.remove(monkey);
          iso.addMonkey(monkey);
        }
      }
      
      /*
       * Pop monkey from isolation to enclosure.
       */
      for (Housing iso : sanctuary.getIsolations()) {
        if (iso.isEmpty()) {
          break;
        }
        Monkey monkey = iso.getResidents().get(0);

        for (Housing enc : sanctuary.getEnclosures()) {
          if (enc.getSpecies() == monkey.getSpecies() 
              && enc.getRemainSize() >= monkey.getSizeInt()) {
            iso.removeMonkey(monkey);
            enc.addMonkey(monkey);
            break;
          }
        }
      }
      
      afterNumMonkey = monkeys.size();
    }
    
    /*
     * Required functions of the sanctuary.
     */
    PrintStream ps;
    try {
      ps = new PrintStream("res/output.txt");
      System.setOut(ps);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }


    SpeciesLocation report = sanctuary.report();
    System.out.println("Report of species and their locations.");
    for (int i = 0; i < report.species.size(); i++) {
      System.out.println(report.species.get(i));
      System.out.println(report.location.get(i));
    }
    System.out.println();

    List<Housing> lookfor = sanctuary.lookFor(Species.Drill);
    System.out.println("Location of the specific species.");
    System.out.println(lookfor);
    System.out.println();
    
    System.out.println("Signs for enclosures in the sanctuary.");
    for (Housing enc : sanctuary.getEnclosures()) {
      System.out.println(enc.showSign());
    }
    System.out.println();

    List<NameLocation> nameList = sanctuary.nameList();
    System.out.println("Name of each monkey and its location.");
    for (int i = 0; i < nameList.size(); i++) {
      System.out.println(nameList.get(i).name);
      System.out.println(nameList.get(i).location);
    }
    System.out.println();

    List<Item> shoppingList = sanctuary.shoppingList();
    System.out.println("Shopping list of favorite food and their quantity needed.");
    for (int i = 0; i < shoppingList.size(); i++) {
      System.out.println(shoppingList.get(i).name);
      System.out.println(shoppingList.get(i).quantity);
    }
    System.out.println();

  }
  
  
  /**
   * Generate many monkey instances and use an ArrayList to store them.
   */
  private static List<Monkey> generateMonkeys() {
    List<Monkey> monkeys = new ArrayList<Monkey>();
    
    for (Species species : Species.values()) {
      for (Sex sex : Sex.values()) {
        for (Size size : Size.values()) {
          for (Food food : Food.values()) {
            Random random = new Random();
            int weight = random.nextInt(49) + 1;
            int age = random.nextInt(10);
            String name = species.toString() + sex.toString() 
                + size.toString() + Integer.toString(weight) 
                + food.toString() + Integer.toString(age);
            
            Monkey monkey = new Monkey(name, species, sex, size, weight, food, age);
            monkeys.add(monkey);
          }
        }
      }
    }

    return monkeys;
  }
  
  
  /**
   * Generate a Sanctuary with 5 isolations and 16 enclosure.
   */
  private static Sanctuary generateSanctuary() {
    int[] enclosureSize = new int[16];
    Species[] enclosureSpecies = new Species[16];
    Random random = new Random();
    
    int i = 0;
    for (Species species : Species.values()) {
      enclosureSize[2 * i] = random.nextInt(200) + 50;
      enclosureSize[2 * i + 1] = random.nextInt(200) + 50;
      enclosureSpecies[2 * i] = species;
      enclosureSpecies[2 * i + 1] = species;
      i += 1;
    }
    
    return new JungleFriendsSanctuary(5, 16, enclosureSize, enclosureSpecies);
  }
  
}
