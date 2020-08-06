package com.quan.games.entity;


import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class Manage {
    private Integer mId;
    private String mName;
    private String mPassword;
    private Integer isDelete;
    //List<User> getUserList;

    @Override
    public String toString() {
        return "Manage{" +
                "mId=" + mId +
                ", mName='" + mName + '\'' +
                ", mPassword='" + mPassword + '\'' +
                ", isDelete=" + isDelete +
                '}';
    }
}
