import org.hibernate. * ;
import org.hibernate.cfg. * ;
import java.awt. * ;
import java.awt.event. * ;
import javax.swing. * ;
import org.jfree.chart. * ;
import org.jfree.chart.plot. * ;
import org.jfree.data.category. * ;
import java.io. * ;
import java.util. * ;
import java.sql. * ;

class Sms extends JFrame {
  Container c;
  JButton btnadd,
  btnview,
  btnupdate,
  btndelete,
  btncharts;
  Sms() {
    c = getContentPane();
    c.setLayout(new FlowLayout());

    btnadd = new JButton("Add");
    btnview = new JButton("View");
    btnupdate = new JButton("Update");
    btndelete = new JButton("Delete");
    btncharts = new JButton("Charts");
    c.add(btnadd);
    c.add(btnview);
    c.add(btnupdate);
    c.add(btndelete);
    c.add(btncharts);
    ActionListener al = (ae) ->{
      AddStud a = new AddStud();
      dispose();
    };
    btnadd.addActionListener(al);

    ActionListener alv = (ae) ->{
      ViewStud a = new ViewStud();
      dispose();
    };
    btnview.addActionListener(alv);

    ActionListener alu = (ae) ->{
      UpdateStud a = new UpdateStud();
      dispose();
    };
    btnupdate.addActionListener(alu);

    ActionListener ald = (ae) ->{
      DeleteStud a = new DeleteStud();
      dispose();
    };
    btndelete.addActionListener(ald);

    ActionListener alc = (ae) ->{
      ChartsStudent a = new ChartsStudent();
      dispose();
    };
    btncharts.addActionListener(alc);

    setTitle("S.M.S");
    setSize(250, 200);
    setLocationRelativeTo(null);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);
  }

}
class SmsMain {
  public static void main(String args[]) {
    Configuration cfg = new Configuration();
    cfg.configure("hibernate.cfg.xml");
    SessionFactory sf = cfg.buildSessionFactory();
    Transaction t = null;
    Session s = null;
    try {
      s = sf.openSession();
      System.out.println(" Connected hib");
      t = s.beginTransaction();

    }
    catch(Exception e) {
      System.out.println(" Error hib");
    }
    finally {
      s.close();
      System.out.println(" closed ");
    }
    Sms e = new Sms();
  }
}
class AddStud extends JFrame {
  Container c;
  JTextField tf1,
  tf2,
  tf3,
  tf4,
  tf5;
  JButton btnsave,
  btnback;
  JLabel label1,
  label2,
  label3,
  label4,
  label5;
  AddStud() {
    c = getContentPane();

    c.setLayout(new FlowLayout());
    label1 = new JLabel("Enter rno:");
    tf1 = new JTextField(20);
    label2 = new JLabel("Enter name:");
    tf2 = new JTextField(20);
    label3 = new JLabel("Enter sub marks 1:");
    tf3 = new JTextField(20);
    label4 = new JLabel("Enter sub marks 2:");
    tf4 = new JTextField(20);
    label5 = new JLabel("Enter sub marks 3:");
    tf5 = new JTextField(20);
    btnsave = new JButton("Save");
    btnback = new JButton("Back");

    c.add(label1);
    c.add(tf1);
    c.add(label2);
    c.add(tf2);
    c.add(label3);
    c.add(tf3);
    c.add(label4);
    c.add(tf4);
    c.add(label5);
    c.add(tf5);

    c.add(btnsave);
    c.add(btnback);

    ActionListener al = (ae) ->{
      new Sms();
      dispose();
    };
    btnback.addActionListener(al);

    ActionListener a = (ae) ->{
      int rno = 0;
      int m1 = 0,
      m2 = 0,
      m3 = 0;
      String sname = "";

      try {
        rno = Integer.parseInt(tf1.getText());
        sname = tf2.getText();
        m1 = Integer.parseInt(tf3.getText());
        m2 = Integer.parseInt(tf4.getText());
        m3 = Integer.parseInt(tf5.getText());
      }
      catch(NumberFormatException e) {
        System.out.println(e);
      }

      if (rno == 0 && sname.isEmpty() && m1 == 0 && m2 == 0 && m3 == 0) {
        JOptionPane.showMessageDialog(new JDialog(), "Enter All Details Correctly");
      }

      int len=0;
      len = sname.length();
      Session s = null;
      try {

        if (rno > 0 && !sname.isEmpty() && len >= 2) {
          if (m1 > 0 && m1 < 100 && m2 > 0 && m2 < 100 && m3 > 0 && m3 < 100) {
            Configuration cfg = new Configuration();
            cfg.configure("hibernate.cfg.xml");
            SessionFactory sf = cfg.buildSessionFactory();
            Transaction t = null;
            s = sf.openSession();
            System.out.println(" Connected hib");
            t = s.beginTransaction();
            DBHandler db = (DBHandler) s.get(DBHandler.class, rno);
            
            DBHandler d = new DBHandler(rno, sname, m1, m2, m3);

            if (d == null || db != null) {
              JOptionPane.showMessageDialog(new JDialog(), "Record not Added!!");
            } else if (db == null) {
              JOptionPane.showMessageDialog(new JDialog(), "Record Added");
              s.save(d);
              t.commit();
            }

            System.out.println("Record added hib");
          } else {
            JOptionPane.showMessageDialog(new JDialog(), "Enter Valid Marks");
          }
        }
        else if (rno <= 0 && rno != ' ') {
          JOptionPane.showMessageDialog(new JDialog(), "Enter Valid Roll NO");
        }
        else if (sname == null) {
          JOptionPane.showMessageDialog(new JDialog(), "Enter Valid Name");
        }
        else if (len < 2) {
          JOptionPane.showMessageDialog(new JDialog(), "Enter Valid Name");
        }
      } catch(Exception e) {
        System.out.println(" Error hib");
      }
      finally {
        s.close();
        System.out.println(" closed ");
      }

    };
    btnsave.addActionListener(a);
    setTitle("S.M.S ADD");
    setSize(250, 350);
    setLocationRelativeTo(null);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);
  }
}

