package com.quan.games.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

@Data
@Component
public class Article {
    private Integer aId;
    private String title;
    private String content;
    private String img;
    //fastjson 日期格式转换
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;
    private Integer categoryId;
    private Integer isDelete;

    @Override
    public String toString() {
        return "Article{" +
                "aId=" + aId +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", img='" + img + '\'' +
                ", createDate=" + createDate +
                ", categoryId=" + categoryId +
                ", isDelete=" + isDelete +
                '}';
    }

/* public static void main(String[] args) {
        Article article = new Article();
        article.setCreateDate(new Date());
        System.out.println(JSONObject.toJSON(article));
    }*/
}
