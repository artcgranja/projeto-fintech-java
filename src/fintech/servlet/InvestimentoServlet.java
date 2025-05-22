package fintech.servlet;

import fintech.dao.DAOFactory;
import fintech.dao.InvestimentoDAO;
import fintech.model.investimento.*;

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
 * Servlet para gerenciamento de investimentos.
 */
@WebServlet("/investimento")
public class InvestimentoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Verifica autenticação
        if (!isUserAuthenticated(request, response)) return;

        String action = request.getParameter("action");

        try {
            InvestimentoDAO investimentoDAO = DAOFactory.createInvestimentoDAO();

            if ("listar".equals(action) || action == null) {
                listarInvestimentos(request, response, investimentoDAO);
            } else if ("buscar".equals(action)) {
                buscarInvestimento(request, response, investimentoDAO);
            } else if ("excluir".equals(action)) {
                excluirInvestimento(request, response, investimentoDAO);
            } else if ("simular".equals(action)) {
                simularRendimento(request, response, investimentoDAO);
            } else {
                response.sendRedirect("investimento?action=listar");
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
            InvestimentoDAO investimentoDAO = DAOFactory.createInvestimentoDAO();

            if ("criar".equals(action)) {
                criarInvestimento(request, response, investimentoDAO);
            } else {
                response.sendRedirect("investimento?action=listar");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("erro", "Erro ao processar operação: " + e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }

    private void listarInvestimentos(HttpServletRequest request, HttpServletResponse response,
                                     InvestimentoDAO investimentoDAO)
            throws ServletException, IOException, SQLException {

        List<Investimento> investimentos = investimentoDAO.getAll();
        request.setAttribute("investimentos", investimentos);
        request.getRequestDispatcher("investimentos.jsp").forward(request, response);
    }

    private void buscarInvestimento(HttpServletRequest request, HttpServletResponse response,
                                    InvestimentoDAO investimentoDAO)
            throws ServletException, IOException, SQLException {

        String codigo = request.getParameter("codigo");
        if (codigo != null && !codigo.trim().isEmpty()) {
            Investimento investimento = investimentoDAO.getByCodigo(codigo.trim());

            if (investimento != null) {
                request.setAttribute("investimento", investimento);
                request.setAttribute("sucesso", "Investimento encontrado!");
            } else {
                request.setAttribute("erro", "Investimento não encontrado!");
            }
        }

        // Lista todos os investimentos também
        List<Investimento> investimentos = investimentoDAO.getAll();
        request.setAttribute("investimentos", investimentos);
        request.getRequestDispatcher("investimentos.jsp").forward(request, response);
    }

    private void criarInvestimento(HttpServletRequest request, HttpServletResponse response,
                                   InvestimentoDAO investimentoDAO)
            throws ServletException, IOException, SQLException {

        try {
            String codigo = request.getParameter("codigo");
            double valorAplicado = Double.parseDouble(request.getParameter("valorAplicado"));
            String tipoInvestimento = request.getParameter("tipoInvestimento");
            double taxa = Double.parseDouble(request.getParameter("taxa"));

            Investimento investimento;

            switch (tipoInvestimento) {
                case "CDB":
                    investimento = new Cdb(codigo, valorAplicado, taxa);
                    break;
                case "TESOURO_DIRETO":
                    investimento = new TesouroDireto(codigo, valorAplicado, taxa);
                    break;
                case "CDI":
                    investimento = new Cdi(codigo, valorAplicado, taxa);
                    break;
                case "BOLSA":
                    investimento = new Bolsa(codigo, valorAplicado, taxa);
                    break;
                default:
                    request.setAttribute("erro", "Tipo de investimento inválido!");
                    listarInvestimentos(request, response, investimentoDAO);
                    return;
            }

            investimentoDAO.insert(investimento);
            request.setAttribute("sucesso", "Investimento criado com sucesso!");

        } catch (NumberFormatException e) {
            request.setAttribute("erro", "Dados inválidos! Verifique os valores informados.");
        } catch (SQLException e) {
            if (e.getMessage().contains("unique constraint")) {
                request.setAttribute("erro", "Código de investimento já existe!");
            } else {
                throw e;
            }
        }

        listarInvestimentos(request, response, investimentoDAO);
    }

    private void excluirInvestimento(HttpServletRequest request, HttpServletResponse response,
                                     InvestimentoDAO investimentoDAO)
            throws ServletException, IOException, SQLException {

        String codigo = request.getParameter("codigo");
        if (codigo != null) {
            investimentoDAO.delete(codigo);
            request.setAttribute("sucesso", "Investimento excluído com sucesso!");
        }

        listarInvestimentos(request, response, investimentoDAO);
    }

    private void simularRendimento(HttpServletRequest request, HttpServletResponse response,
                                   InvestimentoDAO investimentoDAO)
            throws ServletException, IOException, SQLException {

        String codigo = request.getParameter("codigo");
        String diasStr = request.getParameter("dias");

        if (codigo != null && diasStr != null) {
            try {
                int dias = Integer.parseInt(diasStr);
                Investimento investimento = investimentoDAO.getByCodigo(codigo);

                if (investimento != null) {
                    double valorResgate = investimento.calcularResgate(dias);
                    double rendimento = valorResgate - investimento.getValorAplicado();
                    double percentualRendimento = (rendimento / investimento.getValorAplicado()) * 100;

                    request.setAttribute("investimento", investimento);
                    request.setAttribute("diasSimulacao", dias);
                    request.setAttribute("valorResgate", valorResgate);
                    request.setAttribute("rendimento", rendimento);
                    request.setAttribute("percentualRendimento", percentualRendimento);
                    request.setAttribute("sucesso", "Simulação realizada com sucesso!");
                } else {
                    request.setAttribute("erro", "Investimento não encontrado!");
                }
            } catch (NumberFormatException e) {
                request.setAttribute("erro", "Número de dias inválido!");
            }
        }

        // Lista todos os investimentos
        List<Investimento> investimentos = investimentoDAO.getAll();
        request.setAttribute("investimentos", investimentos);
        request.getRequestDispatcher("investimentos.jsp").forward(request, response);
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