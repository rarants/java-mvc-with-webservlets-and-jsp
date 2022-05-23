<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page isELIgnored="false" %>

<html>
    <head>
        <title>Login</title>
    </head>
    <style>
        body {
            padding: 10px;
            background: cornsilk;
        }
        .formulario {
            background-color: white;
            padding: 40px;
            border-radius: 10px;
            box-shadow: 1px 1px 10px #dddddd;
            color: rgb(63, 63, 63);
            margin: 10px;
            width: 80%;
        }
        .input {
            border-radius: 8px;
            border: none;
            border: 1px solid #dddddd;
            padding: 5px;
            padding-left: 8px;
            width: 100%;
            margin-top: 5px;
            margin-right: 20px;
            width: 100%;
        }
        .center {
            text-align: center;
        }
        .left {
            text-align: left;
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
        }
        .title {
            padding: 0px;
            font-size: 2rem;
            word-wrap: break-word;
            margin-bottom: 20px;
        }
        .button.primary {
            background-color: darkcyan;
        }
        .button:hover {
            transform: translateY(-6px);
            transition: 0.5s;
            opacity: 1;
        }
        .grid-container {
            display: grid;
            grid-template-areas: 'main main right right';
            gap: 10px;
            padding: 10px;
        }
        .grid-container > div {
            padding: 20px 0;
        }
        .email { grid-area: main; }
        .password { grid-area: right; }
        .formulario .button {
            margin-left: 2px;
        }
        .child {
            margin: 0 auto;
        }
        .error {
            color: white;
            padding: 10px;
            border: none;
            box-shadow: 1px 1px 5px #dddddd;
            border-radius: 10px;
            background-color: coral;
            margin: 10px;
        }
    </style>
    <body>
        <div>
            <h1 class="title center">LOGIN</h1>
            <div class="container">
                <form action="login" method="post" class="formulario child">
                    <div class="grid-container">
                        <div class="email left">
                            <label for="email">Login</label>
                            <input type="email" placeholder="Digite seu email" name="email" required class="input"/>
                            </div>
                        <div class="password left">
                            <label for="senha">Senha</label>
                            <input type="password" placeholder="Digite sua senha" name="password" required class="input"/>
                        </div>
                    </div>
                    <c:if test="${not empty error}">
                        <div class="container center">
                            <h2 class="child error">
                                <b>${error}</b>
                            </h2>
                        </div>
                    </c:if>
                    <div class="center">
                        <input type="submit" value="Login" name="login" class="button primary"/>
                    </div>
                    <br />
                    <div class="center">
                        <a href="register?route=register" class="primary">Ainda não possui cadastro?</a>
                    </div>
                </form>
            </div>
        </div>
    </body>
</html>
