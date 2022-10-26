package tile;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.SQLException;

import javax.imageio.ImageIO;

import MAIN.GamePanel;
import MAIN.KeyHandler;
import MAIN.MouseEvent;

public class Song {
	GamePanel gp;
	KeyHandler keyH;
	MouseEvent mouseevent;
	Tile[] tile;
	public BufferedImage tamp, miss, bad, good, great, perfect, marvelous;
	public int missCount, badCount, goodCount, greatCount, perfectCount, marvelousCount, combo;
	BufferedImage lightingN;
	public int relocate;
	public boolean collideL, collideD, collideT, collideR;
	public boolean collideLConfirm, collideDConfirm, collideTConfirm, collideRConfirm, stopsong;
	Loadmusic b;
	public Long nowFrame;
	public BufferedImage[] songImg = new BufferedImage[20];
	int screenX;
	int screenY;
	public int judg;
	public double speed = 12;
	// 調控速度變化
	public int speedcountrol = 1;
	public int mapTileNum[][] = new int[4][970];
	public int rowRange = 970;
	int judg2 = 0;
	Color c = new Color(0, 0, 0, 200);
	public boolean startplay;
	BufferedReader br;
	// 選擇歌曲
	public int choice = 0;

	public InputStream[] is = new InputStream[10];
	// 間奏
	public int Interlude;
	// 歌曲編號跟譜面編號
	public int songOrder = 0;
	public int mapOrder = 0;
	// 第一個括號是有多少歌 第二個是參數位置
	public int[][] songArg;

	// 音樂遊戲初始化
	public Song(GamePanel gp, KeyHandler keyH, MouseEvent mouseevent) {
		this.gp = gp;
		this.keyH = keyH;
		this.mouseevent=mouseevent;
		tile = new Tile[10];
		getUDLRImage();
		loadmusicmap();
		judgeImage();
		setArg();
		getSongImage();
		createtestsong2(choice);
		
	}

