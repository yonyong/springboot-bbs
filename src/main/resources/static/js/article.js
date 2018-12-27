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
            } else if ("2"==msg.errorCode){
                alert("您没有发布文章的权限！");
                return false;
            }
        }
    });
});

$("#editArticleButton").on("click",function () {
    var editArticleTitle=$("#editArticleTitle").val();
    var editArticleContent=$("#editArticleContent").val();
    var articleId=$("#id").val();
    var editArticleId =$("#editArticleId").val();
    if (editArticleTitle==""){
        alert("标题不能为空！");
        return false;
    }

    if (editArticleContent==""){
        alert("正文不能为空！");
        return false;
    }
    var data={
        editArticleId:editArticleId,
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
            } else  if ("2"==msg.errorCode){
                alert("很抱歉，您没有编辑文章的权限！");
                return false;
            }
            else  if ("3"==msg.errorCode){
                alert("很抱歉，您没有编辑自己文章的权限！");
                return false;
            }
            else  if ("4"==msg.errorCode){
                alert("很抱歉，您没有编辑他人文章的权限！");
                return false;
            }
        }
    });
});

$("#deleteArticleButton").on("click",function () {
    var articleId=$("#id").val();
    var deleteArticleId=$("#deleteArticleId").val();
    var data={
        deleteArticleId:deleteArticleId,
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
            } else if ("2"==msg.errorCode){
                alert ("提示：您没有删除文章的权限！");
                return false;
            }
            else if ("3"==msg.errorCode){
                alert ("提示：您没有删除自己文章的权限！");
                return false;
            }
            else if ("4"==msg.errorCode){
                alert ("提示：您没有删除他人文章的权限！");
                return false;
            }
        }
    });
});


$("#replyButton").on("click",function () {
    var reply_content=$("#reply_content").val();
    var articleId=$("#id").val();
    if (reply_content==""){
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
            } else if ("2"==msg.errorCode){
                alert("您没有评论文章的权限！");
                return false;
            }
        }
    });
});