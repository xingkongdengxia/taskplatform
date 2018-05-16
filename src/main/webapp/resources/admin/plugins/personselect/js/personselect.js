//显示弹窗效果
function show_lay(id) {
    // alert(id);
    $('.detail_layer').show();
    $('.detail_layer').attr('id', 'y' + id);
    $('.fade_layer').fadeIn();

}

//点击下拉人员效果
$('.detail_layer').find('.left .areas_list .areas_list_one > a').click(function () {
    if ($(this).parent().hasClass('on')) {
        $(this).parent().removeClass('on');
        $(this).parent().next('.areas_list_two').css('height', '0');
        //隐藏下一级目录
        $(this).siblings('dl').hide();

        //初始化
        $(this).siblings('dl').find('dl').hide();
        $(this).siblings('dl').find('a').removeClass('on');
        //console.log($(this).siblings('dl').find('ul.areas_list_two').length);
        $(this).siblings('dl').find('ul.areas_list_two').css('height', '0');
    } else {
        //解绑子级分类点击事件
        $(this).siblings('dl').children('dd').children('a').unbind('click');

        $(this).parent().addClass('on');
        //如果下面还有下一级
        if ($(this).siblings('dl').length > 0) {
            //显示子类
            $(this).siblings('dl').show();
            //子级分类点击事件
            $(this).siblings('dl').children('dd').children('a').click(function () {
                //解绑子级分类点击事件
                $(this).siblings('dl').children('dd').children('a').unbind('click');
                //切换隐藏
                if ($(this).hasClass('on')) {
                    $(this).removeClass('on');
                    $(this).parent().next('.areas_list_two').css('height', '0');
                    //隐藏下一级目录
                    $(this).siblings('dl').hide();
                } else {

                    $(this).addClass('on');
                    $(this).parent().next('.areas_list_two').css('height', 'auto');
                    //如果还有下一级
                    if ($(this).siblings('dl').length > 0) {
                        $(this).siblings('dl').show().css('padding-left', '30px');
                        $(this).siblings('dl').children('dd').children('a').click(function () {
                            //解绑子级分类点击事件
                            $(this).siblings('dl').children('dd').children('a').unbind('click');
                            if ($(this).hasClass('on')) {
                                $(this).removeClass('on');
                                $(this).parent().next('.areas_list_two').css('height', '0');
                                //隐藏下一级目录
                                $(this).siblings('dl').hide();
                            } else {
                                $(this).addClass('on');
                                $(this).parent().next('.areas_list_two').css('height', 'auto');
                                //如果还有下一级
                                if ($(this).siblings('dl').length > 0) {
                                    $(this).siblings('dl').show().css('padding-left', '30px');
                                    $(this).siblings('dl').children('dd').children('a').click(function () {
                                        console.log(1233);
                                        if ($(this).hasClass('on')) {
                                            $(this).removeClass('on');
                                            $(this).parent().next('.areas_list_two').css('height', '0');
                                            //隐藏下一级目录
                                            $(this).siblings('dl').hide();
                                        } else {
                                            $(this).addClass('on');
                                            $(this).parent().next('.areas_list_two').css('height', 'auto');
                                        }
                                    });
                                } else {
                                    //console.log($(this).parent().next('.areas_list_two').html());

                                    $(this).parent().next('.areas_list_two').css('height', 'auto');
                                }
                            }
                        });
                    } else {
                        //console.log($(this).parent().next('.areas_list_two').html());

                        $(this).parent().next('.areas_list_two').css('height', 'auto');
                    }
                }



            });
        } else {
            $(this).parent().next('.areas_list_two').css('height', 'auto');
        }

    }

});

//插入元素
$('.detail_layer').find('.areas_list_two li').click(function () {
    //对勾切换效果
    if (!$(this).find('span').hasClass('hover')) {
        $(this).find('span').addClass('hover');
    } else {
        $(this).find('span').removeClass('hover');
    }
    //获取选中元素html
    var val_prev = $(this).find('span').attr('id');
    $message_peo = $(this).html();

    //获取添加后的数组
    var attrid = $(this).parents('.select_peo_con').find('.right ul.send_to li').map(function () {
        return $(this).find('span').attr('id');
    });
    //判断数字是否存在数组里
    if (jQuery.inArray(val_prev, attrid) == -1) {
        $(this).parents('.select_peo_con').find('.right ul.send_to').append("<li>" + $message_peo + "</li>");
    } else {
        // alert('已存在列表中');
        while ($(this).parents('.select_peo_con').find('.right ul.send_to').find("#" + val_prev).length > 0)
        {
            $(this).parents('.select_peo_con').find('.right ul.send_to').find("#" + val_prev).parent().remove();
        }
    }

});

//选择人员插入当前点击的input里面
function do_add(elm) {
    var id = $(elm).parents('div.detail_layer').attr('id');
    var temp = '';
    var nruid = '';
    var temp1 = '';
    var eid = '';
    $(elm).parents('#' + id).find('ul.send_to li').each(function (i, e) {
        text = $(e).find('span');
        for (var i = 0; i < text.length; i++) {
            var val = $(text[i]).html();
            if (val != '') {
                temp += val + ',';
            }
            var id = $(text[i]).attr('id');
            if (id != '') {
                nruid += id + ',';
            }
        }
        ;
    });

    var temps = temp.substring(0, temp.length - 1);
    $('#area_btn_' + id).val(temps);
    $('#nruid_' + id).val(nruid);
    $('#eid').val(eid);
    $(elm).parents('.detail_layer').fadeOut();
    //清空选择人员
    $('.detail_layer .right ul li').remove();
    $(elm).parents('.select_peo_con').find('ul.areas_list_two').css('height', '0');
    $(elm).parents('.select_peo_con').find('.areas_list_one').removeClass('on');
    $(elm).parents('.select_peo_con').find('.areas_list_two li span').removeClass('hover');
    $('.fade_layer').fadeOut();
}
;
//取消按钮关闭事件
$('a.close_btn').click(function () {
    $(this).parents('.detail_layer').fadeOut();
    //清空选择人员
    $('.detail_layer .right ul li').remove();
    $(this).parents('.select_peo_con').find('ul.areas_list_two').css('height', '0');
    $(this).parents('.select_peo_con').find('.areas_list_one').removeClass('on');
    $(this).parents('.select_peo_con').find('.areas_list_two li span').removeClass('hover');
    $('.fade_layer').fadeOut();
});

