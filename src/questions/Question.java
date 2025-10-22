package questions;

import javax.swing.*;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Formatter;

public class Question extends JFrame {
    
    MyVariables myVariables = new MyVariables();
      
    // create a label for the questions, and to check whether the answer is correct and a text area to show
    // the answer
    // and a text area to write the answer
    
    JTextArea answerText, correctAnswer;
    JLabel questionLabel, checkLabel;
    JButton checkButton;
    
    int que_index=0; // to show the question number -> 0 for 1 and so on
    
    private void questions(String[] answer, boolean questionRight) {
        
        // accesses the files created in Questions.java and stores their values
        File x = new File(myVariables.filePath + "r.txt");
        File xQues = new File(myVariables.filePath + "q_no.txt");
        File xRights = new File(myVariables.filePath + "q_right_no.txt");
        
        try {
            Scanner scQues, scRights;
            try (Scanner sc = new Scanner(x)) {
                scQues = new Scanner(xQues);
                scRights = new Scanner(xRights);
                
                while(sc.hasNext()) {
                    for(int a=0; a<myVariables.numberOfQuestions; a++) {
                        myVariables.r[a] = Integer.parseInt(sc.next());
                    }
                }
                
                while(scQues.hasNext()) {
                    myVariables.numOfQuesDone = Double.parseDouble(scQues.next());
                }
                while(scRights.hasNext()) {
                    myVariables.quesRightNum = Double.parseDouble(scRights.next());
                }
            }
            scQues.close();
            scRights.close();
        } catch (FileNotFoundException ex) {
            
        }
        
        // when the button is pressed, it takes the answer in the text area and checks if the words in the 
        // answers array are there
        
        if(checkButton.getText().equals("check")) {
            answerText.setEditable(false);
            String myAnswer = (answerText.getText()).toLowerCase(); // change all to lower case incase the
                                                                    //  user writes in upper case
            for(String w : answer) {
                questionRight = myAnswer.contains(w);
            }
            
            if(questionRight) {
                checkLabel.setText("Correct");
            }
            else {
                checkLabel.setText("Wrong");
            }
                
        // reduce the number of repeats for the question and increase the questions done and one correct
        // update the numbers in the file
            if(checkLabel.getText().equals("Correct")) {
                    
                myVariables.r[que_index]--;
                myVariables.numOfQuesDone++;
                myVariables.quesRightNum++;
                
                try {
                    Formatter q_no, q_right_no;
                    try (Formatter f = new Formatter(myVariables.filePath + "r.txt")) {
                        q_no = new Formatter(myVariables.filePath + "q_no.txt");
                        q_right_no = new Formatter(myVariables.filePath + "q_right_no.txt");
                    
                        for(int b=0; b<myVariables.numberOfQuestions; b++) {
                            f.format(myVariables.r[b] + " ");
                        }
                        
                        q_no.format("" + myVariables.numOfQuesDone);
                        q_right_no.format("" + myVariables.quesRightNum);
                    }
                    q_no.close();
                    q_right_no.close();
                    } catch (FileNotFoundException ex) {
                                
                    }
            }
            // if wrong, only increase the questions done
            else if(checkLabel.getText().equals("Wrong")) {
                myVariables.numOfQuesDone++;
                    
                try {
                    try (Formatter q_no = new Formatter(myVariables.filePath + "q_no.txt")) {
                        q_no.format("" + myVariables.numOfQuesDone);
                    }
                } catch (FileNotFoundException ex) {
                            
                }
            }
                
            // show the correct answer whether the question is wrong or not
            String answers="";
            for(String answers2 : answer) {
                answers=answers+answers2+", ";
            }
            correctAnswer.setText("answer: "+answers);
            checkButton.setText("next");
        }
        // check if the next question can be repeated. If not, check the currect one
        
        else if(checkButton.getText().equals("next")){
            
            int repeatNext;
            
            try {
                repeatNext = myVariables.r[que_index+1];
            } catch(Exception e) {
                repeatNext = myVariables.r[0];
            }
            
            if(repeatNext>0) {
                checkButton.setText("check");
                try {
                    que_index++;
                    questionLabel.setText(myVariables.ques[que_index]);
                } catch(Exception e) {
                    que_index=0;
                    questionLabel.setText(myVariables.ques[que_index]);
                }
                
                answerText.setText("");
                checkLabel.setText("");
                correctAnswer.setText("");
                answerText.setEditable(true);
            }
            else {
                if(myVariables.r[que_index]>0) {
                    checkButton.setText("check");
                    questionLabel.setText(myVariables.ques[que_index]);
                    
                    answerText.setText("");
                    checkLabel.setText("");
                    correctAnswer.setText("");
                    answerText.setEditable(true);
                }
                else { // if no question is to be repeated, it moves to the next frame and calculates the score
                    int frameX = getX();
                    int frameY = getY();
                        
                    EndFrame endFrame = new EndFrame();
                    endFrame.setLocation(frameX, frameY);
                    endFrame.setVisible(true);
                    dispose();
                }
            }
        }
    }
    
    // creating the layout
    private void createWidgets() {
        
        questionLabel= new JLabel();
        
        questionLabel.setText(myVariables.ques[que_index]);
        
        questionLabel.setBounds(100,100,600,20);
        
        answerText = new JTextArea();
        
        checkLabel = new JLabel();
        checkLabel.setBounds(150,355,100,20);
        
        correctAnswer = new JTextArea();
        correctAnswer.setEditable(false);
        
        checkButton = new JButton("check");
        checkButton.setFocusPainted(false);
        checkButton.setBounds(150,330,100,20);
        checkButton.addActionListener(v -> {
            switch (que_index) {
                case 0 -> questions(myVariables.answer1, myVariables.question1Right);
                case 1 -> questions(myVariables.answer2, myVariables.question2Right);
                default -> {
                }
            }
        });
        
        JScrollPane scrollPane = new JScrollPane(answerText);
        scrollPane.setBounds(10,125,700,200);
        
        JScrollPane scroll2 = new JScrollPane(correctAnswer);
        scroll2.setBounds(10,380,700,40);
        
        add(questionLabel); add(scrollPane); add(checkButton);
        add(checkLabel); add(scroll2);
    }
    
    public Question() {
        setSize(800,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setLocationRelativeTo(null);
        setResizable(false);
        
        createWidgets();
    }
}