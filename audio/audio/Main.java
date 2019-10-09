package audio;



public class Main {

	public static void main(String[] args) {

		Keyboard k = new Keyboard();
		System.out.println(k);
		Thread keyboard = 
				new Thread(k);
		Thread sound = new Thread(new Sound(k));
		keyboard.start();
		sound.start();
		
		try {
			keyboard.join();
			sound.join();
		}catch(Exception e) {e.printStackTrace();}
	
			
		
	}
}

/*
 *TODO: 
 * - DO PROPER NOTE MAPPING  TO KEYBOARD -> SOUND, FIX NOTE LEVELS i.e. if its A5 or A4 or A3 etc...
 * - ADD Keyboard to Note name mapping. (Allow user to modify it in the future if implemented this way?)
 *x
 *x
 */