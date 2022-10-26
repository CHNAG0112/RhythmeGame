package tile;

import java.awt.Font;
import java.awt.Graphics2D;
import java.sql.SQLException;

import MAIN.GamePanel;
import MAIN.KeyHandler;
import MAIN.MouseEvent;

public class History {
	GamePanel gp;
	KeyHandler keyh;
	public boolean firstIn;
	MouseEvent mouseevent;
	int relocation;

	public History(GamePanel gp, KeyHandler keyh, MouseEvent mouseevent) {
		this.gp = gp;
		this.keyh = keyh;
		this.mouseevent = mouseevent;

	}

	public void update() throws SQLException {
//		if (firstIn == false) {
//			SaveScores.getHistory(gp.myId);
//			firstIn = true;
//		}
		// 返回按鈕
		if (mouseevent.mousex < 1390 && mouseevent.mousex > 1250 && mouseevent.mousey > 10 && mouseevent.mousey < 80) {
			gp.historySwitch = false;
		}
		if (keyh.upPressed == true) {
			relocation -= 20;
			keyh.upPressed = false;
		}
		if (keyh.downPressed == true) {
			relocation += 20;
			keyh.downPressed = false;
		}

	}

	public void draw(Graphics2D g2) {
		g2.drawImage(gp.som.settleImg[15], 0, 0, 48 * 30, 48 * 16, null);
//		g2.drawString(gp.sS.songName[gp.son1.choice], 0, 0);
		Font f = new Font("Dialog", Font.BOLD, 20);
		g2.setFont(f);

		for (int i = 0; i < gp.som.ss.table.size(); i++) {
			for (int j = 1; j < gp.som.ss.table.get(i).size(); j++) {
				if ((100 + 40 * i + relocation + 50) > 100) {
					g2.drawString(gp.som.ss.table.get(i).get(j), 200 * (j - 1), 100 + 40 * i + relocation + 50);
				}
			}
		}
		g2.drawString("Songnames", 0, 100);
		g2.drawString("scores", 200, 100);
		g2.drawString("marvelousCount", 400, 100);
		g2.drawString("perfectCount", 600, 100);
		g2.drawString("goodCount", 800, 100);
		g2.drawString("greatCount", 1000, 100);
		g2.drawString("missCount", 1200, 100);
		g2.drawImage(gp.som.settleImg[13], 1250, 10, 140, 70, null);
		f = new Font("Dialog", Font.BOLD, 30);
		g2.setFont(f);
		// 帳號顯示
		g2.drawString(gp.nowAccount, 0, 50);

	}
}
