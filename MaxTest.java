import java.*;
import java.io.*;
import java.lang.*;
import java.util.*;
import java.nio.file.*;

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
    for (int index = 0; index<800; index++) {
      int optionLength = (int) ((rnd.nextFloat() * 3)+1);
      String[] optionArray = new String[optionLength];
      for (int innerIndex = 0;innerIndex < optionLength ; innerIndex++) {
        optionArray[innerIndex] = getRandomizedString(5);
      }
      Question q = new Question(getRandomizedString(100),optionArray,1);
      q1.insert(q);
      q2.insert(q);
    }
    // File newFile;     //physics new
    // File oldFile;     //physics old
    // try {
    //   newFile = new File("physicsNew");
    //   oldFile = new File("physics");
    //   oldFile.createNewFile();
    //   Files.copy(oldFile.toPath(),newFile.toPath());
    // }
    // catch (IOException e) {
    //   System.out.println("Unable to copy the old physics into the new one");
    //   System.out.println(e);
    // }
    // for (int index = 0; index<999; index+=10) {
    //   int optionLength = (int) ((rnd.nextFloat() * 3)+1);
    //   String[] optionArray = new String[optionLength];
    //   for (int innerIndex = 0;innerIndex < optionLength ; innerIndex++) {
    //     optionArray[innerIndex] = getRandomizedString(5);
    //   }
    //   Question q = new Question(getRandomizedString(100),optionArray,1);
    //   q1.modify(index,q);
    // }

  }
}
