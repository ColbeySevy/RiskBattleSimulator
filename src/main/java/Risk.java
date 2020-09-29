
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;
import java.math.*;
import java.util.*;

public class Risk extends JFrame implements ActionListener {

    //ALl the elements required for the GUI
    ImageIcon attackerIcon; //Icons
    ImageIcon defenderIcon;
    JLabel atkLabel; //Various Labels
    JLabel defLabel;
    JLabel title;
    JLabel atkEnter;
    JLabel defEnter;
    JLabel advantage;
    JLabel convert;
    JLabel errorLabel;
    JLabel result;
    JLabel aRolls;
    JLabel dRolls;
    JFrame frame; //Frame
    JPanel titlePanel; //Panels
    JPanel atkdefPanel;
    JPanel atkPanel;
    JPanel defPanel;
    JPanel attackerRolls;
    JPanel defenderRolls;
    JPanel dataPanel;
    JTextField atkField; //Fields
    JTextField defField;
    Player atker; //Objects
    Player defender;

    public Risk() {
        //Declare JFrame
        frame = new JFrame();

        //Declare font used for all text
        Font f1 = new Font(Font.SERIF, Font.PLAIN, 24);

        //Declare button
        JButton battle = new JButton("Run a round");
        battle.addActionListener(this);
        JButton reset = new JButton("Reset");
        reset.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                atker.setTroops(0);
                defender.setTroops(0);
                advantage.setText("");
                aRolls.setText("");
                dRolls.setText("");
                result.setText("Troop count has been reset");
            }
        });
        //Declare all elements and set their fonts if needed
        title = new JLabel("Welcome to the Risk Battle Simulator");
        title.setFont(f1);
        atkEnter = new JLabel("Attackers");
        atkEnter.setFont(f1);
        defEnter = new JLabel("Defenders");
        defEnter.setFont(f1);
        advantage = new JLabel();
        advantage.setFont(f1);
        convert = new JLabel();
        convert.setFont(f1);
        errorLabel = new JLabel();
        errorLabel.setFont(f1);
        result = new JLabel();
        result.setFont(f1);
        aRolls = new JLabel();
        aRolls.setFont(f1);
        dRolls = new JLabel();
        dRolls.setFont(f1);
        attackerIcon = new ImageIcon("img/attacker.png");
        atkLabel = new JLabel(attackerIcon);
        defenderIcon = new ImageIcon("img/defender.png");
        defLabel = new JLabel(defenderIcon);
        atkField = new JTextField(10);
        defField = new JTextField(10);
        atker = new Player();
        defender = new Player();

        //Design and declare panels for the JFrame
        //Title panel
        titlePanel = new JPanel();
        titlePanel.setBackground(Color.LIGHT_GRAY);
        titlePanel.add(title);

        //Attacker panel
        atkPanel = new JPanel();
        atkPanel.setBackground(Color.LIGHT_GRAY);
        atkPanel.add(atkEnter);
        atkPanel.add(atkField);
        atkPanel.add(atkLabel);

        //Defender panel
        defPanel = new JPanel();
        defPanel.setBackground(Color.LIGHT_GRAY);
        defPanel.add(defLabel);
        defPanel.add(defEnter);
        defPanel.add(defField);

        //atkdef Panel is a combination of the atk and def panel for proper formatting in a borderlayout
        atkdefPanel = new JPanel();
        atkdefPanel.setBackground(Color.LIGHT_GRAY);
        atkdefPanel.setLayout(new GridLayout(1, 2));
        atkdefPanel.add(atkPanel);
        atkdefPanel.add(defPanel);

        //Panels to hold the rolls
        attackerRolls = new JPanel();
        attackerRolls.setBackground(Color.LIGHT_GRAY);
        attackerRolls.add(aRolls);

        defenderRolls = new JPanel();
        defenderRolls.setBackground(Color.LIGHT_GRAY);
        defenderRolls.add(dRolls);

        //Relevant info panel
        dataPanel = new JPanel();
        dataPanel.setBackground(Color.LIGHT_GRAY);
        dataPanel.add(convert);
        dataPanel.add(errorLabel);
        dataPanel.add(advantage);
        dataPanel.add(result);
        dataPanel.add(battle);
        dataPanel.add(reset);

        //Frame declaration and formatting
        frame.setSize(1920, 900);
        frame.setLayout(new BorderLayout(0, 0));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Risk Battle Simulator");
        frame.add(titlePanel, BorderLayout.PAGE_START);
        frame.add(atkdefPanel, BorderLayout.CENTER);
        frame.add(attackerRolls, BorderLayout.LINE_START);
        frame.add(defenderRolls, BorderLayout.LINE_END);
        frame.add(dataPanel, BorderLayout.PAGE_END);
        frame.setVisible(true);
    }

    //This is the method that runs a single battle and returns the status after the battle
    public int[] Battle(int atk, int def) {
        //Reset the rolls label
        aRolls.setText("");
        dRolls.setText("");
        //Two ints to hold the number of dice
        int atkdice, defdice, holdsize;
        //Array to hold and edit troop count
        int[] left = new int[2];
        left[0] = atk;
        left[1] = def;
        //Arraylists for dice rolls
        ArrayList<Integer> atkrolls = new ArrayList<>();
        ArrayList<Integer> defrolls = new ArrayList<>();
        //Determine number of dice to be rolled
        if (atk >= 3) {
            atkdice = 3;
        } else if (atk == 2) {
            atkdice = 2;
        } else {
            atkdice = 1;
        }

        if (def >= 2) {
            defdice = 2;
        } else {
            defdice = 1;
        }

        //Prepare the roll labels
        aRolls.setText("Rolls:");
        dRolls.setText("Rolls:");

        //"Roll the dice" store and sort those values in the arraylists
        for (int i = 0; i < atkdice; i++) {
            atkrolls.add((int) (Math.random() * (6 - 1)) + 1);
        }

        Collections.sort(atkrolls, Collections.reverseOrder());

        //Insert the rolls into the label
        for (Integer atkroll : atkrolls) {
            aRolls.setText(aRolls.getText() + atkroll + " ");
        }

        for (int i = 0; i < defdice; i++) {
            defrolls.add((int) (Math.random() * (6 - 1)) + 1);
        }

        Collections.sort(defrolls, Collections.reverseOrder());

        //Insert the rolls into the label
        for (Integer defroll : defrolls) {
            dRolls.setText(dRolls.getText() + defroll + " ");
        }

        //Determine what array is smaller to avoid IndexOutOfBoundsExceptions
        if (atkrolls.size() > defrolls.size()) {
            holdsize = defrolls.size();
        } else {
            holdsize = atkrolls.size();
        }

        //Compare the dice rolls and edit the troops accordingly
        for (int i = 0; i < holdsize; i++) {
            if (defrolls.get(i) >= atkrolls.get(i)) {
                left[0]--;
            } else {
                left[1]--;
            }
        }

        return left;
    }

    public static void main(String[] args) {
        new Risk();
    }

    //Everything that is done when the user submits data
    @Override
    public void actionPerformed(ActionEvent e) {
        //Make the error labels empty
        convert.setText("");
        errorLabel.setText("");
        //Arraylist of errors 
        ArrayList<String> errors = new ArrayList<>();
        //ints for troops
        int attackers = 0, defenders = 0;
        //Array for the battle method
        int[] track;
        //Try catch to catch exception and make sure the fields aren't empty
        try {
            if ("".equals(atkField.getText()) || "".equals(defField.getText())) {
                errors.add("Both fields must be entered ");
            } else {
                attackers = Integer.parseInt(atkField.getText());
                defenders = Integer.parseInt(defField.getText());
            }
        } catch (NumberFormatException error) {
            convert.setText("You entered text, please try again with numbers");
        }

        //Make sure the troop numbers are higher than 0
        if (attackers < 1 || defenders < 1) {
            errors.add("Both fields must be greater than 0 ");
        }

        //Run if errors is empty (everything is okay)
        if (errors.isEmpty()) {
            if (atker.getTroops() <= 0) {
                atker.setTroops(attackers);
            }
            if (defender.getTroops() <= 0) {
                defender.setTroops(defenders);
            }
            //Determine advantage
            if (atker.getTroops() > defender.getTroops()) {
                advantage.setText("Attackers had the advantage");
            } else if (atker.getTroops() == defender.getTroops()) {
                advantage.setText("The armies were evenly matched.");
            } else {
                advantage.setText("Defenders had the advantage");
            }
            //Run the battle
            track = Battle(atker.getTroops(), defender.getTroops());
            atker.setTroops(track[0]);
            defender.setTroops(track[1]);
            //Print results
            if (defender.getTroops() == 0) {
                result.setText("The attackers have won with " + atker.getTroops() + " troops left and " + defender.getTroops() + " defenders left");
            } else if (atker.getTroops() == 0) {
                result.setText("The defenders have won with " + defender.getTroops() + " troops left and " + atker.getTroops() + " attackers left");
            } else {
                result.setText("Their is no victor yet, attackers have " + atker.getTroops() + " troops left and defenders have " + defender.getTroops() + " troops left");
            }
            //If errors isn't empty (something went wrong) print what happened
        } else {
            for (int i = 0; i < errors.size(); i++) {
                errorLabel.setText(errorLabel.getText() + errors.get(i) + "\n");
            }
        }
    }
}
