import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AlunoSystem {
    private static final String JDBC_URL = "jdbc:postgresql://localhost:5432/DadosGerais";
    private static final String JDBC_USERNAME = "postgres";
    private static final String JDBC_PASSWORD = "12345";

    private Connection conn;

    public static void main(String[] args) {
        AlunoSystem system = new AlunoSystem();
        system.start();
    }

    public void start() {
        try {
            conn = DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD);
            createTable();
            Scanner scanner = new Scanner(System.in);
            int opcao;
            do {
                System.out.println("\n----- SISTEMA DE ALUNOS -----\n");
                System.out.println("1 - Cadastrar aluno");
                System.out.println("2 - Atualizar aluno");
                System.out.println("3 - Excluir aluno");
                System.out.println("4 - Listar todos os alunos");
                System.out.println("5 - Listar alunos aprovados");
                System.out.println("0 - Sair");
                System.out.print("Escolha uma opção: ");
                opcao = scanner.nextInt();
                scanner.nextLine(); // Limpar o buffer do scanner

                switch (opcao) {
                    case 1:
                        cadastrarAluno(scanner);
                        break;
                    case 2:
                        atualizarAluno(scanner);
                        break;
                    case 3:
                        excluirAluno(scanner);
                        break;
                    case 4:
                        listarAlunos();
                        break;
                    case 5:
                        listarAlunosAprovados();
                        break;
                    case 0:
                        System.out.println("Saindo do sistema...");
                        break;
                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                        break;
                }
            } while (opcao != 0);
        } catch (SQLException e) {
            System.err.println("Erro ao conectar ao banco de dados: " + e.getMessage());
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    System.err.println("Erro ao fechar a conexão com o banco de dados: " + e.getMessage());
                }
            }
        }
    }

    private void createTable() throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS alunos (" +
                "id SERIAL PRIMARY KEY," +
                "nome VARCHAR(100) NOT NULL," +
                "disciplina VARCHAR(100) NOT NULL," +
                "nota FLOAT NOT NULL" +
                ");";
        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sql);
        }
    }

    private void cadastrarAluno(Scanner scanner) throws SQLException {
        System.out.println("\n----- CADASTRO DE ALUNO -----\n");
        System.out.print("Nome do aluno: ");
        String nome = scanner.nextLine();
        System.out.print("Disciplina: ");
        String disciplina = scanner.nextLine();
        System.out.print("Nota: ");
        float nota = scanner.nextFloat();
        scanner.nextLine(); // Limpar o buffer do scanner

        String sql = "INSERT INTO alunos (nome, disciplina, nota) VALUES (?, ?, ?);";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nome);
            stmt.setString(2, disciplina);
            stmt.setFloat(3, nota);
            stmt.executeUpdate();
            System.out.println("Aluno cadastrado com sucesso!");
        }
    }

    private void atualizarAluno(Scanner scanner) throws SQLException {
        System.out.println("\n----- ATUALIZAÇÃO DE ALUNO -----\n");
        System.out.print("ID do aluno a ser atualizado: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer do scanner

        String sql = "SELECT * FROM alunos WHERE id = ?;";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                System.out.println("Aluno encontrado:");
                System.out.println("Nome: " + rs.getString("nome"));
                System.out.println("Disciplina: " + rs.getString("disciplina"));
                System.out.println("Nota: " + rs.getFloat("nota"));

                System.out.print("Novo nome: ");
                String novoNome = scanner.nextLine();
                System.out.print("Nova disciplina: ");
                String novaDisciplina = scanner.nextLine();
                System.out.print("Nova nota: ");
                float novaNota = scanner.nextFloat();
                scanner.nextLine(); // Limpar o buffer do scanner

                String updateSql = "UPDATE alunos SET nome = ?, disciplina = ?, nota = ? WHERE id = ?;";
                try (PreparedStatement updateStmt = conn.prepareStatement(updateSql)) {
                    updateStmt.setString(1, novoNome);
                    updateStmt.setString(2, novaDisciplina);
                    updateStmt.setFloat(3, novaNota);
                    updateStmt.setInt(4, id);
                    updateStmt.executeUpdate();
                    System.out.println("Aluno atualizado com sucesso!");
                }
            } else {
                System.out.println("Aluno não encontrado.");
            }
        }
    }

    private void excluirAluno(Scanner scanner) throws SQLException {
        System.out.println("\n----- EXCLUSÃO DE ALUNO -----\n");
        System.out.print("ID do aluno a ser excluído: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer do scanner

        String sql = "SELECT * FROM alunos WHERE id = ?;";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                System.out.println("Aluno encontrado:");
                System.out.println("Nome: " + rs.getString("nome"));
                System.out.println("Disciplina: " + rs.getString("disciplina"));
                System.out.println("Nota: " + rs.getFloat("nota"));

                System.out.print("Confirma a exclusão? (S/N): ");
                String confirmacao = scanner.nextLine();
                if (confirmacao.equalsIgnoreCase("S")) {
                    String deleteSql = "DELETE FROM alunos WHERE id = ?;";
                    try (PreparedStatement deleteStmt = conn.prepareStatement(deleteSql)) {
                        deleteStmt.setInt(1, id);
                        deleteStmt.executeUpdate();
                        System.out.println("Aluno excluído com sucesso!");
                    }
                } else {
                    System.out.println("Exclusão cancelada.");
                }
            } else {
                System.out.println("Aluno não encontrado.");
            }
        }
    }

    private void listarAlunos() throws SQLException {
        System.out.println("\n----- LISTA DE ALUNOS -----\n");
        String sql = "SELECT * FROM alunos;";
        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id"));
                System.out.println("Nome: " + rs.getString("nome"));
                System.out.println("Disciplina: " + rs.getString("disciplina"));
                System.out.println("Nota: " + rs.getFloat("nota"));
                System.out.println("------------------------");
            }
        }
    }

    private void listarAlunosAprovados() throws SQLException {
        System.out.println("\n----- ALUNOS APROVADOS -----\n");
        String sql = "SELECT * FROM alunos WHERE nota >= 6.0;";
        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id"));
                System.out.println("Nome: " + rs.getString("nome"));
                System.out.println("Disciplina: " + rs.getString("disciplina"));
                System.out.println("Nota: " + rs.getFloat("nota"));
                System.out.println("------------------------");
            }
        }
    }
}
