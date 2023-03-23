First_judge();
radio_checked();

function First_judge() {
	var x = [document.getElementById('begin-input'), document.getElementById('end-input'), document.getElementById(
		'signal-input'), document.getElementById('double-input-begin'), document.getElementById(
		'double-input-end')]
	for (let a = 0; a < x.length; a++) {
		x[a].value = "";
	}
}

function radio_checked() {
	if (document.getElementById('signal').checked = true) {
		document.getElementById('signal-trip').style.display = 'block';
		document.getElementById('begin-input').className = 'Signal-inputs';
		document.getElementById('end-input').className = 'Signal-inputs';
		document.getElementById('signal-input').className = 'Signal-inputs';
		document.getElementById('double-input-begin').className = '';
		document.getElementById('double-input-end').className = '';
	} else {
		document.getElementById('double').checked = true;
		document.getElementById('double-trip').style.display = 'block';
		document.getElementById('begin-input').className = 'Double-inputs';
		document.getElementById('end-input').className = 'Double-inputs';
		document.getElementById('double-input-begin').className = 'Double-inputs';
		document.getElementById('double-input-end').className = 'Double-inputs';
		document.getElementById('signal-input').className = '';
	}
}
var Choose_signal_trip = document.getElementsByClassName('Signal-inputs');
var Choose_double_trip = document.getElementsByClassName('Double-inputs');
// 国际热门
var judge_input_reset;
function Choose_city_down(id) {
	document.getElementsByClassName(id)[0].style.opacity = 0.3;
	if (id == 'begin-input') {
		judge_input = 1;
	} else if (id == 'end-input') {
		judge_input = 2;
	} else if (id == 'signal-input') {
		judge_input = 3;
	} else if (id == 'double-input-begin') {
		judge_input = 4;
	} else if (id == 'double-input-end') {
		judge_input = 5;
	}
	judge_input_reset=judge_input;
	placeholder(judge_input_reset);
	Time_input = setInterval("placeholder(judge_input_reset)", 100);
}
var judge_input;
var Time_input;
var judge_senter;
var input_together = [document.getElementById('begin-input'), document.getElementById('end-input'), document
	.getElementById('signal-input'), document.getElementById('double-input-begin'), document.getElementById(
		'double-input-end')
];
var date_menu = document.getElementById('date-menu');
var city_menu = document.getElementById('city-menu');
// 数组存放变量，调用变量需要通过数组
var js1, js2, js3, js4, js5;
var js_together = [js1, js2, js3, js4, js5];

function city_menu_enter() {
	input_together[judge_input - 1].style.borderColor = '#338fff';
	clearTimeout(js_together[judge_input - 1]);
	clearTimeout(menu_Time_list[judge_input - 1]);
}
var mjs1, mjs2, mjs3, mjs4, mjs5;
var menu_Time_list = [mjs1, mjs2, mjs3, mjs4, mjs5];

function mouseleave_menu() {
	if (judge_input == 1) {
		menu_Time_list[0] = setTimeout(function() {
			document.getElementsByClassName('first-city-input')[0].style.display = 'none';
			input_together[judge_input - 1].style.borderColor = '#e7e7e7';
		}, 1000)
	} else if (judge_input == 2) {
		menu_Time_list[1] = setTimeout(function() {
			document.getElementsByClassName('second-city-input')[0].style.display = 'none';
			input_together[judge_input - 1].style.borderColor = '#e7e7e7';
		}, 1000)
	} else if (judge_input == 3) {
		menu_Time_list[2] = setTimeout(function() {
			document.getElementsByClassName('first-date-input')[0].style.display = 'none';
			input_together[judge_input - 1].style.borderColor = '#e7e7e7';
		}, 1000)
	} else if (judge_input == 4) {
		menu_Time_list[3] = setTimeout(function() {
			document.getElementsByClassName('second-date-input')[0].style.display = 'none';
			input_together[judge_input - 1].style.borderColor = '#e7e7e7';
		}, 1000)
	} else if (judge_input == 5) {
		menu_Time_list[4] = setTimeout(function() {
			document.getElementsByClassName('third-date-input')[0].style.display = 'none';
			input_together[judge_input - 1].style.borderColor = '#e7e7e7';
		}, 1000)
	}
}

