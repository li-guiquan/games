package com.quan.games.service.impl;

import com.quan.games.entity.Manage;
import com.quan.games.entity.Post;
import com.quan.games.mappers.IManageMapper;
import com.quan.games.service.IManageService;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;
import java.util.List;

@Service("manageService")
public class ManageServiceImpl implements IManageService {
    @Resource
    private IManageMapper manageMapper;

    /**
     * 第一部分
     */
    @Override
    public Integer checkedManageName(String name) {
        Integer rSet = manageMapper.checkedManageName(name);
        return rSet;
    }

    @Override
    public Integer checkedManage(String mName, String mPassword) {
        Integer rSet = manageMapper.checkedManage(mName, mPassword);
        return rSet;
    }

    @Override
    public Manage getListById(int id) {
        Manage list = manageMapper.getListById(id);
        return list;
    }

    @Override
    public Integer updateManage(Manage manage) {
        Integer rSet = manageMapper.updateManage(manage);
        return rSet;
    }

    /**
     * 第二部分
     **/
//查询所有用户的帖子
    @Override
    public List<Post> getListPost() {
        List<Post> list = manageMapper.getListPost();
        return list;
    }

    //查询某个用户的发帖
    @Override
    public List<Post> getUserPostById(int uid) {
        List<Post> userPost = manageMapper.getUserPostById(uid);
        return userPost;
    }

    //查询所有待审核的帖子
    @Override
    public List<Post> auditPostAll() {
        List<Post> posts = manageMapper.auditPostAll();
        return posts;
    }

    //审核所有的帖子
    @Override
    public Integer auditPost(int state, int pid) {
        Integer rSet = manageMapper.auditPost(state, pid);
        return rSet;
    }

    //删除某个用户的发帖
    @Override
    public Integer deleteUserPost(int num, int pids, int userid) {
        Integer rSet = manageMapper.deleteUserPost(num, pids, userid);
        return rSet;
    }

    /**
     * 第四功能
     */
  /*  //查看所有的论坛内容
    @Override
    public Object getListArticle() {
      //  List<?> listArticle = manageMapper.getListArticle();
        return null;
    }
*/
    //更新论坛内容标题
    @Override
    public Integer updateArticleTitle(String title, int aid) {
        Integer rSet = manageMapper.updateArticleTitle(title, aid);
        return rSet;
    }

    //更新论坛内容
    @Override
    public Integer updateArticleContent(String content, int aid) {
        Integer rSet = manageMapper.updateArticleContent(content, aid);
        return rSet;
    }

    //更新论坛内容图片
    @Override
    public Integer updateArticleImg(String img, int aid) {
        Integer rSet = manageMapper.updateArticleImg(img, aid);
        return rSet;
    }

    //添加论坛内容
    @Override
    public Integer addArticle(String title, String content, String img, String createDate, int category) {
        Integer rSet = manageMapper.addArticle(title, content, img, createDate, category);
        return rSet;
    }

    //删除论坛内容
    @Override
    public Integer deleteArticle(int aId) {
        Integer rSet = manageMapper.deleteArticle(aId);
        return rSet;
    }

    /*//搜索论坛内容
    @Override
    public List getListByContent(String txt) {
        String text = "%" + txt + "%";
        System.out.println(text);
        List<?> listByContent = manageMapper.getListByContent(text);
        return listByContent;
    }
*/

}
