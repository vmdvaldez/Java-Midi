package audio;
import java.util.concurrent.locks.*;
import java.util.LinkedList;
import java.util.Queue;

public interface SoundDriver {
	
	Queue<String> buffer = new LinkedList<String>();
	Lock lock = new ReentrantLock();
	Condition c_buffer = lock.newCondition();
	
	public Boolean insert(String s);
	public String read();
	public String peek();
	
	
}
