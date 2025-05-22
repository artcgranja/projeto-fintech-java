<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gerenciar Investimentos - Fintech</title>
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

        .navbar {
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

        .navbar .nav-links {
            display: flex;
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

        .page-header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 30px;
        }

        .page-title {
            font-size: 28px;
            color: #333;
            font-weight: 300;
        }

        .btn {
            display: inline-block;
            padding: 10px 20px;
            border: none;
            border-radius: 5px;
            text-decoration: none;
            font-size: 14px;
            cursor: pointer;
            transition: all 0.3s;
            font-weight: 500;
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

        .btn-danger {
            background-color: #dc3545;
            color: white;
        }

        .btn-info {
            background-color: #17a2b8;
            color: white;
        }

        .btn-small {
            padding: 5px 10px;
            font-size: 12px;
        }

        .card {
            background: white;
            border-radius: 10px;
            padding: 24px;
            box-shadow: 0 4px 6px rgba(0,0,0,0.1);
            margin-bottom: 20px;
        }

        .form-grid {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(200px, 1fr));
            gap: 15px;
            margin-bottom: 20px;
        }

        .form-group {
            margin-bottom: 15px;
        }

        .form-group label {
            display: block;
            margin-bottom: 5px;
            color: #555;
            font-weight: 500;
        }

        .form-group input,
        .form-group select {
            width: 100%;
            padding: 10px;
            border: 2px solid #ddd;
            border-radius: 5px;
            font-size: 14px;
        }

        .form-group input:focus,
        .form-group select:focus {
            outline: none;
            border-color: #667eea;
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

        .alert {
            padding: 12px;
            margin-bottom: 20px;
            border-radius: 5px;
        }

        .alert-success {
            background-color: #d4edda;
            color: #155724;
            border: 1px solid #c3e6cb;
        }

        .alert-error {
            background-color: #f8d7da;
            color: #721c24;
            border: 1px solid #f5c6cb;
        }

        .tabs {
            display: flex;
            background-color: #f8f9fa;
            border-radius: 5px;
            margin-bottom: 20px;
        }

        .tab {
            flex: 1;
            padding: 15px;
            text-align: center;
            cursor: pointer;
            border: none;
            background: none;
            font-size: 14px;
            font-weight: 500;
            color: #666;
            transition: all 0.3s;
        }

        .tab.active {
            background-color: #667eea;
            color: white;
            border-radius: 5px;
        }

        .tab-content {
            display: none;
        }

        .tab-content.active {
            display: block;
        }

        .search-bar {
            display: flex;
            gap: 10px;
            margin-bottom: 20px;
        }

        .search-bar input {
            flex: 1;
            padding: 10px;
            border: 2px solid #ddd;
            border-radius: 5px;
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

        .simulation-result {
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            color: white;
            border-radius: 10px;
            padding: 20px;
            margin-top: 20px;
        }

        .simulation-grid {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(150px, 1fr));
            gap: 15px;
            margin-top: 15px;
        }

        .simulation-item {
            text-align: center;
        }

        .simulation-label {
            font-size: 14px;
            opacity: 0.8;
            margin-bottom: 5px;
        }

        .simulation-value {
            font-size: 18px;
            font-weight: bold;
        }
    </style>
</head>
<body>
<nav class="navbar">
    <h1>💰 Fintech</h1>
    <div class="nav-links">
        <a href="dashboard">Dashboard</a>
        <a href="conta">Contas</a>
        <a href="investimento">Investimentos</a>
        <a href="logout">Sair</a>
    </div>
</nav>

<div class="container">
    <div class="page-header">
        <h1 class="page-title">📈 Gerenciar Investimentos</h1>
        <a href="dashboard" class="btn btn-primary">← Voltar ao Dashboard</a>
    </div>

    <!-- Mensagens de Feedback -->
    <c:if test="${not empty sucesso}">
        <div class="alert alert-success">
                ${sucesso}
        </div>
    </c:if>

    <c:if test="${not empty erro}">
        <div class="alert alert-error">
                ${erro}
        </div>
    </c:if>

    <!-- Abas -->
    <div class="tabs">
        <button class="tab active" onclick="showTab('listar')">📋 Listar Investimentos</button>
        <button class="tab" onclick="showTab('criar')">➕ Novo Investimento</button>
        <button class="tab" onclick="showTab('simular')">🧮 Simular Rendimento</button>
    </div>

    <!-- Aba: Listar Investimentos -->
    <div id="listar" class="tab-content active">
        <div class="card">
            <h3>🔍 Buscar Investimento</h3>
            <div class="search-bar">
                <form method="get" action="investimento" style="display: flex; gap: 10px; width: 100%;">
                    <input type="hidden" name="action" value="buscar">
                    <input type="text" name="codigo" placeholder="Digite o código do investimento"
                           value="${param.codigo}">
                    <button type="submit" class="btn btn-primary">Buscar</button>
                    <a href="investimento?action=listar" class="btn btn-primary">Listar Todos</a>
                </form>
            </div>
        </div>

        <div class="card">
            <h3>📋 Lista de Investimentos</h3>

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
                            <th>Ações</th>
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
                                <td>
                                    <a href="investimento?action=excluir&codigo=${investimento.codigo}"
                                       class="btn btn-danger btn-small"
                                       onclick="return confirm('Tem certeza que deseja excluir este investimento?')">
                                        🗑️ Excluir
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </c:when>
                <c:otherwise>
                    <div class="empty-state">
                        <div class="icon">📈</div>
                        <p>Nenhum investimento encontrado.</p>
                    </div>
                </c:otherwise>
            </c:choose>
        </div>
    </div>

    <!-- Aba: Criar Investimento -->
    <div id="criar" class="tab-content">
        <div class="card">
            <h3>➕ Criar Novo Investimento</h3>

            <form method="post" action="investimento">
                <input type="hidden" name="action" value="criar">

                <div class="form-grid">
                    <div class="form-group">
                        <label for="codigo">Código do Investimento:</label>
                        <input type="text" id="codigo" name="codigo" required
                               placeholder="Ex: CDB001, TD002, etc.">
                    </div>

                    <div class="form-group">
                        <label for="valorAplicado">Valor Aplicado (R$):</label>
                        <input type="number" id="valorAplicado" name="valorAplicado"
                               step="0.01" min="1" required>
                    </div>

                    <div class="form-group">
                        <label for="tipoInvestimento">Tipo de Investimento:</label>
                        <select id="tipoInvestimento" name="tipoInvestimento" required>
                            <option value="">Selecione...</option>
                            <option value="CDB">🏛️ CDB - Certificado de Depósito Bancário</option>
                            <option value="TESOURO_DIRETO">🇧🇷 Tesouro Direto</option>
                            <option value="CDI">📊 CDI - Certificado de Depósito Interbancário</option>
                            <option value="BOLSA">📈 Bolsa de Valores</option>
                        </select>
                    </div>

                    <div class="form-group">
                        <label for="taxa">Taxa Anual (%):</label>
                        <input type="number" id="taxa" name="taxa"
                               step="0.01" min="0" required
                               placeholder="Ex: 8.5 para 8,5% ao ano">
                    </div>
                </div>

                <button type="submit" class="btn btn-success">💾 Criar Investimento</button>
            </form>
        </div>
    </div>

    <!-- Aba: Simular Rendimento -->
    <div id="simular" class="tab-content">
        <div class="card">
            <h3>🧮 Simular Rendimento</h3>

            <form method="get" action="investimento">
                <input type="hidden" name="action" value="simular">

                <div class="form-grid">
                    <div class="form-group">
                        <label for="codigoSimulacao">Código do Investimento:</label>
                        <input type="text" id="codigoSimulacao" name="codigo" required
                               placeholder="Digite o código do investimento">
                    </div>

                    <div class="form-group">
                        <label for="dias">Período (dias):</label>
                        <input type="number" id="dias" name="dias"
                               min="1" max="3650" required
                               placeholder="Ex: 30, 365, etc.">
                    </div>
                </div>

                <button type="submit" class="btn btn-info">📊 Simular Rendimento</button>
            </form>

            <!-- Resultado da Simulação -->
            <c:if test="${not empty valorResgate}">
                <div class="simulation-result">
                    <h4>📊 Resultado da Simulação</h4>
                    <div class="simulation-grid">
                        <div class="simulation-item">
                            <div class="simulation-label">Investimento</div>
                            <div class="simulation-value">${investimento.codigo}</div>
                        </div>
                        <div class="simulation-item">
                            <div class="simulation-label">Período</div>
                            <div class="simulation-value">${diasSimulacao} dias</div>
                        </div>
                        <div class="simulation-item">
                            <div class="simulation-label">Valor Inicial</div>
                            <div class="simulation-value">
                                <fmt:formatNumber value="${investimento.valorAplicado}" type="currency" currencySymbol="R$ " />
                            </div>
                        </div>
                        <div class="simulation-item">
                            <div class="simulation-label">Valor de Resgate</div>
                            <div class="simulation-value">
                                <fmt:formatNumber value="${valorResgate}" type="currency" currencySymbol="R$ " />
                            </div>
                        </div>
                        <div class="simulation-item">
                            <div class="simulation-label">Rendimento</div>
                            <div class="simulation-value">
                                <fmt:formatNumber value="${rendimento}" type="currency" currencySymbol="R$ " />
                            </div>
                        </div>
                        <div class="simulation-item">
                            <div class="simulation-label">Rentabilidade</div>
                            <div class="simulation-value">
                                <fmt:formatNumber value="${percentualRendimento}" type="number" minFractionDigits="2" maxFractionDigits="2" />%
                            </div>
                        </div>
                    </div>
                </div>
            </c:if>
        </div>
    </div>
</div>

<script>
    function showTab(tabName) {
        // Esconde todas as abas
        const tabs = document.querySelectorAll('.tab-content');
        tabs.forEach(tab => tab.classList.remove('active'));

        // Remove classe active de todas as tab buttons
        const tabButtons = document.querySelectorAll('.tab');
        tabButtons.forEach(button => button.classList.remove('active'));

        // Mostra a aba selecionada
        document.getElementById(tabName).classList.add('active');

        // Adiciona classe active ao botão clicado
        event.target.classList.add('active');
    }
</script>
</body>
</html>