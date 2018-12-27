$("#editRole0").on("click",function () {
    var editRoleUserId=$("#editRoleUserId0").val();
    var editRoleSeclection0=$("#editRoleSeclection0").val();
    var data={
        editRoleUserId:editRoleUserId,
        editRoleSeclection0:editRoleSeclection0
    };
    if (editRoleSeclection0==""){
        alert("请选择一个角色！");
        return false;
    }
    $.ajax({
        type:"POST",
        url : "editRole",
        data : data,
        dataType : 'json',
        success : function(msg) {
            if ("0"==msg.errorCode) {
                alert("只能更改权限等级比自己低的人！");
                return false;
            }else if ("1"==msg.errorCode) {
                alert("不可将对象用户等级设置为比您高或与您持平（超级管理员等级>管理员等级>普通用户等级）！")
                return false;
            } else if ("2"==msg.errorCode){
                alert("修改成功！");
                window.location.reload();
                return false;
            }
        }
    });
});
$("#editRole1").on("click",function () {
    var editRoleUserId=$("#editRoleUserId1").val();
    var editRoleSeclection0=$("#editRoleSeclection1").val();
    var data={
        editRoleUserId:editRoleUserId,
        editRoleSeclection0:editRoleSeclection0
    };
    if (editRoleSeclection0==""){
        alert("请选择一个角色！");
        return false;
    }
    $.ajax({
        type:"POST",
        url : "editRole",
        data : data,
        dataType : 'json',
        success : function(msg) {
            if ("0"==msg.errorCode) {
                alert("只能更改权限等级比自己低的人！");
                return false;
            }else if ("1"==msg.errorCode) {
                alert("不可将对象用户等级设置为比您高或与您持平（超级管理员等级>管理员等级>普通用户等级）！")
                return false;
            } else if ("2"==msg.errorCode){
                alert("修改成功！");
                window.location.reload();
                return false;
            }
        }
    });
});
$("#editRole2").on("click",function () {
    var editRoleUserId=$("#editRoleUserId2").val();
    var editRoleSeclection0=$("#editRoleSeclection2").val();
    var data={
        editRoleUserId:editRoleUserId,
        editRoleSeclection0:editRoleSeclection0
    };
    if (editRoleSeclection0==""){
        alert("请选择一个角色！");
        return false;
    }
    $.ajax({
        type:"POST",
        url : "editRole",
        data : data,
        dataType : 'json',
        success : function(msg) {
            if ("0"==msg.errorCode) {
                alert("只能更改权限等级比自己低的人！");
                return false;
            }else if ("1"==msg.errorCode) {
                alert("不可将对象用户等级设置为比您高或与您持平（超级管理员等级>管理员等级>普通用户等级）！")
                return false;
            } else if ("2"==msg.errorCode){
                alert("修改成功！");
                window.location.reload();
                return false;
            }
        }
    });
});


// 角色管理

// 添加角色
$("#addRoleButton").on("click",function () {
   var addrolename=$("#addrolename").val();
   var addRoleLevel=$("#addRoleLevel").val();

    // 指定请求的数据格式为json，实际上传的是json字符串
   var addRoleSlection=JSON.stringify($("#addRoleSlection").val());
   alert(addRoleSlection);
    var data={
        addrolename:addrolename,
        addRoleLevel:addRoleLevel,
        addRoleSlection:addRoleSlection
    };
    if (addrolename==""){
        alert("请输入角色名");
        return false;
    }
    if (addRoleLevel==""){
        alert("请选择角色等级");
        return false;
    }
    if (addRoleSlection==""){
        alert("请选择角色权限");
        return false;
    }
    $.ajax({
        type:"POST",
        url : "addRole",
        data : data,
        dataType : 'json',
        success : function(msg) {
            if ("0"==msg.errorCode) {
                alert("您不具备超级管理员权限，不能添加管理员角色！");
                return false;
            }else if ("1"==msg.errorCode) {
                alert("角色添加成功");
                window.location.reload();
                return false;
            }
        }
    });

});

