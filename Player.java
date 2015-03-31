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
    if (turn.equals("icebeam")) this.icebeam(enemy);
    if (turn.equals("heal")) this.heal();
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
      if (super.d20() > 10)
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
  public void icebeam(NPC enemy)
  {
    int damage;
    if (super.d20() > 5)
    {
      damage = super.d10();
      System.out.println("An icebeam bursts from your hands dealing " + damage + " damage to the enemy.");
      enemy.takeDamage(damage);
      if (super.d20() > 13)
      {
        enemy.becomeImmobilized();
      }
    }
    else 
    {
      damage = 0;
      System.out.println("The icebeam misses its target.");
    }
  }
  
  public void heal() //method for the player to heal himself
  {
    int regen = super.d10() + 2;
    myHealth += regen;
    System.out.println("You gain " + regen + " health.");
    if (myHealth > 50)
    {
      System.out.println("You gain too much health and explode.");
      myHealth = 0;
    }
  }
}