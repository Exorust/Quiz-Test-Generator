import java.*;

public class Question {
  String questionString;
  String[] options;
  int numOfOptions;
  int answer;

  Question(String questionString,String[] options,int answer) {
    this.questionString = questionString;
    this.options = options;
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
      tmp = tmp + "|" + options[index];
    }
    return tmp;
  }
  String stringify() {
    return (questionString + this.optionStringify() + "|" + answer);
  }
}
