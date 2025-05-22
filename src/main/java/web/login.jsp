<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login - Fintech</title>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            min-height: 100vh;
            display: flex;
            align-items: center;
            justify-content: center;
        }

        .login-container {
            background: white;
            padding: 40px;
            border-radius: 10px;
            box-shadow: 0 15px 35px rgba(0,0,0,0.1);
            width: 100%;
            max-width: 400px;
        }

        .logo {
            text-align: center;
            margin-bottom: 30px;
        }

        .logo h1 {
            color: #333;
            font-size: 28px;
            font-weight: 300;
        }

        .form-group {
            margin-bottom: 20px;
        }

        .form-group label {
            display: block;
            margin-bottom: 5px;
            color: #555;
            font-weight: 500;
        }

        .form-group input {
            width: 100%;
            padding: 12px;
            border: 2px solid #ddd;
            border-radius: 5px;
            font-size: 16px;
            transition: border-color 0.3s;
        }

        .form-group input:focus {
            outline: none;
            border-color: #667eea;
        }

        .btn-login {
            width: 100%;
            padding: 12px;
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            color: white;
            border: none;
            border-radius: 5px;
            font-size: 16px;
            font-weight: 500;
            cursor: pointer;
            transition: opacity 0.3s;
        }

        .btn-login:hover {
            opacity: 0.9;
        }

        .alert {
            padding: 12px;
            margin-bottom: 20px;
            border-radius: 5px;
            text-align: center;
        }

        .alert-error {
            background-color: #f8d7da;
            color: #721c24;
            border: 1px solid #f5c6cb;
        }

        .demo-info {
            margin-top: 20px;
            padding: 15px;
            background-color: #e3f2fd;
            border-radius: 5px;
            font-size: 14px;
            color: #1565c0;
        }

        .demo-info strong {
            display: block;
            margin-bottom: 5px;
        }
    </style>
</head>
<body>
<div class="login-container">
    <div class="logo">
        <h1>💰 Fintech</h1>
        <p>Sistema de Gestão Financeira</p>
    </div>

    <c:if test="${not empty erro}">
        <div class="alert alert-error">
                ${erro}
        </div>
    </c:if>

    <form method="post" action="login">
        <div class="form-group">
            <label for="login">Login:</label>
            <input type="text" id="login" name="login" required
                   value="${param.login}" placeholder="Digite seu login">
        </div>

        <div class="form-group">
            <label for="senha">Senha:</label>
            <input type="password" id="senha" name="senha" required
                   placeholder="Digite sua senha">
        </div>

        <button type="submit" class="btn-login">Entrar</button>
    </form>

    <div class="demo-info">
        <strong>Usuários de Demonstração:</strong>
        Login: <code>admin</code> | Senha: <code>123456</code><br>
        Login: <code>usuario</code> | Senha: <code>senha123</code>
    </div>
</div>
</body>
</html>