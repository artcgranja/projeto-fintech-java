<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dashboard - Fintech</title>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background-color: #f5f5f5;
            line-height: 1.6;
        }

        .table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 10px;
        }

        .table th,
        .table td {
            padding: 12px;
            text-align: left;
            border-bottom: 1px solid #eee;
        }

        .table th {
            background-color: #f8f9fa;
            font-weight: 600;
            color: #555;
        }

        .table tr:hover {
            background-color: #f8f9fa;
        }

        .btn {
            display: inline-block;
            padding: 8px 16px;
            border: none;
            border-radius: 4px;
            text-decoration: none;
            font-size: 14px;
            cursor: pointer;
            transition: all 0.3s;
        }

        .btn-primary {
            background-color: #667eea;
            color: white;
        }

        .btn-primary:hover {
            background-color: #5a6fd8;
        }

        .btn-success {
            background-color: #28a745;
            color: white;
        }

        .btn-success:hover {
            background-color: #218838;
        }

        .navigation-menu {
            display: flex;
            gap: 10px;
            margin-bottom: 20px;
        }

        .empty-state {
            text-align: center;
            padding: 40px;
            color: #666;
        }

        .empty-state .icon {
            font-size: 48px;
            margin-bottom: 16px;
            opacity: 0.5;
        }
    </style>
</head>
<body>
<nav class="navbar">
    <h1>💰 Fintech</h1>
    <div class="user-info">
        <span>Bem-vindo, ${usuario.nome}!</span>
        <a href="conta">Contas</a>
        <a href="investimento">Investimentos</a>
        <a href="logout">Sair</a>
    </div>
</nav>

<div class="container">
    <!-- Cards de Resumo -->
    <div class="cards-container">
        <div class="card">
            <div class="card-header">
                <span class="card-icon">🏦</span>
                <span class="card-title">Total em Contas</span>
            </div>
            <div class="card-value">
                <fmt:formatNumber value="${totalContas}" type="currency" currencySymbol="R$ " />
            </div>
        </div>

        <div class="card">
            <div class="card-header">
                <span class="card-icon">📈</span>
                <span class="card-title">Total Investido</span>
            </div>
            <div class="card-value">
                <fmt:formatNumber value="${totalInvestimentos}" type="currency" currencySymbol="R$ " />
            </div>
        </div>

        <div class="card total-patrimonio">
            <div class="card-header">
                <span class="card-icon">💎</span>
                <span class="card-title">Patrimônio Total</span>
            </div>
            <div class="card-value">
                <fmt:formatNumber value="${patrimonioTotal}" type="currency" currencySymbol="R$ " />
            </div>
        </div>
    </div>

    <!-- Menu de Navegação -->
    <div class="navigation-menu">
        <a href="conta" class="btn btn-primary">Gerenciar Contas</a>
        <a href="investimento" class="btn btn-success">Gerenciar Investimentos</a>
    </div>

    <!-- Seção de Contas -->
    <div class="section">
        <h2 class="section-title">📋 Suas Contas</h2>

        <c:choose>
            <c:when test="${not empty contas}">
                <table class="table">
                    <thead>
                    <tr>
                        <th>Número</th>
                        <th>Tipo</th>
                        <th>Saldo</th>
                        <th>Informações Extras</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="conta" items="${contas}">
                        <tr>
                            <td>${conta.numeroConta}</td>
                            <td>
                                <c:choose>
                                    <c:when test="${conta.class.simpleName == 'ContaCorrente'}">
                                        🏦 Conta Corrente
                                    </c:when>
                                    <c:when test="${conta.class.simpleName == 'ContaPoupanca'}">
                                        🐷 Poupança
                                    </c:when>
                                    <c:otherwise>
                                        📊 Conta Comum
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td>
                                <fmt:formatNumber value="${conta.saldo}" type="currency" currencySymbol="R$ " />
                            </td>
                            <td>
                                <c:choose>
                                    <c:when test="${conta.class.simpleName == 'ContaCorrente'}">
                                        Limite: <fmt:formatNumber value="${conta.limite}" type="currency" currencySymbol="R$ " />
                                    </c:when>
                                    <c:when test="${conta.class.simpleName == 'ContaPoupanca'}">
                                        Taxa: <fmt:formatNumber value="${conta.taxaJuros}" type="percent" minFractionDigits="2" maxFractionDigits="2" />
                                    </c:when>
                                    <c:otherwise>
                                        -
                                    </c:otherwise>
                                </c:choose>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </c:when>
            <c:otherwise>
                <div class="empty-state">
                    <div class="icon">🏦</div>
                    <p>Nenhuma conta encontrada. <a href="conta">Crie sua primeira conta</a>!</p>
                </div>
            </c:otherwise>
        </c:choose>
    </div>

    <!-- Seção de Investimentos -->
    <div class="section">
        <h2 class="section-title">💹 Seus Investimentos</h2>

        <c:choose>
            <c:when test="${not empty investimentos}">
                <table class="table">
                    <thead>
                    <tr>
                        <th>Código</th>
                        <th>Tipo</th>
                        <th>Valor Aplicado</th>
                        <th>Taxa (%)</th>
                        <th>Rendimento (30 dias)</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="investimento" items="${investimentos}">
                        <tr>
                            <td>${investimento.codigo}</td>
                            <td>
                                <c:choose>
                                    <c:when test="${investimento.class.simpleName == 'Cdb'}">
                                        🏛️ CDB
                                    </c:when>
                                    <c:when test="${investimento.class.simpleName == 'TesouroDireto'}">
                                        🇧🇷 Tesouro Direto
                                    </c:when>
                                    <c:when test="${investimento.class.simpleName == 'Cdi'}">
                                        📊 CDI
                                    </c:when>
                                    <c:when test="${investimento.class.simpleName == 'Bolsa'}">
                                        📈 Bolsa
                                    </c:when>
                                    <c:otherwise>
                                        💰 Investimento
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td>
                                <fmt:formatNumber value="${investimento.valorAplicado}" type="currency" currencySymbol="R$ " />
                            </td>
                            <td>
                                <c:choose>
                                    <c:when test="${investimento.class.simpleName == 'Cdb'}">
                                        <fmt:formatNumber value="${investimento.taxaAnual}" type="number" minFractionDigits="2" maxFractionDigits="2" />%
                                    </c:when>
                                    <c:when test="${investimento.class.simpleName == 'TesouroDireto'}">
                                        <fmt:formatNumber value="${investimento.taxaAnual}" type="number" minFractionDigits="2" maxFractionDigits="2" />%
                                    </c:when>
                                    <c:when test="${investimento.class.simpleName == 'Cdi'}">
                                        <fmt:formatNumber value="${investimento.taxaAnual}" type="number" minFractionDigits="2" maxFractionDigits="2" />%
                                    </c:when>
                                    <c:when test="${investimento.class.simpleName == 'Bolsa'}">
                                        <fmt:formatNumber value="${investimento.taxaMediaAnual}" type="number" minFractionDigits="2" maxFractionDigits="2" />%
                                    </c:when>
                                </c:choose>
                            </td>
                            <td>
                                <fmt:formatNumber value="${investimento.calcularResgate(30)}" type="currency" currencySymbol="R$ " />
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </c:when>
            <c:otherwise>
                <div class="empty-state">
                    <div class="icon">📈</div>
                    <p>Nenhum investimento encontrado. <a href="investimento">Faça seu primeiro investimento</a>!</p>
                </div>
            </c:otherwise>
        </c:choose>
    </div>
