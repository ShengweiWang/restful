<%--
  Created by IntelliJ IDEA.
  User: Shengwei_Wang
  Date: 11/19/16
  Time: 1:29 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title></title>
  </head>
  <body>
  <p>
    <form action = "/add" method="post">
      Company Symbol : (fb, aapl ...)<br>
      <input type="text" name="company"><br>
      <input type="submit" value = "Add Company">
    </form>
  </p>
  <p>
    <form action = "/delete" method="post">
      Company Symbol : (fb, aapl ...)<br>
      <input type="text" name="company"><br>
      <input type="submit" value = "Delete Company">
    </form>
  </p>
  <p>
    <form action = "/history" method="get">
      Company Symbol : (fb, aapl ...)<br>
      <input type="text" name="company"><br>
      <input type="submit" value = "Company History">
    </form>
  </p>

  <p>
    <form action = "/listcompanies" method="get">
      <input type="submit" value = "list Companies">
    </form>
  </p>
  </body>
</html>
