import java.*;
import java.io.*;
import java.lang.*;
import java.util.*;
import java.nio.file.*;

class QuestionBankHolder {
  /*
  *Wrapper class for multiple QuestionBanks
  *Uses a hashMap for high performance
  */
  Map<String,QuestionBank> hm;

  QuestionBankHolder() {
    hm = new HashMap<String,QuestionBank>();
  }

  void insertQuestionBank(String path,String name) {
    hm.put(name, new QuestionBank(path,name));
  }
  void insertQuestionBank(String name) {
    hm.put(name, new QuestionBank(name));
  }
  void insertQuestion(String name,Question q) {
    if(hm.containsKey(name)) {
      hm.get(name).insert(q);
    }
    else {
      System.out.println("No such Key, can not insert given question");
    }
  }
  void modifyQuestion(String name,int num,Question q) {
    if(hm.containsKey(name)) {
      hm.get(name).modify(num,q);
    }
    else {
      System.out.println("No such Key, can not modify given question");
    }
  }
  void deleteQuestion(String name,int num) {
    if(hm.containsKey(name)) {
      hm.get(name).delete(num);
    }
    else {
      System.out.println("No such Key, can not modify given question");
    }
  }
  public static void main(String[] args) {
    QuestionBankHolder qbh = new QuestionBankHolder();
    qbh.insertQuestionBank("/home/chandrahas/mybin/Quiz-Test-Generator","physics");
    qbh.insertQuestionBank("math");
    String[] optionArray = new String[3];
    optionArray[0] = "a";
    optionArray[1] = "B";
    optionArray[2] = "c";
    Question q = new Question("Test1",optionArray,2);
    for (int index = 0; index<100; index++) {
          qbh.insertQuestion("math",q);
    }
  }
}
