import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class MainSwingControl {
  MainSwingControl() {
    JFrame jfrm = new JFrame("Quiz Generator");
    jfrm.setSize(275, 100);
    jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    // JTabbedPane questionBankTabs = new JTabbedPane();
    QuestionBankHolder qBankHolder = new QuestionBankHolder();

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
    jmFile.add(nqb);
    jmFile.add(oqb);
    jmFile.add(exit);
    jmEdit.add(deleteqb);
    jmGenerate.add(generatequiz);
    jfrm.add(jmBar);

    //Set visibility
    jfrm.setVisible(true);
  }

  // class QBankPanel extends JPanel {
  //
  // }
  public static void main(String[] args) {
    SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        new MainSwingControl();
      }
    });
  }
}
