package view;

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

import controller.DungeonController;
import model.DungeonModel;

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
  
  private JTextField dungeonConfig;
  
  private JButton generate;

  private JTextArea testArea;


  public DungeonGuiView(DungeonModel model) {
    super("Dungeon");
    this.model = model;
    this.setSize(500, 580);
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
    this.buttonPanel.setSize(500, 30);
    this.add(this.buttonPanel);
    
    this.exit = new JButton("Exit");
    this.buttonPanel.add(this.exit);
    this.exit.addActionListener(this);
    this.exit.setName("Close");
    
    this.direction = new JTextField(5);
    this.buttonPanel.add(this.direction);
    
    this.move = new JButton("Move");
    this.move.setName("Move");
    this.move.addActionListener(this);
    this.buttonPanel.add(this.move);
    
    this.dungeonConfig = new JTextField(10);
    this.buttonPanel.add(this.dungeonConfig);
    
    this.generate = new JButton("Generate");
    this.generate.setName("Generate");
    this.generate.addActionListener(this);
    this.buttonPanel.add(this.generate);
    
    this.setVisible(true);
  }
  
  @Override
  public void refresh() {
    this.gamePanel.setBoard(model.getBoard());
    this.repaint(); 
  }

  @Override
  public void addClickListener(DungeonController listener) {
    this.controller = listener;
    this.gamePanel.addMouseListener(new DungeonMouse(listener)); 
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
        
        this.controller.handleCellClick(direstion);
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
        
        System.out.println(coor);
        this.controller.startGame(wrap, row, col, interconnectivity, percent, numMonster);
        this.dungeonConfig.setText("");
      }
      
      
    }    
  }
  
  public void setPanel(int row, int col) {
    this.gamePanel = new DungeonPanel(row, col);
    this.gamePanel.setBounds(0, 50, 500, 500);
    this.add(this.gamePanel);
    
    this.setVisible(true);

  }

  @Override
  public void show(String playerLocation) {
    testArea.setText(playerLocation);
  }


}
