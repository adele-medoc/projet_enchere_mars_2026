package fr.eni.projetenchere.dal;

public interface DaoAdresse {
    void insertAdresse(String rue,String codePostal, String ville);
    long insertAdresseAndGetID(String rue,String codePostal, String ville);
}
