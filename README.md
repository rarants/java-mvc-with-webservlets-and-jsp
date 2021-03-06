# Programação Orientada a Objetos da Web I
Trabalho desenvolvido durante a disciplina de Programação Orientada a Objetos da Web I
Implementação de sistema de loja: CRUD de produtos e clientes com usuários, permissões e sessões 

> Utilizando-se o [IntelliJ IDEA](https://www.jetbrains.com/pt-br/idea/) para implementação e o [Maven](https://mvnrepository.com/) 
> para lidar com versões das ferramentas, bibliotecas e frameworks.

## :zap: Etapas da elaboração :zap: 
### 🔸 Primeira etapa - Inicialização do projeto 🔸
1. Inicialização do projeto maven na IDE
2. Download do servlet WildFly
3. Descompactação do arquivo baixado
4. Edição das configurações do projeto adicionando o JBoss local com o path (caminho) do arquivo descompactado
5. Correção de bugs caso existam (utilizando as recomendações da IDE)
6. Aplicação das configurações
7. Inicialização do servidor
<hr />

### 🔸 Segunda etapa - Construção do MVC + DAO + Service (Model, View, Controller e DAO) 🔸
1. Em src > main > java, realizar a criação de um pacote identificador do projeto (ex: br.rarantes.si.poowi)
2. No pacote criado, adicionar os pacotes: Model, Controller, DAO e Service

> Note que o pacote model irá abrigar o modelo das tabelas do banco relacional a serem utilizadas pela aplicação; 
> 
> Controller será a camada intermediária entre a View (camada de visualização, parte visual da aplicação) e 
> 
>  A camada DAO (responsável pela comunicação com o banco de dados. 
>  
>  Service será o pacote de armazenamento dos serviços da aplicação, 
> como, por exemplo, a verificação se um usuário está autenticado e tem permissão para acessar a informação solicitada.

3. Em model, crie os modelos de permissão e usuário com os campos necessários (nome, email, senha, data de criação da conta, status de atividade, etc). 
(posteriormente essa etapa será complementada)
4. Em webapp > WEB-INF > home, adicione o arquivo login.jsp e escreva uma página de login por meio de HTML e CSS (opcional).
> Adicione as seguintes notações ao topo do arquivo; 
```
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> <!-- garantir que a página receba as informações necessárias para a taglib. O prefixo pode ser alterado de acordo com sua preferência. -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %> <!-- padrão -->
<%@page isELIgnored="false" %> <!-- garantir que a página receba as informações necessárias e seja redirecionada corretamente -->
```
5. Ainda em login, adicione a taglib dentro do html para poder exibir uma mensagem de erro que será recebida pelo controller.
> Exemplo: 
```
<c:if test="${not empty error}"> <!-- error será o atributo que iremos criar no controller para retornar o erro, enquanto que o not empty estará testando se o atributo estará vazio -->
    <div class="container center">
        <h2 class="child error"> <!-- error, neste caso, se refere à classe css e não ao atributo java -->
            <b>${error}</b>
        </h2>
    </div>
</c:if>
 ```
6. Busque no repositório maven (MVN Repository) pelo webservlet e adicione a dependência ao seu arquivo pom.xml;
7. Agora em controller, crie o controller index com a notação @WebServlet e informe a rota padrão ("/");
8. Na classe IndexController que estenderá HttpServlet, sobrescreva o método doGet e redirecione a rota para a página de login utilizando o RequestDispatcher
```
RequestDispatcher rd = request.getRequestDispatcher(uri); // onde request é o parâmetro recebido pelo método doGet e uri será a uri de redirecionamento (EX: "/WEB-INF/home/dashboard.jsp")
rd.forward(request, response); // forward realiza, de fato, o redirecionamento. Response também é um parâmetro recebido pelo método.
```
8. Feito o controller index, para redirecionar o projeto ao iniciar para a página de login, vamos realizar o login de fato. Para tanto, crie o 
controller de login;
9. No LoginController, sobrescreva o método doPost nele será verificado se o usuário X existe ou não. 
10. Para tanto, você precisará criar um serviço para verificar se aquele usuário está autenticado ou não. Logo, crie o o UsuarioService
11. Nele, será chamada a classe DAO de usuário para a busca de suas credenciais e, caso estejam corretas, retornará o usuário para o método doGet do 
LoginController;
12. Para isso, você precisará adicionar informações de usuário na classe DAO. Inicialmente, você pode criar um usuário manualmente para testar e, 
posteriormente adicionar a busca ao banco de dados.
> Assim, crie o método getUsuario na classe UsuarioDAO para buscar usuarios na sua base, que receba por parâmetro o email informado pelo jsp. 
> Nesse método, adicione um usuário para testar e, caso o email 
> recebido por parâmetro corresponda a algum em sua base, retorne o usuário. Do contrário, retorne null.
```
  Usuario user = new Usuario();
  user.setEmail("email@mail.com");
  user.sestSenha("12356");
```
13. Assim, no service UsuarioService, chame no método de autenticação (nesse caso, chamado de autenticado(String email, String senha)) o método 
getUsuario da classe DAO;
14. Se a senha e o email corresponderem, retorne o usuário encontrado para o controller LoginController.
15. Agora com as informações recebidas, no controller de login, você pode retornar, caso o usuário foi encontrado, os dados do usuário e chamar uma 
página privada (neste caso, dashboard.jsp) ou uma mensagem de erro
> A mensagem de erro poderá ser enviada por meio de um atributo, como comentado anteriormente. Isso poderá ser feito da seguinte maneira: 
`` request.setAttribute("error", "Usuário e/ou senha incorretos!"); ``
> (passando o nome do atributo seguido da mensagem de erro).

> Use ``HttpSession session = request.getSession();`` para adicionar a sessão ao login do usuário. Use ``request.getSession().invalidate();`` para encerrar 
a sessão. 
> Assim, você bloqueará que usuários sem acesso possam acessar páginas privadas ao retornar à url privada sem login.
<hr/>

### 🔸 Terceira etapa - Conexão com o banco de dados Postgresql + cadastro de usuários  🔸

1. Agora é o momento de adicionar a conexão com o banco de dados. Novamente, vá ao mvn repository e busque pela dependência do postgresql;
2. Agora, crie a base de dados por meio do postgresql, adicionando as tabelas referentes aos models criados anteriormente;
```
  CREATE TABLE permissao (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(50) NOT NULL
  );
  CREATE TABLE usuario (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(50) NOT NULL,
    email VARCHAR(50) NOT NULL,
    senha VARCHAR(50) NOT NULL,
    ativo BOOLEAN NOT NULL DEFAULT TRUE,
    data_cadastro DATE DEFAULT CURRENT_DATE
  );
  CREATE TABLE permissao_usuario (
    id SERIAL PRIMARY KEY,
    id_usuario INTEGER NOT NULL,
    id_permissao INTEGER NOT NULL,
    FOREIGN KEY (id_usuario) REFERENCES usuario (id),
    FOREIGN KEY (id_permissao) REFERENCES permissao (id)
  );
```
3. Feito isso, teste sua conexão com o banco de dados;
4. Agora, crie um pacote conection e adicione uma classe de conexão ao banco de dados;
5. Para melhor prática, crie variáveis estáticas para armazenar as informações de driver, url, user e password, que serão usadas para conexão;
> Driver: org.postgresql.Driver
> Url: jdbc:postgresql://localhost:5433/poowi // neste caso, localhost se refere ao host, 5433 à porta em que o banco está rodando e poowi ao database
> User e password podem variar para o seu banco. Por padrão o user será postgres e a senha será aquela definida na instalação/configuração
6. Nessa classe, crie um método para buscar conexão, getConnection. Nela, adicione o driver e busque a conexão.
```
  Class.forName(DRIVER);
  return DriverManager.getConnection(URL, USER, PASSWORD);
```
7. Altere o método getUsuario em UsuarioDAO, adicionando as interações com o banco seguindo o JDBC.
8. Ainda na classe DAO, adicione o método postUsuario para realizar o cadastro do usuário, seguindo o JDBC.
9. Agora basta criar a página de cadastro, alterar a de login para possibilitar a navegação à de cadastro, adicionar um controller para redirecionamento 
e cadastro de usuário (que chamará a dao).
10. Faça os redirecionamentos e retornos de erros e o cadastro estará finalizado.
<hr />

### 🔸 Próximas etapas - "Mais do mesmo"  🔸
1. Siga a lógica implementada nas etapas anteriores para finalizar as funcionalidades que estão faltando na aplicação :)
