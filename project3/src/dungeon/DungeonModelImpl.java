package dungeon;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Stack;

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
    this.player.move(this.start);
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
      res.append(" " + t + "\n");
    }
    
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
      res.append(" " + t + "\n");
    }
    if (curr.getNorth()) {
      res.append("This location is connected to the north.\n");
    }
    if (curr.getWest()) {
      res.append("This location is connected to the west.\n");
    }
    if (curr.getSouth()) {
      res.append("This location is connected to the south.\n");
    }
    if (curr.getEast()) {
      res.append("This location is connected to the east.\n");
    }
    
    return res.toString();
  }

  @Override
  public void move(Location location) throws IllegalArgumentException {
    if (this.player == null) {
      throw new IllegalArgumentException("No player in this dungeon.");
    }
    boolean find = false;
    int curr = this.player.getLocation().getIndex();
    for (int next : this.edges.get(curr)) {
      if (next == location.getIndex()) {
        this.player.move(location);
        find = true;
        break;
      }
    } 
    
    if (!find) {
      throw new IllegalArgumentException("Player cannot move to this location.");
    }

  }

  @Override
  public void pick() throws IllegalArgumentException {
    if (this.player == null) {
      throw new IllegalArgumentException("No player in this dungeon.");
    }
    
    Location curr = this.player.getLocation();
    HashMap<Treasure, Integer> locationTreasures = curr.getTreasures();
    for (Treasure t : locationTreasures.keySet()) {
      this.player.addTreasure(t, locationTreasures.get(t));
    }

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
}
