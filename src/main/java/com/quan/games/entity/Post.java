package com.quan.games.entity;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class Post {
    private Integer pId;
    private String pName;
    private String pContent;
    private Integer categoryId;
    private Integer userId;
    private Integer isDelete;

    @Override
    public String toString() {
        return "Post{" +
                "pId=" + pId +
                ", pName='" + pName + '\'' +
                ", pContent='" + pContent + '\'' +
                ", categoryId=" + categoryId +
                ", userId=" + userId +
                ", isDelete=" + isDelete +
                '}';
    }
}
