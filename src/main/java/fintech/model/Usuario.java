package fintech.model;

/**
 * Representa um usuário do sistema para autenticação.
 */
public class Usuario {
    private String login;
    private String senha;
    private String nome;
    private String email;

    /**
     * Construtor completo do usuário.
     *
     * @param login login do usuário
     * @param senha senha do usuário
     * @param nome nome completo do usuário
     * @param email email do usuário
     */
    public Usuario(String login, String senha, String nome, String email) {
        this.login = login;
        this.senha = senha;
        this.nome = nome;
        this.email = email;
    }

    /**
     * Construtor para autenticação (sem nome e email).
     *
     * @param login login do usuário
     * @param senha senha do usuário
     */
    public Usuario(String login, String senha) {
        this.login = login;
        this.senha = senha;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}