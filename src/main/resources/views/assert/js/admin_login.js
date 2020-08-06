$(function () {
    $("#form").bootstrapValidator({
        message: "登录失败",
        feedbackIcons: {
            //校验通过的输入框状态
            valid: 'glyphicon glyphicon-ok',
            //校验通过失败的输入框状态
            invalid: 'glyphicon glyphicon-remove',
            //校验通过中的状态
            validating: 'glyphicon glyphicon-refresh'
        },
        //设置需要进行校验的属性
        fields: {
            //根据input 标签的name属性值进行匹配
            adminName: {
                message: "用户名不符合规范",
                validators: {
                    notEmpty: {
                        message: "用户名不能为空"
                    }
                }
            },
            adminPassword: {
                message: "密码不规范",
                validators: {
                    notEmpty: {
                        message: "密码不能为空"
                    }
                }
            }
        }
    });

    $("#adminName").blur(function () {
        var adminName = $("#adminName").val();
        var url = "http://localhost:8080/Game_admin/manageLogin/checkedName";
        var setting = {
            url: url,
            type: "get",
            data: {
                name: adminName
            },
            async: true,
            success: function (data) {
                if (data == 1) {
                    $("span:eq(0)").html("<font color='green'>管理员已验证成功</font>");
                } else {
                    $("span:eq(0)").html("<font color='red'>管理员账号不存在</font>");
                }
            },
            //请求失败
            error: function (status) {
            }
        };
        $.ajax(url, setting);
    });
    $("#btn").click(function () {
        var adminName = $("#adminName").val();
        var adminPassword = $("#adminPassword").val();
        var data = {
            name: adminName,
            password: adminPassword
        }
        //第一个参数为请求地址
        //第二个参数为请求参数
        //第三个参数为回调函数，data为响应数据
        $.get("http://localhost:8080/Game_admin/manageLogin/login", data, function (data) {
            if (data == 1) {
                location.href = "admin_index.html";
            } else {
                $("span:eq(1)").html("<font color='red'>密码错误！！！</font>");
            }
        });
    })
})