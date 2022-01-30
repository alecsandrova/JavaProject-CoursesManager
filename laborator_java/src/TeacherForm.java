import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.io.*;
import java.awt.event.ActionListener;

public class TeacherForm {
    private final JFrame owner;
    private JPanel panel1;
    private JTabbedPane tabbedPane1;
    private JTable tabelCourses;
    private JTabbedPane tabbedPane2;
    private JTable tabelStudents;
    private JButton veziStudentiButton;

    private JButton addCoursebtn;
    private JButton addStudentbtn;
    private JTextField courseName;
    private JTextField courseDescription;
    private JPanel AddCoursePanel;
    private JPanel AddStudentPanel;
    private JTextField studentname;
    private JLabel Year;
    private JTextField studentYear;
    private JTextField studentGrade;


    public TeacherForm(JFrame owner) {
        this.owner = owner;

        veziStudentiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if ( e.getSource() == veziStudentiButton) {
                    System.out.println("veziStudenti");
                    File file2 = new File("note.csv");
                    try {
                        FileReader fr = new FileReader(file2);
                        BufferedReader br = new BufferedReader(fr);
                        String firstLine = br.readLine();
                        if (firstLine != null) {
                            String[] columnsName = firstLine.split(",");

                            modelStudenti.setColumnIdentifiers(columnsName);
                            Object[] lines = br.lines().toArray();
                            modelStudenti.setRowCount(0);

                                for (int i = 0; i < lines.length; i++) {
                                    int selectedIndex = tabelCourses.getSelectedRow();
                                    if(selectedIndex != -1) {
                                        String materie = tabelCourses.getValueAt(selectedIndex, 0).toString();
                                        String[] row = lines[i].toString().split(",");
                                        if(row[2].toString().equals(materie)){
                                            modelStudenti.addRow(row);
                                        }
                                    }
                                }
                        }
                    } catch (Exception ex) {
                        System.out.println(ex);
                    }
                }
            }
        });



        addCoursebtn.addActionListener(new ActionListener() {
           @Override
            public void actionPerformed(ActionEvent e) {
               AddCoursePanel.setVisible(true);
               if(!courseName.getText().isEmpty() && !courseDescription.getText().isEmpty()){
                   String line = courseName.getText().toString() + "," + courseDescription.getText().toString()
                           + "," + Application.getInstance().currentUser.firstName + " "
                           + Application.getInstance().currentUser.lastName;


                   try (FileWriter writer = new FileWriter("cursuri.csv", true);
                        BufferedWriter bw = new BufferedWriter(writer)) {

                       bw.newLine();
                       bw.write(line);
                       bw.close();
                       AddCoursePanel.setVisible(false);
                       getPanel1();

                   } catch (IOException error) {
                       System.err.format("IOException: %s%n", error);
                   }
               }
           }
        });

        addStudentbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddStudentPanel.setVisible(true);
                String line;
                if(!studentname.getText().isEmpty() && !studentYear.getText().isEmpty()){
                    int selectedIndex = tabelCourses.getSelectedRow();
                    String materie = tabelCourses.getValueAt(selectedIndex, 0).toString();
                   if(!studentGrade.getText().isEmpty()) {
                        line = studentname.getText().toString() + "," + studentYear.getText().toString()
                           + "," + materie + "," +studentGrade.getText().toString();
                   }
                   else {
                     line = studentname.getText().toString() + "," + studentYear.getText().toString()
                               + "," + materie + "," + "null";
                   }


                    try (FileWriter writer = new FileWriter("note.csv", true);
                         BufferedWriter bw = new BufferedWriter(writer)) {

                        bw.newLine();
                        bw.write(line);
                        bw.close();
                        AddStudentPanel.setVisible(false);
                        getPanel1();

                    } catch (IOException error) {
                        System.err.format("IOException: %s%n", error);
                    }
                }
            }


        });
    }

    DefaultTableModel modelCursuri = (DefaultTableModel) tabelCourses.getModel();
    DefaultTableModel modelStudenti = (DefaultTableModel) tabelStudents.getModel();
    public JPanel getPanel1() {
        AddCoursePanel.setVisible(false);
        AddStudentPanel.setVisible(false);
        File file = new File("cursuri.csv");
        try {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String firstLine = br.readLine();
            if (firstLine != null) {
                String[] columnsName = firstLine.split(",");
                String[] CoursesColumns = new String[2];
                modelCursuri.setColumnIdentifiers(columnsName);
                Object[] lines = br.lines().toArray();
                modelCursuri.setRowCount(0);
                modelStudenti.setColumnIdentifiers(columnsName);
                modelStudenti.setRowCount(0);

                for (int i = 0; i < lines.length; i++) {
                    String[] row = lines[i].toString().split(",");
                    User user = Application.getInstance().currentUser;
                    if (row[2].toString().equals(user.firstName +" "+ user.lastName)) {
                        modelCursuri.addRow(row);
//                    modelCursuriNote.addRow(row);
                    }
                }
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }

        return panel1;
    }


}
