package BrickBreaker;

import javax.swing.JFrame;

public class Main_play {

	public static void main(String[] args) {
			
			JFrame obj = new JFrame();
			Frame_Game gameplay = new Frame_Game();
			obj.setBounds(10,10,1015,650);
			obj.setTitle("Brick Breaker");
			obj.setResizable(false);
			obj.setVisible(true);
			obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			obj.add(gameplay);
		}
	
}
