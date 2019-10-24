
import javax.swing.AbstractButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.BorderFactory;
import javax.swing.JButton;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;
public class Control1 extends JPanel implements ActionListener 
{
	private JButton startbutton,pausebutton,exitbutton,restartbutton;//buttons
	private Timer timer;//timer
	private JLabel iterations;//iterations
	
	private int count = 0;//counts
	private int iterations1 = 0;//iterations
	
	
	int Size = 21;//number of x and y
	int Grid[][] = new int [Size][Size];//grid 
	int Change[][] = new int [Size][Size];//changed grid
	

	public Control1() //buttons

	{ Random();
		
		startbutton = new JButton("Start");//caption
		startbutton.setVerticalTextPosition(AbstractButton.CENTER);
		startbutton.setHorizontalTextPosition(AbstractButton.CENTER);//position
		startbutton.setActionCommand("Start");//event action when start is clicked



		pausebutton = new JButton("Pause");
		pausebutton.setVerticalTextPosition(AbstractButton.CENTER);
		pausebutton.setHorizontalTextPosition(AbstractButton.CENTER);
		pausebutton.setActionCommand("Pause");
		pausebutton.setEnabled(false);//button is not shown

		exitbutton = new JButton("Exit");
		exitbutton.setVerticalTextPosition(AbstractButton.CENTER);
		exitbutton.setHorizontalTextPosition(AbstractButton.CENTER);
		exitbutton.setActionCommand("Exit");

		restartbutton = new JButton("Restart");
		restartbutton.setVerticalTextPosition(AbstractButton.CENTER);
		restartbutton.setHorizontalTextPosition(AbstractButton.CENTER);
		restartbutton.setActionCommand("Restart");

		//listens for the action but using "this" for user inpput
		startbutton.addActionListener(this);
		pausebutton.addActionListener(this);
		exitbutton.addActionListener(this);
		restartbutton.addActionListener(this);
		
		iterations = new JLabel("Iterations");


		//adds to the frame
		add(startbutton);
		add(pausebutton);
		add(exitbutton);
		add(restartbutton);
		add(iterations);


		//timer 0.5seconds using "this" to process the action
		timer = new Timer(500, this);
		//creates the action "timer" every second
		timer.setActionCommand("Timer");
		//starts straight away
		timer.setInitialDelay(0);
	}
	
	public void actionPerformed(ActionEvent e) //every time a button is used or timer event occurs this method runs
	{
		
		if (e.getActionCommand().equals("Start")) //checks if user clicks on "start"
		{
			System.out.println("Start pressed");//test if its working
			startbutton.setEnabled(false);//disables the button
			pausebutton.setEnabled(true);//enable pause button
			timer.start();//timer starts
			rule();//calls rule
		}

		if (e.getActionCommand().equals("Pause"))//checks if user clicks on "pause"
		{
			System.out.println("Stop pressed");
			startbutton.setEnabled(true);
			pausebutton.setEnabled(false);
			timer.stop();//stops timer so stops counting

	
		}
		if (e.getActionCommand().equals("Timer"))//checks if user clicks on "timer"
		{
			count++;//adds to count
			iterations1++;
			System.out.println("t"+"="+count);//prints out iterations 
			rule();//calls rule
			repaint();
			iterations.setText("iterations:" + iterations1);
		}
		if (e.getActionCommand().equals("Exit"))
		{
			System.out.println("Final Iteration = "+count);//prints out final number
			//Stop the timer
			timer.stop();
			//Get the parent JFrame of this panel
			JFrame frame = (JFrame)SwingUtilities.getWindowAncestor(this);
			frame.dispose();//closes frame
		}   
		if (e.getActionCommand().equals("Restart")){
			System.out.println("Restart pressed");
			startbutton.setEnabled(true);
			pausebutton.setEnabled(false);
			timer.stop();
			count = 0;//returns counter back to 0
			Random();//calls random
			iterations1 = 0;
			
		}
	}
	
	public void paint(Graphics grid){
		int width = 60;// width frame the frame
		int size = (360/Size);//size of the grid
		super.paint(grid);//so that everything is drawn correctly
		for (int x = 0; x < Size; x++){
			int height = 70;
			for(int y = 0; y < Size; y++){
				if(Grid[x][y]>0){
					grid.setColor(Color.RED);// change colour if alive
				}
				else{
					grid.setColor(Color.GRAY);//change colour if dead
				}
				grid.fillRect(width, height, size, size);//shows the whole grid by the height and width
				height= height +size +1;//acts as a grid line
			}
			width = width + size + 1;
		}
	}
	
	public void Random(){
		for (int x = 0; x < Grid.length; x++){// for loop
			for (int y = 0; y <Grid.length; y++){//for each x and y
				Random rand = new Random();
				int Num = rand.nextInt(2);//random number between 1 or 0
				Grid[x][y] = Num;
			}
		}
		Random2();//calls random2
	}

	private void Random2() {
		for (int x = 0; x < Grid.length; x++){
			for (int y = 0; y <Grid.length; y++){
				Change[x][y] = Grid[x][y];//change is equals grid
			}
			}
		repaint();//redraws the window
		
	}
	
	public void rule(){//rule
		int x,y = 0;
		for(x = 0; x < Size; x++){
		for(y = 0; y < Size; y++){
			if(Neighbours(x,y)==3){// if there's 3 neighbours cell stays alive
				Grid[x][y] = 1;
			}
			else if (Neighbours(x,y) < 2){// cell dies if less that 2
				Grid[x][y]=0;
			}
			else if (Neighbours(x,y) > 3){//cell dies is more than 3
				Grid[x][y]=0;
			}
		}
		}
		Random2();//
	}

	private int Neighbours(int x, int y) {
		int countcell = 0;
		int xforward = ( x + 1), xbackwards = (x - 1), yup = (y - 1), ydown = (y + 1);//off the grid
		
		if (xbackwards == -1){
			xbackwards = Size - 1;//left coordinate returns to right
		}
		if (xforward == Size){//right returns to left
			xforward = 0;
		}
		if (yup == -1){
			yup = Size - 1;//top return to bottom
		}
		if (ydown == Size){//bottom returns to top
			ydown = 0;
		}
		if (xbackwards == -1 && yup == -1){//top left returns to bottom right
			xbackwards = Size - 1;
			yup = Size - 1;
		}
		if (xforward == Size && ydown == Size){//bottom right to top left
			xforward = 0;
			ydown = 0;
		}
		if (xbackwards == -1 && ydown == Size){//bottom left to top right
			xbackwards = Size - 1;
			ydown = 0;
		}
		if (xforward == Size && yup == -1){//top right to bottom left
			xforward = 0;
			yup = Size - 1;
		}
		
		
		
		
		if (Change[xbackwards][yup] == 1){// counts if cell is alive
			countcell++;
		}
		if (Change[x][yup] == 1){
			countcell++;
		}
		if (Change[xforward][yup] == 1){
			countcell++;
		}
		if (Change[xbackwards][y] == 1){
			countcell++;
		}
		if (Change[xforward][y] == 1){
			countcell++;
		}
		if (Change[xbackwards][ydown] == 1){
			countcell++;
		}
		if (Change[x][ydown] == 1){
			countcell++;
		}
		if (Change[xforward][ydown] == 1){
			countcell++;
		}
		return countcell; // return count to 0
	}
	
	
	


}