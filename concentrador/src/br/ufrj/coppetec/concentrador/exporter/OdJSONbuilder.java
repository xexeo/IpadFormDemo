/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrj.coppetec.concentrador.exporter;

import java.sql.ResultSet;
import org.json.JSONObject;

/**
 *
 * @author mangeli
 */
public class OdJSONbuilder implements JSONBuilder{

	@Override
	public String build(ResultSet result) throws Exception{
		String s;
		JSONObject json = new JSONObject();
		JSONObject reg;
		while(result.next()){
			reg = new JSONObject();
			reg.put("id", result.getString("id"));
			reg.put("estaNoNote" ,result.getInt("estaNoNote"));
			reg.put("cancelado" ,result.getInt("cancelado"));
			reg.put("idPosto" ,result.getInt("idPosto"));
			reg.put("sentido" ,result.getString("sentido"));
			reg.put("idIpad" ,result.getString("idIpad"));
			//reg.put("login" ,result.getString("login"));
			reg.put("dataIniPesq" ,result.getString("dataIniPesq"));
			reg.put("dataFimPesq" ,result.getString("dataFimPesq"));
			reg.put("placa" ,result.getString("placa"));
			reg.put("anoDeFabricacao" ,result.getInt("anoDeFabricacao"));
			reg.put("tipo" ,result.getString("tipo"));
			reg.put("idOrigemPais" ,result.getInt("idOrigemPais"));
			reg.put("idOrigemMunicipio" ,result.getInt("idOrigemMunicipio"));
			reg.put("idDestinoPais" ,result.getInt("idDestinoPais"));
			reg.put("idDestinoMunicipio" ,result.getInt("idDestinoMunicipio"));
			reg.put("idMotivoDeEscolhaDaRota" ,result.getInt("idMotivoDeEscolhaDaRota"));
			reg.put("frequenciaQtd" ,result.getInt("frequenciaQtd"));
			reg.put("frequenciaPeriodo" ,result.getString("frequenciaPeriodo"));
			reg.put("idPropriedadesDoVeiculo",result.getInt("idPropriedadesDoVeiculo"));
			reg.put("placaEstrangeira" ,result.getInt("placaEstrangeira"));
			reg.put("idPaisPlacaEstrangeira" ,result.getInt("idPaisPlacaEstrangeira"));
			reg.put("idCombustivel" ,result.getInt("idCombustivel"));
			reg.put("categoria" ,result.getString("categoria"));
			reg.put( "possuiReboque",result.getInt("possuiReboque"));
			reg.put( "numeroDePessoasNoVeiculo",result.getInt("numeroDePessoasNoVeiculo"));
			reg.put("numeroDePessoasATrabalho" ,result.getInt("numeroDePessoasATrabalho"));
			reg.put("idRendaMedia" ,result.getInt("idRendaMedia"));
			reg.put("idMotivoDaViagem" ,result.getInt("idMotivoDaViagem"));
			reg.put("tipoCaminhao" ,result.getString("tipoCaminhao"));
			reg.put("idTipoDeContainer" ,result.getInt("idTipoDeContainer"));
			reg.put("idTipoCarroceria" ,result.getInt("idTipoCarroceria"));
			reg.put("rntrc" ,result.getString("rntrc"));
			reg.put("possuiCargaPerigosa" ,result.getInt("possuiCargaPerigosa"));
			reg.put("idNumeroDeRisco" ,result.getInt("idNumeroDeRisco"));
			reg.put("idNumeroDaOnu" ,result.getInt("idNumeroDaOnu"));
			reg.put("idAgregado" ,result.getInt("idAgregado"));
			reg.put("placaVermelha" ,result.getInt("placaVermelha"));
			reg.put("idTipoDeViagemOuServico" ,result.getInt("idTipoDeViagemOuServico"));
			reg.put("pesoDaCarga" ,result.getFloat("pesoDaCarga"));
			reg.put("valorDoFrete" ,result.getFloat("valorDoFrete"));
			reg.put("utilizaParadaEspecial" ,result.getInt("utilizaParadaEspecial"));
			reg.put("idProduto" ,result.getInt("idProduto"));
			reg.put("idCargaAnterior" ,result.getInt("idCargaAnterior"));
			reg.put("valorDaCarga" ,result.getFloat("valorDaCarga"));
			reg.put("municipioEmbarqueCarga" ,result.getInt("municipioEmbarqueCarga"));
			reg.put("idLocalEmbarqueCarga" ,result.getInt("idLocalEmbarqueCarga"));
			reg.put("municipioDesembarqueCarga" ,result.getInt("municipioDesembarqueCarga"));
			reg.put("idLocalDesembarqueCarga" ,result.getInt("idLocalDesembarqueCarga"));
			reg.put("indoPegarCarga" ,result.getInt("indoPegarCarga"));
			reg.put("paradaObrigatoriaMunicipio1" ,result.getInt("paradaObrigatoriaMunicipio1"));
			reg.put("paradaObrigatoriaMunicipio2" ,result.getInt("paradaObrigatoriaMunicipio2"));
			reg.put("idPerguntaExtra" ,result.getInt("idPerguntaExtra"));
			
			json.append("dados", reg);
		}
		
		s = json.get("dados").toString().replace("},", "},\n").replace(":[",":\n[");
		return s;
	}
	
}
