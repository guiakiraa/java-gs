package tria.entidades;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlacaBateria extends _EntidadeBase{
    private double tensao_nominal;
    private double capacidade_ah;
    private double capacidade_kwh;

    public PlacaBateria(int id, double tensao_nominal, double capacidade_ah, double capacidade_kwh) {
        super(id);
        this.tensao_nominal = tensao_nominal;
        this.capacidade_ah = capacidade_ah;
        this.capacidade_kwh = capacidade_kwh;
    }
}
