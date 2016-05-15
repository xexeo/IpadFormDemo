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
			//reg.put("id",JSONExporter.getJSONString(result.getString("id"));
			reg.put("estaNoNote" , JSONExporter.getJSONInteger(result.getString("estaNoNote")));
			reg.put("cancelado" ,JSONExporter.getJSONInteger(result.getString("cancelado")));
			reg.put("idPosto" ,JSONExporter.getJSONInteger(result.getString("idPosto")));
			reg.put("sentido" ,JSONExporter.getJSONString(result.getString("sentido")));
			reg.put("idIpad" ,JSONExporter.getJSONString(result.getString("idIpad")));
			//reg.put("login" ,JSONExporter.getJSONString(result.getString("login"));
			reg.put("dataIniPesq" ,JSONExporter.getJSONString(result.getString("dataIniPesq")));
			reg.put("dataFimPesq" ,JSONExporter.getJSONString(result.getString("dataFimPesq")));
			reg.put("placa" ,JSONExporter.getJSONString(result.getString("placa")));
			reg.put("anoDeFabricacao" ,JSONExporter.getJSONInteger(result.getString("anoDeFabricacao")));
			reg.put("tipo" ,JSONExporter.getJSONString(result.getString("tipo")));
			reg.put("idOrigemPais" ,JSONExporter.getJSONInteger(result.getString("idOrigemPais")));
			reg.put("idOrigemMunicipio" ,JSONExporter.getJSONInteger(result.getString("idOrigemMunicipio")));
			reg.put("idDestinoPais" ,JSONExporter.getJSONInteger(result.getString("idDestinoPais")));
			reg.put("idDestinoMunicipio" ,JSONExporter.getJSONInteger(result.getString("idDestinoMunicipio")));
			reg.put("idMotivoDeEscolhaDaRota" ,JSONExporter.getJSONInteger(result.getString("idMotivoDeEscolhaDaRota")));
			reg.put("frequenciaQtd" ,JSONExporter.getJSONInteger(result.getString("frequenciaQtd")));
			reg.put("frequenciaPeriodo" ,JSONExporter.getJSONString(result.getString("frequenciaPeriodo")));
			reg.put("idPropriedadesDoVeiculo",JSONExporter.getJSONInteger(result.getString("idPropriedadesDoVeiculo")));
			reg.put("placaEstrangeira" ,JSONExporter.getJSONInteger(result.getString("placaEstrangeira")));
			reg.put("idPaisPlacaEstrangeira" ,JSONExporter.getJSONInteger(result.getString("idPaisPlacaEstrangeira")));
			reg.put("idCombustivel" ,JSONExporter.getJSONInteger(result.getString("idCombustivel")));
			reg.put("categoria" ,JSONExporter.getJSONString(result.getString("categoria")));
			reg.put( "possuiReboque",JSONExporter.getJSONInteger(result.getString("possuiReboque")));
			reg.put( "numeroDePessoasNoVeiculo",JSONExporter.getJSONInteger(result.getString("numeroDePessoasNoVeiculo")));
			reg.put("numeroDePessoasATrabalho" ,JSONExporter.getJSONInteger(result.getString("numeroDePessoasATrabalho")));
			reg.put("idRendaMedia" ,JSONExporter.getJSONInteger(result.getString("idRendaMedia")));
			reg.put("idMotivoDaViagem" ,JSONExporter.getJSONInteger(result.getString("idMotivoDaViagem")));
			reg.put("tipoCaminhao" ,JSONExporter.getJSONString(result.getString("tipoCaminhao")));
			reg.put("idTipoDeContainer" ,JSONExporter.getJSONInteger(result.getString("idTipoDeContainer")));
			reg.put("idTipoCarroceria" ,JSONExporter.getJSONInteger(result.getString("idTipoCarroceria")));
			reg.put("rntrc" ,JSONExporter.getJSONString(result.getString("rntrc")));
			reg.put("possuiCargaPerigosa" ,JSONExporter.getJSONInteger(result.getString("possuiCargaPerigosa")));
			reg.put("idNumeroDeRisco" ,JSONExporter.getJSONInteger(result.getString("idNumeroDeRisco")));
			reg.put("idNumeroDaOnu" ,JSONExporter.getJSONInteger(result.getString("idNumeroDaOnu")));
			reg.put("idAgregado" ,JSONExporter.getJSONInteger(result.getString("idAgregado")));
			reg.put("placaVermelha" ,JSONExporter.getJSONInteger(result.getString("placaVermelha")));
			reg.put("idTipoDeViagemOuServico" ,JSONExporter.getJSONInteger(result.getString("idTipoDeViagemOuServico")));
			reg.put("pesoDaCarga" ,JSONExporter.getJSONDouble(result.getString("pesoDaCarga")));
			reg.put("valorDoFrete" ,JSONExporter.getJSONDouble(result.getString("valorDoFrete")));
			reg.put("utilizaParadaEspecial" ,JSONExporter.getJSONInteger(result.getString("utilizaParadaEspecial")));
			reg.put("idProduto" ,JSONExporter.getJSONInteger(result.getString("idProduto")));
			reg.put("idCargaAnterior" ,JSONExporter.getJSONInteger(result.getString("idCargaAnterior")));
			reg.put("valorDaCarga" ,JSONExporter.getJSONDouble(result.getString("valorDaCarga")));
			reg.put("municipioEmbarqueCarga" ,JSONExporter.getJSONInteger(result.getString("municipioEmbarqueCarga")));
			reg.put("idLocalEmbarqueCarga" ,JSONExporter.getJSONInteger(result.getString("idLocalEmbarqueCarga")));
			reg.put("municipioDesembarqueCarga" ,JSONExporter.getJSONInteger(result.getString("municipioDesembarqueCarga")));
			reg.put("idLocalDesembarqueCarga" ,JSONExporter.getJSONInteger(result.getString("idLocalDesembarqueCarga")));
			reg.put("indoPegarCarga" ,JSONExporter.getJSONInteger(result.getString("indoPegarCarga")));
			reg.put("paradaObrigatoriaMunicipio1" ,JSONExporter.getJSONInteger(result.getString("paradaObrigatoriaMunicipio1")));
			reg.put("paradaObrigatoriaMunicipio2" ,JSONExporter.getJSONInteger(result.getString("paradaObrigatoriaMunicipio2")));
			reg.put("idPerguntaExtra" ,JSONExporter.getJSONInteger(result.getString("idPerguntaExtra")));
			
			json.append("dados", reg);
		}
		
		s = json.get("dados").toString().replace("},", "},\n").replace(":[",":\n[");
		return s;
	}
	
}
