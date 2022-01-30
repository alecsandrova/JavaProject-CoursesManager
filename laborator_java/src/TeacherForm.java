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
    private JButton updateGradeButton;
    private JButton addCourseButton;


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


        updateGradeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if ( e.getSource() == veziStudentiButton) {
                    System.out.println("updateGradeButton");
                    File file2 = new File("note.csv");

                }



            }
        });
    }



    DefaultTableModel modelCursuri = (DefaultTableModel) tabelCourses.getModel();
    DefaultTableModel modelStudenti = (DefaultTableModel) tabelStudents.getModel();
    public JPanel getPanel1() {
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

                    modelCursuri.addRow(row);

                }
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }



        return panel1;
    }


}
