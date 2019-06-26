
//==================图片详细页函数=====================

//图片放大镜效果
$(document).ready(function(){
	//点击到中图
	var midChangeHandler = null;

	$(".spec-scroll img").bind("mouseover", function(){
		window.clearTimeout(midChangeHandler);
		midChange($(this).attr("src").replace("small", "mid"));
		$(this).css({
			"border":"1px solid #011F3D",
			"padding":"1px"
		});
	}).bind("mouseout", function(){
		$(this).css({
			"border":"1px solid #ccc",
			"padding":"2px"
		});
	});

	function midChange(src) {
		$("#midimg").attr("src", src).load(function() {
			changeViewImg();
		});
	}

	//大视窗看图
	function mouseover(e) {
		if ($("#winSelector").css("display") == "none") {
			$("#winSelector,#bigView").show();
		}

		$("#winSelector").css(fixedPosition(e));
		e.stopPropagation();
	}


	function mouseOut(e) {
		if ($("#winSelector").css("display") != "none") {
			$("#winSelector,#bigView").hide();
		}
		e.stopPropagation();
	}


	$("#midimg").mouseover(mouseover); //中图事件
	$("#midimg,#winSelector").mousemove(mouseover).mouseout(mouseOut); //选择器事件

	var $divWidth = $("#winSelector").width(); //选择器宽度
	var $divHeight = $("#winSelector").height(); //选择器高度
	var $imgWidth = $("#midimg").width(); //中图宽度
	var $imgHeight = $("#midimg").height(); //中图高度
	var $viewImgWidth = $viewImgHeight = $height = null; //IE加载后才能得到 大图宽度 大图高度 大图视窗高度

	function changeViewImg() {
		$("#bigView img").attr("src", $("#midimg").attr("src").replace("mid", "big"));
	}

	changeViewImg();

	$("#bigView").scrollLeft(0).scrollTop(0);
	function fixedPosition(e) {
		if (e == null) {
			return;
		}
		var $imgLeft = $("#midimg").offset().left; //中图左边距
		var $imgTop = $("#midimg").offset().top; //中图上边距
		X = e.pageX - $imgLeft - $divWidth / 2; //selector顶点坐标 X
		Y = e.pageY - $imgTop - $divHeight / 2; //selector顶点坐标 Y
		X = X < 0 ? 0 : X;
		Y = Y < 0 ? 0 : Y;
		X = X + $divWidth > $imgWidth ? $imgWidth - $divWidth : X;
		Y = Y + $divHeight > $imgHeight ? $imgHeight - $divHeight : Y;

		if ($viewImgWidth == null) {
			$viewImgWidth = $("#bigView img").outerWidth();
			$viewImgHeight = $("#bigView img").height();
			if ($viewImgWidth < 200 || $viewImgHeight < 200) {
				$viewImgWidth = $viewImgHeight = 800;
			}
			$height = $divHeight * $viewImgHeight / $imgHeight;
			$("#bigView").width($divWidth * $viewImgWidth / $imgWidth);
			$("#bigView").height($height);
		}

		var scrollX = X * $viewImgWidth / $imgWidth;
		var scrollY = Y * $viewImgHeight / $imgHeight;
		$("#bigView img").css({ "left": scrollX * -1, "top": scrollY * -1 });
		$("#bigView").css({ "top":0, "left":410});

		return { left: X, top: Y };
	}

});
//购买数量效果
$(function(){
	num = $('.mete .number').text();//购买数量初始值
	set = $('#stock span').text();//库存量

	//增加购买数量
	$('#subjoin').bind("click", function(){
		if( Number(num) >= Number(set)) {
			alert("购买数量已经超出库存");
		}else{
			num=String(Number(num)+1);
			$('.mete .number').text(num);
		}
	});

	//减少购买数量
	$('#subtract').bind("click", function(){
		if( Number(num) <= 1) {
			alert("购买数量最低不能少于1");
			$('.mete .number').text("1");
		}else{
			num=String(Number(num)-1);
			$('.mete .number').text(num);
		}
	});
})