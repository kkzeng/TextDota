package textdota;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Kenneth
 */
public class TextDota {

    /**
     */
    public static String progress = "0"; //acts as indicator of progress - save/load function
    public static Scanner userInput = new Scanner(System.in);
    public static int heroChoice; //holds the number identifier for the hero chosen, used to retrieve stats from heroStats array
    public static int[][] radiantHeroStats = new int[6][3];//holds different stats of heroes and spells for radiant
    public static int[][] direHeroStats = new int[6][4];//holds different stats of heroes and spells for dire
    public static String[] heroName = {"Lina The Slayer", "Nyx Assassin", "Morphling"};
    public static String[] enemyName = {"Queen of Pain", "Death Prophet", "Doom"};
    public static String heroTitle; //holds the name identifier for the hero chosen.
    public static int laneChoice; //holds number identifier for lane choice
    public static String[] laneConditions = {"You walk up the top stone path surrounded by flowers and fauna.\nHowever, the nature seems to end, fading into a eerie darkness. You sense this is the dire.", "You walk up the middle stone path, surrounded by nature. Soon, you reach the river, what lies beyond appears to be darkness. You sense that this is the dire.", "You walk down the bottom stone path. Nature blooming around you, you feel safe as the darkness of the Dire appears to be far away."}; 
    public static String gameExit = "No"; //gameloop condition
    public static int passCorrect = 0; //flag for the passGate()
    public static String direHeroName; //holds the name of the hero you are battling - retrieved by getRandDire()
    public static int direHealth, direMana, radiantHealth, radiantMana, damageCount, attackVariable; // initializing the temporary variables holding battle conditions
    public static int deathCount; //counts the amount of deaths before reverting to last checkpoint
    public static int loopFlag; //handles error checking
    public static String revertCheckpoint;

