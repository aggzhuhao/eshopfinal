/**
 * Created by stack on 2015/7/8.
 */

/**
 * 左右晃动函数
 * element 对象
 */
function shake(element)
{
    element.css('margin-left',element.css('margin-left'));

    for (var i = 1; i <= 3; i++)
    {
        element.animate({ 'left': (37- 10 * i) }, 20);
        element.animate({ 'left': (2 * (37 - 10 * i)) }, 20);
    }
}
