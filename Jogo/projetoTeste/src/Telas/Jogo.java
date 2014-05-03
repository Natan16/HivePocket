package Telas;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import Logica.Logica;
import Telas.Menu.DrawingPanel;
import Utils.Utils;

public class Jogo {
	public Jogo() {
		createAndShowGUI();
		Utils.setXYasVertex(false); //RECOMMENDED: leave this as FALSE.
		Utils.setHeight(HEXSIZE); //Either setHeight or setSize must be run to initialize the hex
		Utils.setBorders(BORDERS);
	}
	//constants and global variables
	final static Color COLOURBACK =  Color.WHITE;
	public final static Color COLOURCELL =  Color.ORANGE;	 
	public final static Color COLOURGRID =  Color.BLACK;	 
	public final static Color COLOURONE = new Color(255,255,255,200);
	public final static Color COLOURONETXT = Color.BLUE;
	public final static Color COLOURTWO = new Color(0,0,0,200);
	public final static Color COLOURTWOTXT = new Color(255,100,255);
//	final static int EMPTY = 0;
	final static int BSIZE = 5; //tanto de hexagonos na horizontal
	static int HEXSIZE = 100;	
	public final static int BORDERS = 20; //espaco lateral 
	final static int SCRSIZE = HEXSIZE * (BSIZE + 1) + BORDERS*3; //screen size (vertical dimension).
	private static Container content;
	BufferedImage[][] board = new BufferedImage[BSIZE][BSIZE];

	private void createAndShowGUI()
	{
		JPanel p1 = new JPanel(); // jogo do Jogador 1
		JPanel p2 = new JPanel(); // jogo do Jogador 2
		JPanel g = new JPanel();
		DrawingPanel panel = new DrawingPanel();
		DrawingPanel jogo1 = new DrawingPanel();
		DrawingPanel jogo2 = new DrawingPanel();
		
		p1.add(jogo1);
		p2.add(jogo2);
		g.add(panel);
		//JFrame.setDefaultLookAndFeelDecorated(true);
		content = Menu.getContainer();
		content.add(panel);
		content.add(jogo1);
		content.add(jogo2);
	}
	

	@SuppressWarnings("serial")
	class DrawingPanel extends JPanel
	{		
		public DrawingPanel()
		{	
			setBackground(COLOURBACK);
			Logica ml = new Logica();            
			addMouseListener(ml);
		}

		public void paintComponent(Graphics g)
		{
			Graphics2D g2 = (Graphics2D)g;
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			g.setFont(new Font("TimesRoman", Font.PLAIN, 10));
			super.paintComponent(g2);
			//draw grid
			for (int i=0;i<BSIZE;i++) {
				for (int j=1;j<BSIZE;j++) {
					Utils.drawHex(i,j,g2);
				}
			}
			Utils.drawHex(1,0,g2);

			//fill in hexes
			for (int i=0;i<BSIZE;i++) {
				for (int j=0;j<BSIZE;j++) {						
					Utils.fillHex(i,j,board[i][j],g2);
				}
			}
		}

	} // end of DrawingPanel class
}
