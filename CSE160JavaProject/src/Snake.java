import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.CardLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.Color;

public class Snake {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Snake window = new Snake();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Snake() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 900, 900);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new CardLayout(0, 0));
		
		JPanel panelMenu = new JPanel();
		panelMenu.setBackground(Color.BLACK);
		frame.getContentPane().add(panelMenu, "name_77041085302519");
		panelMenu.setLayout(null);
		
		JLabel lblSnake = new JLabel("Snake");
		lblSnake.setForeground(Color.GREEN);
		lblSnake.setFont(new Font("Snap ITC", Font.BOLD, 99));
		lblSnake.setBounds(255, 201, 368, 167);
		panelMenu.add(lblSnake);
		
		JButton btnInstructions = new JButton("Instructions");
		btnInstructions.setBackground(Color.WHITE);
		btnInstructions.setForeground(Color.RED);
		btnInstructions.setFont(new Font("Snap ITC", Font.PLAIN, 40));
		btnInstructions.setBounds(265, 449, 333, 81);
		panelMenu.add(btnInstructions);
		
		JButton btnPlay = new JButton("Play");
		btnPlay.setForeground(Color.RED);
		btnPlay.setFont(new Font("Snap ITC", Font.PLAIN, 40));
		btnPlay.setBounds(350, 588, 165, 61);
		panelMenu.add(btnPlay);
		
		JPanel panelInstructions = new JPanel();
		frame.getContentPane().add(panelInstructions, "name_77796248813751");
		
		JPanel panelGame = new JPanel();
		frame.getContentPane().add(panelGame, "name_77062832835280");
		
		JPanel panelScore = new JPanel();
		frame.getContentPane().add(panelScore, "name_77066424649336");
	}
}
