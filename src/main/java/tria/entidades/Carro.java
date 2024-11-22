package tria.entidades;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Carro extends _EntidadeBase{
    private String placa;
    private String marca;
    private String modelo;
    private int ano;
    private Bateria bateria;
    private PlacaBateria placaBateria;

    public Carro(int id, String placa, String marca, String modelo, int ano) {
        super(id);
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.ano = ano;
    }

    public Carro(int id, String placa, String marca, String modelo, int ano, Bateria bateria, PlacaBateria placaBateria) {
        super(id);
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.ano = ano;
        this.bateria = bateria;
        this.placaBateria = placaBateria;
    }
}
