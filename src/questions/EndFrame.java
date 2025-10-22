package questions;

import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javax.swing.*;

public class EndFrame extends JFrame {
    
    public EndFrame() {
        setSize(500,500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setLocationRelativeTo(null);
        setResizable(false);
        
        MyVariables myVariables = new MyVariables();
        
        JLabel endLabel = new JLabel("Completed");
        endLabel.setBounds(200,230,100,20);
        
        // gets the number of right questions/the total number of questions & calculates the score in %
        File xQues = new File(myVariables.filePath + "q_no.txt");
        File xRights = new File(myVariables.filePath + "q_right_no.txt");
        
        try {
            Scanner scQues = new Scanner(xQues);
            Scanner scRights = new Scanner(xRights);
            
            while(scQues.hasNext()) {
                myVariables.numOfQuesDone = Double.parseDouble(scQues.next());
            }
            while(scRights.hasNext()) {
                myVariables.quesRightNum = Double.parseDouble(scRights.next());
            }
            
        } catch (FileNotFoundException ex) {
            
        }
        
        int quesNum = (int) myVariables.numOfQuesDone;
        int rightQues = (int) myVariables.quesRightNum;
        
        String percentage = String.format("%.2f", ((myVariables.quesRightNum/myVariables.numOfQuesDone)*100));
        
        JLabel scoreLabel = new JLabel(rightQues + "/" + quesNum + " = " + percentage + "%");
        scoreLabel.setBounds(200,250,200,20);
        
        JButton exitButton = new JButton("Exit");
        exitButton.setBackground(Color.white);
        exitButton.setFocusPainted(false);
        exitButton.setBounds(180,300,100,20);
        exitButton.addActionListener(v -> {
            dispose();
        });
        
        add(endLabel); add(exitButton); add(scoreLabel);
    }
}