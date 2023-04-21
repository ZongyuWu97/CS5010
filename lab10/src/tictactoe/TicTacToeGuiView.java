package tictactoe;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * The GUI View of Tic Tac Toe game.

 * @author ZongyuWu
 *
 */
public class TicTacToeGuiView extends JFrame implements TicTacToeView, ActionListener {
  
  private static final long serialVersionUID = 1L;

  private TicTacToeController controller;
  
  private ReadonlyTttModel model; 
  
  private JMenuBar menuBar;
  
  private JMenu fileMenu;
  
  private JMenuItem close;
  
  private JPanel buttonPanel;
  
  private TicTacToePanel gamePanel;
  
  private JButton exit;
  
  private JTextField input;
  
  private JButton move;
  
  /**
   * Constructor of the GUI View with a model.

   * @param model model
   */
  public TicTacToeGuiView(ReadonlyTttModel model) {
    super("Tic Tac Toe");
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
    
    this.input = new JTextField(5);
    this.buttonPanel.add(this.input);
    
    this.move = new JButton("Move");
    this.move.setName("Move");
    this.move.addActionListener(this);
    this.buttonPanel.add(this.move);
    
    this.gamePanel = new TicTacToePanel();
    this.gamePanel.setBounds(0, 50, 500, 500);
    this.add(this.gamePanel);
    
    this.setVisible(true);
  }

  @Override
  public void addClickListener(TicTacToeController listener) {
    this.controller = listener;
    this.gamePanel.addMouseListener(new TicTacToeMouse(listener));
  }

  @Override
  public void refresh() {
    this.gamePanel.setBoard(model.getBoard());
    this.repaint(); 
  }

  @Override
  public void makeVisible() {
    this.setVisible(true);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() instanceof Component) {
      Component c = (Component) e.getSource();
      if (c.getName().equals("Close")) {
        System.exit(0);;
      }
      if (c.getName().equals("Move")) {
        String[] coord = this.input.getText().split(" ");
        int row = Integer.parseInt(coord[0]);
        int col = Integer.parseInt(coord[1]);
        
        this.controller.handleCellClick(row, col);
        this.input.setText("");
      }
      
    }
  }

}
