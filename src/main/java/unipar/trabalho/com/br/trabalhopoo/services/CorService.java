package unipar.trabalho.com.br.trabalhopoo.services;


import unipar.trabalho.com.br.trabalhopoo.models.Cor;
import unipar.trabalho.com.br.trabalhopoo.repository.CorRepository;
import java.util.List;

public class CorService {
    private final CorRepository repository;

    public CorService(CorRepository repository) {
        this.repository = repository;
    }

    public void addCor(Cor cor) {
        
        repository.insert(cor);
    }

    public List<Cor> getAllCores() {
        return repository.findAll();
    }
}