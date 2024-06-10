package unipar.trabalho.com.br.trabalhopoo.models;
 
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
 
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cachorro {
    private Long id;
    private String nome;
    private Raca raca;
    private Cor cor;
    private Pelagem pelagem;
}