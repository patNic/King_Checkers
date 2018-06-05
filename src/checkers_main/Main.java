package checkers_main;

import java.io.IOException;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import checkers_gui.Launcher;

public class Main {
	public static AudioInputStream audioInputStream;
    public static Clip clip;
    public static Launcher l;
	public static void main(String[] args) {
		l = new Launcher();
		playAudio(l);
	}
	public static void playAudio(Launcher launch) {
		 try {
	         URL url = launch.getClass().getClassLoader().getResource("ot_soundtrack.wav");
	         audioInputStream = AudioSystem.getAudioInputStream(url);
	         clip = AudioSystem.getClip();
	         clip.open(audioInputStream);
	         clip.loop(Clip.LOOP_CONTINUOUSLY);
	      } catch (UnsupportedAudioFileException e) {
	         e.printStackTrace();
	      } catch (IOException e) {
	         e.printStackTrace();
	      } catch (LineUnavailableException e) {
	         e.printStackTrace();
	      }
		
	}
}