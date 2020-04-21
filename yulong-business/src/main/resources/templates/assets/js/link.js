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
			</li>`,
	str1 = `<li>
				<a href="#"><i class="fa fa-object-group "></i><em>收益管理</em><span class="fa arrow"></span></a>
				<ul class="nav nav-second-level">
					<li>
						<a href="coin-detail.html"><em>收益明细</em></a>
					</li>
					
					<li>
						<a href="coin-recharge.html"><em>提现</em></a>
					</li>
				</ul>
			</li>`,
	str2 = `<li>
				<a href=""><i class="fa fa-th-large "></i><em>商户公告</em><span class="fa arrow"></span></a>
				<ul class="nav nav-second-level">
					<li>
						<a href="platform-notice.html"><em>公告设置</em></a>
					</li>
					<li>
						<a href="platform-banner.html"><em>banner设置</em></a>
					</li>
				</ul>
			</li>`,
	str3 = `<li id="store">
				<a href=""><i class="fa fa-home"></i><em>商铺</em><span class="fa arrow"></span></a>
				<ul class="nav nav-second-level">
					
					
					<li>
						<a href="store-commend-goods.html"><em>推荐商品管理</em></a>
					</li>
					
					
					<li>
						<a href="store-index.html"><em>商户管理</em></a>
					</li>
					<li>
						<a href="store-control.html"><em>商户管理员</em></a>
					</li>
				</ul>
			</li>`,
	str4 = `<li id="order">
				
			</li>`;
let str = str0 + str1 + str2 + str3 + str4;
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
	
	if(localStorage.getItem('storeType')) {
		isTypes()
	}else {
		var appendTimer = setInterval(function() {
			if(localStorage.getItem('storeType')) {
				clearInterval(appendTimer)
				isTypes()
			}
		},200)
	}
})()

function isTypes() {
	// 1=>普通商户 2=>本地服务商户
	if(localStorage.getItem('storeType') == 2) {
		$('#store .nav-second-level').prepend(`<li>
				<a href="store-service-kind.html"><em>服务分类管理</em></a>
			</li>`)
		$('#store .nav-second-level').prepend(`<li>
				<a href="store-service-goods.html"><em>服务管理</em></a>
			</li>`);
		$('#order').append(`<a href="order-service.html"><i class="fa fa-tasks"></i><em>订单管理</em></a>`);
	}else {
		$('#store .nav-second-level').prepend(`<li>
				<a href="store-kind.html"><em>商品分类管理</em></a>
			</li>`);
		$('#store .nav-second-level').prepend(`<li>
				<a href="store-goods.html"><em>商品管理</em></a>
			</li>`)
		$('#order').append(`<a href="order-before.html"><i class="fa fa-tasks"></i><em>订单管理</em></a>`);
	}
}