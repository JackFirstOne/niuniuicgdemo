<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/9/8
  Time: 9:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="${pageContext.request.contextPath}/js/jquery-1.6.2.min.js" type="text/javascript"></script>
    <script>
         var userList = new Array();
         userList.push({username:"zhangs",age:14});
         userList.push({username:"lisi",age:29});

         $.ajax({
             url: "${pageContext.request.contextPath}/user/hello15",
             type:"POST",
             data:JSON.stringify(userList),
             contentType:"application/json;charset=utf-8"

         })

    </script>
</head>
<body>

</body>
</html>
