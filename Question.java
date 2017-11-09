import java.*;

public class Question {
  String questionString;
  String[] options;
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
    return options;
  }
}
