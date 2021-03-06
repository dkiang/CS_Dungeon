import java.util.Scanner;
public class Dungeon
{
  public static void main (String[] args)
  {
    Scanner kb = new Scanner(System.in);
    Player player = new Player();
    int count = 1; //count keeps track of how far along in the dungeon the player is
    int superCount = 0; //keeps track of how many dungeons are cleared, progress in the game
    boolean dead = false;
    System.out.println("Type in the name of a spell to cast it.");
    System.out.println("You know the spells fireball, icebeam, thunder, poison, heal.");
    
    
    String monsters[][] = new String[][]{
      {"Goblin", "Bugbear", "Bear", "Minotaur", "Beholder"}, 
      {"Kobold", "Bandit", "Orc", "Drake", "Dragon"},
      {"Imp", "Zombie", "Demon", "Vampire", "Lich"}
    };
    
  
    
    while (count <= 6 && dead == false) //keeps track of the monsters defeated, loop represents the dungeon
    {
      int row = ((int)((Math.random() * 3) + 1) - 1); //randomly picks which row the monster comes from 
      int monsterCol = count - 1; //this value is which collumn the monster comes from
      int col = count - 4; //this value is returned to the player class to determine which spell is earned
      if (count == 1) System.out.println("You arrive at the entrance to a dungeon.");
      String monsterName = monsters[row][monsterCol]; //generates a random monster from the matrix above
      Monster enemy = new Monster(count, monsterName, monsterCol, row);
      System.out.println("A wild " + enemy.getName() + " appears!");
      System.out.println("You have " + player.getHealth() + " health.");
      System.out.println("The " + enemy.getName() + " has " + enemy.getHealth() + " health.");
      
      
      while (player.getHealth() > 0 && enemy.getHealth() > 0) //combat!!
      {
        if (enemy.getHealth() < 0) System.out.println("Enemy is dead.");
        System.out.println("What do you do?"); 
        player.play(enemy, count); //player goes first
        if (!enemy.isImobilized())
        {
          enemy.randomAttack(player, count); //enemy goes if not immobilized
        }
        else
        {
          if ((int)(Math.random() * 20 + 1) > 10)
          {
            enemy.reverseImmobilized(); //imobilization only lasts one turn
            System.out.println("The " + enemy.getName() + " is no longer immobilized.");
          }
          else System.out.println("The enemy is still immobilized.");
        }
        //SERIES OF STATEMENTS KEEP TRACK OF ONGOING EFFECTS
        if (enemy.isOnFire())//if enemy is on fire, takes damage
        {
          System.out.println("The " + enemy.getName() + " is burning alive!");
          enemy.takeDamage(5); 
        }
        //if enemy is poisoned, takes not as much damage
        if (enemy.isPoisoned())
        {
          System.out.println("The " + enemy.getName() + " is hurt by the poison!");
          enemy.takeDamage(2);
        }
      }
      
      
      
      if (enemy.getHealth() <= 0)
      {
        count++;
        System.out.println("You've defeated the " + enemy.getName() + "!");
        player.spellbook(row, col, enemy);
        System.out.println("You progress to the next stage of the dungeon.");
      }
      else if (player.getHealth() <= 0)
      {
        System.out.println("You are dead.");
        dead = true;
      }
      if (count == 6)
      {
        System.out.println("You've beaten the dungeon!");
        count = 1;
        superCount++;
      }
    }
    if (dead == true)
    {
      System.out.println("You've failed. Game over. RIP in piece.");
      System.out.println("You cleared " + superCount + " dungeons and killed " + (superCount * 5 + (count - 1)) + " monsters."); //lets the player know his/her progress
    }
  }
}