package enigmaMachine;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import guiFormat.GUIFormater;

public class EnigmaMachine implements KeyListener,ActionListener
{
	JFrame frame;
	JPanel panel;
	JTextField message,password,result;
	JLabel messageLabel,passwordLabel;
	JButton encode,reset;
	/*
	JLabel[] letters=new JLabel[28] ;
	String[] alphabets=new String[28];
	boolean isPressed=false;
	char newch;
	*/
	String alphaString,gear1Copy,gear2Copy,gear3Copy,gear1,gear2,gear3,reverser;
	int currentLetter,charCount;
	EnigmaMachine()
	{
		frame = new JFrame("EnigmaMachine");
		GUIFormater.guiFormater(frame);
		frame.addKeyListener(this);

		panel=new JPanel();
		GUIFormater.guiFormater(panel);
		frame.add(panel);

		messageLabel=new JLabel("Enter message:");
		GUIFormater.guiFormater(messageLabel);
		panel.add(messageLabel,GUIFormater.setDimension(0,0));
		passwordLabel=new JLabel("Enter password:");
		GUIFormater.guiFormater(passwordLabel);
		panel.add(passwordLabel,GUIFormater.setDimension(0,1));

		encode=new JButton("Encode:");
		GUIFormater.guiFormater(encode);
		panel.add(encode,GUIFormater.setDimension(0,2));
		encode.addActionListener(this);
		reset=new JButton("Reset Cipher");
		GUIFormater.guiFormater(reset);
		panel.add(reset,GUIFormater.setDimension(0,4,3,1));
		reset.addActionListener(this);

		message=new JTextField();
		GUIFormater.guiFormater(message);
		panel.add(message,GUIFormater.setDimension(1,0,2,1));
		password=new JTextField();
		GUIFormater.guiFormater(password);
		panel.add(password,GUIFormater.setDimension(1,1,2,1));
		result=new JTextField();
		GUIFormater.guiFormater(result);
		panel.add(result,GUIFormater.setDimension(1,2,2,1));

		
		/*
		alphabets=new String[]{"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"," ","."};
		*/
		alphaString="ABCDEFGHIJKLMNOPQRSTUVWXYZ .";
		gear1=    "MNAB .OPQRSTUCDEFGVHIJKLWXYZ";
		gear1Copy=gear1;

		alphaString="ABCDEFGHIJKLMNOPQRSTUVWXYZ .";
		gear2=    "HYZ .OPQIJKLMFGVWXNABCDERSTU";
		gear2Copy=gear2;

		alphaString="ABCDEFGHIJKLMNOPQRSTUVWXYZ .";
		gear3=    "YZ CDEPQR.OLMNVWXABSTUHIJKFG";
		gear3Copy=gear3;

		alphaString="ABCDEFGHIJKLMNOPQRSTUVWXYZ .";
		reverser=   "VWXYZ .OPQRSTUHIJKLMNABCDEFG";  
		/*
		alphaString="ABCDEFGHIJKLMNOPQRSTUVWXYZ .";
		gear1Copy=    "OPQRSTUVWXYZ .ABCDEFGHIJKLMN";

		alphaString="ABCDEFGHIJKLMNOPQRSTUVWXYZ .";
		gear2Copy=    "FGHIJKLMNOPQRSTUVWXYZ .ABCDE";

		alphaString="ABCDEFGHIJKLMNOPQRSTUVWXYZ .";
		gear3Copy=    "QRSTUVWXYZ .ABCDEFGHIJKLMNOP";

		alphaString="ABCDEFGHIJKLMNOPQRSTUVWXYZ .";
		reverser=   "VWXYZ .OPQRSTUHIJKLMNABCDEFG"; 
		gear1Copy="UVWXYZ ABCDEFGHOPQRSTIJKLMN";
		gear2Copy="Z ABCDEFGUVWXYTIJKLMNHOPQRS";
		gear3Copy="IJKLMNHOEFGUVWPQRSXYTBCDZ A";
		reverser=   "DCBA. ZYXWVUTSRQPONMLKJIHGFE";
		
		
		currentLetter='a';
		newch='A';
		charCount=0;
		for (int i=0; i<28; i=i) 
		{
			for (int j=0;j<7 ;j++ ) 
			{	
				letters[i]=new JLabel(alphabets[i]);
				GUIFormater.guiFormater(letters[i]);
				panel.add(letters[i],GUIFormater.setDimension(j,i/7)); 
				i++;
			}
		}
		
		
		String str="OAARVKIODDPGMDO";
		String encoded="";
		for(int i=0;i<str.length();i++)
		{
			encoded=encoded+enigma(str.charAt(i));
		}
		System.out.println(encoded);
		*/
		frame.setVisible(true);
	}

