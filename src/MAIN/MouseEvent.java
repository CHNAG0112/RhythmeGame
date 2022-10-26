package MAIN;

import java.awt.Point;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseEvent implements MouseListener, MouseMotionListener {
	public Point poi;
	public int mousex,mousey;

	@Override
	public void mouseClicked(java.awt.event.MouseEvent e) {
		mousex=e.getX();
		mousey=e.getY();
	}

	@Override
	public void mousePressed(java.awt.event.MouseEvent e) {
		
		
	}

	@Override
	public void mouseMoved(java.awt.event.MouseEvent e) {

		
	}

	@Override
	public void mouseDragged(java.awt.event.MouseEvent e) {
		
	}

	@Override
	public void mouseReleased(java.awt.event.MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(java.awt.event.MouseEvent e) {
	
	}

	@Override
	public void mouseExited(java.awt.event.MouseEvent e) {

	}

}
