import java.*;
import java.io.*;
import java.lang.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


class MainSwingControl {
  String user,password;
  JTabbedPane jtp;
  Map<String,QuestionBank> hm;
  int numOfQuestionBanks;
  JFrame jFrameMain;

  MainSwingControl() {
    user = new String("hello");
    password = new String("hello");
    hm = new HashMap<String,QuestionBank>();

    jFrameMain = new JFrame("Quiz Generator");


    JFrame jFrameLogin = new JFrame("Login");
    JPanel jPanelLogin = new JPanel();
    jPanelLogin.setLayout(new BoxLayout(jPanelLogin, BoxLayout.Y_AXIS));
    jFrameLogin.setSize(300,100);
    jFrameLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    jFrameLogin.add(jPanelLogin);
    JLabel jUser = new JLabel("User");
    JLabel jPassword = new JLabel("Password");
    JTextField jUserEnter = new JTextField(15);
    JTextField jPassEnter = new JTextField(15);
    JButton jEnterButton = new JButton("Enter");
    jPanelLogin.add(jUser);
    jPanelLogin.add(jUserEnter);
    jPanelLogin.add(jPassword);
    jPanelLogin.add(jPassEnter);
    jPanelLogin.add(jEnterButton);
    jEnterButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent ae) {
        if(user.equals(jUserEnter.getText()) && password.equals(jPassEnter.getText())){
          jFrameLogin.setVisible(false);
          jFrameMain.setVisible(true);
        }
        else {
          jUserEnter.setText("WRONG");
          jPassEnter.setText("WRONG");
        }
      }
    });
    jFrameLogin.setVisible(true);


    jFrameMain.setSize(575, 400);
    jFrameMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    // JTabbedPane questionBankTabs = new JTabbedPane();

    /*Creating the MenuBar with:
    *File : New Question Bank,Open Question Bank,Exit
    *Edit : Delete Question Bank
    *Generate : Generate
    */

    JMenuBar jmBar = new JMenuBar();
    JMenu jmFile = new JMenu("File");
    JMenu jmEdit = new JMenu("Edit");
    JMenu jmGenerate = new JMenu("Generate");
    JMenuItem nqb= new JMenuItem("New Question Bank");
    JMenuItem oqb= new JMenuItem("Open Question Bank");
    JMenuItem exit= new JMenuItem("Exit");
    JMenuItem deleteqb= new JMenuItem("Delete Question Bank");
    JMenuItem generatequiz= new JMenuItem("Generate");
    jFrameMain.add(jmBar);
    jmFile.add(nqb);
    jmFile.add(oqb);
    jmFile.add(exit);
    jmEdit.add(deleteqb);
    jmGenerate.add(generatequiz);


    //TESTING!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    insertQuestionBank("/home/chandrahas/mybin/Quiz-Test-Generator","physics");
    insertQuestionBank("math");
    String[] optionArray = new String[3];
    optionArray[0] = "a";
    optionArray[1] = "B";
    optionArray[2] = "c";
    Question q = new Question("Test1",optionArray,2);
    for (int index = 0; index<100; index++) {
          insertQuestion("math",q);
    }
    //END OF TESTING!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

    //Make jTabbedPane and JTable

    jtp = new JTabbedPane();
    jtpRefresh();

    //Set visibility
    jFrameMain.setVisible(false);







  }

  void jtpRefresh() {
    jtp.removeAll();
    Set<Map.Entry<String,QuestionBank>> set = hm.entrySet();
    for (Map.Entry<String,QuestionBank> me : set) {
      jtp.addTab(me.getKey(),new XPanel(me.getValue(),this));
    }
    jFrameMain.add(jtp);
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
    SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        new MainSwingControl();
      }
    });
  }
}

class XPanel extends JPanel {
  XPanel(QuestionBank qb,MainSwingControl msc) {
    setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    JButton generateButton = new JButton("Generate");
    JButton modifyButton = new JButton("Modify");
    JButton insertButton = new JButton("Insert");
    JButton deleteButton = new JButton("Delete");
    String[][] arr = new String[qb.getTotalQuestions()+1][6];
    for(int index=0;index<qb.getTotalQuestions();index++) {
      Question q = qb.getQuestion(index+1);
      arr[index][0] = Integer.toString(index+1);
      arr[index][1] = q.getQuestionString();
      String[] tmp = q.getOptions();
      for (int innerIndex = 0;innerIndex <q.getNumOfOptions() ; innerIndex++) {
        arr[index][2+innerIndex] = tmp[innerIndex];
      }
    }
    String[] colheads = {"Num","Question","Option1","Option2","Option3","Option4"};
    JTable table = new JTable(arr,colheads);
    table.setEnabled(false);
    JScrollPane jsp = new JScrollPane(table);
    add(jsp);
    add(insertButton);
    add(modifyButton);
    add(deleteButton);
    add(generateButton);

    //Add action listener to each button
    insertButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent ae) {
        InsertDialog id = new InsertDialog(msc,qb,"Question Insertion",true);
        id.setSize(200,200);
        id.setVisible(true);
        msc.jtpRefresh();
      }
    });
  }
}

