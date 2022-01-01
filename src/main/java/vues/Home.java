package vues;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Home extends JFrame {
    private JButton studentMenuButton;
    private JButton employeeMenuButton;
    private JButton groupManagementButton;
    private JButton activityManagementButton;
    private JPanel mainPanel;



    public Home()  {
        setContentPane(mainPanel);

        mainPanel.setSize(600,600);
        setTitle("Home");
        setSize(600, 600);//size of jframe
        setLocationRelativeTo(null); // set JFrame in center of the screen
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // closes the window
        setVisible(true);
        studentMenuButton.setBounds(100,160,200,40);
        studentMenuButton.setFocusable(false);



        employeeMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource()==employeeMenuButton){
                    try {
                        EmployeeMenu employeeMenu = new EmployeeMenu();
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                    dispose();

                }
            }
        });
        studentMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    StudentMenu studentMenu = new StudentMenu();
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
                dispose();
            }
        });
        groupManagementButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    GroupMenu menu = new GroupMenu();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                dispose();
            }
        });
    }


}
