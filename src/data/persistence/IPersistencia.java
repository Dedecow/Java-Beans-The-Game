package data.persistence;

import data.model.Historico;

public interface IPersistencia {
    
    void salvar(Historico registro);

    Historico[] lerHistorico();
}