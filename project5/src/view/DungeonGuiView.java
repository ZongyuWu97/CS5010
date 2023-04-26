package view;

import controller.DungeonController;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import model.DungeonModel;

/**
 * Implements the DungeonView and ActionListener interface. 
 * Also extends JFrame.

 * @author ZongyuWu
 *
 */
public class DungeonGuiView extends JFrame implements DungeonView, ActionListener {

  private static final long serialVersionUID = 1L;

  private DungeonController controller;
  
  private DungeonModel model; 
  
  private JMenuBar menuBar;
  
  private JMenu fileMenu;
  
  private JMenuItem close;
  
  private JPanel buttonPanel;
  
  private DungeonPanel gamePanel;
  
  private JButton exit;
  
  private JTextField direction;
  
  private JButton move;
  
  private JTextField shootDirection;
  
  private JButton shoot;
  
  private JButton pick;
  
  private JTextField dungeonConfig;
  
  private JButton generate;
  
  private JButton north;
  
  private JButton east;
  
  private JButton south;
  
  private JButton west;

  private JTextArea textArea;

  /**
   * Constructor of a view object.

   * @param model model
   */
  public DungeonGuiView(DungeonModel model) {
    super("Dungeon");
    this.setLayout(null);
    this.model = model;
    this.setSize(500, 800);
    this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    
    this.menuBar = new JMenuBar();
    this.setJMenuBar(this.menuBar);
    
    this.fileMenu = new JMenu("File");
    this.menuBar.add(this.fileMenu);
    this.menuBar.setName("MenuBar");
    
    this.close = new JMenuItem("Close");
    this.fileMenu.add(this.close);
    this.close.addActionListener(this);
    this.close.setName("Close");
    
    this.buttonPanel = new JPanel();
    this.buttonPanel.setSize(500, 100);
    this.add(this.buttonPanel);
    
    this.exit = new JButton("Exit");
    this.buttonPanel.add(this.exit);
    this.exit.addActionListener(this);
    this.exit.setName("Close");
    
    this.dungeonConfig = new JTextField(10);
    this.buttonPanel.add(this.dungeonConfig);
    
    this.generate = new JButton("Generate");
    this.generate.setName("Generate");
    this.generate.addActionListener(this);
    this.buttonPanel.add(this.generate);
    
    this.direction = new JTextField(5);
    this.buttonPanel.add(this.direction);
    
    this.move = new JButton("Move");
    this.move.setName("Move");
    this.move.addActionListener(this);
    this.buttonPanel.add(this.move);
    
    this.shootDirection = new JTextField(5);
    this.buttonPanel.add(this.shootDirection);
    
    this.shoot = new JButton("Shoot");
    this.shoot.setName("Shoot");
    this.shoot.addActionListener(this);
    this.buttonPanel.add(this.shoot);
    
    this.pick = new JButton("Pick");
    this.pick.setName("Pick");
    this.pick.addActionListener(this);
    this.buttonPanel.add(this.pick);
    
    this.north = new JButton("north");
    this.north.setName("north");
    this.north.addActionListener(this);
    this.buttonPanel.add(this.north);
    
    this.east = new JButton("east");
    this.east.setName("east");
    this.east.addActionListener(this);
    this.buttonPanel.add(this.east);
    
    this.south = new JButton("south");
    this.south.setName("south");
    this.south.addActionListener(this);
    this.buttonPanel.add(this.south);
    
    this.west = new JButton("west");
    this.west.setName("west");
    this.west.addActionListener(this);
    this.buttonPanel.add(this.west);
    
    this.textArea = new JTextArea("Information");
    this.textArea.setName("Information");
    this.textArea.setBounds(0, 100, 500, 150);
    this.add(this.textArea);
    
    this.gamePanel = new DungeonPanel(1, 1);
    this.gamePanel.setBounds(0, 250, 500, 500);
    this.add(this.gamePanel);
    
    this.setVisible(true);
  }
  
  @Override
  public void refresh() {
    if (model.getBoard() == null) {
      return;
    }
    this.gamePanel.setBoard(model.getBoard());
    this.repaint(); 
  }

  @Override
  public void addClickListener(DungeonController listener) {
    this.controller = listener;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() instanceof Component) {
      Component c = (Component) e.getSource();
      
      if (c.getName().equals("Close")) {
        System.exit(0);
      }
      
      if (c.getName().equals("Move")) {
        String direstion = this.direction.getText();
        
        this.controller.move(direstion);
        this.direction.setText("");
      }
      
      if (c.getName().equals("Generate")) {
        String[] coor = this.dungeonConfig.getText().split(" ");
        
        String wrap = coor[0];
        int row = Integer.parseInt(coor[1]);
        int col = Integer.parseInt(coor[2]);
        int interconnectivity = Integer.parseInt(coor[3]);
        int percent = Integer.parseInt(coor[4]);
        int numMonster = Integer.parseInt(coor[5]);

        this.setPanel(row, col);
        this.controller.startGame(wrap, row, col, interconnectivity, percent, numMonster);
        this.dungeonConfig.setText("");
      }
      
      if (c.getName().equals("Shoot")) {
        String[] coor = this.shootDirection.getText().split(" ");
        
        String direction = coor[0];
        int distance = Integer.parseInt(coor[1]);
        
        this.controller.shoot(direction, distance);
      }
      
      if (c.getName().equals("Pick")) {
        this.controller.pick();
      }
      
      if (c.getName().equals("north")) {
        this.controller.move("north");
      }
      
      if (c.getName().equals("east")) {
        this.controller.move("east");
      }
      
      if (c.getName().equals("south")) {
        this.controller.move("south");
      }
      
      if (c.getName().equals("west")) {
        this.controller.move("west");
      }
      
    }    
  }
  
  /**
   * Set the dungeon panel.

   * @param row number of rows
   * @param col number of cols
   */
  public void setPanel(int row, int col) {
    this.gamePanel = new DungeonPanel(row, col);
    this.gamePanel.setBounds(0, 250, 500, 500);
    this.add(this.gamePanel);
    
    this.setVisible(true);

  }

  @Override
  public void show(String information) {
    textArea.setText(information);
  }


}
