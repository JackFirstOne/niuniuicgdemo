<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/9/8
  Time: 10:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
  <%--文件上传客户端三要素 ，
      1 input type = file
      2、form method = post
      3、form enctype= multipart/form-data
  --%>
  <form method="post" action="${pageContext.request.contextPath}/user/hello24" enctype="multipart/form-data">
      文件名称：<input type="text" name="filename" /> </br>
     <%-- 文件1：<input type="file" name="upload"> </br>
      文件2：<input type="file" name="upload2"> </br>--%>
      <%--文件name相同在服务器端用数组接收数据--%>
      文件1：<input type="file" name="upload"> </br>
      文件2：<input type="file" name="upload"> </br>
      文件3：<input type="file" name="upload"> </br>
      <input type="submit" value="提交"> </br>
  </form>

</body>
</html>
