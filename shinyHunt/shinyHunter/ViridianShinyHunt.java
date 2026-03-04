package shinyHunter;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.io.*;
import guiFormat.GUIFormater;

public class ViridianShinyHunt implements ActionListener
{
	//declaration
	JFrame frame;
	JPanel panel;
	JButton start,stop;
	JLabel statusLabel;
	Robot robot;
	String file;
	int count;
	boolean end,isShiny,ifSwap;
	int swapCount;
	Color overworld,battlegray,battlered,ppLimit,caterpie,weedle,metapod,kakuna,pikachu;
	ViridianShinyHunt()
	{
		try 
		{
			robot = new Robot();
		} 
		catch (AWTException e) 
		{
			e.printStackTrace();
		}

		isShiny=false;
		ifSwap=false;
		count=0;
		swapCount=1;
		overworld=new Color(232,232,248);
		battlegray=new Color(40,48,48);
		battlered=new Color(248,208,80);
		ppLimit=new Color(248,177,0);
		caterpie=new Color(120,152,96);
		weedle=new Color(208,136,104);
		metapod=new Color(112,168,48);
		kakuna=new Color(248,224,152);
		pikachu=new Color(248,232,0);
		

		frame = new JFrame("Shiny Hunter");
		GUIFormater.guiFormater(frame);

		panel=new JPanel();
		GUIFormater.guiFormater(panel);
		frame.add(panel);
		
		statusLabel=new JLabel("Waiting");
		GUIFormater.guiFormater(statusLabel);
		panel.add(statusLabel,GUIFormater.setDimension(0,0,2,1));
		
		start = new JButton("Start");
		GUIFormater.guiFormater(start);
		start.addActionListener(this);
		panel.add(start,GUIFormater.setDimension(0,1));


		stop = new JButton("Stop");
		GUIFormater.guiFormater(stop);
		stop.addActionListener(this);
		panel.add(stop,GUIFormater.setDimension(1,1));

		frame.setVisible(true);
	}
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==start)
		{
			//	call startHunt function
			statusLabel.setText("Start");
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
		
		System.out.println("setup");
		file="D:\\jake\\coding\\eclipse space\\shinyHunt\\src\\shinyHunter\\count3.txt";
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
        buttonClick(KeyEvent.VK_F5);
        Thread.sleep(500);
        buttonClick(KeyEvent.VK_H);
		Thread.sleep(2000);
		buttonClick(KeyEvent.VK_ENTER);
		Thread.sleep(500);
		buttonClick(KeyEvent.VK_Y);
		statusLabel.setText("Setup keys done");
		end=false;

	}
	void startHunt () throws InterruptedException, AWTException
	{
	//	call setUp method
		statusLabel.setText("StartHunt");
		while(!isShiny && !end)
		{
			if(ifSwap)
			{
				ifSwap=false;
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
			if(robot.getPixelColor(1030,309).equals(caterpie) || robot.getPixelColor(982,198).equals(pikachu) || robot.getPixelColor(1034,371).equals(metapod) || robot.getPixelColor(1042,356).equals(weedle) || robot.getPixelColor(1013,240).equals(kakuna))
			{
				count++;
				statusLabel.setText(Integer.toString(count));
				statusLabel.setForeground(GUIFormater.colorFormater(count, 8192));
				while(robot.getPixelColor(194, 585).equals(battlegray) && !end)
				{
					if(robot.getPixelColor(1002,670).equals(new Color(248,144,0)))
					{
						ifSwap=true;
					}
					buttonClick(KeyEvent.VK_E);	
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
		if(swapCount>3 )
		{
			end=true;
			pokeCenter();
			end=true;
			save();
			Thread.sleep(5000);
			setUp();
			return;
		}
		Thread.sleep(2000);
		robot.keyPress(KeyEvent.VK_X);
		Thread.sleep(200);
		robot.keyRelease(KeyEvent.VK_X);
		Thread.sleep(1000);
		for (int i=0;i<3 ;i++ ) 
		{
		robot.keyPress(KeyEvent.VK_E);
		Thread.sleep(200);
		robot.keyRelease(KeyEvent.VK_E);
		Thread.sleep(500);	
		}
		Thread.sleep(1000);
		for (int i=0;i<3 ;i++ ) 
		{
		robot.keyPress(KeyEvent.VK_D);
		Thread.sleep(200);
		robot.keyRelease(KeyEvent.VK_D);
		Thread.sleep(500);	
		}
		robot.keyPress(KeyEvent.VK_E);
		Thread.sleep(200);
		robot.keyRelease(KeyEvent.VK_E);
		Thread.sleep(200);
		robot.keyPress(KeyEvent.VK_E);
		Thread.sleep(200);
		robot.keyRelease(KeyEvent.VK_E);
		Thread.sleep(200);
		for(int i=0 ;i<swapCount ;i++ )
		{
			robot.keyPress(KeyEvent.VK_S);
			Thread.sleep(10);
			robot.keyRelease(KeyEvent.VK_S);
			Thread.sleep(200);
		}
		robot.keyPress(KeyEvent.VK_E);
		Thread.sleep(200);
		robot.keyRelease(KeyEvent.VK_E);
		Thread.sleep(200);
		for (int i=0;i<5 ;i++ ) 
		{
			robot.keyPress(KeyEvent.VK_Q);
		Thread.sleep(200);
		robot.keyRelease(KeyEvent.VK_Q);
		Thread.sleep(200);
		}
		swapCount++;
	}
	void pokeCenter() throws InterruptedException
	{
		Thread.sleep(2000);
		while(!(robot.getPixelColor(841,405).equals(new Color(192,192,200)))&& !isShiny)
		{
			Thread.sleep(1000);
			while(!(robot.getPixelColor(770, 385).equals(overworld)))
			{
				hunt();
			}
			Color sign=new Color(192,192,200);
			if(sign.equals(robot.getPixelColor(690,329 )))	
			{
					buttonClick(KeyEvent.VK_A);
			}
			else if(sign.equals(robot.getPixelColor(765,328 )))
			{
					buttonClick(KeyEvent.VK_A);
			}		
			else if(sign.equals(robot.getPixelColor(928,326 )))
			{
					buttonClick(KeyEvent.VK_D);
			}
			else if(sign.equals(robot.getPixelColor(1000,326 )))	
			{
					buttonClick(KeyEvent.VK_D);
			}		
			else if(sign.equals(robot.getPixelColor(840,329 )))
			{	
					buttonClick(KeyEvent.VK_W);
			}		
		}
		Thread.sleep(1000);
		while(!(robot.getPixelColor(770, 385).equals(overworld)))
		{
			hunt();
		}
		while(!(robot.getPixelColor(766,130).equals(new Color(56,88,16))) && !isShiny)
		{
			buttonClick(KeyEvent.VK_W);
		}
		while(!(robot.getPixelColor(825,430).equals(new Color(120,72,72))) && !isShiny)
		{
			buttonClick(KeyEvent.VK_D);
		}
		while(!(robot.getPixelColor(410,238).equals(new Color(168,32,40))) && !isShiny)
		{
			buttonClick(KeyEvent.VK_W);
		}
		while(!(robot.getPixelColor(761,350).equals(new Color(96,160,216))) && !isShiny)
		{
			buttonClick(KeyEvent.VK_A);
		}
		while(!(robot.getPixelColor(850,325).equals(new Color(248,128,112))) && !isShiny)
		{
			buttonClick(KeyEvent.VK_W);
		}
		while(!(robot.getPixelColor(1115,720).equals(new Color(224,8,8))) && !isShiny)
		{
			robot.keyPress(KeyEvent.VK_E);
			Thread.sleep(10);
			robot.keyRelease(KeyEvent.VK_E);
			Thread.sleep(300);
		}
		while(!(robot.getPixelColor(982,793).equals(new Color(0,0,0))) && !isShiny)
		{
			buttonClick(KeyEvent.VK_Q);
		}
		while(!(robot.getPixelColor(726,435).equals(new Color(184,248,136))) && !isShiny)
		{
			buttonClick(KeyEvent.VK_S);
		}
		while(!(robot.getPixelColor(776,517).equals(new Color(232,224,136))) && !isShiny)
		{
			buttonClick(KeyEvent.VK_D);
		}
		while(!(robot.getPixelColor(786,513).equals(new Color(120,72,72))) && !isShiny)
		{
			buttonClick(KeyEvent.VK_S);
		}
		while(!(robot.getPixelColor(1181,480).equals(new Color(128,200,48))) && !isShiny)
		{
			buttonClick(KeyEvent.VK_A);
		}
		while(!(robot.getPixelColor(690,438).equals(new Color(40,64,8))) && !isShiny)
		{
			buttonClick(KeyEvent.VK_S);
		}
		Thread.sleep(1000);
		robot.keyPress(KeyEvent.VK_SHIFT);
		Thread.sleep(500);
		robot.keyPress(KeyEvent.VK_F5);
		Thread.sleep(100);
		robot.keyRelease(KeyEvent.VK_F5);
		Thread.sleep(500);
		robot.keyRelease(KeyEvent.VK_SHIFT);
		Thread.sleep(500);
	}
	void buttonClick(int e) throws InterruptedException
	{
		Thread.sleep(300);
		robot.keyPress(e);
		Thread.sleep(5);
		robot.keyRelease(e);
		Thread.sleep(100);
	}
	void save() throws InterruptedException 
	{
		
		Thread.sleep(2000);
		buttonClick(KeyEvent.VK_G);
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
