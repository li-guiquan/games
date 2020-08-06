package com.quan.games.mappers;

import com.quan.games.entity.Article;
import com.quan.games.entity.Manage;
import com.quan.games.entity.Post;
import org.apache.ibatis.annotations.Param;
import java.util.List;

public interface IManageMapper {
    //检查管理员账号是否存在
    Integer checkedManageName(@Param("mName") String mName);

    //校验管理员账号密码
    Integer checkedManage(@Param("manageName") String mName, @Param("managePassword") String mPassword);

    //查看管理员信息
    Manage getListById(@Param("id") int id);

    //修改账号或密码
    Integer updateManage(@Param("admin") Manage manage);

    //查询所有用户的帖子
    List<Post> getListPost();

    //查询某个用户的发帖
    List<Post> getUserPostById(@Param("uid") int uid);

    //删除某用户的帖子
    Integer deleteUserPost(@Param("num") int num, @Param("pids") int pids, @Param("userid") int userid);

    //查询所有待审核的帖子
    List<Post> auditPostAll();

    //审核所有的帖子
    Integer auditPost(@Param("state") int state, @Param("pid") int pid);

    //查询所有的论坛内容
  //  List<Article> getListArticle();

    //修改论坛内容标题
    Integer updateArticleTitle(@Param("title") String title, @Param("aid") int aid);

    //修改论坛内容
    Integer updateArticleContent(@Param("content") String content, @Param("aid1") int aid);

    //修改论坛图片
    Integer updateArticleImg(@Param("img") String img, @Param("aid2") int aid);

    //添加论坛内容
    Integer addArticle(@Param("t1") String title, @Param("t2") String content, @Param("t3") String img, @Param("t4") String createDate, @Param("t5") int category);

    //删除论坛内容
    Integer deleteArticle(@Param("aId") int aId);

    //搜索论坛内容
  //  List<Article> getListByContent(@Param("txt") String txt);
}
