package yairvalbuena.android.lealtestapp.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Transaction {
    @SerializedName("id")
    public int id;
    @SerializedName("userId")
    public int userId;
    @SerializedName("createdDate")
    public String createdDate;
    @SerializedName("commerce")
    public Commerce commerce;
    @SerializedName("branch")
    public Branch branch;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public Commerce getCommerce() {
        return commerce;
    }

    public void setCommerce(Commerce commerce) {
        this.commerce = commerce;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }
}
