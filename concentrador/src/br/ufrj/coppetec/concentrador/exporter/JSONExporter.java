/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrj.coppetec.concentrador.exporter;

import br.ufrj.coppetec.concentrador.database.myDB;
import java.io.File;
import java.io.FileWriter;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import org.json.JSONException;
import org.json.JSONObject;


/**
 *
 * @author mangeli
 */
public class JSONExporter {
	private File file;
	private File tmpFile;
	private DbTable table;
	static public enum WhatToExport {ALL, NOT_SENT}
	private JSONBuilder jsonBuilder;
	static public enum DbTable { PV("voltable"),OD("odtable");
									private final String text;
									DbTable(final String s){
										this.text = s;
									}
									public String toString(){
										return text;
									}
									public String sql(){
										String r = "SELECT * FROM ";
										if (text.equals("odtable")){
											r += "odtable WHERE cancelado=0";
										} else {
											r+= "voltable";
										}
										return r;
									}
								}
	
	public JSONExporter(File f, DbTable t){
		this.file = f;
		this.table = t;
		this.tmpFile = new File("temp.json");
		switch(t){
			case PV:
				jsonBuilder = new PvJSONbuilder();
				break;
			case OD:
				jsonBuilder = new OdJSONbuilder();
				break;
		}
	}
	
	/*public void exportAll(){
		myDB database;
		ResultSet result; 
		FileWriter writer;	
		try{
			database = new myDB();
			database.setStatement();
			result = database.executeQuery("SELECT * FROM odtable;");
			writer = new FileWriter(file);
			//System.out.println(buildJSON(result));
			writer.write(buildJSON(result));
			writer.close();
			result.close();
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, "Erro ao exportar dados da pesquisa volumétrica:\n" + e.getMessage(), 
					"Erro na exportação de dados.", JOptionPane.ERROR_MESSAGE);
			return;
		}
	}*/
	
	public void export(WhatToExport what){
		myDB database;
		ResultSet result; 
		FileWriter writer;
		String qry;
		switch(what){
			case ALL:
				qry = this.table.sql();
				break;
			case NOT_SENT:
				if (this.table == table.OD){
					qry = this.table.sql() + " AND enviado=0;";
				} else {
					qry = this.table.sql() + " WHERE enviado=0;";
				}
				
				break;
			default:
				qry = this.table.sql();
		}
		
		try{
			database = new myDB();
			database.setStatement();
			result = database.executeQuery(qry);
			
			//writer = new FileWriter(file);
			writer = new FileWriter(tmpFile);
			writer.write(jsonBuilder.build(result));
			result.close();
			writer.close();
			Zipper zipper = new Zipper(tmpFile, file);
			zipper.zipIt();
			database.executeStatement("UPDATE " + this.table.toString() + " SET enviado=1 WHERE enviado=0;");
			
			JOptionPane.showMessageDialog(null, "Arquivo " + this.file.getName() + " exportado com sucesso.", 
					"Exportação de dados.", JOptionPane.INFORMATION_MESSAGE);
		}catch (JSONException je){
			//probably empty table
			JOptionPane.showMessageDialog(null, "Não existem dados para exportação.", 
					"Exportação de dados.", JOptionPane.INFORMATION_MESSAGE);
			return;
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, "Erro ao exportar dados:\n" + e.getMessage(), 
					"Erro na exportação de dados.", JOptionPane.ERROR_MESSAGE);
			return;
		}
	}
	
	static public Object getJSONInteger(String val){
		Object r;
		if (val == null){
			r = JSONObject.NULL;
		} else {
			r = new Integer(val);
		}
		
		return r;
	}
	
	static public Object getJSONDouble(String val){
		Object r;
		if (val == null){
			r = JSONObject.NULL;
		} else {
			r = JSONObject.stringToValue(val);
		}
		return r;
	}
	
	static public Object getJSONString(String val){
		Object r;
		if (val == null || val.equals("null")){
			r = JSONObject.NULL;
		} else {
			r = val;
		}
		
		return r;
	}
	
}
