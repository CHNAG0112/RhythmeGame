package MAIN;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

//按鍵偵測
public class KeyHandler implements KeyListener {
	public boolean upPressed, downPressed, leftPressed, rightPressed, leftA, downS, topW, RightD, gameStart;

	public boolean renew, lpress, lrelease, dpress, drelease, tpress, trelease, rpress, rrelease;
	public boolean escapekey, goOn, goOnrelease, upPressjudge, downPressjudge,textGoOn,reTextRefrain,skip;

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

		int code = e.getKeyCode();
		if (code == KeyEvent.VK_ESCAPE) {
			escapekey = true;
		}
		if (code == KeyEvent.VK_ENTER) {
			//歌曲進行開關
			goOn = true;
			//
			if(reTextRefrain==false)
			{textGoOn=true;}
			
		}
		if (code == KeyEvent.VK_UP) {
			if (upPressjudge == false) {
				upPressed = true;
				upPressjudge = true;
			}

		}
		if (code == KeyEvent.VK_DOWN) {
			if (downPressjudge == false) {
				downPressed = true;
				downPressjudge = true;
			}
			downPressed = true;

		}

		if (code == KeyEvent.VK_LEFT) {
			leftPressed = true;

		}
		if (code == KeyEvent.VK_Z) {
			skip=true;

		}
		if (code == KeyEvent.VK_RIGHT) {
			rightPressed = true;
		}

		if (code == KeyEvent.VK_A) {
			leftA = true;
			if (lpress == false && lrelease == false) {
				lpress = true;
				lrelease = true;
			}
		}
		if (code == KeyEvent.VK_S) {
			downS = true;
			if (dpress == false && drelease == false) {
				dpress = true;
				drelease = true;
			}
		}
		if (code == KeyEvent.VK_K) {
			topW = true;
			if (tpress == false && trelease == false) {
				tpress = true;
				trelease = true;
			}
		}
		if (code == KeyEvent.VK_L) {
			RightD = true;
			if (rpress == false && rrelease == false) {
				rpress = true;
				rrelease = true;
			}
		}
		if (code == KeyEvent.VK_B) {
			renew = true;
		}
		if (code == KeyEvent.VK_N) {
			if (gameStart == true) {
				gameStart = false;
			}
			;
			if (gameStart == false) {
				gameStart = true;
			}
			;

		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		int code = e.getKeyCode();

		if (code == KeyEvent.VK_UP) {
			upPressed = false;
			upPressjudge = false;
		}
		if (code == KeyEvent.VK_ENTER) {
			
			goOnrelease = false;
			textGoOn=false;
			reTextRefrain=false;
		}
		if (code == KeyEvent.VK_DOWN) {
			downPressed = false;
			downPressjudge = false;
		}

		if (code == KeyEvent.VK_LEFT) {
			leftPressed = false;
		}
		if (code == KeyEvent.VK_RIGHT) {
			rightPressed = false;
		}

		if (code == KeyEvent.VK_A) {
			leftA = false;
			lrelease = false;
		}
		if (code == KeyEvent.VK_S) {
			downS = false;
			drelease = false;
		}
		if (code == KeyEvent.VK_K) {
			topW = false;
			trelease = false;
		}
		if (code == KeyEvent.VK_L) {
			RightD = false;
			rrelease = false;
		}

	}

}
