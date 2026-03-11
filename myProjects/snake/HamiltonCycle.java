package snake;
import java.util.*;


public class HamiltonCycle 
{
	static int x,y,pos=0;
	static boolean[][] visited;
	static int[][] grid;
	public static void main(String[] args)
	{
		hamiltonCycle(20,20);
	}
	static public int[] hamiltonCycle(int row,int col)
	{
		x=row;
		y=col;
		visited=new boolean[x][y];
		grid=new int[x][y];
		Random ran=new Random();
		if((x*y)%2!=0)
		{
			System.out.println("Hamilton cycle not possible");
			System.exit(0);
		}
		int[] path=new int[x*y];
		for(int i=0;i<x;i++)
		{
			for (int j=0; j<y; j++) 
			{
				visited[i][j]=false;
				grid[i][j]=pos;
				pos++;
			}
		}
		int loop=1;
		
		while(!isCompletelyVisited())
		{
			printGrid(-1,-1);
			System.out.println("Loop: "+loop);
			loop++;
			for(int i=0;i<x;i++)
			{
				for (int j=0; j<y; j++) 
				{
					visited[i][j]=false;
				}
			}
			int h=0,v=0,i=0;
			visited[h][v]=true;
			path[0]=grid[h][v];
			
			int backtrack=0,backtrack2=0,max=i,endtrack=0;
			
			while(true)
			{
				visited[h][v]=true;
				path[i]=grid[h][v];
				
				//printGrid(h,v);
				//System.out.println("_________________________________");
				/*
				if(holeCheck(h,v))
				{
					//printGrid(h,v);
					//System.out.println("_________________________________");
					int back=i-3;
					if(0>back)
						back=0;
					while(i>back)
					{
						visited[h][v]=false;
						h=path[i]/y;
						v=path[i]%y;
						i--;
						
					}
					i++;
				}
				*/
				
				boolean[] conditions=sideChecker(h,v,i+1);
				if(conditions[0])
				{
					System.out.println("complete");
					break;
				}
				
				
				
				if(conditions[5]||conditions[6]||conditions[8]||holeCheck(h,v))
				{
					int back;
					
					if(conditions[5]||conditions[6]||conditions[8])
						back=i-6;
					else
					
						back=i-3;
						backtrack++;
						if(backtrack%10==0)
						{
						printGrid(h,v);
						System.out.println("_________________________________");
						}
					if(backtrack>300)
					{
						back=i-(x*y)/4;
						backtrack=0;
						backtrack2++;
					}
					
					if(backtrack2>2000)
					{
						back=i-(x*y)/2;
						backtrack2=0;
					}
					
					if(0>back)
						back=0;
					while(i>back)
					{
						visited[h][v]=false;
						h=path[i]/y;
						v=path[i]%y;
						i--;
						
					}
					i++;
					
				}
				
				
				
				conditions=sideChecker(h,v,i+1);
				while(conditions[7]||conditions[8])
				{
					int back=i-Math.max(x,y);
					//System.out.println(" going back "+backtrack);
					//if(i<max)
						endtrack++;
						if(endtrack%100==0)
						{
						//printGrid(h,v);
						//System.out.println("_________________________________");
						}
					//else 
						//max=i;
					if(endtrack>5*Math.max(x,y))
					{
						back=i-2*(x*y)/3;
						endtrack=0;
					}
					if(0>back)
						back=0;
					while(i>back)
					{
						visited[h][v]=false;
						h=path[i]/y;
						v=path[i]%y;
						i--;
						
					}
					i++;
					conditions=sideChecker(h,v,i+1);
					if(!conditions[7]&&conditions[8])		
					{
						break;
					}
				}
				conditions=sideChecker(h,v,i+1);
				
				
			
				ArrayList<Integer> moves=new ArrayList<>();
				moves.add(0);
				moves.add(1);
				moves.add(2);
				moves.add(3);
				while(true)
				{
					int n=moves.get(ran.nextInt(moves.size()));  
					if(n==0)
					{
						if(!conditions[1])
						{
							h++;
							break;
						}
						else
							moves.remove(moves.indexOf(n));
					}
					else if(n==1)
					{
						if(!conditions[2])
						{
							h--;
							break;
						}
						else
							moves.remove(moves.indexOf(n));
					}
					else if(n==2)
					{
						if(!conditions[3])
						{
							v++;
							break;
						}
						else
							moves.remove(moves.indexOf(n));
					}
					else if(n==3)
					{
						if(!conditions[4])
						{
							v--;
							break;
						}
						else
							moves.remove(moves.indexOf(n));
					}
				}
				i++;
				
				
			}
		}
		
		for(int i=0;i<path.length;i++)
		{
			System.out.println(path[i]);
		}
		
		printGrid(-1,-1);
		return path;
	}
	static boolean isCompletelyVisited()
	{
		for(int i=0;i<x;i++)
		{
			for (int j=0; j<y; j++) 
			{
				if(!visited[i][j])
					return false;
			}
		}
		return true;
	}
	static void printGrid(int h,int v)
	{
		for(int i=0;i<x;i++)
		{
			for(int j=0;j<y;j++)
			{
				if(visited[i][j])
				{
					if(h==i && v==j)
					{
						System.out.print("|00");
					}
					else
						System.out.print("|[]");
				}
				else
				{
					System.out.print("|  ");
				}
			}
			System.out.println("|");
		}
	}
	
