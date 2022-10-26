package tile;


//播放載入音樂
import java.io.IOException;

import java.net.URL;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Loadmusic   {
public	Clip clip;
public FloatControl gainControl;
	URL[] t = new URL[50];


	public void runtest(int i) {
		switch (i) {
		case 0:
			t[0] = getClass().getResource("/songs/ra.wav/");
			break;
			
		case 1:
			t[1] = getClass().getResource("/songs/rap.wav/");
			break;
			
		case 2:
			t[2] = getClass().getResource("/songs/fly.wav/");
			break;
			
		case 3:
			t[3] = getClass().getResource("/songs/rain.wav/");
			break;
			
		case 4:
			t[4] = getClass().getResource("/songs/akane.wav/");
			break;
			
		case 5:
			t[5] = getClass().getResource("/songs/hero.wav/");
			break;
		case 6:
			t[6] = getClass().getResource("/songs/skull.wav/");
			break;			
		case 7:
			t[7] = getClass().getResource("/songs/boys.wav/");
			break;	
		case 8:
			t[8] = getClass().getResource("/songs/ahoy.wav/");
			break;	
		case 48:
			t[48] = getClass().getResource("/musicgredients/nc.wav/");
			break;	
		}			

		try {
			Sound2(i);
		} catch (Exception e) {}
		
	}

	public void Sound2(int i) throws UnsupportedAudioFileException, IOException {
		AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(t[i]);
		AudioFormat audioFormat = audioInputStream.getFormat();
		int bufferSize = (int) Math.min(audioInputStream.getFrameLength() * audioFormat.getFrameSize(),
				Integer.MAX_VALUE); // 緩衝大小，如果音訊檔案不大，可以全部存入緩衝空間。
		DataLine.Info dataLineInfo = new DataLine.Info(Clip.class, audioFormat, bufferSize);
		 clip = null;
		try {
			clip = (Clip) AudioSystem.getLine(dataLineInfo);			
			clip.open(audioInputStream);	
			audioInputStream.close();
			gainControl =(FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			gainControl.setValue(0.0f);
		} catch (Exception e) {			
		}		
		
	}

	public void enhanceVolume() {
		gainControl =(FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
		gainControl.setValue(0.0f);
	}

	
}

