package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Stack;
import util.Cave;
import util.Location;
import util.Monster;
import util.MyRandom;
import util.Player;
import util.PlayerImpl;
import util.Treasure;
import util.Tunnel;

/**
 * This class implements the DungeonModel interface.

 * @author ZongyuWu
 *
 */
public class DungeonModelImpl implements DungeonModel {
  
  private Player player;
  private HashMap<Integer, Location> locations;
  private HashMap<Integer, Location> caves;
  private HashMap<Integer, HashSet<Integer>> edges;
  private Location start;
  private Location end;
  private int row;
  private int col;
  private int interconnectivity;
  
  @Override
  public String create(String wrap, int row, int col, int interconnectivity)
      throws IllegalArgumentException {
    if (!(("wrapping".equals(wrap) | ("non-wrapping".equals(wrap))))) {
      throw new IllegalArgumentException("wrap must be wrapping or non-wrapping");
    }
    if ((row <= 0) | (col <= 0)) {
      throw new IllegalArgumentException("row and col must be positive.");
    }
    if (interconnectivity < 0) {
      throw new IllegalArgumentException("interconnectivity must be non-negative");
    }
    
    this.row = row;
    this.col = col;
    this.interconnectivity = interconnectivity;
    
    // Create union find help set.
    HashMap<Integer, Integer> unionFind = new HashMap<>();
    for (int i = 0; i < row * col; i++) {
      unionFind.put(i, i);
    }
    
    // Create space for not used edges.
    HashMap<Integer, HashSet<Integer>> restEdges = new HashMap<>();
    for (int i = 0; i < row * col; i++) {
      restEdges.put(i, new HashSet<>());
    }
    
    // Create space for used edges.
    this.edges = new HashMap<>();
    for (int i = 0; i < row * col; i++) {
      this.edges.put(i, new HashSet<>());
    }
    
    // Create adjacent list.
    HashMap<Integer, HashSet<Integer>> potentialEdges = generatePotentialEdges(wrap, row, col);
    
    // Kruskal's algorithm.
    kruskal(potentialEdges, restEdges, unionFind, interconnectivity);

    // Generate locations for each node.
    generateLocations();
    
    // Generate degree matrix for each node.
    StringBuffer maze = new StringBuffer();
    String tmp;
    for (int i = 0; i < row; i++) {
      tmp = "";
      for (int j = 0; j < col; j++) {
        tmp += String.format(" %d ", this.edges.get(i * col + j).size());
      }
      maze.append(tmp + "\n");
    }
    return maze.toString();
  }
  
  @Override
  public void setStartEnd() {
    //Take a random cave as start;
    List<Integer> keys = new ArrayList<>(this.caves.keySet());
    MyRandom r = new MyRandom(true);
    this.start = this.caves.get(keys.get(r.nextInt(keys.size())));

    //BFS to get locations too close to the start.
    HashSet<Integer> tooClose = new HashSet<>();
    getTooClose(tooClose, edges);

    // Get random end from the rest caves.
    List<Integer> rest = new ArrayList<>();
    for (Location cave : this.caves.values()) {
      if (tooClose.contains(cave.getIndex())) {
        continue;
      }
      rest.add(cave.getIndex());
    }
    if (rest.isEmpty()) {
      throw new IllegalArgumentException("All other caves are too close to the start.");
    }
    this.end = this.caves.get(rest.get(r.nextInt(rest.size())));
  }
  
  @Override
  public void setStartEnd(int start, int end) {
    //Take a random cave as start;
    MyRandom r = new MyRandom(false);
    this.start = this.caves.get(r.nextInt(start));

    this.end = this.caves.get(r.nextInt(end));
  }

  
  @Override
  public void addTreasure(int percentage) throws IllegalArgumentException {
    if ((percentage < 0) | (percentage > 100)) {
      throw new IllegalArgumentException("Invalid percent.");
    }
    int count = 0;
    int total = this.caves.size() * percentage / 100;
    for (Location cave : this.caves.values()) {
      if (count + 1 > total) {
        break;
      }
      cave.addTreasure();
      count++;
    }
    
    count = 0;
    total = this.locations.size() * percentage / 100;
    for (Location l : this.locations.values()) {
      if (count + 1 > total) {
        break;
      }
      l.setArrow();
      count++;
    }
  }
  
