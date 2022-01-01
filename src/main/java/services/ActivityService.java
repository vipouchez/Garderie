package services;

import dao.ActivityDao;
import models.Activity;
import models.Employee;

import java.util.List;

public class ActivityService {

    // singleton design pattern

    private ActivityService(){
    }

    private static ActivityService instance = new ActivityService();

    public static ActivityService getInstance(){
        return instance;
    }

    ActivityDao dao = ActivityDao.getInstance();
    EmployeeService employeeService =  EmployeeService.getInstance();

    public Activity getActivity(int activityId) {
        return dao.findById(activityId);
    }

    public List<Activity> findAll(){
        try {
            return dao.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Activity addActivity(Activity a ){
        return dao.save(a);
    }

    public void deleteActivity(int id){
        dao.deleteById(id);
    }

    public void setResponsibleEmployee(int activityId, int employeeId){
        Activity a = dao.findById(activityId);
        Employee e = employeeService.getEmployee(employeeId);
        a.setResponsible(e);
    }
}
