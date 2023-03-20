@startuml
interface Player {
- STRENGTH: int
- CONSTITUTION: int
- DEXTERITY: int
- CHARISMA: int
- headgear: Gear
- potions: Gear[]
- belts: Gear[]
- footwear: Gear

+ getStrength(): int
+ getConstitution(): int
+ getDexterity(): int
+ getCharisma(): int
+ addHeadgear(headGear: Gear)
+ addPotions(potions: Gear)
+ addBelt(belt: Gear)
+ addFootwear(footwear: Gear)
+ isEnoughBeltUnit(): boolean
+ getHeadgear(): Gear
+ getPotions(): Gear[]
+ getBelts(): Gear[]
+ getFootwear(): Gear
+ remainingHealth(): int
+ strikingPower(): int
+ avoidanceAbility(): int
+ potentialStrikingDamage(): int
}

class BattlePlayer implements Player {
+ BattlePlayer(strength: int, constitution: int, dexterity: int, charisma: int)
}

interface Gear{
- STRENGTHCHANGE: int
- CONSTITUTIONCHANGE: int
- DEXTERITYCHANGE: int
- CHARISMACHANGE: int

+ getStrengthChange(): int
+ getConstitutionChange(): int
+ getDexterityChange(): int
+ getCharismaChange(): int
}

class Headgear implements Gear {
+ Headwear(constitutionChange: int)
}
class Potions implements Gear {
+ Potions(strengthChange: int, constitutionChange: int, dexterityChange: int, charismaChange: int)
}
class Belts implements Gear {
- SIZE: Size

+ Belts(strengthChange: int, constitutionChange: int, dexterityChange: int, charismaChange: int)
+ getSize(): Size
+ getUnit(): int
}
class Footwear implements Gear {
+ Footwear(dexterityChange: int)
}

enum Size {
Small, Medium, Large;
}

interface Weapon {
{static}- attackLow
{static}- attackHigh

+ getAttackLow(): int
+ getAttackHigh(): int
}

class Katanas implements Weapon
class Broadswords implements Weapon
class TwoHandedSwords implements Weapon
class Axes implements Weapon
class Flails implements Weapon

interface Battle {
- player: Player[]

+ addPlayer(player: Player)
+ getPlayer(): Player[]
+ generateGearBag(): Gear[]
+ equipPlayer()
+ generateWeapon(): Weapon
+ playerDescription(player: Player): String
+ battleStart(playerA: Player, playerB: Player)
}

class BattleModel implements Battle {
+ BattleModel()
}

Player "1" o-- "1" Headgear
Player "1" o-- "*" Potions
Player "1" o-- "*" Belts
Player "1" o-- "*" Footwear
Player "1" o-- "*" Weapon
Weapon "1" *-- "1" Size
BattleModel "1" o-- "2" Player
BattleModel "1" o-- "*" Weapon
BattleModel "1" o-- "*" Gear

@enduml