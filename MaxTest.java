import java.*;
import java.io.*;
import java.lang.*;
import java.util.*;

class MaxTest {
  static String getRandomizedString(int max) {
    String RANDCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnpqrstuvwxyz ";
    Random rnd = new Random();
    StringBuilder randomized = new StringBuilder();
    while (randomized.length() < max) {
      int index = (int) (rnd.nextFloat() * RANDCHARS.length());
      randomized.append(RANDCHARS.charAt(index));
    }
    String randomizedStr = randomized.toString();
    return randomizedStr;
}

  public static void main(String[] args) {
    QuestionBank q1 = new QuestionBank("/home/chandrahas/mybin/Quiz-Test-Generator","physics");
    QuestionBank q2 = new QuestionBank("math");

    Random rnd = new Random();
    for (int index = 0; index<999995; index++) {
      int optionLength = (int) ((rnd.nextFloat() * 3)+1);
      String[] optionArray = new String[optionLength];
      for (int innerIndex = 0;innerIndex < optionLength ; innerIndex++) {
        optionArray[innerIndex] = getRandomizedString(5);
      }
      Question q = new Question(getRandomizedString(100),optionArray,1);
      q1.insert(q);
    }
  }
}
