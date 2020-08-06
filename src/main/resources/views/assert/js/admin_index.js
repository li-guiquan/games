//第一模块展示 封装
function show1() {
    var url = "http://localhost:8080/Game_admin/manage/getList";
    var setting = {
        url: url,
        type: "GET",
        async: true,
        success: function (data) {
            var json = JSON.parse(data);
            var html = "<thead><tr>" +
                "<th>编号</th>" +
                "<th>名称</th>" +
                "<th>操作</th>" +
                "</tr></thead>";
            html += "<tbody><tr><td>" + json.mId + "</td><td>"
                + json.mName + "</td>" +
                "<td><button type='button' class='btn btn-primary' data-toggle='modal' data-target='#myModal' id='mId' value='" + json.mId + "' onclick='huixian()'>编辑</button>" +
                "<input type='hidden'id='mIds' value='" + json.mId + "'>" +
                "</td>" + "</tr></tbody>"
            $("#tablebody").html(html);

        }
    };
    $.ajax(url, setting);
}

//信息回显
function huixian() {
    var url = "http://localhost:8080/Game_admin/manage/getList";
    var setting = {
        url: url,
        type: "GET",
        async: true,
        success: function (data) {
            var json = JSON.parse(data);
            $("#username").val(json.mName);
            $("#password").val(json.mPassword);
        }
    };
    $.ajax(url, setting);
}

/* 第一功能*/
$(function () {
    $("#info").click(function () {
        $("#LB").css("display", "none");
        $("#two").css("display", "none");
        $("#four").css("display", "none");
        $("#one").css("display", "block");
        show1();
    });


    //修改管理员信息
    $("#submit").click(function () {
        var username = $("#username").val();
        var password = $("#password").val();
        var mId = $("#mIds").val();
        var manage = {
            mId: mId,
            mName: username,
            mPassword: password
        }
        var url = "http://localhost:8080/Game_admin/manage/update";
        var setting = {
            url: url,
            data: manage,
            type: "get",
            async: true,
            success: function (data) {
                if (data == 1) {
                    $("#msg").html("<font color='greed'>修改成功！！！</font>");
                    show1();
                } else {
                    $("#msg").html("<font color='red'>修改失败！！！</font>");
                }
            }
        };
        $.ajax(url, setting);
    });
});
$(function () {
    //    第二模块
    $("#post").click(function () {
        $("#LB").css("display", "none");
        $("#one").css("display", "none");
        $("#four").css("display", "none");
        $("#two").css("display", "block")
    });
    //    查看所有帖子
    $("#look").click(function () {
        var url = "http://localhost:8080/Game_admin/manage/getListPost"
        var setting = {
            url: "http://localhost:8080/Game_admin/manage/getListPost",
            type: "post",
            async: true,
            success: function (data) {
                var data = JSON.parse(data);
                var html = "<thead><tr>" +
                    "<th>编号</th>" +
                    "<th>贴名</th>" +
                    "<th>内容</th>" +
                    "<th>帖子类型</th>" +
                    "<th>用户ID</th>" +
                    "</tr></thead>";
                for (var i = 0; i < data.length; i++) {
                    html += "<tr><td>" + data[i].pId + "</td><td>" +
                        data[i].pName + "</td><td>" +
                        data[i].pContent + "</td><td>" +
                        data[i].categoryId + "</td><td>" +
                        data[i].userId + "</td></tr>"
                }
                $("#twobody").html(html);
            }
        };
        $.ajax(url, setting);
    });
});

