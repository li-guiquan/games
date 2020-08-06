package com.quan.games.controller;


import com.alibaba.fastjson.JSONObject;
import com.quan.games.entity.Article;
import com.quan.games.entity.Manage;
import com.quan.games.entity.Post;
import com.quan.games.service.IManageService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/manage")
public class ManageController {
    @Resource
    private IManageService manageService;
    @Resource
    private Manage manage;

    @RequestMapping(value = "/getList", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Object getListById() {
        Manage list = manageService.getListById(manage.getMId());
        String json = JSONObject.toJSONString(list);
        return json;
    }

    @RequestMapping(value = "/update", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Object updateManage(Manage manages) {
        Integer rSet = manageService.updateManage(manages);
        String json = JSONObject.toJSONString(rSet);
        return json;
    }


    //查看所有帖子
    @RequestMapping(value = "/getListPost", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Object getListPost() {
        List<Post> listPost = manageService.getListPost();
        String json = JSONObject.toJSONString(listPost);
        return json;
    }

    //    查找某个用户的帖子
    @RequestMapping(value = "/getUserPostById", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Object getUserPostById(Integer uid) {
        List<Post> userPost = manageService.getUserPostById(uid);
        String json = JSONObject.toJSONString(userPost);
        return json;
    }

    //    待审核的帖子
    @RequestMapping(value = "/auditPostAll", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Object auditPostAll() {
        List<Post> auditPost = manageService.auditPostAll();
        String json = JSONObject.toJSONString(auditPost);
        return json;
    }

    //    审核帖子
    @RequestMapping(value = "/auditPost", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Object auditPost(int num, int pid) {
        Integer rSet = manageService.auditPost(num, pid);
        String json = JSONObject.toJSONString(rSet);
        return json;
    }

    //删除某个用户的帖子
    @RequestMapping(value = "/deleteUserPost", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Object deleteUserPost(int num, int pids, int userid) {
        Integer rSet = manageService.deleteUserPost(num, pids, userid);
        String json = JSONObject.toJSONString(rSet);
        return json;
    }

/*    //查看所有的论坛内容
    @RequestMapping(value = "/getListArticle", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Object getListArticle() {
        List<Article> listArticle = manageService.getListArticle();
        String json = JSONObject.toJSONString(listArticle);
        return json;
    }*/
//
    //更新论坛内容标题
    @RequestMapping(value = "/updateArticleTitle", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Object updateArticleTitle(String title, int aid) {
        Integer rSet = manageService.updateArticleTitle(title, aid);
        String json = JSONObject.toJSONString(rSet);
        return json;
    }

    //更新论坛内容
    @RequestMapping(value = "/updateArticleContent", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Object updateArticleContent(String content, int aid) {
        Integer rSet = manageService.updateArticleContent(content, aid);
        String json = JSONObject.toJSONString(rSet);
        return json;
    }

    //更新论坛内容的图片
    @RequestMapping(value = "/updateArticleImg", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Object updateArticleImg(@RequestParam("img3") MultipartFile[] img,
                                   @RequestParam("aid") int aid, HttpServletRequest request) {
        String path = "F:\\QF\\二阶段项目\\Game_admin\\views\\assert\\img";
        String Filename = "";
        for (int i = 0; i < img.length; i++) {
            File dir = new File(path);
            if (!dir.isDirectory()) {
                dir.mkdir();
            }
            if (!img[i].isEmpty()) {
                try {
                    Filename = img[i].getOriginalFilename();
                    img[i].transferTo(new File(path + "//" + Filename));
                } catch (IOException e) {
                    System.out.println(e);
                    e.printStackTrace();
                }
            }
        }
        Integer rSet = manageService.updateArticleImg(Filename, aid);
        String json = JSONObject.toJSONString(rSet);
        return json;
    }

    //添加论坛内容
    @RequestMapping(value = "/addArticle", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Object addArticle(HttpServletRequest request,
                             @RequestParam("title") String title,
                             @RequestParam("content") String content,
                             @RequestParam("img") MultipartFile[] img,
                             @RequestParam("createDate") String createDate,
                             @RequestParam("category") int category) throws IOException {
        String path = "F:\\QF\\二阶段项目\\Game_admin\\views\\assert\\img";
        String Filename = "";
        for (int i = 0; i < img.length; i++) {
            File dir = new File(path);
            if (!dir.isDirectory()) {
                dir.mkdir();
            }
            if (!img[i].isEmpty()) {
                try {
                    Filename = img[i].getOriginalFilename();
                    img[i].transferTo(new File(path + "//" + Filename));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        Integer rSet = manageService.addArticle(title, content, Filename, createDate, category);
        String json = JSONObject.toJSONString(rSet);
        return json;
    }

    //删除论坛内容
    @RequestMapping(value = "/deleteArticle", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Object deleteArticle(int aId) {
        Integer rSet = manageService.deleteArticle(aId);
        String json = JSONObject.toJSONString(rSet);
        return json;
    }

 /*   //搜索论坛内容
    @RequestMapping(value = "/getListByContent", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Object getListByContent(String txt) {
        List<Article> listByContent = manageService.getListByContent(txt);
        String json = JSONObject.toJSONString(listByContent);
        return json;
    }
*/
}