	public  char enigma(char a)
	{
		char c='*';
		for(int i=0;i<28;i++)
		{
			if(alphaString.charAt(i)==a)
			{
				c=gear1Copy.charAt(i);
				break;
			}
		}
		for(int i=0;i<28;i++)
		{
			if(alphaString.charAt(i)==c)
			{
				c=gear2Copy.charAt(i);
				break;
			}
		}
		for(int i=0;i<28;i++)
		{
			if(alphaString.charAt(i)==c)
			{
				c=gear3Copy.charAt(i);
				break;
			}
		}
		for(int i=0;i<28;i++)
		{
			if(alphaString.charAt(i)==c)
			{
				c=reverser.charAt(i);
				break;
			}
		}
		for(int i=0;i<28;i++)
		{
			if(gear3Copy.charAt(i)==c)
			{
				c=alphaString.charAt(i);
				break;
			}
		}
		for(int i=0;i<28;i++)
		{
			if(gear2Copy.charAt(i)==c)
			{
				c=alphaString.charAt(i);
				break;
			}
		}
		for(int i=0;i<28;i++)
		{
			if(gear1Copy.charAt(i)==c)
			{
				a=alphaString.charAt(i);
				break;
			}
		}
		charCount++;
		String swap=""+gear1Copy.charAt(0);
		gear1Copy=gear1Copy.replace(swap,"");
		gear1Copy=gear1Copy+swap;
		if(charCount%28==0)
		{
			swap=""+gear2Copy.charAt(0);
			gear2Copy=gear2Copy.replace(swap,"");
			gear2Copy=gear2Copy+swap;
			if(charCount%(28*28)==0)
			{
				swap=""+gear3Copy.charAt(0);
				gear3Copy=gear3Copy.replace(swap,"");
				gear3Copy=gear3Copy+swap;
			}
		}
		return a;

	}
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==encode)
		{
			String m=message.getText();
			String p=password.getText();
			p=p.toUpperCase();
			m=m.toUpperCase();
			for(int i=0;i<p.length()-1;i++)
			{
				for (int j=i+1;j<p.length();j++ ) 
				{
					if(p.charAt(i)==p.charAt(j))
					{
						JFrame emessage=new JFrame();
						emessage.setSize(300,100);
						emessage.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
						JLabel elabel=new JLabel("Passward cant repeat characters");
						elabel.setFocusable(false);
						elabel.setHorizontalAlignment(JLabel.CENTER);
						emessage.add(elabel);
						emessage.setVisible(true);
						return;
					}	
				}
			}	
			for(int i=0;i<28;i++)
			{
				boolean flag=true;
				for(int j=0;j<p.length();j++)
				{
					if(p.charAt(j)==gear1Copy.charAt(i))
					{
						flag=false;
					}
				}
				if(flag)
				{
					p=p+gear1Copy.charAt(i);
				}
			}
			gear1Copy=p;
			String encoded="";
			for(int i=0;i<m.length();i++)
			{
				encoded=encoded+enigma(m.charAt(i));
			}
			result.setText(encoded);
		}
		else if (e.getSource()==reset) 
		{
			gear1Copy=gear1;
			gear2Copy=gear2;
			gear3Copy=gear3;
		}
	}
	@Override
	public void keyTyped(KeyEvent e) 
	{
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyPressed(KeyEvent e) 
	{
		/*
		if(!isPressed)
		{
			System.out.println("HEY");
			currentLetter=e.getKeyCode();
			isPressed=true;
			for(int i=0;i<28;i++)
			{
				if(e.getKeyChar()==alphaString.charAt(i))
				{
					char ch=alphaString.charAt(i);
					newch=enigma(ch);
					letters[alphaString.indexOf(newch)].setBackground(Color.RED);	
				}
			}
		}
		*/
	}
	@Override
	public void keyReleased(KeyEvent e) throws ArrayIndexOutOfBoundsException
	{
		/*
			if(e.getKeyCode()==currentLetter)
			{
				isPressed=false;
				letters[alphaString.indexOf(newch)].setBackground(new Color(70,70,70));
			}
			*/
	}
}
