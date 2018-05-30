package br.ufrj.coppetec.concentrador;

import javax.swing.JOptionPane;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *	Janela para entrada dos dados de acesso (login) ao sistema.
 * 
 * @author ludes - PESC - COPPE - ufrj
 * @author Eduardo Mangeli
 * @author Marcelo Areas
 * @author Fabrício Pereira
 * @author Geraldo Xexéo
 */
@SuppressWarnings("serial")
public class LoginJanela extends javax.swing.JDialog {

	private static Logger logger = LogManager.getLogger(LoginJanela.class);

	/**
	 * Cria a janela de login.
	 * @param parent Janela principal para referência para posicionamento e comportamento.
	 * @param modal Flag para impor comportamento modal (impedir o uso de outras partes do sistema quando a janela é exibida).
	 */
	public LoginJanela(java.awt.Frame parent, boolean modal) {
		super(parent, modal);
		initComponents();
	}

	/**
	 * Termina o programa e registra.
	 * No caso de o usuário cancelar o login, o método registra o evento no log de atividades e encerra o programa.
	 */
	private void exitProgram() {
		logger.info("Usuário cancelou o login");
		System.exit(0);
	}

	/**
	 * Método que valida o par usuário e senha informados.
	 * @return true No caso de o par informado ser válido.
	 */
	private boolean validateLogin() {
		LoginController controller = new LoginController("/logins/users.json");
		return controller.validateLogin(txtUser.getText(), String.copyValueOf(txtPassword.getPassword()));
	}

	/**
	 * Método utilizado pelo construtor para iniciar os componentes da tela
	 * 
	 * WARNING: Não modifique esse método. O seu conteúdo é sempre gerado automaticamente pelo gerador de formulários
	 * da IDE NetBeans.
	 */
	// <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
	private void initComponents() {

		jLabel1 = new javax.swing.JLabel();
		jLabel2 = new javax.swing.JLabel();
		txtUser = new javax.swing.JTextField();
		txtPassword = new javax.swing.JPasswordField();
		btnOK = new javax.swing.JToggleButton();
		btnCancel = new javax.swing.JButton();

		setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
		setTitle("Login PNCT");
		setAlwaysOnTop(true);
		setIconImage(null);
		setLocationByPlatform(true);
		setModal(true);

		jLabel1.setText("Usuário:");

		jLabel2.setText("Senha:");

		btnOK.setText("OK");
		btnOK.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnOKActionPerformed(evt);
			}
		});

		btnCancel.setText("Cancel");
		btnCancel.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnCancelActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addContainerGap()
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addGroup(layout.createSequentialGroup()
										.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
												.addComponent(jLabel1).addComponent(jLabel2))
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
												.addComponent(txtUser).addComponent(txtPassword)))
								.addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
										layout.createSequentialGroup().addGap(0, 245, Short.MAX_VALUE).addComponent(btnCancel)
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(
														btnOK)))
						.addContainerGap()));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addContainerGap()
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jLabel1)
								.addComponent(txtUser, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jLabel2)
								.addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(btnOK)
								.addComponent(btnCancel))
						.addContainerGap(13, Short.MAX_VALUE)));

		pack();
	}// </editor-fold>//GEN-END:initComponents

	
	/**
	 * Método disparado pelo evento de clicar no botão para validar o login.
	 * @param evt evento de clique no botão
	 */
	private void btnOKActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnOKActionPerformed

		if (validateLogin()) {
			LoginJanela.this.dispose();
		} else {
			JOptionPane.showMessageDialog(LoginJanela.this, "Usuário ou login informados não puderam ser validados.",
					"Erro no login", JOptionPane.ERROR_MESSAGE);
		}

	}// GEN-LAST:event_btnOKActionPerformed

	/**
	 * Método disparado pelo evento de clicar no botão de cancelar.
	 * @param evt evento de clique no botão
	 */
	private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnCancelActionPerformed
		this.exitProgram();
	}// GEN-LAST:event_btnCancelActionPerformed

	/**
	 * Método utilizado apenas durante o desenvolvimento para a realização de testes
	 * 
	 * @param args Executado sem argumentos
	 */
	@Deprecated
	public static void main(String args[]) {
		/* Set the Nimbus look and feel */
		// <editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
		/*
		 * If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel. For details see
		 * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
		 */
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			logger.fatal("ERRO FATAL: a aplicação será finalizada.", ex);
		} catch (InstantiationException ex) {
			logger.fatal("ERRO FATAL: a aplicação será finalizada.", ex);
		} catch (IllegalAccessException ex) {
			logger.fatal("ERRO FATAL: a aplicação será finalizada.", ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			logger.fatal("ERRO FATAL: a aplicação será finalizada.", ex);
		}
		// </editor-fold>

		/* Create and display the dialog */
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				LoginJanela dialog = new LoginJanela(new javax.swing.JFrame(), true);
				dialog.addWindowListener(new java.awt.event.WindowAdapter() {
					@Override
					public void windowClosing(java.awt.event.WindowEvent e) {
						System.exit(0);
					}
				});
				dialog.setVisible(true);
			}
		});
	}

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JButton btnCancel;
	private javax.swing.JToggleButton btnOK;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JPasswordField txtPassword;
	private javax.swing.JTextField txtUser;
	// End of variables declaration//GEN-END:variables
}
