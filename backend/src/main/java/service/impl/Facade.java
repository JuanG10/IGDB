package service.impl;

import dao.impl.HibernateGameDAO;
import dao.impl.HibernateSearchDAO;
import dao.interf.GameDAO;
import dao.interf.SearchDAO;
import model.Genre;
import model.Game;
import model.Platform;
import service.interf.JuegoService;

import java.util.List;

public class Facade {
    private GameDAO gameDAO;
    private JuegoService juegoService;
    private SearchDAO searchDAO;
    private SearchService searchService;


    public Facade(){
        this.gameDAO = new HibernateGameDAO();
        this.juegoService = new GameServiceImpl(gameDAO);
        this.searchDAO = new HibernateSearchDAO();
        this.searchService = new SearchService();
    }

    public Game buscarPorNombre(String nombre){
        return this.juegoService.buscarJuego(nombre);
    }

    public List<Game>buscarListaDeJuegosPorNormbre(String nombre){
        return  this.searchService.busquedaPorNombre(nombre);
    }


    public List<Game>buscarListaDeJuegosPorGenero(Genre genre){
        return  this.searchService.busquedaPorgenero(genre);
    }

    public List<Game>buscarListaDeJuegosPorNormbre(Platform platform){
        return  this.searchService.busquedaPorPlataforma(platform);
    }











}
