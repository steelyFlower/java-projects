package shinyHunter;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.io.*;

public class temp implements ActionListener
{
	//declaration
	JFrame frame;
	JButton start,stop;
	JLabel statusLabel;
	Robot robot;
	String file;
	int count;
	boolean end,isShiny;
	int swapCount;
	int moveCount;
	Color overworld,battlegray,battlered,rattata,spearow,mankey;
	// frame constructor definition
	temp()
	{
		//frame set up
		frame = new JFrame();
		try {
			robot = new Robot();
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		isShiny=false;
		count=0;
		swapCount=1;
		moveCount=0;
		overworld=new Color(232,232,248);
		battlegray=new Color(40,48,48);
		battlered=new Color(248,208,80);
		rattata=new Color(208,144,208);
		spearow=new Color(248,128,112);
		mankey=new Color(248,240,200);

		
		frame.setSize(200,200);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Shiny Hunter");
		frame.setLayout(new GridLayout(3,1));
		
		statusLabel=new JLabel();
		statusLabel.setHorizontalAlignment(JLabel.CENTER);
		frame.add(statusLabel);
		
		start = new JButton();
		start.setText("Start");
		start.setFocusable(false);
		start.addActionListener(this);
		frame.add(start);

		stop = new JButton();
		stop.setText("Stop");
		stop.setFocusable(false);
		stop.addActionListener(this);
		stop.setEnabled(false);
		frame.add(stop);

		frame.setVisible(true);
		statusLabel.setText("Waiting");
	}
	
	//action performed
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==start)
		{
			//	call startHunt function
			statusLabel.setText("Start");
			moveCount=0;
			swapCount=1;
			start.setEnabled(false);
			stop.setEnabled(true);
			new HuntStarter().execute();
		}
		else if(e.getSource()==stop )
		{
			end=true;
			start.setEnabled(true);
			stop.setEnabled(false);
			
			new HuntStopper().execute();
		}
	}
	void setUp() throws InterruptedException
	{
		swapCount=1;
		moveCount=0;
		
		System.out.println("setup");
		file="D:\\jake\\coding\\eclipse space\\shinyHunt\\src\\shinyHunter\\count.txt";
		String content="";
		try (BufferedReader reader = new BufferedReader(new FileReader(file))) 
		{
			statusLabel.setText("got file");
            String line;
            while ((line = reader.readLine()) != null) 
            {
                content=line;
			}
		} catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
            e.printStackTrace();
            
        }
        int num=Integer.parseInt(content);
        count= num;
        System.out.println(num);
        robot.keyPress(KeyEvent.VK_F4);
		Thread.sleep(200);
		robot.keyRelease(KeyEvent.VK_F4);
		Thread.sleep(200);
        robot.keyPress(KeyEvent.VK_ALT);
		Thread.sleep(300);
		robot.keyRelease(KeyEvent.VK_ALT);
		Thread.sleep(300);
		robot.keyRelease(KeyEvent.VK_F);
		Thread.sleep(300);
		robot.keyPress(KeyEvent.VK_F);
		Thread.sleep(500);
		robot.keyPress(KeyEvent.VK_R);
		Thread.sleep(300);
		robot.keyRelease(KeyEvent.VK_R);
		Thread.sleep(500);
		robot.keyPress(KeyEvent.VK_G);
		Thread.sleep(300);
		robot.keyRelease(KeyEvent.VK_G);
		Thread.sleep(2000);
		robot.keyPress(KeyEvent.VK_ENTER);
		Thread.sleep(300);
		robot.keyRelease(KeyEvent.VK_ENTER);
		Thread.sleep(500);
		robot.keyPress(KeyEvent.VK_Y);
		Thread.sleep(300);
		robot.keyRelease(KeyEvent.VK_Y);
		statusLabel.setText("Setup keys done");
		end=false;

	}
	void startHunt () throws InterruptedException, AWTException
	{
	//	call setUp method
		statusLabel.setText("StartHunt");
		while(!isShiny && !end)
		{
			if(moveCount>=25)
			{
				swap();
			}
			hunt();
		}
	}
	void hunt() throws InterruptedException
	{	
		if(robot.getPixelColor(770, 385).equals(overworld))
		{
			for(int i=0 ;i<3 && !end ;i++ )
			{
				robot.keyPress(KeyEvent.VK_A);
				Thread.sleep(200);
				robot.keyRelease(KeyEvent.VK_A);
			}
			for(int i=0 ;i<3 && !end;i++ )
			{
				robot.keyPress(KeyEvent.VK_D);
				Thread.sleep(200);
				robot.keyRelease(KeyEvent.VK_D);
			}
		}
		else if(robot.getPixelColor(194, 585).equals(battlegray) && robot.getPixelColor(380, 205).equals(battlered) && !end)
		{
			if((robot.getPixelColor(993,300).equals(rattata)) || (robot.getPixelColor(1000, 251).equals(mankey)) || (robot.getPixelColor(1080,265).equals(spearow)))
			{
				moveCount++;
				count++;
				statusLabel.setText(Integer.toString(count));
				while(robot.getPixelColor(194, 585).equals(battlegray) && !end)
				{
					robot.keyPress(KeyEvent.VK_E);
					Thread.sleep(200);
					robot.keyRelease(KeyEvent.VK_E);
				}
			}
			else
			{
				isShiny=true;
				end=true;
				start.setEnabled(true);
				stop.setEnabled(false);
				//call save method
				new HuntStopper().execute();
			}
		}
		
	}
	void swap() throws InterruptedException, AWTException
	{
		if(swapCount>2 )
		{
			end=true;
			//call save method
			moveCount=0;
			pokeCenter();
			end=true;
			save();
			Thread.sleep(5000);
			setUp();
			return;
		}
		Thread.sleep(500);
		robot.keyPress(KeyEvent.VK_X);
		Thread.sleep(200);
		robot.keyRelease(KeyEvent.VK_X);
		Thread.sleep(500);
		robot.keyPress(KeyEvent.VK_E);
		Thread.sleep(200);
		robot.keyRelease(KeyEvent.VK_E);
		Thread.sleep(500);
		robot.keyPress(KeyEvent.VK_E);
		Thread.sleep(200);
		robot.keyRelease(KeyEvent.VK_E);
		Thread.sleep(500);
		robot.keyPress(KeyEvent.VK_S);
		Thread.sleep(10);
		robot.keyRelease(KeyEvent.VK_S);
		Thread.sleep(200);
		robot.keyPress(KeyEvent.VK_E);
		Thread.sleep(200);
		robot.keyRelease(KeyEvent.VK_E);
		Thread.sleep(200);
		for(int i=0 ;i<swapCount ;i++ )
		{
			Thread.sleep(200);
			robot.keyPress(KeyEvent.VK_S);
			Thread.sleep(10);
			robot.keyRelease(KeyEvent.VK_S);
		}
		robot.keyPress(KeyEvent.VK_E);
		Thread.sleep(200);
		robot.keyRelease(KeyEvent.VK_E);
		Thread.sleep(200);
		robot.keyPress(KeyEvent.VK_Q);
		Thread.sleep(200);
		robot.keyRelease(KeyEvent.VK_Q);
		Thread.sleep(200);
		robot.keyPress(KeyEvent.VK_Q);
		Thread.sleep(200);
		robot.keyRelease(KeyEvent.VK_Q);
		Thread.sleep(200);
		robot.keyPress(KeyEvent.VK_Q);
		Thread.sleep(200);
		robot.keyRelease(KeyEvent.VK_Q);
		Thread.sleep(200);
		swapCount++;
		moveCount=0;
	}
	void pokeCenter() throws InterruptedException
	{
		Thread.sleep(2000);
		while(!(robot.getPixelColor(1100,400).equals(new Color(232,224,136))) && !isShiny)
		{
			robot.keyPress(KeyEvent.VK_D);
			Thread.sleep(5);
			robot.keyRelease(KeyEvent.VK_D);
			Thread.sleep(500);
			if(!(robot.getPixelColor(770, 385).equals(overworld)))
			{
				hunt();
			}
		}
		while(!(robot.getPixelColor(760,560).equals(new Color(224,216,160))) && !isShiny)
		{
			robot.keyPress(KeyEvent.VK_S);
			Thread.sleep(5);
			robot.keyRelease(KeyEvent.VK_S);
			Thread.sleep(500);
			if(!(robot.getPixelColor(770, 385).equals(overworld)))
			{
				hunt();
			}
		}
		while(!(robot.getPixelColor(830,430).equals(new Color(144,104,104))) && !isShiny)
		{
			robot.keyPress(KeyEvent.VK_D);
			Thread.sleep(5);
			robot.keyRelease(KeyEvent.VK_D);
			Thread.sleep(300);
		}
		while(!(robot.getPixelColor(830,420).equals(new Color(232,224,136))) && !isShiny)
		{
			robot.keyPress(KeyEvent.VK_W);
			Thread.sleep(5);
			robot.keyRelease(KeyEvent.VK_W);
			Thread.sleep(300);
		}
		while(!(robot.getPixelColor(830,410).equals(new Color(232,168,64))) && !isShiny)
		{
			robot.keyPress(KeyEvent.VK_D);
			Thread.sleep(5);
			robot.keyRelease(KeyEvent.VK_D);
			Thread.sleep(300);
		}
		while(!(robot.getPixelColor(830,670).equals(new Color(120,72,72))) && !isShiny)
		{
			robot.keyPress(KeyEvent.VK_S);
			Thread.sleep(5);
			robot.keyRelease(KeyEvent.VK_S);
			Thread.sleep(300);
		}
		while(!(robot.getPixelColor(770,350).equals(new Color(96,160,216))) && !isShiny)
		{
			robot.keyPress(KeyEvent.VK_D);
			Thread.sleep(5);
			robot.keyRelease(KeyEvent.VK_D);
			Thread.sleep(300);
		}
		while(!(robot.getPixelColor(850,325).equals(new Color(248,128,112))) && !isShiny)
		{
			robot.keyPress(KeyEvent.VK_W);
			Thread.sleep(5);
			robot.keyRelease(KeyEvent.VK_W);
			Thread.sleep(300);
		}
		while(!(robot.getPixelColor(1115,720).equals(new Color(224,8,8))) && !isShiny)
		{
			robot.keyPress(KeyEvent.VK_E);
			Thread.sleep(10);
			robot.keyRelease(KeyEvent.VK_E);
			Thread.sleep(300);
		}
		while(!(robot.getPixelColor(1115,725).equals(new Color(224,216,152))) && !isShiny)
		{
			robot.keyPress(KeyEvent.VK_Q);
			Thread.sleep(100);
			robot.keyRelease(KeyEvent.VK_Q);
			Thread.sleep(300);
		}
		while(!(robot.getPixelColor(1115,500).equals(new Color(216,224,224))) && !isShiny)
		{
			robot.keyPress(KeyEvent.VK_S);
			Thread.sleep(5);
			robot.keyRelease(KeyEvent.VK_S);
			Thread.sleep(300);
		}
		Thread.sleep(500);

		while(!(robot.getPixelColor(200,500).equals(new Color(96,160,216))) && !isShiny)
		{
			robot.keyPress(KeyEvent.VK_A);
			Thread.sleep(5);
			robot.keyRelease(KeyEvent.VK_A);
			Thread.sleep(300);
		}
		while(!(robot.getPixelColor(830,410).equals(new Color(232,168,64))) && !isShiny)
		{
			robot.keyPress(KeyEvent.VK_W);
			Thread.sleep(5);
			robot.keyRelease(KeyEvent.VK_W);
			Thread.sleep(300);
		}
		while(!(robot.getPixelColor(760,500).equals(new Color(232,224,136))) && !isShiny)
		{
			robot.keyPress(KeyEvent.VK_A);
			Thread.sleep(5);
			robot.keyRelease(KeyEvent.VK_A);
			Thread.sleep(300);
		}
		while(!(robot.getPixelColor(760,560).equals(new Color(224,216,160))) && !isShiny)
		{
			robot.keyPress(KeyEvent.VK_S);
			Thread.sleep(5);
			robot.keyRelease(KeyEvent.VK_S);
			Thread.sleep(300);
		}
		while(!(robot.getPixelColor(660,430).equals(new Color(120,72,72))) && !isShiny)
		{
			robot.keyPress(KeyEvent.VK_A);
			Thread.sleep(5);
			robot.keyRelease(KeyEvent.VK_A);
			Thread.sleep(300);
		}
		while(!(robot.getPixelColor(760,560).equals(new Color(112,200,160))) && !isShiny)
		{
			robot.keyPress(KeyEvent.VK_W);
			Thread.sleep(5);
			robot.keyRelease(KeyEvent.VK_W);
			Thread.sleep(300);
		}
		Thread.sleep(1000);
		robot.keyPress(KeyEvent.VK_SHIFT);
		Thread.sleep(500);
		robot.keyPress(KeyEvent.VK_F4);
		Thread.sleep(100);
		robot.keyRelease(KeyEvent.VK_F4);
		Thread.sleep(500);
		robot.keyRelease(KeyEvent.VK_SHIFT);
		Thread.sleep(500);
		
	}
	void save() throws InterruptedException 
	{
		robot.keyPress(KeyEvent.VK_ALT);
		Thread.sleep(300);
		robot.keyRelease(KeyEvent.VK_ALT);
		Thread.sleep(300);
		robot.keyPress(KeyEvent.VK_F);
		Thread.sleep(300);
		robot.keyRelease(KeyEvent.VK_F);
		Thread.sleep(500);
		robot.keyPress(KeyEvent.VK_R);
		Thread.sleep(300);
		robot.keyRelease(KeyEvent.VK_R);
		Thread.sleep(500);
		robot.keyPress(KeyEvent.VK_A);
		Thread.sleep(50);
		robot.keyRelease(KeyEvent.VK_A);
		Thread.sleep(2000);
		statusLabel.setText("Save keys done");
		String content=Integer.toString(count);
		try (FileWriter fileWriter = new FileWriter(file))
		{
			
			fileWriter.write(content);
		}catch (IOException e) {
            System.err.println("An error occurred while writing to the file: " + e.getMessage());
            e.printStackTrace();
        }
        statusLabel.setText("Save file done");

	}
	
	class HuntStarter extends SwingWorker<String, Void> 
	{
        @Override
        protected String doInBackground() throws Exception 
        {
            try 
            {
            	statusLabel.setText("HuntStarter");
				Thread.sleep(1500);
			} 
			catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try 
			{
				setUp();
				statusLabel.setText("setup done");
				startHunt();
			} 
			catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} 
			catch (AWTException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			return "";        
        }
    }
	class HuntStopper extends SwingWorker<String, Void> 
	{
        @Override
        protected String doInBackground() throws Exception 
        {
        	try 
        	{
        		statusLabel.setText("HuntStopper");
				Thread.sleep(3000);
			} 
			catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			//call save method
			try 
			{
				save();
			} 
			catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			return "";        
        }
    }
	
}
