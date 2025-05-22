package fintech.servlet;

import fintech.dao.DAOFactory;
import fintech.dao.UsuarioDAO;
import fintech.model.Usuario;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Servlet para autenticação de usuários.
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Exibe a página de login
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String login = request.getParameter("login");
        String senha = request.getParameter("senha");

        // Validação básica
        if (login == null || login.trim().isEmpty() ||
                senha == null || senha.trim().isEmpty()) {

            request.setAttribute("erro", "Login e senha são obrigatórios!");
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }

        try {
            UsuarioDAO usuarioDAO = DAOFactory.createUsuarioDAO();
            Usuario usuario = usuarioDAO.autenticar(login.trim(), senha.trim());

            if (usuario != null) {
                // Login bem-sucedido - criar sessão
                HttpSession session = request.getSession();
                session.setAttribute("usuarioLogado", usuario);
                session.setMaxInactiveInterval(1800); // 30 minutos

                // Redireciona para a página principal
                response.sendRedirect("dashboard");

            } else {
                // Falha na autenticação
                request.setAttribute("erro", "Login ou senha inválidos!");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("erro", "Erro interno do servidor. Tente novamente.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}