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
      <title>Stock Monitor Operations</title>
      <meta name="viewport" content="width=device-width, initial-scale=1.0">
      <!-- import Bootstrap -->
      <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
  </head>
  <body>
  <div class="container-fluid">
      <div class="row-fluid">
          <div class="span12">
              <form action = "/add" method="post">
                  <fieldset>
                      <legend>Add Company</legend> <label></label><input type="text" name="company"/> <span class="help-block">Company Symbol : (fb, aapl ...)</span> <button type="submit" class="btn">submit</button>
                  </fieldset>
              </form>
              <form action = "/delete" method="post">
                  <fieldset>
                      <legend>Delete Company</legend> <label></label><input type="text" name="company"/> <span class="help-block">Company Symbol : (fb, aapl ...)</span> <button type="submit" class="btn">submit</button>
                  </fieldset>
              </form>
              <form action = "/history" method="get">
                  <fieldset>
                      <legend>Show Company History</legend> <label></label><input type="text" name="company"/> <span class="help-block">Company Symbol : (fb, aapl ...)</span> <button type="submit" class="btn">submit</button>
                  </fieldset>
              </form>
              <form action = "/listcompanies" method="get">
                  <fieldset>
                      <legend>List Companies</legend> <label></label><button type="submit" class="btn">submit</button>
                  </fieldset>
              </form>
          </div>
      </div>
  </div>
  </body>
</html>
