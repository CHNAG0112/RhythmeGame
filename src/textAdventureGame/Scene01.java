package textAdventureGame;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.LinkedList;

import javax.imageio.ImageIO;

import MAIN.GamePanel;
import MAIN.KeyHandler;
import tile.Song;
import tile.Tile;

public class Scene01 {
	public GamePanel gp;
	public KeyHandler keyH;
	public Song son1;

	public Scene01(GamePanel gp, KeyHandler keyH, Song son1) {
		this.gp = gp;
		this.keyH = keyH;
		this.son1 = son1;
		setDialog();
		getScenes();
	}

// 對話框長寬座標
	int x = 10;
	int y = 48 * 11;
	int width = 48 * 29;
	int length = 220;
	public String currentDialog = "";
	public LinkedList<String> dialog = new LinkedList<>();
	public LinkedList<String> dialogIn = new LinkedList<>();
	public int dialogIndex = 0;
	public int textSpeed = 1;
	public String[] stringArray;
	public BufferedImage[] scenes = new BufferedImage[40];
	public int sceneChoice, buttonIntervaltime;
	public String dia = "/";
	public int yDash = 0;

	public void getScenes() {
		try {
			scenes[0] = ImageIO.read(getClass().getResourceAsStream("/scene/hanada.png"));
			scenes[1] = ImageIO.read(getClass().getResourceAsStream("/scene/scene2.jpg"));
			scenes[2] = ImageIO.read(getClass().getResourceAsStream("/scene/scene3.jpg"));
			scenes[3] = ImageIO.read(getClass().getResourceAsStream("/scene/scene4.jpg"));
			scenes[19] = ImageIO.read(getClass().getResourceAsStream("/scene/saihate06.png"));
			scenes[18] = ImageIO.read(getClass().getResourceAsStream("/scene/saihate06.png"));
			scenes[17] = ImageIO.read(getClass().getResourceAsStream("/scene/GameName.png"));
			scenes[16] = ImageIO.read(getClass().getResourceAsStream("/scene/Ame.png"));

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setDialog() {
		dialog.add(0, "");
		dialog.add(1, "My name is Ame .I am a detective.One day    / When I want to listen some music,");
		dialog.add(2, "A weird thing happened .Those songs are     / lost.  ");
		dialog.add(3, "Those songs just disppeared  as them never  / existed.");
		dialog.add(4, "Not only CD but also memories .Nobody has   /  memories about songs.");
		dialog.add(5, "Only me remember songs. ");
		dialog.add(6, "I bet my grandfather's name.Abosolutely     /  caught the suspect.");
		dialog.add(7, "Therefore,I embark on a mistery Journey.");
		dialog.add(8, "...");
		dialog.add(9, "After a couple of months reserch...");
		dialog.add(10, "Finally.");
		dialog.add(11, "In Japan,");
		dialog.add(12, "A High School.");
		dialog.add(13, "...");
		dialog.add(14, "According to my reserch,the suspect who    /  stealing songs is hidding in this High School.");
		dialog.add(15, "But this school has thousands of students. /  How do I find the suspect?");
		dialog.add(16, "Suddenlly,");
		dialog.add(17, "A shadow appere in front of me.and his     /   hands takes my CD");
		dialog.add(18, "!!!");
		dialog.add(19, "Amazing , I didn't think you can find here.");
		dialog.add(20, "...Why did suspect take the initiative to  / show up.");
		dialog.add(21, "Why did you steal people's memories about  / songs and my CD.?");
		dialog.add(22, "......");
		dialog.add(23, "You will not understand...");
		dialog.add(24, "If you want to take  these back...");
		dialog.add(25, "Try to defeate me!");
		dialog.add(26, "");
		dialog.add(27, "The suspect has run away.");
		dialog.add(28, "At least I take back my CD.");
		dialog.add(29, "At this moment.");
		dialog.add(30, "A person who looks like a teacher angrily     /  come towards me.");
		dialog.add(31, "");
		dialog.add(32, "Hey you ! why didn't you go to classroom.     /  It has been class time.");
		dialog.add(33, ".....no...I am not student , I am adult....");
		dialog.add(34, "NO excuse,come Disciplinary office with me.");
		dialog.add(35, "NO!!!!!");
		dialog.add(36, "");
		dialog.add(37, "");
		dialog.add(38, "After three hours , I finally convenced       / them  I am a adult.");
		dialog.add(39, "SuspectX!! I will remember."); 
		dialog.add(40, "");
		dialog.add(41, ":So this is the Lost song CD.");
		dialog.add(42, "A antique store boss doubtfully say.");
		dialog.add(43, "Yeah ! it seems touch the CD will take       / back the memories.");
		dialog.add(44, "The Boss touch the CD.");
		dialog.add(45, "Have you remember something.");
		dialog.add(46, "......");
		dialog.add(47, "You are right. OK , if I have any clues      /  I will tell you.");
		dialog.add(48, "Actually, I got a weird rumor about the case.");
		dialog.add(49, ".......");
		dialog.add(50, "So next place is... shrine.");
		dialog.add(51, "A shrine is located at deep in the forest.");
		dialog.add(52, "Many climber said that they saw the YOUKAI   /  at this shrine.");
		dialog.add(53, "It seems that characters in  animation       /  songs come to the realworld.");
		dialog.add(54, ""); 
		dialog.add(55, "I arrived at the shrine.It's surronded by    / the tree and endless darkness ");
		dialog.add(56, "I feel not weel,but I can't see anything     / strange.");
		dialog.add(57, "Or any shadow of the YOUKAI.");
		dialog.add(58, "!!");
		dialog.add(59, "A barking arouse behind me.");
		dialog.add(60, "I turn around immediately.I stare at the    / closing dark contour.");
		dialog.add(61, "Who makes the sound is a....");
		dialog.add(62, "Cat?");
		dialog.add(63, "...");
		dialog.add(64, "You are so late , Natsumei .");
		dialog.add(65, ".......");
		dialog.add(66, "Cat is talking!!!");
		dialog.add(67, "Be quiet please , it is dipnight");
		dialog.add(68, "Sorry.");
		dialog.add(69, "By the way , Natsumei .");
		dialog.add(70, "?");
		dialog.add(71, "Give me the friend-note");
		dialog.add(72, "!!!");
		dialog.add(73, "");
		dialog.add(74, "");
	}

	public void update() {
		System.out.println(dialogIndex);
		textSpeed += 1;
		buttonIntervaltime++;

		// Enter鍵 顯示下段文字 buttonIntervaltime控制可以觸發按鍵的時間
		if (keyH.textGoOn == true && buttonIntervaltime > 40) {
			dialogIndex++;
			keyH.textGoOn = false;
			textSpeed = 0;
			buttonIntervaltime = 0;
			yDash = 0;
		}

		if (dialogIndex < dialog.size() && dialogIndex != 0) {
			stringArray = dialog.get(dialogIndex).split("");
		}
		// 跳轉場景
		switch (dialogIndex) {
		case 12: {
			sceneChoice = 1;

			break;
		}
		case 37: {
			sceneChoice = 2;

			break;
		}
		case 49: {
			sceneChoice = 3;

			break;
		}

		// 跳轉到選擇歌曲
		case 73: {
			gp.scene01Switch = false;
			keyH.goOn = false;
			gp.startplay = false;
			dialogIndex += 1;

			gp.sS.choice = 4;
			gp.son1.choice = 4;
			gp.son1.closeMusic();
			gp.son1.createtestsong2(gp.sS.choice);
			gp.son1.loadmusicmap();

			break;
		}
		case 26: {
			gp.scene01Switch = false;
			keyH.goOn = false;
			gp.startplay = false;
			dialogIndex += 1;

			gp.sS.choice = 1;
			gp.son1.choice = 1;
			gp.son1.closeMusic();
			gp.son1.createtestsong2(gp.sS.choice);
			gp.son1.loadmusicmap();
			break;
		}
		default:

		}
		// skip鍵
		if (keyH.skip == true) {
			if (dialogIndex < 26) {
				keyH.skip = false;
				dialogIndex = 26;
				sceneChoice = 1;
			} else {
				keyH.skip = false;
				dialogIndex = 73;
				sceneChoice = 3;
			}
		}

	}

	public void draw(Graphics2D g2) {
		// 背景圖片
		g2.drawImage(scenes[sceneChoice], 0, 0, 48 * 30, 48 * 16, null);
		// 人物圖片
		g2.drawImage(scenes[16], 48 * 10, 48 * 3, 48 * 8, 48 * 16, null);

	
		// skip圖示
		g2.setColor(Color.black);
		g2.fillRoundRect(10, 10, 100, 40, 35, 35);
		g2.setColor(Color.white);
		Font f = new Font("Serif", Font.BOLD, 30);
		g2.setFont(f);
		g2.drawString("skip z", 23, 37);

		Color c = new Color(0, 0, 0, 180);
		g2.setColor(c);
		g2.fillRoundRect(x, y, width, length, 35, 35);
		c = new Color(255, 255, 255);
		g2.setColor(c);
		g2.setStroke(new BasicStroke(5));
		g2.drawRoundRect(x + 5, y + 5, width - 10, length - 10, 25, 25);
		f = new Font("Dialog", Font.BOLD, 40);
		g2.setFont(f);

		if (dialogIndex < dialog.size() && dialogIndex != 0 && stringArray != null) {
			if (((int) textSpeed / 3) <= stringArray.length) {
				for (int i = 0; i < ((int) textSpeed / 3); i++) {
					if (stringArray[i].equals(dia)) {
						yDash = 60;
					}
					if (i < 43) {
						g2.drawString(stringArray[i], x + 48 + i * 30, y + 48);
					}
					if (!stringArray[i].equals(dia)) {
						g2.drawString(stringArray[i], x + 48 - 23 * yDash + i * 30, y + 48 + yDash);
					}
				}

			} else {
				yDash = 0;
				for (int i = 0; i < stringArray.length; i++) {
					if (stringArray[i].equals(dia)) {
						yDash = 60;
					}
					if (i < 43) {
						g2.drawString(stringArray[i], x + 48 + i * 30, y + 48);
					}
					if (!stringArray[i].equals(dia)) {
						g2.drawString(stringArray[i], x + 48 - 23 * yDash + i * 30, y + 48 + yDash);
					}

				}
			}

		}

	}
}
