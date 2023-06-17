Olá Professora Simone!

Todo o material está dentro da pasta Trabalho de Java! Um abraço!

a. O nome do Sistema: Sistema de Gerenciamento de Alunos.

b. O objetivo do Sistema: O sistema tem como objetivo gerenciar os dados dos alunos de uma escola, permitindo realizar operações de cadastro, atualização, exclusão e listagem dos alunos, além de resgatar os alunos que passaram em alguma disciplina.

c. Requisitos do Sistema: O sistema deve ser capaz de realizar as seguintes funcionalidades:

Cadastrar um novo aluno, informando seu nome, disciplina e nota.
Atualizar os dados de um aluno existente, como nome, disciplina e nota.
Excluir um aluno do sistema.
Listar todos os alunos cadastrados.
Resgatar os alunos que passaram em alguma disciplina, exibindo seus dados.
d. Caso de uso do Sistema:

O usuário inicia o sistema.
O sistema exibe um menu com as opções disponíveis: cadastrar aluno, atualizar aluno, excluir aluno, listar alunos e listar alunos aprovados.
O usuário seleciona a opção desejada.
O sistema executa a ação correspondente à opção selecionada pelo usuário.
O sistema exibe os resultados ou mensagens de confirmação ao usuário.
O usuário pode escolher realizar outra ação ou sair do sistema.
e. Explicação do que foi implementado:

O sistema foi implementado utilizando a linguagem Java e o banco de dados PostgreSQL.
A classe principal do sistema é a classe AlunoSystem, que contém os métodos para cada operação CRUD (criação, leitura, atualização e exclusão) e o método para resgatar os alunos aprovados.
O sistema faz uso de classes do pacote java.sql para estabelecer a conexão com o banco de dados e executar as consultas SQL.
O banco de dados contém uma tabela chamada "alunos" com as colunas "id" (identificador único do aluno), "nome" (nome do aluno), "disciplina" (disciplina em que o aluno está matriculado) e "nota" (nota obtida pelo aluno na disciplina).
As principais classes e métodos do sistema são:
AlunoSystem: Classe principal do sistema, contém os métodos para cada operação CRUD e para resgatar os alunos aprovados. Também possui o método start que inicia o sistema e exibe o menu para o usuário.
conectarBD: Método responsável por estabelecer a conexão com o banco de dados PostgreSQL.
cadastrarAluno: Método para cadastrar um novo aluno no sistema.
atualizarAluno: Método para atualizar os dados de um aluno existente.
excluirAluno: Método para excluir um aluno do sistema.
listarAlunos: Método para listar todos os alunos cadastrados.
listarAlunosAprovados: Método para resgatar e exibir os alunos aprovados.
f. Conclusão: O sistema implementado atende aos requisitos propostos, permitindo o gerenciamento dos dados dos alunos de uma escola. Com as funcionalidades de cadastro, atualização, exclusão e listagem, é possível realizar ações de manipulação dos dados dos alunos. Além disso, o sistema também permite resgatar os alunos que passaram em alguma disciplina, facilitando o acesso a essa informação. No entanto, é importante ressaltar que o sistema pode ser aprimorado com a adição de validações e tratamentos de erros, além de uma interface mais amigável para interação com o usuário.

Aluno: Victor Hugo de Carvalho Alves Ferreira
Matrícula: 202202165794
Email: fsvictorferreira@gmail.com
