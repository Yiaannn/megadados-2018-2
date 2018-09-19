# Manual de Verbos - REST
## Projeto 1 Megadados

* Alexandre Young
* Paulo Tozzo
---
/ (Home) - Página inicial de acesso ao servidor do database

  GET - recebe o html de visualização

  POST - fazer logout da conta administradora

---
/Admin (Painel de Login para Administrador) - Permite

---

/Monsters (Lista de Monstros) - Representa um índice dos recursos do servidor

  GET - recebe o html de visualização

  POST - Adciona um recurso novo vazio (apenas com label) ao índice __requer autenticação de administrador__

  DELETE - Remove

---

/Monsters/*nome-do-monstro* (Informação de um Monstro específico) - Representa o monstro, que é um recurso associado à uma entrada da tabela Monster

  GET - recebe o html de visualização

  POST - Adiciona novas entradas ao monstro do recurso __requer autenticação de administrador__

  DELETE - Remove dados específicos do monstro __requer autenticação de administrador__

---

#### Sobre a autenticação
O servidor criado é sem estados, de forma que para cada recurso que precisa de permissão privilegiada necessita autenticar o usuário ao chamar o verbo http.

Essa autenticação no entanto é automatizada a partir de um cookie simples de autenticação salvo no navegador utilizado. Guardando o hash e login da conta de Adminsitrador associada.
