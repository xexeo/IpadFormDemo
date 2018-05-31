package br.ufrj.coppetec.concentrador.exporter;

import java.sql.ResultSet;

/**
 * Interface que generaliza o processo de construir uma representação JSON do resultado de uma consulta 
 * ao banco de dados
 *
 * @author ludes - PESC - COPPE - ufrj
 * @author Eduardo Mangeli
 * @author Marcelo Areas
 * @author Fabrício Pereira
 * @author Geraldo Xexéo
 */
public interface JSONBuilder {
	/**
	 * Constrói uma representação JSON de uma consulta ao banco
	 * @param result	resultado de uma consulta ao banco de dados interno da aplicação
	 * @return			representação JSON da consulta
	 * @throws Exception 
	 */
	public String build(ResultSet result) throws Exception;
}
