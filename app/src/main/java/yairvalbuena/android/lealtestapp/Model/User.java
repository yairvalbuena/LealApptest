package yairvalbuena.android.lealtestapp.Model;

import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("id")
    public int id;
    @SerializedName("createdDate")
    public String createdDate;
    @SerializedName("name")
    public String name;
    @SerializedName("birthday")
    public String birthday;
    @SerializedName("photo")
    public String photo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
