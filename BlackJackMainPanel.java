//imports
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.imageio.ImageIO;

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

		try {
			ImageIcon cards =  new ImageIcon(ImageIO.read(getClass().getResource("Pictures/blackjackcards.gif")));
			JLabel welcome = new JLabel("Welcome to Blackjack!", JLabel.CENTER);
			welcome.setVerticalTextPosition(JLabel.RIGHT);
			welcome.setHorizontalTextPosition(JLabel.CENTER);
			welcome.setIcon(cards);
			welcome.setVisible(true);
			this.add(welcome);
		} catch (Exception e) {System.out.println(e);};


		//creating buttons

		JButton b1 = new JButton("Leaderboard");
		b1.setActionCommand("Leaderboard");
		b1.addActionListener(this);
		this.add(b1, BorderLayout.WEST);

		JButton b2 = new JButton("Play");
		b2.setActionCommand("Play");
		b2.addActionListener(this);
		this.add(b2, BorderLayout.CENTER);

		JButton b3 = new JButton("How to Play");
		b3.setActionCommand("How to Play");
		b3.addActionListener(this);
		this.add(b3, BorderLayout.EAST);

		frame.setSize(250, 150);
		frame.setVisible(true);
		frame.toFront();
		frame.add(this);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public void actionPerformed(ActionEvent ae) {
		if(ae.getActionCommand().equals("Play")) {
			this.setVisible(false);
			menuVisibility = false;
		} else if (ae.getActionCommand().equals("How to Play")) {
			JOptionPane.showMessageDialog(null,"How to play:\r\n" + 
					"The objective of the game is to have a set of cards whose total \r\n"
					+ "value beats the opponent’s set of cards’ total value without \r\n"
					+ "going over the value twenty-one. Each player bets two dollars \r\n"
					+ "the beginning of each round. Then, each player will be dealt \r\n"
					+ "two cards. Cards two to ten are their actual values, all face \r\n"
					+ "cards have a value of ten, and ace has a value of either one \r\n"
					+ "or eleven, depending on what you want. When you receive and \r\n"
					+ "ace, you can press the purple card for the value of eleven \r\n"
					+ "and the blue card for a value of one. From there, you have an \r\n"
					+ "option to “Hit” or “Stand.” Hit being the grey card and it \r\n"
					+ "means you would like another card, but you do risk going over \r\n"
					+ "twenty-one. Stand being the yellow card and it means you do \r\n"
					+ "not want another card. If you win the round, then you gain the \r\n"
					+ "bet money and if you lose, you don’t get anything. A menu will \r\n"
					+ "pop up asking you whether or not you want to play another round, \r\n"
					+ "if you do, simply type in, “yes,” if not, then type, “no,” and \r\n"
					+ "enter your name to save it to hopefully make the leaderboards.\r\n" + 
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
