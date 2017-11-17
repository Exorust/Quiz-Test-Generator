import java.*;
import java.io.*;
import java.lang.*;
import java.util.*;


//Add: Fixed length for all the segments in the questions.

public class Question {
  /*
  *Defines the question object.
  *Has a max of:
  *250 char questionString
  *4 options,each 20 char
  */
  String questionString;
  String[] options;
  int numOfOptions; //Starts from 1, not 0
  int answer;

  Question(String questionString,String[] options,int answer) {

    if ( options.length <= 4) {
      if (answer <= options.length && answer > 0) {
        if (questionString.length() <= 250){
          //Take input
          this.questionString = questionString;
          this.options = options;
          this.numOfOptions = options.length;
          this.answer = answer;

          //Set variables to fixed sizes.
          StringBuilder setSizeQuestionString = new StringBuilder(this.questionString);
          setSizeQuestionString.setLength(250);
          this.questionString = setSizeQuestionString.toString();

          for (int index = 0; index < options.length; index++) {
            StringBuilder setSizeopt = new StringBuilder(this.options[index]);
            setSizeopt.setLength(20);
            this.options[index] = setSizeopt.toString();
          }
        }
        else {
          System.out.println("Question is too long");
        }
      }
      else {
        System.out.println("Answer is out of the options");
      }
    }
    else {
      System.out.println("Max options is 4.");
    }

  }

  Question(String fileString) {
    String[] tokenizedArray = fileString.split("\\|");
    int maxLength = tokenizedArray.length;
    int answer = Integer.parseInt(tokenizedArray[7]);
    String questionString = tokenizedArray[1];
    int numOfOptions = Integer.parseInt(tokenizedArray[6]);
    String[] options = Arrays.copyOfRange(tokenizedArray, 2, 5);

    if ( numOfOptions <= 4) {
      if (answer <= numOfOptions && answer > 0) {
        if (questionString.length() <= 250){
          //Take input
          this.questionString = questionString;
          this.options = options;
          this.numOfOptions = numOfOptions;
          this.answer = answer;

          //Set variables to fixed sizes.
          StringBuilder setSizeQuestionString = new StringBuilder(this.questionString);
          setSizeQuestionString.setLength(250);
          this.questionString = setSizeQuestionString.toString();

          for (int index = 0; index < options.length; index++) {
            StringBuilder setSizeopt = new StringBuilder(this.options[index]);
            setSizeopt.setLength(20);
            this.options[index] = setSizeopt.toString();
          }
        }
        else {
          System.out.println("Question is too long");
        }
      }
      else {
        System.out.println("Answer is out of the options");
      }
    }
    else {
      System.out.println("Max options is 4.");
    }

  }

  String getQuestionString() {
    return questionString;
  }
  String[] getOptions() {
    return options;
  }
  int getAnswer() {
    return answer;
  }


  String optionStringify() {
    String tmp = new String("");
    for(int index=0; index<numOfOptions; index++) {
      tmp = tmp.concat("|").concat(options[index]);
    }
    // Add blank strings till total options become 4
    StringBuilder blank = new StringBuilder("");
    blank.setLength(20);
    String blankString = blank.toString();
    for(int index = numOfOptions; index<4;index++) {
      tmp = tmp.concat("|").concat(blankString);
    }
    return tmp;
  }
  String stringify() {
    return (questionString.concat(this.optionStringify()) + "|" + numOfOptions +"|" + answer);
  }

  public static void main(String[] args) {
    String[] optionArray = new String[3];
    optionArray[0] = "a";
    optionArray[1] = "B";
    optionArray[2] = "c";
    Question q = new Question("Test1",optionArray,2);
    System.out.println(q.stringify());
    q = new Question("1|Hello|hi|woah|||2|1");
  }

}
