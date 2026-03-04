package Pokedex;
import java.util.Scanner;
import MoveSet.Move;
public class Pokemon
{
	public Scanner s=new Scanner(System.in);
	public String name;
	public Move[] moveset;
	public Move[] moves=new Move[4];
	public int hp,attack,defense,specialAttack,specialDefense,speed,level;
	Pokemon(String n,int h,int a,int d,int sa,int sd,int s)
	{
		name=n;
		hp=h;
		attack=a;
		defense=d;
		specialAttack=sa;
		specialDefense=sd;
		speed=s;
		setLevel();
	}
	public void showHp()
	{
		System.out.println(name +"'s hp: "+hp);
	}
	public void setLevel()
	{
		System.out.print("Enter the level of "+name+": ");
		level =s.nextInt();
	}
}


