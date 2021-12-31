package models;

public class Activity {

    private String id;
    private String label;
    private Employee responsible;

    private Group group;

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }



    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Employee getResponsible() {
        return responsible;
    }

    public void setResponsible(Employee responsible) {
        this.responsible = responsible;
    }

    public Activity() {
        this.id = id;
        this.label = label;
        this.responsible = responsible;
    }


    public String getId() {
        return id;
    }


    public void setId(String id) {
        this.id= id;
    }

    @Override
    public String toString() {
        return "Activity{" +
                "id='" + id+ '\'' +
                ", label='" + label + '\'' +
                ", responsible=" + responsible +
                '}';
    }
}

