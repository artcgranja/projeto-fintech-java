<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isErrorPage="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Erro - Fintech</title>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background: linear-gradient(135deg, #ff6b6b 0%, #ffa500 100%);
            min-height: 100vh;
            display: flex;
            align-items: center;
            justify-content: center;
            color: #333;
        }

        .error-container {
            background: white;
            padding: 40px;
            border-radius: 15px;
            box-shadow: 0 20px 40px rgba(0,0,0,0.1);
            text-align: center;
            max-width: 500px;
            width: 90%;
        }

        .error-icon {
            font-size: 80px;
            margin-bottom: 20px;
            display: block;
        }

        .error-title {
            font-size: 28px;
            color: #e74c3c;
            margin-bottom: 20px;
            font-weight: 300;
        }

        .error-message {
            font-size: 16px;
            color: #666;
            margin-bottom: 30px;
            line-height: 1.6;
        }

        .error-details {
            background: #f8f9fa;
            padding: 20px;
            border-radius: 8px;
            margin-bottom: 30px;
            text-align: left;
            font-family: 'Courier New', monospace;
            font-size: 14px;
            color: #666;
            border-left: 4px solid #e74c3c;
        }

        .btn {
            display: inline-block;
            padding: 12px 24px;
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            color: white;
            text-decoration: none;
            border-radius: 6px;
            font-weight: 500;
            transition: opacity 0.3s;
            margin: 0 10px;
        }

        .btn:hover {
            opacity: 0.9;
        }

        .btn-secondary {
            background: #6c757d;
        }

        .help-text {
            margin-top: 20px;
            font-size: 14px;
            color: #999;
        }
    </style>
</head>
<body>
<div class="error-container">
    <span class="error-icon">⚠️</span>

    <h1 class="error-title">Oops! Algo deu errado</h1>

    <div class="error-message">
        <c:choose>
            <c:when test="${not empty erro}">
                ${erro}
            </c:when>
            <c:when test="${not empty pageContext.exception}">
                Ocorreu um erro interno no sistema. Nossa equipe foi notificada.
            </c:when>
            <c:otherwise>
                Desculpe, ocorreu um erro inesperado. Tente novamente em alguns minutos.
            </c:otherwise>
        </c:choose>
    </div>

    <c:if test="${not empty pageContext.exception}">
        <div class="error-details">
            <strong>Detalhes técnicos:</strong><br>
                ${pageContext.exception.class.simpleName}: ${pageContext.exception.message}
        </div>
    </c:if>

    <div>
        <a href="javascript:history.back()" class="btn btn-secondary">← Voltar</a>
        <a href="dashboard" class="btn">🏠 Ir ao Dashboard</a>
    </div>

    <div class="help-text">
        Se o problema persistir, entre em contato com o suporte técnico.
    </div>
</div>
</body>
</html>