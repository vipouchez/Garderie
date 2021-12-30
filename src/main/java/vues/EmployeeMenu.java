package vues;

import dao.EmployeeDao;
import models.Address;
import models.Employee;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

public class EmployeeMenu extends JFrame {
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
    private JButton returnButton;
    private JButton removeButton;

    EmployeeDao dao = EmployeeDao.getInstance();
    JFrame frame = new JFrame();


    DefaultTableModel model;


    Object[] column = {"ID", "First name", "Last Name", "father name","Birthday", "Cin Number", "Phone", "Postal Code", " road Name", "City"};

    private void fillTable() throws Exception {
        model = new DefaultTableModel();
        model.setColumnIdentifiers(column);
        table1.setModel(model);

        List<Employee> employees = new LinkedList<>();
        employees = dao.findAll();
        for (int i = 0; i < employees.size(); i++) {
            Employee e = employees.get(i);
            Address a = new Address();
            a = e.getAddress();
            model.addRow(new Object[]{e.getId(), e.getFirstName(), e.getLastName(), e.getFatherName(), e.getBirthday(), e.getCinNumber(), e.getPhoneNumber(),
                    a.getPostalCode(), a.getRoadName(), a.getCity()});
        }

        table1.setModel(model);
    }


    public EmployeeMenu() throws Exception {
        setContentPane(mainPanel);
        mainPanel.setSize(600, 600);
        setTitle("Home");
        setSize(1000, 600);//size of jframe
        setLocationRelativeTo(null); // set JFrame in center of the screen
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // closes the window
        setVisible(true);
        fillTable();


        addEmployeeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == addEmployeeButton) {

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
                    model.addRow(new Object[]{emp.getId(), emp.getFirstName(), emp.getLastName(), emp.getFatherName(), emp.getBirthday(), emp.getCinNumber(), emp.getPhoneNumber(),
                            emp.getAddress().getPostalCode(), emp.getAddress().getRoadName(), emp.getAddress().getCity()});
                    JOptionPane.showMessageDialog(frame, "Employee added successfully.");
                    //reset fields to 0 :
                    firstName.setText("");
                    birthday.setText("");
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


        //gets the information of the employee from the table and shows it in the textfields
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
                cinNumber.setText(model.getValueAt(selectedRow, 5).toString());
                phoneNumber.setText(model.getValueAt(selectedRow, 6).toString());
                postalCode.setText(model.getValueAt(selectedRow, 7).toString());
                roadName.setText(model.getValueAt(selectedRow, 8).toString());
                city.setText(model.getValueAt(selectedRow, 9).toString());
            }
        });


        updateEmployeeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == updateEmployeeButton) {

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

                    //gets the employee ID from the table and set it to the updating employee parameters :
                    int selectedRow = table1.getSelectedRow();
                    TableModel model1 = table1.getModel();
                    String id;
                    id = model1.getValueAt(selectedRow, 0).toString();
                    emp.setId(Integer.parseInt(id));

                    //execute the update dao :
                    dao.update(emp);
                    //deletes the row selected :
                    ((DefaultTableModel) table1.getModel()).removeRow(selectedRow);
                    //re-add the updated row :
                    model.addRow(new Object[]{emp.getId(), emp.getFirstName(), emp.getLastName(), emp.getFatherName(), emp.getBirthday(), emp.getCinNumber(), emp.getPhoneNumber(),
                            emp.getAddress().getPostalCode(), emp.getAddress().getRoadName(), emp.getAddress().getCity()});


                    //shows a confirmation update message
                    JOptionPane.showMessageDialog(frame, "Employee updated successfully.");
                    //reset fields to 0 :
                    firstName.setText("");
                    lastName.setText("");
                    fatherName.setText("");
                    cinNumber.setText("");
                    phoneNumber.setText("");
                    roadName.setText("");
                    postalCode.setText("");
                    birthday.setText("");
                    city.setText("");
                }

            }
        });

        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Employee emp = new Employee();
                int selectedRow = table1.getSelectedRow();
                TableModel model = table1.getModel();
                String id;
                id = model.getValueAt(selectedRow, 0).toString();
                emp.setId(Integer.parseInt(id));
                dao.deleteById(emp.getId());
                ((DefaultTableModel) table1.getModel()).removeRow(selectedRow);


                //shows a confirmation update message
                JOptionPane.showMessageDialog(frame, "Employee Deleted successfully.");
                //reset fields to 0 :
                firstName.setText("");
                lastName.setText("");
                fatherName.setText("");
                cinNumber.setText("");
                phoneNumber.setText("");
                roadName.setText("");
                postalCode.setText("");
                city.setText("");
                birthday.setText("");

            }
        });
        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Home home = new Home();
                dispose();
            }
        });
    }

}
