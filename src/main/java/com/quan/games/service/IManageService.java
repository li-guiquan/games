package com.quan.games.service;

import com.quan.games.entity.Article;
import com.quan.games.entity.Manage;
import com.quan.games.entity.Post;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;


import java.util.List;
public interface IManageService {
    //第一功能 --管理员信息
    Integer checkedManageName(String name);

    Integer checkedManage(String mName, String mPassword);

    Manage getListById(int id);

    Integer updateManage(Manage manage);

    //第二功能  --管理帖子
    List<Post> getListPost();

    List<Post> getUserPostById(int uid);

    List<Post> auditPostAll();

    Integer auditPost(int state, int pid);

    Integer deleteUserPost(int num, int pids, int userid);

    //第四功能 ---管理论坛
    //查询所有的论坛内容
 //   List<Article> getListArticle();

    //修改论坛内容标题
    Integer updateArticleTitle(String title, int aid);

    //修改论坛内容
    Integer updateArticleContent(String content, int aid);

    //修改论坛图片
    Integer updateArticleImg(String img, int aid);

    //添加论坛内容
    Integer addArticle(String title, String content, String img, String createDate, int category);

    //删除论坛内容
    Integer deleteArticle(int aId);

    //搜索论坛内容
  //  List<Article> getListByContent(String txt);

}
