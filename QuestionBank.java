import java.*;
import java.io.*;
import java.util.*;

class QuestionBank {
  /*
  * This class contains controls the file containing the questions
  * File is called qFile. The Scanner in control of the file is sc
  */
  String name,path;
  File qFile;
  Scanner sc;
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
      qFile.createNewFile();
    }
    catch(IOException e) {
      //Must add
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

  }

  QuestionBank(String name) {
    this(null,name);
    this.path = qFile.getPath();
  }
  public static void main(String[] args) {
    QuestionBank q1 = new QuestionBank("/home/chandrahas/mybin/Quiz-Test-Generator","physics");
    QuestionBank q2 = new QuestionBank("math");
  }
}
