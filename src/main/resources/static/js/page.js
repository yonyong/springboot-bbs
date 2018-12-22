var page=1;
// 首页
$("#first").on("onclick",function () {
    page=1;
    var data = {
        page:page
    };
    $.ajax({
        type : "POST",
        url : "/page",
        data : data,
        dataType : 'json',
        success : function(msg) {
            if ("1"==msg.errorCode) {
                console.log("submit................success");


                window.location.href = "/tologin";
                window.setTimeout("reloadyemian();",3000);
                return false;
            } else if("0"==msg.errorCode) {
                alert("该会员已存在，立即登陆!");
                console.log("注册失败，请重试!");
                $("#myform")[0].reset();
                return false;
            }
            else if("2"==msg.errorCode){
                alert("验证码错误！");
                console.log("验证码错误！");
                $("#telDiv").attr("style","display:none;");
                $("#imageDiv").attr("style","display:block;");
                $("#password").attr("style","display:none;");
                $("#submit").attr("style","display:none;");
                reloadCode();
                $("#volidate").val("");
                $("#password").val("");
                return false;
            }
        },
        error : function(XMLHttpRequest, textStatus, errorThrown) {
            // 状态码
            console.log(XMLHttpRequest.status);
            // 状态
            console.log(XMLHttpRequest.readyState);
            // 错误信息
            console.log(textStatus);
        }

    });
});

// 上一页
$("#pre").on("onclick",function () {
    page=page-1;
    var data = {
        page:page
    };
    $.ajax({
        type : "POST",
        url : "/page",
        data : data,
        dataType : 'json',
        success : function(msg) {
            if ("1"==msg.errorCode) {
                console.log("submit................success");


                window.location.href = "/tologin";
                window.setTimeout("reloadyemian();",3000);
                return false;
            } else if("0"==msg.errorCode) {
                alert("该会员已存在，立即登陆!");
                console.log("注册失败，请重试!");
                $("#myform")[0].reset();
                return false;
            }
            else if("2"==msg.errorCode){
                alert("验证码错误！");
                console.log("验证码错误！");
                $("#telDiv").attr("style","display:none;");
                $("#imageDiv").attr("style","display:block;");
                $("#password").attr("style","display:none;");
                $("#submit").attr("style","display:none;");
                reloadCode();
                $("#volidate").val("");
                $("#password").val("");
                return false;
            }
        },
        error : function(XMLHttpRequest, textStatus, errorThrown) {
            // 状态码
            console.log(XMLHttpRequest.status);
            // 状态
            console.log(XMLHttpRequest.readyState);
            // 错误信息
            console.log(textStatus);
        }

    });
});
//下一页
$("#next").on("onclick",function () {
    page=page+1;
    var data = {
        page:page
    };
    $.ajax({
        type : "POST",
        url : "/page",
        data : data,
        dataType : 'json',
        success : function(msg) {
            if ("1"==msg.errorCode) {
                console.log("submit................success");


                window.location.href = "/tologin";
                window.setTimeout("reloadyemian();",3000);
                return false;
            } else if("0"==msg.errorCode) {
                alert("该会员已存在，立即登陆!");
                console.log("注册失败，请重试!");
                $("#myform")[0].reset();
                return false;
            }
            else if("2"==msg.errorCode){
                alert("验证码错误！");
                console.log("验证码错误！");
                $("#telDiv").attr("style","display:none;");
                $("#imageDiv").attr("style","display:block;");
                $("#password").attr("style","display:none;");
                $("#submit").attr("style","display:none;");
                reloadCode();
                $("#volidate").val("");
                $("#password").val("");
                return false;
            }
        },
        error : function(XMLHttpRequest, textStatus, errorThrown) {
            // 状态码
            console.log(XMLHttpRequest.status);
            // 状态
            console.log(XMLHttpRequest.readyState);
            // 错误信息
            console.log(textStatus);
        }

    });
});