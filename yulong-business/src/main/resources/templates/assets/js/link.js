var loginurl = 'http://39.96.95.40:9001'
var myurl = 'http://39.96.95.40:9001'
var appurl = 'http://39.96.95.40:9005'
var sessionid = ''
sessionid = localStorage.getItem("sessionid")
// localStorage.setItem("temp",arr); //存入 参数： 1.调用的值 2.所要存入的数据 
// 　　 console.log(localStorage.getItem("temp"));//输出
// 七天后清空localStorage
var localTime = localStorage.getItem("localTime")
if(Number(localTime) + 24*60*60*1000 < new Date()) {
	localStorage.clear()
}
if(/login.html/.test(window.location.href)) {
		
}else {
	if(!sessionid) {
		layer.msg('请登录')
		setTimeout(function() {
			window.location.href = 'login.html'
		},1200)
	}else {
		$(function() {
			var names = localStorage.getItem('userName');
			let times = null;
			let types = localStorage.getItem('storeType');
			if(/index.html/g.test(window.location.href)) {
				times = setInterval(function() {
					if(names == null) {
						names = localStorage.getItem('userName');
						$('nav .header-right .layui-btn-radius').text('账号：' + names);
						types = localStorage.getItem('storeType');
						isTypes(types);
					}else {
						clearInterval(times)
						$('nav .header-right .layui-btn-radius').text('账号：' + names);
						isTypes(types);
					}
				},200)
			}else {
				$('nav .header-right .layui-btn-radius').text('账号：' + names)
				isTypes(types);
			}
			
			$('.navbar .header-right').children().eq(1).click(function() {
				window.location.href="index.html"
			})
			
			$('.navbar .header-right').children().eq(2).click(function() {
				window.location.reload()
			})
			
			$('.navbar .header-right').children().eq(3).click(function() {
				layui.use('layer',function() {
					var layer = layui.layer;
					layer.confirm('确定退出登录吗?', {icon: 3, title:'提示'}, function(index){
					  //do something
					  window.location.href="login.html"
					  layer.close(index);
					});
				})
			})
		})
	}
}

function isTypes(types) {
	if(localStorage.getItem('storeType')) {
		// types = localStorage.getItem('storeType');
		// 1=>普通商户 2=>本地服务商户
		var str1_1 = 'store-goods.html',
			str1_2 = 'store-kind.html',
			str2_1 = 'store-service-goods.html',
			str2_2 = 'store-service-kind.html',
			str_normal = 'order-before.html',
			str_service = 'order-service.html';
		
		$('nav a').each(function(i) {
			if(types == 2) {
				if($(this).attr('href') == str1_1 || $(this).attr('href') == str1_2) {
					$(this).parent().remove()
				}
				if($(this).attr('href') == str_normal) {
					$(this).attr('href',str_service)
				}
			}else {
				if($(this).attr('href') == str2_1 || $(this).attr('href') == str2_2) {
					$(this).parent().remove()
				}
			}
		})
		
	}
}