  @Override
  public void addTreasure(int percentage, boolean noRandom) throws IllegalArgumentException {
    if ((percentage < 0) | (percentage > 100)) {
      throw new IllegalArgumentException("Invalid percent.");
    }
    if (noRandom) {
      int total = this.caves.size() * percentage / 100;
      List<Integer> toBeAdded = new ArrayList<>(this.caves.keySet());
      Collections.sort(toBeAdded);
      
      Location cave;
      int count = 0;
      for (int idx : toBeAdded) {
        cave = this.caves.get(idx);
        if (count + 1 > total) {
          break;
        }
        cave.addTreasure();
        count++;
      }
    }
  }

  @Override
  public void enter() {
    this.player = new PlayerImpl();
    this.move(this.start);
  }

  @Override
  public String playerDescription() throws IllegalArgumentException {
    if (this.player == null) {
      throw new IllegalArgumentException("No player in the dungeon.");
    }
    StringBuffer res = new StringBuffer();
    res.append("The player has collected:\n");
    HashMap<Treasure, Integer> playerTreasures = this.player.getTreasures();
    for (Treasure t : Treasure.values()) {
      res.append(playerTreasures.get(t));
      res.append(" " + t + " ");
    }
    res.append(String.format("and %d arrows.\n", this.player.getArrow()));
    
    return res.toString();
  }

  @Override
  public String playerLocation() throws IllegalArgumentException {
    if (this.player == null) {
      throw new IllegalArgumentException("No player in the dungeon.");
    }
    StringBuffer res = new StringBuffer();
    Location curr = this.player.getLocation();
    res.append(String.format("This location is row %d, column %d.\n", 
        curr.getIndex() / this.col, curr.getIndex() % this.col));
    
    res.append("The current location has\n");
    HashMap<Treasure, Integer> locationTreasures = curr.getTreasures();
    for (Treasure t : Treasure.values()) {
      res.append(locationTreasures.get(t));
      res.append(" " + t + " ");
    }
    res.append("\n");
    
    if (curr.getArrow()) {
      res.append("This location has an arrow.\n");
    } else {
      res.append("This location has no arrow.\n");
    }
    
    res.append("This location is connected to the");
    if (curr.getNorth()) {
      res.append(" north");
    }
    if (curr.getWest()) {
      res.append(" west");
    }
    if (curr.getSouth()) {
      res.append(" south");
    }
    if (curr.getEast()) {
      res.append(" east");
    }
    res.append(".\n");
    
    int smellLevel = this.detectMonster();
    if (smellLevel == 2) {
      res.append("Strong");
    } else if (smellLevel == 1) {
      res.append("Less");
    } else if (smellLevel == 0) {
      res.append("No");
    }
    res.append(" smell is detected.\n");
    
    return res.toString();
  }

  @Override
  public void move(Location location) throws IllegalArgumentException {
    if (this.player == null) {
      throw new IllegalArgumentException("No player in this dungeon.");
    }
    if (this.player.getLocation() != null) {
      this.player.getLocation().setPlayerHere(false);
    }
    this.player.move(location);
    location.setVisited();
    location.setPlayerHere(true);
  }

  @Override
  public void pick() throws IllegalArgumentException {
    if (this.player == null) {
      throw new IllegalArgumentException("No player in this dungeon.");
    }
    
    this.player.pick();
  }
  
