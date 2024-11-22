package tria.servicos;

import lombok.var;
import tria.entidades.PlacaBateria;
import tria.infraestrutura.Log4jLogger;
import tria.repositorios.PlacaBateriaRepositorio;

import java.util.List;
import java.util.Optional;

public class PlacaBateriaServico {
    private PlacaBateriaRepositorio placaBateriaRepositorio;
    private Log4jLogger logger;

    public PlacaBateriaServico() {
        this.placaBateriaRepositorio = new PlacaBateriaRepositorio();
        this.logger = new Log4jLogger();
    }

    public void Cadastrar(PlacaBateria placaBateria) {
        var placaBateriaValido = validarPlacaBateria(placaBateria);
        if (placaBateriaValido) {
            placaBateriaRepositorio.cadastrar(placaBateria);
            logger.logPlacaBateriaCadastrada(placaBateria);
        }
    }

    public void Deletar(int id) {
        var placaBateria = placaBateriaRepositorio.buscarPorId(id);
        if (placaBateria.isPresent()) {
            placaBateriaRepositorio.remover(id);
            logger.logPlacaBateriaDeletado(placaBateria.get());
        }
    }

    public void Atualizar(PlacaBateria placaBateria, int id) {
        var placaBateriaValido = validarPlacaBateria(placaBateria);
        if (placaBateriaValido) {
            placaBateriaRepositorio.atualizar(placaBateria, id);
            logger.logPlacaBateriaAtualizado(placaBateria);
        }
    }

    public Optional<PlacaBateria> BuscarPorId(int id) {
        var placaBateria = placaBateriaRepositorio.buscarPorId(id);
        placaBateria.ifPresent(logger::logPlacaBateriaBuscadoPorId);
        return placaBateria;
    }

    public List<PlacaBateria> Listar() {
        var placaBaterias = placaBateriaRepositorio.listar();
        logger.logPlacaBateriaListadas(placaBaterias);
        return placaBaterias;
    }

    private static boolean validarPlacaBateria(PlacaBateria placaBateria) {
        return placaBateria.getCapacidade_kwh() > 0 & placaBateria.getCapacidade_ah() > 0 & placaBateria.getTensao_nominal() > 0;
    }
}
