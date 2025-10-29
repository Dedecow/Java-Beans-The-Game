package data.persistence;

import data.model.Historico;

/**
 * Interface que define o contrato de persistência.
 * Garante que a lógica do jogo (engine) não precisa saber como o dado é salvo (arquivo ou BD).
 */
public interface IPersistencia {
    
    /**
     * Salva um único registro de pontuação.
     * @param registro O objeto Historico a ser salvo.
     */
    void salvar(Historico registro);

    /**
     * NOVO MÉTODO: Lê todos os registros de pontuação salvos.
     * @return Um array (Historico[]) com todos os registros encontrados.
     * Retorna um array vazio se nenhum registro for encontrado.
     */
    Historico[] lerHistorico();
}