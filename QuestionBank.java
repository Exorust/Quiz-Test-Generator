import java.*;
import java.io.*;
import java.util.*;

class QuestionBank {
  /*
  * This class contains controls the file containing the questions
  * File is called qFile. The Scanner in control of the file is sc.
  */
  String name,path;
  File qFile;
  Scanner sc;
  int totalQuestions;
  FileWriter fw;


  QuestionBank(String path,String name) {
    this.name = name;
    this.path = path;

    if(path == null) {
      qFile = new File(name);
    }
    else {
    qFile = new File(path + "/" + name);
    }

    try {
        qFile.createNewFile();
      }
    catch(SecurityException e) {
        //Must add
    }
    catch(IOException e) {
      //Must add
    }

  }

  QuestionBank(String name) {
    this(null,name);
    this.path = qFile.getPath();
  }

  void insert(Question q) {
    /*
    * This will insert the incoming question in the database
    */
    try {
      // fw = new FileWriter(qFile.getAbsoluteFile());
       fw = new FileWriter(qFile,true);
    }
    catch(IOException e) {
      //Add Later
      System.out.println("Opening FileWriter failed");
    }
    try {
      // fw.write(q.stringify(),0,q.stringify().length());
      fw.write(q.stringify());
      fw.write(System.getProperty("line.separator"));
    }
    catch(IOException e) {
      //Add Later
      System.out.println("write failed");
    }
    finally {
      try {
        fw.close();
      }
      catch (IOException e) {
        System.out.println("Closing Failed");
      }
    }

  }

  public static void main(String[] args) {
    QuestionBank q1 = new QuestionBank("/home/chandrahas/mybin/Quiz-Test-Generator","physics");
    QuestionBank q2 = new QuestionBank("math");
    String[] optionArray = new String[3];
    optionArray[0] = "a";
    optionArray[1] = "B";
    optionArray[2] = "c";
    Question q = new Question("Test1",optionArray,2);
    q1.insert(q);
  }
}




// try {
//   sc = new Scanner(qFile);
// }
// catch(FileNotFoundException e){
//   //Must add
// }
//
// fw = new FileWriter(qFile.getAbsoluteFile());