//封装函数 查看待审核的帖子
function showpost() {
    var url = "http://localhost:8080/Game_admin/manage/auditPostAll";
    var setting = {
        url: url,
        type: "post",
        async: true,
        success: function (data) {
            var data = JSON.parse(data);
            var html = "<thead><tr>" +
                "<th>编号</th>" +
                "<th>贴名</th>" +
                "<th>内容</th>" +
                "<th>帖子类型</th>" +
                "<th>用户ID</th>" +
                "<th>状态</th>" +
                "</tr></thead>";
            var num = 0;
            for (var i = 0; i < data.length; i++) {
                num = i + 1;
                html += "<tr><td><input type='hidden' id='pId' value='" + data[i].pId + "'>" + num + "</td><td>" +
                    data[i].pName + "</td><td>" +
                    data[i].pContent + "</td><td>" +
                    data[i].categoryId + "</td><td>" +
                    data[i].userId + "</td><td>" +
                    "<span class='btn btn-primary' id='no' onclick='nopass()'><input type='hidden' id='num1' value='0' >不通过</span>&nbsp;&nbsp;" +
                    "<span class='btn btn-primary' id='pass' onclick='pass()'><input type='hidden' id='num2' value='1' >通过</span>" +
                    "</td></tr>"
            }
            $("#twobody").html(html);
        }
    };
    $.ajax(url, setting);
}

$(function () {
    //    待审核的贴子
    $("#deal").click(function () {
        showpost();
    });
});

//审核不通过的帖子
function nopass() {
    var num1 = $("#num1").val();
    var pId = $("#pId").val();
    var url = "http://localhost:8080/Game_admin/manage/auditPost";
    var setting = {
        url: url,
        type: "post",
        data: {
            num: num1,
            pid: pId
        },
        async: true,
        success: function (data) {
            if (data == 1) {
                showpost();
            } else {
                alert("操作失败")
            }
        }
    };
    $.ajax(url, setting);
}

//审核通过的帖子
function pass() {
    var num2 = $("#num2").val();
    var pId = $("#pId").val();
    var url = "http://localhost:8080/Game_admin/manage/auditPost";
    var setting = {
        url: url,
        type: "post",
        data: {
            num: num2,
            pid: pId
        },
        async: true,
        success: function (data) {
            if (data == 1) {
                showpost()
            } else {
                alert("操作失败")
            }
        }
    };
    $.ajax(url, setting);
}

//查看搜索帖子的封装函数
function lookpost() {
    var uid = $("#uid").val();
    var url = "http://localhost:8080/Game_admin/manage/getUserPostById";
    var setting = {
        url: url,
        type: "post",
        data: {uid: uid},
        async: true,
        success: function (data) {
            var data = JSON.parse(data);
            var html = "<thead><tr>" +
                "<th>编号</th>" +
                "<th>贴名</th>" +
                "<th>内容</th>" +
                "<th>帖子类型</th>" +
                "<th>状态</th>" +
                "</tr></thead>";
            var num = 0;
            var sum = 0;
            for (var i = 0; i < data.length; i++) {
                num = i + 1;
                html += "<tr><td><input type='hidden' id='pIds'name='pIds'>" + num + "</td><td>" +
                    data[i].pName + "</td><td>" +
                    data[i].pContent + "</td><td>" +
                    data[i].categoryId + "</td><td>" +
                    "<button class='btn btn-primary del' id='del' name='" + data[i].pId + "' onclick='delepost(this)'><input type='hidden' id='num3' value='0'>" +
                    "<input type='hidden' class='sunxun'value='" + sum + "'> 删除</button>" +
                    "</td></tr>";
                sum++;
            }
            $("#twobody").html(html);
        }
    };
    $.ajax(url, setting);
}

$(function () {
    //    搜索某个用户的帖子
    $("#seek").click(function () {
        lookpost();
    });
});

//    删除用户帖子
function delepost(e1) {
    var pids = e1.name;
    var num3 = $("#num3").val();
    var uid = $("#uid").val();
    var url = "http://localhost:8080/Game_admin/manage/deleteUserPost";
    var setting = {
        url: url,
        type: "post",
        data: {
            num: num3,
            pids: pids,
            userid: uid
        },
        async: true,
        success: function (data) {
            if (data == 1) {
                lookpost();
            } else {
                alert("操作失败！")
            }
        }
    };
    $.ajax(url, setting);
}

