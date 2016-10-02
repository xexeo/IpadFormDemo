package br.ufrj.coppetec.concentrador.database;

/**
 *
 * @author mangeli
 */
public class PVKey {
	public int posto;
	public String data;
	public int hora;
	public String sentido;
	
	public PVKey(){
	}
	
	public PVKey(PVregister reg){
		this.posto = reg.posto;
		this.data = reg.data;
		this.hora = reg.hora;
		this.sentido = reg.sentido;
	}
}
