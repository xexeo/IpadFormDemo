/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrj.coppetec.concentrador.exporter;

import br.ufrj.coppetec.concentrador.Janela;
import java.io.File;
import java.io.FileWriter;
import java.sql.ResultSet;

import javax.swing.JOptionPane;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;

import br.ufrj.coppetec.concentrador.database.myDB;
import java.awt.BorderLayout;
import java.awt.Dialog;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingWorker;

/**
 *
 * @author mangeli
 */
public class JSONExporter {

	private static Logger logger = LogManager.getLogger(JSONExporter.class);

	private File file;
	private File tmpFile;
	private DbTable table;
	private Janela janela;

	static public enum WhatToExport {
		ALL, NOT_SENT
	}

	private JSONBuilder jsonBuilder;

	static public enum DbTable {
		PV("voltable"), OD("odtable");
		private final String text;

		DbTable(final String s) {
			this.text = s;
		}

		public String toString() {
			return text;
		}

		public String sql() {
			String r = "SELECT * FROM ";
			if (text.equals("odtable")) {
				r += "odtable WHERE cancelado=0";
			} else {
				r += "voltable";
			}
			return r;
		}
	}

	public JSONExporter(File f, DbTable t, Janela janela) {
		this.file = f;
		this.table = t;
		this.janela = janela;
		this.tmpFile = new File("temp.json");
		switch (t) {
		case PV:
			jsonBuilder = new PvJSONbuilder();
			break;
		case OD:
			jsonBuilder = new OdJSONbuilder();
			break;
		}
	}

	
	public void export(WhatToExport what){
		
	
		SwingWorker<Boolean, Void> mySwingWorker = new SwingWorker<Boolean, Void>(){
         
			@Override
			protected Boolean doInBackground() throws Exception {
				return JSONExporter.this.backgroundExport(what);
			}
			
		};
		
		final JDialog dialog = new JDialog(this.janela, "Dialog", Dialog.ModalityType.APPLICATION_MODAL);

		mySwingWorker.addPropertyChangeListener(new PropertyChangeListener() {

			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				if (evt.getPropertyName().equals("state")) {
					if (evt.getNewValue() == SwingWorker.StateValue.DONE) {
						dialog.dispose();
						try{
							if(mySwingWorker.get() == true){
							JOptionPane.showMessageDialog(janela, "Arquivo " + JSONExporter.this.file.getName() + " exportado com sucesso.", "Exportação de dados.", JOptionPane.INFORMATION_MESSAGE);
							}
						}catch (Exception e){
							logger.error("Error exportando dados: ", e);
						}
						
					}
				}
			}

			
		});
		
		mySwingWorker.execute();
	  
		
		JProgressBar progressBar = new JProgressBar();
		progressBar.setIndeterminate(true);
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(progressBar, BorderLayout.CENTER);
		panel.add(new JLabel("Aguarde a exportação dos dados......."), BorderLayout.PAGE_START);
		dialog.add(panel);
		dialog.pack();
		dialog.setLocationRelativeTo(this.janela);
		dialog.setVisible(true);
			
	}
	

	private Boolean backgroundExport(WhatToExport what) {
		myDB database;
		ResultSet result;
		FileWriter writer;
		String qry;
		switch (what) {
		case ALL:
			qry = this.table.sql();
			break;
		case NOT_SENT:
			if (this.table == table.OD) {
				qry = this.table.sql() + " AND enviado=0;";
			} else {
				qry = this.table.sql() + " WHERE enviado=0;";
			}

			break;
		default:
			qry = this.table.sql();
		}

		try {
			database = myDB.getInstance();
			database.setStatement();
			result = database.executeQuery(qry);

			// writer = new FileWriter(file);
			writer = new FileWriter(tmpFile);
			writer.write(jsonBuilder.build(result));
			result.close();
			writer.close();
			Zipper zipper = new Zipper(tmpFile, file);
			zipper.zipIt();
			database.executeStatement("UPDATE " + this.table.toString() + " SET enviado=1 WHERE enviado=0;");
			tmpFile.delete();
			
		} catch (JSONException je) {
			logger.warn("Não existem dados para exportação", je);
			// probably empty table
			JOptionPane.showMessageDialog(janela, "Não existem dados para exportação.", "Exportação de dados.",
					JOptionPane.INFORMATION_MESSAGE);
			return false;
		} catch (Exception e) {
			logger.error("Erro ao exportar dados.", e);
			JOptionPane.showMessageDialog(janela, "Erro ao exportar dados:\n" + e.getMessage(), "Erro na exportação de dados.",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}
		
		return true;
	}

	static public Object getJSONInteger(String val) {
		Object r;
		if (val == null) {
			r = JSONObject.NULL;
		} else {
			r = new Integer(val);
		}

		return r;
	}

	static public Object getJSONDouble(String val) {
		Object r;
		if (val == null) {
			r = JSONObject.NULL;
		} else {
			r = JSONObject.stringToValue(val);
		}
		return r;
	}

	static public Object getJSONString(String val) {
		Object r;
		if (val == null || val.equals("null")) {
			r = JSONObject.NULL;
		} else {
			r = val;
		}

		return r;
	}

}
