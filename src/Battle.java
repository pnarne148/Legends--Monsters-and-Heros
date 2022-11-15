/**
 * This class represents Battle. It is created when player steps on common space and rolls an odd score.
 * It has all the actions from start to finish of a battle
 */

import java.util.ArrayList;
import java.util.Random;

public class Battle {

    ArrayList<Hero> heros;

    ArrayList<Monster> monsters;
    MonsterCollection mc;
    HeroCollection hc;
    ItemCollection ic;
    boolean gameOver=false;

    Battle(ArrayList<Hero> heros)
    {
        this.heros = heros;
    }

    private void initialise() {
        mc = new MonsterCollection();
        hc = new HeroCollection();
        ic = new ItemCollection();

        int level=0;
        for(Hero hero :heros)
            if(level<hero.getLevel())
                level=hero.getLevel();

        monsters = mc.getRandomMonsters(level,heros.size());
        System.out.println("Battle Instructions: \n> A battle consists of multiple rounds, where the heroes and monsters each make moves. \n" +
                "> The fight ends when the HP of either all of the monsters or all of the heroes is zero.\n" +
                "> The heroes move first in each round. You should chose a hero, attack type and monster in each round\n" +
                "> Monster goes next, a random monster attacks a random hero in each round. Random selection is done by rolling dice\n");
        System.out.println("The Monster(s) you fight are: ");
        System.out.println(mc.getCollectionString(monsters));
        Menu.askEnter("<Press enter to continue>");
    }

    public void start()
    {
        initialise();
        while(!gameOver)
        {
            this.toString();
            Hero hero = selectHero();
            int choice=0;
            do {
                choice = getMove(hero);
            }while(!action(choice,hero));
        }
    }
    
    public boolean action(int choice, Hero hero)
    {
        switch (choice)
        {
            case 1:
                attack(hero, selectMonster());
                monsterAttack();
                return true;
            case 2:
                if(castSpell(selectSpell(hero), hero)) {
                    monsterAttack();
                    return true;
                }
                else
                    return false;
            case 3:
                if(usePotion(selectPotion(hero),hero)) {
                    monsterAttack();
                    return true;
                }
                else
                    return false;
            case 4:
                if(equipItems(hero)) {
                    monsterAttack();
                    return true;
                }
                return false;
            case 5:
                showStats();
                return true;
        }
        return true;
    }

    private void showStats() {
        System.out.println("Monster stats:");
        System.out.println(mc.getCollectionString(monsters));
        System.out.println("\nHero Stats:");
        System.out.println(hc.getCollectionString(heros));

    }

    private boolean equipItems(Hero hero) {
        System.out.println("\nWhat do you want to equip?\n" +
                "<1> Armor\n" +
                "<2> Weapon");
        int choice = Menu.getInt("Your choice: ",1,2,1);
        if(choice==1)
        {
            ArrayList<Armor> armors = hero.inventory.getArmors();
            if(armors.size()==0) {
                System.out.println("~No Armors in the inventory~");
                return false;
            }
            System.out.println(ic.getArmorCollectionString(armors));
            int index = Menu.getInt("Your choice: ",1,armors.size());
            hero.inventory.equipArmor(armors.get(index-1));
        }
        if(choice==2)
        {
            ArrayList<Weapon> weapons = hero.inventory.getWeapons();
            if(weapons.size()==0) {
                System.out.println("~No Weapons in the inventory~");
                return false;
            }
            System.out.println(ic.getWeaponCollectionString(weapons));
            int index = Menu.getInt("Your choice: ",1,weapons.size());
            hero.inventory.equipWeapon(weapons.get(index-1));
        }
        return true;
    }