  private HashMap<Integer, HashSet<Integer>> generatePotentialEdges(String wrap, int row, int col) {
    int curr;
    // Create adjacent list.
    HashMap<Integer, HashSet<Integer>> potentialEdges = new HashMap<>();
    for (int i = 0; i < row * col; i++) {
      potentialEdges.put(i, new HashSet<>());
    }
    
    // Add edges to each node except last row.
    for (int i = 0; i < row - 1; i++) {
      
      // Nodes on left and right
      potentialEdges.get(i * col).add(i * col + 1);
      potentialEdges.get(i * col + 1).add(i * col);
      potentialEdges.get(i * col).add(i * col + col);
      potentialEdges.get(i * col + col).add(i * col);
      
      potentialEdges.get(i * col + col - 2).add(i * col + col - 1);
      potentialEdges.get(i * col + col - 1).add(i * col + col - 2);
      potentialEdges.get(i * col + col - 1).add(i * col + col - 1 + col);
      potentialEdges.get(i * col + col - 1 + col).add(i * col + col - 1);

      // Nodes in the middle.
      for (int j = 1; j < col - 1; j++) {
        curr = col * i + j;
        potentialEdges.get(curr).add(curr + 1);
        potentialEdges.get(curr).add(curr - 1);
        potentialEdges.get(curr + 1).add(curr);
        potentialEdges.get(curr - 1).add(curr);
        potentialEdges.get(curr).add(curr + col);
        potentialEdges.get(curr + col).add(curr);
      }
    }
    
    // Last row.
    potentialEdges.get(col * (row - 1)).add(col * (row - 1) + 1);
    potentialEdges.get(col * (row - 1) + 1).add(col * (row - 1));
    potentialEdges.get(col * (row - 1) + col - 1).add(col * (row - 1) + col - 2);
    potentialEdges.get(col * (row - 1) + col - 2).add(col * (row - 1) + col - 1);
    for (int i = 1; i < col - 1; i++) {
      curr = col * (row - 1) + i;
      potentialEdges.get(curr).add(curr + 1);
      potentialEdges.get(curr).add(curr - 1);
      potentialEdges.get(curr + 1).add(curr);
      potentialEdges.get(curr - 1).add(curr);
    }
    
    // Wrapping.
    if ("wrapping".equals(wrap)) {
      for (int i = 0; i < row; i++) {
        potentialEdges.get(i * col).add(i * col + col - 1);
        potentialEdges.get(i * col + col - 1).add(i * col);
      }
      for (int j = 0; j < col; j++) {
        potentialEdges.get(j).add(j + (row - 1) * col);
        potentialEdges.get(j + (row - 1) * col).add(j);
      }
    }
    
    return potentialEdges;
  }
  
  private int find(HashMap<Integer, Integer> unionFind, int x) {
    int rootx = unionFind.get(x);
    if (x != rootx) {
      unionFind.put(x, find(unionFind, rootx));
    }
    return unionFind.get(x);
  }

  private void union(HashMap<Integer, Integer> unionFind, int x, int y) {
    int rootx = find(unionFind, x);
    int rooty = find(unionFind, y);
    
    if (rootx != rooty) {
      unionFind.put(rootx, rooty);
    }
  }
  
  private void generateLocations() {
    this.locations = new HashMap<>();
    this.caves = new HashMap<>();
    
    for (int x : edges.keySet()) {
      if (this.edges.get(x).size() == 2) {
        this.locations.put(x, new Tunnel(this.row, this.col, x, this.edges.get(x)));
      } else {
        Location tmp = new Cave(this.row, this.col, x, this.edges.get(x));
        this.locations.put(x, tmp);
        this.caves.put(x, tmp);
      }
    }
  }
  
  private void kruskal(
      HashMap<Integer, HashSet<Integer>> potentialEdges, 
      HashMap<Integer, HashSet<Integer>> restEdges, 
      HashMap<Integer, Integer> unionFind, 
      int interconnectivity) {
    List<Integer> tmp1;
    for (int x : potentialEdges.keySet()) {
      tmp1 = new ArrayList<>(potentialEdges.get(x));
      for (int y : tmp1) {
        if (find(unionFind, x) == find(unionFind, y)) {
          restEdges.get(x).add(y);
          restEdges.get(y).add(x);
        } else {
          union(unionFind, x, y);
          this.edges.get(x).add(y);
          this.edges.get(y).add(x);
        }
        potentialEdges.get(x).remove(Integer.valueOf(y));
        potentialEdges.get(y).remove(Integer.valueOf(x));
      }

    }
    
    HashSet<Integer> tmp2;
    for (int x : restEdges.keySet()) {
      if (interconnectivity == 0) {
        break;
      }
      tmp2 = new HashSet<Integer>(restEdges.get(x));
      for (int y : tmp2) {
        if (interconnectivity == 0) {
          break;
        }
        
        this.edges.get(x).add(y);
        this.edges.get(y).add(x);
        
        restEdges.get(x).remove(Integer.valueOf(y));
        restEdges.get(y).remove(Integer.valueOf(x));
        
        interconnectivity -= 1;
      }
    }
  }
  
