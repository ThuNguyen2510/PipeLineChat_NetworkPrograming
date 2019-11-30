
import javax.swing.*;

public class App {
	JFrame f1;
	Chopstick chopstick[];
	Philosopher philosophers[];

	public App() {

		f1 = new JFrame();
		f1.setTitle("DINING PHILOSOPHER SIMULATOR");
		f1.setSize(1100, 640);
		f1.setVisible(true);
		f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f1.setLocationRelativeTo(null);
		f1.setResizable(false);
		initializePos();
		initializePhilosopher();
		Display obj = new Display();
		obj.setBounds(0, 0, 1040, 600);
		Thread t1 = new Thread(obj);
		t1.start();
		new Thread(philosophers[0]).start();
		new Thread(philosophers[1]).start();
		new Thread(philosophers[2]).start();
		new Thread(philosophers[3]).start();
		new Thread(philosophers[4]).start();
		f1.add(obj);
		f1.validate();

	}

	public void initializePos() {
		chopstick = new Chopstick[5];
		chopstick[0] = new Chopstick(0, 280, 380);
		chopstick[1] = new Chopstick(1, 250, 225);
		chopstick[2] = new Chopstick(2, 400, 120);
		chopstick[3] = new Chopstick(3, 540, 250);
		chopstick[4] = new Chopstick(4, 450, 400);
	}

	public void initializePhilosopher() {
		philosophers = new Philosopher[5];
		philosophers[0] = new Philosopher(0, chopstick[0], chopstick[4]);
		philosophers[1] = new Philosopher(1, chopstick[1], chopstick[0]);
		philosophers[2] = new Philosopher(2, chopstick[2], chopstick[1]);
		philosophers[3] = new Philosopher(3, chopstick[3], chopstick[2]);
		philosophers[4] = new Philosopher(4, chopstick[4], chopstick[3]);
	}

	public static void main(String args[]) {
		new App();
	}

}