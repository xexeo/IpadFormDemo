package br.ufrj.coppetec.concentrador;

import java.io.InputStream;
import java.text.ParseException;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

import javax.swing.JOptionPane;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
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

	private JSONObject loginsJson;
	private Map<String, Map<String, String>> logins;
	private static Logger logger = LogManager.getLogger(LoginController.class);
	private static int LEN_LOGIN_USER = 3;

	public LoginController() {
		this("/logins/users.json");
	}

	public LoginController(String loginsFile) {
		this.buildLoginsObject(loginsFile);
	}

	private void buildLoginsObject(String loginsFile) {
		try {
			StringBuilder result = new StringBuilder("");
			InputStream loginsFileStream = this.getClass().getResourceAsStream(loginsFile);
			Scanner scanner = new Scanner(loginsFileStream);
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				result.append(line).append("\n");
			}
			scanner.close();
			loginsJson = new JSONObject(result.toString());
			loadMapLogins(loginsJson.getJSONArray("logins"));
		} catch (Exception e) {
			logger.error("Erro ao ler o arquivo de login", e);
			JOptionPane.showMessageDialog(null, "Erro lendo o arquivo de login." + e.getMessage());
		}
	}

	private void loadMapLogins(JSONArray arrayLoginsJson) {
		logins = new TreeMap<String, Map<String, String>>();
		for (Object objLoginJson : arrayLoginsJson) {
			JSONObject loginJson = (JSONObject) objLoginJson;
			Map<String, String> mapInfoByLogin = new LinkedHashMap<String, String>(loginJson.length() - 1);
			String usr = loginJson.getString("usr");
			for (String key : loginJson.keySet()) {
				if (!key.equals("usr")) {
					Object value = loginJson.get(key);
					if (value instanceof String) {
						mapInfoByLogin.put(key, (String) value);
					} else if (value instanceof Boolean) {
						mapInfoByLogin.put(key, Boolean.toString(loginJson.getBoolean(key)));
					}
				}
			}
			logins.put(usr, mapInfoByLogin);
		}

	}

	public boolean validateLogin(String user, String pwd) {
		boolean isValidLogin = false;

		if (verificaUserPwdProducao(user, pwd)) {
			Concentrador.posto = user;
			Concentrador.trecho = logins.get(user).get("trecho");
			isValidLogin = true;
		} else if (verificaUserPwdTreinamento(user, pwd)) {
			user = user.substring(0, 3);
			Concentrador.treinamento = true;
			Concentrador.posto = user;
			Concentrador.trecho = String.format("TREINAMENTO (%s)", logins.get(user).get("trecho"));
			isValidLogin = true;
		}

		if (isValidLogin == true) {
			logger.info("Login" + (Concentrador.treinamento ? " de TREINAMENTO " : " ") + "do usuário COM sucesso: " + user);
		} else {
			logger.info("Tentativa de login SEM sucesso para o usuário: " + user);
		}
		return isValidLogin;
	}

	private boolean verificaUserPwdProducao(String user, String pwd) {
		if (user.length() == LEN_LOGIN_USER) {
			try {
				if (Util.isInValidPeriod(new Date())) { /*
														 * Se a data atual não vale de nada (afirmação do Mangeli), então basta
														 * comentar/remover o IF e permanecer com o comando contido no IF.
														 */
					return verificaUserPwd(user, pwd);
				}
			} catch (ParseException e) {
				JOptionPane.showMessageDialog(null, "Erro ao carregar datas válidas." + e.getMessage());
			}
		}
		return false;
	}

	private boolean verificaUserPwdTreinamento(String user, String pwd) {
		if ((user.length() == LEN_LOGIN_USER + 1) && (Character.toUpperCase(user.charAt(LEN_LOGIN_USER)) == 'T')) {
			try {
				if (!Util.isInValidPeriod(new Date())) {/*
														 * Se a data atual não vale de nada (afirmação do Mangeli), então basta
														 * comentar/remover o IF e permanecer com o comando contido no IF.
														 */
					return verificaUserPwd(user.substring(0, LEN_LOGIN_USER), pwd);
				}
			} catch (ParseException e) {
				JOptionPane.showMessageDialog(null, "Erro ao carregar datas de treinamento." + e.getMessage());
			}
		}
		return false;
	}

	private boolean verificaUserPwd(String user, String pwd) {
		Map<String, String> loginInfos = logins.get(user);
		if (loginInfos != null) {
			return pwd.equals(loginInfos.get("pwd"));
		}
		return false;
	}

}
