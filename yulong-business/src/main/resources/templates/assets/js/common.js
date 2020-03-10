$(function(){
	
	$('.header-left i').click(function(){
		if($(this).hasClass('fa-outdent')) {
			$(this).addClass('fa-indent');
			$(this).removeClass('fa-outdent');
			$('.navbar-brand').animate({'width':'60px'});
			$('.navbar-side').animate({'width':'60px'});
			$('#page-wrapper').animate({'marginLeft':'60px'});
			$('.nav-second-level').removeClass('in');
			$('.navbar-side li').removeClass('active');
			
			$('.navbar-brand .logoimg').animate({'width':'90%'});
			$('.navbar-brand .logotxt').css('display','none');
			$('.navbar-side li a').css('textAlign','center');
			$('.navbar-side li a span').css('display','none');
			$('#main-menu > li > a em').css('display','none');
			$('.nav > li > a > i').css('marginRight','0');
			$('.nav > li > a > i').css('fontSize','20px');
			
		}else {
			$(this).removeClass('fa-indent');
			$(this).addClass('fa-outdent');
			$('.navbar-brand').animate({'width':'240px'});
			$('.navbar-side').animate({'width':'240px'});
			$('#page-wrapper').animate({'marginLeft':'240px'});
			
			$('.navbar-brand .logoimg').animate({'width':'30%'});
			$('.navbar-brand .logotxt').css('display','inline-block');
			$('.navbar-side li a').css('textAlign','left');
			$('.navbar-side li a span').css('display','inline-block');
			$('#main-menu > li > a em').css('display','inline-block');
			$('.nav > li > a > i').css('marginRight','10px');
			$('.nav > li > a > i').css('fontSize','inherit');
		}
	})
	
	// 左边导航栏缩进的鼠标经过
	$('.nav > li').mouseover(function(){
		let $this = $(this);
		let secondul = $this.find('ul')
		if($('.navbar-brand').css('width') == '60px' && secondul.is('.nav-second-level')) {
			$this.siblings().find('.nav-second-level').removeClass('in secondover');
			secondul.addClass('in secondover');
		}
	})
	$('.nav > li').mouseout(function(){
		let $this = $(this);
		let secondul = $this.find('ul')
		if($('.navbar-brand').css('width') == '60px' && secondul.is('.nav-second-level')) {
			secondul.removeClass('in secondover');
		}
	})
	
	// 左右点击按钮buttons switch
	$('.buttons').click(function(){
		// console.log(1)
		if($(this).hasClass('buttons-default')){
			$(this).addClass('buttons-active');
			$(this).removeClass('buttons-default');
			$(this).next('input').val('on');
		}else {
			$(this).addClass('buttons-default');
			$(this).removeClass('buttons-active');
			$(this).next('input').val('off');
		}
	})
	
	// table  checkbox事件
	$('table input[type=checkbox]').click(function(){
		
		if($(this).parent().is('th')){
			//全选，全不选
			if($(this)[0].checked == true){
				$(this).parents().find('table').find('input[type=checkbox]').each(function(i){
					$(this)[0].checked = true
				})
			}else {
				$(this).parents().find('table').find('input[type=checkbox]').each(function(i){
					$(this)[0].checked = false
				})
			}
			//全选，全不选结束
		}else{
			// 是否全选
			let isallchecked = true;
			$(this).parents().find('table').find('td').find('input[type=checkbox]').each(function(){
				isallchecked = isallchecked && $(this)[0].checked;
			})
			$(this).parents('table').find('th').find('input[type=checkbox]')[0].checked = isallchecked;
			// 是否全选结束
		}
	})
	// table  checkbox事件结束
	
	// table 多选删除事件
	$('[tabledel]').click(function(){
		$(this).parents().find('table').find('td').find('input[type=checkbox]').each(function(){
			if($(this)[0].checked == true){
				console.log($(this).parent().siblings().children())
			}
		})
	})
	// table 多选删除事件
	
	// table 行删除事件
	var tabletrDel ='';
	layui.use('layer',function(){
		var layer = layui.layer;
		
		var eqs = '';
		$('th').each(function(i){
			if($(this).text() == 'ID'){
				eqs = i;
			}
		})
		
		$('[rowdel]').click(function(){
			var $this = $(this);
			layer.confirm('确定删除吗?', function(index){
			  console.log($this.parents('tr').children().eq(eqs).text());
			  
			  layer.close(index);
			});       
			
		})
	})
	
	// table 行删除事件结束
	
	// table点击编辑
	$('[tableedit]').click(function(){
		
		let $this = $(this);
		if($(this).children().is('input')) {
			$(this).children('input').blur(function(){//焦点失去
				$this.text($this.children('input').val())//将input改变后的值作为文本显示
				$this.children('input').remove();//移除input
			})
		}else {
			var input = '<input type="text" value="' + $(this).text() + '" />';//创建input进行编辑，并将文本值赋给input
			$(this).text('');//移除文本
			$(this).append(input);//添加input
		}	
	})
	// table点击编辑事件结束
	// table行内编辑
	$('[rowedit]').click(function(){
		var hrefs = $(this).attr('rowedit');
		window.location.href = hrefs;
	})
	// table行内编辑结束
	// table弹窗
	$('[popupimg]').click(function(){
		console.log($(this).attr('popupimg'))
		let $this = $(this);
		// 弹窗显示
		layui.use('layer', function(){
		  var layer = layui.layer;
		  
			 layer.open({
			   content: '<img src="' + $this.attr('popupimg') + '" />'
			   ,btn: ['提交']
			   ,yes: function(index, layero){
				 //按钮【提交】的回调
			   }
			   ,cancel: function(){ 
				 //右上角关闭回调
				 
				 //return false 开启该代码可禁止点击该按钮关闭
			   }
			 });
		});
	})
	$('[popup]').click(function(){
		console.log($(this).attr('popup'))
		let $this = $(this);
		// 弹窗显示
		layui.use('layer', function(){
		  var layer = layui.layer;
		  
			 layer.open({
			   content: $this.attr('popup')
			   ,btn: ['提交']
			   ,yes: function(index, layero){
				 //按钮【提交】的回调
			   }
			   ,cancel: function(){ 
				 //右上角关闭回调
				 
				 //return false 开启该代码可禁止点击该按钮关闭
			   }
			 });
		});
	})
	// table弹窗结束
	
	// 内容设置下拉框setcon
	$('.setcons a[role=button]').click(function(){
		if($(this).text() == '+'){
			$(this).text('-')
			$(this).parent().parent().siblings().slideDown('fast')
		}else {
			$(this).text('+')
			$(this).parent().parent().siblings().slideUp('fast')
		}
	})
	
	
})

