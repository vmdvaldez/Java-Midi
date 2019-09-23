package audio;

import java.util.LinkedList;
import java.util.Queue;

public interface KeyToSound {
	
	Queue<Character> buffer = new LinkedList<Character>();
	
	public Boolean insert(char c);
	public Character read();
	public Character peek();
	
	
}
