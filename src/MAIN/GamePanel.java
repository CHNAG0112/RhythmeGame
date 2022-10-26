package MAIN;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import textAdventureGame.Scene01;
import tile.History;
import tile.MusicFront;
import tile.SingOverTheme;
import tile.Musicpanel;
import tile.SelectSongScene;
import tile.Song;
public class GamePanel extends JPanel implements Runnable {

//screen settings
	public final int originalTitleSize = 16;
//	16X16 tile
	final int scale = 3;
	public final int tileSize = originalTitleSize * scale; // 48*48tile
	public final int maxScreenCol = 30;
	public final int maxScreenRow = 16;
	public final int screenWidth = tileSize * maxScreenCol; // 30*48pixels 橫向有30格
	public final int screenHeight = tileSize * maxScreenRow; // 16*48pixels 縱向有16格
// WORLD SETTING
	private JTextField account, password;
	private JButton logIn, reg;
	public final int maxWorldCol = 50;
	public final int maxWorldRow = 50;
	public final int worldWidth = tileSize * maxWorldCol;
	public final int worldHeight = tileSize * maxWorldRow;
	public boolean startplay = false;
	public boolean SingOverThemeSwitch = false;
	public boolean scene01Switch = false;
	public boolean historySwitch = false;
    public String nowAccount;
    public int myId;
    
    
	public int FPS = 120;


	KeyHandler keyH = new KeyHandler();
	MouseEvent mouseevent = new MouseEvent();
//Thread is something you can stop and start 
//once thread start it keeps your  programming started  until you stop it 	
	Thread gameThread;

	public Musicpanel mus = new Musicpanel(this, keyH);
	public Song son1 = new Song(this, keyH,mouseevent);
	public Scene01 scene01 = new Scene01(this, keyH, son1);
	public MusicFront MF = new MusicFront(this, keyH);
	public SingOverTheme som = new SingOverTheme(son1, this, keyH, mouseevent);
	public SelectSongScene sS = new SelectSongScene(this, keyH, son1);
	public History  history=new History(this,keyH,mouseevent);	
	public CheckAccount ca;
	public boolean enterGame = false;	

	public GamePanel() {
//登入系統
		this.setLayout(null);
		account = new JTextField("帳號");
		account.setBounds(600, 200, 200, 30);
		password = new JTextField("密碼");
		password.setBounds(600, 250, 200, 30);
		account.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				if (account.getText().isEmpty()) {
					account.setForeground(Color.GRAY);
					account.setText("帳號");
				}
			}

			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub

				if (account.getText().equals("帳號")) {
					account.setText("");
					account.setForeground(Color.BLACK);
				}
			}
		});
		password.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				if (password.getText().isEmpty()) {
					password.setForeground(Color.GRAY);
					password.setText("密碼");
				}
			}

			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub

				if (password.getText().equals("密碼")) {
					password.setText("");
					password.setForeground(Color.BLACK);
				}
			}
		});

		logIn = new JButton("登入");
		reg = new JButton("註冊");
		logIn.requestFocus();
		reg.requestFocus();
		logIn.setBounds(600, 290, 70, 30);
		reg.setBounds(730, 290, 70, 30);
		JPanel regPanel = new JPanel();
		reg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MysqlSaveData msd = new MysqlSaveData(account.getText(), password.getText());               
			}
		});
//		this.add(jLabel);
		this.add(logIn);
		this.add(reg);
		this.add(account);
		this.add(password);
         //登入按鈕
		logIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("aaa");
				ca = new CheckAccount(account.getText(), password.getText());
				if (ca.enterGame == true) {
					myId=ca.Id;
					startGameThread();
					scene01Switch = true;
					enterGame = true;
					logIn.setVisible(false);
					reg.setVisible(false);
					account.setVisible(false);
					password.setVisible(false);
				    keyH.skip=false;
				    nowAccount=account.getText();
				}
			}
		});

		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
//		this.setBackground(Color.black);
//if set  to true all the drawing from this component will be done in an offscreen  painting buffer
		this.setDoubleBuffered(true);
//		把  KeyHandler 加到 gamepanel 讓 這裡可以監聽按鍵事件 
		this.addKeyListener(keyH);
		this.addMouseListener(mouseevent);
		this.addMouseMotionListener(mouseevent);
		this.setFocusable(true);
		this.setVisible(true);

	}

	// panel 控制板 instantiate實例化 rendering 渲染
	public void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
	}

//在run method 放置GAME LOOP 遊戲會在迴圈Update()和repaint()內不斷更新 更新位置 更新圖片
	@Override
	public void run() {
		// TODO Auto-generated method stub
		double drawInterval = 1000000000 / FPS; // 0.0166seconds
		double nextDrawTime = System.nanoTime() + drawInterval;
		while (gameThread != null) {
			update();
			repaint();
			try {
				double remainingTime = nextDrawTime - System.nanoTime();
				remainingTime = remainingTime / 1000000;
				if (remainingTime < 0) {
					remainingTime = 0;
				}
				Thread.sleep((long) remainingTime);
				nextDrawTime += drawInterval;
			} catch (InterruptedException e) {			
				e.printStackTrace();
			}
		}

	}

//	JAVA定位 左上為0,0 向右向下增加
	public void update() {
		if (scene01Switch == true) {
			scene01.update();
		}           		
		
		if (startplay == true && SingOverThemeSwitch == false && scene01Switch == false) {
			mus.update();
			son1.update();
		} else if (startplay == false && SingOverThemeSwitch == false && scene01Switch == false) {
			sS.update();
		} else if (SingOverThemeSwitch == true && startplay == false && scene01Switch == false && historySwitch==false) {
			som.update();
		}
		else if(historySwitch==true
				) {try {
					history.update();
				} catch (SQLException e) {
					
					e.printStackTrace();
				}}
	}


//	把圖畫到指定位置 
	public void paintComponent(Graphics g) {

//	放在越後面的圖層越高 前面的會被蓋掉
       
		if (enterGame == true) {
			super.paintComponent(g);
            
			Graphics2D g2 = (Graphics2D) g;
			
			//場景畫面
			if (scene01Switch == true) {
				scene01.draw(g2);
			}	
			
            //歌曲遊戲畫面
			if (startplay == true && SingOverThemeSwitch == false && scene01Switch == false ) {
				son1.draw(g2);
				mus.draw(g2);
			//歌曲選擇畫面
			} else if (startplay == false && SingOverThemeSwitch == false && scene01Switch==false ) {
				sS.draw(g2);
				
		    //歌曲結算畫面		
			} else if (SingOverThemeSwitch == true && startplay == false && scene01Switch == false && historySwitch==false) {
				som.draw(g2);
			}
			//歷史紀錄畫面
			else if(historySwitch==true
					) {history.draw(g2);}
			
			g2.dispose();
		}
	}
}
