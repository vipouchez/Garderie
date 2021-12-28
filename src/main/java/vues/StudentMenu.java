package vues;

import javax.swing.*;
import java.awt.*;

public class StudentMenu extends JFrame{

    private JButton showAllStudentsButton;
    private JButton findStudentByIDButton;
    private JButton addStudentButton;
    private JButton removeStudentButton;
    private JButton updateStudentButton;
    private JPanel mainPanel;

    public StudentMenu()  {

        setContentPane(mainPanel);
        setTitle("Student Menu");
        setSize(300, 300);//size of jframe
        setLocationRelativeTo(null); // set JFrame in center of the screen
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // closes the window
        setVisible(true);
    }
}

