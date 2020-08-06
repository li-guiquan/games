package com.quan.games.controller;

import com.alibaba.fastjson.JSONObject;
import com.quan.games.entity.Manage;
import com.quan.games.service.IManageService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/manageLogin")
public class ManageLoginController {
    @Resource
    private IManageService manageService;
    @Resource
    private Manage manage;

    @RequestMapping(value = "/checkedName", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Object checkedName(String name) {
        Integer rSet = manageService.checkedManageName(name);
        String json = JSONObject.toJSONString(rSet);
        return json;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Object checkedManage(String name, String password, HttpServletRequest request) {
        Integer rSet = manageService.checkedManage(name, password);
        request.getSession().setAttribute("username", name);
        manage.setMId(rSet);
        String json = JSONObject.toJSONString(rSet);
        return json;
    }

}
