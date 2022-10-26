package tile;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;

import MAIN.GamePanel;
import MAIN.KeyHandler;

public class MusicFront {
	public BufferedImage[] songImg = new BufferedImage[20];
    public GamePanel gp;
	public KeyHandler keyH;
	
	
	public MusicFront(GamePanel gp, KeyHandler keyH) {
		this.gp=gp;
		this.keyH=keyH;
		getSongImage();		
	}

	public void getSongImage() {
		try {
			songImg[0] = ImageIO.read(getClass().getResourceAsStream("/songs/ra.jpg"));
			songImg[1] = ImageIO.read(getClass().getResourceAsStream("/songs/rap.jpg"));
			songImg[2] = ImageIO.read(getClass().getResourceAsStream("/songs/fly.png"));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public void draw(Graphics2D g2) {
	
	} 
	
	
	

}