// 编辑角色权限
$("#ediPerRoleButton0").on("click",function () {
    var ediPerRoleId=$("#ediPerRoleId0").val();
    var ediPerRoleLevel=$("#ediPerRoleLevel0").val();
    var ediPerRoleName=$("#ediPerRoleName0").val();
    var ediPerRoleTargetLevel=$("#ediPerRoleTargetLevel0").val();
    var ediPerRolePer=JSON.stringify($("#ediPerRolePer0").val());

    console.log("0000");

    if (ediPerRoleName==""){
        alert("角色名不能为空！");
        return false;
    }
    if (ediPerRoleTargetLevel==""){
        alert("角色等级不能为空！");
        return false;
    }

    if (ediPerRolePer==""){
        alert("权限不能为空！");
        return false;
    }
    var data={
        ediPerRoleId:ediPerRoleId,
        ediPerRoleLevel:ediPerRoleLevel,
        ediPerRoleName:ediPerRoleName,
        ediPerRoleTargetLevel:ediPerRoleTargetLevel,
        ediPerRolePer:ediPerRolePer
    };
    $.ajax({
        type:"POST",
        url : "editRolePer",
        data : data,
        dataType : 'json',
        success : function(msg) {
            if ("0"==msg.errorCode) {
                alert("不能修改与你同角色等级的用户！");
                return false;
            }else if("1"==msg.errorCode) {
                alert("不能修改角色等级比你高的的用户！");
                return false;
            }
            else if("2"==msg.errorCode) {
                alert("不能将目标角色设置为与你角色等级相同！");
                return false;
            }
            else if("3"==msg.errorCode) {
                alert("不能将目标角色设置为比你角色等级还高！");
                return false;
            }
            else if ("4"==msg.errorCode) {
                alert("编辑成功！点击确认返回页面！");
                window.location.reload();
                return false;
            }
        }
    });

});
$("#ediPerRoleButton1").on("click",function () {
    var ediPerRoleId=$("#ediPerRoleId1").val();
    var ediPerRoleLevel=$("#ediPerRoleLevel1").val();
    var ediPerRoleName=$("#ediPerRoleName1").val();
    var ediPerRoleTargetLevel=$("#ediPerRoleTargetLevel1").val();
    var ediPerRolePer=JSON.stringify($("#ediPerRolePer1").val());

    var t=$("#ediPerRoleRole1").val();

    console.log(t);


    if (ediPerRoleName==""){
        alert("角色名不能为空！");
        return false;
    }
    if (ediPerRoleTargetLevel==""){
        alert("角色等级不能为空！");
        return false;
    }

    if (ediPerRolePer==""){
        alert("权限不能为空！");
        return false;
    }
    var data={
        ediPerRoleId:ediPerRoleId,
        ediPerRoleLevel:ediPerRoleLevel,
        ediPerRoleName:ediPerRoleName,
        ediPerRoleTargetLevel:ediPerRoleTargetLevel,
        ediPerRolePer:ediPerRolePer
    };
    $.ajax({
        type:"POST",
        url : "editRolePer",
        data : data,
        dataType : 'json',
        success : function(msg) {
            if ("0"==msg.errorCode) {
                alert("不能修改与你同角色等级的用户！");
                return false;
            }else if("1"==msg.errorCode) {
                alert("不能修改角色等级比你高的的用户！");
                return false;
            }
            else if("2"==msg.errorCode) {
                alert("不能将目标角色设置为与你角色等级相同！");
                return false;
            }
            else if("3"==msg.errorCode) {
                alert("不能将目标角色设置为比你角色等级还高！");
                return false;
            }
            else if ("4"==msg.errorCode) {
                alert("编辑成功！点击确认返回页面！");
                window.location.reload();
                return false;
            }
        }
    });

});

