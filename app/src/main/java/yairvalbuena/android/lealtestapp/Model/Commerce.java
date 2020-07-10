package yairvalbuena.android.lealtestapp.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Commerce {

    @SerializedName("id")
    public int id;
    @SerializedName("name")
    public String name;
    @SerializedName("branches")
    public List<Branch> branches;
    @SerializedName("valueToPoints")
    public int valueToPoints;


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

    public List<Branch> getBranches() {
        return branches;
    }

    public void setBranches(List<Branch> branches) {
        this.branches = branches;
    }

    public int getValueToPoints() {
        return valueToPoints;
    }

    public void setValueToPoints(int valueToPoints) {
        this.valueToPoints = valueToPoints;
    }
}
