<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page isELIgnored="false" %>
<html>
    <head>
        <title>Dashboard</title>
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
        .center {
            text-align: center;
        }
        .title {
            padding: 0px;
            font-size: 2rem;
            word-wrap: break-word;
            margin-bottom: 20px;
        }
        .grid-container {
            display: grid;
            grid-template-columns: auto auto auto;
            gap: 10px;
            padding: 10px;
        }
        .grid-container > div {
            padding: 20px 0;
        }
        .primary {
            background-color: darkcyan;
        }
        .green {
            background-color: darkseagreen;
        }
        .secondary {
             background-color: coral;
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
            <h1 class="title">Olá ${usuario_logado.nome}!</h1>
            <h2>Id da seção: ${pageContext.session.id}</h2>
            <h3>Data de criação: ${pageContext.session.creationTime}</h3>
            <h3>Última data de acesso: ${pageContext.session.lastAccessedTime}</h3>
            <div clsas="container">
                <div class="grid-container">
                    <a href="controlador?opcao=cliente" class="button primary">Cadastrar cliente</a>
                    <a href="controlador?opcao=produto" class="button green">Cadastrar produto</a>
                    <a href="controlador?opcao=logout" class="button secondary">Sair</a>
                </div>
            </div>
        </div>
    </body>
</html>
