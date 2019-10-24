import javax.swing.JFrame;


public class GUI implements Runnable
{
	
	public void run() 
	{
	     //Create and set up the Window
        JFrame frame = new JFrame("Game of Life");
        //Disable the exit 'X' and close menu
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        //Create and set up the content Pane based on our FinchControl class
        Control1 newContentPane = new Control1();
        //All content Panes must be opaque apparently...
        newContentPane.setOpaque(true);
        //Put the Pane in the Window!
        frame.setContentPane(newContentPane);
        //Move to point 250,250 (offset from top left) and size to 400,400 pixels
        frame.setBounds(250,250,500,550);
        //Un-comment the following line to resize the Window so that all the controls fit... 
        //frame.pack();
        //Display the frame.
        frame.setVisible(true);
        
       
    }
}