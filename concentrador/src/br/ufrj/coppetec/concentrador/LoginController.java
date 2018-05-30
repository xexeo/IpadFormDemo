package br.ufrj.coppetec.concentrador;

import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

import javax.swing.JOptionPane;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *	Classe responsável pelo controle de acesso ao sistema.
 * 
 * @author ludes - PESC - COPPE - ufrj
 * @author Eduardo Mangeli
 * @author Marcelo Areas
 * @author Fabrício Pereira
 * @author Geraldo Xexéo
 */
public class LoginController {

	private JSONObject loginsJson;												///< Objeto com os identificadores dos postos, suas respectivas senhas e outras informações
	private Map<String, Map<String, String>> logins;							///< Objeto com logins e informaçoẽs dos postos
	private static Logger logger = LogManager.getLogger(LoginController.class);	
	private static int LEN_LOGIN_USER = 3;										///< Comprimento do login do usuário

	/**
	 * Construtor que usa o arquivo de logins padrão.
	 */
	public LoginController() {
		this("/logins/users.json");
	}

	/**
	 * Construtor que permite configurar o caminho do arquivo de logins.
	 * @param loginsFile Caminho para o arquivo de logins e senhas
	 */
	public LoginController(String loginsFile) {
		this.buildLoginsObject(loginsFile);
	}

	/**
	 * Constrói a representação interna do arquivo de logins.
	 * @param loginsFile Caminho para o arquivo de logins e senhas.
	 */
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

	/**
	 * Constrói o mapeamento das informações de login mantendo como chave o número do posto (identificação do usuário).
	 * @param arrayLoginsJson representação interna do arquivo de logins.
	 */
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

	/**
	 * Valida o par usuário e senha informados.
	 * O método verifica se o usuário informado é um usuário do perído de treinamento ou do período de produção real.
	 * Em ambos os casos, ajusta os valores de variáveis de configuração como _posto_, _trecho_ e se o sistema está rodando em modo 
	 * de _treinamento_.
	 * A funcionalidade do método é implementada pelos métodos privados __verificaUserPwdProducao__, __verificaUserPwdTreinamento__
	 *  e __verificaUserPwd__.
	 * @param user Usuário
	 * @param pwd Senha
	 * @return True se o par usuário e senha é o adequado
	 */
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
			Concentrador.trecho = String.format("TREINAMENTO (%s)",
					(!user.equals(Concentrador.configuration.getProperty("trainingUser")) ? logins.get(user).get("trecho")
							: "Trecho de teste"));
			isValidLogin = true;
		}

		if (isValidLogin == true) {
			logger.info("Login" + (Concentrador.treinamento ? " de TREINAMENTO " : " ") + "do usuário COM sucesso: " + user);
		} else {
			logger.info("Tentativa de login SEM sucesso para o usuário: " + user);
		}
		return isValidLogin;
	}

	/**
	 * Verifica se o usuário e a senha informados são um par válido para o período de produção.
	 * @param user usuário
	 * @param pwd senha
	 * @return true se o par usuário e senha for válido para o período de produção.
	 */
	private boolean verificaUserPwdProducao(String user, String pwd) {
		if (user.length() == LEN_LOGIN_USER) {
			// try {
			// /* O IF abaixo está comentado por não termos confiança na data do sistema. */
			// if (Util.isInValidPeriod(new Date())) {
			return verificaUserPwd(user, pwd);
			// }
			// } catch (ParseException e) { /* O TRY/CATCH acompanha a condição do IF. */
			// JOptionPane.showMessageDialog(null, "Erro ao carregar datas válidas." + e.getMessage());
			// }
		}
		return false;
	}

	/**
	 * Verifica se o usuário e a senha informados são um par válido para o período de treinamento.
	 * @param user usuário
	 * @param pwd senha
	 * @return true se o par usuário e senha for válido para o período de treinamento.
	 */
	private boolean verificaUserPwdTreinamento(String user, String pwd) {
		if ((user.equals(Concentrador.configuration.getProperty("trainingUser"))
				|| user.equalsIgnoreCase(Concentrador.configuration.getProperty("trainingUser") + "T"))
				&& pwd.equals(Concentrador.configuration.getProperty("trainingPassword"))) {
			return true;
		} else if ((user.length() == LEN_LOGIN_USER + 1) && (Character.toUpperCase(user.charAt(LEN_LOGIN_USER)) == 'T')) {
			// try {
			// /* O IF abaixo está comentado por não termos confiança na data do sistema. */
			// if (!Util.isInValidPeriod(new Date())) {
			return verificaUserPwd(user.substring(0, LEN_LOGIN_USER), pwd);
			// }
			// } catch (ParseException e) { /* O TRY/CATCH acompanha a condição do IF. */
			// JOptionPane.showMessageDialog(null, "Erro ao carregar datas de treinamento." + e.getMessage());
			// }
		}
		return false;
	}

	/**
	 * Verifica se o par usuário senha informado é válido.
	 * @param user usuário
	 * @param pwd senha
	 * @return true se o par informado for válido
	 */
	private boolean verificaUserPwd(String user, String pwd) {
		Map<String, String> loginInfos = logins.get(user);
		if (loginInfos != null) {
			return pwd.equals(loginInfos.get("pwd"));
		}
		return false;
	}

}
