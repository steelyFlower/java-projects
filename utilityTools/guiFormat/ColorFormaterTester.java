package guiFormat;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class ColorFormaterTester implements ActionListener
{
	JFrame frame;
	JPanel panel;
	JLabel label;
	JButton button;
	JTextField textfield;
	
	ColorFormaterTester()
	{
		frame=new JFrame("ColorFormater");
		panel=new JPanel();
		label=new JLabel("this is a label");
		button=new JButton("this is a button");
		textfield=new JTextField();

		GUIFormater.guiFormater(frame);
		GUIFormater.guiFormater(panel);
		GUIFormater.guiFormater(label);
		GUIFormater.guiFormater(button);
		GUIFormater.guiFormater(textfield);
		
		button.addActionListener(this);
		panel.add(label,GUIFormater.setDimension(0,0,2,1));
		panel.add(button,GUIFormater.setDimension(0,1));
		panel.add(textfield,GUIFormater.setDimension(1,1));

		frame.add(panel);
		frame.setVisible(true);
	}
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==button)
		{
			int n=Integer.parseInt(textfield.getText());
			label.setForeground(Color.BLACK);
			label.setBackground(GUIFormater.colorFormater(n,100));
			textfield.setForeground(GUIFormater.colorFormater(n,100));
		}
	}

}
