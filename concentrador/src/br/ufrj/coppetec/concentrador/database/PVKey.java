package br.ufrj.coppetec.concentrador.database;

/**
 * Estrutura que descreve o conjunto de dados que representa unívocamente um registro de pesquisa volumétrica. 
 *
 * @author ludes - PESC - COPPE - ufrj
 * @author Eduardo Mangeli
 * @author Marcelo Areas
 * @author Fabrício Pereira
 * @author Geraldo Xexéo
 */
public class PVKey {
	public int posto;		///< Identificação do posto
	public String data;		///< Data de realização da pesquisa
	public int hora;		///< Hora de realização da pesquisa
	public String sentido;  ///< Sentido da pista no qual a pesquisa foi realizada
	
	/**
	 * Cria um objeto desse tipo com os dados vazios.
	 */
	public PVKey(){
	}
	
	/**
	 * Cria um objeto desse tipo e preenche os dados a partir de um registro de pesquisa volumétrica
	 * @param reg	referência para um registro de pesquisa volumétrica.
	 */
	public PVKey(PVregister reg){
		this.posto = reg.posto;
		this.data = reg.data;
		this.hora = reg.hora;
		this.sentido = reg.sentido;
	}
}
