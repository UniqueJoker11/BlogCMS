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
//获取用户的角色信息
function fetchUserRole(user_id){
    var params=new Object();
    params.user_id=user_id;
    $.post("./fetchUserRoleTree.action",params,function(data){
        if(data.isSuccess){
            var htmlContent="",roleList=data.dataList;
            for(var i=0;i<roleList.length;i++){
                    htmlContent+="<tr data-tt-id=\""+roleList[i].roleId+"\"><td>"+roleList[i].name+"</td><td>"+roleList[i].roleDescription+"</td></tr>"
                if(roleList[i].childrenList!=null){
                    for(var j=0;j<roleList[i].childrenList.length;j++){
                        htmlContent+=sortRoleInfo(roleList[i].childrenList[j]);
                    }
                }
            }
            $("#user-role-body").html(htmlContent);
            $("#user-role-table").treetable({ expandable: true});
            $("#user-role-dialog").modal("toggle");
        }else{
            $("<div><p>对不起，当前用户没有对应的角色</p></div>").alert("close");
        }
    });
}

//递归解析出相应的页面片段
function sortRoleInfo(roleList){
    var htmlContent="";
    if(roleList.childrenList!=null){
        htmlContent+="<tr data-tt-id=\""+roleList.roleId+"\" data-tt-parent-id=\""+roleList.parentRoleId+"\"><td>"+roleList.name+"</td><td>"+roleList.roleDescription+"</td></tr>";
        for(var i=0;i<roleList.childrenList.length;i++){
            htmlContent+=sortRoleInfo(roleList.childrenList[i]);
        }
        return htmlContent;
    }else{
      return "<tr data-tt-id=\""+roleList.roleId+"\" data-tt-parent-id=\""+roleList.parentRoleId+"\"><td>"+roleList.name+"</td><td>"+roleList.roleDescription+"</td></tr>";
    }
}
//编辑用户的角色
function editUserRoleTree(){


}
//获取用户的权限信息
function fetchUserAuthority(user_id){
    var params=new Object();
    params.user_id=user_id;
    $.post("./fetchUserAuthority.action",params,function(data){
       if(data.isSuccess){
           var htmlContent="",roleList=data.dataList;
           for(var i=0;i<roleList.length;i++){
               htmlContent+="<tr><td>roleList[i].role_id</td><td>roleList[i].role_name</td><td>roleList[i].role_description</td></tr>"
           }
           $("#user-role-table").html(htmlContent);
           $("#user-role-dialog").modal("toggle");
       }else{
       $("<div><p>对不起，当前用户没有对应的角色</p></div>").alert('close')
       }
    });
}
//编辑用户的信息
function editUserInfo(user_id){
    var params=new Object();
    params.user_id=user_id;
    $.post("./fetchUserRole.action",params,function(data){

    });
}
//删除用户的信息
function deleteUserInfo(user_id){
    var params=new Object();
    params.user_id=user_id;
    $.post("./fetchUserRole.action",params,function(data){

    });
}
