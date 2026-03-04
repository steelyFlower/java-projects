package MoveSet;
public class Firepunch extends Move
{
	Firepunch()
	{
		super("Fire Punch",1,75);
	}
	public static Firepunch set() 
	{
     	return new Firepunch();       
    }
}