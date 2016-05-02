package br.ufrj.coppeted.concentrador.database;

/**
 *
 * @author mangeli
 */
public class myDB extends Db{

    public myDB() throws Exception {
        super("org.sqlite.JDBC", "jdbc:sqlite:dados.db");
    }
    
}
