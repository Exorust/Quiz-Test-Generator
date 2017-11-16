import java.*;
import java.io.*;
import java.util.*;

// To fix: Add the ability to count number of questions. Put a count at the start of the file





class QuestionBank {
  /*
  * This class contains controls the file containing the questions
  * File is called qFile. The Scanner in control of the file is sc.
  */
  String name,path;
  File qFile;
  File qMetaFile;
  Scanner sc;
  int totalQuestions;
  FileWriter fw;


  QuestionBank(String path,String name) {
    this.name = name;
    this.path = path;

    // Opening qFile
    if(path == null) {
      qFile = new File(name);
      qMetaFile = new File("metaData"+name);
    }
    else {
    qFile = new File(path + "/" + name);
    qMetaFile = new File(path + "/" + "metaData" + name);
    }


    if(qFile.exists() && !qFile.isDirectory() && qMetaFile.exists() && !qMetaFile.isDirectory()){
      //If file exists then read the totalQuestions
      try {
        sc = new Scanner(qMetaFile);
        this.totalQuestions = sc.nextInt();
        System.out.println("Total questions initially:" + this.totalQuestions);
      }
      catch(FileNotFoundException e){
        e.printStackTrace(new PrintStream(System.out));
      }
      finally {
        sc.close();
      }
    }
    else {
      //Else if the file doesn't exist make a new file and add totalQuestions
      try {
          qFile.createNewFile();
          qMetaFile.createNewFile();
          try {
            // fw = new FileWriter(qFile.getAbsoluteFile());
             fw = new FileWriter(qMetaFile,true);
             fw.write("0");
             fw.write(System.getProperty("line.separator"));
          }
          catch(IOException e) {
            //Add Later
            System.out.println("Opening FileWriter failed");
          }
          finally {
            fw.close();
          }
        }
      catch(SecurityException e) {
          //Must add
      }
      catch(IOException e) {
        //Must add
      }
      totalQuestions = 0;
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
    System.out.println(this.totalQuestions);

    totalQuestions++;
    try {
      fw = new FileWriter(qMetaFile);
      fw.write(Integer.toString(totalQuestions));
      fw.write(System.getProperty("line.separator"));
    }
    catch (IOException e) {
      System.out.println("Failed to read Number of Questions");
      System.out.println(e);
    }
    finally{
      try {
        fw.close();
      }
      catch (IOException e) {
        System.out.println("Closing Failed");
        System.out.println(e);
      }
    }

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
      fw.write(Integer.toString(totalQuestions)+"|");
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

  void modify(int num) {

  }

  void delete(int num) {

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