	static boolean[] sideChecker(int h,int v,int s)
	{
		boolean[][] tempVisited=new boolean[x][y];
		for(int i=0;i<x;i++)
		{
			for(int j=0;j<y;j++)
			{
				tempVisited[i][j]=visited[i][j];
			}
		}
		boolean up=false,down=false,right=false,left=false;
		boolean startblock=false ,end=false,divideCheck=false,sideChecks=false;
		if(s==x*y)
		{
			return new boolean[]{true};
		}
		if((visited[0][1] && visited[1][0]))
		{
			startblock=true;
		}
		if(h+1>=x)
		{
			right=true;
		}
		else if(tempVisited[h+1][v])
		{
			right=true;
		}
		if(h-1<0)
		{
			left=true;
		}
		else if(tempVisited[h-1][v])
		{
			left=true;
		}
		if(v-1<0)
		{
			up=true;
		}
		else if(tempVisited[h][v-1])
		{
			up=true;
		}
		if(v+1>=y)
		{
			down=true;
		}
		else if(tempVisited[h][v+1])
		{
			down=true;
		}
		if(right&&left&&!up&&!down)
			divideCheck=true;
		if(!right&&!left&&up&&down)
			divideCheck=true;
		if(up && down && right && left)
		{
			sideChecks=true;
		}
		for(int i=0;i<x;i++)
		{
			for(int j=0;j<y;j++)
			{
				tempVisited[i][j]=visited[i][j];
			}
		}
		int space=recrusiveChecker(h,v,0,tempVisited);
		//System.out.println(space);
		boolean spacecount=false;
		if(space<(x*y-s))
			spacecount=true;
		//boolean hole=holeCheck(h,v);
		boolean[] conditions= {end,right,left,down,up,startblock,divideCheck,sideChecks,spacecount};
		return conditions;	
	}
	static int recrusiveChecker(int h,int v,int space,boolean[][] tempVisited)
	{
		if(h>=x||h<0||v>=y||v<0)
			return space;
		boolean up=false,down=false,right=false,left=false;
		
		
		if(h>=x-1)
		{
			
			right=true;
		}
		else if(tempVisited[h+1][v])
		{
			right=true;
		}
		if(!right)
		{
			space++;
			tempVisited[h+1][v]=true;
			
			space=recrusiveChecker(h+1,v,space,tempVisited);
		}

		if(h<=0)
		{
			
			left=true;
		}
		else if(tempVisited[h-1][v])
		{
			left=true;
		}
		if(!left)
		{
			space++;
			tempVisited[h-1][v]=true;
			space=recrusiveChecker(h-1,v,space,tempVisited);
		}
		
		if(v<=0)
		{
			up=true;
		}
		else if(tempVisited[h][v-1])
		{
			up=true;
		}
		if(!up)
		{
			space++;
			tempVisited[h][v-1]=true;
			space=recrusiveChecker(h,v-1,space,tempVisited);
		}
		
		if(v>=y-1)
		{
			down=true;
		}
		else if(tempVisited[h][v+1])
		{
			down=true;
		}
		if(!down)
		{
			space++;
			tempVisited[h][v+1]=true;
			space=recrusiveChecker(h,v+1,space,tempVisited);
		}	
		return space;
	}
	static boolean holeCheck(int h,int v)
	{
		boolean[][] tempVisited=new boolean[x][y];
		for(int i=0;i<x;i++)
		{
			for(int j=0;j<y;j++)
			{
				tempVisited[i][j]=visited[i][j];
			}
		}
		tempVisited[h][v]=false;
		int xlow=h-3,ylow=v-3,xup=h+3,yup=v+3;

		if(xlow<0)
			xlow=0;
		if(ylow<0)
			ylow=0;
		if(xup>x-1)
			xup=v-1;
		if(yup>y-1)
			yup=y-1;
		for(int i=xlow;i<=xup;i++)
		{
			for(int j=ylow;j<=yup;j++)
			{
				int count=0;
				if((i==0&&j==1)||(i==1&&j==0)||tempVisited[i][j]||(i==h&&j==v))
					continue;
				if(i-1<0)
				{
					count++;
				}
				else if(tempVisited[i-1][j])
				{
					count++;
				}
				if(j-1<0)
				{
					count++;
				}
				else if(tempVisited[i][j-1])
				{
					count++;
				}
				if(i+1>=x)
				{
					count++;
				}
				else if(tempVisited[i+1][j])
				{
					count++;
				}
				if(j+1>=y)
				{
					count++;
				}
				else if(tempVisited[i][j+1])
				{
					count++;
				}
				if(count==3)
					return true;
			}
		}
		return false;
	}
}
