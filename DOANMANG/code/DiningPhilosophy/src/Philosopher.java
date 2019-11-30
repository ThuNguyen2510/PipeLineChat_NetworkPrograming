import java.util.Random;
import java.util.concurrent.locks.Lock;

public class Philosopher implements Runnable {
	private int id;
	final static int THINKING = 0;
	final static int EATING = 3;
	final static int LEFT_BUSY = 2;
	final static int RIGHT_BUSY = 1;

	private Chopstick leftChopstick;
	private Chopstick rightChopstick;
	private static int noMeals[] = new int[5];
	private Random timeGenerator = new Random();
	private static int state[] = new int[5];
	private static int quote[] = new int[5];
	private Chopstick chopstick = new Chopstick();

	public Philosopher() {

	}

	public Philosopher(int id, Chopstick leftChopstick, Chopstick rightChopstick) {
		this.id = id;
		this.leftChopstick = leftChopstick;
		this.rightChopstick = rightChopstick;
	}

	public void run() {
		try {
			while (true) {
				think();
				pickUpChopstick(leftChopstick,rightChopstick);
				eat();
				putDownChopsticks();			
			}
		} catch (InterruptedException e) {
			System.out.println("Philosopher " + id + " was interrupted.\n");
		}

	}

	private void think() throws InterruptedException {
		System.out.println("Philosopher " + id + " is thinking.\n");
		System.out.flush();
		if (id == 0) {
			state[0] = 4;
		}
		if (id == 1) {
			state[1] = 4;
		}
		if (id == 2) {
			state[2] = 4;
		}
		if (id == 3) {
			state[3] = 4;
		}
		if (id == 4) {
			state[4] = 4;
		} //
		Thread.sleep(2000 + timeGenerator.nextInt(1000));
	}

	private void pickUpChopstick(Chopstick leftChopstick, Chopstick rightChopstick) {
		
		while (true) {
			
			try {
				leftChopstick.grab();
				rightChopstick.grab();
			} finally {
				if (!leftChopstick.isFree() && !rightChopstick.isFree()) {
					System.out.println("Philosopher " + id + " Pick Up both Chop Stick.\n");
					quote[id] = 3;
					return;
				}
				if (!leftChopstick.isFree()) {
					System.out.println("Philosopher " + id + " Pick Up  leftChop Stick And RightChop Stick is Busy.\n");
					leftChopstick.release();
					System.out.println("Philosopher " + id + " Put Down  leftChop Stick.\n");
					quote[id] = 1;
				}
				if (!rightChopstick.isFree()) {
					System.out.println("Philosopher " + id + " Pick Up  RightChop Stick And lefttChop Stick is Busy.\n");
					rightChopstick.release();
					System.out.println("Philosopher " + id + " Put Down  RightChop Stick.\n");
					quote[id] = 2;
				}
			}

			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	private void eat() throws InterruptedException {
		Thread.sleep(500);
		quote[id] = 0;
		increment(id);
		System.out.println("Philosopher " + id + " is eating.\n");
		if (id == 0) {
			chopstick.setAxis(0, 340, 470);
			chopstick.setAxis(4, 383, 475);
			state[0] = 0;
		}
		if (id == 1) {
			chopstick.setAxis(0, 200, 355);
			chopstick.setAxis(1, 180, 310);
			state[1] = 0;
		}
		if (id == 2) {
			chopstick.setAxis(1, 250, 140);
			chopstick.setAxis(2, 280, 110);
			state[2] = 0;
		}
		if (id == 3) {
			chopstick.setAxis(2, 520, 120);
			chopstick.setAxis(3, 550, 150);
			state[3] = 0;
		}
		if (id == 4) {
			chopstick.setAxis(3, 585, 340);
			chopstick.setAxis(4, 560, 375);
			state[4] = 0;
		}
		System.out.flush();
		Thread.sleep(400);
		//
		if (id == 0) {
			state[0] = 1;
		}
		if (id == 1) {
			state[1] = 1;
		}
		if (id == 2) {
			state[2] = 1;
		}
		if (id == 3) {
			state[3] = 1;
		}
		if (id == 4) {
			state[4] = 1;
		}
		//
		Thread.sleep(400);
		//
		if (id == 0) {
			state[0] = 2;
		}
		if (id == 1) {
			state[1] = 2;
		}
		if (id == 2) {
			state[2] = 2;
		}
		if (id == 3) {
			state[3] = 2;
		}
		if (id == 4) {
			state[4] = 2;
		} //

		//
		Thread.sleep(400);
		//
		if (id == 0) {
			state[0] = 3;
		}
		if (id == 1) {
			state[1] = 3;
		}
		if (id == 2) {
			state[2] = 3;
		}
		if (id == 3) {
			state[3] = 3;
		}
		if (id == 4) {
			state[4] = 3;
		} //

		Thread.sleep(400 + timeGenerator.nextInt(200));

	}

	private void putDownChopsticks() {
		leftChopstick.release();
		rightChopstick.release();
		System.out.println("Philosopher " + id + " put down Both ChopStick\n");
		if (id == 0) {
			chopstick.setAxis(0,280, 380);
			chopstick.setAxis(4, 450, 400);
			state[0] = 4;
		}
		if (id == 1) {
			chopstick.setAxis(0, 280, 380);
			chopstick.setAxis(1, 250, 225);
			state[1] = 4;
		}
		if (id == 2) {
			chopstick.setAxis(1, 250, 225);
			chopstick.setAxis(2, 400, 120);
			state[2] = 4;
		}
		if (id == 3) {
			chopstick.setAxis(2, 400, 120);
			chopstick.setAxis(3, 540, 250);
			state[3] = 4;
		}
		if (id == 4) {
			chopstick.setAxis(3, 540, 250);
			chopstick.setAxis(4, 450, 400);
			state[4] = 4;
		}
	}

	public int getS(int id) {
		return state[id];

	}

	public void setS(int id, int s) {
		state[id] = s;
	}

	public void increment(int id) {
		noMeals[id]++;
	}

	public int getNoMeals(int id) {
		return noMeals[id];
	}

	public int getQuote(int id) {
		return quote[id];
	}

}
