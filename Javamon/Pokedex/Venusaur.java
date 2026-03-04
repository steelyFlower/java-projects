package Pokedex;
import MoveSet.*;
public class Venusaur extends Pokemon
{
	void setMove()
	{
		Move[] temp={Energyball.set(),};
		moveset=temp;
		moves[0]=moveset[0];
	}
	Venusaur()
	{
		super("Venusaur",80,82,83,100,100,80);
		setMove();
	}
	public static Venusaur get()
	{
		return new Venusaur();
	}
}