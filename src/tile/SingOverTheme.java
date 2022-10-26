package tile;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;

import MAIN.GamePanel;
import MAIN.KeyHandler;
import MAIN.MouseEvent;

public class SingOverTheme extends JPanel {
	public Song son1;
	GamePanel gp;
	KeyHandler keyh;
	JButton end;
	public BufferedImage[] settleImg = new BufferedImage[20];
	public int mc0, mc1, mc2, pc0, pc1, pc2, gc0, gc1, gc2, gdc0, gdc1, gdc2, mic0, mic1, mic2;
	MouseEvent mouseevent;
	public boolean gameover;
	public String score;
	 Integer scores;
	 SaveScores ss;
	

	public SingOverTheme(Song son1, GamePanel gp, KeyHandler keyh, MouseEvent mouseevent) {
		this.son1 = son1;
		this.gp = gp;
		this.keyh = keyh;
		this.mouseevent = mouseevent;
		getUDLRImage();

	}
//讀取0到9的圖示
	public void getUDLRImage() {
		try {

			settleImg[0] = ImageIO.read(getClass().getResourceAsStream("/score/score-0.png"));

			settleImg[1] = ImageIO.read(getClass().getResourceAsStream("/score/score-1.png"));

			settleImg[2] = ImageIO.read(getClass().getResourceAsStream("/score/score-2.png"));

			settleImg[3] = ImageIO.read(getClass().getResourceAsStream("/score/score-3.png"));

			settleImg[4] = ImageIO.read(getClass().getResourceAsStream("/score/score-4.png"));

			settleImg[5] = ImageIO.read(getClass().getResourceAsStream("/score/score-5.png"));

			settleImg[6] = ImageIO.read(getClass().getResourceAsStream("/score/score-6.png"));

			settleImg[7] = ImageIO.read(getClass().getResourceAsStream("/score/score-7.png"));

			settleImg[8] = ImageIO.read(getClass().getResourceAsStream("/score/score-8.png"));

			settleImg[9] = ImageIO.read(getClass().getResourceAsStream("/score/score-9.png"));

			settleImg[10] = ImageIO.read(getClass().getResourceAsStream("/maps/mania-note4.png"));

			settleImg[11] = ImageIO.read(getClass().getResourceAsStream("/maps/SingOver.jpg"));
			
			settleImg[12] = ImageIO.read(getClass().getResourceAsStream("/score/Gameover.jpg"));
			
			settleImg[13] = ImageIO.read(getClass().getResourceAsStream("/score/Back.png"));
			settleImg[14] = ImageIO.read(getClass().getResourceAsStream("/score/history.png"));
			settleImg[15] = ImageIO.read(getClass().getResourceAsStream("/scene/sakurajpg.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void update() {
		//點擊history按鈕
		if (mouseevent.mousex < 1410 && mouseevent.mousex > 1210 && mouseevent.mousey > 377
				&& mouseevent.mousey < 477) {
			gp.history.relocation=0;
			gp.historySwitch=true;			
			gp.history.firstIn=false;
		}
		
		
		// 點擊back按鈕回到場景 歌曲重新初始化1210, 577, 200, 100
		if (mouseevent.mousex < 1410 && mouseevent.mousex > 1210 && mouseevent.mousey > 577
				&& mouseevent.mousey < 677) {
			gp.som.ss.table.clear();
			 keyh.skip=false;
//new SaveScores(gp.myId,gp.sS.songName[gp.son1.choice],scores,son1.marvelousCount,	son1.perfectCount 
//		, son1.goodCount, son1.greatCount,son1.missCount);
//			if(son1.missCount>400) {
//				gameover=true;
//			}
//			else {
				gp.scene01Switch=true; 
				gp.SingOverThemeSwitch=false;
				son1.missCount = 0;
				son1.badCount = 0;
				son1.goodCount = 0;
				son1.greatCount = 0;
				son1.perfectCount = 0;
				son1.marvelousCount = 0;
				son1.combo = 0;
				keyh.gameStart = true;
				son1.stopsong = false;
				keyh.renew = false;
				son1.relocate = 0;
				son1.Interlude = 0;
				son1.judg = 0;
				mouseevent.mousex =0;
				mouseevent.mousey =0;
//			}				
		}
		  scores=son1.marvelousCount*1000+son1.perfectCount*700+son1.greatCount*500+son1.goodCount*300;
		 score=scores.toString();	
	}
	
	

	public void draw(Graphics2D g2) {
		//結算畫面 把判定轉成數字 再顯示相對數字
		
		g2.drawImage(settleImg[11], 0, 0, 48*25, 48*18, null);

		g2.drawImage(son1.marvelous, 280, 150, 500, 50, null);

		Integer c1 = (son1.marvelousCount);
		String[] mc = c1.toString().split("");
		if (mc.length == 3) {
			g2.drawImage(settleImg[Integer.parseInt(mc[0])], 820, 150, 50, 50, null);
			g2.drawImage(settleImg[Integer.parseInt(mc[1])], 870, 150, 50, 50, null);
			g2.drawImage(settleImg[Integer.parseInt(mc[2])], 920, 150, 50, 50, null);
		} else if (mc.length == 2) {
			g2.drawImage(settleImg[Integer.parseInt(mc[0])], 820, 150, 50, 50, null);
			g2.drawImage(settleImg[Integer.parseInt(mc[1])], 870, 150, 50, 50, null);
		} else if (mc.length == 1) {
			g2.drawImage(settleImg[Integer.parseInt(mc[0])], 820, 150, 50, 50, null);
		}

		g2.drawImage(son1.perfect, 280, 250, 500, 50, null);

		Integer c2 = (son1.perfectCount);
		String[] pc = c2.toString().split("");
		if (pc.length == 3) {
			g2.drawImage(settleImg[Integer.parseInt(pc[0])], 820, 250, 50, 50, null);
			g2.drawImage(settleImg[Integer.parseInt(pc[1])], 870, 250, 50, 50, null);
			g2.drawImage(settleImg[Integer.parseInt(pc[2])], 920, 250, 50, 50, null);
		} else if (pc.length == 2) {
			g2.drawImage(settleImg[Integer.parseInt(pc[0])], 820, 250, 50, 50, null);
			g2.drawImage(settleImg[Integer.parseInt(pc[1])], 870, 250, 50, 50, null);
		} else if (pc.length == 1) {
			g2.drawImage(settleImg[Integer.parseInt(pc[0])], 820, 250, 50, 50, null);
		}

		g2.drawImage(son1.great, 280, 350, 500, 50, null);

		Integer c3 = (son1.greatCount);
		String[] gc = c3.toString().split("");
		if (gc.length == 3) {
			g2.drawImage(settleImg[Integer.parseInt(gc[0])], 820, 350, 50, 50, null);
			g2.drawImage(settleImg[Integer.parseInt(gc[1])], 870, 350, 50, 50, null);
			g2.drawImage(settleImg[Integer.parseInt(gc[2])], 920, 350, 50, 50, null);
		} else if (gc.length == 2) {
			g2.drawImage(settleImg[Integer.parseInt(gc[0])], 820, 350, 50, 50, null);
			g2.drawImage(settleImg[Integer.parseInt(gc[1])], 870, 350, 50, 50, null);
		} else if (gc.length == 1) {
			g2.drawImage(settleImg[Integer.parseInt(gc[0])], 820, 350, 50, 50, null);
		}

		g2.drawImage(son1.good, 280, 450, 500, 50, null);

		Integer c4 = (son1.goodCount);
		String[] gdc = c4.toString().split("");
		if (gdc.length == 3) {
			g2.drawImage(settleImg[Integer.parseInt(gdc[0])], 820, 450, 50, 50, null);
			g2.drawImage(settleImg[Integer.parseInt(gdc[1])], 870, 450, 50, 50, null);
			g2.drawImage(settleImg[Integer.parseInt(gdc[2])], 920, 450, 50, 50, null);
		} else if (gdc.length == 2) {
			g2.drawImage(settleImg[Integer.parseInt(gdc[0])], 820, 450, 50, 50, null);
			g2.drawImage(settleImg[Integer.parseInt(gdc[1])], 870, 450, 50, 50, null);
		} else if (gdc.length == 1) {
			g2.drawImage(settleImg[Integer.parseInt(gdc[0])], 820, 450, 50, 50, null);
		}

		g2.drawImage(son1.miss, 280, 550, 500, 50, null);
		Integer c5 = (son1.missCount);
		String[] mic = c5.toString().split("");
		if (mic.length == 3) {
			g2.drawImage(settleImg[Integer.parseInt(mic[0])], 820, 550, 50, 50, null);
			g2.drawImage(settleImg[Integer.parseInt(mic[1])], 870, 550, 50, 50, null);
			g2.drawImage(settleImg[Integer.parseInt(mic[2])], 920, 550, 50, 50, null);
		} else if (mic.length == 2) {
			g2.drawImage(settleImg[Integer.parseInt(mic[0])], 820, 550, 50, 50, null);
			g2.drawImage(settleImg[Integer.parseInt(mic[1])], 870, 550, 50, 50, null);
		} else if (mic.length == 1) {
			g2.drawImage(settleImg[Integer.parseInt(mic[0])], 820, 550, 50, 50, null);
		}
//		 Font f = new Font("Serif",Font.BOLD,40);
//		 g2.setColor(Color.black);
//			g2.setFont(f);
//			if(score!=null) {
//				g2.drawString(score, 1300, 200);
//			}
			int scoreX=0;
			if(score!=null) {
				String[] ss=score.split("");
			    for(String s: ss) {
			    	g2.drawImage(settleImg[Integer.parseInt(s)], 300+scoreX, 50, 30, 70, null);
			    	scoreX+=70;
			    }
			}
//			g2.setColor(Color.blue);
//			g2.fillRect(1072, 577, 200, 100);
			//back 圖示
            g2.drawImage(settleImg[13], 1210, 577, 200, 100,null);
            //history圖示
            g2.drawImage(settleImg[14], 1210, 377, 200, 100,null);
			Font f = new Font("Serif",Font.BOLD,70);
			g2.setColor(Color.white);
			g2.setFont(f);
			g2.drawString("Scores", 70, 100);
		//Gameover畫面
//		g2.drawImage(settleImg[12],0,0,48*30,48*16,null);
	}
}
