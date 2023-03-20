package battle;

import java.util.HashSet;
import java.util.Random;

/**
 * Represents the model of a battle.

 * @author ZongyuWu
 *
 */
public class BattleModel implements Battle {
  
  private Player[] players;
  private int numPlayer;
  private Random random;
  
  /**
   * Generate a model with two players and a random for 
   * future usage.
   */
  public BattleModel() {
    this.players = new Player[2];
    this.numPlayer = 0;
    this.random = new Random();
  }

  @Override
  public Player addPlayer(String name) {
    if (numPlayer >= 2) {
      throw new IllegalArgumentException("Cannot add more player.");
    }
    players[numPlayer] = new BattlePlayer(name);
    numPlayer += 1;
    return players[numPlayer - 1];
  }
  
  @Override
  public void assign(Player player, int strength, int constitution, int dexterity, int charisma) {
    player.assignAbility(strength, constitution, dexterity, charisma);
  }
  
  @Override
  public Player[] getPlayer() {
    return players;
  }

  @Override
  public Gear[] generateGearBag() {
    HashSet<Integer> diminish = new HashSet<>();
    while (diminish.size() < 10) {
      diminish.add(random.nextInt(40));
    }
    int contain;
    
    Gear[] bag = new Gear[40];
    int i = 0;
    // Generate headgears.
    while (i < 5) {
      contain = diminish.contains(i) ? -1 : 1;
      bag[i] = new Headgear(0, contain * (random.nextInt(10) + 1), 0, 0);
      i++;
    }
    
    // Generate potions.
    while (i < 20) {
      int which = random.nextInt(4);
      contain = diminish.contains(i) ? -1 : 1;
      bag[i] = new Potions((which == 0 ? contain : 0) * (random.nextInt(10) + 1),
          (which == 1 ? contain : 0) * (random.nextInt(10) + 1),
          (which == 2 ? contain : 0) * (random.nextInt(10) + 1),
          (which == 3 ? contain : 0) * (random.nextInt(10) + 1));
      i++;
    }
    
    // Generate belts.
    while (i < 35) {
      // Decide which two abilities will be changed.
      HashSet<Integer> which = new HashSet<>();
      while (which.size() < 2) {
        which.add(random.nextInt(4));
      }
      contain = diminish.contains(i) ? -1 : 1;
      bag[i] = new Belts((which.contains(0) ? contain : 0) * (random.nextInt(10) + 1),
          (which.contains(1) ? contain : 0) * (random.nextInt(10) + 1),
          (which.contains(2) ? contain : 0) * (random.nextInt(10) + 1),
          (which.contains(3) ? contain : 0) * (random.nextInt(10) + 1),
          Size.values()[random.nextInt(3)]);
      i++;
    }
    
    // Generate foorwears.
    while (i < 40) {
      contain = diminish.contains(i) ? -1 : 1;
      bag[i] = new Footwear(0, 0, contain * (random.nextInt(10) + 1), 0);
      i++;
    }
    
    return bag;
  }

  @Override
  public void equipPlayer(Player player, Gear[] gears) {
    // Decide which gears will be used.
    HashSet<Integer> which = new HashSet<>();
    while (which.size() < 20) {
      which.add(random.nextInt(40));
    }
    
    // Equip the decided gears.
    for (int i = 0; i < 40; i++) {
      if (!which.contains(i)) {
        continue;
      }
      if (gears[i] instanceof Belts) {
        Belts b = (Belts) gears[i];
        if (player.remainingBeltUnit() >= b.getUnit()) {
          player.addBelt(b);
        }
      } else if (gears[i] instanceof Headgear) {
        if (player.getHeadgear() == null) {
          player.addHeadgear((Headgear) gears[i]);
        }
      } else if (gears[i] instanceof Potions) {
        player.addPotions((Potions) gears[i]);
      } else if (gears[i] instanceof Headgear) {
        if (player.getFootwear() == null) {
          player.addFootwear((Footwear) gears[i]);
        }
      }   
    }
    
  }

  @Override
  public void requestWeapon(Player player) {
    player.addWeapon(Weapon.values()[random.nextInt(5)]);
  }

  @Override
  public String playerDescription(Player player) {
    StringBuffer res = new StringBuffer();
    res.append("This player is " + player.getName() + ".\n");
    res.append("The base ability values of this player are:\n");
    res.append(String.format("Strength: %d\n", player.getStrength()));
    res.append(String.format("Constitution: %d\n", player.getConstitution()));
    res.append(String.format("Dexterity: %d\n", player.getDexterity()));
    res.append(String.format("Charisma: %d\n\n", player.getCharisma()));
    
    int str = player.getStrength();
    int con = player.getConstitution();
    int dex = player.getDexterity();
    int cha = player.getCharisma();
    
    if (player.getHeadgear() != null) {
      res.append(String.format("His/Her headwear adds constitution by %d\n", 
          player.getHeadgear().getConstitutionChange()));
      con += player.getHeadgear().getConstitutionChange();
    }
    
    if (player.getPotions() != null) {
      for (Potions p : player.getPotions()) {
        res.append(String.format("His/Her potion adds abilities by %d, %d, %d, %d\n", 
            p.getStrengthChange(), p.getConstitutionChange(), 
            p.getDexterityChange(), p.getCharismaChange()));
        str += p.getStrengthChange();
        con += p.getConstitutionChange();
        dex += p.getDexterityChange();
        cha += p.getCharismaChange();
      }
    }
    
    if (player.getBelts() != null) {
      for (Belts b : player.getBelts()) {
        res.append(String.format("His/Her belt adds abilities by %d, %d, %d, %d\n", 
            b.getStrengthChange(), b.getConstitutionChange(), 
            b.getDexterityChange(), b.getCharismaChange()));
        str += b.getStrengthChange();
        con += b.getConstitutionChange();
        dex += b.getDexterityChange();
        cha += b.getCharismaChange();
      }
    }
    
    if (player.getFootwear() != null) {
      res.append(String.format("His/Her footwear adds dexterity by %d\n", 
          player.getFootwear().getDexterityChange()));
      dex += player.getFootwear().getDexterityChange();
    }
    
    res.append("\nThe final ability values of this player are:\n");
    res.append(String.format("Strength: %d\n", str));
    res.append(String.format("Constitution: %d\n", con));
    res.append(String.format("Dexterity: %d\n", dex));
    res.append(String.format("Charisma: %d\n\n", cha));
    
    Weapon weapon = player.getWeapon();
    res.append(String.format("His/Her weapon is %s with attack in range "
        + "%d, %d.\nThe ability requirement of this weapon is %d.\n", 
        weapon.name(), weapon.getLow(), weapon.getHigh(), weapon.getRequirement()));

    return res.toString();
  }
  
  @Override
  public void restart() {
    for (Player p : players) {
      p.resetHealth();
    }
  }

}
