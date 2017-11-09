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

    try{
      try {
        qFile.createNewFile();
      }
      catch(SecurityException e) {
        //Must add
      }
      try {
        sc = new Scanner(qFile);
      }
      catch(FileNotFoundException e){
        //Must add
      }

      fw = new FileWriter(qFile.getAbsoluteFile());
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
    
  }

  void close() {
    try {
      bw.close();
    }
    catch(IOException e) {
      //Must add
    }
  }
  public static void main(String[] args) {
    QuestionBank q1 = new QuestionBank("/home/chandrahas/mybin/Quiz-Test-Generator","physics");
    QuestionBank q2 = new QuestionBank("math");
  }
}
