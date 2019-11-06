package service;

import dao.impl.HibernateDataDAO;
import dao.interf.DataDAO;
import model.Juego;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import service.impl.DataServiceImpl;
import service.impl.Facade;
import service.interf.DataService;

import java.util.List;

public class TestFacade {
    private Facade facade;
    private DataDAO dataDAO;
    private DataService dataService;



    @Before
    public void setUp(){
        facade = new Facade();
        dataDAO = new HibernateDataDAO();
        dataService = new DataServiceImpl(dataDAO);
        dataService.crearDatosIniciales();
    }

    @Test
    public void buscarJuegoPorNombre(){
        Juego juego = facade.buscarPorNombre("Resident Evil");
        Assert.assertEquals("Resident Evil",juego.getNombre());
    }


    @After
    public void clear(){
        dataService.eliminarDatos();
    }







}
