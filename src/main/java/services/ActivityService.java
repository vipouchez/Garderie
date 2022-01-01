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

    public void  addActivity(Activity a ){
         dao.save(a);
    }

    public void deleteActivity(int id){
        dao.deleteById(id);
    }


    public void deleteActivityByGroupId(String name){
        dao.deleteByGroupId(name);
    }



    public void updateActivity(Activity a){
        dao.update(a);
    }







}
