package MoveSet;
public class Move
{
	public String name;
	public int base;
	public Catagory catagory;
	Move(String n,int c,int b,int prio)
	{
		name=n;
		switch(c)
		{
		case 1:catagory=Catagory.physical;
			break;
		case 2:catagory=Catagory.special;
			break;
		case 3:catagory=Catagory.status;
			break;
		}
		base=b;
	}
}



ispower=true && type =grass 
return 0;