package unipar.trabalho.com.br.trabalhopoo.services;

import unipar.trabalho.com.br.trabalhopoo.models.Pelagem;
import unipar.trabalho.com.br.trabalhopoo.repository.PelagemRepository;
import java.util.List;

public class PelagemService {
    private final PelagemRepository repository;

    public PelagemService(PelagemRepository repository) {
        this.repository = repository;
    }

    
    public void addPelagem(Pelagem pelagem) {
        repository.insert(pelagem);
    }

    public List<Pelagem> getAllPelagens() {
        return repository.findAll();
    }
}