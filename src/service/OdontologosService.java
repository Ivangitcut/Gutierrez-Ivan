package service;

import dao.IDao;
import model.Odontologos;

public class OdontologosService {
    private IDao<Odontologos> odontologosIDao;

    public OdontologosService(IDao<Odontologos> odontologosIDao){
        this.odontologosIDao=odontologosIDao;
    }

    public Odontologos guardarOdontologos(Odontologos odontologos){
        return pacienteIdao.guardar(Odontologos);
    }
}
