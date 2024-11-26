package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import sql_banco__malvader.DBUtil;

public class ContaController {

    public String cadastrarConta(String numeroConta, String agencia, String tipoConta, int idCliente,
                                 Double saldo, Double limite, Double taxaRendimento, String dataVencimento) {
        String mensagem = "";

        try (Connection conn = DBUtil.conectar()) { // Usando try-with-resources para garantir o fechamento da conexão
            conn.setAutoCommit(false); // Inicia transação

            // Cadastrar conta principal
            String sqlConta = "INSERT INTO conta (numero_conta, agencia, saldo, tipo_conta, id_cliente) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement stmtConta = conn.prepareStatement(sqlConta)) {
                stmtConta.setString(1, numeroConta);
                stmtConta.setString(2, agencia);
                stmtConta.setDouble(3, saldo);
                stmtConta.setString(4, tipoConta);
                stmtConta.setInt(5, idCliente);
                stmtConta.executeUpdate();
            }

            // Cadastrar conta corrente ou conta poupança dependendo do tipo
            if (tipoConta.equals("CORRENTE")) {
                // Usar limite para conta corrente
                String sqlContaCorrente = "INSERT INTO conta_corrente (limite, data_vencimento, id_conta) "
                        + "SELECT ?, ?, id_conta FROM conta WHERE numero_conta = ?";

                try (PreparedStatement stmtContaCorrente = conn.prepareStatement(sqlContaCorrente)) {
                    stmtContaCorrente.setDouble(1, limite);  // Limite para conta corrente
                    stmtContaCorrente.setString(2, dataVencimento);
                    stmtContaCorrente.setString(3, numeroConta);
                    stmtContaCorrente.executeUpdate();
                }
            } else if (tipoConta.equals("POUPANCA")) {
                // Usar taxa de rendimento para conta poupança
                String sqlContaPoupanca = "INSERT INTO conta_poupanca (taxa_rendimento, id_conta) "
                        + "SELECT ?, id_conta FROM conta WHERE numero_conta = ?";

                try (PreparedStatement stmtContaPoupanca = conn.prepareStatement(sqlContaPoupanca)) {
                    stmtContaPoupanca.setDouble(1, taxaRendimento);  // Taxa de rendimento para conta poupança
                    stmtContaPoupanca.setString(2, numeroConta);
                    stmtContaPoupanca.executeUpdate();
                }
            }

            conn.commit(); // Confirma as alterações
            mensagem = "Conta cadastrada com sucesso!";
        } catch (SQLException e) {
            e.printStackTrace();
            mensagem = "Erro ao cadastrar conta: " + e.getMessage();
        }

        return mensagem;
    }
}
