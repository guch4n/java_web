<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<!DOCTYPE html>
<html lang="pt-BR">
    <head>
        <meta charset="UTF-8">
        <title>Projeto Autenticação</title>
    </head>
    <body>
        <h2>Faça login</h2>

        <form action="login" method="post">

            <input name="usuario" placeholder="Informe o usuario" /> <br><br>
            <input name="senha" type="password" placeholder="Informe a senha" /> <br><br>

            <button type="submit">Entrar</button> <br><br>
            <a href="cadastro.jsp">Faça seu cadastro</a>

            <br>

            <p style="color: red;">${mensagemErro}</p>

        </form>
    
    </body>
</html>