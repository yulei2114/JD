var lunhuan = document.getElementById('lunhuan-text');
var text = document.getElementById('lunbo-text').getElementsByClassName('lunbo');
var len = text.length;
var i = 0;

function lunhuanText() {
	lunhuan.innerHTML = text[i].innerHTML;
	i++;
	if (i >= len) {
		i = 0;
	}
}
var int = setInterval("lunhuanText()", 3000);
var input = document.getElementById('input');
input.onfocus = function() {
	clearInterval(int);
	document.getElementById('lunhuan-text').style.opacity = 0.7;
}
// 重新启用定时器，不要重新var int，否则会打开两个定时器
input.onblur = function() {
	int = setInterval("lunhuanText()", 3000);
	document.getElementById('lunhuan-text').style.opacity = 'initial';
}
// 输入框文本检测
function jisuanInlen() {
	var inlen = input.value.length;
	if (inlen == 0) {
		document.getElementById('lunhuan-text').style.visibility = "visible";
	} else {
		document.getElementById('lunhuan-text').style.visibility = "hidden";
	}
}
var curlen = setInterval("jisuanInlen()", 30);
// 轮播图和导航标
var lunboimg = document.getElementsByClassName('lunbo-imgs01');
var lunboindicator = document.getElementsByClassName('indicator');
var len02 = lunboimg.length;
var a = 0;

function lunboimg01() {
	if (a >= len02) {
		a = 0;
	}
	for (var b = 0; b < len02; b++) {
		lunboimg[b].style.opacity = 0;
		lunboindicator[b].style.background = "rgba(255,255,255,.4)";
	}
	lunboimg[a].style.opacity = 1;
	lunboindicator[a].style.background = "white";
	a++;
}
var c = setInterval(lunboimg01, 3000);
// 鼠标滑过导航标切换对应图标
// var d = document.getElementsByClassName('indicator');
// d[0].onmouseover = function() {
// 	addevent();
// }
function addevent(value) {
	clearInterval(c);
	for (let f = 0; f < lunboindicator.length; f++) {
		lunboindicator[f].style.background = "rgba(255,255,255,.4)";
		lunboimg[f].style.opacity = 0;
	}
	if (value == 'main-body-img01') {
		a = 0;
		lunboindicator[a].style.background = "white";
		lunboimg[a].style.opacity = 1;
	} else if (value == 'main-body-img02') {
		a = 1;
		lunboindicator[a].style.background = "white";
		lunboimg[a].style.opacity = 1;
	} else if (value == 'main-body-img03') {
		a = 2;
		lunboindicator[a].style.background = "white";
		lunboimg[a].style.opacity = 1;
	} else if (value == 'main-body-img04') {
		a = 3;
		lunboindicator[a].style.background = "white";
		lunboimg[a].style.opacity = 1;
	} else if (value == 'main-body-img05') {
		a = 4;
		lunboindicator[a].style.background = "white";
		lunboimg[a].style.opacity = 1;
	} else if (value == 'main-body-img06') {
		a = 5;
		lunboindicator[a].style.background = "white";
		lunboimg[a].style.opacity = 1;
	} else if (value == 'main-body-img07') {
		a = 6;
		lunboindicator[a].style.background = "white";
		lunboimg[a].style.opacity = 1;
	} else {
		a = 7;
		lunboindicator[a].style.background = "white";
		lunboimg[a].style.opacity = 1;
	}
	c = setInterval(lunboimg01, 3000);
}
// 按钮与轮播图和导航标关联
var button_left = document.getElementById('button-left');
button_left.onclick = function() {
	clearInterval(c);
	for (var b = 0; b < len02; b++) {
		if (lunboimg[b].style.opacity == 1 && b >= 1) {
			a = b;
			lunboimg[b - 1].style.opacity = 1;
			lunboimg[b].style.opacity = 0;
			lunboindicator[b - 1].style.background = "white";
			lunboindicator[b].style.background = "rgba(255,255,255,.4)";
			break;
		} else if (lunboimg[b].style.opacity == 1 && b == 0) {
			a = b;
			lunboimg[len02 - 1].style.opacity = 1;
			lunboimg[b].style.opacity = 0;
			lunboindicator[len02 - 1].style.background = "white";
			lunboindicator[b].style.background = "rgba(255,255,255,.4)";
			break;
		}
	}
	c = setInterval(lunboimg01, 3000);
}
var button_right = document.getElementById('button-right');
button_right.onclick = function() {
	clearInterval(c);
	for (var b = 0; b < len02; b++) {
		if (lunboimg[b].style.opacity == 1 && b < len02 - 1) {
			a = b + 1;
			lunboimg[b + 1].style.opacity = 1;
			lunboimg[b].style.opacity = 0;
			lunboindicator[b + 1].style.background = "white";
			lunboindicator[b].style.background = "rgba(255,255,255,.4)";
			break;
		} else if (lunboimg[b].style.opacity == 1 && b == len02 - 1) {
			a = b + 1;
			lunboimg[0].style.opacity = 1;
			lunboimg[b].style.opacity = 0;
			lunboindicator[0].style.background = "white";
			lunboindicator[b].style.background = "rgba(255,255,255,.4)";
			break;
		}
	}
	c = setInterval(lunboimg01, 3000);
}
// 鼠标滑过关闭定时器，划出启动
var start_end = document.getElementById('lunbo-img-main');
start_end.onmouseover = function() {
	clearInterval(c);
}
start_end.onmouseout = function() {
	c = setInterval(lunboimg01, 3000);
}
// 今日推荐轮播图
var JRtuijian_part01 = document.getElementsByClassName('tuijian-part01');
var lentuijian = JRtuijian_part01.length;
var JRtuijian_part02 = document.getElementsByClassName('tuijian-part02');
var JRtuijian_part03 = document.getElementsByClassName('tuijian-part03');
var JR = 0;
var f = 0;
var a1 = document.getElementsByClassName('a1');
var a2 = document.getElementsByClassName('a2');
var a3 = document.getElementsByClassName('a3');