	// 每一首歌曲的前奏時間 ,位移
	public void setArg() {
		songArg = new int[15][10];
		songArg[0][0] = ((gp.FPS * 10) + 30);
		songArg[0][1] = 63;
		songArg[0][2] = 1600;
		songArg[0][3] = 5;
		songArg[1][0] = (gp.FPS * 5) - 20;
		songArg[1][1] = 58;
		songArg[1][2] = 1580;
		songArg[1][3] = 5;
		songArg[2][0] = (gp.FPS * 15);
		songArg[2][1] = 57;
		songArg[2][2] = 1600;
		songArg[2][3] = 5;
		songArg[3][0] = (gp.FPS * 0);
		songArg[3][1] = 76;
		songArg[3][2] = 900;
		songArg[3][3] = 4;
		songArg[4][0] = (gp.FPS * 0);
		songArg[4][1] = 195;
		songArg[4][2] = 2670;
		songArg[4][3] = 8;
		songArg[5][0] = (gp.FPS * 0);
		songArg[5][1] = 151;
		songArg[5][2] = 180;
		songArg[5][3] = 8;
		songArg[6][0] = (gp.FPS * 2) + 70;
		songArg[6][1] = 99;
		songArg[6][2] = 900;
		songArg[6][3] = 8;
		songArg[7][0] = (gp.FPS * 7) + 70;
		songArg[7][1] = 77;
		songArg[7][2] = 900;
		songArg[7][3] = 8;
		songArg[8][0] = (gp.FPS * 6);
		songArg[8][1] = 83;
		songArg[8][2] = 800;
		songArg[8][3] = 8;

	}

//載入上下左右圖示
	public void getUDLRImage() {
		try {
			tile[0] = new Tile();
			tile[0].image = ImageIO.read(getClass().getResourceAsStream("/maps/mania-note1.png"));

			tile[1] = new Tile();
			tile[1].image = ImageIO.read(getClass().getResourceAsStream("/maps/mania-note1.png"));

			tile[2] = new Tile();
			tile[2].image = ImageIO.read(getClass().getResourceAsStream("/maps/mania-note2.png"));

			tile[3] = new Tile();
			tile[3].image = ImageIO.read(getClass().getResourceAsStream("/maps/mania-note3.png"));

			tile[4] = new Tile();
			tile[4].image = ImageIO.read(getClass().getResourceAsStream("/maps/mania-note4.png"));

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 載入圖片背景
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
		} catch (IOException e) {
		}
	}
//讀取判定圖示
	public void judgeImage() {
		try {
			miss = ImageIO.read(getClass().getResourceAsStream("/maps/mania-hit0.png"));

			bad = ImageIO.read(getClass().getResourceAsStream("/maps/mania-hit50.png"));

			good = ImageIO.read(getClass().getResourceAsStream("/maps/mania-hit100.png"));

			great = ImageIO.read(getClass().getResourceAsStream("/maps/mania-hit200.png"));

			perfect = ImageIO.read(getClass().getResourceAsStream("/maps/mania-hit300.png"));

			marvelous = ImageIO.read(getClass().getResourceAsStream("/maps/mania-hit300g.png"));

			lightingN = ImageIO.read(getClass().getResourceAsStream("/maps/lightingN.png"));

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

//寫入音檔
	public void createtestsong2(int choice) {
		b = new Loadmusic();
		b.runtest(choice);	
	}
	public void closeMusic() {
		b.clip.close();
	}
//播放音效
	public void broad(int i) {
		Loadmusic tick = new Loadmusic();
		tick.runtest(i);
		tick.clip.start();
		}

//寫入音樂譜面
	public void loadmusicmap() {
		try {
			is[0] = getClass().getResourceAsStream("/songs/ra.txt");
			is[1] = getClass().getResourceAsStream("/songs/rap.txt");
			is[2] = getClass().getResourceAsStream("/songs/fly.txt");
			is[3] = getClass().getResourceAsStream("/songs/rain.txt");
			is[4] = getClass().getResourceAsStream("/songs/akane.txt");
			is[5] = getClass().getResourceAsStream("/songs/hero.txt");
			is[6] = getClass().getResourceAsStream("/songs/skull.txt");
			is[7] = getClass().getResourceAsStream("/songs/boys.txt");
			is[8] = getClass().getResourceAsStream("/songs/ahoy.txt");
			BufferedReader br = new BufferedReader(new InputStreamReader(is[choice]));

			int col = 0;
			int row = 0;

			while (col < 5 && row < rowRange) {
				String line = br.readLine();
				while (col < 4) {
					String numbers[] = line.split(" ");
					int num = Integer.parseInt(numbers[col]);
					mapTileNum[col][row] = num;
					col++;

				}
				if (col == 4) {
					col = 0;
					row++;
				}
			}
			br.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		System.out.println(mapTileNum);
	}

	public void update() {
	//快速結束歌曲
		if (mouseevent.mousex < 1310 && mouseevent.mousex > 1210 && mouseevent.mousey > 10 && mouseevent.mousey < 50) {
			b.clip.close();
			gp.SingOverThemeSwitch = true;
			keyH.goOn = false;
			int scores=marvelousCount*1000+perfectCount*700+greatCount*500+goodCount*300;
			new SaveScores(gp.myId,gp.sS.songName[gp.son1.choice],scores,marvelousCount,perfectCount 
					, goodCount, greatCount,missCount);
			gp.startplay = false;
			gp.SingOverThemeSwitch = true;
			try {
				SaveScores.getHistory(gp.myId);
			} catch (SQLException e) {
				System.out.println(e);
			
			}
			
		}
		
		speed = songArg[choice][3];
		// 暫停歌曲 譜面
		if (keyH.escapekey == true && stopsong == false) {
			nowFrame = b.clip.getMicrosecondPosition();
			b.clip.close();
			stopsong = true;
		}
		// 暫停後返回 歌曲繼續譜面
		if (keyH.goOn == true && keyH.goOnrelease == false && stopsong == true) {
			createtestsong2(choice);
			b.clip.setMicrosecondPosition(nowFrame);
			b.clip.start();
			stopsong = false;
			keyH.goOn = false;
			keyH.goOnrelease = true;
			keyH.escapekey = false;
		}
		// 開始歌曲 移動樂譜 樂譜速度
		if (keyH.gameStart == true) {
			if (stopsong == false) {
				b.clip.start();
				Interlude++;
				// 間奏時間
				if (Interlude > songArg[choice][0]) {
					relocate += speed * speedcountrol;
				}
			}
		}
		// 重新開始歌曲
		if (keyH.renew == true) {
			b.clip.close();
			createtestsong2(choice);
			getUDLRImage();
			loadmusicmap();
			missCount = 0;
			badCount = 0;
			goodCount = 0;
			greatCount = 0;
			perfectCount = 0;
			marvelousCount = 0;
			combo = 0;
			keyH.gameStart = true;
			stopsong = false;
			keyH.renew = false;
			relocate = 0;
			Interlude = 0;
			judg = 0;

		}
		// collideL是音符進入範圍得時候
//		collideLConfirm為碰撞判定成功判定
		if (keyH.lpress == true) {
			keyH.lpress = false;
			broad(48);
			if (collideL == true) {

				collideLConfirm = true;
			}
		}
		if (keyH.dpress == true) {
			keyH.dpress = false;
			broad(48);
			if (collideD == true) {

				collideDConfirm = true;
			}
		}
		if (keyH.tpress == true) {
			keyH.tpress = false;
			broad(48);
			if (collideT == true) {

				collideTConfirm = true;
			}
		}
		if (keyH.rpress == true) {
			keyH.rpress = false;
			broad(48);
			if (collideR == true) {

				collideRConfirm = true;
			}
		}
		// 歌曲結束挑轉畫面
		if (songOver() == true) {
			int scores=marvelousCount*1000+perfectCount*700+greatCount*500+goodCount*300;
			new SaveScores(gp.myId,gp.sS.songName[gp.son1.choice],scores,marvelousCount,perfectCount 
					, goodCount, greatCount,missCount);
			gp.startplay = false;
			gp.SingOverThemeSwitch = true;
			try {
				SaveScores.getHistory(gp.myId);
			} catch (SQLException e) {
				System.out.println(e);
			
			}
		}
	}

//確認歌曲結束
	public boolean songOver() {
		if (b.clip.getMicrosecondLength() == b.clip.getMicrosecondPosition()) {
			b.clip.close();
			gp.SingOverThemeSwitch = true;
			keyH.goOn = false;
			return true;
		}
		return false;
	}

//命中計算
	public void countodo() {
		switch (judg) {
		case 1:
			marvelousCount++;
			break;
		case 2:
			perfectCount++;
			break;
		case 3:
			greatCount++;
			break;
		case 4:
			goodCount++;
			break;
		case 5:
			badCount++;
			break;
		}
		judg = 0;
	}
	
	
	
//繪製開始
	
	
	
	public void draw(Graphics2D g2) {
	
		// 設置背景圖片
		if (keyH.gameStart == true) {
			if (stopsong == false) {
				g2.drawImage(songImg[choice], 0, 0, 48 * 30, 48 * 16, null);
			}
		}
		// 設置顏色
		g2.setColor(c);
		g2.fillRect(0, 0, 400, 800);
		// 顯示判定
		switch (judg2) {
		case 1:
			g2.drawImage(marvelous, -20, 350, 500, 50, null);

			break;
		case 2:
			g2.drawImage(perfect, -20, 350, 500, 50, null);

			break;
		case 3:
			g2.drawImage(great, -20, 350, 500, 50, null);

			break;
		case 4:
			g2.drawImage(good, -20, 350, 500, 50, null);

			break;
		case 5:
			g2.drawImage(bad, -20, 350, 500, 50, null);

			break;
		case 6:
			g2.drawImage(miss, -20, 350, 500, 50, null);

			break;

		}
		int Col = 0;
		int Row = 0;
//碰撞判定後計算判定&畫面改變 
		while (Col < 5 && Row < rowRange) {

			int tileNum = mapTileNum[Col][Row];
			screenX = Col * 100;
			screenY = Row * songArg[choice][1] * speedcountrol + songArg[choice][2] * speedcountrol - relocate;

			if (screenX == 0 && screenY < 56 && screenY > -56) {
				collideL = true;
				if (collideLConfirm == true) {

					if (screenY < 14 && screenY > -12 && mapTileNum[Col][Row] != 0) {
						judg = 1;
						judg2 = 1;
						g2.drawImage(lightingN, 0, -28, 100, 200, null);

					} else if (screenY < 28 && screenY > -24 && mapTileNum[Col][Row] != 0) {
						judg = 2;
						judg2 = 2;
						g2.drawImage(lightingN, 0, -28, 100, 200, null);

					} else if (screenY < 42 && screenY > -36 && mapTileNum[Col][Row] != 0) {
						judg = 3;
						judg2 = 3;
						g2.drawImage(lightingN, 0, -28, 100, 200, null);

					} else if (screenY < 56 && screenY > -48 && mapTileNum[Col][Row] != 0) {
						judg = 4;
						judg2 = 4;
						g2.drawImage(lightingN, 0, -28, 100, 200, null);
					}

					countodo();
					mapTileNum[Col][Row] = 0;
					tileNum = 0;
					collideLConfirm = false;
				}
			}

			if (screenX == 100 && screenY < 56 && screenY > -56) {
				collideD = true;
				if (collideDConfirm == true) {

					if (screenY < 14 && screenY > -12 && mapTileNum[Col][Row] != 0) {
						judg = 1;
						judg2 = 1;
						g2.drawImage(lightingN, 100, -28, 100, 200, null);
					} else if (screenY < 28 && screenY > -24 && mapTileNum[Col][Row] != 0) {
						judg = 2;
						judg2 = 2;
						g2.drawImage(lightingN, 100, -28, 100, 200, null);
					} else if (screenY < 42 && screenY > -36 && mapTileNum[Col][Row] != 0) {
						judg = 3;
						judg2 = 3;
						g2.drawImage(lightingN, 100, -28, 100, 200, null);
					} else if (screenY < 56 && screenY > -48 && mapTileNum[Col][Row] != 0) {
						judg = 4;
						judg2 = 4;
						g2.drawImage(lightingN, 100, -28, 100, 200, null);
					}
					countodo();
					mapTileNum[Col][Row] = 0;
					tileNum = 0;
					collideDConfirm = false;

				}
			}
			if (screenX == 200 && screenY < 70 && screenY > -56) {
				collideT = true;
				if (collideTConfirm == true) {

					if (screenY < 14 && screenY > -12 && mapTileNum[Col][Row] != 0) {
						judg = 1;
						judg2 = 1;
						g2.drawImage(lightingN, 200, -28, 100, 200, null);
					} else if (screenY < 28 && screenY > -24 && mapTileNum[Col][Row] != 0) {
						judg = 2;
						judg2 = 2;
						g2.drawImage(lightingN, 200, -28, 100, 200, null);
					} else if (screenY < 42 && screenY > -36 && mapTileNum[Col][Row] != 0) {
						judg = 3;
						judg2 = 3;
						g2.drawImage(lightingN, 200, -28, 100, 200, null);
					} else if (screenY < 56 && screenY > -48 && mapTileNum[Col][Row] != 0) {
						judg = 4;
						judg2 = 4;
						g2.drawImage(lightingN, 200, -28, 100, 200, null);
					}

					countodo();
					mapTileNum[Col][Row] = 0;
					tileNum = 0;
					collideTConfirm = false;
				}
			}
			if (screenX == 300 && screenY < 56 && screenY > -56) {
				collideR = true;
				if (collideRConfirm == true) {

					if (screenY < 14 && screenY > -12 && mapTileNum[Col][Row] != 0) {
						judg = 1;
						judg2 = 1;
						g2.drawImage(lightingN, 300, -28, 100, 200, null);
					} else if (screenY < 28 && screenY > -24 && mapTileNum[Col][Row] != 0) {
						judg = 2;
						judg2 = 2;
						g2.drawImage(lightingN, 300, -28, 100, 200, null);
					} else if (screenY < 42 && screenY > -36 && mapTileNum[Col][Row] != 0) {
						judg = 3;
						judg2 = 3;
						g2.drawImage(lightingN, 300, -28, 100, 200, null);
					} else if (screenY < 56 && screenY > -48 && mapTileNum[Col][Row] != 0) {
						judg = 4;
						judg2 = 4;
						g2.drawImage(lightingN, 300, -28, 100, 200, null);
					}
					countodo();
					mapTileNum[Col][Row] = 0;
					tileNum = 0;
					collideRConfirm = false;

				}
			}
			if (tileNum != 0 && screenY < -57 && screenY > (-58 - songArg[choice][3])) {
				judg = 6;
				judg2 = 6;
				missCount++;

			}
			if (screenY < 3000 && screenY > -65) {
				if (tileNum != 0) {
					g2.drawImage(tile[tileNum].image, screenX, screenY, 100, 100, null);
				}

			}

			Col++;

			if (Col == 4) {
				Col = 0;
				Row++;
			}

		}
		g2.setColor(Color.black);		
		g2.fillRoundRect(1210, 10, 100,40, 35, 35);
		g2.setColor(Color.white);
		Font f = new Font("Serif",Font.BOLD,30);
	    g2.setFont(f);
		g2.drawString("skip  ", 1223,37);
		
	}

}
