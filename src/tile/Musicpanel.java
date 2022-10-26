package tile;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

import MAIN.GamePanel;
import MAIN.KeyHandler;

public class Musicpanel {
	BufferedImage musicpR;
	BufferedImage musicpD;
	BufferedImage musicpT;
	BufferedImage musicpL;
	BufferedImage musicpR1;
	BufferedImage musicpD1;
	BufferedImage musicpT1;
	BufferedImage musicpL1;
	int patcount;
	boolean leftCheck, rightCheck, topCheck, downCheck,lc,rc,tc,dc;


	GamePanel gp;
	KeyHandler keyH;

	public Musicpanel(GamePanel gp, KeyHandler keyH) {
		this.gp = gp;
		this.keyH = keyH;
		musicimg();
	}


	public void musicimg() {
		try {
			musicpR = ImageIO.read(getClass().getResourceAsStream("/musicgredients/mania-key4D.png"));
			musicpD = ImageIO.read(getClass().getResourceAsStream("/musicgredients/mania-key2D.png"));
			musicpT = ImageIO.read(getClass().getResourceAsStream("/musicgredients/mania-key3D.png"));
			musicpL = ImageIO.read(getClass().getResourceAsStream("/musicgredients/mania-key1D.png"));

			musicpR1 = ImageIO.read(getClass().getResourceAsStream("/musicgredients/mania-key4.png"));
			musicpD1 = ImageIO.read(getClass().getResourceAsStream("/musicgredients/mania-key2.png"));
			musicpT1 = ImageIO.read(getClass().getResourceAsStream("/musicgredients/mania-key3.png"));
			musicpL1 = ImageIO.read(getClass().getResourceAsStream("/musicgredients/mania-key1.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void update() {
		patcount++;
          
		if (keyH.leftA == true) {
			
			leftCheck = true;
		}
		if (keyH.downS == true) {
			
			downCheck = true;
		}
		if (keyH.topW == true) {
		
			topCheck = true;
		}
		if (keyH.RightD == true) {
			rightCheck = true;
		}
		if (keyH.leftA == false) {
			leftCheck = false;
		}
		if (keyH.downS == false) {
			downCheck = false;
		}
		if (keyH.topW == false) {
			topCheck = false;
		}
		if (keyH.RightD == false) {
			rightCheck = false;
		}

	}

	public void draw(Graphics2D g2) {
		if (leftCheck) {
			g2.drawImage(musicpL1, 0, -630, 100, 768, null);
		}
		if (downCheck) {
			g2.drawImage(musicpD1, 100, -630, 100, 768, null);
		}
		if (topCheck) {
			g2.drawImage(musicpT1, 200, -630, 100, 768, null);
		}
		if (rightCheck) {
			g2.drawImage(musicpR1, 300, -630, 100, 768, null);
		}

		if (!leftCheck) {
			g2.drawImage(musicpL, 0, -630, 100, 768, null);
		}
		if (!downCheck) {
			g2.drawImage(musicpD, 100, -630, 100, 768, null);
		}
		if (!topCheck) {
			g2.drawImage(musicpT, 200, -630, 100, 768, null);
		}
		if (!rightCheck) {
			g2.drawImage(musicpR, 300, -630, 100, 768, null);
		}
//		g2.drawImage(musicpL, 0, 0, 100, 768, null);
//		g2.drawImage(musicpD, 100, 0, 100, 768, null);
//		g2.drawImage(musicpT, 200, 0, 100, 768, null);
//		g2.drawImage(musicpR, 300, 0, 100, 768, null);
	}
}
