package fintech.dao;

/**
 * Factory para criação de objetos DAO.
 */
public class DAOFactory {

    /**
     * Cria uma instância de ContaDAO.
     *
     * @return ContaDAO nova instância
     */
    public static ContaDAO createContaDAO() {
        return new ContaDAO();
    }

    /**
     * Cria uma instância de ClienteDAO.
     *
     * @return ClienteDAO nova instância
     */
    public static ClienteDAO createClienteDAO() {
        return new ClienteDAO();
    }

    /**
     * Cria uma instância de InvestimentoDAO.
     *
     * @return InvestimentoDAO nova instância
     */
    public static InvestimentoDAO createInvestimentoDAO() {
        return new InvestimentoDAO();
    }

    /**
     * Cria uma instância de UsuarioDAO.
     *
     * @return UsuarioDAO nova instância
     */
    public static UsuarioDAO createUsuarioDAO() {
        return new UsuarioDAO();
    }
}