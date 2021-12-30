package vues;


import dao.StudentDao;
import models.Address;
import models.Employee;
import models.Group;
import models.Student;

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

public class StudentMenu {
    private JPanel mainPanel;
    private JTextField fatherName;
    private JTextField firstName;
    private JTextField lastName;
    private JTextField birthday;
    private JTextField motherName;
    private JTextField grandFatherName;
    private JTextField fatherCin;
    private JTextField fatherPhone;
    private JTable table1;
    private JButton addStudentButton;
    private JButton returnButton;
    private JButton updateStudentButton;
    private JButton removeButton;
    private JTextField postalCode;
    private JTextField roadName;
    private JTextField city;
    private JTextField group;


    StudentDao dao = StudentDao.getInstance();
    JFrame frame = new JFrame();


    DefaultTableModel model;


    Object[] column = {"ID", "First name", "Last Name", "father name", "Birthday", "Mother Name","Grand f.Name","Father CIN","Father Phone", "Postal Code", " road Name", "City","Group"};
    private void fillTable() throws Exception {
        model = new DefaultTableModel();
        model.setColumnIdentifiers(column);
        table1.setModel(model);

        List<Student> students = new LinkedList<>();
        students = dao.findAll();
        for (int i = 0; i < students.size(); i++) {
            Student s = students.get(i);
            Address a = new Address();
            a = s.getAddress();
            model.addRow(new Object[]{s.getId(), s.getFirstName(), s.getLastName(), s.getFatherName(), s.getBirthday(),s.getMotherName(), s.getGrandFatherName(),s.getFatherCin(),s.getFatherPhoneNumber(),
                    a.getPostalCode(), a.getRoadName(), a.getCity(),s.getGroup().getName()});
        }

        table1.setModel(model);
    }