$("#ediPerRoleButton2").on("click",function () {
    var ediPerRoleId=$("#ediPerRoleId2").val();
    var ediPerRoleLevel=$("#ediPerRoleLevel2").val();
    var ediPerRoleName=$("#ediPerRoleName2").val();
    var ediPerRoleTargetLevel=$("#ediPerRoleTargetLevel2").val();
    var ediPerRolePer=JSON.stringify($("#ediPerRolePer2").val());

    if (ediPerRoleName==""){
        alert("角色名不能为空！");
        return false;
    }
    if (ediPerRoleTargetLevel==""){
        alert("角色等级不能为空！");
        return false;
    }

    if (ediPerRolePer==""){
        alert("权限不能为空！");
        return false;
    }
    var data={
        ediPerRoleId:ediPerRoleId,
        ediPerRoleLevel:ediPerRoleLevel,
        ediPerRoleName:ediPerRoleName,
        ediPerRoleTargetLevel:ediPerRoleTargetLevel,
        ediPerRolePer:ediPerRolePer
    };
    $.ajax({
        type:"POST",
        url : "editRolePer",
        data : data,
        dataType : 'json',
        success : function(msg) {
            if ("0"==msg.errorCode) {
                alert("不能修改与你同角色等级的用户！");
                return false;
            }else if("1"==msg.errorCode) {
                alert("不能修改角色等级比你高的的用户！");
                return false;
            }
            else if("2"==msg.errorCode) {
                alert("不能将目标角色设置为与你角色等级相同！");
                return false;
            }
            else if("3"==msg.errorCode) {
                alert("不能将目标角色设置为比你角色等级还高！");
                return false;
            }
            else if ("4"==msg.errorCode) {
                alert("编辑成功！点击确认返回页面！");
                window.location.reload();
                return false;
            }
        }
    });

});

//删除角色

$("#delPerRoleButton0").on("click",function () {
    alert("将要执行删除操作,请谨慎执行!");
    var ediPerRoleId=$("#ediPerRoleId0").val();
    var ediPerRoleLevel=$("#ediPerRoleLevel0").val();
    var data={
        ediPerRoleId:ediPerRoleId,
        ediPerRoleLevel:ediPerRoleLevel
    };
    $.ajax({
        type:"POST",
        url : "deleteRolePer",
        data : data,
        dataType : 'json',
        success : function(msg) {
            if ("0"==msg.errorCode) {
                alert("基础角色不可删除！");
                return false;
            }else if("1"==msg.errorCode) {
                alert("不可以删除角色等级与你相同的角色！");
                return false;
            }
            else if("2"==msg.errorCode) {
                alert("不可以删除角色等级比你高的角色！");
                return false;
            }
            else if ("3"==msg.errorCode) {
                alert("角色已删除！点击确认返回页面！");
                window.location.reload();
                return false;
            }
        }
    });

});

$("#delPerRoleButton1").on("click",function () {
    alert("将要执行删除操作,请谨慎执行!");
    var ediPerRoleId=$("#ediPerRoleId1").val();
    var ediPerRoleLevel=$("#ediPerRoleLevel1").val();
    var data={
        ediPerRoleId:ediPerRoleId,
        ediPerRoleLevel:ediPerRoleLevel
    };
    $.ajax({
        type:"POST",
        url : "deleteRolePer",
        data : data,
        dataType : 'json',
        success : function(msg) {
            if ("0"==msg.errorCode) {
                alert("基础角色不可删除！");
                return false;
            }else if("1"==msg.errorCode) {
                alert("不可以删除角色等级与你相同的角色！");
                return false;
            }
            else if("2"==msg.errorCode) {
                alert("不可以删除角色等级比你高的角色！");
                return false;
            }
            else if ("3"==msg.errorCode) {
                alert("角色已删除！点击确认返回页面！");
                window.location.reload();
                return false;
            }
        }
    });

});

$("#delPerRoleButton2").on("click",function () {
    alert("将要执行删除操作,请谨慎执行!");
    var ediPerRoleId=$("#ediPerRoleId2").val();
    var ediPerRoleLevel=$("#ediPerRoleLevel2").val();
    var data={
        ediPerRoleId:ediPerRoleId,
        ediPerRoleLevel:ediPerRoleLevel
    };
    $.ajax({
        type:"POST",
        url : "deleteRolePer",
        data : data,
        dataType : 'json',
        success : function(msg) {
            if ("0"==msg.errorCode) {
                alert("基础角色不可删除！");
                return false;
            }else if("1"==msg.errorCode) {
                alert("不可以删除角色等级与你相同的角色！");
                return false;
            }
            else if("2"==msg.errorCode) {
                alert("不可以删除角色等级比你高的角色！");
                return false;
            }
            else if ("3"==msg.errorCode) {
                alert("角色已删除！点击确认返回页面！");
                window.location.reload();
                return false;
            }
        }
    });

});