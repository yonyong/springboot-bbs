$("#addArticleButton").on("click",function () {
    var addArticleTitle=$("#addArticleTitle").val();
    var addArticleContent=$("#addArticleContent").val();
    var data={
        addArticleTitle:addArticleTitle,
        addArticleContent:addArticleContent
    };
    if (addArticleTitle==""){
        alert("标题不能为空！");
        return false;
    }

    if (addArticleContent==""){
        alert("正文不能为空！");
        return false;
    }
    $.ajax({
        type:"POST",
        url : "addArticle",
        data : data,
        dataType : 'json',
        success : function(msg) {
            if ("1"==msg.errorCode) {
                alert("文章发布成功！");
                window.location.href("/toarticle");
                return false;
            }else if ("0"==msg.errorCode) {
                alert("您还尚未登陆，点击会话按钮进行登陆！")
                return false;
            } else{
                alert("您没有权限！");
                return false;
            }
        }
    });
});

$("#editArticleButton").on("click",function () {
    var editArticleTitle=$("#editArticleTitle").val();
    var editArticleContent=$("#editArticleContent").val();
    var articleId=$("#id").val();
    if (editArticleTitle==""){
        alert("标题不能为空！");
        return false;
    }

    if (editArticleContent==""){
        alert("正文不能为空！");
        return false;
    }
    var data={
        articleId:articleId,
        editArticleTitle:editArticleTitle,
        editArticleContent:editArticleContent
    };

    $.ajax({
        type:"POST",
        url : "editArticle",
        data : data,
        dataType : 'json',
        success : function(msg) {
            if ("1"==msg.errorCode) {
                alert("文章修改成功！");
                window.location.reload();
                return false;
            }else if ("0"==msg.errorCode) {
                alert("您还尚未登陆，点击会话按钮进行登陆！")
                return false;
            } else{
                alert("您没有权限！");
                return false;
            }
        }
    });
});

$("#deleteArticleButton").on("click",function () {
    var articleId=$("#id").val();
    var data={
        articleId:articleId
    };

    $.ajax({
        type:"POST",
        url : "deleteArticle",
        data : data,
        dataType : 'json',
        success : function(msg) {
            if ("1"==msg.errorCode) {
                alert("文章删除成功！");
                window.location.href("/toarticle");
                return false;
            }else if ("0"==msg.errorCode) {
                alert("您还尚未登陆，点击会话按钮进行登陆！")
                return false;
            } else{
                alert("您没有权限！");
                return false;
            }
        }
    });
});


$("#replyButton").on("click",function () {
    var reply_content=$("#reply_content").val();
    var articleId=$("#id").val();
    if (reply_content===""){
        alert("评论不能为空！");
        return false;
    }
    var data={
        articleId:articleId,
        reply_content:reply_content
    };

    $.ajax({
        type:"POST",
        url : "addReply",
        data : data,
        dataType : 'json',
        success : function(msg) {
            if ("1"==msg.errorCode) {
                alert("评论添加成功！");
                window.location.reload();
                return false;
            }else if ("0"==msg.errorCode) {
                alert("您还尚未登陆，点击会话按钮进行登陆！")
                return false;
            } else{
                alert("您没有权限！");
                return false;
            }
        }
    });
});