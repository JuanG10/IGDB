package service;

import dao.impl.HibernateDataDAO;
import dao.impl.HibernateGameDAO;
import dao.impl.HibernateSearchDAO;
import dao.interf.DataDAO;
import dao.interf.GameDAO;
import dao.interf.SearchDAO;
import model.Genre;
import model.Game;
import model.Platform;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import service.impl.DataServiceImpl;
import service.impl.GameServiceImpl;
import service.impl.SearchService;
import service.impl.ServiceHibernate;
import service.interf.DataService;
import service.interf.JuegoService;

import java.util.List;

public class TestGameServiceImpl {
    private GameDAO gameDAO;
    private JuegoService juegoService;
    private DataService dataService;
    private DataDAO dataDAO;
    private Game lol;
    private ServiceHibernate serviceHibernate;
    private SearchDAO searchDAO;
    private SearchService searchService;


    @Before
    public void setUp(){
        gameDAO = new HibernateGameDAO();
        juegoService = new GameServiceImpl(gameDAO);
        dataDAO = new HibernateDataDAO();
        dataService = new DataServiceImpl(dataDAO);
        serviceHibernate = new ServiceHibernate(gameDAO);
        searchDAO = new HibernateSearchDAO();
        searchService = new SearchService(searchDAO);
        dataService.crearDatosIniciales();
    }

    @Test
    public void recupero_juego(){
        Assert.assertEquals("league of legends",juegoService.buscarJuego("league of legends").getNombre());
    }

    @Test
    public void lista_de_juegos_por_nombre(){

        List<Game> games = searchService.busquedaPorNombre("Resident");
        Assert.assertEquals(3, games.size());
        Assert.assertEquals("Resident Evil", games.get(0).getNombre());
        Assert.assertEquals("Resident Evil 2", games.get(1).getNombre());
        Assert.assertEquals("Resident Evil 3", games.get(2).getNombre());

    }
    @Test
    public void lista_de_juegos_por_Genero(){
        List<Game> games = searchService.busquedaPorgenero(Genre.Fighting);
        Assert.assertEquals(1, games.size());
        Assert.assertEquals("Dragon ball Z", games.get(0).getNombre());


    }
    @Test
    public void lista_de_juegos_por_Plataforma(){
        List<Game> games = searchService.busquedaPorPlataforma(Platform.PS1);
        Assert.assertEquals(3, games.size());
        Assert.assertEquals("Resident Evil", games.get(0).getNombre());
        Assert.assertEquals("Resident Evil 2", games.get(1).getNombre());
        Assert.assertEquals("Resident Evil 3", games.get(2).getNombre());

    }

    @After
    public void eliminarDatos(){
        dataService.eliminarDatos();
   }

}