    public StudentMenu() throws Exception {
        frame.setContentPane(mainPanel);
        mainPanel.setSize(600, 600);
        frame.setTitle("Home");
        frame.setSize(1000, 600);//size of jframe
        frame.setLocationRelativeTo(null); // set JFrame in center of the screen
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // closes the window
        frame.setVisible(true);
        fillTable();


        addStudentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == addStudentButton) {

                    Student s = new Student();

                    s.setFirstName(firstName.getText());
                    s.setLastName(lastName.getText());
                    s.setFatherName(fatherName.getText());
                    s.setMotherName(motherName.getText());
                    s.setGrandFatherName(grandFatherName.getText());
                    s.setFatherCin(fatherCin.getText());
                    s.setFatherPhoneNumber(fatherPhone.getText());
                    s.setAddress(new Address());
                    s.getAddress().setRoadName(roadName.getText());
                    s.getAddress().setPostalCode(Integer.parseInt(postalCode.getText()));
                    s.getAddress().setCity(city.getText());
                    s.setBirthday(LocalDate.now());
                    s.getGroup().setName(group.getText()); //todo

                    dao.save(s);
                    model.addRow(new Object[]{s.getId(), s.getFirstName(), s.getLastName(), s.getFatherName(), s.getBirthday(),s.getMotherName(), s.getGrandFatherName(), s.getFatherCin(), s.getFatherPhoneNumber(),
                            s.getAddress().getPostalCode(), s.getAddress().getRoadName(), s.getAddress().getCity(),s.getGroup().getName()});
                    JOptionPane.showMessageDialog(frame, "Student added successfully.");
                    //reset fields to 0 :
                    firstName.setText("");
                    lastName.setText("");
                    fatherName.setText("");
                    birthday.setText("");
                    motherName.setText("");
                    grandFatherName.setText("");
                    fatherCin.setText("");
                    fatherPhone.setText("");
                    roadName.setText("");
                    postalCode.setText("");
                    city.setText("");
                    group.setText("");

                }
            }
        });


        //gets the information of the student from the table and shows it in the textfields
        table1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int selectedRow = table1.getSelectedRow();
                TableModel model = table1.getModel();

                firstName.setText(model.getValueAt(selectedRow, 1).toString());
                lastName.setText(model.getValueAt(selectedRow, 2).toString());
                fatherName.setText(model.getValueAt(selectedRow, 3).toString());
                birthday.setText(model.getValueAt(selectedRow, 4).toString());
                motherName.setText(model.getValueAt(selectedRow, 5).toString());
                grandFatherName.setText(model.getValueAt(selectedRow, 6).toString());
                fatherCin.setText(model.getValueAt(selectedRow, 7).toString());
                fatherPhone.setText(model.getValueAt(selectedRow, 8).toString());
                postalCode.setText(model.getValueAt(selectedRow, 9).toString());
                roadName.setText(model.getValueAt(selectedRow, 10).toString());
                city.setText(model.getValueAt(selectedRow, 11).toString());
                group.setText(model.getValueAt(selectedRow, 12).toString());

            }
        });


        updateStudentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == updateStudentButton) {

                    Student s = new Student();
                    s.setFirstName(firstName.getText());
                    s.setLastName(lastName.getText());
                    s.setFatherName(fatherName.getText());
                    s.setMotherName(motherName.getText());
                    s.setGrandFatherName(grandFatherName.getText());
                    s.setFatherCin(fatherCin.getText());
                    s.setFatherPhoneNumber(fatherPhone.getText());
                    s.setAddress(new Address());
                    s.getAddress().setRoadName(roadName.getText());
                    s.getAddress().setPostalCode(Integer.parseInt(postalCode.getText()));
                    s.getAddress().setCity(city.getText());
                    s.setBirthday(LocalDate.now());
                    s.getGroup().setName(group.getText());

                    //gets the student ID from the table and set it to the updating student parameters :
                    int selectedRow = table1.getSelectedRow();
                    TableModel model1 = table1.getModel();
                    String id;
                    id = model1.getValueAt(selectedRow, 0).toString();
                    s.setId(Integer.parseInt(id));

                    //execute the update dao :
                    dao.update(s);
                    //deletes the row selected :
                    ((DefaultTableModel) table1.getModel()).removeRow(selectedRow);
                    //re-add the updated row :
                    model.addRow(new Object[]{s.getId(), s.getFirstName(), s.getLastName(), s.getFatherName(), s.getBirthday(),s.getMotherName(), s.getGrandFatherName(), s.getFatherCin(), s.getFatherPhoneNumber(),
                            s.getAddress().getPostalCode(), s.getAddress().getRoadName(), s.getAddress().getCity(),s.getGroup().getName()});


                    //shows a confirmation update message
                    JOptionPane.showMessageDialog(frame, "Student updated successfully.");
                    //reset fields to 0 :
                    firstName.setText("");
                    lastName.setText("");
                    fatherName.setText("");
                    birthday.setText("");
                    motherName.setText("");
                    grandFatherName.setText("");
                    fatherCin.setText("");
                    fatherPhone.setText("");
                    roadName.setText("");
                    postalCode.setText("");
                    city.setText("");
                    group.setText("");
                }

            }
        });

        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Student s = new Student()
;                int selectedRow = table1.getSelectedRow();
                TableModel model = table1.getModel();
                String id;
                id = model.getValueAt(selectedRow, 0).toString();
                s.setId(Integer.parseInt(id));
                dao.deleteById(s.getId());
                ((DefaultTableModel) table1.getModel()).removeRow(selectedRow);


                //shows a confirmation update message
                JOptionPane.showMessageDialog(frame, "Student Deleted successfully.");
                //reset fields to 0 :
                firstName.setText("");
                lastName.setText("");
                fatherName.setText("");
                birthday.setText("");
                motherName.setText("");
                grandFatherName.setText("");
                fatherCin.setText("");
                fatherPhone.setText("");
                roadName.setText("");
                postalCode.setText("");
                city.setText("");

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
