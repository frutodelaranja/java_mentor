<%--
  Created by IntelliJ IDEA.
  User: evgeny
  Date: 25.04.2020
  Time: 17:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script src='https://kit.fontawesome.com/a076d05399.js'></script>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<body>
<div class="w3-container">
    <div class="w3-responsive">

        <table class="w3-table  w3-striped w3-centered w3-bordered w3-border w3-hoverable ">
            <caption>Таблица 1.0 Список чебурашек</caption>
            <tr class="w3-grey">
                <th>User</th>
                <th>ID</th>
                <th>Name</th>
                <th>Login</th>
                <th>Pass</th>
                <th>Portal</th>
                <th>Portal</th>
            </tr>
            <c:set var="User" value="user"/>
            <c:forEach var="user" items="${users}">
                <tr>
                    <td>
                        <c:out value="${User}"/>
                    </td>
                    <td>
                        <c:out value="${user.getId()}"/>
                    </td>
                    <td>
                        <c:out value="${user.getName()}"/>
                    </td>
                    <td>
                        <c:out value="${user.getLogin()}"/>
                    </td>
                    <td>
                        <c:out value="${user.getPassword()}"/>
                    </td>

                    <td>
                        <form action="/update" method="get">

                            <div>
                                    <%--                            <input type="number" name="id" value="${user.getId()}" style="width: 10%"/>--%>
                                <button type="submit" class="w3-btn w3-hover-amber w3-round" onclick=input name="id"
                                        value="${user.getId()}">Update <i class="fa fa-refresh fa-spin"></i></button>
                            </div>

                        </form>

                    </td>
                    <td>
                        <form action="/delete" method="post">
                            <div>
                                    <%--                                <input type="submit" name="id" value="${user.getId()}" class="w3-btn w3-hover-red w3-round">Delete</input>--%>
                                <button type="submit" class="w3-btn w3-hover-pink w3-round" onclick=input name="id"
                                        value="${user.getId()}">Delete <i class='fa fa-recycle'></i></button>
                            </div>

                        </form>
                    </td>
                </tr>
            </c:forEach>

        </table>

        <div class="w3-container w3-center">
            <div class="w3-bar w3-padding-large w3-padding-24">
                <%--                <i style="font-size:24px" class="fa fa-refresh fa-spin"></i>--%>
                <button class="w3-btn w3-hover-lime w3-round-large" onclick="location.href='/add'">Add <i
                        class='fas fa-user-plus'></i></button>
            </div>
        </div>
    </div>
</div>

</body>
</html>
