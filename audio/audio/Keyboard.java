package audio;

import java.awt.event.*;
import javax.swing.*;


public class Keyboard  implements KeyToSound{
	
	
	public Keyboard() {
	}
	
	public Boolean insert(String s) {return buffer.add(s);}
	public String read() {return buffer.remove();}
	public String peek() {return buffer.peek();}


	public void map2sound(String key)
	{
		lock.lock();
		String note = null;
		System.out.println(buffer);
		System.out.println(key);
		switch(key) {
		
			case "a":
				break;
			case "b":
				note = "G4";
				break;
			case "c":
				note = "E4";
				break;
			case "d":
				note = "D#4";
				break;
			case "e":
				note = "E5";
				break;
			case "f":
				break;
			case "g":
				note = "F#4";
				break;
			case "h":
				note = "G#4";
				break;
			case "i":
				note = "C6";
				break;
			case "j":
				note = "A#4";
				break;
			case "k":
				break;
			case "l":
				note = "C#5";
				break;
			case "m":
				note = "B4";
				break;
			case "n":
				note = "A4";
				break;
			case "o":
				note = "D6";
				break;
			case "p":
				note = "E6";
				break;
			case "q":
				note = "C5";
				break;
			case "r":
				note = "F5";
				break;
			case "s":
				note = "C#4";
				break;
			case "t":
				note = "G5";	
				break;
			case "u":
				note = "B5";
				break;
			case "v":
				note = "F4";
				break;
			case "w":
				note = "D5";
				break;
			case "x":
				note = "D4";
				break;
			case "y":
				note = "A5";
				break;
			case "z":
				note = "C4";
				break;
			case "1":
				break;
			case "2":
				note = "C#5";
				break;
			case "3":
				note = "D#5";
				break;
			case "4":
				break;
			case "5":
				note = "F#5";
				break;
			case "6":
				note = "G#5";
				break;
			case "7":
				note = "A#5";
				break;
			case "8":
				break;
			case "9":
				note = "C#6";
				break;
			case "0":
				note = "D#6";
				break;
			case ",":
				note = "C5";
				break;
			case ".":
				note = "D5";
				break;
			case "/":
				note = "E5";
				break;
			case "[":
				note = "F6";
				break;
			case "]":
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
	
}
