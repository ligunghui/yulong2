var loginurl = 'http://39.96.95.40:9001'
var myurl = 'http://39.96.95.40:9002'
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
			if(/index.html/g.test(window.location.href)) {
				times = setInterval(function() {
					if(names == null) {
						names = localStorage.getItem('userName');
						$('nav .header-right .layui-btn-radius').text('账号：' + names);
					}else {
						clearInterval(times)
						$('nav .header-right .layui-btn-radius').text('账号：' + names);
					}
				},200)
			}else {
				$('nav .header-right .layui-btn-radius').text('账号：' + names)
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
					  localStorage.clear();
					  layer.close(index);
					});
				})
			})
		})
	}
}


// 添加左侧栏
var str0 = `<li>
				<a class="" href="index.html"><i class="fa fa-desktop "></i><em>管理控制台</em></a>
			</li>
			<li>
				<a href="indexmsg-msg.html" class=""><i class="fa fa-object-group "></i><em>商会信息管理</em></a>
			</li>
			<li>
				<a href="#"><i class="fa fa-gears "></i><em>商会收益</em><span class="fa arrow"></span></a>
				<ul class="nav nav-second-level">
					<li>
						<a href="coin-month.html"><em>本月收益明细</em></a>
					</li>
					<li>
						<a href="coin-history.html"><em>历史收益明细</em></a>
					</li>
					<li>
						<a href="coin-recharge.html"><em>收益提现</em></a>
					</li>
				</ul>
			</li>
			
			<li>
				<a href="storecontrol.html"><i class="fa fa-user-circle-o "></i><em>推荐商户管理</em></span></a>
			</li>
			<li>
				<a href="#"><i class="fa fa-flag "></i><em>商会管理</em><span class="fa arrow"></span></a>
				<ul class="nav nav-second-level">
					<li>
						<a href="indexcontrol-master.html"><em>管理员管理</em></a>
					</li>
					<li>
						<a href="indexcontrol-vip.html"><em>会员管理</em></a>
					</li>
					<li>
						<a class="" href="indexcontrol-member.html"><em>成员管理</em></a>
					</li>
				</ul>
			</li>
			<li>
				<a href="chatteam-control.html"><i class="fa fa-comments "></i><em>商会聊天室管理</em></span></a>
			</li>
			<li>
				<a href="#"><i class="fa fa-bullhorn "></i><em>资讯管理</em><span class="fa arrow"></span></a>
				<ul class="nav nav-second-level">
					<li>
						<a href="news-index.html"><em>商会动态</em></a>
					</li>
					<li>
						<a href="active-public.html"><em>活动信息发布</em></a>
					</li>
					
				</ul>
			</li>`;
let str = str0;
(function addMenu() {
	$('#main-menu').append(str)
	let locals = window.location.href.split('/').slice(-1);
	$('#main-menu li a').each(function(i,item){
		if($(item).attr('href')) {
			let hrefs = new RegExp($(item).attr('href'))
			if(hrefs.exec(locals)) {
				if(hrefs.exec(locals).index == 0) {
					$(item).addClass('active-menu').parents('li').addClass('active')
				}
			}
		}
		
	})
})()