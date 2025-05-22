package fintech.servlet;

import fintech.dao.DAOFactory;
import fintech.dao.ContaDAO;
import fintech.model.Conta;
import fintech.model.ContaCorrente;
import fintech.model.ContaPoupanca;
import fintech.model.Usuario;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Servlet para gerenciamento de contas.
 */
@WebServlet("/conta")
public class ContaServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Verifica autenticação
        if (!isUserAuthenticated(request, response)) return;

        String action = request.getParameter("action");

        try {
            ContaDAO contaDAO = DAOFactory.createContaDAO();

            if ("listar".equals(action) || action == null) {
                listarContas(request, response, contaDAO);
            } else if ("buscar".equals(action)) {
                buscarConta(request, response, contaDAO);
            } else if ("excluir".equals(action)) {
                excluirConta(request, response, contaDAO);
            } else {
                response.sendRedirect("conta?action=listar");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("erro", "Erro ao processar operação: " + e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Verifica autenticação
        if (!isUserAuthenticated(request, response)) return;

        String action = request.getParameter("action");

        try {
            ContaDAO contaDAO = DAOFactory.createContaDAO();

            if ("criar".equals(action)) {
                criarConta(request, response, contaDAO);
            } else if ("operacao".equals(action)) {
                realizarOperacao(request, response, contaDAO);
            } else {
                response.sendRedirect("conta?action=listar");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("erro", "Erro ao processar operação: " + e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }

    private void listarContas(HttpServletRequest request, HttpServletResponse response, ContaDAO contaDAO)
            throws ServletException, IOException, SQLException {

        List<Conta> contas = contaDAO.getAll();
        request.setAttribute("contas", contas);
        request.getRequestDispatcher("contas.jsp").forward(request, response);
    }

    private void buscarConta(HttpServletRequest request, HttpServletResponse response, ContaDAO contaDAO)
            throws ServletException, IOException, SQLException {

        String numeroStr = request.getParameter("numero");
        if (numeroStr != null && !numeroStr.trim().isEmpty()) {
            try {
                int numero = Integer.parseInt(numeroStr.trim());
                Conta conta = contaDAO.getByNumero(numero);

                if (conta != null) {
                    request.setAttribute("conta", conta);
                    request.setAttribute("sucesso", "Conta encontrada!");
                } else {
                    request.setAttribute("erro", "Conta não encontrada!");
                }
            } catch (NumberFormatException e) {
                request.setAttribute("erro", "Número de conta inválido!");
            }
        }

        // Lista todas as contas também
        List<Conta> contas = contaDAO.getAll();
        request.setAttribute("contas", contas);
        request.getRequestDispatcher("contas.jsp").forward(request, response);
    }

    private void criarConta(HttpServletRequest request, HttpServletResponse response, ContaDAO contaDAO)
            throws ServletException, IOException, SQLException {

        try {
            int numero = Integer.parseInt(request.getParameter("numero"));
            double saldoInicial = Double.parseDouble(request.getParameter("saldoInicial"));
            String tipoConta = request.getParameter("tipoConta");

            Conta conta;

            if ("CORRENTE".equals(tipoConta)) {
                double limite = Double.parseDouble(request.getParameter("limite"));
                conta = new ContaCorrente(numero, saldoInicial, limite);
            } else if ("POUPANCA".equals(tipoConta)) {
                double taxaJuros = Double.parseDouble(request.getParameter("taxaJuros"));
                conta = new ContaPoupanca(numero, saldoInicial, taxaJuros);
            } else {
                conta = new Conta(numero, saldoInicial);
            }

            contaDAO.insert(conta);
            request.setAttribute("sucesso", "Conta criada com sucesso!");

        } catch (NumberFormatException e) {
            request.setAttribute("erro", "Dados inválidos! Verifique os valores informados.");
        } catch (SQLException e) {
            if (e.getMessage().contains("unique constraint")) {
                request.setAttribute("erro", "Número de conta já existe!");
            } else {
                throw e;
            }
        }

        listarContas(request, response, contaDAO);
    }

    private void realizarOperacao(HttpServletRequest request, HttpServletResponse response, ContaDAO contaDAO)
            throws ServletException, IOException, SQLException {

        try {
            int numeroConta = Integer.parseInt(request.getParameter("numeroConta"));
            String tipoOperacao = request.getParameter("tipoOperacao");
            double valor = Double.parseDouble(request.getParameter("valor"));

            Conta conta = contaDAO.getByNumero(numeroConta);
            if (conta == null) {
                request.setAttribute("erro", "Conta não encontrada!");
                listarContas(request, response, contaDAO);
                return;
            }

            boolean sucesso = false;
            String mensagem = "";

            if ("DEPOSITO".equals(tipoOperacao)) {
                conta.depositar(valor);
                contaDAO.updateSaldo(numeroConta, conta.getSaldo());
                sucesso = true;
                mensagem = "Depósito realizado com sucesso!";
            } else if ("SAQUE".equals(tipoOperacao)) {
                if (conta.sacar(valor)) {
                    contaDAO.updateSaldo(numeroConta, conta.getSaldo());
                    sucesso = true;
                    mensagem = "Saque realizado com sucesso!";
                } else {
                    mensagem = "Saldo insuficiente para o saque!";
                }
            }

            if (sucesso) {
                request.setAttribute("sucesso", mensagem);
            } else {
                request.setAttribute("erro", mensagem);
            }

        } catch (NumberFormatException e) {
            request.setAttribute("erro", "Dados inválidos! Verifique os valores informados.");
        }

        listarContas(request, response, contaDAO);
    }

    private void excluirConta(HttpServletRequest request, HttpServletResponse response, ContaDAO contaDAO)
            throws ServletException, IOException, SQLException {

        String numeroStr = request.getParameter("numero");
        if (numeroStr != null) {
            try {
                int numero = Integer.parseInt(numeroStr);
                contaDAO.delete(numero);
                request.setAttribute("sucesso", "Conta excluída com sucesso!");
            } catch (NumberFormatException e) {
                request.setAttribute("erro", "Número de conta inválido!");
            }
        }

        listarContas(request, response, contaDAO);
    }

    private boolean isUserAuthenticated(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("usuarioLogado") == null) {
            response.sendRedirect("login");
            return false;
        }
        return true;
    }
}