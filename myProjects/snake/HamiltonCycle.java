package snake;
import java.util.*;


public class HamiltonCycle 
{
	static int x,y,pos=0;
	static boolean[][] visited;
	static int[][] grid;
	public static void main(String[] args)
	{
		hamiltonCycle(8,9);
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
		int loop=1,max=0;
		
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
			int h=0,v=0,i=0,tempmax=0;
			visited[h][v]=true;
			path[0]=grid[h][v];
			
			int backtrack=0;
			
			while(true)
			{
				tempmax++;
				//System.out.println(tempmax);
				visited[h][v]=true;
				path[i]=grid[h][v];
				
				
				
				boolean[] conditions=checkAllConditions(h,v,i);
				if(conditions[0])
				{
					System.out.println("blocked start ");
					break;
				}
				if(conditions[7]||conditions[8])
				{
					printGrid();
					int back=i-(x*y)/6;
					System.out.println(" going back "+backtrack++);
					if(backtrack>x*y*(x+y)/4)
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
				conditions=checkAllConditions(h,v,i);
				if(conditions[5]|| !conditions[6])
				{
					System.out.println("no space");
					break;
				}
				
				
				int temph=h,tempv=v;
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
				printGrid();
			}
			if(tempmax>max)
				max=tempmax;
			//System.out.println(" max: "+tempmax);
			System.out.println(" loop: "+loop);
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
	static boolean[][] tempVisited;
	static int minSpaces,space;
	static boolean checkAvailableSpacesCount(int h,int v,int visitCount)
	{
		tempVisited=new boolean[x][y];
		for(int i=0;i<x;i++)
		{
			for(int j=0;j<y;j++)
			{
				tempVisited[i][j]=visited[i][j];
			}
		}
		space=0;
		minSpaces=(x*y-visitCount-2);
		recrusiveChecker(h,v);
		if(space>=minSpaces)
		{
			return false;
		}
		return true;
	}
	static void recrusiveChecker(int h,int v)
	{
		boolean up=false,down=false,right=false,left=false;

		if(space>=minSpaces)
		{
			return ;
		}
		if(h+1>=x)
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
			recrusiveChecker(h+1,v);
		}

		if(space>=minSpaces)
		{
			return ;
		}
		if(h-1<0)
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
			recrusiveChecker(h-1,v);
		}
		if(space>=minSpaces)
		{
			return ;
		}


		if(v-1<0)
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
			recrusiveChecker(h,v-1);
		}
		
		if(space>=minSpaces)
		{
			return ;
		}
		if(v+1>=y)
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
			recrusiveChecker(h,v+1);
		}	
		return ;
	}
	static boolean[] checkAllConditions(int h,int v,int i)
	{
		boolean startblock=false ,end=false,availSpace=true,sideChecks=false;
		if(i==x*y-1 )
		{
			return new boolean[]{true};
		}
		if((visited[0][1] && visited[1][0]))
		{
			startblock=true;
		}
		boolean up=false,down=false,right=false,left=false,rend=false,lend=false,uend=false,dend=false;
		int rspace=0,lspace=0,uspace=0,dspace=0;
		if(h+1>=x)
		{
			right=true;
		}
		else if(visited[h+1][v] )
		{
			right=true;
		}
		else if(checkAvailableSpacesCount(h+1,v,i))
		{
			System.out.println("right ");
			rend=true;
			right=true;
		}
		else 
		{
			rspace=space;
		}
		if(v+1>=y)
		{
			down=true;
		}
		else if(visited[h][v+1])
		{
			down=true;
		}
		else if(checkAvailableSpacesCount(h,v+1,i))
		{
			System.out.println("down ");
			dend=true;
			down=true;
		}
		else
		{
			dspace=space;
		}
		if(h-1<0)
		{
			left=true;
		}
		else if(visited[h-1][v])
		{
			left=true;
		}
		else if(checkAvailableSpacesCount(h-1,v,i))
		{
			System.out.println("left");
			lend=true;
			left=true;
		}
		else
		{
			lspace=space;
		}
		if(v-1<0)
		{
			up=true;
		}
		else if(visited[h][v-1])
		{
			up=true;
		}
		else if(checkAvailableSpacesCount(h,v-1,i))
		{
			System.out.println("up ");
			uend=true;
			up=true;
		}
		else
		{
			uspace=space;
		}
		if(up && down && right && left)
		{
			sideChecks=true;
		}
		int m1=Math.max(rspace,lspace);
		int m2=Math.max(dspace,uspace);
		int m=Math.max(m1, m2);
		if (m!=0)
		{
			if((rspace+lspace+uspace+dspace)%m!=0)
			{
				availSpace=false;
			}
		}
		boolean finalcheck=false;
		if(rend||lend||uend||dend)
		finalcheck=true;
		boolean[] conditions= {end,right,left,down,up,sideChecks,availSpace,finalcheck,startblock};
		return conditions;
	}
}
