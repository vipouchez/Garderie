package vues;

import dao.EmployeeDao;
import models.Address;
import models.Employee;
import models.Student;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
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

    EmployeeDao dao = EmployeeDao.getInstance();
    JFrame frame = new JFrame();


    public void addRowToTable() throws Exception {
        DefaultTableModel model = (DefaultTableModel) table1.getModel();
        List<Employee> emplist = new LinkedList<Employee>();
        emplist = dao.findAll();
        Object rowData[] = new Object[4];
        for (int i =0; i< emplist.size();i++){
            rowData[0] = emplist.get(i).getId();
            rowData[1] = emplist.get(i).getFirstName();
            rowData[2] = emplist.get(i).getLastName();
            rowData[3] = emplist.get(i).getFatherName();


        }

    }





    public EmployeeMenu() throws Exception {
        setContentPane(mainPanel);
        mainPanel.setSize(600,600);
        setTitle("Home");
        setSize(600, 600);//size of jframe
        setLocationRelativeTo(null); // set JFrame in center of the screen
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // closes the window
        setVisible(true);
        addRowToTable();

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
