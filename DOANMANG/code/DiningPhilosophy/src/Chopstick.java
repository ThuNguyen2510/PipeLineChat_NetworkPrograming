import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

public class Chopstick {
	private int id;
	private static int xpos[] = new int[5];
	private static int ypos[] = new int[5];

	public Semaphore mutex = new Semaphore(1);

	void grab() {
		try {
			mutex.acquire();
		} catch (Exception e) {
			e.printStackTrace(System.out);
		}
	}

	void release() {
		mutex.release();
	}

	boolean isFree() {
		return mutex.availablePermits() > 0;
	}

	public Chopstick() {

	}

	public Chopstick(int id, int xpos, int ypos) {
		this.id = id;
		this.xpos[id] = xpos;
		this.ypos[id] = ypos;
	}

	public static void setAxis(int id, int x, int y) {
		xpos[id] = x;
		ypos[id] = y;
	}

	public int getXpos(int id) {
		return xpos[id];
	}

	public int getYpos(int id) {
		return ypos[id];
	}

}
