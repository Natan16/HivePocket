package Telas;

import java.awt.*;

import javax.imageio.ImageIO;
import javax.swing.*;

import Utils.*;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

/**********************************
  This is the main class of a Java program to play a game based on hexagonal tiles.
  The mechanism of handling hexes is in the file hexmech.java.

  Written by: M.H.
  Date: December 2012

 ***********************************/

public class Menu{
  private Menu() throws IOException {
		initGame();
		createAndShowGUI();
	}

	public static void main(String[] args)
	{
		SwingUtilities.invokeLater(new Runnable() {
				public void run() {
				try {
					new Menu();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				}
				});
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
	final static int BSIZE = 3; //tanto de hexagonos na horizontal
	final static int HEXSIZE = 
			300;	//Zoom
	public final static int BORDERS = 20; //espaco lateral 
	final static int SCRSIZE = HEXSIZE * (BSIZE + 1) + BORDERS*3; //screen size (vertical dimension).
	private static Container content;
	BufferedImage[][] board;

	void initGame() throws IOException{

		Utils.setXYasVertex(false); //RECOMMENDED: leave this as FALSE.
		Utils.setHeight(HEXSIZE); //Either setHeight or setSize must be run to initialize the hex
		Utils.setBorders(BORDERS);

//		for (int i=0;i<BSIZE;i++) {
//			for (int j=0;j<BSIZE;j++) {
//				board[i][j]=EMPTY;
//			}
//		}

		//set up board here
		board = new BufferedImage[BSIZE][BSIZE];
		URL url1 =	this.getClass().getResource("Imagens/bee.png");
		board[1][1] = ImageIO.read(url1);
		URL url2 =	this.getClass().getResource("Imagens/novojogo.png");
		board[1][0] = ImageIO.read(url2);
		URL url3 =	this.getClass().getResource("Imagens/opcoes.png");
		board[2][1] = ImageIO.read(url3);
		URL url4 =	this.getClass().getResource("Imagens/instrucoes.png");
		board[2][2] = ImageIO.read(url4);
		URL url5 =	this.getClass().getResource("Imagens/sair.png");
		board[1][2] = ImageIO.read(url5);
		URL url6 =	this.getClass().getResource("Imagens/creditos.png");
		board[0][2] = ImageIO.read(url6);
		URL url7 =	this.getClass().getResource("Imagens/estatisticas.png");
		board[0][1] = ImageIO.read(url7);
		
		
	}

	private void createAndShowGUI()
	{
		DrawingPanel panel = new DrawingPanel();


		//JFrame.setDefaultLookAndFeelDecorated(true);
		JFrame frame = new JFrame("Tabuleiro");
		frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		content = frame.getContentPane();
		content.add(panel);
		//this.add(panel);  -- cannot be done in a static context
		//for hexes in the FLAT orientation, the height of a 10x10 grid is 1.1764 * the width. (from h / (s+t))
		frame.setSize( (int)(SCRSIZE/1.23), SCRSIZE);
		frame.setResizable(false);
		frame.setLocationRelativeTo( null );
		frame.setVisible(true);
	}
	

	@SuppressWarnings("serial")
	class DrawingPanel extends JPanel
	{		
		//mouse variables here
		//Point mPt = new Point(0,0);

		public DrawingPanel()
		{	
			setBackground(COLOURBACK);

			TelaManager ml = new TelaManager();            
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
					//if (board[i][j] < 0) hexmech.fillHex(i,j,COLOURONE,-board[i][j],g2);
					//if (board[i][j] > 0) hexmech.fillHex(i,j,COLOURTWO, board[i][j],g2);
					Utils.fillHex(i,j,board[i][j],g2);
				}
			}

			//g.setColor(Color.RED);
			//g.drawLine(mPt.x,mPt.y, mPt.x,mPt.y);
		}

	} // end of DrawingPanel class
	public static Container getContainer(){
		return content;
	}
}