//第四模块
$(function () {
    $("#article").click(function () {
        $("#LB").css("display", "none");
        $("#one").css("display", "none");
        $("#two").css("display", "none");
        $("#four").css("display", "block");
    });
    //查看所有的论坛内容
    $("#lookarticle").click(function () {
        b();
    });
    //添加论坛内容
    $("#submit2").click(function () {
        //获取当前系统时间
        var date = new Date();
        var year = date.getFullYear();
        var month = date.getMonth();
        var day = date.getDay();
        var hours = date.getHours();
        var minutes = date.getMinutes();
        var seconds = date.getSeconds();
        var time = year + "-" + month + "-" + day + " " + hours + ":" + minutes + ":" + seconds;
        var formData = new FormData($("#form2")[0]);
        formData.append("createDate", time);
        /*
        var title=$("#title").val();
        var content=$("#content").val();
        var imgname=$("#img").val();
*/
        var url = "http://localhost:8080/Game_admin/manage/addArticle";
        var setting = {
            url: url,
            type: "post",
            data: formData,
            /*{title:title,
                content:content,
                img:imgname,
                createDate:time},*/
            dataType: "json",
            // 用于对参数进行序列化处理，这里必须设为false
            contentType: false,
            processData: false,
            async: true,
            success: function (data) {
                if (data == 1) {
                    $("#msg2").html("<font color='#6b8e23'>添加成功,请关闭！！！</font>");
                    b();
                } else {
                    $("#msg2").html("<font color='red'>添加失败,请检查！！！</font>");
                }
            }
        };
        $.ajax(url, setting);
    });
});

//删除论坛内容
function bb(e1) {
    var aid = e1.name;
    //var aid = document.getElementsByName("aId")[dele].value;
    var url = "http://localhost:8080/Game_admin/manage/deleteArticle";
    var setting = {
        url: url,
        data: {aId: aid},
        type: "post",
        async: true,
        success: function (data) {
            if (data == 1) {
                b();
            } else {
                alert("删除失败");
            }
        }
    };
    $.ajax(url, setting);
}

//查看所有的论坛内容
function b() {
    var url = "http://localhost:8080/Game_admin/manage/getListArticle";
    var setting = {
        url: url,
        type: "post",
        datatype: "json",
        async: true,
        success: function (data) {
            var data = JSON.parse(data);
            var html = "<thead><tr>" +
                "<th>序号</th>" +
                "<th>标题</th>" +
                "<th>内容</th>" +
                "<th>时间</th>" +
                "<th>操作</th>" +
                "</tr></thead>";
            var num = 0;
            var sum = 0;
            for (var i = 0; i < data.length; i++) {
                num = i + 1;
                html += "<tr><td> <input type='hidden' id='aId' name='aId' value='" + data[i].aId + "'>" + num + "</td><td>" +
                    data[i].title + "</td><td>" +
                    data[i].content + "</td><td>" +
                    data[i].createDate + "</td><td>" +
                    "<button class='btn btn-primary ' name='" + sum + "' onclick='edaritcle(this)'>编辑 </button>&nbsp;&nbsp;" +

                    "<button class='btn btn-primary dels' name='" + data[i].aId + "' onclick='bb(this)'><input type='hidden' id='dele' value='" + sum + "' '>删除</button>" +
                    " <div name='fade' style='display:none; '>" +
                    "<button class='btn btn-primary btn-xs'  data-toggle='modal' data-target='#myModal3' name='" + data[i].aId + "' onclick='updatetitle(this)'>修改标题</button> " +
                    "<button class='btn btn-primary btn-xs'  data-toggle='modal' data-target='#myModal4' name='" + data[i].aId + "' onclick='updatecontent(this)'>修改内容</button> " +
                    "<button class='btn btn-primary btn-xs'  data-toggle='modal' data-target='#myModal5' name='" + data[i].aId + "' onclick='updateimg(this)'>修改图片</button>" +
                    "</div>" +
                    "</td></tr>";
                sum++;
            }
            $("#fourbody").html(html);

        }
    };
    $.ajax(url, setting);
}

function edaritcle(e1) {
    var num = e1.name;
    var fade = document.getElementsByName("fade")[num];
    $(fade).toggle(1000);
}

