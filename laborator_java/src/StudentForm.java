import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class StudentForm {
    private final JFrame owner;
    private JPanel panel1;
    private JTabbedPane tabbedpane;
    private JButton medieButton;
    private JPanel Panel;
    private JTable tabelCoursesGrades;
    Double medie = Double.valueOf(0);
    int numarNote = 0;
    public StudentForm(JFrame owner) {
        this.owner = owner;



        medieButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if ( e.getSource() == medieButton) {
                    JOptionPane.showMessageDialog(null, "Media pentru studentul "
                            + Application.getInstance().currentUser.firstName + " "
                            + Application.getInstance().currentUser.lastName + " este "
                            + medie);
                }
            }
        });}


    DefaultTableModel modelCursuriNote = (DefaultTableModel) tabelCoursesGrades.getModel();
    public JPanel getPanel1() {
        File file = new File("note.csv");

        try {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String firstLine = br.readLine();
            if (firstLine != null) {
                String[] columnsName = firstLine.split(",");

                modelCursuriNote.setColumnIdentifiers(columnsName);
                Object[] lines = br.lines().toArray();
                modelCursuriNote.setRowCount(0);

                for (int i = 0; i < lines.length; i++) {
                    String[] row = lines[i].toString().split(",");
                    User user = Application.getInstance().currentUser;

                    if (row[0].toString().equals(user.firstName +" "+ user.lastName)) {
                        modelCursuriNote.addRow(row);
//                    modelCursuriNote.addRow(row);
                        if(!row[3].equals(null)){
                            medie =medie + Double.parseDouble(row[3]);
                            numarNote++;
                        }

                    }
                }
                medie = medie/numarNote;
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }

//                        User user = Application.getInstance().currentUser;
//                        String[] row = lines[i].toString().split(",");
//                        if(row[0].toString().equals(user.firstName + user.lastName)){
//                            modelCursuri.addRow(row);
//                    }





        return panel1;
    }

}
