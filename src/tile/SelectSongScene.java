package tile;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.LayoutManager;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import MAIN.KeyHandler;
import MAIN.GamePanel;

public class SelectSongScene extends JPanel {
	GamePanel gp;
	KeyHandler keyH;
	Song son1;
	BufferedImage[] songImg = new BufferedImage[22];
	String[] songName = new String[22];
	String[] songTime = new String[22];
	public int choice = 0;
	int timeCount = 0;

	public SelectSongScene(GamePanel gp, MAIN.KeyHandler keyH, Song son1) {
		this.gp = gp;
		this.keyH = keyH;
		this.son1 = son1;
		getSongImage();
		loadSongName();
	}

//讀取歌曲圖片
	public void getSongImage() {
		try {
			songImg[0] = ImageIO.read(getClass().getResourceAsStream("/songs/ral.jpg"));
			songImg[1] = ImageIO.read(getClass().getResourceAsStream("/songs/rap.jpg"));
			songImg[2] = ImageIO.read(getClass().getResourceAsStream("/songs/fly.png"));
			songImg[3] = ImageIO.read(getClass().getResourceAsStream("/songs/rain.jpg"));
			songImg[4] = ImageIO.read(getClass().getResourceAsStream("/songs/akane.jpg"));
			songImg[5] = ImageIO.read(getClass().getResourceAsStream("/songs/hero.png"));
			songImg[6] = ImageIO.read(getClass().getResourceAsStream("/songs/skull.jpg"));
			songImg[7] = ImageIO.read(getClass().getResourceAsStream("/songs/boys.jpg"));
			songImg[8] = ImageIO.read(getClass().getResourceAsStream("/songs/ahoy.png"));
			songImg[19] = ImageIO.read(getClass().getResourceAsStream("/songs/back02.jpg"));
			songImg[20] = ImageIO.read(getClass().getResourceAsStream("/songs/back1.jpg"));

		} catch (IOException e) {
		}
	}

//歌名
	public void loadSongName() {
		songName[0] = ("Only my Railgun");
		songName[1] = ("My noficiton");
		songName[2] = ("Butterfly");
		songName[3] = ("Rain");
		songName[4] = ("Akane");
		songName[5] = ("Polaris");
		songName[6] = ("My romantic road");
		songName[7] = ("Boys and girls");
		songName[8] = ("Ahoy!! ");
	}

//歌曲時間
	public void loadSongTimes() {
		songTime[0] = ("1:36");
		songTime[1] = ("1:30");
		songTime[2] = ("1:39");
		songTime[3] = ("1:33");
		songTime[4] = ("1:33");
		songTime[5] = ("1:40");
		songTime[6] = ("1:30");
		songTime[7] = ("1:30");
		songTime[8] = ("1:50");
	}

	public void loadbox() {

	}

	public void update() {
//上下鍵控制歌曲的 choice		
		if (keyH.upPressjudge == true && keyH.upPressed == true) {
			if (songImg[choice + 1] != null) {
				choice += 1;
				son1.choice += 1;
				son1.closeMusic();
				son1.createtestsong2(choice);
				son1.loadmusicmap();
			} else {
				choice = 0;
				son1.choice = 0;
				son1.closeMusic();
				son1.createtestsong2(choice);
				son1.loadmusicmap();
			}
			keyH.upPressed = false;
		}
		if (keyH.downPressjudge == true && keyH.downPressed == true) {
			if (choice > 0) {
				choice -= 1;
				son1.choice -= 1;
				son1.closeMusic();
				son1.createtestsong2(choice);
				son1.loadmusicmap();
			} else {
				choice = 8;
				son1.choice = 8;
				son1.closeMusic();
				son1.createtestsong2(choice);
				son1.loadmusicmap();
			}
			keyH.downPressed = false;
		}
		if (keyH.goOn == true) {
			gp.startplay = true;
			keyH.goOn = false;
		}
	}
	public void draw(Graphics2D g2) {
		g2.drawImage(songImg[20], 0, 0, 30 * 48, 16 * 48, null);
		g2.setColor(Color.BLUE);
		g2.setFont(new Font("Arial", Font.BOLD, 40));
		g2.drawString(songName[choice], 700, 200);
		g2.setColor(Color.RED);
		g2.drawImage(songImg[choice], 180, 150, 470, 470, null);
	}
}
