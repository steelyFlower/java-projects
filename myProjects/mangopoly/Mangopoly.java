package mangopoly;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import guiFormat.GUIFormater;

public class Mangopoly implements ActionListener, ComponentListener
{
	JFrame frame;
	JPanel panel,midpanel;
	JLabel[] tiles=new JLabel[40];
	JButton[] properties=new JButton[40];
	Mangopoly()
	{
		frame = new JFrame("EnigmaMachine");
		GUIFormater.guiFormater(frame);
		frame.setSize(500,500);

		panel=new JPanel();
		GUIFormater.guiFormater(panel);
		//panel.addComponentListener(this);
		frame.add(panel);
		midpanel=new JPanel();

		GUIFormater.guiFormater(midpanel);
		panel.add(midpanel,GUIFormater.setDimension(4,4,18,18,0));

		tiles[0]=new JLabel("Start");
		GUIFormater.guiFormater(tiles[0]);
		panel.add(tiles[0],GUIFormater.setDimension(22,23,4,3,0));
		properties[0]=new JButton("Start");
		GUIFormater.guiFormater(properties[0]);
		panel.add(properties[0],GUIFormater.setDimension(22,22,4,1,0));

		tiles[10]=new JLabel("Jail");
		GUIFormater.guiFormater(tiles[10]);
		panel.add(tiles[10],GUIFormater.setDimension(0,22,3,4,0));
		properties[10]=new JButton("Jail");
		GUIFormater.guiFormater(properties[10]);
		panel.add(properties[10],GUIFormater.setDimension(3,22,1,4,0));

		tiles[20]=new JLabel("Free Parking");
		GUIFormater.guiFormater(tiles[20]);
		panel.add(tiles[20],GUIFormater.setDimension(0,0,4,3,0));
		properties[20]=new JButton("Free Parking");
		GUIFormater.guiFormater(properties[20]);
		panel.add(properties[20],GUIFormater.setDimension(0,3,4,1,0));

		tiles[30]=new JLabel("Go To Jail");
		GUIFormater.guiFormater(tiles[30]);
		panel.add(tiles[30],GUIFormater.setDimension(23,0,3,4,0));
		properties[30]=new JButton("Go To Jail");
		GUIFormater.guiFormater(properties[30]);
		panel.add(properties[30],GUIFormater.setDimension(22,0,1,4,0));

		for(int i=20, j=1;i>=4&&j<=9;j++)
		{
			tiles[j]=new JLabel("tile "+Integer.toString(j));
			GUIFormater.guiFormater(tiles[j]);
			panel.add(tiles[j],GUIFormater.setDimension(i,23,2,3,0));
			properties[j]=new JButton("property "+Integer.toString(j));
			GUIFormater.guiFormater(properties[j]);
			panel.add(properties[j],GUIFormater.setDimension(i,22,2,1,0));
			i-=2;
		}
		for(int i=20, j=11;i>=4&&j<=19;j++)
		{
			tiles[j]=new JLabel("tile "+Integer.toString(j));
			GUIFormater.guiFormater(tiles[j]);
			panel.add(tiles[j],GUIFormater.setDimension(0,i,3,2,0));
			properties[j]=new JButton("property "+Integer.toString(j));
			GUIFormater.guiFormater(properties[j]);
			panel.add(properties[j],GUIFormater.setDimension(3,i,1,2,0));
			i-=2;
		}
		for(int i=20, j=21;i>=4&&j<=29;j++)
		{
			tiles[j]=new JLabel("tile "+Integer.toString(j));
			GUIFormater.guiFormater(tiles[j]);
			panel.add(tiles[j],GUIFormater.setDimension(i,0,2,3,0));
			properties[j]=new JButton("property "+Integer.toString(j));
			GUIFormater.guiFormater(properties[j]);
			panel.add(properties[j],GUIFormater.setDimension(i,3,2,1,0));
			i-=2;
		}
		for(int i=20, j=31;i>=4&&j<=39;j++)
		{
			tiles[j]=new JLabel("tile "+Integer.toString(j));
			GUIFormater.guiFormater(tiles[j]);
			panel.add(tiles[j],GUIFormater.setDimension(23,i,3,2,0));
			properties[j]=new JButton("property "+Integer.toString(j));
			GUIFormater.guiFormater(properties[j]);
			panel.add(properties[j],GUIFormater.setDimension(22,i,1,2,0));
			i-=2;
		}
		frame.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void componentResized(ComponentEvent e) 
	{
		Component component=e.getComponent();
		Dimension size = component.getSize();
		int s=Math.min(size.width,size.height);
		component.setSize(s,s);
	}
	@Override
	public void componentMoved(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void componentShown(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void componentHidden(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}
}
