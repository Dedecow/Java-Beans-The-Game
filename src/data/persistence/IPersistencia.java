package data.persistence;

import data.model.Historico;

/**
 * Interface que define o contrato de persistência. 
 * Garante que a lógica do jogo (engine) não precisa saber como o dado é salvo (arquivo ou BD).
 */
public interface IPersistencia {
    void salvar(Historico registro);
}