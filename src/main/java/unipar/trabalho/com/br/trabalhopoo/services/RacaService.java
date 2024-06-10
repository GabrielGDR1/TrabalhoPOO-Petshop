package unipar.trabalho.com.br.trabalhopoo.services;


import unipar.trabalho.com.br.trabalhopoo.models.Raca;
import unipar.trabalho.com.br.trabalhopoo.repository.*;

import java.util.List;

public class RacaService {
	
    private final RacaRepository repository;

    public RacaService(RacaRepository repository) {
        this.repository = repository;
    }

    public void addRaca(Raca raca) {
       
        repository.insert(raca);
    }

    public List<Raca> getAllRacas() {
        return repository.findAll();
    }
}