    private boolean usePotion(Potion potion, Hero hero) {
        if(potion==null)
            return false;

        String attributes = potion.getAttributeAffected();

        if(attributes.indexOf("Health")>0)
            hero.setHP(hero.getHP()+potion.attributeIncrease);

        if(attributes.indexOf("Strength")>0)
            hero.strength+=(+potion.attributeIncrease);

        if(attributes.indexOf("Mana")>0)
            hero.MP+=(+potion.attributeIncrease);

        if(attributes.indexOf("Agility")>0)
            hero.agility+=(+potion.attributeIncrease);

        if(attributes.indexOf("Dexterity")>0)
            hero.dexterity+=(+potion.attributeIncrease);

//        if(attributes.indexOf("Defense")>0)
//            hero.+=(+potion.attributeIncrease);

//todo: print updated hero
        System.out.println(hero.name+" after using potion:");
        System.out.println("HP: "+hero.getHP()+"  Strength: "+hero.strength+"   Mana: "+hero.MP+"   Agility: "+hero.agility+"   Dexterity: "+hero.dexterity);

        return true;
    }

    private Potion selectPotion(Hero hero) {
        if(hero.inventory.getPotions().size()==0)
        {
            System.out.println("~No potions in the inventory~");
            return null;
        }
        System.out.println(ic.getPotionCollectionString(hero.inventory.getPotions()));
        return hero.inventory.getPotions().get(Menu.getInt("Your choice:",1,hero.inventory.getPotions().size(),1)-1);
    }

    private boolean castSpell(Spell spell,  Hero hero) {
        if(spell==null)
            return false;
        if(hero.MP<spell.manaCost)
        {
            System.out.println("Mana required "+spell.manaCost+", "+hero.name+"'s mana: "+hero.MP);
            return false;
        }
        Monster monster = selectMonster();

        int damage = (int) (((hero.dexterity*1.0)/50000)* spell.damage);
        damage = (int) (damage-(monster.defense*0.05));
        if(damage<0)
            damage=0;

        System.out.println(hero.name+" used "+spell.name+" spell on "+monster.name+" for "+damage+" damage!");
        monster.HP-=damage;

        if(spell.type=="Ice")
        {
            System.out.println(spell.name+" spell also reduced damage of "+monster.name+" by "+(monster.damage- (int) (monster.damage*0.9)));
            monster.damage= (int) (monster.damage*0.9);
        } else if (spell.type=="Fire") {
            System.out.println(spell.name+" spell also reduced defense of "+monster.name+" by "+(monster.defense- (int) (monster.defense*0.9)));
            monster.defense= (int) (monster.defense*0.9);
        } else {
            System.out.println(spell.name+" spell also reduced dodge chance of "+monster.name+" by "+(monster.dodge- (int) (monster.dodge*0.9)));
            monster.dodge= (int) (monster.dodge*0.9);
        }
        return true;
    }

    private Spell selectSpell(Hero hero) {
        if(hero.inventory.getSpells().size()==0)
        {
            System.out.println("~No spells in the inventory~");
            return null;
        }
        System.out.println(ic.getSpellCollectionString(hero.inventory.getSpells()));
        return hero.inventory.getSpells().get(Menu.getInt("Your choice:",1,hero.inventory.getSpells().size(),1)-1);
    }

    private void monsterAttack() {
        Monster monster = getRandomMonster();
        if(monster==null)
            return;
        Hero hero = getRandomHero();
        int damage = (int) (monster.damage*0.05);

        if(hero.inventory.equippedArmor!=null)
        {
            damage = (int) (damage-(hero.inventory.equippedArmor.damageReduction*0.1));
            if(damage<0)
                damage=0;
        }
        System.out.println("Monster Attack:");
        if(dodged(hero.agility*0.002*0.5))
        {
            System.out.println(hero.name+" dodged the attack by "+monster.name+"! Attack damage is 0!");
        }
        else{
            System.out.println(monster.name+" attacked "+hero.name+" for "+damage+" damage!");
            if(hero.getHP()-damage<0)
                hero.setHP(0);
            else
                hero.setHP(hero.getHP()-damage);
            if(hero.getHP()==0)
                System.out.println(hero.name+" is defeated!!");
        }
        checkWin();
    }

    private void checkWin() {
        if(getRandomMonster()==null)
        {
            System.out.println("Heros Won!!");
            herosWon();
            gameOver=true;
        }
        else if (getRandomHero()==null)
        {
            System.out.println("Monsters Won!!");
            gameOver=true;
        }
    }

