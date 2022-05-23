<%--
  Created by IntelliJ IDEA.
  User: Aluno
  Date: 11/05/2022
  Time: 16:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page isELIgnored="false" %>
<html>
    <head>
        <title>Clientes</title>
    </head>
    <style>
        body {
            background: lightgoldenrodyellow;
        }
        .card {
            background-color: white;
            padding: 40px;
            border-radius: 10px;
            box-shadow: 1px 1px 10px #dddddd;
            color: rgb(63, 63, 63);
            margin: 5px;
            width: 95%;
        }
        .title {
            padding: 0px;
            font-size: 2rem;
            word-wrap: break-word;
            margin-bottom: 20px;
        }
        .center {
            text-align: center;
        }
        .button {
            color: white;
            padding: 10px;
            border: none;
            box-shadow: 1px 1px 5px #dddddd;
            border-radius: 10px;
            cursor: pointer;
            opacity: 0.6;
            transition: 0.5s;
            margin-left: 2px;
        }
        .button.secondary {
            background-color: coral;
        }
        .button:hover {
            transform: translateY(-6px);
            transition: 0.5s;
            opacity: 1;
        }
        a {
            text-decoration: none;
        }
    </style>
    <body>
        <div class="card center">
            <h1 class="title">Cadastrar cliente</h1>
            <a href="controlador?opcao=voltar" class="button secondary">Voltar</a>
        </div>
    </body>
</html>
