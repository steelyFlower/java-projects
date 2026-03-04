package MoveSet;
public class Energyball extends Move
{
	Energyball()
	{
		super("Energy Ball",2,90);
	}
	public static Energyball set() 
	{
     	return new Energyball();       
    }
}