class InsertDialog extends JDialog {
  InsertDialog (MainSwingControl msc,QuestionBank qb,String name,boolean modality) {
    super(msc.jFrameMain,name,modality);
    JPanel pnl = new JPanel();
    pnl.setLayout(new BoxLayout(pnl, BoxLayout.Y_AXIS));
    Label l = new Label("Chose one type to insert.");
    Button b1 = new Button("True/False");
    Button b2 = new Button("MCQ");
    Button b3 = new Button("Fill in the Blank");
    b1.addActionListener( new ActionListener() {
      public void actionPerformed(ActionEvent ae) {
        TrueDialog td = new TrueDialog(msc,qb,"True False",true);
        td.setSize(200,200);
        td.setVisible(true);
        dispose();
      }
    });
    add(pnl);
    pnl.add(l);
    pnl.add(b1);
    pnl.add(b2);
    pnl.add(b3);
  }
}

class TrueDialog extends JDialog {
  TrueDialog(MainSwingControl msc,QuestionBank qb,String name,boolean modality) {
    super(msc.jFrameMain,name,modality);
    JPanel pnl = new JPanel();
    pnl.setLayout(new BoxLayout(pnl, BoxLayout.Y_AXIS));
    JLabel ques = new JLabel("Question");
    JTextField quesJTF = new JTextField(25);
    JLabel ans = new JLabel("Answer either T/F");
    JTextField ansJTF = new JTextField(1);
    JButton jb = new JButton("Finish");
    pnl.add(ques);
    pnl.add(quesJTF);
    pnl.add(ans);
    pnl.add(ansJTF);
    pnl.add(jb);
    add(pnl);
    jb.addActionListener( new ActionListener() {
      public void actionPerformed(ActionEvent ae) {
        if((ansJTF.getText().equals("T") || ansJTF.getText().equals("F")) && quesJTF.getText().length() >0 ) {
          int tmp = 1;
          if(ansJTF.getText().equals("F")) {
            tmp = 2;
          }
          qb.insert(new Question(quesJTF.getText(),new String[]{"T","F"},tmp));
          dispose();
        }
      }
    });
  }


}

class MCQDialog extends JDialog {
  MCQDialog(MainSwingControl msc,QuestionBank qb,String name,boolean modality) {
    super(msc.jFrameMain,name,modality);
    JPanel pnl = new JPanel();
    pnl.setLayout(new BoxLayout(pnl, BoxLayout.Y_AXIS));
    JLabel ques = new JLabel("Question");
    JTextField quesJTF = new JTextField(25);

    JLabel[] options = new JLabel[4];
    JLabel option1 = new JLabel("Option1:");
    JTextField option1JTF = new JTextField(10);
    JLabel option2 = new JLabel("Option2:");
    JTextField option2JTF = new JTextField(10);
    JLabel option3 = new JLabel("Option3:");
    JTextField option3JTF = new JTextField(10);
    JLabel optio4n = new JLabel("Option4:");
    JTextField option4JTF = new JTextField(10);
    JLabel ans = new JLabel("Answer number??");
    JTextField ansJTF = new JTextField(10);
    JButton jb = new JButton("Finish");
    pnl.add(ques);
    pnl.add(quesJTF);
    pnl.add(ans);
    pnl.add(ansJTF);
    pnl.add(jb);
    add(pnl);
    jb.addActionListener( new ActionListener() {
      public void actionPerformed(ActionEvent ae) {
        if(quesJTF.getText().length() >0 && option1JTF.getText().length() >0 ) {
          int length =0;
          String[] optString;
          if(option1JTF.getText().length() >0){
            length++;
            optString = new String[1];
          }
          if(option2JTF.getText().length() >0){
            length++;
            optString = new String[2];
          }
          if(option3JTF.getText().length() >0){
            length++;
            optString = new String[3];
          }
          if(option4JTF.getText().length() >0){
            length++;
            optString = new String[4];
          }
          for (int innerIndex = 0;innerIndex<length ;innerIndex++ ) {
            optString[innerIndex] =
          }
          qb.insert(new Question(quesJTF.getText(),optString,tmp));
          dispose();
        }
      }
    });
  }


}
