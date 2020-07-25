<%--
  Created by IntelliJ IDEA.
  User: xiaobao
  Date: 2020-07-24
  Time: 09:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta name="viewport"
          content="maximum-scale=1.0,minimum-scale=1.0,user-scalable=0,width=device-width,initial-scale=1.0"/>
    <meta charset="UTF-8">
    <title>Ad Hoc .ipa在线安装</title>
    <style>
        * {
            margin: 0px;
            padding: 0px;
        }

        .download_div {
            width: 100%;
            height: 100%;
            text-align: center;
            font-weight: bold;
            /*background: -webkit-gradient(linear,0 0,0 bottom,from(#dbeae0),to(hsla(0,0%,100%,.6)));*/
            font-size: 2em;
        }

        .download_title {
            padding-top: 1em;
            padding-bottom: 1em;
            /*background-color: #d7e2f7;*/
            background: -webkit-gradient(linear, left top, left bottom, from(#d7e2f7), to(#ffffff));
        }

        .img_app_overview {
            width: 100%;
            height: 480px;
            display: block;
        }

        .download_button {
            /*margin-top: -0.3em;*/
            background: -webkit-gradient(linear, left top, left bottom, from(#879ee3), to(#ffffff));
            width: 100%;
        }

        .button {
            /*margin-top: 5em;*/
            margin-left: 0.8em;
            display: inline-block;
            outline: none;
            cursor: pointer;
            text-align: center;
            text-decoration: none;
            font: 14px/100% Arial, Helvetica, sans-serif;
            padding: .5em 2em .55em;
            text-shadow: 0 1px 1px rgba(0, 0, 0, .3);
            -webkit-border-radius: .5em;
            -moz-border-radius: .5em;
            border-radius: .5em;
            -webkit-box-shadow: 0 1px 2px rgba(0, 0, 0, .2);
            -moz-box-shadow: 0 1px 2px rgba(0, 0, 0, .2);
            box-shadow: 0 1px 2px rgba(0, 0, 0, .2);
            color: #fff;
        }

        .button_green {
            background: #c8dd95;
            background: -webkit-gradient(linear, left top, left bottom, from(#7a71d0), to(#ffffff));
            background: -moz-linear-gradient(-90deg, #7a71d0, #ffffff);
        }

        .download_tips {
            font-size: 0.5em;
            margin-top: 1em;
            font-weight: normal;
            margin-top: 3em;
        }

        .button_top {
            margin-top: 3em;
        }

        a:hover, a:visited, a:link, a:active {
            text-decoration: none;
            color: #fff;
        }

        /*mobile*/
        @media screen and (max-width: 1199px) {
            .img_app_overview {
                width: 100%;
                height: 250px;
                display: block;
            }
        }
    </style>
</head>
<body>
<div class="download_div">


    <p class="download_title">test.jsp</p>
    <img class="img_app_overview" src="img_app_overview.png" alt="Mr.chao">
    <div class="download_button">
        <button class="button button_green button_top" onclick="anzhuangclick();"><a href="javascript:void(0);">安装
        </a>

            <button class="button button_green button_top" onclick="downloadClientApp();"><a
                    href="javascript:void(0);">首次安装</a>
            </button>
            <!-- <button class="button button_green button_top"><a href="{{androidAUrl}}">Android客户端下载</a></button> -->
            <div class="download_tips">
                <strong>微信扫描22222</strong>下载,<strong>请点击右上角</strong>,<br/>选择“<strong>在浏览器中打开</strong>”即可安装下载
            </div>
    </div>

    <div class="popup" style="display:none;"
         style="position:fixed;left:0;top:0;right:0;bottom:0;width:100%;height:100%;background:rgba(255,0,0,0.5)">

        <p style="font-size:20px;text-align:center;margin-top:100px;">正在加载中...</p>
    </div>
</div>

<script>
    var timer = null;
    if (getUrlParam()) {
        document.getElementsByClassName('popup')[0].style.display = 'block';
        setTimeout(function () {
            document.getElementsByClassName('popup')[0].style.display = 'none';

            window.open('itms-services://?action=download-manifest&url=https://shuyangxiaobao.github.io/ioschaojiqianming/src/main/webapp/Mytest.plist');

        }, 10000)
    } else {
        document.getElementsByClassName('popup')[0].style.display = 'none';

    }


    function getUrlParam() {
        var href = window.location.href;
        var arr = href.split('?UDID=');
        if (arr.length == 1) {
            return "";
        }
        var str = arr[1];
        if (str.length > 8) {
            document.cookie = 'name=' + str;
            var Days = 300;
            var exp = new Date();
            exp.setTime(exp.getTime() + Days * 24 * 60 * 60 * 1000);
            document.cookie = 'expires=' + exp.toGMTString();

        }
        return str;
    }

    function getCookie(objName) {//获取指定名称的cookie的值
        var arrStr = document.cookie.split("; ");
        for (var i = 0; i < arrStr.length; i++) {
            var temp = arrStr[i].split("=");
            if (temp[0] == objName) {
                return decodeURI(temp[1]);
            }
        }
    }


    // 直接安装
    function anzhuangclick() {
        alert("xlp");
        window.open('itms-services://?action=download-manifest&url=https://shuyangxiaobao.github.io/ioschaojiqianming/src/main/webapp/Mytest.plist');
    }


    function downloadClientApp() {
        var udid = getUrlParam();

        var udid2 = getCookie("name") || "";


        if (udid.length < 8 && udid2.length < 8) {
            alert("1");
            window.open('https://shuyangxiaobao.github.io/ioschaojiqianming/udid.mobileconfig');
        } else if (true || udid.length > 8 && udid2.length > 8) {

            ajaxFn(udid);
        } else if (udid2.length > 8 && udid.length < 8) {
            alert("3");
        }

    }
    function ajaxFn(udid){
        var ajax = new XMLHttpRequest();
        // 步骤二:设置请求的url参数,参数一是请求的类型,参数二是请求的url,可以带参数,动态的传递参数starName到服务端
        ajax.open('get', 'http://192.168.0.108:8080/receiveUUIDh5?udid=' + udid);
        //步骤三:发送请求
        ajax.send();

        ajax.onreadystatechange = function () {

            if (ajax.readyState == 4 && ajax.status == 200) {
                if (ajax.responseText == "success") {
                    // anzhuangclick();
                    window.location.href = "itms-services://?action=download-manifest&url=https://shuyangxiaobao.github.io/ioschaojiqianming/src/main/webapp/Mytest.plist";
                } else {
                    setTimeout(function () {
                        ajaxFn(udid);
                    },5000);
                }
            } else {
                // setTimeout(function () {
                //     ajaxFn(udid);
                // },5000);
            }
        }
    }

</script>

</body>
</html>

