<%--
  Created by IntelliJ IDEA.
  User: yang！
  Date: 20/7/6
  Time: 15:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>WeChat</title>
    <link rel="stylesheet" href="/layui/css/layui.css">
</head>
<body>
    <%--<form >
        <input name="context" value="消息" type="text">
        <input  value="发送" type="submit">
    </form>--%>

   <%-- <form action="/sendImage" method="post">
        <input name="image" value="" type="file">
        <input  value="发送" type="submit">
    </form>--%>
    <form class="layui-form" action="/sendImage" method="post" enctype="multipart/form-data">
        <div class="layui-form-item">
            <label class="layui-form-label">文件框</label>
            <div class="layui-input-block">
                <input type="file" name="image" required  lay-verify="required" placeholder="请选择" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn" type="submit">立即发送</button>
                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
            </div>
        </div>
    </form>
    <hr class="layui-bg-orange">
    <i class="layui-icon layui-icon-heart-fill" style="font-size: 30px; color: #ff1600;"></i>
    <i class="layui-icon layui-icon-heart-fill" style="font-size: 30px; color: rgb(67,255,248);"></i>
    <i class="layui-icon layui-icon-heart-fill" style="font-size: 30px; color: #f01cff;"></i>
    <i class="layui-icon layui-icon-heart-fill" style="font-size: 30px; color: #68ff34;"></i>
    <i class="layui-icon layui-icon-heart-fill" style="font-size: 30px; color: #2facff;"></i>
    <hr class="layui-bg-orange">
    <form class="layui-form" action="/sendWeChat" method="post">
        <div class="layui-form-item">
            <label class="layui-form-label">输入框</label>
            <div class="layui-input-block">
                <input type="text" name="context" required  lay-verify="required" placeholder="请输入" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn" type="submit">立即发送</button>
                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
            </div>
        </div>
    </form>
</body>
<script src="/layui/layui.js"></script>
<script>
   /* layui.use('form', function(){
        var form = layui.form;

        //监听提交
        form.on('submit(formDemo)', function(data){
            layer.msg(JSON.stringify(data.field));
            return false;
        });
    });*/
</script>
</html>
