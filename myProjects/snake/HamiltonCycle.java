package snake;
import java.util.*;


public class HamiltonCycle 
{
	static int x,y,pos=0;
	static boolean[][] visited;
	static int[][] grid;
	public static void main(String[] args)
	{
		hamiltonCycle(12,12);
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
			printGrid();
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
			
			int backtrack=0,max=i;
			
			while(true)
			{
				visited[h][v]=true;
				path[i]=grid[h][v];
				
				
				
				boolean[] conditions=sideChecker(h,v,i+1);
				if(conditions[0])
				{
					System.out.println("complete");
					break;
				}
			
				if(conditions[5]||conditions[6]||conditions[8])
				{
					//printGrid();
					int back=i-Math.max(x, y)/2;
					//System.out.println(" going back "+backtrack);
					//if(i<max)
						backtrack++;
					//else 
						//max=i;
					if(backtrack>5*Math.max(x,y))
					{
						back=i-(x*y)/4;
						backtrack=0;
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
					int back=i-Math.max(x, y);
					//System.out.println(" going back "+backtrack);
					//if(i<max)
						backtrack++;
					//else 
						//max=i;
					if(backtrack>5*Math.max(x,y))
					{
						back=i-3*(x*y)/4;
						backtrack=0;
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
				if(i%20==0)
				{
				printGrid();
				System.out.println("_________________________________");
				}
			}
		}
		
		for(int i=0;i<path.length;i++)
		{
			System.out.println(path[i]);
		}
		
		printGrid();
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
	static void printGrid()
	{
		for(int i=0;i<x;i++)
		{
			for(int j=0;j<y;j++)
			{
				if(visited[i][j])
				{
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
}
