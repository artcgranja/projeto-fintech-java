# 🚀 Instruções de Deploy - Sistema Fintech

## 📋 Pré-requisitos

1. **JDK 8 ou superior** instalado
2. **Apache Tomcat 9 ou superior**
3. **Oracle Database** (usando instância da FIAP)
4. **IntelliJ IDEA** (para desenvolvimento)

## 🗄️ Configuração do Banco de Dados

### 1. Conectar ao Oracle da FIAP

```sql
-- Configurações de conexão (já configuradas no ConnectionManager.java)
URL: jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL
USER: seu_rm (substitua pelo seu RM)
PASSWORD: sua_data_nascimento (formato: ddmmaaaa)
```

### 2. Executar Scripts SQL

Execute o arquivo `scripts_banco.sql` no SQL Developer ou similar:

```sql
-- O arquivo já contém:
-- ✅ Criação de todas as tabelas
-- ✅ Criação de sequências
-- ✅ Inserção de dados de teste
-- ✅ Usuários padrão para login
```

## ⚙️ Configuração do Projeto

### 1. Atualizar ConnectionManager

No arquivo `src/fintech/dao/ConnectionManager.java`, atualize:

```java
private static final String USER = "SEU_RM_AQUI";
private static final String PASSWORD = "SUA_DATA_NASCIMENTO";
```

### 2. Estrutura de Pastas

Certifique-se de que a estrutura esteja assim:

```
projeto-fintech/
├── src/
│   └── fintech/
│       ├── dao/
│       │   ├── ConnectionManager.java
│       │   ├── DAOFactory.java
│       │   ├── ContaDAO.java
│       │   ├── ClienteDAO.java
│       │   ├── InvestimentoDAO.java
│       │   └── UsuarioDAO.java
│       ├── model/
│       │   ├── Usuario.java
│       │   ├── Cliente.java
│       │   ├── Conta.java
│       │   ├── ContaCorrente.java
│       │   ├── ContaPoupanca.java
│       │   ├── cartao/
│       │   ├── investimento/
│       │   └── operacao/
│       ├── servlet/
│       │   ├── LoginServlet.java
│       │   ├── LogoutServlet.java
│       │   ├── DashboardServlet.java
│       │   ├── ContaServlet.java
│       │   └── InvestimentoServlet.java
│       ├── filter/
│       │   └── AuthenticationFilter.java
│       └── app/
├── web/
│   ├── WEB-INF/
│   │   └── web.xml
│   ├── login.jsp
│   ├── dashboard.jsp
│   ├── contas.jsp
│   ├── investimentos.jsp
│   └── error.jsp
└── lib/
    └── ojdbc8.jar (driver Oracle)
```

## 🏗️ Build e Deploy

### 1. No IntelliJ IDEA

1. **Configurar Tomcat:**
    - File → Project Structure → Artifacts
    - Add → Web Application: Exploded → From modules
    - Configure o output directory

2. **Adicionar Driver JDBC:**
    - Baixar `ojdbc8.jar` do site da Oracle
    - Adicionar em `lib/` do projeto
    - Add as Library no IntelliJ

3. **Configurar Run Configuration:**
    - Run → Edit Configurations
    - Add → Tomcat Server → Local
    - Configure o caminho do Tomcat
    - Deploy artifact criado

### 2. Export para ZIP

1. **Clique com botão direito** na raiz do projeto no IntelliJ
2. **Export to Zip File**
3. Escolha o local para salvar
4. **Verifique** se todos os arquivos estão incluídos

## 🔐 Usuários Padrão

O sistema vem com usuários pré-configurados:

| Login   | Senha    | Descrição    |
|---------|----------|--------------|
| admin   | 123456   | Administrador|
| usuario | senha123 | Usuário teste|

## 🚀 Funcionalidades Implementadas

### ✅ Autenticação
- [x] Login/Logout
- [x] Proteção de páginas (Filter)
- [x] Sessão de usuário

### ✅ Gestão de Contas
- [x] Criar conta (Comum, Corrente, Poupança)
- [x] Listar contas
- [x] Buscar conta por número
- [x] Realizar operações (Depósito/Saque)
- [x] Excluir conta

### ✅ Gestão de Investimentos
- [x] Criar investimento (CDB, Tesouro, CDI, Bolsa)
- [x] Listar investimentos
- [x] Buscar por código
- [x] Simular rendimentos
- [x] Excluir investimento

### ✅ Dashboard
- [x] Resumo financeiro
- [x] Totalizadores
- [x] Listagem de contas e investimentos

## 🧪 Testes

### Dados de Teste Incluídos

- **3 Clientes** cadastrados
- **3 Contas** (Corrente, Poupança, Comum)
- **4 Investimentos** (CDB, Tesouro, CDI, Bolsa)

### Cenários de Teste

1. **Login:** Tente fazer login com usuários válidos/inválidos
2. **Contas:** Crie conta, faça depósitos/saques
3. **Investimentos:** Crie investimentos, simule rendimentos
4. **Navegação:** Teste todos os links e botões

## 🔧 Troubleshooting

### Problemas Comuns

1. **Erro de Conexão BD:**
    - Verifique RM e data de nascimento
    - Teste conexão no SQL Developer

2. **Erro 404:**
    - Verifique se web.xml está correto
    - Confirme estrutura de pastas

3. **Erro de Compilação:**
    - Verifique se todas as libs estão incluídas
    - Confirme versão do Java

## 📞 Suporte

Em caso de dúvidas:
1. Verifique logs do Tomcat
2. Confirme configurações do banco
3. Teste cada funcionalidade individualmente

---