    public static void main(String[] args) {
        //Populating the 2D arrays with hero stats   
        radiantHeroStats[0][0] = 1200; //Lina's Health
        radiantHeroStats[0][1] = 850; //Nyx Assassin's Health
        radiantHeroStats[0][2] = 900; //Morphling's Health
        radiantHeroStats[1][0] = 1200; //Lina's Mana Pool
        radiantHeroStats[1][1] = 700; //Nyx Assassin's Mana Pool
        radiantHeroStats[1][2] = 400;  //Morphling's Mana Pool
        radiantHeroStats[2][0] = 300; //Lina's First Skill
        radiantHeroStats[2][1] = 300; //Nyx Assassin's First Skill
        radiantHeroStats[2][2] = 350; //Morphling's First Skill
        radiantHeroStats[3][0] = 150; //Lina's Second Skill
        radiantHeroStats[3][1] = 230; //Nyx Assassin's Second Skill
        radiantHeroStats[3][2] = 230; //Morphling's Second Skill
        radiantHeroStats[4][0] = 0; //Lina's Third Skill
        radiantHeroStats[4][1] = 0; //Nyx Assassin's Third Skill
        radiantHeroStats[4][2] = 0; //Morphling's Third Skill
        radiantHeroStats[5][0] = 1600; //Lina's Ultimate
        radiantHeroStats[5][1] = 0; //Nyx Assassin's Ultimate
        radiantHeroStats[5][2] = 230; //Morphling's Ultimate

        direHeroStats[0][0] = 1200; //Queen Of Pain's Health
        direHeroStats[0][1] = 1000; //Death Prophet's Health
        direHeroStats[0][2] = 2000; //Doom's Health
        direHeroStats[0][3] = 5000; //Boss's Health
        direHeroStats[1][0] = 700; //QOP's Mana Pool
        direHeroStats[1][1] = 700; //DP's Mana Pool
        direHeroStats[1][2] = 300; //Doom's Mana Pool
        direHeroStats[1][3] = 99999; //Boss's Mana Pool
        direHeroStats[2][0] = 30; //QOP's First Skill
        direHeroStats[2][1] = 350; //DP's First Skill
        direHeroStats[2][2] = 100; //Doom's First Skill
        direHeroStats[2][3] = 400; //Boss's First Skill
        direHeroStats[3][0] = 0; //QOP's Second Skill
        direHeroStats[3][1] = 0; //DP's Second Skill
        direHeroStats[3][2] = 100; //Doom's Second Skill
        direHeroStats[3][3] = 650; //Boss's Second Skill
        direHeroStats[4][0] = 300; //QOP's Third Skill
        direHeroStats[4][1] = 0;//DP's Third Skill
        direHeroStats[4][2] = 0; //Doom's Third Skill
        direHeroStats[4][3] = 0; //Boss's Thir Skill
        direHeroStats[5][0] = 490;//QOP's Ultimate
        direHeroStats[5][1] = 300;//DP's Ultimate
        direHeroStats[5][2] = 500; //Doom's Third Skill  
        direHeroStats[5][3] = 700; //Boss's Ultimate Skill
        deathCount = 0;
        
        System.out.print("Welcome to RETRO TEXT DOTA by Kenneth Zeng!\n\nInstructions:\n\nThis is a text adventure game in which you play as the Radiant and fight against the deadly, evil Dire.\nYou will be given the option to perform different actions throughout the storyline, each choice affecting the outcome of the game.\nThe main objective is to destroy the Dire's ancient building so you can end the war!\nOh and don't die in battles because it is a sin to revive a dead warrior and so the game will end (other deaths, I may be able to revert for you).\nEverything else is a surprise.\nGood luck hero!\n\n");
        //The "Game" Loop        
        while (gameExit.equalsIgnoreCase("No")) {
            outerswitch: //labelled so I can break out of the outer switch from an inner loop
            switch (progress) {
                case "0":
                    System.out.println("You wake up surrounded by mist, only to realize that you are in the fountain of the Radiant.\nHowever, when you look into the water, the reflection is of nothing.\nYou are a spirit wandering the realms of this world.\nYou see 3 dormant heroes, which one will you inhabit?");
                    progress = "1";
                    break;

                case "1":
                    System.out.println("You may choose from the following:\n\n[1]Lina\n[2]Nyx Assassin,\n[3]Morphling\n\nEnter your choice:");
                    progress = "1A";
                    break;

                case "1A":
                    do {
                        try {
                            heroChoice = userInput.nextInt();
                            heroTitle = heroName[heroChoice - 1];
                            loopFlag = 0;
                        } catch (java.util.InputMismatchException | java.lang.ArrayIndexOutOfBoundsException e) {
                            System.out.println("Invalid choice! You must choose from the options listed! You may choose from the following:\n\n[1]Lina\n[2]Nyx Assassin,\n[3]Morphling\n\nPlease try again:");
                            userInput.nextLine();
                            loopFlag = 1;
                        }
                    } while (loopFlag == 1);

                    System.out.println("\nYou have chosen" + " " + heroTitle);
                    progress = "2";
                    break;

                case "2":
                    System.out.println("Now, hero, you must choose a lane." + " " + heroTitle + ", which lane will you explore?\nYou may choose from:\n\n[1]Top\n[2]Middle\n[3]Bottom");
                    progress = "2A";
                    break;

                case "2A":
                    do {
                        try {
                            laneChoice = userInput.nextInt();
                            System.out.println(laneConditions[laneChoice - 1]);
                            loopFlag = 0;
                        } catch (java.util.InputMismatchException | java.lang.ArrayIndexOutOfBoundsException e) {
                            System.out.println("Invaid choice! You must choose from the options listed! You may choose from:\n\n[1]Top\n[2]Middle\n[3]Bottom\n\nPlease try again:");
                            loopFlag = 1;
                            userInput.nextLine();
                        }
                    } while (loopFlag == 1);
                    progress = "3";
                    break;

                case "3":
                    if (laneChoice == 1) {
                        getRandDire();
                        getRandDire();
                        System.out.println("These two evil Dire heroes appear, thirsting for your blood. Do you wish to create a diversion?\n\n[Y]\n[N]");

                        do {
                            String diversion = userInput.next();

                            if (diversion.equalsIgnoreCase("N")) {
                                System.out.println("You are brave but foolish. Both heroes attack you at once and you suffer a fatal injury.");
                                savedGame("3");
                                loopFlag = 0;
                                break outerswitch;
                            } else if (diversion.equalsIgnoreCase("Y")) {
                                progress = "3A";
                                loopFlag = 0;
                                break outerswitch;
                            } else {
                                loopFlag = 1;
                                System.out.println("Invalid choice! You must choose from the options listed! You may choose from the following:\n\n[Y]\n[N]\n\nPlease try again: ");
                            }
                        } while (loopFlag == 1);
                    } else if (laneChoice == 2) {
                        getRandDire();
                        System.out.println("This evil Dire hero seems to be extremely powerful.\nHe seems extremely hostile and it is likely you will have to fight him 1 on 1.\n\nDo you wish to escape?\n[Y]\n[N]");
                        String escape = userInput.next();

                        do {
                            if (escape.equalsIgnoreCase("N")) {
                                System.out.println("You are brave but foolish. The Dire hero is too powerful and instantly beheads you.");
                                loopFlag = 0;
                                savedGame("3");
                                break outerswitch;

                            } else if (escape.equalsIgnoreCase("Y")) {
                                progress = "3B";
                                loopFlag = 0;
                                break outerswitch;
                            } else {
                                loopFlag = 1;
                                System.out.println("Invalid choice! You must choose from the options listed! You may choose from the following:\n\n[Y]\n[N]\n\nPlease try again:");
                            }

                        } while (loopFlag == 1);
                    } else {
                        System.out.println("It seems you have made a wise choice, this lane is clear and safe beyond doubt. You are free to pass into the realms of the darkness without any disturbance.");
                        progress = "3C";
                        break;
                    }

                case "3A":
                    System.out.println("You create an illusion of yourself sending it in a random direction. Your diversion is successful...almost.\n" + direHeroName + " still trails you.\nIt seems that a battle is inevitable!");
                    battleMode("4A");
                    break;

                case "3B":
                    System.out.println("You manage to flee successfully...well almost. He chases you to your jungle and you meet a dead-end, forcing you to duel him. Luckily for you, he appears to be weakened by the Radiant aura.");
                    battleMode("4B");
                    break;
                case "3C":
                    System.out.println("You advance into the Dire territory on your quest; however you run into a giant gate surrounded by defense towers.\nYou are intimidated first but you soon remember that the Dire are dimwits and you are confident in your ability to deceive.\nTime to get CRACKING! *ba dum tss*");
                    passGate("ICE", "A hero who uses his ultimate ability to turn the tide.", "Tidehunter", "4C", "3C");
                    break;
                case "4A":
                    System.out.println("\nPhew, that was close...\nIn front of you, appears a gigantic thorn bush - there isn't a way in.\nYou wander to the middle river from the jungle. You spot a luminous glowing transparent rune.\n\nWill you take it?\n[Y]\n[N]");

                    do {
                        String takeInvis = userInput.next();
                        if (takeInvis.equalsIgnoreCase("Y")) {
                            System.out.println("\nWise choice hero!");
                            progress = "5AInvisrune";
                            loopFlag = 0;
                            break outerswitch;
                        } else if (takeInvis.equalsIgnoreCase("N")) {
                            System.out.println("I would have taken if I were you...bad luck, it's too late.");
                            progress = "5B";
                            loopFlag = 0;
                            break outerswitch;
                        } else {
                            loopFlag = 1;
                            System.out.println("Invalid choice! You must choose from the options listed! You must choose from the following:\n\n[Y]\n[N]\n\nPlease try again:");
                        }
                    } while (loopFlag == 1);

                case "4B":
                    System.out.println("\nPhew, that was close...");
                    progress = "5B";
                    break;

                case "4C":
                    System.out.println("\nPhew, that was close...\nYou are now deep within the Dire base.\nYou spot a secret shop with a neutral merchant who offers you a selection of items!\n\nDo you walk closer to take a look?\n[Y]\n[N]");
                    do {
                        String look = userInput.next();
                        if (look.equalsIgnoreCase("Y")) {
                            System.out.println("He stabs you. The Lannisters send their regards.");
                            savedGame("4C");
                            loopFlag = 0;
                            break outerswitch;
                        } else if (look.equalsIgnoreCase("N")) {
                            System.out.println("He looked creepy anyways...");
                            progress = "5C";
                            loopFlag = 0;
                            break outerswitch;
                        } else {
                            System.out.println("Invalid choice! You must choose from the options listed! You must choose from the following:\n\n[Y]\n[N]\n\nPlease try again:");
                            loopFlag = 1;
                        }
                    } while (loopFlag == 1);
                case "5AInvisrune":
                    System.out.println("\nYou are invisible, no one can see you. So you decide to go back to top lane.\nYou sneak all the way into Dire base through the top forest, passing that creepy shopkeeper.\nYou meet a gigantic gate. You have no choice but to try and answer the question!");
                    passGate("DOOM", "Which other hero is invisible like you are, except all the time?", "Rikimaru", "6", "4A");

                    break;
                case "5CHasterune":
                    System.out.println("\nYou are hasted, you run so fast, no one can catch you so you decide to go back to bottom lane.\nYou whizz all the way past all the Dire heros through bottom lane, only to meet a gigantic gate. You have no choice but to try and answer the question!");
                    passGate("FIRE", "Who is speedy in the night, but slow and weak in the day?", "Nightstalker", "6", "4C");

                    break;
                case "5C":
                    System.out.println("You're frightened and so you backtrack and make your way to the middle river where you spot a red, shiny, rune.\n\nDo you take it?\n[Y]\n[N]");

                    do {
                        String takeHaste = userInput.next();
                        if (takeHaste.equalsIgnoreCase("Y")) {
                            System.out.println("\nWise choice hero!");
                            progress = "5CHasterune";
                            loopFlag = 0;
                            break outerswitch;

                        } else if (takeHaste.equalsIgnoreCase("N")) {
                            System.out.println("Bad luck...Seemed like you could have had a free pass into their base with that haste rune.");
                            progress = "5B";
                            loopFlag = 0;
                            break outerswitch;
                        } else {
                            loopFlag = 1;
                            System.out.println("Invalid choice! You must choose from the options listed! You must choose from the following:\n\n[Y]\n[N]\n\nPlease try again:");
                        }
                    } while (loopFlag == 1);
                case "5B":
                    System.out.println("You arrive at middle where all heroes seem to be gone.\nYou appear to have free passage.\nYou wander, but soon you hear a noise\nYou have been caught!");
                    getRandDire();
                    battleMode("6");
                    break;

                case "6":
                    System.out.println("You arrive at the main gates of the hellish Dire base.\n You must get past one last gate. It's a very large gate...");
                    passGate("GATES", "You are a master of the fire, able to do vast amounts of damage.", "Lina", "7", "6");
                    break;

                case "7":
                    System.out.println("\nYou can see The Ancient of the Dire, the building you crave to kill. You can end the war now.\nAll that stands in your way, is the BOSS.\nDefeat him and end the war now!");
                    direHeroName = "Boss";
                    battleMode("8");
                    break;
                case "8":
                    System.out.println("The fight was tough but you are triumphant. You emerge victorious! CELEBRATE FOR THE WAR HAS ENDED AND THE RADIANT PREVAILS!\n YOU HAVE WON THE GAME!");
                    progress = "GG";
                    break;
                case "GG":
                    System.out.println("GAME OVER! GOOD GAME! You died a total of " + deathCount + " deaths.");
                    System.out.println("\n\nDo you wish to try again from the beginning?\n[Y]\n[N]\n(This will reset your death counter.");
                    do {
                        String restartGame = userInput.next();
                        if (restartGame.equalsIgnoreCase("Y")) {
                            progress = "0";
                            loopFlag = 0;
                            break outerswitch;
                        } else if (restartGame.equalsIgnoreCase("N")) {
                            gameExit = "Yes";
                            loopFlag = 0;
                            break outerswitch;
                        } else {
                            System.out.println("Invalid choice! You can only choose:\n\n[Y]\n[N]\n\nPlease try again:");
                            loopFlag = 1;
                        }
                    } while (loopFlag == 1);

            }
        }
    }

