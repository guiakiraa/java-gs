package tria.entidades;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public abstract class _EntidadeBase {
    private int id;

    public _EntidadeBase(int id) {
        this.id = id;
    }
}
