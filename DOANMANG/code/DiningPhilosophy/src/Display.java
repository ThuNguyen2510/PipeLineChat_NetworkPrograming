import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JPanel;

public class Display extends JPanel implements Runnable {

 Image bg;
 Image s0,s1,s2,s3,s4;
 Image name0, name1, name2, name3, name4;
 Image plate[]=new Image[5];
 Toolkit t;
 Image state1,state2;
 Font font;
 Chopstick chopstick;
 Philosopher philosopher;
 
 public Display(){
  t=Toolkit.getDefaultToolkit();
  font = new Font("Serif", Font.PLAIN, 20);
  bg=t.getImage("image//BG1.png");
  s0=t.getImage("image//ts0.png");
  s1=t.getImage("image//ts1.png");
  s2=t.getImage("image//ts2.png");
  s3=t.getImage("image//ts3.png");
  s4=t.getImage("image//ts4.png");
  
  name0=t.getImage("image//name0.png");
  name1=t.getImage("image//name1.png");
  name2=t.getImage("image//name2.png");
  name3=t.getImage("image//name3.png");
  name4=t.getImage("image//name4.png");
  
  state1=t.getImage("image//eating.jpg");
  state2=t.getImage("image//thinking.jpg");
  plate[0]=t.getImage("image//p1.png");
  plate[1]=t.getImage("image//p2.png");
  plate[2]=t.getImage("image//p3.png");
  plate[3]=t.getImage("image//p4.png");
  plate[4]=t.getImage("image//p5.png");
  
  chopstick=new Chopstick();
  philosopher=new Philosopher();
  
  
 }
 
 public void run(){
  while(true){
   repaint();
   
   try {
    Thread.sleep(10);
   } catch (InterruptedException e) {
    e.printStackTrace();
   }
  }
  
 }
 
 public void paint(Graphics g){
  super.paint(g);
  this.setBackground(Color.white);
  g.drawImage(bg,100 , 0, 600, 580, null);
  drawP(g);
  drawName(g);
  drawP0(g);
  drawP1(g);
  drawP2(g);
  drawP3(g);
  drawP4(g);
  draw0(g);
  draw1(g);
  draw2(g);
  draw3(g);
  draw4(g);
  drawS0(g);
  drawS1(g);
  drawS2(g);
  drawS3(g);
  drawS4(g);
 }
 public void draw0(Graphics g){
  
  g.drawImage(s0,chopstick.getXpos(0), chopstick.getYpos(0), 25, 25, null);
  
 }
 public void draw1(Graphics g){
     
     g.drawImage(s1,chopstick.getXpos(1) , chopstick.getYpos(1), 25, 25, null);
  
 }public void draw2(Graphics g){
  g.drawImage(s2,chopstick.getXpos(2), chopstick.getYpos(2), 25, 25, null);
  
 }public void draw3(Graphics g){
  
  g.drawImage(s3,chopstick.getXpos(3), chopstick.getYpos(3), 25, 25, null);
  
 }public void draw4(Graphics g){
  
  g.drawImage(s4,chopstick.getXpos(4), chopstick.getYpos(4), 25, 25, null);
  
 }
 
 public void drawP0(Graphics g){
  
  g.drawImage(plate[philosopher.getS(0)], 335,380, 100, 100, null);
  
 }
 public void drawP1(Graphics g){
  g.drawImage(plate[philosopher.getS(1)], 200,275, 100, 100, null);
 }
 public void drawP2(Graphics g){
  g.drawImage(plate[philosopher.getS(2)], 275,120, 100, 100, null);
 }
 public void drawP3(Graphics g){
  g.drawImage(plate[philosopher.getS(3)], 455,135, 100, 100, null);
 }
 public void drawP4(Graphics g){
  g.drawImage(plate[philosopher.getS(4)], 488,290, 100, 100, null);
 }
 
 public void drawP(Graphics g){
  g.drawImage(plate[0], 335,380, 100, 100, null);
  g.drawImage(plate[0], 200,275, 100, 100, null);
  g.drawImage(plate[0], 275,120, 100, 100, null);
  g.drawImage(plate[0], 455,135, 100, 100, null);
  g.drawImage(plate[0], 488,290, 100, 100, null);
 }
 
 public void drawName(Graphics g){
	  g.drawImage(name0, 335,545, 110, 20, null);
	  g.drawImage(name1, 105,345, 75, 100, null);
	  g.drawImage(name2, 170,55, 85, 90, null);
	  g.drawImage(name3, 545,55, 85, 90, null);
	  g.drawImage(name4, 590,375, 90, 100, null);
	 }
 
 
 //
 public void drawS0(Graphics g){
  g.setFont(font);
  //getNoMeals
  g.setColor(Color.RED);
  g.drawString("No Of Meals="+Integer.toString(philosopher.getNoMeals(0)), 920, 60);
  g.setColor(Color.BLACK);
  if(philosopher.getS(0)==4){
   g.drawImage(state2,800 , 0, 100, 100, null);
   g.drawString("Thinking", 920, 30);
  }else{
   g.drawImage(state1,800 , 0, 100, 100, null);
   g.drawString("Eating", 920, 30);
  }
  
 }
 public void drawS1(Graphics g){
  g.setFont(font);
  g.setColor(Color.RED);
  g.drawString("No Of Meals="+Integer.toString(philosopher.getNoMeals(1)), 920, 180);
  g.setColor(Color.BLACK);
  if(philosopher.getS(1)==4){
   g.drawImage(state2,800 , 120, 100, 100, null);
   g.drawString("Thinking", 920, 150);
  }else{
   g.drawImage(state1,800 , 120, 100, 100, null);
   g.drawString("Eating", 920, 150);
  }
 }
 public void drawS2(Graphics g){
  g.setFont(font);
  g.setColor(Color.RED);
  g.drawString("No Of Meals="+Integer.toString(philosopher.getNoMeals(2)), 920, 300);
  g.setColor(Color.BLACK);
  if(philosopher.getS(2)==4){
   g.drawImage(state2,800 , 240, 100, 100, null);
   g.drawString("Thinking", 920, 270);
  }else{
   g.drawImage(state1,800 , 240, 100, 100, null);
   g.drawString("Eating", 920, 270);
  }
  
 }public void drawS3(Graphics g){
  g.setFont(font);
  g.setColor(Color.RED);
  g.drawString("No Of Meals="+Integer.toString(philosopher.getNoMeals(3)), 920, 420);
  g.setColor(Color.BLACK);
  if(philosopher.getS(3)==4){
   g.drawImage(state2,800, 360, 100, 100, null);
   g.drawString("Thinking", 920, 390);
  }else{
   g.drawImage(state1,800, 360, 100, 100, null);
   g.drawString("Eating", 920, 390);
  }
  
 }public void drawS4(Graphics g){
  g.setFont(font);
  g.setColor(Color.RED);
  g.drawString("No Of Meals="+Integer.toString(philosopher.getNoMeals(4)), 920, 540);
  g.setColor(Color.BLACK);
  if(philosopher.getS(4)==4){
   g.drawImage(state2,800, 480, 100, 100, null);
   g.drawString("Thinking", 920, 510);
  }else{
   g.drawImage(state1,800, 480, 100, 100, null);
   g.drawString("Eating", 920, 510);
  }
 }
 public void drawQuote(Graphics g){
  if(philosopher.getQuote(0)==1){
   
  }
  if(philosopher.getQuote(0)==2){
   
  }if(philosopher.getQuote(0)==3){
   
  }
 }
    


}
