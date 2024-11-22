package tria.infraestrutura;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import tria.entidades.Bateria;
import tria.entidades.Carro;
import tria.entidades.PlacaBateria;

import java.util.List;

public class Log4jLogger {
    private final Logger carroLogger;
    private final Logger bateriaLogger;
    private final Logger placaBateriaLogger;

    public Log4jLogger() {
        this.carroLogger = LogManager.getLogger(Carro.class);
        this.bateriaLogger = LogManager.getLogger(Bateria.class);
        this.placaBateriaLogger = LogManager.getLogger(PlacaBateria.class);
    }

    public void logCarroCadastrado(Carro carro) {
        carroLogger.info("Cadastrado: " + carro);
    }

    public void logCarrosListados(List<Carro> carros) {
        carroLogger.info("Listados: " + carros);
    }

    public void logCarroAtualizado(Carro carro) {
        carroLogger.info("Atualizado: " + carro);
    }

    public void logCarroDeletado(Carro carro) {
        carroLogger.info("Deletado: " + carro);
    }

    public void logCarroBuscadoPorId(Carro carro) {
        carroLogger.info("Buscado por ID: " + carro);
    }

    public void logBateriaCadastrada(Bateria bateria) {
        bateriaLogger.info("Cadastrado: " + bateria);
    }

    public void logBateriaListados(List<Bateria> baterias) {
        bateriaLogger.info("Listados: " + baterias);
    }

    public void logBateriaAtualizada(Bateria bateria) {
        bateriaLogger.info("Atualizado: " + bateria);
    }

    public void logBateriaDeletado(Bateria bateria) {
        bateriaLogger.info("Deletado: " + bateria);
    }

    public void logBateriaBuscadoPorId(Bateria bateria) {
        bateriaLogger.info("Buscado por ID: " + bateria);
    }

    public void logPlacaBateriaCadastrada(PlacaBateria placaBateria) {
        placaBateriaLogger.info("Cadastrado: " + placaBateria);
    }

    public void logPlacaBateriaListadas(List<PlacaBateria> placas) {
        placaBateriaLogger.info("Listados: " + placas);
    }

    public void logPlacaBateriaAtualizado(PlacaBateria placaBateria) {
        placaBateriaLogger.info("Atualizado: " + placaBateria);
    }

    public void logPlacaBateriaDeletado(PlacaBateria placaBateria) {
        placaBateriaLogger.info("Deletado: " + placaBateria);
    }

    public void logPlacaBateriaBuscadoPorId(PlacaBateria placaBateria) {
        placaBateriaLogger.info("Buscado por ID: " + placaBateria);
    }
}
