package ru.gb.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;


public class CreateGroupRequest {
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("groupName")
    @Expose
    private String groupName;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("roles")
    @Expose
    private List<Role> roles = new ArrayList<Role>();

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
