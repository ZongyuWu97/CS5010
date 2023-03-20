package battle;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * A demo of a battle.

 * @author ZongyuWu
 *
 */
public class Main {
  
  public static PrintStream ps = null;
  public static Random r = new Random();

  /**
   * Operations of how to do a battle.

   * @param args No args needed.
   */
  public static void main(String[] args) {
    // Set up out put file.
    try {
      ps = new PrintStream("res/output.txt");
      System.setOut(ps);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }    
    System.setOut(ps);
    ps.append("This is a battle between two players. They will "
        + "get armed and attack each other until one dies or it's "
        + "a tie. If it's not a tie, the battle information will "
        + "be generated for each round and you can choose to start "
        + "again between these two players in the end by typing "
        + "y/n.\n\n");
    

    // Generate model and two players.
    BattleModel m = new BattleModel();
    Player playerA = generatePlayer(m, "A");
    Player playerB = generatePlayer(m, "B");

    
    // Print basic information.
    ps.append("Players generated!\n" + playerA.getName() + "'s basic ability is\n");
    ps.append(playerA.getBasicAbility() + "\n");
    ps.append("\n" + playerB.getName() + "'s basic ability is\n");
    ps.append(playerB.getBasicAbility() + "\n");
    
    
    // Generate and equip gear bag. Request weapon.
    Gear[] bagA = m.generateGearBag();
    m.equipPlayer(playerA, bagA);
    m.requestWeapon(playerA);
    
    Gear[] bagB = m.generateGearBag();
    m.equipPlayer(playerB, bagB);
    m.requestWeapon(playerB);
    
    
    // Print armed information.
    ps.append("\nPlayers armed!\n\n" + playerA.getName() + "'s description is\n\n");
    ps.append(m.playerDescription(playerA));
    ps.append("\n" + playerB.getName() + "'s description is\n\n");
    ps.append(m.playerDescription(playerB));
    
    
    // Decide if this is a tie.
    int[] abilityA = playerA.getFinalAbility();
    int[] abilityB = playerB.getFinalAbility();
    if (isTie(playerA, playerB, abilityA, abilityB)) {
      ps.append("\nThis is a tie!\n");
    }
    
    // Decide who start first.
    Player curr = null;
    Player next = null;
    int[] abilityCurr;
    int[] abilityNext;
    if (abilityA[3] >= abilityB[3]) {
      curr = playerA;
      abilityCurr = abilityA;
      next = playerB;
      abilityNext = abilityB;
    } else {
      curr = playerB;
      abilityCurr = abilityB;
      next = playerA;
      abilityNext = abilityA;
    }
    ps.append("\nBattle start with " + curr.getName() + "!\n\n");
    ps.append(String.format(playerA.getName() 
        + "'s total health is %d.\n", playerA.remainingHealth()));
    ps.append(String.format(playerB.getName() 
        + "'s total health is %d.\n\n", playerB.remainingHealth()));
    
    
    // Begin battle.
    Player tmpPlayer;
    int[] tmpAbility;
    Scanner s = new Scanner(System.in);
    
    while (true) {
      while (playerA.remainingHealth() > 0 && playerB.remainingHealth() > 0) {
        ps.append(curr.getName() + "'s turn!\n");
        int time = 1;
        if (curr.getWeapon() == Weapon.Katanas) {
          time += 1;
        }
        for (int i = 0; i < time; i++) {
          if (curr.strikingPower() > next.avoidanceAbility()) {
            int damage = curr.potentialStrikingDamage() - abilityNext[1];
            ps.append("Attack succeed! ");
            ps.append(String.format("%d damage received by the opponent. ", damage));
            next.receiveDamage(curr.potentialStrikingDamage() - abilityNext[1]);
            ps.append(String.format(next.getName() 
                + "'s remain health is %d.\n\n", next.remainingHealth()));
          } else {
            ps.append("Attack failed!\n\n");
          }
        }
        tmpPlayer = curr;
        curr = next;
        next = tmpPlayer;
        
        tmpAbility = abilityCurr;
        abilityCurr = abilityNext;
        abilityNext = tmpAbility;
      }
      
      ps.append(next.getName() + " wins! Remain health is " 
          + next.remainingHealth() + "\n\n");
      
      ps.append("Type y to rematch or n to exit.\n\n");
      String x = s.next();
      if ("y".equals(x)) {
        m.restart();
        ps.append("Rematch begins!\n\n");
        continue;
      } else if ("n".equals(x)) {
        break;
      } else {
        ps.append("Please type y or n.\n\n");
      }
    }
    ps.append("\nBattle ended!\n");
    s.close();
    return;

  }

  private static Player generatePlayer(BattleModel m, String name) {
    // Generate player and get total ability.
    Player p = m.addPlayer(name);
    int total = p.getTotalAbility();
    ps.append(String.format(name + "'s total ability to be "
        + "assigned: %d\n\n", total));
    
    // Assign ability.
    List<Integer> abilities = new ArrayList<Integer>();
    for (int i = 0; i < 3; i++) {
      abilities.add(r.nextInt(total - 3 + i) + 1);
      total -= abilities.get(i);
    }
    abilities.add(total);
    p.assignAbility(abilities.get(0), abilities.get(1), 
        abilities.get(2), abilities.get(3));
    
    return p;
  }

  private static boolean isTie(Player playerA, Player playerB, int[] abilityA, int[] abilityB) {
    if ((playerA.strikingPower() <= playerA.avoidanceAbility()) 
        && (playerB.strikingPower() <= playerB.avoidanceAbility())) {
      return true;
    }
    
    int maxDamageA = maxDamage(playerA, abilityA);
    int maxDamageB = maxDamage(playerB, abilityB);
    
    if (maxDamageA <= abilityB[1] && maxDamageB <= abilityA[1]) {
      return true;
    }
    
    return false;
  }
  
  private static int maxDamage(Player player, int[] ability) {    
    int maxDamage = ability[0];
    int maxWeapon = player.getWeapon().getHigh();
    if (player.getWeapon() == Weapon.TwoHandedSwords) {
      maxDamage += ability[0] >= player.getWeapon().getRequirement() 
          ? maxWeapon : maxWeapon / 2;
    } else if (player.getWeapon() == Weapon.Flails) {
      maxDamage += ability[2] >= player.getWeapon().getRequirement() 
          ? maxWeapon : maxWeapon / 2;
    } else {
      maxDamage += maxWeapon;
    }
    return maxDamage;
  }

}
