package questions;

public class MyVariables {
    
    // Must update if you add more questions
    String question1 = "letters";
    String question2 = "numbers";
    
    String[] ques = {question1, question2};
    
    // store the answers in lower case only
    String[] answer1 = {"a", "b", "c"};
    String[] answer2 = {"1", "2", "3"};
    
    boolean question1Right=true, question2Right=true;
//____________________________________________________________________________________________________________________________________    
//____________________________________________________________________________________________________________________________________    
    // No update needed
    
    int numberOfQuestions = ques.length;
    
    String filePath = "D:\\Projects\\Java\\Questions\\";
    
    int repeatEachQuestion=1;
    
    int[] r = new int[numberOfQuestions]; // to store how many times a question has to be repeated
    
    double numOfQuesDone=0, quesRightNum=0; // counts the questions done and the ones correct
    
}