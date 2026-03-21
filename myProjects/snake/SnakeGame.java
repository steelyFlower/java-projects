package snake;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.concurrent.ExecutionException;
import guiFormat.GUIFormater;

public class SnakeGame implements KeyListener,ActionListener
{
	JFrame frame;
	JPanel panel;
	JButton restart,auto;
	JLabel[] body=new JLabel[2500];
	JLabel[] midbody=new JLabel[2500];
	JLabel point,pointDisplay;
	int length,s,b,d,n,w,h,x,y,td;
	char move;
	boolean automate,stop;

	SnakeGame()
	{
		x=20;
		y=20;
		td=1;
		d=700/y;
		w=4+d*x;
		while(w>1500)
		{
			d=d-1;
			w=4+d*x;
		}
		h=4+d*y;
		b=d-4;
		
		
		int fw=w+100,fh=h+100;
		frame=new JFrame("Snake Game");
		frame.setBounds(770-fw/2,0,fw,fh);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(true);
		frame.setLayout(null);

		panel=new JPanel();
		panel.setBounds(40,40,w,h);
		panel.setOpaque(true);
		panel.setBackground(Color.GRAY);
		panel.setLayout(null);
		panel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 4));
		frame.add(panel);
		
		int pw=(w*2)/5;
		int bw=w/5;
		restart=new JButton("RESTART");
		GUIFormater.guiFormater(restart);
		restart.setFont(new Font(Font.SANS_SERIF,Font.BOLD,50));
		restart.setBounds(w-bw+40,0,bw,40);
		restart.addActionListener(this);
		frame.add(restart);

		auto=new JButton("MANUAL");
		GUIFormater.guiFormater(auto);
		auto.setFont(new Font(Font.SANS_SERIF,Font.BOLD,50));
		auto.setBounds(w-bw-bw+40,0,bw,40);
		auto.addActionListener(this);
		frame.add(auto);

		pointDisplay=new JLabel();
		pointDisplay.setBounds(40,0,pw,40);
		GUIFormater.guiFormater(pointDisplay);
		pointDisplay.setFont(new Font(Font.DIALOG,Font.BOLD,pw/15));
		frame.add(pointDisplay);
		
		body[0]=new JLabel();
		body[0].setBounds(4,4,b,b);
		body[0].setBackground(Color.RED);
		body[0].setOpaque(true);

		midbody[0]=new JLabel();
		midbody[0].setBounds(0,0,0,0);
		midbody[0].setBackground(Color.RED);
		midbody[0].setOpaque(true);
		
		point=new JLabel();
		
		start();
		
		frame.setVisible(true);
		
		autoplay();
	}
	void start()
	{
		length=0;
		move='d';
		auto.setText("MANUAL");
		automate=false;
		stop=false;
		
		pointDisplay.setText("Point: "+length);
		
		
		panel.add(body[0]);
		
		
		
		panel.add(midbody[length]);
		body[0].setLocation(4, 4);
		midbody[0].setLocation(0,0);
		
		generatePoint();

		frame.addKeyListener(this);
	}

	void moveSnake(char m) 
	{
		int hm=0, vm=0;
		if(m=='w')
		{
			hm=0;
			vm=-1;
		}	
		else if(m=='s')
		{
			hm=0;
			vm=1;
		}
		else if(m=='d')
		{
			hm=1;
			vm=0;
		}
		else if(m=='a')
		{
			hm=-1;
			vm=0;
		}
			if((body[0].getLocation().y==4 && vm==-1) || (body[0].getLocation().y==(h-d)&& vm==1) || (body[0].getLocation().x==(w-d)&&hm==1) || (body[0].getLocation().x==4 && hm==-1))
			{
				gameOver();
			}

			if(body[0].getLocation().y>=4 && body[0].getLocation().y<=(h-d) && body[0].getLocation().x<=(w-d) && body[0].getLocation().x>=4)
			{
				changeLoc();
				int midw,midh,px=0,py=0;
				if(hm!=0)
				{
					midw=4;
					midh=b;
					if(hm==1)
					{
						px=-4;
					}
					else
					{
						px=b;
					}
				}
				else
				{
					midw=b;
					midh=4;
					if(vm==1)
					{
						py=-4;
					}
					else
					{
						py=b;
					}
				}
				body[0].setLocation(body[0].getLocation().x+hm*d, body[0].getLocation().y+vm*d);
				midbody[0].setBounds(body[0].getLocation().x+px,body[0].getLocation().y+py,midw,midh);
				for(int i=1;i<=length && length<this.x*this.y;i++)
				{
					if(body[0].getLocation().equals(body[i].getLocation()))
					gameOver();
				}
				if(body[0].getLocation().equals(point.getLocation()))
				{
					
					newPoint();
				}
			}
	}

	void autoMoveSnake(char m) throws InterruptedException
	{
		int hm=0, vm=0;
		if(m=='w')
		{
			hm=0;
			vm=-1;
		}	
		else if(m=='s')
		{
			hm=0;
			vm=1;
		}
		else if(m=='d')
		{
			hm=1;
			vm=0;
		}
		else if(m=='a')
		{
			hm=-1;
			vm=0;
		}
		if((body[0].getLocation().y==4 && vm==-1) || (body[0].getLocation().y==(h-d)&& vm==1) || (body[0].getLocation().x==(w-d)&&hm==1) || (body[0].getLocation().x==4 && hm==-1))
		{
			gameOver();
		}

		if(body[0].getLocation().y>=4 && body[0].getLocation().y<=(h-d) && body[0].getLocation().x<=(w-d) && body[0].getLocation().x>=4)
		{
			changeLoc();
			int midw,midh,px=0,py=0;
			if(hm!=0)
			{
				midw=4;
				midh=b;
				if(hm==1)
				{
					px=-4;
				}
				else
				{
					px=b;
				}
			}
			else
			{
				midw=b;
				midh=4;
				if(vm==1)
				{
					py=-4;
				}
				else
				{
					py=b;
				}
			}
			for(int i=1;i<=d && !stop;i++)
			{
				body[0].setLocation(body[0].getLocation().x+hm, body[0].getLocation().y+vm);
				midbody[0].setBounds(body[0].getLocation().x+px,body[0].getLocation().y+py,midw,midh);
				Thread.sleep(1+100/d);
			}	
			for(int i=1;i<=length;i++)
			{
				if(body[0].getLocation().equals(body[i].getLocation()))
				gameOver();
			}
			if(body[0].getLocation().equals(point.getLocation()))
			{
				
				newPoint();
			}
		}
	}
	void changeLoc()
	{
		try
		{
		for(int i=length;i>=1 && length<this.x*this.y;i--)
		{
			
			body[i].setLocation(body[i-1].getLocation());
			
		}
		for(int i=length;i>=1 && length<this.x*this.y;i--)
		{
			if(body[i-1].getLocation().x>body[i].getLocation().x)
			{
				midbody[i].setBounds(body[i].getLocation().x+b,body[i].getLocation().y,4,b);
			}
			else if(body[i-1].getLocation().x<body[i].getLocation().x)
			{
				midbody[i].setBounds(body[i].getLocation().x-4,body[i].getLocation().y,4,b);
			}
			else if(body[i-1].getLocation().y>body[i].getLocation().y)
			{
				midbody[i].setBounds(body[i].getLocation().x,body[i].getLocation().y+b,b,4);
			}
			else if(body[i-1].getLocation().y<body[i].getLocation().y)
			{
				midbody[i].setBounds(body[i].getLocation().x,body[i].getLocation().y-4,b,4);
			}
		}
		}
		catch (NullPointerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	void generatePoint()
	{
		Random r=new Random();
		int x,y;
		while (true)
		{
			x=r.nextInt(0,this.x);
			y=r.nextInt(0,this.y);
			if(x==0 && y==0)
				continue;
			else
				break;
		}
		
		point.setBounds(4+x*d,4+y*d,b,b);
		point.setBackground(Color.GREEN);
		point.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		point.setOpaque(true);
			panel.add(point);
	}

	void autoManual() throws InterruptedException
	{
		while(automate)
		{
			autoMoveSnake(move);
		}
	}
	void newPoint()
	{
		length++;
		if(length==(this.x)*(this.y))
		{
			panel.remove(point);
			win();
			return;
		}
		pointDisplay.setText("Point: "+length);
		body[length]=new JLabel();
		body[length].setSize(b,b);
		body[length].setLocation(body[length-1].getLocation());
		body[length].setBackground(GUIFormater.colorFormater(length, (h/d)*(w/d)));
		body[length].setOpaque(true);
		panel.add(body[length]);

		midbody[length]=new JLabel();
		midbody[length].setBounds(0,0,0,0);
		midbody[length].setBackground(GUIFormater.colorFormater(length, (h/d)*(w/d)));
		midbody[length].setOpaque(true);
		panel.add(midbody[length]);
		
		Random r=new Random();
		int x,y;
		while (true && length!=this.x*this.y)
		{
			x=r.nextInt(0,this.x);
			y=r.nextInt(0,this.y);
			boolean f=true;
			for(int i=0;i<=length;i++)
			{
				if(body[i].getLocation().equals(new Point(4+x*d,4+y*d)))
				{
					f=false;
					break;
				}
			}
			if(f)
			{
				point.setLocation(4+x*d,4+y*d);
				break;
			}
		}
	}

	void gameOver()
	{
		frame.removeKeyListener(this);
		automate=false;
		pointDisplay.setText("Point: "+length+"   GAMEOVER");
	}
	
	void win()
	{
		frame.removeKeyListener(this);
		automate=false;
		pointDisplay.setText("Point: "+length+"   YOU WIN");

	}
	void autoplay()
	{

		int[] moveList=HamiltonCycle.hamiltonCycle(y,x);
		try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		while(true && length<this.x*this.y)
		{
			for(int i=0;i<moveList.length && length<this.x*this.y;i++)
			{
				int j=i+1;
				if(i==moveList.length-1)
					j=0;
				if(moveList[i]+1==moveList[j])
				{
					moveSnake('d');
				}
				else if(moveList[i]-1==moveList[j])
				{
					moveSnake('a');
				}
				else if(moveList[i]<moveList[j])
				{
					moveSnake('s');
				}
				else if(moveList[i]>moveList[j])
				{
					moveSnake('w');
				}
				try {
					Thread.sleep(td);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) 
	{
		if(!automate)
		{
			move=e.getKeyChar();
			moveSnake(move);
		}
		else
		{
			move=e.getKeyChar();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource()==restart)
		{
			stop=true;
			automate=false;
			frame.removeKeyListener(this);
			panel.remove(point);
			for(int i=0;i<length;i++)
			{
				panel.remove(body[i]);
				midbody[0].setSize(0,0);
				panel.remove(midbody[i]);
				panel.updateUI();
			}
			start();
			new AutoPlay().execute();
		}
		else if(e.getSource()==auto)
		{
			if(automate)
			{
				automate=false;
				auto.setText("MANUAL");
			}
			else
			{
				automate=true;
				auto.setText("AUTOMATIC");
				new AutoManual().execute();
			}
		}
	}
	class AutoManual extends SwingWorker<String, Void> 
	{
        @Override
        protected String doInBackground() throws Exception 
        {
				autoManual();
				return "";        
        }
    }
	class AutoPlay extends SwingWorker<String, Void> 
	{
        @Override
        protected String doInBackground() throws Exception 
        {
				autoplay();
				return "";        
        }
    }
}
