/*
Normal
Fire
Water
Grass
Electric
Ice
Fighting
Poison
Ground
Flying
Psychic
Bug
Rock
Ghost
Dragon
Dark
Steel
Fairy
*/
package Types;
public class Type
{
	String name ,offenciveSuperEffective[],offenciveResist[],offenciveImmunity[],defensiveSuperEffective[],defensiveResist[],defensiveImmunity[] ;
	Type(String n,String os[],String or[],String oi[],String ds[],String dr[],String di[])
	{
		name =n;
		offenciveSuperEffective=os;
		offenciveResist=or;
		offenciveImmunity=oi;
		defensiveSuperEffective=ds;
		defensiveResist=dr;
		defensiveImmunity=di;
	}
	double typeEffevtiveness(String t)
	{
		for (int i=0;i<offenciveSuperEffective.length;i++)
		{
			if(t==offenciveSuperEffective[i])
			return 2;
		}
		for (int i=0;i<offenciveResist.length;i++)
		{
			if(t==offenciveResist[i])
			return 0.5;
		}
		for (int i=0;i<offenciveImmunity.length;i++)
		{
			if(t==offenciveImmunity[i])
			return 0;
		}
		return 1;
	}
	void showInfo()
	{
		System.out.println("Offencive SuperEffective: ");
		for (int i=0;i<offenciveSuperEffective.length;i++)
		System.out.println (offenciveSuperEffective[i]);
		System.out.println("\nOffencive Resist");
		for (int i=0;i<offenciveResist.length;i++)
		System.out.print (offenciveResist[i]);
		System.out.println("\nOffencive Immunity: ");
		for (int i=0;i<offenciveImmunity.length;i++)
		System.out.print (offenciveImmunity [i]);
		System.out.println("\nDefensice SuperEffective: ");
		for (int i=0;i<defensiveSuperEffective.length;i++)
		System.out.println (defensiveSuperEffective[i]);
		System.out.println("\nDefensice Resist");
		for (int i=0;i<defensiveResist.length;i++)
		System.out.print (defensiveResist[i]);
		System.out.println("\nDefensice Immunity: ");
		for (int i=0;i<defensiveImmunity.length;i++)
		System.out.print (defensiveImmunity [i]);
	}
}