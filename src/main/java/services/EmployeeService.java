package services;

import dao.EmployeeDao;
import dao.GroupDao;
import models.Employee;

import java.util.List;

public class EmployeeService {

    private EmployeeDao employeeDao = EmployeeDao.getInstance();
    private GroupDao groupDao = GroupDao.getInstance();
    private GroupService groupService =  GroupService.getInstance();

    private EmployeeService(){}

    private static EmployeeService instance = new EmployeeService();

    public static EmployeeService getInstance(){
        return instance;
    }


    public void addEmployee(Employee employee) {
        employeeDao.save(employee);
    }

    public List<Employee> getEmployees() throws Exception {
        return employeeDao.findAll();
    }

    public Employee getEmployee(int id){
        return employeeDao.findById(id);
    }

}
