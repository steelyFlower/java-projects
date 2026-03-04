package shinyHunter;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import guiFormat.*;
public class ShinyCounter implements ActionListener,KeyListener
{
	JFrame frame;
	JPanel panel;
	JLabel label;
	JButton add,remove;
	String file;
	int count;
	ShinyCounter()
	{
		frame=new JFrame("Shiny Counter");
		GUIFormater.guiFormater(frame);
		frame.addKeyListener(this);
		
		panel=new JPanel();
		GUIFormater.guiFormater(panel);
		frame.add(panel);
		

		count=0;
		System.out.println("setup");
		file="D:\\jake\\coding\\eclipse space\\shinyHunt\\src\\shinyHunter\\count3.txt";
		String content="";
		try (BufferedReader reader = new BufferedReader(new FileReader(file))) 
		{
            String line;
            while ((line = reader.readLine()) != null) 
            {
                content=line;
			}
		} catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
            e.printStackTrace();
            
        }
        count=Integer.parseInt(content);
		
		label=new JLabel();
		GUIFormater.guiFormater(label);
		panel.add(label,GUIFormater.setDimension(0,0,2,1));
		label.setText(Integer.toString(count));
		
		add = new JButton("Add");
		GUIFormater.guiFormater(add);
		add.addActionListener(this);
		panel.add(add,GUIFormater.setDimension(0,1));

		remove = new JButton("Remove");
		GUIFormater.guiFormater(remove);
		remove.addActionListener(this);
		panel.add(remove,GUIFormater.setDimension(1,1));
		
		frame.setVisible(true);
	}
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==add)
		{
			count++;
			label.setText(Integer.toString(count));
			String content=Integer.toString(count);
		try (FileWriter fileWriter = new FileWriter(file))
		{
			
			fileWriter.write(content);
		}catch (IOException e1) {
            System.err.println("An error occurred while writing to the file: " + e1.getMessage());
            e1.printStackTrace();
        }
		}
		else if(e.getSource()==remove)
		{
			count--;
			label.setText(Integer.toString(count));
			String content=Integer.toString(count);
		try (FileWriter fileWriter = new FileWriter(file))
		{
			
			fileWriter.write(content);
		}catch (IOException e1) {
            System.err.println("An error occurred while writing to the file: " + e1.getMessage());
            e1.printStackTrace();
        }
		}
	}
	public void keyTyped(KeyEvent e)
	{
		
	}
	public void keyPressed(KeyEvent e)
	{
		
	}
	public void keyReleased(KeyEvent e)
	{
		if(e.getKeyChar()=='+')
		{
			count++;
			label.setText(Integer.toString(count));
			String content=Integer.toString(count);
			try (FileWriter fileWriter = new FileWriter(file))
			{
				
				fileWriter.write(content);
			}
			catch (IOException e1)
			{
		        System.err.println("An error occurred while writing to the file: " + e1.getMessage());
		        e1.printStackTrace();
		    }
		}
		if(e.getKeyChar()==KeyEvent.VK_MINUS)
		{
			count--;
			label.setText(Integer.toString(count));
			String content=Integer.toString(count);
			try (FileWriter fileWriter = new FileWriter(file))
			{
				
				fileWriter.write(content);
			}
			catch (IOException e1) 
			{
			    System.err.println("An error occurred while writing to the file: " + e1.getMessage());
			    e1.printStackTrace();
			}
		}
	}
}