class ViewStud extends JFrame {
  Container c;
  JTextArea tadata;
  JButton btnback;
  ViewStud() {
    c = getContentPane();
    c.setLayout(new FlowLayout());
    tadata = new JTextArea(10, 40);
    btnback = new JButton("Back");

    c.add(tadata);
    c.add(btnback);
    ActionListener al = (ae) ->{
      new Sms();
      dispose();
    };
    btnback.addActionListener(al);

    StringBuffer sb = new StringBuffer();
    Configuration cfg = new Configuration();
    cfg.configure("hibernate.cfg.xml");
    SessionFactory sf = cfg.buildSessionFactory();
    Session s = null;
    try {
      s = sf.openSession();
      System.out.println(" Connected hib");
      java.util.List < DBHandler > data = new ArrayList < >();
      data = s.createQuery("from DBHandler").list();
      for (DBHandler db: data) {
        System.out.println(db.getrno() + " " + db.getsname() + " " + db.getm1() + " " + db.getm2() + " " + db.getm3());
        sb.append(" Rno: " + db.getrno() + " Student name: " + db.getsname() + " Marks1: " + db.getm1() + " Marks2: " + db.getm2() + " Marks3: " + db.getm3() + "\n");
      }
      tadata.setText(sb.toString());
    } catch(Exception e) {
      System.out.println(" Error hib");
    }
    finally {
      s.close();
      System.out.println(" closed ");
    }
    //String data = new DbHandler().viewStudent();
    //tadata.setText(data);

    setTitle("S.M.S View");
    setSize(450, 450);
    setLocationRelativeTo(null);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);
  }

}
class UpdateStud extends JFrame {
  Container c;
  JTextField tf1,
  tf2,
  tf3,
  tf4,
  tf5;
  JButton btnsave,
  btnback;
  JLabel label1,
  label2,
  label3,
  label4,
  label5;
  UpdateStud() {
    c = getContentPane();

    c.setLayout(new FlowLayout());
    label1 = new JLabel("Enter rno:");
    tf1 = new JTextField(20);
    label2 = new JLabel("Enter name:");
    tf2 = new JTextField(20);
    label3 = new JLabel("Enter sub marks 1:");
    tf3 = new JTextField(20);
    label4 = new JLabel("Enter sub marks 2:");
    tf4 = new JTextField(20);
    label5 = new JLabel("Enter sub marks 3:");
    tf5 = new JTextField(20);
    btnsave = new JButton("Update");
    btnback = new JButton("Back");

    c.add(label1);
    c.add(tf1);
    c.add(label2);
    c.add(tf2);
    c.add(label3);
    c.add(tf3);
    c.add(label4);
    c.add(tf4);
    c.add(label5);
    c.add(tf5);

    c.add(btnsave);
    c.add(btnback);

    ActionListener al = (ae) ->{
      new Sms();
      dispose();
    };
    btnback.addActionListener(al);

    ActionListener a = (ae) ->{
      int rno = 0;
      int m1 = 0,
      m2 = 0,
      m3 = 0;
      String sname = null;

      try {
        rno = Integer.parseInt(tf1.getText());
        sname = tf2.getText();
        m1 = Integer.parseInt(tf3.getText());
        m2 = Integer.parseInt(tf4.getText());
        m3 = Integer.parseInt(tf5.getText());
      }
      catch(NumberFormatException e) {
        System.out.println(e);
      }

      if (rno == 0 && sname.isEmpty() && m1 == 0 && m2 == 0 && m3 == 0) {
        System.out.println("count 5");
        JOptionPane.showMessageDialog(new JDialog(), "Enter All Details Correctly");
      }

      int len=0;
      len = sname.length();
      Session s = null;
       System.out.println("count 4");
      try {
        if (rno > 0 && !sname.isEmpty() && len >= 2) {
          System.out.println("count 1");
          if (m1 > 0 && m1 < 100 && m2 > 0 && m2 < 100 && m3 > 0 && m3 < 100) {
           Configuration cfg = new Configuration();
            cfg.configure("hibernate.cfg.xml");
            SessionFactory sf = cfg.buildSessionFactory();
            Transaction t = null;
            s = sf.openSession();
            System.out.println(" Connected hib");
            t = s.beginTransaction();
            DBHandler db = (DBHandler) s.get(DBHandler.class, rno);
            if (db == null) {
              System.out.println("value=" + db);
              JOptionPane.showMessageDialog(new JDialog(), "Student Does not Exist!!");
            } else {
              db.setsname(sname);
              db.setm1(m1);
              db.setm2(m2);
              db.setm3(m3);
              JOptionPane.showMessageDialog(new JDialog(), "Record Updated");
            }

            s.save(db);
            t.commit();
            System.out.println("Record Updated hib");
          }else {
            JOptionPane.showMessageDialog(new JDialog(), "Enter Valid Marks");
          }
          //new DbHandler().updateStudent(rno,sname,m1,m2,m3);
        }
        else if (rno <= 0 && rno != ' ') {
           System.out.println("count 7");
          JOptionPane.showMessageDialog(new JDialog(), "Enter Valid Roll NO");
        }
        else if (sname == null) {
          System.out.println("count 2");
          JOptionPane.showMessageDialog(new JDialog(), "Enter Valid Name");
        }
        else if (len < 2) {
           System.out.println("count 8");
          JOptionPane.showMessageDialog(new JDialog(), "Enter Valid Name");
        }
      }
      catch(Exception e) {
        System.out.println(" Error hib" + e);
      }
      finally {
      // s.close();
        System.out.println(" closed ");
      }
    };
    btnsave.addActionListener(a);

    setTitle("S.M.S UPDATE");
    setSize(250, 350);
    setLocationRelativeTo(null);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);
  }
}

