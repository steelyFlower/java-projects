package guiFormat;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.BorderFactory;
import javax.swing.border.*;

public class GUIFormater 
{
	static Font button_font=new Font(Font.SANS_SERIF,Font.BOLD,50);
	static Font label_font=new Font(Font.DIALOG,Font.BOLD,100);
	static Font text_field_font=new Font(Font.MONOSPACED,Font.BOLD,50);
	static Color text_color=Color.WHITE;
	static Color containter_color=new Color(100,100,100);
	static Color interactable_color=new Color(50,50,50);
	static Color non_interactable_color=new Color(70,70,70);
	static Border blackRoundLine = BorderFactory.createLineBorder(Color.BLACK, 5);
	public static Color colorFormater(int place,int total)
	{
		int phase=(int)((place*120.0)/total);
		float h;
		if(phase<=60)
		{
			h=phase;
		}
		else
		{
			h=60+(phase-60)*2;
		}
		if(place==1)
		{
			h=0;
		}
		h/=360;
		return (Color.getHSBColor(h, 0.8f, 0.98f));
	}
	public static Color colorFormater(float place,float total)
	{
		int phase=(int)((place*120.0)/total);
		float h;
		if(phase<=60)
		{
			h=phase;
		}
		else
		{
			h=60+(phase-60)*2;
		}
		if(place==1)
		{
			h=0;
		}
		h/=360;
		return (Color.getHSBColor(h, 0.8f, 0.98f));
	}
	public static Color colorFormater(float place,int total)
	{
		int phase=(int)((place*120.0)/total);
		float h;
		if(phase<=60)
		{
			h=phase;
		}
		else
		{
			h=60+(phase-60)*2;
		}
		if(place==1)
		{
			h=0;
		}
		h/=360;
		return (Color.getHSBColor(h, 0.8f, 0.98f));
	}
	public static Color colorFormater(int place,float total)
	{
		int phase=(int)((place*120.0)/total);
		float h;
		if(phase<=60)
		{
			h=phase;
		}
		else
		{
			h=60+(phase-60)*2;
		}
		if(place==1)
		{
			h=0;
		}
		h/=360;
		return (Color.getHSBColor(h, 0.8f, 0.98f));
	}
	public static void guiFormater(JFrame frame)
	{
		frame.setSize(500,400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setBackground(containter_color);
	}
	public static void guiFormater(JPanel panel)
	{
		panel.setBackground(containter_color);
		panel.setLayout(new GridBagLayout());
		Border greyline = BorderFactory.createLineBorder(containter_color, 15);
		panel.setBorder(greyline);
	}
	public static void guiFormater(JLabel label)
	{
		label.setOpaque(true);
		label.setBackground(non_interactable_color);
		label.setForeground(text_color);
		label.setFont(label_font);
		label.setBorder(blackRoundLine);
		label.setFocusable(false);
		label.setHorizontalTextPosition(JLabel.CENTER);
		label.setVerticalTextPosition(JLabel.BOTTOM);
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setVerticalAlignment(JLabel.CENTER);
		label.addComponentListener(new ComponentAdapter() 
		{
            
            public void componentResized(ComponentEvent e) 
            {
                
                fontChanger(e);
            }
        });
	}
	public static void guiFormater(JButton button)
	{
		button.setOpaque(true);
		button.setBackground(interactable_color);
		button.setForeground(text_color);
		button.setFont(button_font);
		button.setBorder(blackRoundLine);
		button.setFocusable(false);
		button.setHorizontalTextPosition(JLabel.CENTER);
		button.setVerticalTextPosition(JLabel.BOTTOM);
		button.setHorizontalAlignment(JLabel.CENTER);
		button.setVerticalAlignment(JLabel.CENTER);
		button.addComponentListener(new ComponentAdapter() 
		{
            
            public void componentResized(ComponentEvent e) 
            {
                
               fontChanger(e);
            }
        });
	}
	public static void guiFormater(JTextField textfield)
	{
		textfield.setOpaque(true);
		textfield.setBackground(interactable_color);
		textfield.setForeground(text_color);
		textfield.setFont(text_field_font);
		textfield.setBorder(blackRoundLine);
		textfield.setCaretColor(text_color);
		textfield.addComponentListener(new ComponentAdapter() 
		{
            
            public void componentResized(ComponentEvent e) 
            {
                
                fontChanger(e);
            }
        });
	}
	static void fontChanger(ComponentEvent e)
	{
		Component component=e.getComponent();
		Dimension size = component.getSize();
                
                // Calculate a new font size based on the smaller dimension (width or height)
                // You can adjust the scaling factor (e.g., / 15) to control how fast the font grows/shrinks
                int newSize = Math.min(size.width, size.height) / 4;
                
                newSize = Math.max(10, newSize); 

		component.setFont(new Font(component.getFont().getName(), component.getFont().getStyle(), newSize));
	}
	public static GridBagConstraints setDimension(int x,int y)
	{
		GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets.set(10, 10, 10, 10); 
        gbc.weightx = 1.0;
        gbc.weighty = 1.0; 
        gbc.gridx = x; 
        gbc.gridy = y; 
        gbc.gridwidth = 1;
        gbc.gridheight=1;
        return gbc;
	}
	public static GridBagConstraints setDimension(int x,int y,int w,int h)
	{
		GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets.set(10, 10, 10, 10); 
        gbc.weightx = 1.0;
        gbc.weighty = 1.0; 
        gbc.gridx = x; 
        gbc.gridy = y; 
        gbc.gridwidth = w;
        gbc.gridheight=h;
        return gbc;
	}
	public static GridBagConstraints setDimension(int x,int y,int w,int h,int s)
	{
		GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets.set(s, s, s, s); 
        gbc.weightx = 1.0;
        gbc.weighty = 1.0; 
        gbc.gridx = x; 
        gbc.gridy = y; 
        gbc.gridwidth = w;
        gbc.gridheight=h;
        return gbc;
	}
}
