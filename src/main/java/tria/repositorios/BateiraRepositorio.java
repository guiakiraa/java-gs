package tria.repositorios;

import lombok.var;
import tria.entidades.Bateria;
import tria.infraestrutura.DatabaseConfig;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
public class BateiraRepositorio implements _RepositorioCrud<Bateria> {
    @Override
    public void cadastrar(Bateria entidade) {
        try {
            var conn = DatabaseConfig.getConnection();
            var query = "INSERT INTO T_BATERIA (TENSAO_NOMINAL , CAPACIDADE_AH , CAPACIDADE_KWH ) VALUES (?, ?, ?)";
            var stmt = conn.prepareStatement(query);
            stmt.setDouble(1, entidade.getTensao_nominal());
            stmt.setDouble(2, entidade.getCapacidade_ah());
            stmt.setDouble(3, entidade.getCapacidade_kwh());
            stmt.executeUpdate();
            stmt.close();
            conn.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void atualizar(Bateria entidade, int id) {
        try {
            var conn = DatabaseConfig.getConnection();
            var query = "UPDATE T_BATERIA SET TENSAO_NOMINAL = ?, CAPACIDADE_AH = ?, CAPACIDADE_KWH = ? WHERE ID_BATERIA = ?";
            var stmt = conn.prepareStatement(query);
            stmt.setDouble(1, entidade.getTensao_nominal());
            stmt.setDouble(2, entidade.getCapacidade_ah());
            stmt.setDouble(3, entidade.getCapacidade_kwh());
            stmt.setInt(4, id);
            stmt.executeUpdate();
            stmt.close();
            conn.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void remover(int id) {
        try {
            var conn = DatabaseConfig.getConnection();
            var query = "DELETE FROM T_BATERIA WHERE ID_BATERIA = ?";
            var stmt = conn.prepareStatement(query);
            stmt.setInt(1, id);
            stmt.executeUpdate();
            stmt.close();
            conn.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Bateria> buscarPorId(int id) {
        Optional<Bateria> bateria = Optional.empty();
        try {
            var conn = DatabaseConfig.getConnection();
            var query = "SELECT * FROM T_BATERIA WHERE ID_BATERIA = ? ORDER BY ID_BATERIA";
            var stmt = conn.prepareStatement(query);
            stmt.setInt(1, id);
            var rs = stmt.executeQuery();
            if (rs.next()) {
                var _id = rs.getInt("ID_BATERIA");
                var tensaoNominal = rs.getDouble("TENSAO_NOMINAL");
                var capacidadeAh = rs.getDouble("CAPACIDADE_AH");
                var capacidadeKwh = rs.getDouble("CAPACIDADE_KWH");
                bateria = Optional.of(new Bateria(_id, tensaoNominal, capacidadeAh, capacidadeKwh));
            }
            rs.close();
            stmt.close();
            conn.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return bateria;
    }

    @Override
    public List<Bateria> listar() {
        var baterias = new ArrayList<Bateria>();
        try {
            var conn = DatabaseConfig.getConnection();
            var query = "SELECT * FROM T_BATERIA ORDER BY ID_BATERIA";
            var stmt = conn.prepareStatement(query);
            var rs = stmt.executeQuery();
            while (rs.next()) {
                var _id = rs.getInt("ID_BATERIA");
                var tensaoNominal = rs.getDouble("TENSAO_NOMINAL");
                var capacidadeAh = rs.getDouble("CAPACIDADE_AH");
                var capacidadeKwh = rs.getDouble("CAPACIDADE_KWH");
                baterias.add(new Bateria(_id, tensaoNominal, capacidadeAh, capacidadeKwh));
            }
            rs.close();
            stmt.close();
            conn.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return baterias;
    }
}
