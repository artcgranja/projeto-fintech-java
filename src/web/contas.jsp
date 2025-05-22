<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gerenciar Contas - Fintech</title>
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
        <h1 class="page-title">🏦 Gerenciar Contas</h1>
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
        <button class="tab active" onclick="showTab('listar')">📋 Listar Contas</button>
        <button class="tab" onclick="showTab('criar')">➕ Nova Conta</button>
        <button class="tab" onclick="showTab('operacoes')">💰 Operações</button>
    </div>

    <!-- Aba: Listar Contas -->
    <div id="listar" class="tab-content active">
        <div class="card">
            <h3>🔍 Buscar Conta</h3>
            <div class="search-bar">
                <form method="get" action="conta" style="display: flex; gap: 10px; width: 100%;">
                    <input type="hidden" name="action" value="buscar">
                    <input type="number" name="numero" placeholder="Digite o número da conta"
                           value="${param.numero}">
                    <button type="submit" class="btn btn-primary">Buscar</button>
                    <a href="conta?action=listar" class="btn btn-primary">Listar Todas</a>
                </form>
            </div>
        </div>

        <div class="card">
            <h3>📋 Lista de Contas</h3>

            <c:choose>
                <c:when test="${not empty contas}">
                    <table class="table">
                        <thead>
                        <tr>
                            <th>Número</th>
                            <th>Tipo</th>
                            <th>Saldo</th>
                            <th>Informações Extras</th>
                            <th>Ações</th>
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
                                <td>
                                    <a href="conta?action=excluir&numero=${conta.numeroConta}"
                                       class="btn btn-danger btn-small"
                                       onclick="return confirm('Tem certeza que deseja excluir esta conta?')">
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
                        <div class="icon">🏦</div>
                        <p>Nenhuma conta encontrada.</p>
                    </div>
                </c:otherwise>
            </c:choose>
        </div>
    </div>

    <!-- Aba: Criar Conta -->
    <div id="criar" class="tab-content">
        <div class="card">
            <h3>➕ Criar Nova Conta</h3>

            <form method="post" action="conta">
                <input type="hidden" name="action" value="criar">

                <div class="form-grid">
                    <div class="form-group">
                        <label for="numero">Número da Conta:</label>
                        <input type="number" id="numero" name="numero" required>
                    </div>

                    <div class="form-group">
                        <label for="saldoInicial">Saldo Inicial:</label>
                        <input type="number" id="saldoInicial" name="saldoInicial"
                               step="0.01" min="0" required>
                    </div>

                    <div class="form-group">
                        <label for="tipoConta">Tipo de Conta:</label>
                        <select id="tipoConta" name="tipoConta" required onchange="toggleExtraFields()">
                            <option value="">Selecione...</option>
                            <option value="COMUM">Conta Comum</option>
                            <option value="CORRENTE">Conta Corrente</option>
                            <option value="POUPANCA">Poupança</option>
                        </select>
                    </div>
                </div>

                <div id="extraFields" style="display: none;">
                    <div class="form-grid">
                        <div class="form-group" id="limiteField" style="display: none;">
                            <label for="limite">Limite (R$):</label>
                            <input type="number" id="limite" name="limite" step="0.01" min="0">
                        </div>

                        <div class="form-group" id="taxaJurosField" style="display: none;">
                            <label for="taxaJuros">Taxa de Juros (%):</label>
                            <input type="number" id="taxaJuros" name="taxaJuros" step="0.01" min="0">
                        </div>
                    </div>
                </div>

                <button type="submit" class="btn btn-success">💾 Criar Conta</button>
            </form>
        </div>
    </div>

    <!-- Aba: Operações -->
    <div id="operacoes" class="tab-content">
        <div class="card">
            <h3>💰 Realizar Operação</h3>

            <form method="post" action="conta">
                <input type="hidden" name="action" value="operacao">

                <div class="form-grid">
                    <div class="form-group">
                        <label for="numeroConta">Número da Conta:</label>
                        <input type="number" id="numeroConta" name="numeroConta" required>
                    </div>

                    <div class="form-group">
                        <label for="tipoOperacao">Tipo de Operação:</label>
                        <select id="tipoOperacao" name="tipoOperacao" required>
                            <option value="">Selecione...</option>
                            <option value="DEPOSITO">💵 Depósito</option>
                            <option value="SAQUE">💸 Saque</option>
                        </select>
                    </div>

                    <div class="form-group">
                        <label for="valor">Valor (R$):</label>
                        <input type="number" id="valor" name="valor" step="0.01" min="0.01" required>
                    </div>
                </div>

                <button type="submit" class="btn btn-success">✓ Executar Operação</button>
            </form>
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

    function toggleExtraFields() {
        const tipoConta = document.getElementById('tipoConta').value;
        const extraFields = document.getElementById('extraFields');
        const limiteField = document.getElementById('limiteField');
        const taxaJurosField = document.getElementById('taxaJurosField');

        if (tipoConta === 'CORRENTE') {
            extraFields.style.display = 'block';
            limiteField.style.display = 'block';
            taxaJurosField.style.display = 'none';
            document.getElementById('limite').required = true;
            document.getElementById('taxaJuros').required = false;
        } else if (tipoConta === 'POUPANCA') {
            extraFields.style.display = 'block';
            limiteField.style.display = 'none';
            taxaJurosField.style.display = 'block';
            document.getElementById('limite').required = false;
            document.getElementById('taxaJuros').required = true;
        } else {
            extraFields.style.display = 'none';
            document.getElementById('limite').required = false;
            document.getElementById('taxaJuros').required = false;
        }
    }
</script>
</body>
</html>