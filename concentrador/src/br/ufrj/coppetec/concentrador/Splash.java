/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrj.coppetec.concentrador;

import javax.swing.ImageIcon;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Tela exibida durante a inicialização da aplicação
 * 
 * @author ludes - PESC - COPPE - ufrj
 * @author Eduardo Mangeli
 * @author Marcelo Areas
 * @author Fabrício Pereira
 * @author Geraldo Xexéo
 */
public class Splash extends javax.swing.JFrame {

	private static Logger logger = LogManager.getLogger(Splash.class);

	/**
	 * Cria a tela de inicialização
	 */
	public Splash() {
		initComponents();

	}

	/**
	 * Ajusta a versão do programa a ser exibida na tela inicial
	 * 
	 * @param version Versão do programa
	 */
	public void setVersionLabet(String version) {
		lblVersao.setText("Versão: " + version);
	}

	/**
	 * Método utilizado pelo construtor para iniciar os componentes da tela
	 * 
	 * WARNING: Não modifique esse método. O seu conteúdo é sempre gerado automaticamente pelo gerador de formulários
	 * da IDE NetBeans.
	 */
	@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        lblVersao = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setIconImage(new ImageIcon(Splash.this.getClass().getResource("/images/icon.png")).getImage());
        setUndecorated(true);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/splash.png"))); // NOI18N

        lblVersao.setText("Versão: xxx");
        lblVersao.setAlignmentX(0.5F);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblVersao, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblVersao)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

	/**
	 * Método utilizado apenas durante o desenvolvimento para a realização de testes
	 * 
	 * @param args Executado sem argumentos
	 * 
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

		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new Splash().setVisible(true);
			}
		});
	}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lblVersao;
    // End of variables declaration//GEN-END:variables
}