  private void getTooClose(
      HashSet<Integer> tooClose, 
      HashMap<Integer, HashSet<Integer>> edges) {
    Stack<Location> curr = new Stack<>();
    Stack<Location> next = new Stack<>();
    Stack<Location> tmp;
    curr.add(this.start);
    tooClose.add(this.start.getIndex());
    
    int iter = 0;
    Location node;
    while (iter < 5) {
      while (!curr.isEmpty()) {
        node = curr.pop();
        for (int y : edges.get(node.getIndex())) {
          if (tooClose.contains(y)) {
            continue;
          }
          next.add(locations.get(y));
          tooClose.add(y);
        }
      }
      tmp = curr;
      curr = next;
      next = tmp;
      iter++;
    }
  }

  @Override
  public int getRow() {
    return this.row;
  }

  @Override
  public int getColumn() {
    return this.col;
  }

  @Override
  public int getInterconnectivity() {
    return this.interconnectivity;
  }

  @Override
  public Location getStart() {
    return this.start;
  }

  @Override
  public Location getEnd() {
    return this.end;
  }

  @Override
  public Location getNextLocation(Location currLocation, String direction) {
    return this.locations.get(currLocation.getNextLocation(direction));
  }

  @Override
  public Location getLocation() {
    return this.player.getLocation();
  }

  @Override
  public Player getPlayer() {
    return this.player;
  }

  @Override
  public void addMonster(int num) {
    if (num < 0) {
      throw new IllegalArgumentException("Invalid input.");
    }
    
    this.end.addMonster();
    num--;
    for (Location cave : this.caves.values()) {
      if (cave.equals(start) | cave.equals(end)) {
        continue;
      }
      if (num == 0) {
        break;
      }
      cave.addMonster();
      num--;
    }
  }

  @Override
  public int detectMonster() {
    Location l = this.player.getLocation();
    HashMap<Integer, HashSet<Location>> monsterDistance = new HashMap<>();
    monsterDistance.put(1, new HashSet<>());
    monsterDistance.put(2, new HashSet<>());
    
    for (Integer tmp : this.edges.get(l.getIndex())) {
      monsterDistance.get(1).add(this.locations.get(tmp));
    }
    for (Location distanceOne : monsterDistance.get(1)) {
      for (Integer tmp : this.edges.get(distanceOne.getIndex())) {
        if (tmp == l.getIndex() || monsterDistance.get(1).contains(this.locations.get(tmp))) {
          continue;
        }
        monsterDistance.get(2).add(this.locations.get(tmp));
      }
    }    
    
    int monsterAtOne = 0;
    if (this.getLocation().hasMonster()) {
      monsterAtOne++;
    }
    for (Location cave : monsterDistance.get(1)) {
      if (cave.hasMonster()) {
        monsterAtOne++;
      }
    }
      
    int monsterAtTwo = 0;
    for (Location cave : monsterDistance.get(2)) {
      if (cave.hasMonster()) {
        monsterAtTwo++;
      }
    }
    
    if (monsterAtOne > 0 || monsterAtOne + monsterAtTwo >= 2) {
      return 2;
    }
    if (monsterAtTwo == 1) {
      return 1;
    }
    return 0;
  }

