package audio;

import java.awt.event.*;
import javax.swing.*;


public class Keyboard extends KeyAdapter implements KeyToSound, Runnable{
	
	
	public Keyboard() {
	}
	
	public Boolean insert(String s) {return buffer.add(s);}
	public String read() {return buffer.remove();}
	public String peek() {return buffer.peek();}

	
	
	@Override
	public void keyReleased(KeyEvent e) {}
	
	@Override
	public void keyTyped(KeyEvent e) {}
	
	@Override
	public void keyPressed(KeyEvent e)
	{
		lock.lock();
		
		String note = null;
		
		System.out.println(buffer);
		switch(e.getKeyCode()) {
		
			case KeyEvent.VK_A:
				System.out.println("A pressed");
				break;
			case KeyEvent.VK_B:
				note = "G4";
				break;
			case KeyEvent.VK_C:
				System.out.println("C pressed");
				note = "E4";
				break;
			case KeyEvent.VK_D:
				note = "D#4";
				System.out.println("D pressed");
				break;
			case KeyEvent.VK_E:
				note = "E5";
				System.out.println("E pressed");
				break;
			case KeyEvent.VK_F:
				System.out.println("F pressed");
				break;
			case KeyEvent.VK_G:
				note = "F#4";
				System.out.println("G pressed");
				break;
			case KeyEvent.VK_H:
				note = "G#4";
				System.out.println("H pressed");
				break;
			case KeyEvent.VK_I:
				note = "C6";
				System.out.println("I pressed");
				break;
			case KeyEvent.VK_J:
				note = "A#4";
				System.out.println("J pressed");
				break;
			case KeyEvent.VK_K:
				System.out.println("K pressed");
				break;
			case KeyEvent.VK_L:
				note = "C#5";
				System.out.println("L pressed");
				break;
			case KeyEvent.VK_M:
				note = "B4";
				System.out.println("M pressed");
				break;
			case KeyEvent.VK_N:
				note = "A4";
				System.out.println("N pressed");
				break;
			case KeyEvent.VK_O:
				note = "D6";
				System.out.println("O pressed");
				break;
			case KeyEvent.VK_P:
				note = "E6";
				System.out.println("P pressed");
				break;
			case KeyEvent.VK_Q:
				note = "C5";
				System.out.println("Q pressed");
				break;
			case KeyEvent.VK_R:
				note = "F5";
				System.out.println("R pressed");
				break;
			case KeyEvent.VK_S:
				note = "C#4";
				System.out.println("S pressed");
				break;
			case KeyEvent.VK_T:
				note = "G5";	
				System.out.println("T pressed");
				break;
			case KeyEvent.VK_U:
				note = "B5";
				System.out.println("U pressed");
				break;
			case KeyEvent.VK_V:
				note = "F4";
				System.out.println("V pressed");
				break;
			case KeyEvent.VK_W:
				note = "D5";
				System.out.println("W pressed");
				break;
			case KeyEvent.VK_X:
				note = "D4";
				System.out.println("X pressed");
				break;
			case KeyEvent.VK_Y:
				note = "A5";
				System.out.println("Y pressed");
				break;
			case KeyEvent.VK_Z:
				note = "C4";
				System.out.println("Z pressed");
				break;
			case KeyEvent.VK_1:
				System.out.println("1 pressed");
				break;
			case KeyEvent.VK_2:
				note = "C#5";
				System.out.println("2 pressed");
				break;
			case KeyEvent.VK_3:
				note = "D#5";
				System.out.println("3 pressed");
				break;
			case KeyEvent.VK_4:
				System.out.println("4 pressed");
				break;
			case KeyEvent.VK_5:
				note = "F#5";
				System.out.println("5 pressed");
				break;
			case KeyEvent.VK_6:
				note = "G#5";
				System.out.println("6 pressed");
				break;
			case KeyEvent.VK_7:
				note = "A#5";
				System.out.println("7 pressed");
				break;
			case KeyEvent.VK_8:
				System.out.println("8 pressed");
				break;
			case KeyEvent.VK_9:
				note = "C#6";
				System.out.println("9 pressed");
				break;
			case KeyEvent.VK_0:
				note = "D#6";
				System.out.println("0 pressed");
				break;
			case KeyEvent.VK_COMMA:
				note = "C5";
				System.out.println(", pressed");
				break;
			case KeyEvent.VK_PERIOD:
				note = "D5";
				break;
			case KeyEvent.VK_BACK_SLASH:
				note = "E5";
				break;
			case KeyEvent.VK_OPEN_BRACKET:
				note = "F6";
				break;
			case KeyEvent.VK_CLOSE_BRACKET:
				note = "G6";
				break;
			
			default: break;
		}
		
		System.out.println(note);
		
		if(note != null)
			this.insert(note);
		
		c_buffer.signal();
		lock.unlock();
	}
	
	public void run() {
		
		JFrame frame = new JFrame("Test");
//		frame.pack();
//		frame.setSize(new Dimension(300,200));
		frame.setVisible(true);
		Keyboard test = new Keyboard();
		frame.addKeyListener(test);
		
	}

}