function mouseleave_input(input_name) {
	document.getElementsByClassName(input_name)[0].style.display = 'none';
	input_together[judge_input - 1].style.borderColor = '#e7e7e7';
}

function leave_input(x, y) {
	if (x == 1) {
		js_together[x - 1] = setTimeout("mouseleave_input('first-city-input')", 1000);
	} else {
		document.getElementById(y).style.borderColor = '#e7e7e7';
	}
}

function leave_input_2(x, y) {
	if (x == 2) {
		js_together[x - 1] = setTimeout("mouseleave_input('second-city-input')", 1000);
	} else {
		document.getElementById(y).style.borderColor = '#e7e7e7';
	}
}

function leave_input_3(x, y) {
	if (x == 3) {
		js_together[x - 1] = setTimeout("mouseleave_input('first-date-input')", 1000);
	} else {
		document.getElementById(y).style.borderColor = '#e7e7e7';
	}
}

function leave_input_4(x, y) {
	if (x == 4) {
		js_together[x - 1] = setTimeout("mouseleave_input('second-date-input')", 1000);
	} else {
		document.getElementById(y).style.borderColor = '#e7e7e7';
	}
}

function leave_input_5(x, y) {
	if (x == 5) {
		js_together[x - 1] = setTimeout("mouseleave_input('third-date-input')", 1000);
	} else {
		document.getElementById(y).style.borderColor = '#e7e7e7';
	}
}

function placeholder(x) {
	var pele;
	var begin_end;
	if (x == 1) {
		begin_end = document.getElementById('begin-input');
		pele = document.getElementById('begin-p');
		if (begin_end.value.length != 0) {
			pele.style.opacity = 0;
			return 0;
		} else {
			pele.style.opacity = 0.3;
			return 1;
		}
	} else if (x == 2) {
		begin_end = document.getElementById('end-input');
		pele = document.getElementById('end-p');
		if (begin_end.value.length != 0) {
			pele.style.opacity = 0;
			return 0;
		} else {
			pele.style.opacity = 0.3;
			return 1;
		}
	} else if (x == 3) {
		begin_end = document.getElementById('signal-input');
		pele = document.getElementById('signal-input-p');
		if (begin_end.value.length != 0) {
			pele.style.opacity = 0;
			return 0;
		} else {
			pele.style.opacity = 0.3;
			return 1;
		}
	} else if (x == 4) {
		begin_end = document.getElementById('double-input-begin');
		pele = document.getElementById('double-input-begin-p');
		if (begin_end.value.length != 0) {
			pele.style.opacity = 0;
			return 0;
		} else {
			pele.style.opacity = 0.3;
			return 1;
		}
	} else if (judge_input == 5) {
		begin_end = document.getElementById('double-input-end');
		pele = document.getElementById('double-input-end-p');
		if (begin_end.value.length != 0) {
			pele.style.opacity = 0;
			return 0;
		} else {
			pele.style.opacity = 0.3;
			return 1;
		}
	}
}

function hot_cities(id) {
	var x = document.getElementsByClassName('span01');
	var y = document.getElementsByClassName('cities');
	var z = document.getElementById(id);
	for (let b = 0; b < x.length; b++) {
		x[b].style.backgroundColor = '#e6e6e6';
		x[b].style.color = '#666';
		y[b].style.display = 'none';
	}
	z.style.backgroundColor = '#c81623';
	z.style.color = '#fff';
	if (id == 'span01') {
		y[0].style.display = 'block';
	} else if (id == 'span02') {
		y[1].style.display = 'block';
	} else if (id == 'span03') {
		y[2].style.display = 'block';
	} else if (id == 'span04') {
		y[3].style.display = 'block';
	} else if (id == 'span05') {
		y[4].style.display = 'block';
	} else if (id == 'span06') {
		y[5].style.display = 'block';
	} else {
		y[6].style.display = 'block';
	}
}
// 单选按钮
function signal_choosed() {
	document.getElementById('double-trip').style.display = 'none';
	document.getElementById('signal-trip').style.display = 'block';
	document.getElementById('begin-input').className = 'Signal-inputs';
	document.getElementById('end-input').className = 'Signal-inputs';
	document.getElementById('signal-input').className = 'Signal-inputs';
	document.getElementById('double-input-begin').className = '';
	document.getElementById('double-input-end').className = '';
}

