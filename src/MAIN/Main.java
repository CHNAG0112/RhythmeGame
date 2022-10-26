package MAIN;

import java.awt.BorderLayout;
import java.awt.LayoutManager;

import javax.swing.JFrame;

public class Main {

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
     JFrame window = new JFrame();
     window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     window.setResizable(false); 
     window.setTitle("Lost song");     
    setLayout(new BorderLayout());  
     
     GamePanel gamePanel =new GamePanel();
     window.add(gamePanel,BorderLayout.CENTER); 
   
     window.pack();
     
     window.setLocationRelativeTo(null);
     window.setVisible(true);
//     gamePanel.startGameThread();
     
	}

	private static void setLayout(BorderLayout borderLayout) {
		// TODO Auto-generated method stub
		
	}

}
