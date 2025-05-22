package fintech.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Filtro para verificar autenticação do usuário.
 * Protege páginas que requerem login.
 */
@WebFilter(urlPatterns = {"/dashboard", "/conta", "/investimento"})
public class AuthenticationFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Inicialização do filtro (se necessário)
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        // Verifica se existe uma sessão válida
        HttpSession session = httpRequest.getSession(false);
        boolean isLoggedIn = (session != null) && (session.getAttribute("usuarioLogado") != null);

        if (isLoggedIn) {
            // Usuário autenticado, continua com a requisição
            chain.doFilter(request, response);
        } else {
            // Usuário não autenticado, redireciona para login
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/login");
        }
    }

    @Override
    public void destroy() {
        // Limpeza do filtro (se necessário)
    }
}