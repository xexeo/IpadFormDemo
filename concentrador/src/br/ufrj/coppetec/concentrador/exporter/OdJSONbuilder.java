package br.ufrj.coppetec.concentrador.exporter;

import br.ufrj.coppetec.concentrador.Util;
import java.sql.ResultSet;
import java.util.Date;

import javax.swing.JOptionPane;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;

/**
 * Constrói a representação JSON de um resultado de consulta ao banco com pesquisas Origem/Destino.
 * 
 * @author ludes - PESC - COPPE - ufrj
 * @author Eduardo Mangeli
 * @author Marcelo Areas
 * @author Fabrício Pereira
 * @author Geraldo Xexéo
 */
public class OdJSONbuilder implements JSONBuilder {

	private static Logger logger = LogManager.getLogger(OdJSONbuilder.class);

	/**
	 * Constrói uma representação JSON de uma consulta ao banco
	 * @param result	resultado de uma consulta ao banco de dados interno da aplicação
	 * @return			representação JSON da consulta
	 * @throws Exception 
	 */
	@Override
	public String build(ResultSet result) throws Exception {
		String s;
		JSONObject json = new JSONObject();
		JSONObject reg;
		while (result.next()) {
			try {
				reg = new JSONObject();
				// reg.put("id",JSONExporter.getJSONString(result.getString("id"));
				reg.put("estaNoNote", JSONExporter.getJSONInteger(result.getString("estaNoNote")));
				reg.put("cancelado", JSONExporter.getJSONInteger(result.getString("cancelado")));
				reg.put("idPosto", JSONExporter.getJSONInteger(result.getString("idPosto")));
				reg.put("sentido", JSONExporter.getJSONString(result.getString("sentido")));
				reg.put("idIpad", JSONExporter.getJSONString(result.getString("idIpad")));
				// reg.put("login" ,JSONExporter.getJSONString(result.getString("login"));
				reg.put("dataIniPesq", JSONExporter.getJSONString(result.getString("dataIniPesq")));
				reg.put("dataFimPesq", JSONExporter.getJSONString(result.getString("dataFimPesq")));
				reg.put("placa", JSONExporter.getJSONString(result.getString("placa")));
				reg.put("anoDeFabricacao", JSONExporter.getJSONInteger(result.getString("anoDeFabricacao")));
				reg.put("tipo", JSONExporter.getJSONString(result.getString("tipo")));
				reg.put("idOrigemPais", JSONExporter.getJSONInteger(result.getString("idOrigemPais")));
				reg.put("idOrigemMunicipio", JSONExporter.getJSONInteger(result.getString("idOrigemMunicipio")));
				reg.put("idDestinoPais", JSONExporter.getJSONInteger(result.getString("idDestinoPais")));
				reg.put("idDestinoMunicipio", JSONExporter.getJSONInteger(result.getString("idDestinoMunicipio")));
				reg.put("idMotivoDeEscolhaDaRota", JSONExporter.getJSONInteger(result.getString("idMotivoDeEscolhaDaRota")));
				reg.put("frequenciaQtd", JSONExporter.getJSONInteger(result.getString("frequenciaQtd")));
				reg.put("frequenciaPeriodo", JSONExporter.getJSONString(result.getString("frequenciaPeriodo")));
				reg.put("idPropriedadesDoVeiculo", JSONExporter.getJSONInteger(result.getString("idPropriedadesDoVeiculo")));
				reg.put("placaEstrangeira", JSONExporter.getJSONInteger(result.getString("placaEstrangeira")));
				reg.put("idPaisPlacaEstrangeira", JSONExporter.getJSONInteger(result.getString("idPaisPlacaEstrangeira")));
				reg.put("idCombustivel", JSONExporter.getJSONInteger(result.getString("idCombustivel")));
				reg.put("categoria", JSONExporter.getJSONString(result.getString("categoria")));
				reg.put("possuiReboque", JSONExporter.getJSONInteger(result.getString("possuiReboque")));
				reg.put("numeroDePessoasNoVeiculo", JSONExporter.getJSONInteger(result.getString("numeroDePessoasNoVeiculo")));
				reg.put("numeroDePessoasATrabalho", JSONExporter.getJSONInteger(result.getString("numeroDePessoasATrabalho")));
				reg.put("idRendaMedia", JSONExporter.getJSONInteger(result.getString("idRendaMedia")));
				reg.put("idMotivoDaViagem", JSONExporter.getJSONInteger(result.getString("idMotivoDaViagem")));
				reg.put("tipoCaminhao", JSONExporter.getJSONString(result.getString("tipoCaminhao")));
				reg.put("idTipoDeContainer", JSONExporter.getJSONInteger(result.getString("idTipoDeContainer")));
				reg.put("idTipoCarroceria", JSONExporter.getJSONInteger(result.getString("idTipoCarroceria")));
				reg.put("rntrc", JSONExporter.getJSONString(result.getString("rntrc")));
				reg.put("possuiCargaPerigosa", JSONExporter.getJSONInteger(result.getString("possuiCargaPerigosa")));
				reg.put("idNumeroDeRisco", JSONExporter.getJSONInteger(result.getString("idNumeroDeRisco")));
				reg.put("idNumeroDaOnu", JSONExporter.getJSONInteger(result.getString("idNumeroDaOnu")));
				reg.put("idAgregado", JSONExporter.getJSONInteger(result.getString("idAgregado")));
				reg.put("placaVermelha", JSONExporter.getJSONInteger(result.getString("placaVermelha")));
				reg.put("idTipoDeViagemOuServico", JSONExporter.getJSONInteger(result.getString("idTipoDeViagemOuServico")));
				reg.put("pesoDaCarga", JSONExporter.getJSONDouble(result.getString("pesoDaCarga")));
				reg.put("valorDoFrete", JSONExporter.getJSONDouble(result.getString("valorDoFrete")));
				reg.put("utilizaParadaEspecial", JSONExporter.getJSONInteger(result.getString("utilizaParadaEspecial")));
				reg.put("idProduto", JSONExporter.getJSONInteger(result.getString("idProduto")));
				reg.put("idCargaAnterior", JSONExporter.getJSONInteger(result.getString("idCargaAnterior")));
				reg.put("valorDaCarga", JSONExporter.getJSONDouble(result.getString("valorDaCarga")));
				reg.put("municipioEmbarqueCarga", JSONExporter.getJSONInteger(result.getString("municipioEmbarqueCarga")));
				reg.put("idLocalEmbarqueCarga", JSONExporter.getJSONInteger(result.getString("idLocalEmbarqueCarga")));
				reg.put("municipioDesembarqueCarga", JSONExporter.getJSONInteger(result.getString("municipioDesembarqueCarga")));
				reg.put("idLocalDesembarqueCarga", JSONExporter.getJSONInteger(result.getString("idLocalDesembarqueCarga")));
				reg.put("indoPegarCarga", JSONExporter.getJSONInteger(result.getString("indoPegarCarga")));
				reg.put("paradaObrigatoriaMunicipio1",
						JSONExporter.getJSONInteger(result.getString("paradaObrigatoriaMunicipio1")));
				reg.put("paradaObrigatoriaMunicipio2",
						JSONExporter.getJSONInteger(result.getString("paradaObrigatoriaMunicipio2")));
				reg.put("idPerguntaExtra", JSONExporter.getJSONInteger(result.getString("idPerguntaExtra")));
				reg.put("idPerguntaExtra2", JSONExporter.getJSONInteger(result.getString("idPerguntaExtra2")));
				reg.put("dataExportacao", Util.sdfArq.format(new Date()));
				// reg.put("duracaoPesq", JSONExporter.getJSONInteger(result.getString("duracaoPesq")));

				json.append("dados", reg);
			} catch (Exception e) {
				logger.error(String.format("Erro ao exportar dados da pesquisa OD no tratamento do registro: %s",
						result.getString("id")), e);
				JOptionPane.showMessageDialog(null, "Erro ao exportar dados:\n" + e.getMessage(), "Erro na exportação de dados.",
						JOptionPane.ERROR_MESSAGE);
			}
		}

		s = json.get("dados").toString().replace("},", "},\n").replace(":[", ":\n[");
		return s;
	}

}