//修改论坛标题
function updatetitle(e1) {
    var aid = e1.name;
    $("#submit3").click(function () {
        var titles = $("#title2").val();
        var url = "http://localhost:8080/Game_admin/manage/updateArticleTitle";
        var setting = {
            url: url,
            data: {
                title: titles,
                aid: aid
            },
            type: "post",
            async: true,
            success: function (data) {
                if (data == 1) {
                    $("#msg3").html("<font color='#6b8e23'>修改成功,请关闭！！！</font>");
                    b();
                } else {
                    $("#msg3").html("<font color='#6b8e23'>修改失败,请检查！！！</font>");

                }
            }
        };
        $.ajax(url, setting);
    });
}

//修改论坛内容
function updatecontent(e1) {
    var aid = e1.name;
    $("#submit4").click(function () {
        var contents = $("#content2").val();
        var url = "http://localhost:8080/Game_admin/manage/updateArticleContent";
        var setting = {
            url: url,
            data: {
                content: contents,
                aid: aid
            },
            type: "post",
            async: true,
            success: function (data) {
                if (data == 1) {
                    $("#msg4").html("<font color='#6b8e23'>修改成功,请关闭！！！</font>");
                    b();
                } else {
                    $("#msg").html("<font color='red'>修改失败,请检查！！！</font>");

                }
            }
        };
        $.ajax(url, setting)
    });
}

//修改论坛图片
function updateimg(e1) {
    var aid = e1.name;
    $("#submit5").click(function () {
        var formdata = new FormData($("#form5")[0]);
        formdata.append("aid", aid);
        console.log(formdata);
        if (formdata == "") {
            $("#msg5").html("<font color='red'>失败,请检查！！！</font>");
            return;
        }
        var url = "http://localhost:8080/Game_admin/manage/updateArticleImg";
        var setting = {
            url: url,
            data: formdata,
            type: "POST",
            processData: false,
            contentType: false,
            async: true,
            success: function (data) {
                if (data == 1) {
                    $("#msg5").html("<font color='#6b8e23'>修改成功,请关闭！！！</font>");
                    b();
                } else {
                    $("#msg5").html("<font color='red'>失败成功,请检查！！！</font>");
                }
            }
        };
        $.ajax(url, setting)
    });
}

//搜索论坛
$(function () {
    $("#hunt1").click(function () {
        var txt = $("#hunt").val();
        if (txt == "") {
            return;
        }
        var url = "http://localhost:8080/Game_admin/manage/getListByContent";
        var setting = {
            url: url,
            type: "post",
            data: {
                txt: txt
            },
            async: true,
            success: function (data) {
                if (null !== data) {
                    var data = JSON.parse(data);
                    var html = "<thead><tr>" +
                        "<th>序号</th>" +
                        "<th>标题</th>" +
                        "<th>内容</th>" +
                        "<th>时间</th>" +
                        "<th>操作</th>" +
                        "</tr></thead>";
                    var num = 0;
                    var sum = 0;
                    for (var i = 0; i < data.length; i++) {
                        num = i + 1;
                        html += "<tr><td> <input type='hidden' id='aId' name='aId' value='" + data[i].aId + "'>" + num + "</td><td>" +
                            data[i].title + "</td><td>" +
                            data[i].content + "</td><td>" +
                            data[i].createDate + "</td><td>" +
                            "<button class='btn btn-primary ' name='" + sum + "' onclick='edaritcle(this)'>编辑 </button>&nbsp;&nbsp;" +

                            "<button class='btn btn-primary dels' name='" + data[i].aId + "' onclick='bb(this)'><input type='hidden' id='dele' value='" + sum + "' '>删除</button>" +
                            " <div name='fade' style='display:none; '>" +
                            "<button class='btn btn-primary btn-xs'  data-toggle='modal' data-target='#myModal3' name='" + data[i].aId + "' onclick='updatetitle(this)'>修改标题</button> " +
                            "<button class='btn btn-primary btn-xs'  data-toggle='modal' data-target='#myModal4' name='" + data[i].aId + "' onclick='updatecontent(this)'>修改内容</button> " +
                            "<button class='btn btn-primary btn-xs'  data-toggle='modal' data-target='#myModal5' name='" + data[i].aId + "' onclick='updateimg(this)'>修改图片</button>" +
                            "</div>" +
                            "</td></tr>";
                        sum++;
                    }
                    $("#fourbody").html(html);
                } else {

                }
            }

        };
        $.ajax(url, setting);
    });
})