function lunboimgJR() {
	var z_index = 3;
	if (f >= lentuijian) {
		f = 0;
	}
	var a_xiaobiao = f;
	if (JR >= lentuijian) {
		JR = 0;
	}
	for (var b = 0; b < lentuijian; b++) {
		JRtuijian_part01[b].style.opacity = 0;
		JRtuijian_part02[b].style.opacity = 0;
		JRtuijian_part03[b].style.opacity = 0;
		if (a_xiaobiao >= lentuijian) {
			a_xiaobiao = 0;
		}
		a1[a_xiaobiao].style.zIndex = z_index;
		a2[a_xiaobiao].style.zIndex = z_index;
		a3[a_xiaobiao].style.zIndex = z_index;
		// 驼峰式写法,zIndex
		z_index--;
		a_xiaobiao++;
	}
	JRtuijian_part01[JR].style.opacity = 1;
	JRtuijian_part02[JR].style.opacity = 1;
	JRtuijian_part03[JR].style.opacity = 1;
	JR++;
	f++;
}
var Time_JR = setInterval(lunboimgJR, 6000);
// 按钮部分
var button_left_02 = document.getElementById('button-left-02');
button_left_02.onclick = function() {
	clearInterval(Time_JR);
	for (var b = 0; b < lentuijian; b++) {
		if (JRtuijian_part01[b].style.opacity == 1 && b >= 1) {
			JR = b;
			f = b;
			JRtuijian_part01[b - 1].style.opacity = 1;
			JRtuijian_part02[b - 1].style.opacity = 1;
			JRtuijian_part03[b - 1].style.opacity = 1;
			JRtuijian_part01[b].style.opacity = 0;
			JRtuijian_part02[b].style.opacity = 0;
			JRtuijian_part03[b].style.opacity = 0;
			break;
		} else if (JRtuijian_part01[b].style.opacity == 1 && b == 0) {
			JR = b;
			f = b;
			JRtuijian_part01[lentuijian - 1].style.opacity = 1;
			JRtuijian_part02[lentuijian - 1].style.opacity = 1;
			JRtuijian_part03[lentuijian - 1].style.opacity = 1;
			JRtuijian_part01[b].style.opacity = 0;
			JRtuijian_part02[b].style.opacity = 0;
			JRtuijian_part03[b].style.opacity = 0;
			break;
		}
	}
	Time_JR = setInterval(lunboimgJR, 6000);
}
var button_right_02 = document.getElementById('button-right-02');
button_right_02.onclick = function() {
	clearInterval(Time_JR);
	for (var b = 0; b < len02; b++) {
		if (JRtuijian_part01[b].style.opacity == 1 && b < lentuijian - 1) {
			JR = b + 1;
			f = b + 1;
			JRtuijian_part01[b + 1].style.opacity = 1;
			JRtuijian_part02[b + 1].style.opacity = 1;
			JRtuijian_part03[b + 1].style.opacity = 1;
			JRtuijian_part01[b].style.opacity = 0;
			JRtuijian_part02[b].style.opacity = 0;
			JRtuijian_part03[b].style.opacity = 0;
			break;
		} else if (JRtuijian_part01[b].style.opacity == 1 && b == lentuijian - 1) {
			JR = b + 1;
			JRtuijian_part01[0].style.opacity = 1;
			JRtuijian_part02[0].style.opacity = 1;
			JRtuijian_part03[0].style.opacity = 1;
			JRtuijian_part01[b].style.opacity = 0;
			JRtuijian_part02[b].style.opacity = 0;
			JRtuijian_part03[b].style.opacity = 0;
			break;
		}
	}
	Time_JR = setInterval(lunboimgJR, 6000);
}
// 定时器开关,事件属性调用时执行函数，不调用不执行
function Time_JR_close() {
	clearInterval(Time_JR);
}

