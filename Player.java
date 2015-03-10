import java.util.Scanner;
public class Player extends NPC
{
  Scanner kb = new Scanner(System.in);
  int myHealth = 20;
  public Player()
  {
  }
  
  public void play(NPC enemy)
  {
    String turn = kb.nextLine();
    if (turn.equals("health"))
    {
      System.out.println("Your health is " + myHealth + ".");
                         }
    if (turn.equals("fireball")) this.fireball(enemy);
    else 
    {
      System.out.println("You suffer a headache as you attempt to draw upon mystical knowledge unavailable to you.");
    }
  }
  
  public void takeDamage(int damage) //methods to lower class variables
  {
    myHealth -= damage;
    System.out.println("You take " + damage + " damage.");
    System.out.println("You have " + myHealth + " health left.");
  }
  
  public void fireball(NPC enemy)
  {
    int damage;
    if (super.d20() > 5)
    {
      damage = super.d10();
      System.out.println("You send a fireball forth which crashes into your foe for " + damage + " damage.");
      enemy.takeDamage(damage);
      if (super.d20() > 15)
      {
        enemy.becomeOnFire();
      }
    }
    else 
    {
      damage = 0;
      System.out.println("The fireball misses its target.");
    }
  }
}