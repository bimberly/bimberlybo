//imports
import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

class BlackJackMainPanel extends JPanel implements ActionListener {

	boolean menuVisibility = true; 

	public BlackJackMainPanel() {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});
	}
	/**
	 * Creates Frame with Buttons
	 * pre:
	 * post:
	 */
	private void createAndShowGUI() {
		setLayout(new FlowLayout());
		JFrame frame = new JFrame("Blackjack");
		JPanel contentPane = new JPanel(new BorderLayout());
		JLabel welcome = new JLabel("Welcome to Blackjack!");
		contentPane.add(welcome, BorderLayout.NORTH);
		contentPane.setVisible(true);
		//creating buttons
		
		JButton b1 = new JButton("Leaderboard");
		b1.setActionCommand("Leaderboard");
		b1.addActionListener(this);
		b1.setVisible(true);
		contentPane.add(b1, BorderLayout.WEST);
		
		JButton b2 = new JButton("Play");
		b2.setActionCommand("Play");
		b2.addActionListener(this);
		b2.setVisible(true);
		contentPane.add(b2, BorderLayout.CENTER);

		JButton b3 = new JButton("How to Play");
		b3.setActionCommand("How to Play");
		b3.addActionListener(this);
		b3.setVisible(true);
		contentPane.add(b3, BorderLayout.EAST);
		
		frame.setSize(300, 100);
		frame.setVisible(true);
		frame.toFront();
		frame.add(contentPane);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public void actionPerformed(ActionEvent ae) {
		if(ae.getActionCommand().equals("Play")) {
			this.setVisible(false);
			menuVisibility = false;
		} else if (ae.getActionCommand().equals("Instructions")) {
			JOptionPane.showMessageDialog(null,"How to play:\r\n" + 
					"The objective of the game is to have a set of cards \r\n"
					+ "whose total value beats the opponent’s set of cards’ \r\n"
					+ "total value without going over the value twenty-one. \r\n"
					+ "At the beginning, you will decide how much you want \r\n"
					+ "to bet. Then, each player will be dealt two cards, \r\n"
					+ "one facing up, one facing down. Cards two to ten are \r\n"
					+ "their actual values, all face cards have a value of ten, \r\n"
					+ "and ace has a value of either one or eleven, depending \r\n"
					+ "on what you want. From there, you have an option to “Hit” \r\n"
					+ "or “Stand.” Hit meaning you would like another card, but \r\n"
					+ "you do risk being “Bust,” which is when you have gone over \r\n"
					+ "twenty-one. Stand means you do not want another card. If \r\n"
					+ "you win the round, then you gain the bet money and if you \r\n"
					+ "lose, you don’t get anything.\r\n" + 
					"Note: Suits do not matter in Blackjack.\r\n");  
		} else if (ae.getActionCommand().equals("Leaderboard")) {
			String S = openTextFile();
			JOptionPane.showMessageDialog(null, S);
		}
		
	}
	private String openTextFile() {
		String S = "These are the top scores:\n";
		try {
			Scanner sc = new Scanner(new File("src/highscores.txt"));
			while(sc.hasNextLine()) {
				String cur = sc.next();
				int score = sc.nextInt();
				S += cur + " " + score + "\n";
			}
		} catch(Exception e) { e.printStackTrace();}
		return S;
	}
	
	public static void main(String[] args) {
		BlackJackMainPanel menuPanel = new BlackJackMainPanel();
	}
}
