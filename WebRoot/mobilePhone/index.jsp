<%@page pageEncoding="UTF-8" language="java"%>
<!DOCTYPE html>
<html>
	<head>
		<meta content="text/html; charset=UTF-8" http-equiv="Content-Type">
		<meta charset="utf-8">
		<meta
			content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no"
			name="viewport">
		<meta name="description" />
		<meta name="author" />
		<meta name="viewport" content="width=device-width, maximum-scale=1.0, user-scalable=2.0;" />
		<link rel="stylesheet" href="css/html5reset.css" />
		<link rel="stylesheet" href="css/index.css" />
		<link rel="stylesheet" href="css/keyframes.css" />
		<script src="js/jquery-2.0.2.min.js"></script>
		<script src="js/index.js"></script>
		<style>

		.weixin-tip img {
			max-width: 90%;
			height: auto;
			position: absolute;
			top: 0;
			right: 0;
		}
		.weixin-tip {
			display: none;
			position: fixed;
			left:0;
			top:0;
			bottom:0;
			background: rgba(0,0,0,0.6);
			filter:alpha(opacity=60);
			height: 100%;
			width: 100%;
			z-index: 100;
		}
		.weixin-tip p {
			text-align: center;
			margin-top: 10%;
			padding:0 5%;
		}

</style>		
		
		
	</head>
	
	<body>
		<div class="weixin-tip">
			<p>
				<img src="img/live_weixin.png" alt="微信打开"/>
			</p>
		</div>
		<header>
			沃尔沃汽车（中国）SMM平台
		</header>
		
		<div class="bd">
			<img src="img/bg.png" style="width: 100%;margin-top: 50px;">
			<p style="width:50%;float:left;"> 
				<a name="androidapk_tongyong" class="btn androidversion"
					href="http://smm.volvocars.com.cn/smm_android.apk">
					Android安装
				</a>
			</p>
			<p style="width:50%;float:left;">
				<a id="iosapp_tongyong" name="iosapp_tongyong" class="btn iosversion"
					href="itms-services:///?action=download-manifest&url=https://smm.volvocars.com.cn/innoShare_test.plist">
					iPhone安装
				</a>
			</p>
		</div>
		<footer class="footer_1">
			Copyright©2005-2015 www.volvocars.com.cn

		</footer>
	</body>
</html>
<script type="text/javascript">
function oncheck(){
	var ua = window.navigator.userAgent.toLowerCase();
	if(ua.match(/MicroMessenger/i) == 'micromessenger'){
		alert('请点击右上角的浏览器中打开');
		return true;
	}else{
		return false;
	}
}
$(window).on("load",function(){
    var winHeight = $(window).height();
	function is_weixin() {
	    var ua = navigator.userAgent.toLowerCase();
	    if (ua.match(/MicroMessenger/i) == "micromessenger") {
	        return true;
	    } else {
	        return false;
	    }
	}
	var isWeixin = is_weixin();
	if(isWeixin){
		$(".weixin-tip").css("height",winHeight);
        $(".weixin-tip").show();
	}
})

</script>


