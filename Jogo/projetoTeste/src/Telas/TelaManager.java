package Telas;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import Utils.*;

public class TelaManager extends MouseAdapter {
	@SuppressWarnings("unused")
	public void mouseClicked(MouseEvent e) { 
		int x = e.getX(); 
		int y = e.getY(); 
		
		//mPt.x = x;
		//mPt.y = y;
		Point p = new Point( Utils.pxtoHex(e.getX(),e.getY()) );
		if (p.x < 0 || p.y < 0 || p.x >= Menu.BSIZE || p.y >= Menu.BSIZE) return;
		//apagar tela
		Menu.getContainer().removeAll();
		Menu.getContainer().revalidate();
		Menu.getContainer().repaint();
		
		
		if(p.x == 1 && p.y == 2 ){
			Sair sair = new Sair();
		}
		if(p.x == 1 && p.y == 0 ){
			Jogo novo = new Jogo();
		}
		if(p.x == 1 && p.y == 0 ){
			
		}
		if(p.x == 1 && p.y == 0 ){
			
		}
		if(p.x == 1 && p.y == 0 ){
			
		}
		if(p.x == 1 && p.y == 0 ){
			
		}
			
	}		 
} //end of MyMouseListener class 
