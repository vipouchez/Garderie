package models;


import java.util.LinkedList;
import java.util.List;

public class Group {

    private String name; // functional id
    private List<Activity> activities;
    private List<Student> students;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addStudent(Student s ){
        if(students == null) students = new LinkedList<>();
        students.add(s);
    }

    public void removeStudent(Student s) {
        students.remove(s);
    }


    public void addActivity(Activity activity){
        if(activities == null) activities = new LinkedList<>();
        activities.add(activity);
    }

    @Override
    public String toString() {
        return "Group{" +
                "name='" + name +  '\'' +
                ", students=" + students +
                ", activities=" + activities +
                '}';
    }
}
