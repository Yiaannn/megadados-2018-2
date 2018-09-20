# Manual de Uso
## Projeto 1 Megadados

* Alexandre Young
* Paulo Tozzo

---

### Introdução

O projeto está disponível para usuários a partir de uma conexão HTTP ao servidor.
Como exemplo de projeto, esse servidor está localizado localmente, via
loopback, no entanto, a intenção do projeto é representar uma conexão real a um servidor externo

A proposta do projeto realizado é simular um database dos monstros do jogo "Monster Hunter World", a ideia é dispor um site onde usuários poderiam consultar as propriedades das diferentes criaturas do jogo, de forma que a maior parte das operações feitas são consultas do servidor. É disponível via um sistema de login também uma conta de Administrador que, por meio dela, é possível apagar entradas do database e adicionar entradas novas

---

### Uso

Uma vez conectado à página root do servidor (Manejado como um servidor apache com backend em java __rearticular isto__), uma página de Home estará disponível, de onde é possível acessar a conta de administrador ou diretamente consultar a lista de monstros disponíveis. Como mencionado anteriormente, as opções de edição do servidor está disponível apenas para o usuário Administrador, que para quesito de demonstração tem o par login:senha como admin:admin.

Aberta a lista de monstros, o usuário é redirecionado por meio de um método GET para o recurso "/Monsters" do servidor, listando o nome de todos os monstros disponíveis no servidor, cada um desses nomes é um link para o recurso específico do monstro, por exemplo, o nome Rathalos é um link para o recurso "/Monsters/Rathalos" por método GET

Quando em uma conta de Administrador, cada link para o recurso específico do monstro é acompanhado de um botão HTML que requisita uma ação DELETE para remover a entrada do monstro da lista. Uma caixa com um form html também se encontra à parte dos links onde é possível criar uma nova entrada para um monstro atrávez de uma ação POST.

Quando um novo monstro é criado, é possível acessar o seu recurso adicionado atráves da URL "/Monster/*nome-do-novo-monstro*", de onde é possível adicionar as partes específicas que compões o monstro e os atributos gerais por meio de forms html.

---

### Instalação

O servidor foi montado a partir de um ambiente "Dynamic Web Project" em Eclipse, onde foram importado o uso de diversos microsserviços por meio de edições do Build Path do projeto.

Para o projeto, foi utilizado um servidor apache de deployment "apache-tomact-9.0.10" com a intenção de traduzir as requests puras em html como chamadas de funções em Java.

Para fazer a exibição das páginas HTML foram utilizados arquivo em jsp com implementação das taglibs standard disponíveis pelo Apache. JSPs são "receitas" de como gerar HTML estáticos a partir de inputs de tamanho variável

O Backend em sí foi escrito por meio de Servlets, que são um tipo de classe disponível por uma biblioteca (também disponível pelo apache) que lida diretamente com as requests e responses http que interage com um database em mySQL 8.0.

Para replicar o ambiente, é necessário o uso de uma versão recente do "Eclipse IDE for Java EE Developers" e inicializar o projeto como um "Dynamic Web Project". Uma instalação
do "apache-tomact-9.0.10" reconhecida pelo build path do Eclipse e inicializado como um servidor standalone em loopback também é necessário.

As bibliotecas de comunicação com o apache são disponibilizados entre os arquivos de instalação, os quais precisam ser adicionados ao Build Path do projeto, esses sãos os taglibs e o servlet.

Para a comunicação com o database foi importado para o projeto a biblioteca "mysql-connector-java-8.0.12" que corresponde a versão do servidor instalada

Para inicializar o database, dois scripts estão disponíveis, mhstartup.sql e mhpopulate.sql, que correspondentemente geram as tabelas vazias do servidor e populam ela com dados de exemplo e simulação. O script para popular o servidor também inicializa o usuário Administrador de exemplo admin:admin, é recomendado que esse script seja inicializado para testes pois a interface web deliberadamente não disponibiliza meios de inserção de novos usuários administradores.

A conexão do backend com o servidor SQL ocorrem no arquivo DAO.java, nesse arquivo é necessário editar na string de inicialização de conexão com o servidor os dados login e senha de acesso do servidor utilizado no environment.

Para finalizar, para fazer o sistema de login de uma forma REST, possibilitando o uso de um servidor Stateless, um cookie de autenticação é gerado ao realizar o login na página que é requesitado ao acessar qualquer recurso do servidor, de forma que é necessário habilitar o uso de cookies para usar os recursos de Administrador.