    public static void getRandDire() //generates random heroes for encounters
    {
        Random rng = new Random();
        int randomNumber = rng.nextInt(3);
        direHeroName = enemyName[randomNumber];
        System.out.println(direHeroName);
    }

    public static void battleMode(String level) //actual battling feature of game
    {
        switch (direHeroName) {
            case "Queen Of Pain":
                direHealth = direHeroStats[0][0]; //setting health and mana pool for enemy
                direMana = direHeroStats[1][0];
                break;
            case "Death Prophet":
                direHealth = direHeroStats[0][1];
                direMana = direHeroStats[1][1];
                break;
            case "Doom":
                direHealth = direHeroStats[0][2];
                direMana = direHeroStats[1][2];
                break;
            case "Boss":
                direHealth = direHeroStats[0][3];
                direMana = direHeroStats[1][3];
                break;
        }
        switch (heroTitle) {
            case "Lina The Slayer":
                radiantHealth = radiantHeroStats[0][0]; //setting health and mana pool for own hero
                radiantMana = radiantHeroStats[1][0];
                break;
            case "Nyx Assassin":
                radiantHealth = radiantHeroStats[0][1];
                radiantMana = radiantHeroStats[1][1];
                break;
            case "Morphling":
                radiantHealth = radiantHeroStats[0][2];
                radiantMana = radiantHeroStats[1][2];
                break;
        }
        Random rng2 = new Random();

        while (radiantHealth > 0 && direHealth > 0) //battle continues until one of the heroes is dead
        {
            System.out.println("---------------------------------------------\nThe Battle Begins!\nYou face " + direHeroName + "!\n");
            System.out.println("---------------------------------------------\nEnemy Health:" + " " + direHealth + "\nYour Health:" + " " + radiantHealth + "\n---------------------------------------------");
            System.out.println("It is your turn to fight! Enter any integer to randomize your attack:");

            do {
                try {
                    attackVariable = rng2.nextInt(userInput.nextInt()); //generates random number smaller than number user entered
                    loopFlag = 0;
                } catch (java.util.InputMismatchException e) {
                    System.out.println("You must enter a random integer value!\nPlease try again: ");
                    userInput.nextLine();
                    loopFlag = 1;
                }
            } while (loopFlag == 1);

            int rng3 = attackVariable % 4;

            switch (heroTitle) {
                case "Lina The Slayer":
                    damageCount = radiantHeroStats[rng3][0];
                    break;
                case "Nyx Assassin":
                    damageCount = radiantHeroStats[rng3][1];
                    break;
                case "Morphling":
                    damageCount = radiantHeroStats[rng3][2];
                    break;
            }
            direHealth = direHealth - damageCount;
            System.out.println(direHeroName + " takes " + damageCount + " damage!\n\n");
            if (direHealth <= 0) {
                break;
            }
            System.out.println("It is" + " " + direHeroName + "'s turn to fight!");
            attackVariable = rng2.nextInt(5);
            rng3 = attackVariable;
            switch (direHeroName) {
                case "Queen Of Pain":
                    damageCount = direHeroStats[rng3][0];
                    break;

                case "Death Prophet":
                    damageCount = direHeroStats[rng3][1];
                    break;
                case "Doom":
                    damageCount = direHeroStats[rng3][2];
                    break;
                case "Boss":
                    damageCount = direHeroStats[rng3][3];
                    break;
            }
            radiantHealth = radiantHealth - damageCount;
            System.out.println("You take " + damageCount + " damage!\n\n");
        }
        if (radiantHealth <= 0 && direHealth <= 0) //if both die 
        {
            System.out.println("Unlucky! RNGesus was not with either of you. Well fought but both of you died.");
            progress = "GG";
        } else if (radiantHealth <= 0) //if only player dies
        {
            System.out.println("Well fought but RNGesus was not with you today.");
            progress = "GG";
        } else if (direHealth <= 0) {
            System.out.println("Well fought! RNGesus was with you and you were able to conquer the Dire scum.");
            progress = level;
        }
    }