function double_choosed() {
	document.getElementById('signal-trip').style.display = 'none';
	document.getElementById('double-trip').style.display = 'block';
	document.getElementById('begin-input').className = 'Double-inputs';
	document.getElementById('end-input').className = 'Double-inputs';
	document.getElementById('double-input-begin').className = 'Double-inputs';
	document.getElementById('double-input-end').className = 'Double-inputs';
	document.getElementById('signal-input').className = '';
}
var city = document.getElementsByClassName('cities');
for (let a = 0; a < city.length; a++) {
	for (let b = 0; b < city[a].getElementsByTagName('span').length; b++) {
		city[a].getElementsByTagName('span')[b].addEventListener('click', function() {
			var x = document.getElementById('begin-input');
			var y = document.getElementById('end-input');
			if (judge_input == 1) {
				x.value = city[a].getElementsByTagName('span')[b].innerHTML;
			} else if (judge_input == 2) {
				y.value = city[a].getElementsByTagName('span')[b].innerHTML;
			}
			if (Choose_signal_trip.length > 0) {
				judge_input++;
				var z = judge_input;
				if (judge_input - 1 < 3) {
					setTimeout(function() {
						Choose_signal_trip[z - 1].focus()
					}, 1500);
					judge_input--;
					Choose_signal_trip[judge_input - 1].style.borderColor = '#e7e7e7';
					city_menu.style.display = 'none';
				}
			} else if (Choose_double_trip.length > 0) {
				if (judge_input < 3) {
					judge_input++;
					var z = judge_input;
					setTimeout(function() {
						Choose_double_trip[z - 1].focus()
					}, 1500);
					judge_input--;
					Choose_double_trip[judge_input - 1].style.borderColor = '#e7e7e7';
					city_menu.style.display = 'none';
				} else {
					judge_input++;
					var z = judge_input;
					if (judge_input - 1 < 5) {
						setTimeout(function() {
							Choose_double_trip[z - 2].fouse()
						}, 1500);
					}
					judge_input--;
					Choose_double_trip[judge_input - 2].style.borderColor = '#e7e7e7';
					city_menu.style.display = 'none';

				}
			}

		}, false)
	}
}
// 点击按钮转换
function Change_begin_end() {
	var x, y, z;
	x = document.getElementById('begin-input');
	y = document.getElementById('end-input');
	z = x.value;
	x.value = y.value;
	y.value = z;
}
// 日历
// 首先获取日期
var date = document.getElementById('day').getElementsByTagName('span');
var li = document.getElementById('day').getElementsByTagName('li');
var this_year = document.getElementById('year');
var this_month = document.getElementById('month');
var today;
today = new Date();

function Choose_date() {
	Your_date(today);
	Judge_week(today);
}

function Judge_week(your_day) {
	var judge_weekday = new Date(your_day.getFullYear(), your_day.getMonth(), 1);
	var x = judge_weekday.getDay();
	if (x == 0) {
		li[0].style.marginLeft = 0;
	} else if (x == 1) {
		li[0].style.marginLeft = '36px';
	} else if (x == 2) {
		li[0].style.marginLeft = '72px';
	} else if (x == 3) {
		li[0].style.marginLeft = '108px';
	} else if (x == 4) {
		li[0].style.marginLeft = '144px';
	} else if (x == 5) {
		li[0].style.marginLeft = '180px';
	} else {
		li[0].style.right = '216px';
	}
}

