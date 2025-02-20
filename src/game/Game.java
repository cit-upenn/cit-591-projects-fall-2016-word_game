package game;

/**
* This is a test class for car game
* @author Wen Zhong
*/
import java.awt.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

public class Game extends JFrame{
	public static GamePanel panel = null;
	public static QuestionPanel qPanel = null;
	public static EndOfGamePanel cPanel = null;
	public static CardLayout layout = null;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			new Game();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * constructor
	 * @throws IOException
	 */
	public Game() throws IOException{
		layout = new CardLayout();
		this.setLayout(layout);
		panel = new GamePanel();
		Thread t = new Thread(panel);
		t.start();
		qPanel = new QuestionPanel();
		try {
			cPanel = new EndOfGamePanel();
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.add(panel, "game panel");
		this.add(qPanel, "question panel");
		this.add(cPanel, "end");
		this.addKeyListener(panel);
		int width = Toolkit.getDefaultToolkit().getScreenSize().width;
		int height = Toolkit.getDefaultToolkit().getScreenSize().height;
		this.setVisible(true);
		this.setSize(width / 3 * 2, height - 100);
		this.setLocation(200, 0);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
