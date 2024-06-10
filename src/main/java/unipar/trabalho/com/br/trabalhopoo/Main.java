package unipar.trabalho.com.br.trabalhopoo;
 
 
import unipar.trabalho.com.br.trabalhopoo.models.Cachorro;
import unipar.trabalho.com.br.trabalhopoo.models.Cor;
import unipar.trabalho.com.br.trabalhopoo.models.Pelagem;
import unipar.trabalho.com.br.trabalhopoo.models.Raca;
import unipar.trabalho.com.br.trabalhopoo.repository.CachorroRepository;
import unipar.trabalho.com.br.trabalhopoo.repository.CorRepository;
import unipar.trabalho.com.br.trabalhopoo.repository.PelagemRepository;
import unipar.trabalho.com.br.trabalhopoo.repository.*;
import unipar.trabalho.com.br.trabalhopoo.services.CachorroService;
import unipar.trabalho.com.br.trabalhopoo.services.CorService;
import unipar.trabalho.com.br.trabalhopoo.services.PelagemService;
import unipar.trabalho.com.br.trabalhopoo.services.RacaService;
 
 
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;
 
public class Main {
    public static void main(String[] args) {
        Properties props = new Properties();
        try (InputStream input = Main.class.getClassLoader().getResourceAsStream("application.properties")) {
            if (input == null) {
                System.out.println("Desculpe, n�o foi poss�vel encontrar o arquivo application.properties");
                System.out.println("Classpath: " + System.getProperty("java.class.path"));
                return;
            }
            props.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
            return;
        }
 
        String url = props.getProperty("jdbc.url");
        String user = props.getProperty("jdbc.username");
        String password = props.getProperty("jdbc.password");
 
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            RacaRepository racaRepo = new RacaRepository(connection);
            CorRepository corRepo = new CorRepository(connection);
            PelagemRepository pelagemRepo = new PelagemRepository(connection);
            CachorroRepository cachorroRepo = new CachorroRepository(connection);
 
            
            RacaService racaService = new RacaService(racaRepo);
            CorService corService = new CorService(corRepo);
            PelagemService pelagemService = new PelagemService(pelagemRepo);
            CachorroService cachorroService = new CachorroService(cachorroRepo);
 
            
            Raca raca = new Raca(null, "Labrador");
            racaService.addRaca(raca);
 
            Cor cor = new Cor(null, "Preto");
            corService.addCor(cor);
 
            Pelagem pelagem = new Pelagem(null, "Curta");
            pelagemService.addPelagem(pelagem);
 
            Cachorro cachorro = new Cachorro(null, "Rex", raca, cor, pelagem);
            cachorroService.addCachorro(cachorro);
 
            
            List<Raca> racas = racaService.getAllRacas();
            List<Cor> cores = corService.getAllCores();
            List<Pelagem> pelagens = pelagemService.getAllPelagens();
            List<Cachorro> cachorros = cachorroService.getAllCachorros();
 
            System.out.println("Racas:");
            racas.forEach(System.out::println);
 
            System.out.println("Cores:");
            cores.forEach(System.out::println);
 
            System.out.println("Pelagens:");
            pelagens.forEach(System.out::println);
 
            System.out.println("Cachorros:");
            cachorros.forEach(System.out::println);
 
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}