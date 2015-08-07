/**
 * Created by DELL on 2015/7/15.
 */
$(function(){
    $("#colin-manage-nav").find(".children").find("a").on("click",function(e){
        $("#colin-manage-content").load($(this).attr("href"));
        e.preventDefault();
        return false;
    });
});
