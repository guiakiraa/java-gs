package tria.servicos;

import lombok.var;
import tria.entidades.Bateria;
import tria.infraestrutura.Log4jLogger;
import tria.repositorios.BateiraRepositorio;

import java.util.List;
import java.util.Optional;

public class BateriaServico {
    private BateiraRepositorio bateiraRepositorio;
    private Log4jLogger logger;

    public BateriaServico() {
        this.bateiraRepositorio = new BateiraRepositorio();
        this.logger = new Log4jLogger();
    }

    public void Cadastrar(Bateria bateria) {
        var bateriaValido = validarBateria(bateria);
        if (bateriaValido) {
            bateiraRepositorio.cadastrar(bateria);
            logger.logBateriaCadastrada(bateria);
        }
    }

    public void Deletar(int id) {
        var bateria = bateiraRepositorio.buscarPorId(id);
        if (bateria.isPresent()) {
            bateiraRepositorio.remover(id);
            logger.logBateriaDeletado(bateria.get());
        }
    }

    public void Atualizar(Bateria bateria, int id) {
        var bateriaValido = validarBateria(bateria);
        if (bateriaValido) {
            bateiraRepositorio.atualizar(bateria, id);
            logger.logBateriaAtualizada(bateria);
        }
    }

    public Optional<Bateria> BuscarPorId(int id) {
        var bateria = bateiraRepositorio.buscarPorId(id);
        bateria.ifPresent(logger::logBateriaBuscadoPorId);
        return bateria;
    }

    public List<Bateria> Listar() {
        var baterias = bateiraRepositorio.listar();
        logger.logBateriaListados(baterias);
        return baterias;
    }

    private static boolean validarBateria(Bateria bateria) {
        return bateria.getCapacidade_kwh() > 0 & bateria.getCapacidade_ah() > 0 & bateria.getTensao_nominal() > 0;
    }
}