  @Override
  public int shoot(String direction, int distance) {
    if (distance < 0) {
      throw new IllegalArgumentException("Distance should be larger than 0.");
    }
    if (this.player.getArrow() <= 0) {
      throw new IllegalArgumentException("Not enough arrow.");
    }
    String[] directions = {"north", "east", "south", "west"};
    if (!new HashSet<>(Arrays.asList(directions)).contains(direction)) {
      throw new IllegalArgumentException("Invalid direction.");
    }
    Location currentLocation = this.player.getLocation();
    while (distance > 0) {
      // If it's a tunnel
      if (!this.caves.containsKey(currentLocation.getIndex())) {
        //        System.out.println("tunnel");
        // For each direction...
        if ("north".equals(direction)) {
          if (currentLocation.getNorth()) {
            direction = "north";
            currentLocation = this.locations.get(currentLocation.getNextLocation("north"));
          } else if (currentLocation.getWest()) {
            direction = "west";
            currentLocation = this.locations.get(currentLocation.getNextLocation("west"));
          } else if (currentLocation.getEast()) {
            direction = "east";
            currentLocation = this.locations.get(currentLocation.getNextLocation("east"));
          }
        }
        
        if ("east".equals(direction)) {
          if (currentLocation.getEast()) {
            direction = "east";
            currentLocation = this.locations.get(currentLocation.getNextLocation("east"));
          } else if (currentLocation.getNorth()) {
            direction = "north";
            currentLocation = this.locations.get(currentLocation.getNextLocation("north"));
          } else if (currentLocation.getSouth()) {
            direction = "south";
            currentLocation = this.locations.get(currentLocation.getNextLocation("south"));
          }
        }
          
        if ("south".equals(direction)) {
          if (currentLocation.getSouth()) {
            direction = "south";
            currentLocation = this.locations.get(currentLocation.getNextLocation("south"));
          } else if (currentLocation.getWest()) {
            direction = "west";
            currentLocation = this.locations.get(currentLocation.getNextLocation("west"));
          } else if (currentLocation.getEast()) {
            direction = "east";
            currentLocation = this.locations.get(currentLocation.getNextLocation("east"));
          }
        }
          
        if ("west".equals(direction)) {
          if (currentLocation.getWest()) {
            direction = "west";
            currentLocation = this.locations.get(currentLocation.getNextLocation("west"));
          } else if (currentLocation.getNorth()) {
            direction = "north";
            currentLocation = this.locations.get(currentLocation.getNextLocation("north"));
          } else if (currentLocation.getSouth()) {
            direction = "south";
            currentLocation = this.locations.get(currentLocation.getNextLocation("south"));
          }
        }
          
      } else {
        //        System.out.println("cave");
        // If it's a cave.
        if ("north".equals(direction)) {
          if (currentLocation.getNorth()) {
            currentLocation = this.locations.get(currentLocation.getNextLocation("north"));
          } else {
            break;
          }
        }  
        
        if ("east".equals(direction)) {
          if (currentLocation.getEast()) {
            currentLocation = this.locations.get(currentLocation.getNextLocation("east"));
          } else {
            break;
          }
        }  
        
        if ("south".equals(direction)) {
          if (currentLocation.getSouth()) {
            currentLocation = this.locations.get(currentLocation.getNextLocation("south"));
          } else {
            break;
          }
        }  
        
        if ("west".equals(direction)) {
          if (currentLocation.getWest()) {
            currentLocation = this.locations.get(currentLocation.getNextLocation("west"));
          } else {
            break;
          }
        }  
        
      }
      distance--;
    }
    
    // If ended without break then reduce health by one.
    this.player.setArrow(this.player.getArrow() - 1);
    //    System.out.println(distance);
    boolean hit = false;
    if (distance == 0) {
      for (Monster m : currentLocation.getMonsters()) {
        if (m.getHealth() > 0) {
          hit = true;
          m.setHealth(m.getHealth() - 1);
          break;
        }
      }
    }
    
    if (hit) {
      return 1;
    } else {
      return -1;
    }
  }

  @Override
  public HashMap<Integer, Location> getLocations() {    
    return this.locations;
  }

  @Override
  public HashMap<Integer, Location> getCaves() {
    return this.caves;
  }

  @Override
  public Location[][] getBoard() {
    if (this.locations == null) {
      return null;
    }
    Location[][] res = new Location[row][col];
    for (int i = 0; i < locations.size(); i++) {
      res[i / col][i % col] = locations.get(i);
    }
    
    return res;
  }

  @Override
  public void setBoard(Location[][] board) {    
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board[0].length; j++) {
        this.locations.put(i * col + j, board[i][j]);
      }
    }
    for (int i : this.caves.keySet()) {
      this.caves.put(i, this.locations.get(i));
    }
  }
}
