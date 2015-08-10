/**
 * Created by DELL on 2015/8/6.
 */
$("#userinfo_table").dataTable({
    language: {
        "sProcessing": "处理中...",
        "sLengthMenu": "显示 _MENU_ 项结果",
        "sZeroRecords": "没有匹配结果",
        "sInfo": "显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项",
        "sInfoEmpty": "显示第 0 至 0 项结果，共 0 项",
        "sInfoFiltered": "(由 _MAX_ 项结果过滤)",
        "sInfoPostFix": "",
        "sSearch": "搜索:",
        "sEmptyTable": "表中数据为空",
        "sLoadingRecords": "载入中...",
        "sInfoThousands": ",",
        "oPaginate": {
            "sFirst": "首页",
            "sPrevious": "上页",
            "sNext": "下页",
            "sLast": "末页"
        },
        "oAria": {
            "sSortAscending": ": 以升序排列此列",
            "sSortDescending": ": 以降序排列此列"
        }
    },
    "sPaginationType": "full_numbers"
});
//绑定用户的信息修改
$("#user-info-detail-content").find("a").editable();
//初始化用户的人员列表
initUserList();
function initUserList() {
    $.post("./fetchuserlist.action", function (data) {
        if (data != null && data.length > 0) {
            var list_content = "";
            for (var i = 0; i < data.length; i++) {
                list_content += "<li data-id=\"" + data[i].id + "\" class=\"list-group-item\"><span class='fa fa-user'></span>&nbsp;&nbsp;" + data[i].name + "</li>"
            }
            $("#user-info-list").html(list_content);
            initUserListClickBinding();
        }
    });
}
function initUserListClickBinding() {
    $("#user-info-list li").bind("click", function (e) {
        $(this).addClass("active").siblings().removeClass("active");
        //显示用户的具体信息
        var params = new Object();
        params.user_id = $(this).attr("data-id");
        $.post("./fetchUserDetailInfo.action", params, function (data) {
            if (data != null) {
                $("#user-info-detail").attr("data-id", data.user_id);
                $("#user-info-username").html(data.user_name);
                $("#user-info-callname").html(data.user_callname);
                $("#user-info-email").html(data.user_email);
                $("#user-info-phone").html(data.user_phone);
                $("#user-info-logintime").html(data.user_logintime);
                $("#user-info-createtime").html(data.user_createtime);
                $("#user-info-detail").slideDown();
            } else {
                $("<p>暂无用户资料</p>").alert();
            }
        });
        //显示用户的角色和权限信息
        $.post("./fetchUserRoleTree.action", params, function (data) {
            if (data.isSuccess) {
                //初始化用户的角色树
                var setting = {
                    view: {
                        fontCss: {size: "1.5em"},
                        txtSelectedEnable: true,
                        showLine: true

                    },
                    callback: {
                        onClick: function (event, treeId, treeNode, clickFlag) {
                            if (clickFlag) {
                                alert(treeId);
                            }
                        }
                    },
                    check: {
                        enable: true
                    },
                    data: {
                        simpleData: {
                            enable: true,
                            idKey: "roleId",
                            pIdKey: "parentRoleId",
                            rootPId: 0
                        },
                        key: {
                            children: "childrenList"
                        }
                    }
                }
                var treeObj=$.fn.zTree.init($("#user-info-role-tree"), setting, data.dataList);
                treeObj.expandAll(true);
            }
        });
        //初始化用户的权限列表
        $.post("./fetchUserAuthorityList.action", params, function (data) {
            if (data != null && data.length > 0) {
                var authority_content="";
                for (var i = 0; i < data.length; i++) {
                    authority_content+="<tr><td>"+data[i].authority_name+"</td><td>"+data[i].authoruty_description+"</td></tr>";
                }
                $("#user-info-authority-table >tbody").html(authority_content);
            }else{
                $("#user-info-authority-table >tbody").html("");
            }
        });
        $("#user-info-authority").slideDown();
    }).eq(0).trigger("click")
}
//获取用户的角色信息
function fetchUserRole(user_id) {
    var params = new Object();
    params.user_id = user_id;
    $.post("./fetchUserRole.action", params, function (data) {
        if (data.isSuccess) {
            var userRoleList = data.dataList;
            var role_content = "";
            for (var i = 0; i < userRoleList.length; i++) {
                role_content += "<tr><td>" + userRoleList[i].role_name + "</td><td>" + userRoleList[i].role_description + "</td></tr>";
            }
            $("#user-role-body").html(role_content);
            $("#user-role-dialog").modal("toggle");
        } else {
            $("<div><p>对不起，当前用户没有对应的角色</p></div>").alert("close");
        }
    });
}

//递归解析出相应的页面片段
function sortRoleInfo(roleList) {
    var htmlContent = "";
    if (roleList.childrenList != null) {
        htmlContent += "<tr data-tt-id=\"" + roleList.roleId + "\" data-tt-parent-id=\"" + roleList.parentRoleId + "\"><td>" + roleList.name + "</td><td>" + roleList.roleDescription + "</td></tr>";
        for (var i = 0; i < roleList.childrenList.length; i++) {
            htmlContent += sortRoleInfo(roleList.childrenList[i]);
        }
        return htmlContent;
    } else {
        return "<tr data-tt-id=\"" + roleList.roleId + "\" data-tt-parent-id=\"" + roleList.parentRoleId + "\"><td>" + roleList.name + "</td><td>" + roleList.roleDescription + "</td></tr>";
    }
}
//编辑用户的角色
function editUserRoleTree() {


}
//获取用户的权限信息
function fetchUserAuthority(user_id) {
    var params = new Object();
    params.user_id = user_id;
    $.post("./fetchUserAuthority.action", params, function (data) {
        if (data.isSuccess) {
            var htmlContent = "", roleList = data.dataList;
            for (var i = 0; i < roleList.length; i++) {
                htmlContent += "<tr><td>roleList[i].role_id</td><td>roleList[i].role_name</td><td>roleList[i].role_description</td></tr>"
            }
            $("#user-role-table").html(htmlContent);
            $("#user-role-dialog").modal("toggle");
        } else {
            $("<div><p>对不起，当前用户没有对应的角色</p></div>").alert('close')
        }
    });
}
//编辑用户的信息
function editUserInfo(user_id) {
    var params = new Object();
    params.user_id = user_id;
    $.post("./fetchUserRole.action", params, function (data) {

    });
}
//删除用户的信息
function deleteUserInfo(user_id) {
    var params = new Object();
    params.user_id = user_id;
    $.post("./fetchUserRole.action", params, function (data) {

    });
}