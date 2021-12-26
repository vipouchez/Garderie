package services;

import dao.ActivityDao;
import models.Activity;
import models.Employee;

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

    public Activity getActivity(String activityId) {
        return dao.findById(activityId);
    }

    public Activity addActivity(Activity a ){
        return dao.save(a);
    }


    public void deleteActivity(String id){
        dao.deleteById(id);
    }



    public void setResponsibleEmployee(String activityId, int employeeId){
        Activity a = dao.findById(activityId);
        Employee e = employeeService.getEmployee(employeeId);
        a.setResponsible(e);
    }
}
