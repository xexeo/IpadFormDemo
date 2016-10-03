package br.ufrj.coppetec.concentrador;

import java.io.InputStream;
import java.util.Scanner;
import javax.swing.JOptionPane;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author mangeli
 */
public class LoginController {
	
	private JSONObject logins;
	private static Logger logger = LogManager.getLogger(LoginController.class);
	
	public LoginController(){
		this.buildLoginsObject("/logins/users.json");
	}
	
	public LoginController(String loginsFile){
		this.buildLoginsObject(loginsFile);
	}
	
	private void buildLoginsObject(String loginsFile){
		try{
			StringBuilder result = new StringBuilder("");
			InputStream loginsFileStream = this.getClass().getResourceAsStream(loginsFile);
			Scanner scanner = new Scanner(loginsFileStream);
			while (scanner.hasNextLine()){
				String line = scanner.nextLine();
					result.append(line).append("\n");
			}
			scanner.close();
			logins = new JSONObject(result.toString());
		}catch (Exception e) {
			logger.error("Erro ao ler o arquivo de login", e);
			JOptionPane.showMessageDialog(null, "Erro lendo o arquivo de login." + e.getMessage());
		}
	}
	
	public boolean validateLogin(String user, String pwd) {
		boolean r = false;
		for (Object dados : logins.getJSONArray("logins")) {

			if (user.equals(((JSONObject) dados).getString("usr"))
					&& pwd.equals(((JSONObject) dados).getString("pwd"))) {
				Concentrador.trecho = ((JSONObject) dados).getString("trecho");
				Concentrador.posto = ((JSONObject) dados).getString("usr");
				r = true;
			}
		}
		if (r == false){
			logger.info("Tentativa de login SEM sucesso para o usuário: " + user);
		} else {
			logger.info("Login do usuário COM sucesso: " + user);
		}
		return r;
	}
}
