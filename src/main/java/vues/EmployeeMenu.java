package vues;

import dao.EmployeeDao;
import models.Address;
import models.Employee;
import models.Student;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class EmployeeMenu extends JFrame{
    private JPanel mainPanel;
    private JTextField firstName;
    private JTextField lastName;
    private JTextField fatherName;
    private JTextField birthday;
    private JTextField cinNumber;
    private JTextField phoneNumber;
    private JTextField postalCode;
    private JTextField roadName;
    private JTextField city;
    private JButton addEmployeeButton;
    private JButton updateEmployeeButton;
    private JTable table1;
    private JScrollPane scrollPanel;

    EmployeeDao dao = EmployeeDao.getInstance();
    JFrame frame = new JFrame();


    DefaultTableModel model;


    Object[] column = {"ID","First name","Last Name","birthday","mother name"};
    Object[] row= new Object[0];

    private void fillTable() throws Exception{
        model = new DefaultTableModel();

        model.setColumnIdentifiers(column);
        table1.setModel(model);

        List<Employee> employees = new LinkedList<>();
        employees = dao.findAll();
        for (int i=0;i<employees.size();i++)
        {
            Employee e = employees.get(i) ;
            model.addRow(new Object[] { e.getId(),e.getFirstName(),e.getLastName(),e.getBirthday()});
        }

        table1.setModel(model);
    }











    public EmployeeMenu() throws Exception {
        setContentPane(mainPanel);
        mainPanel.setSize(600,600);
        setTitle("Home");
        setSize(600, 600);//size of jframe
        setLocationRelativeTo(null); // set JFrame in center of the screen
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // closes the window
        setVisible(true);
        fillTable();








        addEmployeeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource()==addEmployeeButton){

                    Employee emp = new Employee();
                    emp.setFirstName(firstName.getText());
                    emp.setLastName(lastName.getText());
                    emp.setFatherName(fatherName.getText());
                    emp.setCinNumber(cinNumber.getText());
                    emp.setPhoneNumber(phoneNumber.getText());
                    emp.setAddress(new Address());
                    emp.getAddress().setRoadName(roadName.getText());
                    emp.getAddress().setPostalCode(Integer.parseInt(postalCode.getText()));
                    emp.getAddress().setCity(city.getText());
                    emp.setBirthday(LocalDate.now());
                    dao.save(emp);
                    model.addRow(new Object[] { emp.getId(),emp.getFirstName(),emp.getLastName(),emp.getBirthday()});
                    JOptionPane.showMessageDialog(frame,"Employee added successfully.");
                    //reset fields to 0 :
                    firstName.setText("");
                   lastName.setText("");
                    fatherName.setText("");
                    cinNumber.setText("");
                    phoneNumber.setText("");
                    roadName.setText("");
                    postalCode.setText("");
                    city.setText("");
                }
            }
        });
    }

}
