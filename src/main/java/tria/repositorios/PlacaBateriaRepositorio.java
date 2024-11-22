package tria.repositorios;

import lombok.var;
import tria.entidades.Bateria;
import tria.entidades.PlacaBateria;
import tria.infraestrutura.DatabaseConfig;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PlacaBateriaRepositorio implements _RepositorioCrud<PlacaBateria> {
    @Override
    public void cadastrar(PlacaBateria entidade) {
        try {
            var conn = DatabaseConfig.getConnection();
            var query = "INSERT INTO T_PLACA_BATERIA (TENSAO_NOMINAL , CAPACIDADE_AH , CAPACIDADE_KWH ) VALUES (?, ?, ?)";
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
    public void atualizar(PlacaBateria entidade, int id) {
        try {
            var conn = DatabaseConfig.getConnection();
            var query = "UPDATE T_PLACA_BATERIA SET TENSAO_NOMINAL = ?, CAPACIDADE_AH = ?, CAPACIDADE_KWH = ? WHERE ID_PLACA_BATERIA = ?";
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
            var query = "DELETE FROM T_PLACA_BATERIA WHERE ID_PLACA_BATERIA = ?";
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
    public Optional<PlacaBateria> buscarPorId(int id) {
        Optional<PlacaBateria> placaBateria = Optional.empty();
        try {
            var conn = DatabaseConfig.getConnection();
            var query = "SELECT * FROM T_PLACA_BATERIA WHERE ID_BATERIA = ? ORDER BY ID_PLACA_BATERIA";
            var stmt = conn.prepareStatement(query);
            stmt.setInt(1, id);
            var rs = stmt.executeQuery();
            if (rs.next()) {
                var _id = rs.getInt("ID_PLACA_BATERIA");
                var tensaoNominal = rs.getDouble("TENSAO_NOMINAL");
                var capacidadeAh = rs.getDouble("CAPACIDADE_AH");
                var capacidadeKwh = rs.getDouble("CAPACIDADE_KWH");
                placaBateria = Optional.of(new PlacaBateria(_id, tensaoNominal, capacidadeAh, capacidadeKwh));
            }
            rs.close();
            stmt.close();
            conn.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return placaBateria;
    }

    @Override
    public List<PlacaBateria> listar() {
        var placasBaterias = new ArrayList<PlacaBateria>();
        try {
            var conn = DatabaseConfig.getConnection();
            var query = "SELECT * FROM T_PLACA_BATERIA ORDER BY ID_PLACA_BATERIA";
            var stmt = conn.prepareStatement(query);
            var rs = stmt.executeQuery();
            while (rs.next()) {
                var _id = rs.getInt("ID_PLACA_BATERIA");
                var tensaoNominal = rs.getDouble("TENSAO_NOMINAL");
                var capacidadeAh = rs.getDouble("CAPACIDADE_AH");
                var capacidadeKwh = rs.getDouble("CAPACIDADE_KWH");
                placasBaterias.add(new PlacaBateria(_id, tensaoNominal, capacidadeAh, capacidadeKwh));
            }
            rs.close();
            stmt.close();
            conn.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return placasBaterias;
    }
}
