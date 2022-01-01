package vues;

import dao.EmployeeDao;
import models.Activity;
import models.Address;
import models.Employee;
import models.Group;
import services.ActivityService;
import services.EmployeeService;
import services.GroupService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

public class ActivityMenu {
    private JPanel mainPanel;
    private JTextField responsible;
    private JTextField label;
    private JTextField group;
    private JButton addButton;
    private JButton removeButton;
    private JButton updateButton;
    private JButton returnButton;
    private JTable table1;
    private JComboBox comboBox1;

    GroupService groupService = GroupService.getInstance();
    ActivityService activityService = ActivityService.getInstance();
    JFrame frame = new JFrame();
    DefaultTableModel model;
    Object[] column = {"ID", "Activity name", "Responsible name", "Responsible ID", "Group name"};


    private void fillTable() throws Exception {
        model = new DefaultTableModel();
        model.setColumnIdentifiers(column);
        table1.setModel(model);
        String SelectedGroup;
        List<Activity> activities = new LinkedList<>();
        activities = activityService.findAll();
        for (int i = 0; i < activities.size(); i++) {
            Activity a = activities.get(i);
            model.addRow(new Object[]{a.getId(), a.getLabel(), a.getResponsible().getFirstName() + " " + a.getResponsible().getLastName(), a.getResponsible().getId(), a.getGroup().getName()});

        }

        table1.setModel(model);
    }


    public ActivityMenu() throws Exception {
        frame.setContentPane(mainPanel);
        mainPanel.setSize(600, 600);
        frame.setTitle("Home");
        frame.setSize(1000, 600);//size of jframe
        frame.setLocationRelativeTo(null); // set JFrame in center of the screen
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // closes the window
        frame.setVisible(true);
        fillTable();


        groupService.getGroups().forEach(e -> {
            comboBox1.addItem(e.getName());
        });
        comboBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getActionCommand().equals("comboBoxChanged")) {

                    String selectedGroup = (String) comboBox1.getSelectedItem();

                    // TODO: update list of activities
                }
            }
        });


        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == addButton) {
                    Group g = new Group();
                    Employee emp = new Employee();
                    Activity a = new Activity();

                    a.setLabel(label.getText());
                    emp.setId(Integer.parseInt(responsible.getText()));
                    a.setResponsible(emp);
                    g.setName(String.valueOf(comboBox1.getSelectedItem()));
                    a.setGroup(g);
                    activityService.addActivity(a);

                    // add values to table
                    model.addRow(new Object[]{a.getId(), a.getLabel(), a.getResponsible().getFirstName() + " " + a.getResponsible().getLastName(), a.getResponsible().getId(), a.getGroup().getName()});
                    JOptionPane.showMessageDialog(frame, "Activity added successfully.");
                    //reset fields to 0 :
                    label.setText("");
                    responsible.setText("");
                    group.setText("");


                    try {
                        fillTable();
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                }
            }
        });


        //gets the information of the employee from the table and shows it in the textfields
        table1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int selectedRow = table1.getSelectedRow();
                TableModel model = table1.getModel();

                label.setText(model.getValueAt(selectedRow, 1).toString());
                responsible.setText(model.getValueAt(selectedRow, 3).toString());
                group.setText(model.getValueAt(selectedRow, 4).toString());

            }
        });


        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == updateButton) {

                    Activity a = new Activity();
                    a.setLabel(label.getText());
                    a.setResponsible(new Employee());
                    a.getResponsible().setId(Integer.parseInt(responsible.getText()));

                    a.setGroup(new Group());
                    a.getGroup().setName(String.valueOf(comboBox1.getSelectedItem()));


                    //gets the activity ID from the table and set it to the updating Activity parameters :
                    int selectedRow = table1.getSelectedRow();
                    TableModel model1 = table1.getModel();
                    String id;
                    id = model1.getValueAt(selectedRow, 0).toString();
                    a.setId(Integer.parseInt(id));




                    //execute the update service :
                    activityService.updateActivity(a);
                    //deletes the row selected :
                    ((DefaultTableModel) table1.getModel()).removeRow(selectedRow);
                    //re-add the updated row :
                    model.addRow(new Object[]{a.getId(), a.getLabel(), a.getResponsible().getFirstName() + " " + a.getResponsible().getLastName(), a.getResponsible().getId(), a.getGroup().getName()});
                    //shows a confirmation update message
                    JOptionPane.showMessageDialog(frame, "Activity updated successfully.");
                    //reset fields to 0 :
                    label.setText("");
                    responsible.setText("");
                    group.setText("");


                    try {
                        fillTable();
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }

                }

            }
        });

        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Activity a = new Activity();
                int selectedRow = table1.getSelectedRow();
                TableModel model = table1.getModel();
                String id;
                id = model.getValueAt(selectedRow, 0).toString();
                a.setId(Integer.parseInt(id));
                activityService.deleteActivity(a.getId());
                ((DefaultTableModel) table1.getModel()).removeRow(selectedRow);


                //shows a confirmation update message
                JOptionPane.showMessageDialog(frame, "Activity Deleted successfully.");
                //reset fields to 0 :
                label.setText("");
                responsible.setText("");
                group.setText("");
            }
        });

        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Home home = new Home();
                frame.dispose();
            }
        });
    }


}
