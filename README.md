# Spring-Security

<p>Spring Security é um projeto do ecossistema Spring que conta com uma poderosa estrutura personalizável para implementação de autenticação
e autorização em aplicações Java Spring.<p/>

<h3>Riscos e vulnerabilidades em aplicacoes web</h3>
<p>O site <a href="https://owasp.org/www-project-top-ten/">Top 10 OWASP</a> lista as dez prinicpais vulnerabilidades para aplicacoes web na atualidade.</p>
<ul>
  <li>Autenticacao e Autorizacao === uma falha e verificar APENAS se o usuario esta logado, e nao verificar se tem autorizacao de acessar determinados recursos;</li>
</ul>
<p>Retorno de erros de HTTP == 401 usuario nao logado e o 403, usuario logado mas nao tem accesso a recursos especificos.</p>

<h3>Iniciando o Spring Security</h3>
<p>Inserir a dependencia do Spring Security no pom.xml do projeto. Ao inicializar o projeto o prorio Spring Security já cria um password para acessar o projeto. No console de inicialização é possível localiza essa chave: <em>Using generated security password: 12a546f2-af4f-4d9d-8aa6-b7d185354e06
.</em> - este password modifica a cada reinicializacao do Spring.</p> Para acessar a aplicacao é necessario enviar o password e o usuario padrao do Spring Security <em>(user)</em> via <em>Basic Auth</em>.

<h3>Primeira Forma == Usando a classe WebSecurityConfigurerAdapter --- maneira mais antiga, porem existente em varios projeto.</h3>
<p>As configuracoes iniciais do Spring Security estao no pacore security dentro de configs.</p>
<p></p>

<h3>Protecao contra CSRF</h3>
<p>Por padrao o Spring Security traz habilitado protecao contra CSRF, onde metodos como POST e DELETE nao conseguem autenticar somente com <em>Basic Authentication</em>.</p>
 E necessario desabilitar essa protecao na calsse <em>WebSecurityConfig</em>.

<h3>Customizar autenticao em memoria</h3>
<p>Inicialmente sera feita uma autenticao em memoria na classe <em>WebSecurityConfig.</em></p>
<p>No momento de setar a senha nao e aceito uma senha em <em>String</em> e sim com um <em>passwordEnconder.</em></p>

<h3>Customizar autenticao com banco de dados e JPA</h3>
<p>E criado um UserModel que implementa <em>UserDetails</em> uma interface do Spring Security que traz alguns metodos ja definidos. </p>
<p>O segundo passo e a criacao do repository do UserModel.</p>
<p>Proximo passo e a criacao do servico dentro do pacote security. Este servico implementa <em>UserDetailsService</em> que posseu o metodo <em>loadUserByUserName</em> este medoto e onde implementamos nosso logica para buscar os dados no banco. </p>
<p>Por fim na classe principal do Spring Security a <em>WebSecurityConfig</em> o metodo configure deve ser ajustado para buscar um <em>UserDetailServiceImpl</em>.</p>

<h3>Controle de acesso</h3>

<p>No método <emph>configure<emph> da classe <emph>WebSecurityConfig</emph> é adicionado no builder os <emph>antMatechers</emph>, passando
o verbo HTTP, o caminho da url que terá a regra e por fim qual <emph>Role</emph> é permitida acessar esta url.</p>
<p>O exemplo acima sobre quais roles podem acessar foi descontinuado (não se utiliza mais a classe <emph>WebSecurityConfigurerAdapter</emph>), porém foi visto pois é muito utilizado em projetos antigos do <emph>Spring</emph>.</p>
<p>Os testes irao continuar criando uma classe chamada de <emph>WebSecurityConfigV2</emph> que usara o metodo <emph>filterChain</emph> para autenticar e autorizar as requisicoes.</p>

<p><strong>Outra forma de restringir o acesso:</strong> usando a anotacao <emph>@EnableGlobalMethodSecurity(prePostEnabled = true).</emph> As configurações serão feitas no controller. Em cada endpoint deve ser usada a anotacao <emph>@PreAuthorize("hasRole('ADMIN')")</emph> com a role que deve ter permissao.</p>

