package Pokedex;
import MoveSet.*;
public class Charizard extends Pokemon
{
	Charizard()
	{
		super("Charizard",78,84,78,109,85,100);
		setMove();

	}
	void setMove()
	{
		Move[] temp={Flamepunch.set()};
		moveset=temp;
		moves[0]=moveset[0];
	}
	public static Charizard get()
	{
		return new Charizard();
	}
}