function Your_date(your_day) {
	this_year.innerHTML = your_day.getFullYear();
	this_month.innerHTML = your_day.getMonth() + 1;
	var year = your_day.getFullYear();
	var month = your_day.getMonth() + 1;
	var max_day = new Date(year, month, 0).getDate();
	var date_length = date.length;
	var day_list = document.getElementById('day');
	if (max_day > date.length) {
		for (let a = 1; a <= (max_day - date_length); a++) {
			let li_e = document.createElement('li');
			var span = document.createElement('span');
			span.innerText = date_length + a;
			li_e.appendChild(span);
			day_list.appendChild(li_e);
			add_li_event(date_length + a - 1);
		}
	} else {
		for (let a = 0; a < (date_length - max_day); a++) {
			day_list.removeChild(li[date_length - 1 - a])
		}
	}
	for (let a = 0; a < date.length; a++) {
		if (Number(date[a].innerText) == today.getDate() && Number(this_year.innerText) == today.getFullYear() &&
			Number(this_month.innerText) - 1 == today.getMonth()) {
			date[a].setAttribute('style', 'background-color:#c81623;color:#fff');
		} else {
			date[a].setAttribute('style', 'background-color:transparent;color:#666');
		}
	}
}

for (let a = 0; a < date.length; a++) {
	date[a].style.backgroundColor = 'transparent';
}
Choose_date();
var Next_one = document.getElementById('Next-one');
var Previous_one = document.getElementById('Previous-one');
Next_one.addEventListener('click', function() {
	var next_month = new Date(Number(this_year.innerText), Number(this_month.innerText));
	var new_day = new Date(next_month.getFullYear(), next_month.getMonth(), 1)
	Your_date(next_month);
	Judge_week(new_day);
}, false)
Previous_one.addEventListener('click', function() {
	if (Number(this_year.innerText) > today.getFullYear() || (Number(this_year.innerText) == today
			.getFullYear() && Number(this_month.innerText) > today.getMonth() + 1)) {
		var previous_month;
		var new_day;
		today = new Date();
		previous_month = new Date(Number(this_year.innerText), Number(this_month.innerText) - 2);
		Your_date(previous_month);
		new_day = new Date(previous_month.getFullYear(), previous_month.getMonth(), 1);
		Judge_week(new_day);
		// if (Number(this_year.innerText) == today.getFullYear() && Number(this_month.innerText) == today
		// 	.getMonth() + 1) {
		// 	for (let a = 0; a < date.length; a++) {
		// 		today = new Date();
		// 		if (a >= today.getDate() - 1) {
		// 			add_li_event(a);
		// 		} else {
		// 			li[a].removeEventListener('click', clickli, false)
		// 			li[a].removeEventListener('mouseover', mouseoverli, false);
		// 			li[a].removeEventListener('mouseout', mouseoutli, false);
		// 			date[a].removeEventListener('mouseover', mouseoverspan, false);
		// 			date[a].removeEventListener('mouseout', mouseoutspan, false);
		// 		}
		// 	}
		// } else {
		// 	for (let a = 0; a < date.length; a++) {
		// 		add_li_event(a);
		// 	}
		// }
	}
}, false)
var judge_date_input;
var double_input_begin = document.getElementById('double-input-begin');
var double_input_end = document.getElementById('double-input-end');
function clickli(x){
	
}
function mouseoverli(x){
	
}
function mouseoutli(x){
	
}
function mouseoverspan(x){
	
}
function mouseoutspan(x){
	
}
function add_li_event(x) {
	li[x].addEventListener('click', function(){
		for (let b = 0; b < date.length; b++) {
			date[b].style.backgroundColor = 'transparent';
			date[b].style.color = '#666';
		}
		date[x].style.backgroundColor = '#c81623';
		date[x].style.color = '#fff';
		li[x].style.backgroundColor = 'transparent';
		var signal_input = document.getElementById('signal-input')
		if (judge_date_input == 1) {
			if (Number(this_month.innerText >= 10)) {
				if (Number(date[x].innerText >= 10)) {
					signal_input.value = this_year.innerText + '/' + this_month
						.innerText + '/' + date[x].innerText;
				} else {
					signal_input.value = this_year.innerText + '/' + this_month
						.innerText + '/' + '0' + date[x].innerText;
				}
		
			} else if (Number(date[x].innerText >= 10)) {
				signal_input.value = this_year.innerText + '/' + '0' + this_month
					.innerText + '/' + date[x].innerText;
			} else {
				signal_input.value = this_year.innerText + '/' + '0' + this_month
					.innerText + '/' + '0' + date[x].innerText;
			}
		} else if (judge_date_input == 2) {
			if (Number(this_month.innerText >= 10)) {
				if (Number(date[x].innerText >= 10)) {
					double_input_begin.value = this_year.innerText + '/' + this_month
						.innerText + '/' + date[x].innerText;
				} else {
					double_input_begin.value = this_year.innerText + '/' + this_month
						.innerText + '/' + '0' + date[x].innerText;
				}
			} else if (Number(date[x].innerText >= 10)) {
				double_input_begin.value = this_year.innerText + '/' + '0' + this_month
					.innerText + '/' + date[x].innerText;
			} else {
				double_input_begin.value = this_year.innerText + '/' + '0' + this_month
					.innerText + '/' + '0' + date[x].innerText;
			}
		} else if (Number(this_month.innerText >= 10)) {
			if (Number(date[x].innerText >= 10)) {
				double_input_end.value = this_year.innerText + '/' + this_month
					.innerText + '/' + date[x].innerText;
			} else {
				double_input_begin.value = this_year.innerText + '/' + this_month
					.innerText + '/' + '0' + date[x].innerText;
			}
		} else if (Number(date[x].innerText >= 10)) {
			double_input_end.value = this_year.innerText + '/' + '0' + this_month
				.innerText + '/' + date[x].innerText;
		} else {
			double_input_end.value = this_year.innerText + '/' + '0' + this_month
				.innerText + '/' + '0' + date[x].innerText;
		}
		if (judge_date_input==1) {
			signal_input.style.borderColor='#e7e7e7';
			date_menu.style.display='none';
			signal_input.blur();
			judge_input=undefined;
		}
		else if (judge_date_input==2) {
			double_input_begin.style.borderColor='#e7e7e7';
			date_menu.style.display='none';
			setTimeout(function(){
				double_input_end.focus()
			},1500)
		}
		else if (judge_date_input==3) {
			double_input_end.style.borderColor='#e7e7e7';
			date_menu.style.display='none';
			double_input_end.blur();
			judge_input=undefined;
		}
	}, false);
	li[x].addEventListener('mouseover',function(){
		if (date[x].style.backgroundColor == '#c81623') {
			li[x].style.backgroundColor = 'transparent';
		} else if (date[x].style.backgroundColor == 'transparent') {
			li[x].style.backgroundColor = '#eee';
		}
	}, false);
	li[x].addEventListener('mouseout', function(){
		li[x].style.backgroundColor = 'transparent';
	}, false);
	date[x].addEventListener('mouseover', function(){
		if (date[x].style.backgroundColor == '#c81623') {
			date[x].style.color = '#fff';
		} else if (date[x].style.backgroundColor == 'transparent') {
			date[x].style.color = '#c81623';
		}
	}, false);
	date[x].addEventListener('mouseout',function(){
		if (date[x].style.backgroundColor == '#c81623') {
				date[x].style.color = '#fff';
			} else if (date[x].style.backgroundColor == 'transparent') {
				date[x].style.color = '#666';
			}
	}, false)
}
for (let a = 0; a < date.length; a++) {
		add_li_event(a);
}
// 输入框与日历交互
// 机票
var Choose_ticket=document.getElementById('JP-three-parts').getElementsByTagName('a');
var Three_kinds_ticket=document.getElementsByClassName('ticket');
function Choose_your_ticket(id){
	for(let a=0;a<Choose_ticket.length;a++){
		Choose_ticket[a].style.color='#666';
	}
	document.getElementById(id).style.color='#c81623';
	if (id=='Domestic') {
		for (let i = 0; i < Three_kinds_ticket.length; i++) {
			Three_kinds_ticket[i].style.transform="translateX(0)";
		}
	}
	else if (id=='International') {
		for (let i = 0; i < Three_kinds_ticket.length; i++) {
			Three_kinds_ticket[i].style.transform="translateX(-163px)"
		}
	}
	else{
		for (let i = 0; i < Three_kinds_ticket.length; i++) {
			Three_kinds_ticket[i].style.transform="translateX(-326px)"
		}
	}
}
// var p = document.createElement('p');
// p.id = 'ppppp';
// var weekday=today.getDay();
// today.setMonth(today.getMonth()+1);
// today.setDate(today.getDate()+19);
// p.innerHTML = Choose_signal_trip[0].className;
// document.getElementsByTagName('body')[0].appendChild(p)

