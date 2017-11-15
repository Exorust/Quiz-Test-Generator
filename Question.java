import java.*;

public class Question {
  String questionString;
  String[] options;
  int numOfOptions;
  int answer;

  Question(String questionString,String[] options,int answer) {
    this.questionString = questionString;
    this.options = options;
    numOfOptions = options.length;
    this.answer = answer;

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
    return tmp;
  }
  String stringify() {
    return (questionString + this.optionStringify() + "|" + answer);
  }
  public static void main(String[] args) {
    String[] optionArray = new String[3];
    optionArray[0] = "a";
    optionArray[1] = "B";
    optionArray[2] = "c";
    Question q = new Question("Test1",optionArray,2);
    System.out.println(q.stringify());
  }
}
