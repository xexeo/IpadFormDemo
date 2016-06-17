/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrj.coppetec.concentrador.exporter;

import java.sql.ResultSet;

import javax.swing.JOptionPane;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;

/**
 *
 * @author mangeli
 */
public class PvJSONbuilder implements JSONBuilder {

	private static Logger logger = LogManager.getLogger(PvJSONbuilder.class);

	@Override
	public String build(ResultSet result) throws Exception {
		String s;
		JSONObject json = new JSONObject();
		JSONObject reg;
		while (result.next()) {
			try {
				reg = new JSONObject();
				reg.put("Posto", result.getInt("posto"));
				reg.put("Pista", result.getString("pista"));
				reg.put("Data", result.getString("data"));
				reg.put("Hora", result.getString("hora"));
				reg.put("Sentido", result.getString("Sentido"));
				reg.put("Local", result.getString("local"));
				reg.put("Pesquisador1", result.getString("pesquisador1"));
				reg.put("Pesquisador2", result.getString("pesquisador2"));
				reg.append("DadosVolumetrica", new JSONObject(result.getString("P1")));
				reg.append("DadosVolumetrica", new JSONObject(result.getString("P3")));
				reg.append("DadosVolumetrica", new JSONObject(result.getString("P2")));
				reg.append("DadosVolumetrica", new JSONObject(result.getString("M")));
				reg.append("DadosVolumetrica", new JSONObject(result.getString("O1")));
				reg.append("DadosVolumetrica", new JSONObject(result.getString("O2")));
				reg.append("DadosVolumetrica", new JSONObject(result.getString("O3")));
				reg.append("DadosVolumetrica", new JSONObject(result.getString("C1")));
				reg.append("DadosVolumetrica", new JSONObject(result.getString("C2")));
				reg.append("DadosVolumetrica", new JSONObject(result.getString("C3")));
				reg.append("DadosVolumetrica", new JSONObject(result.getString("C4")));
				reg.append("DadosVolumetrica", new JSONObject(result.getString("C5")));
				reg.append("DadosVolumetrica", new JSONObject(result.getString("S1")));
				reg.append("DadosVolumetrica", new JSONObject(result.getString("S2")));
				reg.append("DadosVolumetrica", new JSONObject(result.getString("S3")));
				reg.append("DadosVolumetrica", new JSONObject(result.getString("S4")));
				reg.append("DadosVolumetrica", new JSONObject(result.getString("S5")));
				reg.append("DadosVolumetrica", new JSONObject(result.getString("S6")));
				reg.append("DadosVolumetrica", new JSONObject(result.getString("SE1")));
				reg.append("DadosVolumetrica", new JSONObject(result.getString("SE2")));
				reg.append("DadosVolumetrica", new JSONObject(result.getString("SE3")));
				reg.append("DadosVolumetrica", new JSONObject(result.getString("SE4")));
				reg.append("DadosVolumetrica", new JSONObject(result.getString("SE5")));
				reg.append("DadosVolumetrica", new JSONObject(result.getString("R1")));
				reg.append("DadosVolumetrica", new JSONObject(result.getString("R2")));
				reg.append("DadosVolumetrica", new JSONObject(result.getString("R3")));
				reg.append("DadosVolumetrica", new JSONObject(result.getString("R4")));
				reg.append("DadosVolumetrica", new JSONObject(result.getString("R5")));
				reg.append("DadosVolumetrica", new JSONObject(result.getString("R6")));

				json.append("dados", reg);
			} catch (Exception e) {
				logger.error("Erro ao exportar dados da PV.", e);
				JOptionPane.showMessageDialog(null, "Erro ao exportar dados:\n" + e.getMessage(), "Erro na exportação de dados.",
						JOptionPane.ERROR_MESSAGE);
			}
		}
		s = json.get("dados").toString().replace("},", "},\n").replace(":[", ":\n[");
		return s;
	}

}
