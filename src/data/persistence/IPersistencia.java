// data/persistence/IPersistencia.java
package data.persistence;

import data.model.Historico;

public interface IPersistencia {
    void salvar(Historico registro);
    Historico[] lerHistorico();  // Já está correto
}