function Time_JR_open() {
	Time_JR = setInterval(lunboimgJR, 6000);
}
// 服务导航栏效果
var H_J_J = [document.getElementById('huafei-liuliang'), document.getElementById('jipiao'), document.getElementById(
	'jiudian-yuding')];
var four_a = document.getElementById('Services-nevigation').getElementsByTagName('a');
function Four_main_parts(id) {
	for (let b = 0; b < 3; b++) {
		four_a[b].className = 'A';
		four_a[b].style.transform = 'translateY(-28px)';
		four_a[b].getElementsByClassName('s1')[0].style.color = '#333';
		H_J_J[b].style.display = 'none';
	}
	var x = document.getElementById(id);
	x.className = 'A2';
	x.getElementsByClassName('s1')[0].style.color = '#c81623';
	document.getElementById('Details').style.transform = 'translateY(-190px)';
	if (id == 'huafei') {
		H_J_J[0].style.display = 'block';
	} else if (id == 'feiji') {
		H_J_J[1].style.display = 'block';
	} else {
		H_J_J[2].style.display = 'block';
	}
}
// 还原按钮
document.getElementById('quxiao').addEventListener('click', function() {
	for (let b = 0; b < 3; b++) {
		four_a[b].className = 'A';
		four_a[b].style.transform = 'translateY(0)';
		four_a[b].getElementsByClassName('s1')[0].style.color = '#333';
	}
	event.stopPropagation();
}, false)
// 初始判断选项框，并插入对应文本
document.getElementById('huafei').addEventListener('mouseenter', function() {
	var w = document.getElementById('sel01');
	var k = document.getElementById('sel03');
	for (let b = 0; b < w.options.length; b++) {
		if (w.value == w.options[b].value) {
			ChangeText(w.options[b].id);
			break;
		}
	}
	for (let b = 0; b < k.options.length; b++) {
		if (k.value == k.options[b].value) {
			ChangeText02(k.options[b].id);
		}
	}
	event.stopPropagation();
}, false);
// 鼠标划过话费和流量板块
function Change_H_L(id) {
	var x = document.getElementById(id);
	var y = document.getElementById('two-parts').getElementsByTagName('a');
	var w = document.getElementById('two-parts-H-L').getElementsByClassName('display');
	for (let b = 0; b < 2; b++) {
		y[b].style.color = '#666';
		w[b].style.display = 'none';
	}
	if (id == 'huafeichongzhi') {
		w[0].style.display = 'block';
	} else {
		w[1].style.display = 'block';
	}
	x.style.color = '#e40015';
}
// 话费框
function ChangeText(id) {
	var x = document.getElementById(id);
	var y = document.getElementById('chongzhi-youhui-H');
	if (x.value == 10) {
		y.innerHTML = '￥9.8-￥11.0';
	} else if (x.value == 20) {
		y.innerHTML = '￥19.6-￥21.0';
	} else if (x.value == 30) {
		y.innerHTML = '￥29.4-￥31.0';
	} else if (x.value == 50) {
		y.innerHTML = '￥49.0-￥50.0';
	} else if (x.value == 100) {
		y.innerHTML = '￥98.0-￥100.0';
	} else if (x.value == 200) {
		y.innerHTML = '￥196.0-￥200.0';
	} else if (x.value == 300) {
		y.innerHTML = '￥294.0-￥300.0';
	} else {
		y.innerHTML = '￥490.0-￥500.0';
	}
}
// 流量框
function ChangeText02(id) {
	var x = document.getElementById(id);
	var y = document.getElementById('chongzhi-youhui-L');
	if (x.value == 50) {
		y.innerHTML = '￥7.5-￥10.0';
	} else if (x.value == 100) {
		y.innerHTML = '￥9.95-￥20.0';
	} else if (x.value == 200) {
		y.innerHTML = '￥19.9-￥20.5';
	} else if (x.value == 300) {
		y.innerHTML = '￥29.0-￥29.9';
	} else {
		y.innerHTML = '￥49.0-￥50.0';
	}
}
// body点击事件
function bodyclick() {
	document.getElementById('Details').style.transform = 'translateY(0)';;
	for (let i = 0; i < 3; i++) {
		four_a[i].className = 'A';
		four_a[i].style.transform = 'translateY(0)';
		four_a[i].getElementsByClassName('s1')[0].style.color = '#333';
	}
	event.stopPropagation();
}