    private void herosWon() {
        for(Hero h: heros)
        {
            if(h.getHP()>0)
                h.gold+=heros.size()*100;
            else
                h.setHP(h.level*50);

            h.setHP((int) (h.getHP()*1.1));
            h.MP = (int) (h.MP*1.1);
            h.experience+=heros.size()*2;

            if(h.experience>h.level*10)
                h.levelup();
        }
    }

    private Monster getRandomMonster() {
        ArrayList<Monster> aliveMonsters = getAliveMonsters();
        if(aliveMonsters.size()>0)
            return aliveMonsters.get(new Random().nextInt(aliveMonsters.size()));
        else
            return null;
    }

    private Hero getRandomHero() {
        ArrayList<Hero> aliveHero = getAliveHeros();
        if(aliveHero.size()>0)
            return aliveHero.get(new Random().nextInt(aliveHero.size()));
        return null;
    }

    private void attack(Hero hero, Monster monster) {
        int damage = hero.strength;
        for(int i=0;i<hero.inventory.equippedWeapon.size();i++)
            damage+=hero.inventory.equippedWeapon.get(i).damage;

        damage= (int) (damage*0.05);

        damage = (int) (damage-(monster.defense*0.05));
        if(damage<0)
            damage=0;

        System.out.println("Hero Attack:");
        if(dodged(monster.dodge*0.01))
        {
            System.out.println(monster.name+" dodged the attack! Attack damage is 0!");
        }
        else{
            System.out.println(hero.name+" attacked "+monster.name+" for "+damage+" damage!");
            monster.HP-=damage;
            if(monster.HP<0)
                monster.HP=0;
        }
        if(monster.HP==0)
            System.out.println(monster.name+" is defeated!!");

        checkWin();
    }

    private boolean dodged(double chance)
    {
        double dodge = Math.random();
        return dodge<chance;
    }

    private Monster selectMonster() {
        System.out.println("\nPick a monster you want to attack:");
        System.out.println(mc.getCollectionString(getAliveMonsters(),true));
        return getAliveMonsters().get(Menu.getInt("Your choice: ",1,monsters.size())-1);
    }

    ArrayList<Hero> getAliveHeros()
    {
        ArrayList<Hero> alive = new ArrayList<>();
        for(Hero h:heros)
        {
            if(h.getHP()>0)
                alive.add(h);
        }
        return alive;
    }

    ArrayList<Monster> getAliveMonsters()
    {
        ArrayList<Monster> alive = new ArrayList<>();
        for(Monster m:monsters)
        {
            if(m.getHP()>0)
                alive.add(m);
        }
        return alive;
    }

    private int getMove(Hero hero) {
        System.out.println("\nSelect a move for "+hero.name);
        System.out.println("<1> Attack\n" +
                "<2> Cast Spell\n" +
                "<3> Use Potion\n" +
                "<4> Equip items\n" +
                "<5> Show stats");
        return Menu.getInt("Your choice: ",1,6,1);
    }

    private Hero selectHero() {
        System.out.println("Pick a hero to start the fight:");
        System.out.println(hc.getCollectionString(getAliveHeros()));
        return heros.get(Menu.getInt("Your choice: ",1,heros.size())-1);
    }

    @Override
    public String toString() {
        String str="\nBattle Scenario: ";
        System.out.println(str);
        System.out.format("%25s%18s%35s%18s%n", new String[] { "HEROS", "HP", "MONSTERS" , "HP"});
        for(int i=0;i<heros.size();i++)
        {
            System.out.format("%25s%18s%35s%18s%n", new String[] {heros.get(i).name, getProgress(heros.get(i).getHP(),heros.get(i).getLevel()*100),
                    monsters.get(i).name , getProgress(monsters.get(i).HP, monsters.get(i).level*100)});
        }

        System.out.println();
        System.out.println();
        return str;
    }

    String getProgress(int value,int max)
    {
        String progress="";
        String empty = "░"; // U+2591 Unicode Character
        String full = "█";

        value = value*100;
        value = value/max;
        for(int i=0;i<value/10;i++)
        {
            progress+=full;
        }
        for(int i=0;i<10-value/10;i++)
            progress+=empty;

        return progress+" "+value;
    }
}
