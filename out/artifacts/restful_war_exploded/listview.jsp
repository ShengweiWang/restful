<%--
  Created by IntelliJ IDEA.
  User: Shengwei_Wang
  Date: 11/21/16
  Time: 6:35 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Company List</title>
</head>
<body>
<div class="container-fluid">
    <div class="row-fluid">
        <div class="span12">
            <h3>
                Stock Monitor Company List
            </h3>
        </div>
    </div>
    <div class="row-fluid">
        <div class="span12">
            <script type="text/javascript">

                var list = ${it}
                for (var p in list) {
                    document.writeln("Company " + p + "<br>");
                    document.writeln("Company Symbol : " + list[p].symbol + "<br>");
                    document.writeln("Price : " + list[p].price + "<br>")
                    document.writeln("<br>");
                }

            </script>
        </div>
    </div>
</div>
</body>
</html>
