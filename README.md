# RiskBattleSimulator
This is a simulator of a battle in the board game risk.

If you're unfamiliar Risk is a board game where players fight each other for complete dominance over the globe. In every battle there is an attacker and defender, the attacker
can role up to 3 dice the defender 2, dice rolls are used per battle to determine who loses however many troops. Defenders always win on ties and the higher value wins. 
The dices are compared individually, you could think of each die representing one soldier.

It is a project coded in Java using a swing GUI
The project is handled by two classes, Risk.class and Player.class
Risk encompasses the GUI and all the math and other algorithms while the player class is made for management of troops.

The GUI uses a borderlayout and 6 panels one with its own grid layout
The GUI is a visual representation of one continous battle of any number of attackers and defenders that could be executed.
The battles are determined per round with the "round" button where one attack or roll is calculated and compared. The "reset" button restarts the status of the battle so the 
rounds can be started over.
If one side's troops are reduced to 0 the opposing side is declared the victor. 

The project has error and exception handling for anything that could go wrong on the user's side, specifically input into the two text fields for the amount of soldiers
compared, in those fields many things could go wrong like the user entering text instead of a number or a negative number. The project deals with these things in intelligent
ways that tell the user what they did wrong and they need to do to make the program work correctly.