class DeleteStud extends JFrame {
  Container c;
  JTextField tadata;
  JButton btnback,
  btnsave;
  DeleteStud() {
    c = getContentPane();
    c.setLayout(new FlowLayout());
    tadata = new JTextField(20);
    btnback = new JButton("Back");
    btnsave = new JButton("Delete");

    c.add(tadata);
    c.add(btnsave);
    c.add(btnback);
    ActionListener al = (ae) ->{
      new Sms();
      dispose();
    };
    btnback.addActionListener(al);
    ActionListener a = (ae) ->{
      int rno = 0;
      try {
        rno = Integer.parseInt(tadata.getText());
      }
      catch(NumberFormatException e) {
        System.out.println(e);
      }
      Configuration cfg = new Configuration();
      cfg.configure("hibernate.cfg.xml");
      SessionFactory sf = cfg.buildSessionFactory();
      Transaction t = null;
      Session s = null;
      try {
        s = sf.openSession();
        System.out.println(" Connected hib");
        t = s.beginTransaction();
        DBHandler db = (DBHandler) s.get(DBHandler.class, rno);
        if (db == null) {
          JOptionPane.showMessageDialog(new JDialog(), "Student Does not Exist!!");
        } else {
          s.delete(db);
          JOptionPane.showMessageDialog(new JDialog(), "Record Deleted");
          t.commit();
          System.out.println("Record Deleted hib");
        }
      } catch(Exception e) {
        System.out.println(" Error hib");
      }
      finally {
        s.close();
        System.out.println(" closed ");
      }

      //new DbHandler().deleteStudent(rno);
    };
    btnsave.addActionListener(a);

    setTitle("S.M.S Delete");
    setSize(350, 350);
    setLocationRelativeTo(null);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);
  }

}

class ChartsStudent extends JFrame {
  ChartsStudent() {
    // step 1: create dataset's

    DefaultCategoryDataset ds1 = new DefaultCategoryDataset();

    Configuration cfg = new Configuration();
    cfg.configure("hibernate.cfg.xml");
    SessionFactory sf = cfg.buildSessionFactory();
    Session s = null;
    try {
      s = sf.openSession();
      System.out.println(" Connected hib");
      java.util.List < DBHandler > data = new ArrayList < >();
      data = s.createQuery("from DBHandler").list();
      for (DBHandler db: data) {
        String name = db.getsname();
        int Marks1 = db.getm1();;
        int Marks2 = db.getm2();
        int Marks3 = db.getm3();

        ds1.addValue(Marks1, name, "Marks1");
        ds1.addValue(Marks2, name, "Marks2");
        ds1.addValue(Marks3, name, "Marks3");
      }

    } catch(Exception e) {
      System.out.println("db issue " + e);
    }

    // step 2 : design chart
    JFreeChart chart = ChartFactory.createBarChart("Student Performance", "Subjects", "Marks", ds1, PlotOrientation.VERTICAL, true, false, false);

    //step 3 : chart display
    ChartPanel p = new ChartPanel(chart);
    setContentPane(p);

    // step 4: save the chart as jpeg file
    try {
      File img = new File("perform.jpeg");
      ChartUtilities.saveChartAsJPEG(img, chart, 400, 300);
    } catch(IOException e) {
      System.out.println("issue " + e);
    } finally {
      s.close();
      System.out.println(" closed ");
    }

    setTitle("Student Performance");
    setSize(400, 300);
    setLocationRelativeTo(null);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);

  }
}