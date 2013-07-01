package tictactoe;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * A class that runs a game of Tic Tac Toe.
 * @author Daniel Kapit
 *
 */
public class Game extends JFrame {
	/**
	 * The constructor for the game, which creates the letter grid
	 * and adds them to the frame.
	 */
	public Game() {
		JMenuBar menu = new JMenuBar();
		menu.add(createFileMenu());
		menu.add(createGameMenu());
		setJMenuBar(menu);
		JButton[][] letters = new JButton[width][height];
		setLayout(new GridLayout(width, height));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				letters[i][j] = new JButton();
				letters[i][j].addMouseListener(p);
				add(letters[i][j]);
			}
		}
		temp = letters;
	}
	
	/**
	 * The listener for the letters. Sets their text.
	 * @author Daniel Kapit
	 *
	 */
	class PopUp extends MouseAdapter {
		/**
		 * Acts on the component when the mouse button is released.
		 */
		public void mouseReleased(MouseEvent e) {
			JButton comp = (JButton) e.getComponent();
			if (turn % 2 == 0) {
				comp.setText("X");
				comp.setEnabled(false);
				comp.removeMouseListener(p);
				String l = checkWin();
				if (l.equalsIgnoreCase("X")) {
					JOptionPane.showMessageDialog(null, "X wins!");
					for (int i = 0; i < width; i++) {
						for (int j = 0; j < height; j++) {
							temp[i][j].setEnabled(true);
							temp[i][j].addMouseListener(p);
							temp[i][j].setText("");
							
						}
					}
					turn = 0;
				}
				else if (l.equalsIgnoreCase("Tie")) {
					JOptionPane.showMessageDialog(null, "Tie!");
					for (int i = 0; i < width; i++) {
						for (int j = 0; j < height; j++) {
							temp[i][j].setEnabled(true);
							temp[i][j].addMouseListener(p);
							temp[i][j].setText("");
							
						}
					}
					turn = 0;
				}
				else
					turn++;
			}
			else if (turn % 2 != 0) {
				comp.setText("O");
				comp.setEnabled(false);
				comp.removeMouseListener(p);
				String l = checkWin();
				if (l.equalsIgnoreCase("O")) {
					JOptionPane.showMessageDialog(null, "O wins!");
					for (int i = 0; i < width; i++) {
						for (int j = 0; j < height; j++) {
							temp[i][j].setEnabled(true);
							temp[i][j].addMouseListener(p);
							temp[i][j].setText("");
							
						}
					}
					turn = 0;
				}
				else if (l.equalsIgnoreCase("Tie")) {
					JOptionPane.showMessageDialog(null, "Tie!");
					for (int i = 0; i < width; i++) {
						for (int j = 0; j < height; j++) {
							temp[i][j].setEnabled(true);
							temp[i][j].addMouseListener(p);
							temp[i][j].setText("");
							
						}
					}
					turn = 0;
				}
				else
					turn++;
			}
		}
	}
	
	/**
	 * Checks to see if there is a win.
	 * @return The letter that wins, "tie" if there is a tie, or "Not done"
	 * if the game is not finished.
	 */
	protected static String checkWin() {
		// sets an array with the letters in the game.
		String[][] s = new String[width][height];
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				s[i][j] = temp[i][j].getText();
			}
		}
		// checks to see if there is a win going across.
		int i = 0;
		for (int r = 0; r < width; r++) {
			if (s[r][i].equalsIgnoreCase(s[r][i+ 1]) && 
					s[r][i + 1].equalsIgnoreCase(s[r][i + 2])) {
				return s[r][i];
			}
		}
		// checks for a win going down.
		for (int r = 0; r < height; r++) {
			if (s[i][r].equalsIgnoreCase(s[i + 1][r]) && 
					s[i + 1][r].equalsIgnoreCase(s[i + 2][r])) {
				return s[i][r];
			}
		}
		// checks for a diagonal win.
		if (s[0][0] == s[1][1] && s[1][1] == s[2][2])
			return s[0][0];
		else if (s[2][0] == s[1][1] && s[1][1] == s[0][2])
			return s[2][0];
		// checks for a tie.
		int count = 0;
		for (int g = 0; g < width; g++) {
			for (int j = 0; j < height; j++) {
				if (temp[g][j].isEnabled()) {
					break;
				}
				else {
					count++;
				}
			}
		}
		if (count == 9)
			return "Tie";
		else
			return "Not done";
	}
	
public JMenu createFileMenu() {
	JMenu menu = new JMenu("File");
	menu.add(createExitItem());
	return menu;
}

public JMenu createGameMenu() {
	JMenu menu = new JMenu("Game");
	menu.add(createResetItem());
	return menu;
}

public JMenuItem createResetItem() {
	JMenuItem item = new JMenuItem("Reset");
	class Reset implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			for (int i = 0; i < width; i++) {
				for (int j = 0; j < height; j++) {
					temp[i][j].setEnabled(true);
					temp[i][j].addMouseListener(p);
					temp[i][j].setText("");
					turn = 0;
				}
			}
		}
	}
	item.addActionListener(new Reset());
	return item;
}

public JMenuItem createExitItem() {
	JMenuItem item = new JMenuItem("Quit");
	class Quit implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			System.exit(0);
		}
	}
	item.addActionListener(new Quit());
	return item;
}

	private static JButton[][] temp;
	int turn = 0;
	private final static int width = 3;
	private final static int height = 3;
	private final MouseListener p = new PopUp();
}