</div>
</body>
</html>navbar {
background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
color: white;
padding: 1rem 2rem;
display: flex;
justify-content: space-between;
align-items: center;
box-shadow: 0 2px 4px rgba(0,0,0,0.1);
}

.navbar h1 {
font-size: 24px;
font-weight: 300;
}

.navbar .user-info {
display: flex;
align-items: center;
gap: 20px;
}

.navbar a {
color: white;
text-decoration: none;
padding: 8px 16px;
border-radius: 4px;
transition: background-color 0.3s;
}

.navbar a:hover {
background-color: rgba(255,255,255,0.1);
}

.container {
max-width: 1200px;
margin: 2rem auto;
padding: 0 1rem;
}

.cards-container {
display: grid;
grid-template-columns: repeat(auto-fit, minmax(280px, 1fr));
gap: 20px;
margin-bottom: 30px;
}

.card {
background: white;
border-radius: 10px;
padding: 24px;
box-shadow: 0 4px 6px rgba(0,0,0,0.1);
transition: transform 0.3s;
}

.card:hover {
transform: translateY(-2px);
}

.card-header {
display: flex;
align-items: center;
margin-bottom: 16px;
}

.card-icon {
font-size: 24px;
margin-right: 12px;
}

.card-title {
color: #555;
font-size: 16px;
font-weight: 500;
}

.card-value {
font-size: 28px;
font-weight: bold;
color: #333;
}

.card.total-patrimonio {
background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
color: white;
}

.card.total-patrimonio .card-title,
.card.total-patrimonio .card-value {
color: white;
}

.section {
background: white;
border-radius: 10px;
padding: 24px;
margin-bottom: 20px;
box-shadow: 0 4px 6px rgba(0,0,0,0.1);
}

.section-title {
color: #333;
margin-bottom: 20px;
padding-bottom: 10px;
border-bottom: 2px solid #eee;
}

.