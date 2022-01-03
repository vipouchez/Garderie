package vues;

import dao.GroupDao;
import models.Activity;
import models.Group;
import models.Student;
import services.ActivityService;
import services.GroupService;
import services.StudentService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class GroupMenu {
    private JPanel mainPanel;
    private JComboBox comboBox1;
    private JTextField groupTextField;
    private JButton addGroupButton;
    private JTable activityTable;
    private JTable studentTable;
    private JButton removeButton;
    private JButton returnButton;


    GroupService groupService = GroupService.getInstance();
    StudentService studentService = StudentService.getInstance();
    JFrame frame = new JFrame();
    Object[] groupColumns = {"ID", "First name", "Last Name"};
    Object[] activityColumns = {"ID", "Label", "Responsable"};


    public GroupMenu() throws Exception {
        frame.setContentPane(mainPanel);
        mainPanel.setSize(600, 600);
        frame.setTitle("Group menu");
        frame.setSize(1000, 600);//size of jframe
        frame.setLocationRelativeTo(null); // set JFrame in center of the screen
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // closes the window
        frame.setVisible(true);

        groupService.getGroups().forEach(e -> {
            comboBox1.addItem(e.getName());
        });
        comboBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getActionCommand().equals("comboBoxChanged")) {
                    String currentSelectedGroup = (String) comboBox1.getModel().getSelectedItem();
                    fillGroupTable(currentSelectedGroup);
                    fillActivityTable(currentSelectedGroup);
                    // TODO: update list of activities
                }
            }
        });


        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Home home = new Home();
                frame.dispose();
            }
        });
        addGroupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Group g = new Group();
                g.setName(groupTextField.getText());
                try {
                    groupService.addGroup(g);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
                JOptionPane.showMessageDialog(frame, "Group added successfully.");

                //reset fields to 0 :
                groupTextField.setText("");


            }

        });
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                groupService.removeGroup(groupTextField.getText());
                JOptionPane.showMessageDialog(frame, "Group deleted successfully.");
            }

        });
    }

    private void fillActivityTable(String currentSelectedGroup) {
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(activityColumns);
        activityTable.setModel(model);

        List<Activity> activities = ActivityService.getInstance().findAll();

        for (int i = 0; i < activities.size(); i++) {
            Activity a = activities.get(i);
            if (a.getGroup().getName().equals(currentSelectedGroup)) {
                String responsableName = a.getResponsible().getLastName() + " " + a.getResponsible().getFirstName();
                model.addRow(new Object[]{a.getId(), a.getLabel(), responsableName});
            }

        }
        activityTable.setModel(model);
    }

    private void fillGroupTable(String groupName) {
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(groupColumns);
        studentTable.setModel(model);

        List<Student> students = null;
        try {
            students = studentService.getStudents();
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (int i = 0; i < students.size(); i++) {
            Student s = students.get(i);
            if (s.getGroup().getName().equals(groupName)) {
                model.addRow(new Object[]{s.getId(), s.getFirstName(), s.getLastName()});
            }
        }
        studentTable.setModel(model);
    }
}
