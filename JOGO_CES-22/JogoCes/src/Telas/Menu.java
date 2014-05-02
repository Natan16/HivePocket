package Telas;

import java.awt.*;

import javax.swing.*;

import java.awt.event.*; 

/**********************************
  This is the main class of a Java program to play a game based on hexagonal tiles.
  The mechanism of handling hexes is in the file hexmech.java.

  Written by: M.H.
  Date: December 2012

 ***********************************/

public class Menu
{
  private Menu() {
		initGame();
		createAndShowGUI();
	}

	public static void main(String[] args)
	{
		SwingUtilities.invokeLater(new Runnable() {
				public void run() {
				new Menu();
				}
				});
	}

	//constants and global variables
	final static Color COLOURBACK =  Color.WHITE;
	final static Color COLOURCELL =  Color.ORANGE;	 
	final static Color COLOURGRID =  Color.BLACK;	 
	final static Color COLOURONE = new Color(255,255,255,200);
	final static Color COLOURONETXT = Color.BLUE;
	final static Color COLOURTWO = new Color(0,0,0,200);
	final static Color COLOURTWOTXT = new Color(255,100,255);
//	final static Icon EMPTY ;
	final static int BSIZE = 3; //tanto de hexagonos na horizontal
	final static int HEXSIZE = 190;	//Zoom
	final static int BORDERS = 5; //espaco lateral 
	final static int SCRSIZE = HEXSIZE * (BSIZE + 1) + BORDERS*3; //screen size (vertical dimension).

	Icon[][] board = new Icon[BSIZE][BSIZE];

	void initGame(){

		hexmech.setXYasVertex(false); //RECOMMENDED: leave this as FALSE.
		hexmech.setHeight(HEXSIZE); //Either setHeight or setSize must be run to initialize the hex
		hexmech.setBorders(BORDERS);

		board[1][1]=new ImageIcon("Imagens/bee");
		board[1][0]=new ImageIcon("Imagens/novojogo");
		board[2][1]=new ImageIcon("Imagens/instrucoes");
		board[2][2]=new ImageIcon("Imagens/opcoes");
		board[1][2]=new ImageIcon("Imagens/sair");
		board[0][2]=new ImageIcon("Imagens/creditos");
		board[0][1]=new ImageIcon("Imagens/estatisticas");


	}

	private void createAndShowGUI()
	{
		DrawingPanel panel = new DrawingPanel();


		//JFrame.setDefaultLookAndFeelDecorated(true);
		JFrame frame = new JFrame("Tabuleiro");
		frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		Container content = frame.getContentPane();
		content.add(panel);
		//this.add(panel);  -- cannot be done in a static context
		//for hexes in the FLAT orientation, the height of a 10x10 grid is 1.1764 * the width. (from h / (s+t))
		frame.setSize( (int)(SCRSIZE/1.23), SCRSIZE);
		frame.setResizable(false);
		frame.setLocationRelativeTo( null );
		frame.setVisible(true);
	}


	class DrawingPanel extends JPanel
	{		
		//mouse variables here
		//Point mPt = new Point(0,0);

		public DrawingPanel()
		{	
			setBackground(COLOURBACK);

			MyMouseListener ml = new MyMouseListener();            
			addMouseListener(ml);
		}

		public void paintComponent(Graphics g)
		{
			Graphics2D g2 = (Graphics2D)g;
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			g.setFont(new Font("TimesRoman", Font.PLAIN, 50));
			super.paintComponent(g2);
			//draw grid
			for (int i=0;i<BSIZE;i++) {
				for (int j=1;j<BSIZE;j++) {
					hexmech.drawHex(i,j,g2);
				}
			}
			hexmech.drawHex(1,0,g2);
			//fill in hexes
			for (int i=0;i<BSIZE;i++) {
				for (int j=0;j<BSIZE;j++) {					
					//if (board[i][j] < 0) hexmech.fillHex(i,j,COLOURONE,-board[i][j],g2);
					//if (board[i][j] > 0) hexmech.fillHex(i,j,COLOURTWO, board[i][j],g2);
					hexmech.fillHex(i,j,board[i][j],g2);
				}
			}

			//g.setColor(Color.RED);
			//g.drawLine(mPt.x,mPt.y, mPt.x,mPt.y);
		}

		class MyMouseListener extends MouseAdapter	{	//inner class inside DrawingPanel 
			public void mouseClicked(MouseEvent e) { 
				int x = e.getX(); 
				int y = e.getY(); 
				//mPt.x = x;
				//mPt.y = y;
				Point p = new Point( hexmech.pxtoHex(e.getX(),e.getY()) );
				if (p.x < 0 || p.y < 0 || p.x >= BSIZE || p.y >= BSIZE) return;

				//DEBUG: colour in the hex which is supposedly the one clicked on
				//clear the whole screen first.
				/* for (int i=0;i<BSIZE;i++) {
					for (int j=0;j<BSIZE;j++) {
						board[i][j]=EMPTY;
					}
				} */
				Icon Escolhido = new ImageIcon("Imagens/escolhido.png");
				//What do you want to do when a hexagon is clicked?
				board[p.x][p.y] = Escolhido;
				repaint();
			}		 
		} //end of MyMouseListener class 
	} // end of DrawingPanel class
}
