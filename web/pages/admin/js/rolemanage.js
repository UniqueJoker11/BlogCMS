/**
 * Created by DELL on 2015/8/10.
 */
$(function(){
    //初始化用户的角色信息
    function initRoleInfo(){
        //
        $.post("/fetchAllRoleList.action",function(data){
            if(data!=null&&data.length>0){
                var setting={

                    },
            }
        });
    }
});