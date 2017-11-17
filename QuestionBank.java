import java.*;
import java.io.*;
import java.util.*;


// Max 99,999 questions. To change edit modifiedTransfer = num + transfer.substring(5);



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
          System.out.println("Thats a restricted area.Choose another location");
          System.out.println(e);
      }
      catch(IOException e) {
        System.out.println(e);
      }
      totalQuestions = 0;
    }

  }

  QuestionBank(String name) {
    /*
    * Use when path is not given
    */
    this(null,name);
    this.path = qFile.getPath();
  }

  void insert(Question q) {
    /*
    * This will insert the incoming question in the database
    */
    if (totalQuestions < 999999) {
      //Max size of questtionBank is 999999
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
        try {
          String numString = String.format("%06d", new Integer(totalQuestions));
          fw.write(numString+"|");
          fw.write(q.stringify());
          fw.write(System.getProperty("line.separator"));
          System.out.println( numString + " Question inserted");
        }
        catch (IllegalFormatException e ){
          System.out.println("Number of the question could not be formatted");
          System.out.println(e);
        }

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
    else {
      System.out.println("Max Capacity Reached");
    }

  }

  void modify(int lineNumOfModification, Question q) {
    /*
    *We will copy each question one by one to the new file,
    *then we will delete the old file and add the new one in its place
    */
    if ( lineNumOfModification <= totalQuestions) {
      File tmp = new File("tmp");
      Scanner scOld = null;
      try {
        // Open the old file
        scOld = new Scanner(qFile);
      }
      catch (FileNotFoundException e) {
        System.out.println("Scanner opening for modify function failed");
        System.out.println(e);
      }

      FileWriter fwTmp = null;
      try {
        //Open a new file tmp to copy stuff into it
        fwTmp = new FileWriter(tmp);
        String transfer = new String();                        //Holds each line which will be copied
        for (int index = 1; index < lineNumOfModification ; index++) {
          transfer = scOld.nextLine();
          fwTmp.write(transfer);
          fwTmp.write(System.getProperty("line.separator"));
        }
        transfer = scOld.nextLine();
        String numString = String.format("%06d", new Integer(lineNumOfModification));
        fwTmp.write(numString+"|");
        fwTmp.write(q.stringify());
        fwTmp.write(System.getProperty("line.separator"));
        for (int index = lineNumOfModification; index < this.totalQuestions ; index++) {
          transfer = scOld.nextLine();
          fwTmp.write(transfer);
          fwTmp.write(System.getProperty("line.separator"));
          System.out.println("Question Modified");
        }
      }
      catch (IOException e) {
        System.out.println("File Writing failed");
        System.out.println(e);
      }
      finally {
        try {
          fwTmp.close();
        }
        catch (IOException e) {
          System.out.println("Could not close tmp");
        }
        scOld.close();
      }
      qFile.delete();
      tmp.renameTo(qFile);
    }
    else {
      System.out.println("The question you wish to modify does not exist");
    }
  }


  void delete(int lineNumOfModification) {
    /* Follows same procedure as modify,
    * will take each line and place into another file
    */
    if ( lineNumOfModification <= totalQuestions) {
      File tmp = new File("tmp");
      Scanner scOld = null;
      try {
        // Open the old file
        scOld = new Scanner(qFile);
      }
      catch (FileNotFoundException e) {
        System.out.println("Scanner opening for modify function failed");
        System.out.println(e);
      }

      FileWriter fwTmp = null;
      try {
        //Open a new file tmp to copy stuff into it
        fwTmp = new FileWriter(tmp);
        String transfer = new String();                        //Holds each line which will be copied
        for (int index = 1; index < lineNumOfModification ; index++) {
          transfer = scOld.nextLine();
          fwTmp.write(transfer);
          fwTmp.write(System.getProperty("line.separator"));
        }
        transfer = scOld.nextLine();
        for (int index = lineNumOfModification; index < this.totalQuestions ; index++) {
          String numString = String.format("%06d", new Integer(index));
          transfer = scOld.nextLine();
          fwTmp.write(numString+transfer.substring(6));
          fwTmp.write(System.getProperty("line.separator"));
          System.out.println("Deleted!");
        }
      }
      catch (IOException e) {
        System.out.println("File Writing failed");
        System.out.println(e);
      }
      finally {
        try {
          fwTmp.close();
        }
        catch (IOException e) {
          System.out.println("Could not close tmp");
        }
        scOld.close();
      }
      qFile.delete();
      tmp.renameTo(qFile);
      totalQuestions--;
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
    }
    else {
      System.out.println("The question you wish to modify does not exist");
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
    for (int index = 0; index<100; index++) {
          q1.insert(q);
    }
    Question qDiff = new Question("Test2",optionArray,2);
    q1.modify(3,qDiff);
    q1.modify(5,qDiff);
    q1.delete(4);

  }
}
