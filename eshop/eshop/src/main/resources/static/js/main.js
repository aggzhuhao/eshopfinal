/**
 * Created by stack on 2016/4/25.
 */
require(['js/functions.js'], function ($){
    // some code here
});

/*通用组件*/
$(function(){
    //顶部菜单
    $(".top-group").hover(function(){
        $(this).children(".user-menu").show();
    },function(){
        $(this).children(".user-menu").hide();
    });
    //显示搜索框
    $('.search').each(function(){
        $(this).click(function() {
           $('.search-frame').show('slow');
        });
    });
    //关闭搜索框
    $('.search-close').each(function(){
        $(this).click(function() {
            $('.search-frame').hide('slow');
        });
    });

    //判断是否已经需要置顶
    $(window).scroll(function(){
        //var scrollTop = document.body.scrollTop || document.documentElement.scrollTop;
        if($(window).scrollTop() >= 100){
            $('.sticky').show('slow');
        }else{
            $('.sticky').hide('slow');
        }
    });
    //一键返回顶部
    $('.sticky a').click(function(){
        $('html body').animate({scrollTop: '0px'}, 800);
    })

});