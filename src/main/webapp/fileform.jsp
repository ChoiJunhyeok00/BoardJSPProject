<%--
  Created by IntelliJ IDEA.
  User: choejunhyeog
  Date: 2022/11/15
  Time: 5:00 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <title>fileform</title>
</head>
<body>
<form method="post" action="fileupload.jsp" enctype="multipart/form-data">
    <input type="file" name="file">
    <input type="submit" value="upload">
</form>

</body>
</html>
