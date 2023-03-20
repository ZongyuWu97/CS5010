package battle;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Represents the players of a battle. Have a name and 
 * four abilities and four type of gears. Have a weapon. 
 * Can return the remain health of this player.

 * @author ZongyuWu
 *
 */
public class BattlePlayer implements Player {
  
  private final String name;
  private final int totalAbility;
  private int strength;
  private int constitution;
  private int dexterity;
  private int charisma;
  private Headgear headgear;
  private List<Potions> potions;
  private List<Belts> belts;
  private Footwear footwear;
  private Weapon weapon;
  private int remainingHealth;
  private int remainingBeltUnit = 10;
  private Random random;
  
  /**
   * Initialize a player with his name.

   * @param name Player's name.
   */
  public BattlePlayer(String name) {
    this.name = name;
    this.random = new Random();
    this.totalAbility = this.random.nextInt(17) + 8;
    this.remainingHealth = this.totalAbility;
    this.potions = new ArrayList<>();
    this.belts = new ArrayList<>();
  }
  
  @Override
  public void assignAbility(int strength, int constitution, int dexterity, int charisma) {
    if (strength <= 0 || constitution <= 0 || dexterity <= 0 || charisma <= 0) {
      throw new IllegalArgumentException("Ability must be positive.");
    }
    if (this.totalAbility != strength + constitution + dexterity + charisma) {
      throw new IllegalArgumentException("Sum of four category not equal to total ability.");
    }
    this.strength = strength;
    this.constitution = constitution;
    this.dexterity = dexterity;
    this.charisma = charisma;    
  }
  
  @Override
  public int getTotalAbility() {
    return this.totalAbility;
  }
  
  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public int getStrength() {
    return this.strength;
  }

  @Override
  public int getConstitution() {
    return this.constitution;
  }

  @Override
  public int getDexterity() {
    return this.dexterity;
  }

  @Override
  public int getCharisma() {
    return this.charisma;
  }
  
  @Override
  public String getBasicAbility() {
    return String.format("Strength: %d, Constitution: %d,"
        + " Dexterity: %d, Charisma: %d", 
        this.getStrength(), this.getConstitution(), 
        this.getDexterity(), this.getCharisma());
  }

  @Override
  public void addHeadgear(Headgear headgear) {
    this.headgear = headgear;
  }

  @Override
  public void addPotions(Potions potions) {
    this.potions.add(potions);
  }

  @Override
  public void addBelt(Belts belt) {
    int unit = belt.getUnit();
    if (unit > this.remainingBeltUnit) {
      throw new IllegalArgumentException("Not enough belt unit.");
    }
    this.belts.add(belt);
    this.remainingBeltUnit -= unit;
  }

  @Override
  public void addFootwear(Footwear footwear) {
    this.footwear = footwear;
  }
  
  @Override
  public void addWeapon(Weapon weapon) {
    this.weapon = weapon;
  }
  
  

  @Override
  public Headgear getHeadgear() {
    return this.headgear;
  }

  @Override
  public List<Potions> getPotions() {
    return this.potions;
  }

  @Override
  public List<Belts> getBelts() {
    return this.belts;
  }

  @Override
  public Footwear getFootwear() {
    return this.footwear;
  }
  
  @Override
  public Weapon getWeapon() {
    return this.weapon;
  }

  @Override
  public int remainingHealth() {
    return this.remainingHealth;
  }
  
  @Override
  public void receiveDamage(int damage) {
    if (damage <= 0) {
      return;
    }
    this.remainingHealth -= damage;
  }

  @Override
  public int strikingPower() {
    int sp = basesp();
    
    sp += this.random.nextInt(10) + 1;
    return sp;
  }
  
  /**
   * Calculate the base striking power.

   * @return base striking power
   */
  public int basesp() {
    int sp = this.strength;
    if (this.getPotions() != null) {
      for (Potions p : this.potions) {
        sp += p.getStrengthChange();
      }
    }
    if (this.getBelts() != null) {
      for (Belts b : this.belts) {
        sp += b.getStrengthChange();
      }
    }
    return sp;
  }

  @Override
  public int avoidanceAbility() {
    int aa = baseaa();
    aa += this.random.nextInt(6) + 1;
    return aa;
  }
  
  /**
   * Helper function computing the basic avoidance.

   * @return basic avoidance.
   */
  public int baseaa() {
    int aa = this.getDexterity();
    for (Potions p : this.potions) {
      aa += p.getDexterityChange();
    }
    for (Belts b : this.belts) {
      aa += b.getDexterityChange();
    }
    if (this.getFootwear() != null) {
      aa += this.footwear.getDexterityChange();
    }
    return aa;
  }

  @Override
  public int potentialStrikingDamage() {
    int[] ab = this.getFinalAbility();
    int damage = ab[0];
    if (this.weapon == null) {
      return damage;
    }
    if (this.getWeapon() != null) {
      int weaponDamage = this.weapon.getLow() 
          +  this.random.nextInt(this.weapon.getHigh() - this.weapon.getLow() + 1);
      
      if ((this.weapon == Weapon.TwoHandedSwords 
          && ab[0] < this.weapon.getRequirement()) 
          || (this.weapon == Weapon.Flails 
          && ab[2] < this.weapon.getRequirement())) {
        damage += weaponDamage / 2;
      } else {
        damage += weaponDamage;
      }
    }
    return damage;
  }

  @Override
  public int remainingBeltUnit() {
    return this.remainingBeltUnit;
  }

  @Override
  public int[] getFinalAbility() {
    int[] res = new int[4];
    res[0] = this.strength;
    res[1] = this.constitution;
    res[2] = this.dexterity;
    res[3] = this.charisma;
    
    // Headgear.
    if (this.getHeadgear() != null) {
      res[2] += this.getHeadgear().getConstitutionChange();
    }
    
    // Potions.
    if (this.getPotions() != null) {
      for (Potions p : this.getPotions()) {
        res[0] += p.getStrengthChange();
        res[1] += p.getConstitutionChange();
        res[2] += p.getDexterityChange();
        res[3] += p.getCharismaChange();
      }
    }
    
    // Belts.
    if (this.getBelts() != null) {
      for (Belts b : this.getBelts()) {
        res[0] += b.getStrengthChange();
        res[1] += b.getConstitutionChange();
        res[2] += b.getDexterityChange();
        res[3] += b.getCharismaChange();
      }
    }
    
    // Footwear.
    if (this.getFootwear() != null) {
      res[3] += this.getFootwear().getDexterityChange();
    }
    
    return res;
  }
  
  @Override
  public void resetHealth() {
    this.remainingHealth = this.totalAbility;
  }

}
