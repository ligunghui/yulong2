var loginurl = 'http://39.96.95.40:9001'
var myurl = 'http://39.96.95.40:9002'
var appurl = 'http://39.96.95.40:9005'
var sessionid = ''
sessionid = localStorage.getItem("sessionid")
// localStorage.setItem("temp",arr); //存入 参数： 1.调用的值 2.所要存入的数据 
// 　　 console.log(localStorage.getItem("temp"));//输出
// 24小时后清空localStorage
var localTime = localStorage.getItem("localTime")
if(localTime + 24*60*60*1000 < new Date()) {
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
			$('nav .header-right .layui-btn-radius').text('账号：' + names)
			
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