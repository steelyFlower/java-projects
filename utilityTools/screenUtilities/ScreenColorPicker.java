package screenUtilities;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import guiFormat.GUIFormater;

public class ScreenColorPicker implements KeyListener 
{
	JFrame frame;
	JPanel panel;
	Robot robot;
	JLabel statusLabel,red,green,blue,xbox,ybox;;
	ScreenColorPicker()
	{
		try {
			robot=new Robot();
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		frame=new JFrame("Color Picker");
		GUIFormater.guiFormater(frame);
		frame.addKeyListener(this);
		
		panel=new JPanel();
		GUIFormater.guiFormater(panel);
		frame.add(panel);
		
		xbox=new JLabel("X");
		GUIFormater.guiFormater(xbox);
		panel.add(xbox,GUIFormater.setDimension(0,0));
		
		ybox=new JLabel("Y");
		GUIFormater.guiFormater(ybox);
		panel.add(ybox,GUIFormater.setDimension(1,0));
		
		statusLabel=new JLabel("Color");
		GUIFormater.guiFormater(statusLabel);
		panel.add(statusLabel,GUIFormater.setDimension(2,0));

		red=new JLabel("0");
		GUIFormater.guiFormater(red);
		panel.add(red,GUIFormater.setDimension(0,1));
		green=new JLabel("0");
		GUIFormater.guiFormater(green);
		panel.add(green,GUIFormater.setDimension(1,1));
		blue=new JLabel("0");
		GUIFormater.guiFormater(blue);
		panel.add(blue,GUIFormater.setDimension(2,1));

		
		frame.setVisible(true);
		
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
			PointerInfo pointerInfo = MouseInfo.getPointerInfo();
	        if (pointerInfo != null) 
	        {
	            Point p = pointerInfo.getLocation();
	            int x = p.x;
	            int y = p.y;
	            xbox.setText(Integer.toString(x));
	            ybox.setText(Integer.toString(y));
	            Color color=robot.getPixelColor(x,y);
	            red.setText(Integer.toString(color.getRed()));
				green.setText(Integer.toString(color.getGreen()));
				blue.setText(Integer.toString(color.getBlue()));
				statusLabel.setBackground(color);
	        }
		}
	}
}
