package pl.coderslab.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Plan implements Comparable<Plan>  {
    private int id;
    private String name;
    private String description;
    private String created;
    private int adminId;

    public Plan() {
    }

    public Plan(String name, String description, String created, int adminId) {
        this.name = name;
        this.description = description;
        this.created = created;
        this.adminId = adminId;
    }

    public Plan(String name, String description, int adminId) {
        this.name = name;
        this.description = description;
        this.adminId = adminId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    @Override
    public String toString() {
        return "Plan[" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", created='" + created + '\'' +
                ", adminId=" + adminId +
                ']';
    }

    @Override
    public int compareTo(Plan o) {
        try {
            Date thisDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(this.getCreated());
            Date nextDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(o.getCreated());

            return thisDate.compareTo(nextDate);
        } catch (ParseException e) {
            e.printStackTrace();
            return -1;
        }

    }
}
