package fintech.servlet;

import fintech.dao.DAOFactory;
import fintech.dao.ContaDAO;
import fintech.dao.InvestimentoDAO;
import fintech.model.Conta;
import fintech.model.Usuario;
import fintech.model.investimento.Investimento;

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
 * Servlet para a página principal (dashboard) do sistema.
 */
@WebServlet("/dashboard")
public class DashboardServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Verifica se o usuário está logado
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("usuarioLogado") == null) {
            response.sendRedirect("login");
            return;
        }

        Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");

        try {
            // Carrega dados para o dashboard
            ContaDAO contaDAO = DAOFactory.createContaDAO();
            InvestimentoDAO investimentoDAO = DAOFactory.createInvestimentoDAO();

            List<Conta> contas = contaDAO.getAll();
            List<Investimento> investimentos = investimentoDAO.getAll();

            // Calcula estatísticas
            double totalContas = contas.stream()
                    .mapToDouble(Conta::getSaldo)
                    .sum();

            double totalInvestimentos = investimentos.stream()
                    .mapToDouble(Investimento::getValorAplicado)
                    .sum();

            double patrimonioTotal = totalContas + totalInvestimentos;

            // Adiciona atributos para a JSP
            request.setAttribute("usuario", usuario);
            request.setAttribute("contas", contas);
            request.setAttribute("investimentos", investimentos);
            request.setAttribute("totalContas", totalContas);
            request.setAttribute("totalInvestimentos", totalInvestimentos);
            request.setAttribute("patrimonioTotal", patrimonioTotal);

            request.getRequestDispatcher("dashboard.jsp").forward(request, response);

        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("erro", "Erro ao carregar dados do dashboard.");
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}