    public static void passGate(String gateName, String hint, String password, String level, String prevlevel) //handles the gates where you must guess the password
    {
        int passWrongCount = 0;
        passCorrect = 0;
        System.out.println("\nGatekeeper:WELCOME TO THE GATE OF " + gateName);
        while (passCorrect == 0 && passWrongCount != 3) {
            System.out.println("\n\nGatekeeper:PROVE THAT YOU'RE ONE OF US! YOU MUST ENTER THE CORRECT PASSWORD TO OPEN THE GATE\nIn case you have bad memory, we might have a hint for you!");
            System.out.println("\nHere is your hint: " + hint);
            System.out.println("\nEnter the password:");
            String userPassword = userInput.next();
            if (userPassword.equalsIgnoreCase(password)) {
                System.out.println("\nGatekeeper:WELCOME BACK BROTHER\nYou enter the gate confidently and advance further into the depths of the Dire territory.");
                passCorrect = 1;
                progress = level;
            } else {
                System.out.println("\nGatekeeper:WRONG! YOU ARE PROBABLY AN INTRUDER, BUT I'LL GIVE YOU ANOTHER CHANCE JUST IN CASE!");
                passWrongCount = passWrongCount + 1;
            }
        }

        if (passWrongCount == 3) {
            System.out.println("\nGatekeeper: IMPOSTER, YOU WILL NEVER INFILTRATE US! MUAHAHAHAHAHAHAHAHAHA!\nUSE YOUR GUESSES WISELY IN YOUR NEXT LIFE IMPOSTER!");
            System.out.println("You are shot with powerful bursts of magic by the defense tower and killed!");
            savedGame(prevlevel);
        }

    }

    public static void savedGame(String level) {
        deathCount = deathCount + 1;
        System.out.println("\nYou are dead.\nDo you wish to try from the last saved checkpoint?\n[Y]\n[N]");
        do {
            revertCheckpoint = userInput.next();
            if (revertCheckpoint.equalsIgnoreCase("Y")) {
                progress = level;
            } else if (revertCheckpoint.equalsIgnoreCase("N")) {
                progress = "GG";
            } else {
                loopFlag = 1;
                System.out.println("Invalid choice! You can only choose:\n\n[Y]\n[N]\n\nPlease try again:");
            }
        } while (loopFlag == 1);
    } //handles checkpoints

}
