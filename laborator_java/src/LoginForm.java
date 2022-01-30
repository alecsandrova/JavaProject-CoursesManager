import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginForm {
    public LoginForm(JFrame owner) {
        this.owner = owner;
    btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if ( e.getSource() == btnLogin) {
                    System.out.println("Login");
                    try {
                        Application.getInstance().login(new User(txtUsername.getText(), new String(txtPassword.getPassword())));
                        JOptionPane.showMessageDialog(null, "Login successfully!");
                        mainPanel.setVisible(false);
                        if(Application.getInstance().currentUser.accountType.equals(UserAccountType.TEACHER)) {
                            owner.setContentPane(new TeacherForm(owner).getPanel1());
                        }
                        else owner.setContentPane(new StudentForm(owner).getPanel1());
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage());
                    }
                }

            }
        });
        btnNew.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if ( e.getSource() == btnNew) {
                    System.out.println("New account");
                    UserAccountType type = UserAccountType.valueOf(txtType.getText());
                    try {
                        Application.getInstance().addUser(new User(txtFirstName.getText(), txtLastName.getText(), txtUsername.getText(), new String(txtPassword.getPassword()), type ));
                        JOptionPane.showMessageDialog(null, "Added successfully!");
                        mainPanel.setVisible(false);
                        if(Application.getInstance().currentUser.accountType.equals(UserAccountType.TEACHER)) {
                            owner.setContentPane(new TeacherForm(owner).getPanel1());
                        }
                        else owner.setContentPane(new StudentForm(owner).getPanel1());
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });



        iDonTHaveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if ( e.getSource() == iDonTHaveButton) {
                    System.out.println("idonthavebutton");
                    iDonTHaveButton.setVisible(false);
                    Name1Panel.setVisible(true);
                    Name2Panel.setVisible(true);
                    TypePanel.setVisible(true);
                    btnNew.setVisible(true);
                }
            }
        });
    }

    public JPanel getMainPanel() {
        owner.setResizable(false);

        Name1Panel.setVisible(false);
        Name2Panel.setVisible(false);
        TypePanel.setVisible(false);
        btnNew.setVisible(false);
        return mainPanel;
    }

    private JPanel mainPanel;
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin;
    private JButton btnNew;
    private JTextField txtType;
    private JButton iDonTHaveButton;
    private JTextField txtLastName;
    private JTextField txtFirstName;
    private JPanel Name1Panel;
    private JPanel Name2Panel;
    private JPanel TypePanel;
    private JFrame owner;


}
