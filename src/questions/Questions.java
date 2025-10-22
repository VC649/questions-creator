package questions;

import java.io.FileNotFoundException;
import java.util.Formatter;

public class Questions {

    public static void main(String[] args) {
        MyVariables myVariables = new MyVariables();
        
        /* first create files to store the values for:
           - number of times each question has to be repeated -> t.txt
           - total num of questions done -> q_no.txt
           - total num of questions correct -> q_right_no.txt
        */ 
        try {
            Formatter q_no, q_right_no;
            try (Formatter r = new Formatter(myVariables.filePath + "r.txt")) {
                q_no = new Formatter(myVariables.filePath + "q_no.txt");
                q_right_no = new Formatter(myVariables.filePath + "q_right_no.txt");
                
                for(int a=0; a<myVariables.numberOfQuestions; a++) {
                    r.format(myVariables.repeatEachQuestion + " ");
                }
                q_no.format("0");
                q_right_no.format("0");
            }
            q_no.close();
            q_right_no.close();
        
        } catch (FileNotFoundException ex) {
            
        }
        
        Question question = new Question();
        question.setVisible(true);
        
    }
}