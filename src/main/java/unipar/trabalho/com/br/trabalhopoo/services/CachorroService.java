package unipar.trabalho.com.br.trabalhopoo.services;

import unipar.trabalho.com.br.trabalhopoo.models.Cachorro;
import unipar.trabalho.com.br.trabalhopoo.repository.CachorroRepository;

import java.util.List;

public class CachorroService {
    private final CachorroRepository repository;

    public CachorroService(CachorroRepository repository) {
        this.repository = repository;
    }

    public void addCachorro(Cachorro cachorro) {
        
        repository.insert(cachorro);
    }

    public List<Cachorro> getAllCachorros() {
        return repository.findAll();
    }
}