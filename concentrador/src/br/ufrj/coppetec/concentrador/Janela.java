/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrj.coppetec.concentrador;

import br.ufrj.coppetec.concentrador.database.ImportedDB;
import br.ufrj.coppetec.concentrador.database.PVregister;
import br.ufrj.coppetec.concentrador.database.myDB;
import br.ufrj.coppetec.concentrador.exporter.JSONExporter;
import java.awt.Color;
import java.awt.Component;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Locale;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.filechooser.FileFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;



/**
 *
 * @author ludes
 */
public class Janela extends javax.swing.JFrame {

    /**
     * Creates new form Janela
     */
    public Janela() {
		//quando estava usando tabelas
        numeros = new DefaultTableCellRenderer();
        numeros.setHorizontalAlignment(SwingConstants.CENTER);
        numeros.setVerticalAlignment(SwingConstants.CENTER);
        
        imagens = new ImagemRenderer();
        imagens.setHorizontalAlignment(SwingConstants.CENTER);
        
		intVerifier = new InputVerifier() {
			public boolean verify(JComponent comp) {
				boolean returnValue;
				JTextField textField = (JTextField) comp;
				try {
					Integer.parseInt(textField.getText());
					returnValue = true;
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(Janela.this, "Apenas números inteiros são permitidos como entrada", "Entrada incorreta", JOptionPane.ERROR_MESSAGE);
					returnValue = false;
				}
				return returnValue;
			}
		};
		
        initComponents();
        initFieldValues();
		
    }
    
	private void initFieldValues(){
		
		//leves
		JTextField[] p1Fields = {tl0,tl1,tl2,tl3,tl4,tl5,tl6,tl7};
		JTextField[] p3Fields ={tl8,tl9,tl10,tl11,tl12,tl13,tl14,tl15};
		JTextField[] p2Fields = {tl16,tl17,tl18,tl19,tl20,tl21,tl22,tl23};
		JTextField[] mFields = {tl24,tl25,tl26,tl27,tl28,tl29,tl30,tl31};
		JTextField[] o1Fields = {tl32,tl33,tl34,tl35,tl36,tl37,tl38,tl39};
		JTextField[] o2Fields = {tl40,tl41,tl42,tl43,tl44,tl45,tl46,tl47};
		JTextField[] o3Fields = {tl48,tl49,tl50,tl51,tl52,tl53,tl54,tl55};
		JTextField[] c1Fields = {tl56,tl57,tl58,tl59,tl60,tl61,tl62,tl63};
		JTextField[] c2Fields = {tl64,tl65,tl66,tl67,tl68,tl69,tl70,tl71};
		JTextField[] c3Fields = {tl72,tl73,tl74,tl75,tl76,tl77,tl78,tl79};
		JTextField[] c4Fields = {tl80,tl81,tl82,tl83,tl84,tl85,tl86,tl87};
		JTextField[] c5Fields = {tl88,tl89,tl90,tl91,tl92,tl93,tl94,tl95};
		//pesados
		JTextField[] s1Fields = {tp0,tp1,tp2,tp3,tp4,tp5,tp6,tp7};
		JTextField[] s2Fields = {tp8,tp9,tp10,tp11,tp12,tp13,tp14,tp15};
		JTextField[] s3Fields = {tp16,tp17,tp18,tp19,tp20,tp21,tp22,tp23};
		JTextField[] s4Fields = {tp24,tp25,tp26,tp27,tp28,tp29,tp30,tp31};
		JTextField[] s5Fields = {tp32,tp33,tp34,tp35,tp36,tp37,tp38,tp39};
		JTextField[] s6Fields = {tp40,tp41,tp42,tp43,tp44,tp45,tp46,tp47};
		JTextField[] se1Fields = {tp48,tp49,tp50,tp51,tp52,tp53,tp54,tp55};
		JTextField[] se2Fields = {tp56,tp57,tp58,tp59,tp60,tp61,tp62,tp63};
		JTextField[] se3Fields = {tp64,tp65,tp66,tp67,tp68,tp69,tp70,tp71};
		JTextField[] se4Fields = {tp72,tp73,tp74,tp75,tp76,tp77,tp78,tp79};
		JTextField[] se5Fields = {tp80,tp81,tp82,tp83,tp84,tp85,tp86,tp87};
		JTextField[] r1Fields = {tp88,tp89,tp90,tp91,tp92,tp93,tp94,tp95};
		JTextField[] r2Fields = {tp96,tp97,tp98,tp99,tp100,tp101,tp102,tp103};
		JTextField[] r3Fields = {tp104,tp105,tp106,tp107,tp108,tp109,tp110,tp111};
		JTextField[] r4Fields = {tp112,tp113,tp114,tp115,tp116,tp117,tp118,tp119};
		JTextField[] r5Fields = {tp120,tp121,tp122,tp123,tp124,tp125,tp126,tp127};
		JTextField[] r6Fields = {tp128,tp129,tp130,tp131,tp132,tp133,tp134,tp135};
		
		fieldsMap = new HashMap<String, JTextField[]>();
		fieldsMap.put("P1", p1Fields);
		fieldsMap.put("P3", p3Fields);
		fieldsMap.put("P2", p2Fields);
		fieldsMap.put("M", mFields);
		fieldsMap.put("O1", o1Fields);
		fieldsMap.put("O2", o2Fields);
		fieldsMap.put("O3", o3Fields);
		fieldsMap.put("C1", c1Fields);
		fieldsMap.put("C2", c2Fields);
		fieldsMap.put("C3", c3Fields);
		fieldsMap.put("C4", c4Fields);
		fieldsMap.put("C5", c5Fields);
		fieldsMap.put("S1", s1Fields);
		fieldsMap.put("S2", s2Fields);
		fieldsMap.put("S3", s3Fields);
		fieldsMap.put("S4", s4Fields);
		fieldsMap.put("S5", s5Fields);
		fieldsMap.put("S6", s6Fields);
		fieldsMap.put("SE1", se1Fields);
		fieldsMap.put("SE2", se2Fields);
		fieldsMap.put("SE3", se3Fields);
		fieldsMap.put("SE4", se4Fields);
		fieldsMap.put("SE5", se5Fields);
		fieldsMap.put("R1", r1Fields);
		fieldsMap.put("R2", r2Fields);
		fieldsMap.put("R3", r3Fields);
		fieldsMap.put("R4", r4Fields);
		fieldsMap.put("R5", r5Fields);
		fieldsMap.put("R6", r6Fields);
		
		for(String s : volFieldsNames){
			for(JTextField f : fieldsMap.get(s)){
				f.setText("0");
				f.addFocusListener(new java.awt.event.FocusAdapter() {
					public void focusGained(java.awt.event.FocusEvent evt) {
						SwingUtilities.invokeLater(new Runnable() {
							@Override
							public void run() {
								f.selectAll();
							}
						});
					}
				});
			}
		}
	};
    
    private void puttingImages(){
        HashMap lablesPesadosMap = new HashMap<String, Component>();
		Component[] labelsPesados = panelPesados.getComponents();
		for (int i=0; i< labelsPesados.length;i++){
			lablesPesadosMap.put(labelsPesados[i].getName(), labelsPesados[i]);
		}
		
		
		        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        grpSentido = new javax.swing.ButtonGroup();
        grpPista = new javax.swing.ButtonGroup();
        dadosFileChooser = new javax.swing.JFileChooser();
        exporterFileChooser = new javax.swing.JFileChooser();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        pnl_volumetrica = new javax.swing.JPanel();
        lblPosto = new javax.swing.JLabel();
        lblPosto_dados = new javax.swing.JLabel();
        lblHora = new javax.swing.JLabel();
        cmbHora = new javax.swing.JComboBox<String>();
        lblLocal = new javax.swing.JLabel();
        txtLocal = new javax.swing.JTextField();
        lblSentido = new javax.swing.JLabel();
        rdo_SentidoBA = new javax.swing.JRadioButton();
        rdo_SentidoAB = new javax.swing.JRadioButton();
        lblPista = new javax.swing.JLabel();
        rdo_PistaSimples = new javax.swing.JRadioButton();
        rdo_PistaDupla = new javax.swing.JRadioButton();
        lblData = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        tab_leves = new javax.swing.JPanel();
        lblPesquisador = new javax.swing.JLabel();
        txtPesquisador1 = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        jPanel119 = new javax.swing.JPanel();
        panelLeves = new javax.swing.JPanel();
        leves_hora1 = new javax.swing.JTextField();
        lev15_1 = new javax.swing.JTextField();
        lev30_1 = new javax.swing.JTextField();
        lev45_1 = new javax.swing.JTextField();
        lev60_1 = new javax.swing.JTextField();
        leves_hora2 = new javax.swing.JTextField();
        lev15_2 = new javax.swing.JTextField();
        lev30_2 = new javax.swing.JTextField();
        lev45_2 = new javax.swing.JTextField();
        lev60_2 = new javax.swing.JTextField();
        lblP1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        tl0 = new javax.swing.JTextField();
        tl1 = new javax.swing.JTextField();
        tl3 = new javax.swing.JTextField();
        tl5 = new javax.swing.JTextField();
        tl7 = new javax.swing.JTextField();
        lblP3 = new javax.swing.JLabel();
        tl9 = new javax.swing.JTextField();
        tl11 = new javax.swing.JTextField();
        tl13 = new javax.swing.JTextField();
        tl15 = new javax.swing.JTextField();
        lblP2 = new javax.swing.JLabel();
        tl17 = new javax.swing.JTextField();
        tl19 = new javax.swing.JTextField();
        tl21 = new javax.swing.JTextField();
        tl23 = new javax.swing.JTextField();
        lblM = new javax.swing.JLabel();
        tl25 = new javax.swing.JTextField();
        tl27 = new javax.swing.JTextField();
        tl29 = new javax.swing.JTextField();
        tl31 = new javax.swing.JTextField();
        lbl2CB = new javax.swing.JLabel();
        tl33 = new javax.swing.JTextField();
        tl35 = new javax.swing.JTextField();
        tl37 = new javax.swing.JTextField();
        tl39 = new javax.swing.JTextField();
        lbl3CB = new javax.swing.JLabel();
        tl41 = new javax.swing.JTextField();
        tl43 = new javax.swing.JTextField();
        tl45 = new javax.swing.JTextField();
        tl47 = new javax.swing.JTextField();
        lbl4CB = new javax.swing.JLabel();
        tl49 = new javax.swing.JTextField();
        tl51 = new javax.swing.JTextField();
        tl53 = new javax.swing.JTextField();
        tl55 = new javax.swing.JTextField();
        lbl2C = new javax.swing.JLabel();
        tl57 = new javax.swing.JTextField();
        tl59 = new javax.swing.JTextField();
        tl61 = new javax.swing.JTextField();
        tl63 = new javax.swing.JTextField();
        lbl3C = new javax.swing.JLabel();
        tl65 = new javax.swing.JTextField();
        tl67 = new javax.swing.JTextField();
        tl69 = new javax.swing.JTextField();
        tl71 = new javax.swing.JTextField();
        lbl4C = new javax.swing.JLabel();
        tl73 = new javax.swing.JTextField();
        tl75 = new javax.swing.JTextField();
        tl77 = new javax.swing.JTextField();
        tl79 = new javax.swing.JTextField();
        lbl4CD = new javax.swing.JLabel();
        tl81 = new javax.swing.JTextField();
        tl83 = new javax.swing.JTextField();
        tl85 = new javax.swing.JTextField();
        tl87 = new javax.swing.JTextField();
        lbl3D = new javax.swing.JLabel();
        tl89 = new javax.swing.JTextField();
        tl91 = new javax.swing.JTextField();
        tl93 = new javax.swing.JTextField();
        tl95 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        tl8 = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        tl16 = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        tl24 = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        tl32 = new javax.swing.JTextField();
        jPanel8 = new javax.swing.JPanel();
        tl40 = new javax.swing.JTextField();
        jPanel9 = new javax.swing.JPanel();
        tl48 = new javax.swing.JTextField();
        jPanel10 = new javax.swing.JPanel();
        tl56 = new javax.swing.JTextField();
        jPanel11 = new javax.swing.JPanel();
        tl64 = new javax.swing.JTextField();
        jPanel12 = new javax.swing.JPanel();
        tl72 = new javax.swing.JTextField();
        jPanel13 = new javax.swing.JPanel();
        tl80 = new javax.swing.JTextField();
        jPanel14 = new javax.swing.JPanel();
        tl88 = new javax.swing.JTextField();
        jPanel15 = new javax.swing.JPanel();
        tl2 = new javax.swing.JTextField();
        jPanel16 = new javax.swing.JPanel();
        tl10 = new javax.swing.JTextField();
        jPanel17 = new javax.swing.JPanel();
        tl18 = new javax.swing.JTextField();
        jPanel18 = new javax.swing.JPanel();
        tl26 = new javax.swing.JTextField();
        jPanel19 = new javax.swing.JPanel();
        tl34 = new javax.swing.JTextField();
        jPanel20 = new javax.swing.JPanel();
        tl42 = new javax.swing.JTextField();
        jPanel21 = new javax.swing.JPanel();
        tl50 = new javax.swing.JTextField();
        jPanel22 = new javax.swing.JPanel();
        tl58 = new javax.swing.JTextField();
        jPanel23 = new javax.swing.JPanel();
        tl66 = new javax.swing.JTextField();
        jPanel24 = new javax.swing.JPanel();
        tl74 = new javax.swing.JTextField();
        jPanel25 = new javax.swing.JPanel();
        tl82 = new javax.swing.JTextField();
        jPanel26 = new javax.swing.JPanel();
        tl90 = new javax.swing.JTextField();
        jPanel27 = new javax.swing.JPanel();
        tl4 = new javax.swing.JTextField();
        jPanel28 = new javax.swing.JPanel();
        tl12 = new javax.swing.JTextField();
        jPanel29 = new javax.swing.JPanel();
        tl20 = new javax.swing.JTextField();
        jPanel30 = new javax.swing.JPanel();
        tl28 = new javax.swing.JTextField();
        jPanel31 = new javax.swing.JPanel();
        tl36 = new javax.swing.JTextField();
        jPanel32 = new javax.swing.JPanel();
        tl44 = new javax.swing.JTextField();
        jPanel33 = new javax.swing.JPanel();
        tl52 = new javax.swing.JTextField();
        jPanel34 = new javax.swing.JPanel();
        tl60 = new javax.swing.JTextField();
        jPanel35 = new javax.swing.JPanel();
        tl68 = new javax.swing.JTextField();
        jPanel36 = new javax.swing.JPanel();
        tl76 = new javax.swing.JTextField();
        jPanel37 = new javax.swing.JPanel();
        tl84 = new javax.swing.JTextField();
        jPanel38 = new javax.swing.JPanel();
        tl92 = new javax.swing.JTextField();
        jPanel39 = new javax.swing.JPanel();
        tl6 = new javax.swing.JTextField();
        jPanel40 = new javax.swing.JPanel();
        tl14 = new javax.swing.JTextField();
        jPanel41 = new javax.swing.JPanel();
        tl22 = new javax.swing.JTextField();
        jPanel42 = new javax.swing.JPanel();
        tl30 = new javax.swing.JTextField();
        jPanel43 = new javax.swing.JPanel();
        tl38 = new javax.swing.JTextField();
        jPanel44 = new javax.swing.JPanel();
        tl46 = new javax.swing.JTextField();
        jPanel45 = new javax.swing.JPanel();
        tl54 = new javax.swing.JTextField();
        jPanel46 = new javax.swing.JPanel();
        tl62 = new javax.swing.JTextField();
        jPanel47 = new javax.swing.JPanel();
        tl70 = new javax.swing.JTextField();
        jPanel48 = new javax.swing.JPanel();
        tl78 = new javax.swing.JTextField();
        jPanel49 = new javax.swing.JPanel();
        tl86 = new javax.swing.JTextField();
        jPanel50 = new javax.swing.JPanel();
        tl94 = new javax.swing.JTextField();
        tab_pesados = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        panelPesados = new javax.swing.JPanel();
        pesados_hora1 = new javax.swing.JTextField();
        pes15_1 = new javax.swing.JTextField();
        pes30_1 = new javax.swing.JTextField();
        pes45_1 = new javax.swing.JTextField();
        pes60_1 = new javax.swing.JTextField();
        pesados_hora2 = new javax.swing.JTextField();
        pes15_2 = new javax.swing.JTextField();
        pes30_2 = new javax.swing.JTextField();
        peso45_2 = new javax.swing.JTextField();
        peso60_2 = new javax.swing.JTextField();
        lbl2S1 = new javax.swing.JLabel();
        tp1 = new javax.swing.JTextField();
        tp3 = new javax.swing.JTextField();
        tp5 = new javax.swing.JTextField();
        tp7 = new javax.swing.JTextField();
        lbl2S2 = new javax.swing.JLabel();
        tp9 = new javax.swing.JTextField();
        tp11 = new javax.swing.JTextField();
        tp13 = new javax.swing.JTextField();
        tp15 = new javax.swing.JTextField();
        lbl2S3 = new javax.swing.JLabel();
        tp17 = new javax.swing.JTextField();
        tp19 = new javax.swing.JTextField();
        tp21 = new javax.swing.JTextField();
        tp23 = new javax.swing.JTextField();
        lbl3S1 = new javax.swing.JLabel();
        tp25 = new javax.swing.JTextField();
        tp27 = new javax.swing.JTextField();
        tp29 = new javax.swing.JTextField();
        tp31 = new javax.swing.JTextField();
        lbl3S2 = new javax.swing.JLabel();
        tp33 = new javax.swing.JTextField();
        tp35 = new javax.swing.JTextField();
        tp37 = new javax.swing.JTextField();
        tp39 = new javax.swing.JTextField();
        lbl3S3 = new javax.swing.JLabel();
        tp41 = new javax.swing.JTextField();
        tp43 = new javax.swing.JTextField();
        tp45 = new javax.swing.JTextField();
        tp47 = new javax.swing.JTextField();
        lbl3T4 = new javax.swing.JLabel();
        tp49 = new javax.swing.JTextField();
        tp51 = new javax.swing.JTextField();
        tp53 = new javax.swing.JTextField();
        tp55 = new javax.swing.JTextField();
        lbl3T6 = new javax.swing.JLabel();
        tp57 = new javax.swing.JTextField();
        tp59 = new javax.swing.JTextField();
        tp61 = new javax.swing.JTextField();
        tp63 = new javax.swing.JTextField();
        lbl3T6B = new javax.swing.JLabel();
        tp65 = new javax.swing.JTextField();
        tp67 = new javax.swing.JTextField();
        tp69 = new javax.swing.JTextField();
        tp71 = new javax.swing.JTextField();
        lbl3V5 = new javax.swing.JLabel();
        tp73 = new javax.swing.JTextField();
        tp75 = new javax.swing.JTextField();
        tp77 = new javax.swing.JTextField();
        tp79 = new javax.swing.JTextField();
        lbl3M6 = new javax.swing.JLabel();
        tp81 = new javax.swing.JTextField();
        tp83 = new javax.swing.JTextField();
        tp85 = new javax.swing.JTextField();
        tp87 = new javax.swing.JTextField();
        lbl3Q4 = new javax.swing.JLabel();
        tp89 = new javax.swing.JTextField();
        tp91 = new javax.swing.JTextField();
        tp93 = new javax.swing.JTextField();
        tp95 = new javax.swing.JTextField();
        lbl2C2 = new javax.swing.JLabel();
        tp97 = new javax.swing.JTextField();
        tp99 = new javax.swing.JTextField();
        tp101 = new javax.swing.JTextField();
        tp103 = new javax.swing.JTextField();
        lbl2C3 = new javax.swing.JLabel();
        tp105 = new javax.swing.JTextField();
        tp107 = new javax.swing.JTextField();
        tp109 = new javax.swing.JTextField();
        tp111 = new javax.swing.JTextField();
        lbl3C2 = new javax.swing.JLabel();
        tp113 = new javax.swing.JTextField();
        tp115 = new javax.swing.JTextField();
        tp117 = new javax.swing.JTextField();
        tp119 = new javax.swing.JTextField();
        lbl3C3 = new javax.swing.JLabel();
        tp121 = new javax.swing.JTextField();
        tp123 = new javax.swing.JTextField();
        tp125 = new javax.swing.JTextField();
        tp127 = new javax.swing.JTextField();
        lbl3D4 = new javax.swing.JLabel();
        tp129 = new javax.swing.JTextField();
        tp131 = new javax.swing.JTextField();
        tp133 = new javax.swing.JTextField();
        tp135 = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jPanel51 = new javax.swing.JPanel();
        tp0 = new javax.swing.JTextField();
        jPanel52 = new javax.swing.JPanel();
        tp8 = new javax.swing.JTextField();
        jPanel53 = new javax.swing.JPanel();
        tp16 = new javax.swing.JTextField();
        jPanel54 = new javax.swing.JPanel();
        tp24 = new javax.swing.JTextField();
        jPanel55 = new javax.swing.JPanel();
        tp32 = new javax.swing.JTextField();
        jPanel56 = new javax.swing.JPanel();
        tp40 = new javax.swing.JTextField();
        jPanel57 = new javax.swing.JPanel();
        tp48 = new javax.swing.JTextField();
        jPanel58 = new javax.swing.JPanel();
        tp56 = new javax.swing.JTextField();
        jPanel59 = new javax.swing.JPanel();
        tp64 = new javax.swing.JTextField();
        jPanel60 = new javax.swing.JPanel();
        tp72 = new javax.swing.JTextField();
        jPanel61 = new javax.swing.JPanel();
        tp80 = new javax.swing.JTextField();
        jPanel62 = new javax.swing.JPanel();
        tp88 = new javax.swing.JTextField();
        jPanel63 = new javax.swing.JPanel();
        tp96 = new javax.swing.JTextField();
        jPanel64 = new javax.swing.JPanel();
        tp104 = new javax.swing.JTextField();
        jPanel65 = new javax.swing.JPanel();
        tp112 = new javax.swing.JTextField();
        jPanel66 = new javax.swing.JPanel();
        tp120 = new javax.swing.JTextField();
        jPanel67 = new javax.swing.JPanel();
        tp128 = new javax.swing.JTextField();
        jPanel68 = new javax.swing.JPanel();
        tp2 = new javax.swing.JTextField();
        jPanel69 = new javax.swing.JPanel();
        tp10 = new javax.swing.JTextField();
        jPanel70 = new javax.swing.JPanel();
        tp18 = new javax.swing.JTextField();
        jPanel71 = new javax.swing.JPanel();
        tp26 = new javax.swing.JTextField();
        jPanel72 = new javax.swing.JPanel();
        tp34 = new javax.swing.JTextField();
        jPanel73 = new javax.swing.JPanel();
        tp42 = new javax.swing.JTextField();
        jPanel74 = new javax.swing.JPanel();
        tp50 = new javax.swing.JTextField();
        jPanel75 = new javax.swing.JPanel();
        tp58 = new javax.swing.JTextField();
        jPanel76 = new javax.swing.JPanel();
        tp66 = new javax.swing.JTextField();
        jPanel77 = new javax.swing.JPanel();
        tp74 = new javax.swing.JTextField();
        jPanel78 = new javax.swing.JPanel();
        tp82 = new javax.swing.JTextField();
        jPanel79 = new javax.swing.JPanel();
        tp90 = new javax.swing.JTextField();
        jPanel80 = new javax.swing.JPanel();
        tp98 = new javax.swing.JTextField();
        jPanel81 = new javax.swing.JPanel();
        tp106 = new javax.swing.JTextField();
        jPanel82 = new javax.swing.JPanel();
        tp114 = new javax.swing.JTextField();
        jPanel83 = new javax.swing.JPanel();
        tp122 = new javax.swing.JTextField();
        jPanel84 = new javax.swing.JPanel();
        tp130 = new javax.swing.JTextField();
        jPanel85 = new javax.swing.JPanel();
        tp4 = new javax.swing.JTextField();
        jPanel86 = new javax.swing.JPanel();
        tp12 = new javax.swing.JTextField();
        jPanel87 = new javax.swing.JPanel();
        tp20 = new javax.swing.JTextField();
        jPanel88 = new javax.swing.JPanel();
        tp28 = new javax.swing.JTextField();
        jPanel89 = new javax.swing.JPanel();
        tp36 = new javax.swing.JTextField();
        jPanel90 = new javax.swing.JPanel();
        tp44 = new javax.swing.JTextField();
        jPanel91 = new javax.swing.JPanel();
        tp52 = new javax.swing.JTextField();
        jPanel92 = new javax.swing.JPanel();
        tp60 = new javax.swing.JTextField();
        jPanel93 = new javax.swing.JPanel();
        tp68 = new javax.swing.JTextField();
        jPanel94 = new javax.swing.JPanel();
        tp76 = new javax.swing.JTextField();
        jPanel95 = new javax.swing.JPanel();
        tp84 = new javax.swing.JTextField();
        jPanel96 = new javax.swing.JPanel();
        tp92 = new javax.swing.JTextField();
        jPanel97 = new javax.swing.JPanel();
        tp100 = new javax.swing.JTextField();
        jPanel98 = new javax.swing.JPanel();
        tp108 = new javax.swing.JTextField();
        jPanel99 = new javax.swing.JPanel();
        tp116 = new javax.swing.JTextField();
        jPanel100 = new javax.swing.JPanel();
        tp124 = new javax.swing.JTextField();
        jPanel101 = new javax.swing.JPanel();
        tp132 = new javax.swing.JTextField();
        jPanel102 = new javax.swing.JPanel();
        tp6 = new javax.swing.JTextField();
        jPanel103 = new javax.swing.JPanel();
        tp14 = new javax.swing.JTextField();
        jPanel104 = new javax.swing.JPanel();
        tp22 = new javax.swing.JTextField();
        jPanel105 = new javax.swing.JPanel();
        tp30 = new javax.swing.JTextField();
        jPanel106 = new javax.swing.JPanel();
        tp38 = new javax.swing.JTextField();
        jPanel107 = new javax.swing.JPanel();
        tp46 = new javax.swing.JTextField();
        jPanel108 = new javax.swing.JPanel();
        tp54 = new javax.swing.JTextField();
        jPanel109 = new javax.swing.JPanel();
        tp62 = new javax.swing.JTextField();
        jPanel110 = new javax.swing.JPanel();
        tp70 = new javax.swing.JTextField();
        jPanel111 = new javax.swing.JPanel();
        tp78 = new javax.swing.JTextField();
        jPanel112 = new javax.swing.JPanel();
        tp86 = new javax.swing.JTextField();
        jPanel113 = new javax.swing.JPanel();
        tp94 = new javax.swing.JTextField();
        jPanel114 = new javax.swing.JPanel();
        tp102 = new javax.swing.JTextField();
        jPanel115 = new javax.swing.JPanel();
        tp110 = new javax.swing.JTextField();
        jPanel116 = new javax.swing.JPanel();
        tp118 = new javax.swing.JTextField();
        jPanel117 = new javax.swing.JPanel();
        tp126 = new javax.swing.JTextField();
        jPanel118 = new javax.swing.JPanel();
        tp134 = new javax.swing.JTextField();
        lblPesquisador1 = new javax.swing.JLabel();
        txtPesquisador2 = new javax.swing.JTextField();
        data = new org.jdesktop.swingx.JXDatePicker();
        data.setLocale(new Locale("pt", "BR"));
        btnSalvarForms = new javax.swing.JButton();
        jLabel34 = new javax.swing.JLabel();
        lblTrecho_dados = new javax.swing.JLabel();
        pnl_servidor = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtAreaLog = new javax.swing.JTextArea();
        txtPorta = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        pnl_envio = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        btnInDados = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        btnExportAllVol = new javax.swing.JButton();
        btnVolNotSent = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        btnODNotSent = new javax.swing.JButton();
        btnODexportAll = new javax.swing.JButton();

        dadosFileChooser.setDialogTitle("Importar dados iPad");
        dadosFileChooser.setFileFilter(new SQLiteFilter());

        exporterFileChooser.setDialogTitle("Exportação de dados");
        exporterFileChooser.setFileFilter(new JsonFilter());

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Concentrador de dados");
        setIconImage(new ImageIcon(Janela.this.getClass().getResource("/images/icon.png")).getImage());

        jTabbedPane2.setDoubleBuffered(true);

        lblPosto.setText("Posto:");

        lblPosto_dados.setText("-----");

        lblHora.setText("Hora Inicial:");

        cmbHora.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "0", "2", "4", "6", "8", "10", "12", "14", "16", "18", "20", "22" }));
        cmbHora.setSelectedIndex(-1);
        cmbHora.setToolTipText("");
        cmbHora.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbHoraActionPerformed(evt);
            }
        });

        lblLocal.setText("Local:");

        lblSentido.setText("Sentido:");

        grpSentido.add(rdo_SentidoBA);
        rdo_SentidoBA.setText("B->A");

        grpSentido.add(rdo_SentidoAB);
        rdo_SentidoAB.setText("A->B");

        lblPista.setText("Pista:");

        grpPista.add(rdo_PistaSimples);
        rdo_PistaSimples.setText("Simples");

        grpPista.add(rdo_PistaDupla);
        rdo_PistaDupla.setText("Dupla");

        lblData.setText("Data:");

        jTabbedPane1.setName(""); // NOI18N

        lblPesquisador.setText("Pesquisador_1:");

        txtPesquisador1.setNextFocusableComponent(tl0);

        jPanel119.setPreferredSize(new java.awt.Dimension(616, 458));

        panelLeves.setBackground(new java.awt.Color(255, 255, 255));
        panelLeves.setMinimumSize(new java.awt.Dimension(200, 76));
        panelLeves.setLayout(new java.awt.GridBagLayout());

        leves_hora1.setEditable(false);
        leves_hora1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        panelLeves.add(leves_hora1, gridBagConstraints);

        lev15_1.setEditable(false);
        lev15_1.setBackground(new java.awt.Color(204, 204, 204));
        lev15_1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        lev15_1.setText("15");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        panelLeves.add(lev15_1, gridBagConstraints);

        lev30_1.setEditable(false);
        lev30_1.setBackground(new java.awt.Color(255, 255, 255));
        lev30_1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        lev30_1.setText("30");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        panelLeves.add(lev30_1, gridBagConstraints);

        lev45_1.setEditable(false);
        lev45_1.setBackground(new java.awt.Color(204, 204, 204));
        lev45_1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        lev45_1.setText("45");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        panelLeves.add(lev45_1, gridBagConstraints);

        lev60_1.setEditable(false);
        lev60_1.setBackground(new java.awt.Color(255, 255, 255));
        lev60_1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        lev60_1.setText("60");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        panelLeves.add(lev60_1, gridBagConstraints);

        leves_hora2.setEditable(false);
        leves_hora2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        panelLeves.add(leves_hora2, gridBagConstraints);

        lev15_2.setEditable(false);
        lev15_2.setBackground(new java.awt.Color(204, 204, 204));
        lev15_2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        lev15_2.setText("15");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        panelLeves.add(lev15_2, gridBagConstraints);

        lev30_2.setEditable(false);
        lev30_2.setBackground(new java.awt.Color(255, 255, 255));
        lev30_2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        lev30_2.setText("30");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        panelLeves.add(lev30_2, gridBagConstraints);

        lev45_2.setEditable(false);
        lev45_2.setBackground(new java.awt.Color(204, 204, 204));
        lev45_2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        lev45_2.setText("45");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        panelLeves.add(lev45_2, gridBagConstraints);

        lev60_2.setEditable(false);
        lev60_2.setBackground(new java.awt.Color(255, 255, 255));
        lev60_2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        lev60_2.setText("60");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 9;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        panelLeves.add(lev60_2, gridBagConstraints);

        lblP1.setBackground(new java.awt.Color(255, 255, 255));
        lblP1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/simples/p1_01.png"))); // NOI18N
        lblP1.setToolTipText("");
        lblP1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 3, 1));
        lblP1.setFocusable(false);
        lblP1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        lblP1.setMinimumSize(new java.awt.Dimension(200, 76));
        lblP1.setName(""); // NOI18N
        lblP1.setOpaque(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        panelLeves.add(lblP1, gridBagConstraints);

        jPanel3.setToolTipText("");
        jPanel3.setLayout(new java.awt.GridBagLayout());

        tl0.setColumns(5);
        tl0.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tl0.setToolTipText("");
        tl0.setInputVerifier(intVerifier);
        tl0.setMinimumSize(new java.awt.Dimension(50, 19));
        tl0.setName(""); // NOI18N
        tl0.setNextFocusableComponent(tl1);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        jPanel3.add(tl0, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        panelLeves.add(jPanel3, gridBagConstraints);

        tl1.setColumns(5);
        tl1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tl1.setToolTipText("");
        tl1.setInputVerifier(intVerifier);
        tl1.setMinimumSize(new java.awt.Dimension(50, 19));
        tl1.setName(""); // NOI18N
        tl1.setNextFocusableComponent(tl2);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.weightx = 1.0;
        panelLeves.add(tl1, gridBagConstraints);

        tl3.setColumns(5);
        tl3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tl3.setToolTipText("");
        tl3.setInputVerifier(intVerifier);
        tl3.setMinimumSize(new java.awt.Dimension(50, 19));
        tl3.setName(""); // NOI18N
        tl3.setNextFocusableComponent(tl4);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.weightx = 1.0;
        panelLeves.add(tl3, gridBagConstraints);

        tl5.setColumns(5);
        tl5.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tl5.setToolTipText("");
        tl5.setInputVerifier(intVerifier);
        tl5.setMinimumSize(new java.awt.Dimension(50, 19));
        tl5.setName(""); // NOI18N
        tl5.setNextFocusableComponent(tl6);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.weightx = 1.0;
        panelLeves.add(tl5, gridBagConstraints);

        tl7.setColumns(5);
        tl7.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tl7.setToolTipText("");
        tl7.setInputVerifier(intVerifier);
        tl7.setMinimumSize(new java.awt.Dimension(50, 19));
        tl7.setName(""); // NOI18N
        tl7.setNextFocusableComponent(tl8);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 9;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.weightx = 1.0;
        panelLeves.add(tl7, gridBagConstraints);

        lblP3.setBackground(new java.awt.Color(255, 255, 255));
        lblP3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/simples/p3.png"))); // NOI18N
        lblP3.setToolTipText("");
        lblP3.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 3, 1));
        lblP3.setFocusable(false);
        lblP3.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        lblP3.setMinimumSize(new java.awt.Dimension(200, 76));
        lblP3.setOpaque(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        panelLeves.add(lblP3, gridBagConstraints);

        tl9.setColumns(5);
        tl9.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tl9.setToolTipText("");
        tl9.setInputVerifier(intVerifier);
        tl9.setMinimumSize(new java.awt.Dimension(50, 19));
        tl9.setName(""); // NOI18N
        tl9.setNextFocusableComponent(tl10);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.weightx = 1.0;
        panelLeves.add(tl9, gridBagConstraints);

        tl11.setColumns(5);
        tl11.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tl11.setToolTipText("");
        tl11.setInputVerifier(intVerifier);
        tl11.setMinimumSize(new java.awt.Dimension(50, 19));
        tl11.setName(""); // NOI18N
        tl11.setNextFocusableComponent(tl12);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.weightx = 1.0;
        panelLeves.add(tl11, gridBagConstraints);

        tl13.setColumns(5);
        tl13.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tl13.setToolTipText("");
        tl13.setInputVerifier(intVerifier);
        tl13.setMinimumSize(new java.awt.Dimension(50, 19));
        tl13.setName(""); // NOI18N
        tl13.setNextFocusableComponent(tl14);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.weightx = 1.0;
        panelLeves.add(tl13, gridBagConstraints);

        tl15.setColumns(5);
        tl15.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tl15.setToolTipText("");
        tl15.setInputVerifier(intVerifier);
        tl15.setMinimumSize(new java.awt.Dimension(50, 19));
        tl15.setName(""); // NOI18N
        tl15.setNextFocusableComponent(tl16);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 9;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.weightx = 1.0;
        panelLeves.add(tl15, gridBagConstraints);

        lblP2.setBackground(new java.awt.Color(255, 255, 255));
        lblP2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/simples/p2.png"))); // NOI18N
        lblP2.setToolTipText("");
        lblP2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 3, 1));
        lblP2.setFocusable(false);
        lblP2.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        lblP2.setMinimumSize(new java.awt.Dimension(200, 76));
        lblP2.setOpaque(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        panelLeves.add(lblP2, gridBagConstraints);

        tl17.setColumns(5);
        tl17.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tl17.setToolTipText("");
        tl17.setInputVerifier(intVerifier);
        tl17.setMinimumSize(new java.awt.Dimension(50, 19));
        tl17.setName(""); // NOI18N
        tl17.setNextFocusableComponent(tl18);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.weightx = 1.0;
        panelLeves.add(tl17, gridBagConstraints);

        tl19.setColumns(5);
        tl19.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tl19.setToolTipText("");
        tl19.setInputVerifier(intVerifier);
        tl19.setMinimumSize(new java.awt.Dimension(50, 19));
        tl19.setName(""); // NOI18N
        tl19.setNextFocusableComponent(tl20);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.weightx = 1.0;
        panelLeves.add(tl19, gridBagConstraints);

        tl21.setColumns(5);
        tl21.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tl21.setToolTipText("");
        tl21.setInputVerifier(intVerifier);
        tl21.setMinimumSize(new java.awt.Dimension(50, 19));
        tl21.setName(""); // NOI18N
        tl21.setNextFocusableComponent(tl22);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.weightx = 1.0;
        panelLeves.add(tl21, gridBagConstraints);

        tl23.setColumns(5);
        tl23.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tl23.setToolTipText("");
        tl23.setInputVerifier(intVerifier);
        tl23.setMinimumSize(new java.awt.Dimension(50, 19));
        tl23.setName(""); // NOI18N
        tl23.setNextFocusableComponent(tl24);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 9;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.weightx = 1.0;
        panelLeves.add(tl23, gridBagConstraints);

        lblM.setBackground(new java.awt.Color(255, 255, 255));
        lblM.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblM.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/simples/m.png"))); // NOI18N
        lblM.setToolTipText("");
        lblM.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 3, 1));
        lblM.setFocusable(false);
        lblM.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        lblM.setMinimumSize(new java.awt.Dimension(200, 76));
        lblM.setOpaque(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        panelLeves.add(lblM, gridBagConstraints);

        tl25.setColumns(5);
        tl25.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tl25.setToolTipText("");
        tl25.setInputVerifier(intVerifier);
        tl25.setMinimumSize(new java.awt.Dimension(50, 19));
        tl25.setName(""); // NOI18N
        tl25.setNextFocusableComponent(tl26);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.weightx = 1.0;
        panelLeves.add(tl25, gridBagConstraints);

        tl27.setColumns(5);
        tl27.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tl27.setToolTipText("");
        tl27.setInputVerifier(intVerifier);
        tl27.setMinimumSize(new java.awt.Dimension(50, 19));
        tl27.setName(""); // NOI18N
        tl27.setNextFocusableComponent(tl28);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.weightx = 1.0;
        panelLeves.add(tl27, gridBagConstraints);

        tl29.setColumns(5);
        tl29.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tl29.setToolTipText("");
        tl29.setInputVerifier(intVerifier);
        tl29.setMinimumSize(new java.awt.Dimension(50, 19));
        tl29.setName(""); // NOI18N
        tl29.setNextFocusableComponent(tl30);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.weightx = 1.0;
        panelLeves.add(tl29, gridBagConstraints);

        tl31.setColumns(5);
        tl31.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tl31.setToolTipText("");
        tl31.setInputVerifier(intVerifier);
        tl31.setMinimumSize(new java.awt.Dimension(50, 19));
        tl31.setName(""); // NOI18N
        tl31.setNextFocusableComponent(tl32);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 9;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.weightx = 1.0;
        panelLeves.add(tl31, gridBagConstraints);

        lbl2CB.setBackground(new java.awt.Color(255, 255, 255));
        lbl2CB.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl2CB.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/simples/o1.png"))); // NOI18N
        lbl2CB.setToolTipText("");
        lbl2CB.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 3, 1));
        lbl2CB.setFocusable(false);
        lbl2CB.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        lbl2CB.setMinimumSize(new java.awt.Dimension(200, 76));
        lbl2CB.setOpaque(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        panelLeves.add(lbl2CB, gridBagConstraints);

        tl33.setColumns(5);
        tl33.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tl33.setToolTipText("");
        tl33.setInputVerifier(intVerifier);
        tl33.setMinimumSize(new java.awt.Dimension(50, 19));
        tl33.setName(""); // NOI18N
        tl33.setNextFocusableComponent(tl34);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 6;
        panelLeves.add(tl33, gridBagConstraints);

        tl35.setColumns(5);
        tl35.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tl35.setToolTipText("");
        tl35.setInputVerifier(intVerifier);
        tl35.setMinimumSize(new java.awt.Dimension(50, 19));
        tl35.setName(""); // NOI18N
        tl35.setNextFocusableComponent(tl36);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 6;
        panelLeves.add(tl35, gridBagConstraints);

        tl37.setColumns(5);
        tl37.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tl37.setToolTipText("");
        tl37.setInputVerifier(intVerifier);
        tl37.setMinimumSize(new java.awt.Dimension(50, 19));
        tl37.setName(""); // NOI18N
        tl37.setNextFocusableComponent(tl38);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 6;
        panelLeves.add(tl37, gridBagConstraints);

        tl39.setColumns(5);
        tl39.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tl39.setToolTipText("");
        tl39.setInputVerifier(intVerifier);
        tl39.setMinimumSize(new java.awt.Dimension(50, 19));
        tl39.setName(""); // NOI18N
        tl39.setNextFocusableComponent(tl40);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 9;
        gridBagConstraints.gridy = 6;
        panelLeves.add(tl39, gridBagConstraints);

        lbl3CB.setBackground(new java.awt.Color(255, 255, 255));
        lbl3CB.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl3CB.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/simples/o2.png"))); // NOI18N
        lbl3CB.setToolTipText("");
        lbl3CB.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 3, 1));
        lbl3CB.setFocusable(false);
        lbl3CB.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        lbl3CB.setMinimumSize(new java.awt.Dimension(200, 76));
        lbl3CB.setOpaque(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        panelLeves.add(lbl3CB, gridBagConstraints);

        tl41.setColumns(5);
        tl41.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tl41.setToolTipText("");
        tl41.setInputVerifier(intVerifier);
        tl41.setMinimumSize(new java.awt.Dimension(50, 19));
        tl41.setName(""); // NOI18N
        tl41.setNextFocusableComponent(tl42);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 7;
        panelLeves.add(tl41, gridBagConstraints);

        tl43.setColumns(5);
        tl43.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tl43.setToolTipText("");
        tl43.setInputVerifier(intVerifier);
        tl43.setMinimumSize(new java.awt.Dimension(50, 19));
        tl43.setName(""); // NOI18N
        tl43.setNextFocusableComponent(tl44);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 7;
        panelLeves.add(tl43, gridBagConstraints);

        tl45.setColumns(5);
        tl45.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tl45.setToolTipText("");
        tl45.setInputVerifier(intVerifier);
        tl45.setMinimumSize(new java.awt.Dimension(50, 19));
        tl45.setName(""); // NOI18N
        tl45.setNextFocusableComponent(tl46);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 7;
        panelLeves.add(tl45, gridBagConstraints);

        tl47.setColumns(5);
        tl47.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tl47.setToolTipText("");
        tl47.setInputVerifier(intVerifier);
        tl47.setMinimumSize(new java.awt.Dimension(50, 19));
        tl47.setName(""); // NOI18N
        tl47.setNextFocusableComponent(tl48);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 9;
        gridBagConstraints.gridy = 7;
        panelLeves.add(tl47, gridBagConstraints);

        lbl4CB.setBackground(new java.awt.Color(255, 255, 255));
        lbl4CB.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl4CB.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/simples/o3.png"))); // NOI18N
        lbl4CB.setToolTipText("");
        lbl4CB.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 3, 1));
        lbl4CB.setFocusable(false);
        lbl4CB.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        lbl4CB.setMinimumSize(new java.awt.Dimension(200, 76));
        lbl4CB.setOpaque(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        panelLeves.add(lbl4CB, gridBagConstraints);

        tl49.setColumns(5);
        tl49.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tl49.setToolTipText("");
        tl49.setInputVerifier(intVerifier);
        tl49.setMinimumSize(new java.awt.Dimension(50, 19));
        tl49.setName(""); // NOI18N
        tl49.setNextFocusableComponent(tl50);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 8;
        panelLeves.add(tl49, gridBagConstraints);

        tl51.setColumns(5);
        tl51.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tl51.setToolTipText("");
        tl51.setInputVerifier(intVerifier);
        tl51.setMinimumSize(new java.awt.Dimension(50, 19));
        tl51.setName(""); // NOI18N
        tl51.setNextFocusableComponent(tl52);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 8;
        panelLeves.add(tl51, gridBagConstraints);

        tl53.setColumns(5);
        tl53.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tl53.setToolTipText("");
        tl53.setInputVerifier(intVerifier);
        tl53.setMinimumSize(new java.awt.Dimension(50, 19));
        tl53.setName(""); // NOI18N
        tl53.setNextFocusableComponent(tl54);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 8;
        panelLeves.add(tl53, gridBagConstraints);

        tl55.setColumns(5);
        tl55.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tl55.setToolTipText("");
        tl55.setInputVerifier(intVerifier);
        tl55.setMinimumSize(new java.awt.Dimension(50, 19));
        tl55.setName(""); // NOI18N
        tl55.setNextFocusableComponent(tl56);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 9;
        gridBagConstraints.gridy = 8;
        panelLeves.add(tl55, gridBagConstraints);

        lbl2C.setBackground(new java.awt.Color(255, 255, 255));
        lbl2C.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl2C.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/simples/c1.png"))); // NOI18N
        lbl2C.setToolTipText("");
        lbl2C.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 3, 1));
        lbl2C.setFocusable(false);
        lbl2C.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        lbl2C.setMinimumSize(new java.awt.Dimension(200, 76));
        lbl2C.setOpaque(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        panelLeves.add(lbl2C, gridBagConstraints);

        tl57.setColumns(5);
        tl57.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tl57.setToolTipText("");
        tl57.setInputVerifier(intVerifier);
        tl57.setMinimumSize(new java.awt.Dimension(50, 19));
        tl57.setName(""); // NOI18N
        tl57.setNextFocusableComponent(tl58);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 9;
        panelLeves.add(tl57, gridBagConstraints);

        tl59.setColumns(5);
        tl59.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tl59.setToolTipText("");
        tl59.setInputVerifier(intVerifier);
        tl59.setMinimumSize(new java.awt.Dimension(50, 19));
        tl59.setName(""); // NOI18N
        tl59.setNextFocusableComponent(tl60);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 9;
        panelLeves.add(tl59, gridBagConstraints);

        tl61.setColumns(5);
        tl61.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tl61.setToolTipText("");
        tl61.setInputVerifier(intVerifier);
        tl61.setMinimumSize(new java.awt.Dimension(50, 19));
        tl61.setName(""); // NOI18N
        tl61.setNextFocusableComponent(tl62);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 9;
        panelLeves.add(tl61, gridBagConstraints);

        tl63.setColumns(5);
        tl63.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tl63.setToolTipText("");
        tl63.setInputVerifier(intVerifier);
        tl63.setMinimumSize(new java.awt.Dimension(50, 19));
        tl63.setName(""); // NOI18N
        tl63.setNextFocusableComponent(tl64);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 9;
        gridBagConstraints.gridy = 9;
        panelLeves.add(tl63, gridBagConstraints);

        lbl3C.setBackground(new java.awt.Color(255, 255, 255));
        lbl3C.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl3C.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/simples/c2.png"))); // NOI18N
        lbl3C.setToolTipText("");
        lbl3C.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 3, 1));
        lbl3C.setFocusable(false);
        lbl3C.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        lbl3C.setMinimumSize(new java.awt.Dimension(200, 76));
        lbl3C.setOpaque(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        panelLeves.add(lbl3C, gridBagConstraints);

        tl65.setColumns(5);
        tl65.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tl65.setToolTipText("");
        tl65.setInputVerifier(intVerifier);
        tl65.setMinimumSize(new java.awt.Dimension(50, 19));
        tl65.setName(""); // NOI18N
        tl65.setNextFocusableComponent(tl66);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 10;
        panelLeves.add(tl65, gridBagConstraints);

        tl67.setColumns(5);
        tl67.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tl67.setToolTipText("");
        tl67.setInputVerifier(intVerifier);
        tl67.setMinimumSize(new java.awt.Dimension(50, 19));
        tl67.setName(""); // NOI18N
        tl67.setNextFocusableComponent(tl68);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 10;
        panelLeves.add(tl67, gridBagConstraints);

        tl69.setColumns(5);
        tl69.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tl69.setToolTipText("");
        tl69.setInputVerifier(intVerifier);
        tl69.setMinimumSize(new java.awt.Dimension(50, 19));
        tl69.setName(""); // NOI18N
        tl69.setNextFocusableComponent(tl70);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 10;
        panelLeves.add(tl69, gridBagConstraints);

        tl71.setColumns(5);
        tl71.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tl71.setToolTipText("");
        tl71.setInputVerifier(intVerifier);
        tl71.setMinimumSize(new java.awt.Dimension(50, 19));
        tl71.setName(""); // NOI18N
        tl71.setNextFocusableComponent(tl72);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 9;
        gridBagConstraints.gridy = 10;
        panelLeves.add(tl71, gridBagConstraints);

        lbl4C.setBackground(new java.awt.Color(255, 255, 255));
        lbl4C.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl4C.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/simples/c3.png"))); // NOI18N
        lbl4C.setToolTipText("");
        lbl4C.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 3, 1));
        lbl4C.setFocusable(false);
        lbl4C.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        lbl4C.setMinimumSize(new java.awt.Dimension(200, 76));
        lbl4C.setOpaque(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 11;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        panelLeves.add(lbl4C, gridBagConstraints);

        tl73.setColumns(5);
        tl73.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tl73.setToolTipText("");
        tl73.setInputVerifier(intVerifier);
        tl73.setMinimumSize(new java.awt.Dimension(50, 19));
        tl73.setName(""); // NOI18N
        tl73.setNextFocusableComponent(tl74);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 11;
        panelLeves.add(tl73, gridBagConstraints);

        tl75.setColumns(5);
        tl75.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tl75.setToolTipText("");
        tl75.setInputVerifier(intVerifier);
        tl75.setMinimumSize(new java.awt.Dimension(50, 19));
        tl75.setName(""); // NOI18N
        tl75.setNextFocusableComponent(tl76);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 11;
        panelLeves.add(tl75, gridBagConstraints);

        tl77.setColumns(5);
        tl77.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tl77.setToolTipText("");
        tl77.setInputVerifier(intVerifier);
        tl77.setMinimumSize(new java.awt.Dimension(50, 19));
        tl77.setName(""); // NOI18N
        tl77.setNextFocusableComponent(tl78);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 11;
        panelLeves.add(tl77, gridBagConstraints);

        tl79.setColumns(5);
        tl79.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tl79.setToolTipText("");
        tl79.setInputVerifier(intVerifier);
        tl79.setMinimumSize(new java.awt.Dimension(50, 19));
        tl79.setName(""); // NOI18N
        tl79.setNextFocusableComponent(tl80);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 9;
        gridBagConstraints.gridy = 11;
        panelLeves.add(tl79, gridBagConstraints);

        lbl4CD.setBackground(new java.awt.Color(255, 255, 255));
        lbl4CD.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl4CD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/simples/c4.png"))); // NOI18N
        lbl4CD.setToolTipText("");
        lbl4CD.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 3, 1));
        lbl4CD.setFocusable(false);
        lbl4CD.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        lbl4CD.setMinimumSize(new java.awt.Dimension(200, 76));
        lbl4CD.setOpaque(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        panelLeves.add(lbl4CD, gridBagConstraints);

        tl81.setColumns(5);
        tl81.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tl81.setToolTipText("");
        tl81.setInputVerifier(intVerifier);
        tl81.setMinimumSize(new java.awt.Dimension(50, 19));
        tl81.setName(""); // NOI18N
        tl81.setNextFocusableComponent(tl82);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 12;
        panelLeves.add(tl81, gridBagConstraints);

        tl83.setColumns(5);
        tl83.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tl83.setToolTipText("");
        tl83.setInputVerifier(intVerifier);
        tl83.setMinimumSize(new java.awt.Dimension(50, 19));
        tl83.setName(""); // NOI18N
        tl83.setNextFocusableComponent(tl84);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 12;
        panelLeves.add(tl83, gridBagConstraints);

        tl85.setColumns(5);
        tl85.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tl85.setToolTipText("");
        tl85.setInputVerifier(intVerifier);
        tl85.setMinimumSize(new java.awt.Dimension(50, 19));
        tl85.setName(""); // NOI18N
        tl85.setNextFocusableComponent(tl86);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 12;
        panelLeves.add(tl85, gridBagConstraints);

        tl87.setColumns(5);
        tl87.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tl87.setToolTipText("");
        tl87.setInputVerifier(intVerifier);
        tl87.setMinimumSize(new java.awt.Dimension(50, 19));
        tl87.setName(""); // NOI18N
        tl87.setNextFocusableComponent(tl88);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 9;
        gridBagConstraints.gridy = 12;
        panelLeves.add(tl87, gridBagConstraints);

        lbl3D.setBackground(new java.awt.Color(255, 255, 255));
        lbl3D.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl3D.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/simples/c5.png"))); // NOI18N
        lbl3D.setToolTipText("");
        lbl3D.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 3, 1));
        lbl3D.setFocusable(false);
        lbl3D.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        lbl3D.setMinimumSize(new java.awt.Dimension(200, 76));
        lbl3D.setOpaque(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 13;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        panelLeves.add(lbl3D, gridBagConstraints);

        tl89.setColumns(5);
        tl89.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tl89.setToolTipText("");
        tl89.setInputVerifier(intVerifier);
        tl89.setMinimumSize(new java.awt.Dimension(50, 19));
        tl89.setName(""); // NOI18N
        tl89.setNextFocusableComponent(tl90);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 13;
        panelLeves.add(tl89, gridBagConstraints);

        tl91.setColumns(5);
        tl91.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tl91.setToolTipText("");
        tl91.setInputVerifier(intVerifier);
        tl91.setMinimumSize(new java.awt.Dimension(50, 19));
        tl91.setName(""); // NOI18N
        tl91.setNextFocusableComponent(tl92);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 13;
        panelLeves.add(tl91, gridBagConstraints);

        tl93.setColumns(5);
        tl93.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tl93.setToolTipText("");
        tl93.setInputVerifier(intVerifier);
        tl93.setMinimumSize(new java.awt.Dimension(50, 19));
        tl93.setName(""); // NOI18N
        tl93.setNextFocusableComponent(tl94);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 13;
        panelLeves.add(tl93, gridBagConstraints);

        tl95.setColumns(5);
        tl95.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tl95.setToolTipText("");
        tl95.setInputVerifier(intVerifier);
        tl95.setMinimumSize(new java.awt.Dimension(50, 19));
        tl95.setName(""); // NOI18N
        tl95.setNextFocusableComponent(txtPesquisador2);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 9;
        gridBagConstraints.gridy = 13;
        panelLeves.add(tl95, gridBagConstraints);

        jLabel5.setText("P1");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        panelLeves.add(jLabel5, gridBagConstraints);

        jLabel6.setText("P3");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        panelLeves.add(jLabel6, gridBagConstraints);

        jLabel7.setText("P2");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        panelLeves.add(jLabel7, gridBagConstraints);

        jLabel8.setText("M");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        panelLeves.add(jLabel8, gridBagConstraints);

        jLabel9.setText("2CB");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        panelLeves.add(jLabel9, gridBagConstraints);

        jLabel10.setText("3CB");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 7;
        panelLeves.add(jLabel10, gridBagConstraints);

        jLabel11.setText("4CB");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 8;
        panelLeves.add(jLabel11, gridBagConstraints);

        jLabel12.setText("2C");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 9;
        panelLeves.add(jLabel12, gridBagConstraints);

        jLabel13.setText("3C");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 10;
        panelLeves.add(jLabel13, gridBagConstraints);

        jLabel14.setText("4C");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 11;
        panelLeves.add(jLabel14, gridBagConstraints);

        jLabel15.setText("4CD");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 12;
        panelLeves.add(jLabel15, gridBagConstraints);

        jLabel16.setText("3D");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 13;
        panelLeves.add(jLabel16, gridBagConstraints);

        jPanel4.setToolTipText("");
        jPanel4.setLayout(new java.awt.GridBagLayout());

        tl8.setColumns(5);
        tl8.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tl8.setToolTipText("");
        tl8.setInputVerifier(intVerifier);
        tl8.setMinimumSize(new java.awt.Dimension(50, 19));
        tl8.setName(""); // NOI18N
        tl8.setNextFocusableComponent(tl9);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        jPanel4.add(tl8, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        panelLeves.add(jPanel4, gridBagConstraints);

        jPanel5.setToolTipText("");
        jPanel5.setLayout(new java.awt.GridBagLayout());

        tl16.setColumns(5);
        tl16.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tl16.setToolTipText("");
        tl16.setInputVerifier(intVerifier);
        tl16.setMinimumSize(new java.awt.Dimension(50, 19));
        tl16.setName(""); // NOI18N
        tl16.setNextFocusableComponent(tl17);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        jPanel5.add(tl16, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        panelLeves.add(jPanel5, gridBagConstraints);

        jPanel6.setToolTipText("");
        jPanel6.setLayout(new java.awt.GridBagLayout());

        tl24.setColumns(5);
        tl24.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tl24.setToolTipText("");
        tl24.setInputVerifier(intVerifier);
        tl24.setMinimumSize(new java.awt.Dimension(50, 19));
        tl24.setName(""); // NOI18N
        tl24.setNextFocusableComponent(tl25);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        jPanel6.add(tl24, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        panelLeves.add(jPanel6, gridBagConstraints);

        jPanel7.setToolTipText("");
        jPanel7.setLayout(new java.awt.GridBagLayout());

        tl32.setColumns(5);
        tl32.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tl32.setToolTipText("");
        tl32.setInputVerifier(intVerifier);
        tl32.setMinimumSize(new java.awt.Dimension(50, 19));
        tl32.setName(""); // NOI18N
        tl32.setNextFocusableComponent(tl33);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        jPanel7.add(tl32, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        panelLeves.add(jPanel7, gridBagConstraints);

        jPanel8.setToolTipText("");
        jPanel8.setLayout(new java.awt.GridBagLayout());

        tl40.setColumns(5);
        tl40.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tl40.setToolTipText("");
        tl40.setInputVerifier(intVerifier);
        tl40.setMinimumSize(new java.awt.Dimension(50, 19));
        tl40.setName(""); // NOI18N
        tl40.setNextFocusableComponent(tl41);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        jPanel8.add(tl40, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        panelLeves.add(jPanel8, gridBagConstraints);

        jPanel9.setToolTipText("");
        jPanel9.setLayout(new java.awt.GridBagLayout());

        tl48.setColumns(5);
        tl48.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tl48.setToolTipText("");
        tl48.setInputVerifier(intVerifier);
        tl48.setMinimumSize(new java.awt.Dimension(50, 19));
        tl48.setName(""); // NOI18N
        tl48.setNextFocusableComponent(tl49);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        jPanel9.add(tl48, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        panelLeves.add(jPanel9, gridBagConstraints);

        jPanel10.setToolTipText("");
        jPanel10.setLayout(new java.awt.GridBagLayout());

        tl56.setColumns(5);
        tl56.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tl56.setToolTipText("");
        tl56.setInputVerifier(intVerifier);
        tl56.setMinimumSize(new java.awt.Dimension(50, 19));
        tl56.setName(""); // NOI18N
        tl56.setNextFocusableComponent(tl57);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        jPanel10.add(tl56, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        panelLeves.add(jPanel10, gridBagConstraints);

        jPanel11.setToolTipText("");
        jPanel11.setLayout(new java.awt.GridBagLayout());

        tl64.setColumns(5);
        tl64.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tl64.setToolTipText("");
        tl64.setInputVerifier(intVerifier);
        tl64.setMinimumSize(new java.awt.Dimension(50, 19));
        tl64.setName(""); // NOI18N
        tl64.setNextFocusableComponent(tl65);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        jPanel11.add(tl64, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        panelLeves.add(jPanel11, gridBagConstraints);

        jPanel12.setToolTipText("");
        jPanel12.setLayout(new java.awt.GridBagLayout());

        tl72.setColumns(5);
        tl72.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tl72.setToolTipText("");
        tl72.setInputVerifier(intVerifier);
        tl72.setMinimumSize(new java.awt.Dimension(50, 19));
        tl72.setName(""); // NOI18N
        tl72.setNextFocusableComponent(tl73);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        jPanel12.add(tl72, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 11;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        panelLeves.add(jPanel12, gridBagConstraints);

        jPanel13.setToolTipText("");
        jPanel13.setLayout(new java.awt.GridBagLayout());

        tl80.setColumns(5);
        tl80.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tl80.setToolTipText("");
        tl80.setInputVerifier(intVerifier);
        tl80.setMinimumSize(new java.awt.Dimension(50, 19));
        tl80.setName(""); // NOI18N
        tl80.setNextFocusableComponent(tl81);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        jPanel13.add(tl80, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        panelLeves.add(jPanel13, gridBagConstraints);

        jPanel14.setToolTipText("");
        jPanel14.setLayout(new java.awt.GridBagLayout());

        tl88.setColumns(5);
        tl88.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tl88.setToolTipText("");
        tl88.setInputVerifier(intVerifier);
        tl88.setMinimumSize(new java.awt.Dimension(50, 19));
        tl88.setName(""); // NOI18N
        tl88.setNextFocusableComponent(tl89);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        jPanel14.add(tl88, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 13;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        panelLeves.add(jPanel14, gridBagConstraints);

        jPanel15.setLayout(new java.awt.GridBagLayout());

        tl2.setColumns(5);
        tl2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tl2.setToolTipText("");
        tl2.setInputVerifier(intVerifier);
        tl2.setMinimumSize(new java.awt.Dimension(50, 19));
        tl2.setName(""); // NOI18N
        tl2.setNextFocusableComponent(tl3);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        jPanel15.add(tl2, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        panelLeves.add(jPanel15, gridBagConstraints);

        jPanel16.setLayout(new java.awt.GridBagLayout());

        tl10.setColumns(5);
        tl10.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tl10.setToolTipText("");
        tl10.setInputVerifier(intVerifier);
        tl10.setMinimumSize(new java.awt.Dimension(50, 19));
        tl10.setName(""); // NOI18N
        tl10.setNextFocusableComponent(tl11);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        jPanel16.add(tl10, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        panelLeves.add(jPanel16, gridBagConstraints);

        jPanel17.setLayout(new java.awt.GridBagLayout());

        tl18.setColumns(5);
        tl18.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tl18.setToolTipText("");
        tl18.setInputVerifier(intVerifier);
        tl18.setMinimumSize(new java.awt.Dimension(50, 19));
        tl18.setName(""); // NOI18N
        tl18.setNextFocusableComponent(tl19);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        jPanel17.add(tl18, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        panelLeves.add(jPanel17, gridBagConstraints);

        jPanel18.setLayout(new java.awt.GridBagLayout());

        tl26.setColumns(5);
        tl26.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tl26.setToolTipText("");
        tl26.setInputVerifier(intVerifier);
        tl26.setMinimumSize(new java.awt.Dimension(50, 19));
        tl26.setName(""); // NOI18N
        tl26.setNextFocusableComponent(tl27);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        jPanel18.add(tl26, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        panelLeves.add(jPanel18, gridBagConstraints);

        jPanel19.setLayout(new java.awt.GridBagLayout());

        tl34.setColumns(5);
        tl34.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tl34.setToolTipText("");
        tl34.setInputVerifier(intVerifier);
        tl34.setMinimumSize(new java.awt.Dimension(50, 19));
        tl34.setName(""); // NOI18N
        tl34.setNextFocusableComponent(tl35);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        jPanel19.add(tl34, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        panelLeves.add(jPanel19, gridBagConstraints);

        jPanel20.setLayout(new java.awt.GridBagLayout());

        tl42.setColumns(5);
        tl42.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tl42.setToolTipText("");
        tl42.setInputVerifier(intVerifier);
        tl42.setMinimumSize(new java.awt.Dimension(50, 19));
        tl42.setName(""); // NOI18N
        tl42.setNextFocusableComponent(tl43);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        jPanel20.add(tl42, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        panelLeves.add(jPanel20, gridBagConstraints);

        jPanel21.setLayout(new java.awt.GridBagLayout());

        tl50.setColumns(5);
        tl50.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tl50.setToolTipText("");
        tl50.setInputVerifier(intVerifier);
        tl50.setMinimumSize(new java.awt.Dimension(50, 19));
        tl50.setName(""); // NOI18N
        tl50.setNextFocusableComponent(tl51);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        jPanel21.add(tl50, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        panelLeves.add(jPanel21, gridBagConstraints);

        jPanel22.setLayout(new java.awt.GridBagLayout());

        tl58.setColumns(5);
        tl58.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tl58.setToolTipText("");
        tl58.setInputVerifier(intVerifier);
        tl58.setMinimumSize(new java.awt.Dimension(50, 19));
        tl58.setName(""); // NOI18N
        tl58.setNextFocusableComponent(tl59);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        jPanel22.add(tl58, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        panelLeves.add(jPanel22, gridBagConstraints);

        jPanel23.setLayout(new java.awt.GridBagLayout());

        tl66.setColumns(5);
        tl66.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tl66.setToolTipText("");
        tl66.setInputVerifier(intVerifier);
        tl66.setMinimumSize(new java.awt.Dimension(50, 19));
        tl66.setName(""); // NOI18N
        tl66.setNextFocusableComponent(tl67);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        jPanel23.add(tl66, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        panelLeves.add(jPanel23, gridBagConstraints);

        jPanel24.setLayout(new java.awt.GridBagLayout());

        tl74.setColumns(5);
        tl74.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tl74.setToolTipText("");
        tl74.setInputVerifier(intVerifier);
        tl74.setMinimumSize(new java.awt.Dimension(50, 19));
        tl74.setName(""); // NOI18N
        tl74.setNextFocusableComponent(tl75);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        jPanel24.add(tl74, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 11;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        panelLeves.add(jPanel24, gridBagConstraints);

        jPanel25.setLayout(new java.awt.GridBagLayout());

        tl82.setColumns(5);
        tl82.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tl82.setToolTipText("");
        tl82.setInputVerifier(intVerifier);
        tl82.setMinimumSize(new java.awt.Dimension(50, 19));
        tl82.setName(""); // NOI18N
        tl82.setNextFocusableComponent(tl83);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        jPanel25.add(tl82, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        panelLeves.add(jPanel25, gridBagConstraints);

        jPanel26.setLayout(new java.awt.GridBagLayout());

        tl90.setColumns(5);
        tl90.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tl90.setToolTipText("");
        tl90.setInputVerifier(intVerifier);
        tl90.setMinimumSize(new java.awt.Dimension(50, 19));
        tl90.setName(""); // NOI18N
        tl90.setNextFocusableComponent(tl91);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        jPanel26.add(tl90, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 13;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        panelLeves.add(jPanel26, gridBagConstraints);

        jPanel27.setLayout(new java.awt.GridBagLayout());

        tl4.setColumns(5);
        tl4.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tl4.setToolTipText("");
        tl4.setInputVerifier(intVerifier);
        tl4.setMinimumSize(new java.awt.Dimension(50, 19));
        tl4.setName(""); // NOI18N
        tl4.setNextFocusableComponent(tl5);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        jPanel27.add(tl4, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        panelLeves.add(jPanel27, gridBagConstraints);

        jPanel28.setLayout(new java.awt.GridBagLayout());

        tl12.setColumns(5);
        tl12.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tl12.setToolTipText("");
        tl12.setInputVerifier(intVerifier);
        tl12.setMinimumSize(new java.awt.Dimension(50, 19));
        tl12.setName(""); // NOI18N
        tl12.setNextFocusableComponent(tl13);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        jPanel28.add(tl12, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        panelLeves.add(jPanel28, gridBagConstraints);

        jPanel29.setLayout(new java.awt.GridBagLayout());

        tl20.setColumns(5);
        tl20.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tl20.setToolTipText("");
        tl20.setInputVerifier(intVerifier);
        tl20.setMinimumSize(new java.awt.Dimension(50, 19));
        tl20.setName(""); // NOI18N
        tl20.setNextFocusableComponent(tl21);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        jPanel29.add(tl20, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        panelLeves.add(jPanel29, gridBagConstraints);

        jPanel30.setLayout(new java.awt.GridBagLayout());

        tl28.setColumns(5);
        tl28.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tl28.setToolTipText("");
        tl28.setInputVerifier(intVerifier);
        tl28.setMinimumSize(new java.awt.Dimension(50, 19));
        tl28.setName(""); // NOI18N
        tl28.setNextFocusableComponent(tl29);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        jPanel30.add(tl28, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        panelLeves.add(jPanel30, gridBagConstraints);

        jPanel31.setLayout(new java.awt.GridBagLayout());

        tl36.setColumns(5);
        tl36.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tl36.setToolTipText("");
        tl36.setInputVerifier(intVerifier);
        tl36.setMinimumSize(new java.awt.Dimension(50, 19));
        tl36.setName(""); // NOI18N
        tl36.setNextFocusableComponent(tl37);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        jPanel31.add(tl36, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        panelLeves.add(jPanel31, gridBagConstraints);

        jPanel32.setLayout(new java.awt.GridBagLayout());

        tl44.setColumns(5);
        tl44.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tl44.setToolTipText("");
        tl44.setInputVerifier(intVerifier);
        tl44.setMinimumSize(new java.awt.Dimension(50, 19));
        tl44.setName(""); // NOI18N
        tl44.setNextFocusableComponent(tl45);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        jPanel32.add(tl44, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        panelLeves.add(jPanel32, gridBagConstraints);

        jPanel33.setLayout(new java.awt.GridBagLayout());

        tl52.setColumns(5);
        tl52.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tl52.setToolTipText("");
        tl52.setInputVerifier(intVerifier);
        tl52.setMinimumSize(new java.awt.Dimension(50, 19));
        tl52.setName(""); // NOI18N
        tl52.setNextFocusableComponent(tl53);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        jPanel33.add(tl52, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        panelLeves.add(jPanel33, gridBagConstraints);

        jPanel34.setLayout(new java.awt.GridBagLayout());

        tl60.setColumns(5);
        tl60.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tl60.setToolTipText("");
        tl60.setInputVerifier(intVerifier);
        tl60.setMinimumSize(new java.awt.Dimension(50, 19));
        tl60.setName(""); // NOI18N
        tl60.setNextFocusableComponent(tl61);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        jPanel34.add(tl60, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        panelLeves.add(jPanel34, gridBagConstraints);

        jPanel35.setLayout(new java.awt.GridBagLayout());

        tl68.setColumns(5);
        tl68.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tl68.setToolTipText("");
        tl68.setInputVerifier(intVerifier);
        tl68.setMinimumSize(new java.awt.Dimension(50, 19));
        tl68.setName(""); // NOI18N
        tl68.setNextFocusableComponent(tl69);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        jPanel35.add(tl68, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        panelLeves.add(jPanel35, gridBagConstraints);

        jPanel36.setLayout(new java.awt.GridBagLayout());

        tl76.setColumns(5);
        tl76.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tl76.setToolTipText("");
        tl76.setInputVerifier(intVerifier);
        tl76.setMinimumSize(new java.awt.Dimension(50, 19));
        tl76.setName(""); // NOI18N
        tl76.setNextFocusableComponent(tl77);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        jPanel36.add(tl76, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 11;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        panelLeves.add(jPanel36, gridBagConstraints);

        jPanel37.setLayout(new java.awt.GridBagLayout());

        tl84.setColumns(5);
        tl84.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tl84.setToolTipText("");
        tl84.setInputVerifier(intVerifier);
        tl84.setMinimumSize(new java.awt.Dimension(50, 19));
        tl84.setName(""); // NOI18N
        tl84.setNextFocusableComponent(tl85);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        jPanel37.add(tl84, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        panelLeves.add(jPanel37, gridBagConstraints);

        jPanel38.setLayout(new java.awt.GridBagLayout());

        tl92.setColumns(5);
        tl92.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tl92.setToolTipText("");
        tl92.setInputVerifier(intVerifier);
        tl92.setMinimumSize(new java.awt.Dimension(50, 19));
        tl92.setName(""); // NOI18N
        tl92.setNextFocusableComponent(tl93);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        jPanel38.add(tl92, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 13;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        panelLeves.add(jPanel38, gridBagConstraints);

        jPanel39.setLayout(new java.awt.GridBagLayout());

        tl6.setColumns(5);
        tl6.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tl6.setToolTipText("");
        tl6.setInputVerifier(intVerifier);
        tl6.setMinimumSize(new java.awt.Dimension(50, 19));
        tl6.setName(""); // NOI18N
        tl6.setNextFocusableComponent(tl7);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        jPanel39.add(tl6, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        panelLeves.add(jPanel39, gridBagConstraints);

        jPanel40.setLayout(new java.awt.GridBagLayout());

        tl14.setColumns(5);
        tl14.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tl14.setToolTipText("");
        tl14.setInputVerifier(intVerifier);
        tl14.setMinimumSize(new java.awt.Dimension(50, 19));
        tl14.setName(""); // NOI18N
        tl14.setNextFocusableComponent(tl15);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        jPanel40.add(tl14, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        panelLeves.add(jPanel40, gridBagConstraints);

        jPanel41.setLayout(new java.awt.GridBagLayout());

        tl22.setColumns(5);
        tl22.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tl22.setToolTipText("");
        tl22.setInputVerifier(intVerifier);
        tl22.setMinimumSize(new java.awt.Dimension(50, 19));
        tl22.setName(""); // NOI18N
        tl22.setNextFocusableComponent(tl23);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        jPanel41.add(tl22, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        panelLeves.add(jPanel41, gridBagConstraints);

        jPanel42.setLayout(new java.awt.GridBagLayout());

        tl30.setColumns(5);
        tl30.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tl30.setToolTipText("");
        tl30.setInputVerifier(intVerifier);
        tl30.setMinimumSize(new java.awt.Dimension(50, 19));
        tl30.setName(""); // NOI18N
        tl30.setNextFocusableComponent(tl31);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        jPanel42.add(tl30, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        panelLeves.add(jPanel42, gridBagConstraints);

        jPanel43.setLayout(new java.awt.GridBagLayout());

        tl38.setColumns(5);
        tl38.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tl38.setToolTipText("");
        tl38.setInputVerifier(intVerifier);
        tl38.setMinimumSize(new java.awt.Dimension(50, 19));
        tl38.setName(""); // NOI18N
        tl38.setNextFocusableComponent(tl39);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        jPanel43.add(tl38, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        panelLeves.add(jPanel43, gridBagConstraints);

        jPanel44.setLayout(new java.awt.GridBagLayout());

        tl46.setColumns(5);
        tl46.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tl46.setToolTipText("");
        tl46.setInputVerifier(intVerifier);
        tl46.setMinimumSize(new java.awt.Dimension(50, 19));
        tl46.setName(""); // NOI18N
        tl46.setNextFocusableComponent(tl47);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        jPanel44.add(tl46, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        panelLeves.add(jPanel44, gridBagConstraints);

        jPanel45.setLayout(new java.awt.GridBagLayout());

        tl54.setColumns(5);
        tl54.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tl54.setToolTipText("");
        tl54.setInputVerifier(intVerifier);
        tl54.setMinimumSize(new java.awt.Dimension(50, 19));
        tl54.setName(""); // NOI18N
        tl54.setNextFocusableComponent(tl55);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        jPanel45.add(tl54, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        panelLeves.add(jPanel45, gridBagConstraints);

        jPanel46.setLayout(new java.awt.GridBagLayout());

        tl62.setColumns(5);
        tl62.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tl62.setToolTipText("");
        tl62.setInputVerifier(intVerifier);
        tl62.setMinimumSize(new java.awt.Dimension(50, 19));
        tl62.setName(""); // NOI18N
        tl62.setNextFocusableComponent(tl63);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        jPanel46.add(tl62, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        panelLeves.add(jPanel46, gridBagConstraints);

        jPanel47.setLayout(new java.awt.GridBagLayout());

        tl70.setColumns(5);
        tl70.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tl70.setToolTipText("");
        tl70.setInputVerifier(intVerifier);
        tl70.setMinimumSize(new java.awt.Dimension(50, 19));
        tl70.setName(""); // NOI18N
        tl70.setNextFocusableComponent(tl71);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        jPanel47.add(tl70, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        panelLeves.add(jPanel47, gridBagConstraints);

        jPanel48.setLayout(new java.awt.GridBagLayout());

        tl78.setColumns(5);
        tl78.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tl78.setToolTipText("");
        tl78.setInputVerifier(intVerifier);
        tl78.setMinimumSize(new java.awt.Dimension(50, 19));
        tl78.setName(""); // NOI18N
        tl78.setNextFocusableComponent(tl79);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        jPanel48.add(tl78, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 11;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        panelLeves.add(jPanel48, gridBagConstraints);

        jPanel49.setLayout(new java.awt.GridBagLayout());

        tl86.setColumns(5);
        tl86.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tl86.setToolTipText("");
        tl86.setInputVerifier(intVerifier);
        tl86.setMinimumSize(new java.awt.Dimension(50, 19));
        tl86.setName(""); // NOI18N
        tl86.setNextFocusableComponent(tl87);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        jPanel49.add(tl86, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        panelLeves.add(jPanel49, gridBagConstraints);

        jPanel50.setLayout(new java.awt.GridBagLayout());

        tl94.setColumns(5);
        tl94.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tl94.setToolTipText("");
        tl94.setInputVerifier(intVerifier);
        tl94.setMinimumSize(new java.awt.Dimension(50, 19));
        tl94.setName(""); // NOI18N
        tl94.setNextFocusableComponent(tl95);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        jPanel50.add(tl94, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 13;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        panelLeves.add(jPanel50, gridBagConstraints);

        javax.swing.GroupLayout jPanel119Layout = new javax.swing.GroupLayout(jPanel119);
        jPanel119.setLayout(jPanel119Layout);
        jPanel119Layout.setHorizontalGroup(
            jPanel119Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelLeves, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel119Layout.setVerticalGroup(
            jPanel119Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel119Layout.createSequentialGroup()
                .addComponent(panelLeves, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jScrollPane3.setViewportView(jPanel119);

        javax.swing.GroupLayout tab_levesLayout = new javax.swing.GroupLayout(tab_leves);
        tab_leves.setLayout(tab_levesLayout);
        tab_levesLayout.setHorizontalGroup(
            tab_levesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab_levesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblPesquisador)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtPesquisador1, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1295, Short.MAX_VALUE)
        );
        tab_levesLayout.setVerticalGroup(
            tab_levesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab_levesLayout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addGroup(tab_levesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPesquisador)
                    .addComponent(txtPesquisador1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 584, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Veículos e Caminhões Leves", tab_leves);

        panelPesados.setBackground(new java.awt.Color(255, 255, 255));
        panelPesados.setMinimumSize(new java.awt.Dimension(200, 76));
        panelPesados.setLayout(new java.awt.GridBagLayout());

        pesados_hora1.setEditable(false);
        pesados_hora1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        panelPesados.add(pesados_hora1, gridBagConstraints);

        pes15_1.setEditable(false);
        pes15_1.setBackground(new java.awt.Color(204, 204, 204));
        pes15_1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        pes15_1.setText("15");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        panelPesados.add(pes15_1, gridBagConstraints);

        pes30_1.setEditable(false);
        pes30_1.setBackground(new java.awt.Color(255, 255, 255));
        pes30_1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        pes30_1.setText("30");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        panelPesados.add(pes30_1, gridBagConstraints);

        pes45_1.setEditable(false);
        pes45_1.setBackground(new java.awt.Color(204, 204, 204));
        pes45_1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        pes45_1.setText("45");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        panelPesados.add(pes45_1, gridBagConstraints);

        pes60_1.setEditable(false);
        pes60_1.setBackground(new java.awt.Color(255, 255, 255));
        pes60_1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        pes60_1.setText("60");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        panelPesados.add(pes60_1, gridBagConstraints);

        pesados_hora2.setEditable(false);
        pesados_hora2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        panelPesados.add(pesados_hora2, gridBagConstraints);

        pes15_2.setEditable(false);
        pes15_2.setBackground(new java.awt.Color(204, 204, 204));
        pes15_2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        pes15_2.setText("15");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        panelPesados.add(pes15_2, gridBagConstraints);

        pes30_2.setEditable(false);
        pes30_2.setBackground(new java.awt.Color(255, 255, 255));
        pes30_2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        pes30_2.setText("30");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        panelPesados.add(pes30_2, gridBagConstraints);

        peso45_2.setEditable(false);
        peso45_2.setBackground(new java.awt.Color(204, 204, 204));
        peso45_2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        peso45_2.setText("45");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        panelPesados.add(peso45_2, gridBagConstraints);

        peso60_2.setEditable(false);
        peso60_2.setBackground(new java.awt.Color(255, 255, 255));
        peso60_2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        peso60_2.setText("60");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 9;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        panelPesados.add(peso60_2, gridBagConstraints);

        lbl2S1.setBackground(new java.awt.Color(255, 255, 255));
        lbl2S1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl2S1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/semireboques/s1.png"))); // NOI18N
        lbl2S1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 3, 1));
        lbl2S1.setFocusable(false);
        lbl2S1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        lbl2S1.setMinimumSize(new java.awt.Dimension(200, 76));
        lbl2S1.setName(""); // NOI18N
        lbl2S1.setOpaque(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        panelPesados.add(lbl2S1, gridBagConstraints);
        lbl2S1.getAccessibleContext().setAccessibleDescription("");

        tp1.setColumns(5);
        tp1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tp1.setToolTipText("");
        tp1.setInputVerifier(intVerifier);
        tp1.setMinimumSize(new java.awt.Dimension(50, 19));
        tp1.setName(""); // NOI18N
        tp1.setNextFocusableComponent(tp2);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.weightx = 1.0;
        panelPesados.add(tp1, gridBagConstraints);

        tp3.setColumns(5);
        tp3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tp3.setToolTipText("");
        tp3.setInputVerifier(intVerifier);
        tp3.setMinimumSize(new java.awt.Dimension(50, 19));
        tp3.setName(""); // NOI18N
        tp3.setNextFocusableComponent(tp4);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.weightx = 1.0;
        panelPesados.add(tp3, gridBagConstraints);

        tp5.setColumns(5);
        tp5.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tp5.setToolTipText("");
        tp5.setInputVerifier(intVerifier);
        tp5.setMinimumSize(new java.awt.Dimension(50, 19));
        tp5.setName(""); // NOI18N
        tp5.setNextFocusableComponent(tp6);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.weightx = 1.0;
        panelPesados.add(tp5, gridBagConstraints);

        tp7.setColumns(5);
        tp7.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tp7.setToolTipText("");
        tp7.setInputVerifier(intVerifier);
        tp7.setMinimumSize(new java.awt.Dimension(50, 19));
        tp7.setName(""); // NOI18N
        tp7.setNextFocusableComponent(tp8);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 9;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.weightx = 1.0;
        panelPesados.add(tp7, gridBagConstraints);

        lbl2S2.setBackground(new java.awt.Color(255, 255, 255));
        lbl2S2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl2S2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/semireboques/s2.png"))); // NOI18N
        lbl2S2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 3, 1));
        lbl2S2.setFocusable(false);
        lbl2S2.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        lbl2S2.setMinimumSize(new java.awt.Dimension(200, 76));
        lbl2S2.setOpaque(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        panelPesados.add(lbl2S2, gridBagConstraints);

        tp9.setColumns(5);
        tp9.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tp9.setToolTipText("");
        tp9.setInputVerifier(intVerifier);
        tp9.setMinimumSize(new java.awt.Dimension(50, 19));
        tp9.setName(""); // NOI18N
        tp9.setNextFocusableComponent(tp10);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.weightx = 1.0;
        panelPesados.add(tp9, gridBagConstraints);

        tp11.setColumns(5);
        tp11.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tp11.setToolTipText("");
        tp11.setInputVerifier(intVerifier);
        tp11.setMinimumSize(new java.awt.Dimension(50, 19));
        tp11.setName(""); // NOI18N
        tp11.setNextFocusableComponent(tp12);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.weightx = 1.0;
        panelPesados.add(tp11, gridBagConstraints);

        tp13.setColumns(5);
        tp13.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tp13.setToolTipText("");
        tp13.setInputVerifier(intVerifier);
        tp13.setMinimumSize(new java.awt.Dimension(50, 19));
        tp13.setName(""); // NOI18N
        tp13.setNextFocusableComponent(tp14);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.weightx = 1.0;
        panelPesados.add(tp13, gridBagConstraints);

        tp15.setColumns(5);
        tp15.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tp15.setToolTipText("");
        tp15.setInputVerifier(intVerifier);
        tp15.setMinimumSize(new java.awt.Dimension(50, 19));
        tp15.setName(""); // NOI18N
        tp15.setNextFocusableComponent(tp16);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 9;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.weightx = 1.0;
        panelPesados.add(tp15, gridBagConstraints);

        lbl2S3.setBackground(new java.awt.Color(255, 255, 255));
        lbl2S3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl2S3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/semireboques/s3.png"))); // NOI18N
        lbl2S3.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 3, 1));
        lbl2S3.setFocusable(false);
        lbl2S3.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        lbl2S3.setMinimumSize(new java.awt.Dimension(200, 76));
        lbl2S3.setOpaque(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        panelPesados.add(lbl2S3, gridBagConstraints);

        tp17.setColumns(5);
        tp17.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tp17.setToolTipText("");
        tp17.setInputVerifier(intVerifier);
        tp17.setMinimumSize(new java.awt.Dimension(50, 19));
        tp17.setName(""); // NOI18N
        tp17.setNextFocusableComponent(tp18);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.weightx = 1.0;
        panelPesados.add(tp17, gridBagConstraints);

        tp19.setColumns(5);
        tp19.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tp19.setToolTipText("");
        tp19.setInputVerifier(intVerifier);
        tp19.setMinimumSize(new java.awt.Dimension(50, 19));
        tp19.setName(""); // NOI18N
        tp19.setNextFocusableComponent(tp20);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.weightx = 1.0;
        panelPesados.add(tp19, gridBagConstraints);

        tp21.setColumns(5);
        tp21.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tp21.setToolTipText("");
        tp21.setInputVerifier(intVerifier);
        tp21.setMinimumSize(new java.awt.Dimension(50, 19));
        tp21.setName(""); // NOI18N
        tp21.setNextFocusableComponent(tp22);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.weightx = 1.0;
        panelPesados.add(tp21, gridBagConstraints);

        tp23.setColumns(5);
        tp23.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tp23.setToolTipText("");
        tp23.setInputVerifier(intVerifier);
        tp23.setMinimumSize(new java.awt.Dimension(50, 19));
        tp23.setName(""); // NOI18N
        tp23.setNextFocusableComponent(tp24);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 9;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.weightx = 1.0;
        panelPesados.add(tp23, gridBagConstraints);

        lbl3S1.setBackground(new java.awt.Color(255, 255, 255));
        lbl3S1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl3S1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/semireboques/s4.png"))); // NOI18N
        lbl3S1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 3, 1));
        lbl3S1.setFocusable(false);
        lbl3S1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        lbl3S1.setMinimumSize(new java.awt.Dimension(200, 76));
        lbl3S1.setOpaque(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        panelPesados.add(lbl3S1, gridBagConstraints);

        tp25.setColumns(5);
        tp25.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tp25.setToolTipText("");
        tp25.setInputVerifier(intVerifier);
        tp25.setMinimumSize(new java.awt.Dimension(50, 19));
        tp25.setName(""); // NOI18N
        tp25.setNextFocusableComponent(tp26);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.weightx = 1.0;
        panelPesados.add(tp25, gridBagConstraints);

        tp27.setColumns(5);
        tp27.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tp27.setToolTipText("");
        tp27.setInputVerifier(intVerifier);
        tp27.setMinimumSize(new java.awt.Dimension(50, 19));
        tp27.setName(""); // NOI18N
        tp27.setNextFocusableComponent(tp28);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.weightx = 1.0;
        panelPesados.add(tp27, gridBagConstraints);

        tp29.setColumns(5);
        tp29.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tp29.setToolTipText("");
        tp29.setInputVerifier(intVerifier);
        tp29.setMinimumSize(new java.awt.Dimension(50, 19));
        tp29.setName(""); // NOI18N
        tp29.setNextFocusableComponent(tp30);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.weightx = 1.0;
        panelPesados.add(tp29, gridBagConstraints);

        tp31.setColumns(5);
        tp31.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tp31.setToolTipText("");
        tp31.setInputVerifier(intVerifier);
        tp31.setMinimumSize(new java.awt.Dimension(50, 19));
        tp31.setName(""); // NOI18N
        tp31.setNextFocusableComponent(tp32);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 9;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.weightx = 1.0;
        panelPesados.add(tp31, gridBagConstraints);

        lbl3S2.setBackground(new java.awt.Color(255, 255, 255));
        lbl3S2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl3S2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/semireboques/s5.png"))); // NOI18N
        lbl3S2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 3, 1));
        lbl3S2.setFocusable(false);
        lbl3S2.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        lbl3S2.setMinimumSize(new java.awt.Dimension(200, 76));
        lbl3S2.setOpaque(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        panelPesados.add(lbl3S2, gridBagConstraints);

        tp33.setColumns(5);
        tp33.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tp33.setToolTipText("");
        tp33.setInputVerifier(intVerifier);
        tp33.setMinimumSize(new java.awt.Dimension(50, 19));
        tp33.setName(""); // NOI18N
        tp33.setNextFocusableComponent(tp34);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 6;
        panelPesados.add(tp33, gridBagConstraints);

        tp35.setColumns(5);
        tp35.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tp35.setToolTipText("");
        tp35.setInputVerifier(intVerifier);
        tp35.setMinimumSize(new java.awt.Dimension(50, 19));
        tp35.setName(""); // NOI18N
        tp35.setNextFocusableComponent(tp36);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 6;
        panelPesados.add(tp35, gridBagConstraints);

        tp37.setColumns(5);
        tp37.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tp37.setToolTipText("");
        tp37.setInputVerifier(intVerifier);
        tp37.setMinimumSize(new java.awt.Dimension(50, 19));
        tp37.setName(""); // NOI18N
        tp37.setNextFocusableComponent(tp38);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 6;
        panelPesados.add(tp37, gridBagConstraints);

        tp39.setColumns(5);
        tp39.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tp39.setToolTipText("");
        tp39.setInputVerifier(intVerifier);
        tp39.setMinimumSize(new java.awt.Dimension(50, 19));
        tp39.setName(""); // NOI18N
        tp39.setNextFocusableComponent(tp40);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 9;
        gridBagConstraints.gridy = 6;
        panelPesados.add(tp39, gridBagConstraints);

        lbl3S3.setBackground(new java.awt.Color(255, 255, 255));
        lbl3S3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl3S3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/semireboques/s6.png"))); // NOI18N
        lbl3S3.setToolTipText("");
        lbl3S3.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 3, 1));
        lbl3S3.setFocusable(false);
        lbl3S3.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        lbl3S3.setMinimumSize(new java.awt.Dimension(200, 76));
        lbl3S3.setOpaque(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        panelPesados.add(lbl3S3, gridBagConstraints);

        tp41.setColumns(5);
        tp41.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tp41.setToolTipText("");
        tp41.setInputVerifier(intVerifier);
        tp41.setMinimumSize(new java.awt.Dimension(50, 19));
        tp41.setName(""); // NOI18N
        tp41.setNextFocusableComponent(tp42);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 7;
        panelPesados.add(tp41, gridBagConstraints);

        tp43.setColumns(5);
        tp43.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tp43.setToolTipText("");
        tp43.setInputVerifier(intVerifier);
        tp43.setMinimumSize(new java.awt.Dimension(50, 19));
        tp43.setName(""); // NOI18N
        tp43.setNextFocusableComponent(tp44);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 7;
        panelPesados.add(tp43, gridBagConstraints);

        tp45.setColumns(5);
        tp45.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tp45.setToolTipText("");
        tp45.setInputVerifier(intVerifier);
        tp45.setMinimumSize(new java.awt.Dimension(50, 19));
        tp45.setName(""); // NOI18N
        tp45.setNextFocusableComponent(tp46);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 7;
        panelPesados.add(tp45, gridBagConstraints);

        tp47.setColumns(5);
        tp47.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tp47.setToolTipText("");
        tp47.setInputVerifier(intVerifier);
        tp47.setMinimumSize(new java.awt.Dimension(50, 19));
        tp47.setName(""); // NOI18N
        tp47.setNextFocusableComponent(tp48);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 9;
        gridBagConstraints.gridy = 7;
        panelPesados.add(tp47, gridBagConstraints);

        lbl3T4.setBackground(new java.awt.Color(255, 255, 255));
        lbl3T4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl3T4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/combinados/se1.png"))); // NOI18N
        lbl3T4.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 3, 1));
        lbl3T4.setFocusable(false);
        lbl3T4.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        lbl3T4.setMinimumSize(new java.awt.Dimension(200, 76));
        lbl3T4.setOpaque(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        panelPesados.add(lbl3T4, gridBagConstraints);

        tp49.setColumns(5);
        tp49.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tp49.setToolTipText("");
        tp49.setInputVerifier(intVerifier);
        tp49.setMinimumSize(new java.awt.Dimension(50, 19));
        tp49.setName(""); // NOI18N
        tp49.setNextFocusableComponent(tp50);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 8;
        panelPesados.add(tp49, gridBagConstraints);

        tp51.setColumns(5);
        tp51.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tp51.setToolTipText("");
        tp51.setInputVerifier(intVerifier);
        tp51.setMinimumSize(new java.awt.Dimension(50, 19));
        tp51.setName(""); // NOI18N
        tp51.setNextFocusableComponent(tp52);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 8;
        panelPesados.add(tp51, gridBagConstraints);

        tp53.setColumns(5);
        tp53.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tp53.setToolTipText("");
        tp53.setInputVerifier(intVerifier);
        tp53.setMinimumSize(new java.awt.Dimension(50, 19));
        tp53.setName(""); // NOI18N
        tp53.setNextFocusableComponent(tp54);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 8;
        panelPesados.add(tp53, gridBagConstraints);

        tp55.setColumns(5);
        tp55.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tp55.setToolTipText("");
        tp55.setInputVerifier(intVerifier);
        tp55.setMinimumSize(new java.awt.Dimension(50, 19));
        tp55.setName(""); // NOI18N
        tp55.setNextFocusableComponent(tp56);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 9;
        gridBagConstraints.gridy = 8;
        panelPesados.add(tp55, gridBagConstraints);

        lbl3T6.setBackground(new java.awt.Color(255, 255, 255));
        lbl3T6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl3T6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/combinados/se2.png"))); // NOI18N
        lbl3T6.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 3, 1));
        lbl3T6.setFocusable(false);
        lbl3T6.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        lbl3T6.setMinimumSize(new java.awt.Dimension(200, 76));
        lbl3T6.setOpaque(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        panelPesados.add(lbl3T6, gridBagConstraints);

        tp57.setColumns(5);
        tp57.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tp57.setToolTipText("");
        tp57.setInputVerifier(intVerifier);
        tp57.setMinimumSize(new java.awt.Dimension(50, 19));
        tp57.setName(""); // NOI18N
        tp57.setNextFocusableComponent(tp58);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 9;
        panelPesados.add(tp57, gridBagConstraints);

        tp59.setColumns(5);
        tp59.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tp59.setToolTipText("");
        tp59.setInputVerifier(intVerifier);
        tp59.setMinimumSize(new java.awt.Dimension(50, 19));
        tp59.setName(""); // NOI18N
        tp59.setNextFocusableComponent(tp60);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 9;
        panelPesados.add(tp59, gridBagConstraints);

        tp61.setColumns(5);
        tp61.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tp61.setToolTipText("");
        tp61.setInputVerifier(intVerifier);
        tp61.setMinimumSize(new java.awt.Dimension(50, 19));
        tp61.setName(""); // NOI18N
        tp61.setNextFocusableComponent(tp62);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 9;
        panelPesados.add(tp61, gridBagConstraints);

        tp63.setColumns(5);
        tp63.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tp63.setToolTipText("");
        tp63.setInputVerifier(intVerifier);
        tp63.setMinimumSize(new java.awt.Dimension(50, 19));
        tp63.setName(""); // NOI18N
        tp63.setNextFocusableComponent(tp64);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 9;
        gridBagConstraints.gridy = 9;
        panelPesados.add(tp63, gridBagConstraints);

        lbl3T6B.setBackground(new java.awt.Color(255, 255, 255));
        lbl3T6B.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl3T6B.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/combinados/se3.png"))); // NOI18N
        lbl3T6B.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 3, 1));
        lbl3T6B.setFocusable(false);
        lbl3T6B.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        lbl3T6B.setMinimumSize(new java.awt.Dimension(200, 76));
        lbl3T6B.setOpaque(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        panelPesados.add(lbl3T6B, gridBagConstraints);

        tp65.setColumns(5);
        tp65.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tp65.setToolTipText("");
        tp65.setInputVerifier(intVerifier);
        tp65.setMinimumSize(new java.awt.Dimension(50, 19));
        tp65.setName(""); // NOI18N
        tp65.setNextFocusableComponent(tp66);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 10;
        panelPesados.add(tp65, gridBagConstraints);

        tp67.setColumns(5);
        tp67.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tp67.setToolTipText("");
        tp67.setInputVerifier(intVerifier);
        tp67.setMinimumSize(new java.awt.Dimension(50, 19));
        tp67.setName(""); // NOI18N
        tp67.setNextFocusableComponent(tp68);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 10;
        panelPesados.add(tp67, gridBagConstraints);

        tp69.setColumns(5);
        tp69.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tp69.setToolTipText("");
        tp69.setInputVerifier(intVerifier);
        tp69.setMinimumSize(new java.awt.Dimension(50, 19));
        tp69.setName(""); // NOI18N
        tp69.setNextFocusableComponent(tp70);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 10;
        panelPesados.add(tp69, gridBagConstraints);

        tp71.setColumns(5);
        tp71.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tp71.setToolTipText("");
        tp71.setInputVerifier(intVerifier);
        tp71.setMinimumSize(new java.awt.Dimension(50, 19));
        tp71.setName(""); // NOI18N
        tp71.setNextFocusableComponent(tp72);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 9;
        gridBagConstraints.gridy = 10;
        panelPesados.add(tp71, gridBagConstraints);

        lbl3V5.setBackground(new java.awt.Color(255, 255, 255));
        lbl3V5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl3V5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/combinados/se4.png"))); // NOI18N
        lbl3V5.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 3, 1));
        lbl3V5.setFocusable(false);
        lbl3V5.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        lbl3V5.setMinimumSize(new java.awt.Dimension(200, 76));
        lbl3V5.setOpaque(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 11;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        panelPesados.add(lbl3V5, gridBagConstraints);

        tp73.setColumns(5);
        tp73.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tp73.setToolTipText("");
        tp73.setInputVerifier(intVerifier);
        tp73.setMinimumSize(new java.awt.Dimension(50, 19));
        tp73.setName(""); // NOI18N
        tp73.setNextFocusableComponent(tp74);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 11;
        panelPesados.add(tp73, gridBagConstraints);

        tp75.setColumns(5);
        tp75.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tp75.setToolTipText("");
        tp75.setInputVerifier(intVerifier);
        tp75.setMinimumSize(new java.awt.Dimension(50, 19));
        tp75.setName(""); // NOI18N
        tp75.setNextFocusableComponent(tp76);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 11;
        panelPesados.add(tp75, gridBagConstraints);

        tp77.setColumns(5);
        tp77.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tp77.setToolTipText("");
        tp77.setInputVerifier(intVerifier);
        tp77.setMinimumSize(new java.awt.Dimension(50, 19));
        tp77.setName(""); // NOI18N
        tp77.setNextFocusableComponent(tp78);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 11;
        panelPesados.add(tp77, gridBagConstraints);

        tp79.setColumns(5);
        tp79.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tp79.setToolTipText("");
        tp79.setInputVerifier(intVerifier);
        tp79.setMinimumSize(new java.awt.Dimension(50, 19));
        tp79.setName(""); // NOI18N
        tp79.setNextFocusableComponent(tp80);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 9;
        gridBagConstraints.gridy = 11;
        panelPesados.add(tp79, gridBagConstraints);

        lbl3M6.setBackground(new java.awt.Color(255, 255, 255));
        lbl3M6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl3M6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/combinados/se5.png"))); // NOI18N
        lbl3M6.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 3, 1));
        lbl3M6.setFocusable(false);
        lbl3M6.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        lbl3M6.setMinimumSize(new java.awt.Dimension(200, 76));
        lbl3M6.setOpaque(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        panelPesados.add(lbl3M6, gridBagConstraints);

        tp81.setColumns(5);
        tp81.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tp81.setToolTipText("");
        tp81.setInputVerifier(intVerifier);
        tp81.setMinimumSize(new java.awt.Dimension(50, 19));
        tp81.setName(""); // NOI18N
        tp81.setNextFocusableComponent(tp82);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 12;
        panelPesados.add(tp81, gridBagConstraints);

        tp83.setColumns(5);
        tp83.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tp83.setToolTipText("");
        tp83.setInputVerifier(intVerifier);
        tp83.setMinimumSize(new java.awt.Dimension(50, 19));
        tp83.setName(""); // NOI18N
        tp83.setNextFocusableComponent(tp84);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 12;
        panelPesados.add(tp83, gridBagConstraints);

        tp85.setColumns(5);
        tp85.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tp85.setToolTipText("");
        tp85.setInputVerifier(intVerifier);
        tp85.setMinimumSize(new java.awt.Dimension(50, 19));
        tp85.setName(""); // NOI18N
        tp85.setNextFocusableComponent(tp86);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 12;
        panelPesados.add(tp85, gridBagConstraints);

        tp87.setColumns(5);
        tp87.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tp87.setToolTipText("");
        tp87.setInputVerifier(intVerifier);
        tp87.setMinimumSize(new java.awt.Dimension(50, 19));
        tp87.setName(""); // NOI18N
        tp87.setNextFocusableComponent(tp88);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 9;
        gridBagConstraints.gridy = 12;
        panelPesados.add(tp87, gridBagConstraints);

        lbl3Q4.setBackground(new java.awt.Color(255, 255, 255));
        lbl3Q4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl3Q4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/reboques/r1.png"))); // NOI18N
        lbl3Q4.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 3, 1));
        lbl3Q4.setFocusable(false);
        lbl3Q4.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        lbl3Q4.setMinimumSize(new java.awt.Dimension(200, 76));
        lbl3Q4.setOpaque(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 13;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        panelPesados.add(lbl3Q4, gridBagConstraints);

        tp89.setColumns(5);
        tp89.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tp89.setToolTipText("");
        tp89.setInputVerifier(intVerifier);
        tp89.setMinimumSize(new java.awt.Dimension(50, 19));
        tp89.setName(""); // NOI18N
        tp89.setNextFocusableComponent(tp90);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 13;
        panelPesados.add(tp89, gridBagConstraints);

        tp91.setColumns(5);
        tp91.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tp91.setToolTipText("");
        tp91.setInputVerifier(intVerifier);
        tp91.setMinimumSize(new java.awt.Dimension(50, 19));
        tp91.setName(""); // NOI18N
        tp91.setNextFocusableComponent(tp92);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 13;
        panelPesados.add(tp91, gridBagConstraints);

        tp93.setColumns(5);
        tp93.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tp93.setToolTipText("");
        tp93.setInputVerifier(intVerifier);
        tp93.setMinimumSize(new java.awt.Dimension(50, 19));
        tp93.setName(""); // NOI18N
        tp93.setNextFocusableComponent(tp94);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 13;
        panelPesados.add(tp93, gridBagConstraints);

        tp95.setColumns(5);
        tp95.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tp95.setToolTipText("");
        tp95.setInputVerifier(intVerifier);
        tp95.setMinimumSize(new java.awt.Dimension(50, 19));
        tp95.setName(""); // NOI18N
        tp95.setNextFocusableComponent(tp96);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 9;
        gridBagConstraints.gridy = 13;
        panelPesados.add(tp95, gridBagConstraints);

        lbl2C2.setBackground(new java.awt.Color(255, 255, 255));
        lbl2C2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl2C2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/reboques/r2.png"))); // NOI18N
        lbl2C2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 3, 1));
        lbl2C2.setFocusable(false);
        lbl2C2.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        lbl2C2.setMinimumSize(new java.awt.Dimension(200, 76));
        lbl2C2.setOpaque(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 14;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        panelPesados.add(lbl2C2, gridBagConstraints);

        tp97.setColumns(5);
        tp97.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tp97.setToolTipText("");
        tp97.setInputVerifier(intVerifier);
        tp97.setMinimumSize(new java.awt.Dimension(50, 19));
        tp97.setName(""); // NOI18N
        tp97.setNextFocusableComponent(tp98);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 14;
        panelPesados.add(tp97, gridBagConstraints);

        tp99.setColumns(5);
        tp99.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tp99.setToolTipText("");
        tp99.setInputVerifier(intVerifier);
        tp99.setMinimumSize(new java.awt.Dimension(50, 19));
        tp99.setName(""); // NOI18N
        tp99.setNextFocusableComponent(tp100);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 14;
        panelPesados.add(tp99, gridBagConstraints);

        tp101.setColumns(5);
        tp101.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tp101.setToolTipText("");
        tp101.setInputVerifier(intVerifier);
        tp101.setMinimumSize(new java.awt.Dimension(50, 19));
        tp101.setName(""); // NOI18N
        tp101.setNextFocusableComponent(tp102);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 14;
        panelPesados.add(tp101, gridBagConstraints);

        tp103.setColumns(5);
        tp103.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tp103.setToolTipText("");
        tp103.setInputVerifier(intVerifier);
        tp103.setMinimumSize(new java.awt.Dimension(50, 19));
        tp103.setName(""); // NOI18N
        tp103.setNextFocusableComponent(tp104);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 9;
        gridBagConstraints.gridy = 14;
        panelPesados.add(tp103, gridBagConstraints);

        lbl2C3.setBackground(new java.awt.Color(255, 255, 255));
        lbl2C3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl2C3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/reboques/r3.png"))); // NOI18N
        lbl2C3.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 3, 1));
        lbl2C3.setFocusable(false);
        lbl2C3.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        lbl2C3.setMinimumSize(new java.awt.Dimension(200, 76));
        lbl2C3.setOpaque(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 15;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        panelPesados.add(lbl2C3, gridBagConstraints);

        tp105.setColumns(5);
        tp105.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tp105.setToolTipText("");
        tp105.setInputVerifier(intVerifier);
        tp105.setMinimumSize(new java.awt.Dimension(50, 19));
        tp105.setName(""); // NOI18N
        tp105.setNextFocusableComponent(tp106);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 15;
        panelPesados.add(tp105, gridBagConstraints);

        tp107.setColumns(5);
        tp107.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tp107.setToolTipText("");
        tp107.setInputVerifier(intVerifier);
        tp107.setMinimumSize(new java.awt.Dimension(50, 19));
        tp107.setName(""); // NOI18N
        tp107.setNextFocusableComponent(tp108);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 15;
        panelPesados.add(tp107, gridBagConstraints);

        tp109.setColumns(5);
        tp109.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tp109.setToolTipText("");
        tp109.setInputVerifier(intVerifier);
        tp109.setMinimumSize(new java.awt.Dimension(50, 19));
        tp109.setName(""); // NOI18N
        tp109.setNextFocusableComponent(tp110);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 15;
        panelPesados.add(tp109, gridBagConstraints);

        tp111.setColumns(5);
        tp111.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tp111.setToolTipText("");
        tp111.setInputVerifier(intVerifier);
        tp111.setMinimumSize(new java.awt.Dimension(50, 19));
        tp111.setName(""); // NOI18N
        tp111.setNextFocusableComponent(tp112);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 9;
        gridBagConstraints.gridy = 15;
        panelPesados.add(tp111, gridBagConstraints);

        lbl3C2.setBackground(new java.awt.Color(255, 255, 255));
        lbl3C2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl3C2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/reboques/r4.png"))); // NOI18N
        lbl3C2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 3, 1));
        lbl3C2.setFocusable(false);
        lbl3C2.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        lbl3C2.setMinimumSize(new java.awt.Dimension(200, 76));
        lbl3C2.setOpaque(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 16;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        panelPesados.add(lbl3C2, gridBagConstraints);

        tp113.setColumns(5);
        tp113.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tp113.setToolTipText("");
        tp113.setInputVerifier(intVerifier);
        tp113.setMinimumSize(new java.awt.Dimension(50, 19));
        tp113.setName(""); // NOI18N
        tp113.setNextFocusableComponent(tp114);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 16;
        panelPesados.add(tp113, gridBagConstraints);

        tp115.setColumns(5);
        tp115.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tp115.setToolTipText("");
        tp115.setInputVerifier(intVerifier);
        tp115.setMinimumSize(new java.awt.Dimension(50, 19));
        tp115.setName(""); // NOI18N
        tp115.setNextFocusableComponent(tp116);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 16;
        panelPesados.add(tp115, gridBagConstraints);

        tp117.setColumns(5);
        tp117.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tp117.setToolTipText("");
        tp117.setInputVerifier(intVerifier);
        tp117.setMinimumSize(new java.awt.Dimension(50, 19));
        tp117.setName(""); // NOI18N
        tp117.setNextFocusableComponent(tp118);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 16;
        panelPesados.add(tp117, gridBagConstraints);

        tp119.setColumns(5);
        tp119.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tp119.setToolTipText("");
        tp119.setInputVerifier(intVerifier);
        tp119.setMinimumSize(new java.awt.Dimension(50, 19));
        tp119.setName(""); // NOI18N
        tp119.setNextFocusableComponent(tp120);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 9;
        gridBagConstraints.gridy = 16;
        panelPesados.add(tp119, gridBagConstraints);

        lbl3C3.setBackground(new java.awt.Color(255, 255, 255));
        lbl3C3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl3C3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/reboques/r5.png"))); // NOI18N
        lbl3C3.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 3, 1));
        lbl3C3.setFocusable(false);
        lbl3C3.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        lbl3C3.setMinimumSize(new java.awt.Dimension(200, 76));
        lbl3C3.setOpaque(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 17;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        panelPesados.add(lbl3C3, gridBagConstraints);

        tp121.setColumns(5);
        tp121.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tp121.setToolTipText("");
        tp121.setInputVerifier(intVerifier);
        tp121.setMinimumSize(new java.awt.Dimension(50, 19));
        tp121.setName(""); // NOI18N
        tp121.setNextFocusableComponent(tp122);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 17;
        panelPesados.add(tp121, gridBagConstraints);

        tp123.setColumns(5);
        tp123.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tp123.setToolTipText("");
        tp123.setInputVerifier(intVerifier);
        tp123.setMinimumSize(new java.awt.Dimension(50, 19));
        tp123.setName(""); // NOI18N
        tp123.setNextFocusableComponent(tp124);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 17;
        panelPesados.add(tp123, gridBagConstraints);

        tp125.setColumns(5);
        tp125.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tp125.setToolTipText("");
        tp125.setInputVerifier(intVerifier);
        tp125.setMinimumSize(new java.awt.Dimension(50, 19));
        tp125.setName(""); // NOI18N
        tp125.setNextFocusableComponent(tp126);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 17;
        panelPesados.add(tp125, gridBagConstraints);

        tp127.setColumns(5);
        tp127.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tp127.setToolTipText("");
        tp127.setInputVerifier(intVerifier);
        tp127.setMinimumSize(new java.awt.Dimension(50, 19));
        tp127.setName(""); // NOI18N
        tp127.setNextFocusableComponent(tp128);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 9;
        gridBagConstraints.gridy = 17;
        panelPesados.add(tp127, gridBagConstraints);

        lbl3D4.setBackground(new java.awt.Color(255, 255, 255));
        lbl3D4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl3D4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/reboques/r6.png"))); // NOI18N
        lbl3D4.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 3, 1));
        lbl3D4.setFocusable(false);
        lbl3D4.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        lbl3D4.setMinimumSize(new java.awt.Dimension(200, 76));
        lbl3D4.setOpaque(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 18;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        panelPesados.add(lbl3D4, gridBagConstraints);

        tp129.setColumns(5);
        tp129.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tp129.setToolTipText("");
        tp129.setInputVerifier(intVerifier);
        tp129.setMinimumSize(new java.awt.Dimension(50, 19));
        tp129.setName(""); // NOI18N
        tp129.setNextFocusableComponent(tp130);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 18;
        panelPesados.add(tp129, gridBagConstraints);

        tp131.setColumns(5);
        tp131.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tp131.setToolTipText("");
        tp131.setInputVerifier(intVerifier);
        tp131.setMinimumSize(new java.awt.Dimension(50, 19));
        tp131.setName(""); // NOI18N
        tp131.setNextFocusableComponent(tp132);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 18;
        panelPesados.add(tp131, gridBagConstraints);

        tp133.setColumns(5);
        tp133.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tp133.setToolTipText("");
        tp133.setInputVerifier(intVerifier);
        tp133.setMinimumSize(new java.awt.Dimension(50, 19));
        tp133.setName(""); // NOI18N
        tp133.setNextFocusableComponent(tp134);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 18;
        panelPesados.add(tp133, gridBagConstraints);

        tp135.setColumns(5);
        tp135.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tp135.setToolTipText("");
        tp135.setInputVerifier(intVerifier);
        tp135.setMinimumSize(new java.awt.Dimension(50, 19));
        tp135.setName(""); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 9;
        gridBagConstraints.gridy = 18;
        panelPesados.add(tp135, gridBagConstraints);

        jLabel17.setText("2S1");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        panelPesados.add(jLabel17, gridBagConstraints);

        jLabel18.setText("2S2");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        panelPesados.add(jLabel18, gridBagConstraints);

        jLabel19.setText("2S3");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        panelPesados.add(jLabel19, gridBagConstraints);

        jLabel20.setText("3S1");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        panelPesados.add(jLabel20, gridBagConstraints);

        jLabel21.setText("3S2");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        panelPesados.add(jLabel21, gridBagConstraints);

        jLabel22.setText("3S3");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 7;
        panelPesados.add(jLabel22, gridBagConstraints);

        jLabel23.setText("3T4");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 8;
        panelPesados.add(jLabel23, gridBagConstraints);

        jLabel24.setText("3T6");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 9;
        panelPesados.add(jLabel24, gridBagConstraints);

        jLabel25.setText("3R6");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 10;
        panelPesados.add(jLabel25, gridBagConstraints);

        jLabel26.setText("3V5");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 11;
        panelPesados.add(jLabel26, gridBagConstraints);

        jLabel27.setText("3M6");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 12;
        panelPesados.add(jLabel27, gridBagConstraints);

        jLabel28.setText("3Q4");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 13;
        panelPesados.add(jLabel28, gridBagConstraints);

        jLabel29.setText("2C2");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 14;
        panelPesados.add(jLabel29, gridBagConstraints);

        jLabel30.setText("2C3");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 15;
        panelPesados.add(jLabel30, gridBagConstraints);

        jLabel31.setText("3C2");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 16;
        panelPesados.add(jLabel31, gridBagConstraints);

        jLabel32.setText("3C3");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 17;
        panelPesados.add(jLabel32, gridBagConstraints);

        jLabel33.setText("3D4");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 18;
        panelPesados.add(jLabel33, gridBagConstraints);

        jPanel51.setLayout(new java.awt.GridBagLayout());

        tp0.setColumns(5);
        tp0.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tp0.setToolTipText("");
        tp0.setInputVerifier(intVerifier);
        tp0.setMinimumSize(new java.awt.Dimension(50, 19));
        tp0.setName(""); // NOI18N
        tp0.setNextFocusableComponent(tp1);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        jPanel51.add(tp0, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        panelPesados.add(jPanel51, gridBagConstraints);

        jPanel52.setLayout(new java.awt.GridBagLayout());

        tp8.setColumns(5);
        tp8.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tp8.setToolTipText("");
        tp8.setInputVerifier(intVerifier);
        tp8.setMinimumSize(new java.awt.Dimension(50, 19));
        tp8.setName(""); // NOI18N
        tp8.setNextFocusableComponent(tp9);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        jPanel52.add(tp8, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        panelPesados.add(jPanel52, gridBagConstraints);

        jPanel53.setLayout(new java.awt.GridBagLayout());

        tp16.setColumns(5);
        tp16.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tp16.setToolTipText("");
        tp16.setInputVerifier(intVerifier);
        tp16.setMinimumSize(new java.awt.Dimension(50, 19));
        tp16.setName(""); // NOI18N
        tp16.setNextFocusableComponent(tp17);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        jPanel53.add(tp16, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        panelPesados.add(jPanel53, gridBagConstraints);

        jPanel54.setLayout(new java.awt.GridBagLayout());

        tp24.setColumns(5);
        tp24.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tp24.setToolTipText("");
        tp24.setInputVerifier(intVerifier);
        tp24.setMinimumSize(new java.awt.Dimension(50, 19));
        tp24.setName(""); // NOI18N
        tp24.setNextFocusableComponent(tp25);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        jPanel54.add(tp24, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        panelPesados.add(jPanel54, gridBagConstraints);

        jPanel55.setLayout(new java.awt.GridBagLayout());

        tp32.setColumns(5);
        tp32.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tp32.setToolTipText("");
        tp32.setInputVerifier(intVerifier);
        tp32.setMinimumSize(new java.awt.Dimension(50, 19));
        tp32.setName(""); // NOI18N
        tp32.setNextFocusableComponent(tp33);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        jPanel55.add(tp32, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        panelPesados.add(jPanel55, gridBagConstraints);

        jPanel56.setLayout(new java.awt.GridBagLayout());

        tp40.setColumns(5);
        tp40.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tp40.setToolTipText("");
        tp40.setInputVerifier(intVerifier);
        tp40.setMinimumSize(new java.awt.Dimension(50, 19));
        tp40.setName(""); // NOI18N
        tp40.setNextFocusableComponent(tp41);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        jPanel56.add(tp40, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        panelPesados.add(jPanel56, gridBagConstraints);

        jPanel57.setLayout(new java.awt.GridBagLayout());

        tp48.setColumns(5);
        tp48.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tp48.setToolTipText("");
        tp48.setInputVerifier(intVerifier);
        tp48.setMinimumSize(new java.awt.Dimension(50, 19));
        tp48.setName(""); // NOI18N
        tp48.setNextFocusableComponent(tp49);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        jPanel57.add(tp48, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        panelPesados.add(jPanel57, gridBagConstraints);

        jPanel58.setLayout(new java.awt.GridBagLayout());

        tp56.setColumns(5);
        tp56.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tp56.setToolTipText("");
        tp56.setInputVerifier(intVerifier);
        tp56.setMinimumSize(new java.awt.Dimension(50, 19));
        tp56.setName(""); // NOI18N
        tp56.setNextFocusableComponent(tp57);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        jPanel58.add(tp56, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        panelPesados.add(jPanel58, gridBagConstraints);

        jPanel59.setLayout(new java.awt.GridBagLayout());

        tp64.setColumns(5);
        tp64.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tp64.setToolTipText("");
        tp64.setInputVerifier(intVerifier);
        tp64.setMinimumSize(new java.awt.Dimension(50, 19));
        tp64.setName(""); // NOI18N
        tp64.setNextFocusableComponent(tp65);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        jPanel59.add(tp64, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        panelPesados.add(jPanel59, gridBagConstraints);

        jPanel60.setLayout(new java.awt.GridBagLayout());

        tp72.setColumns(5);
        tp72.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tp72.setToolTipText("");
        tp72.setInputVerifier(intVerifier);
        tp72.setMinimumSize(new java.awt.Dimension(50, 19));
        tp72.setName(""); // NOI18N
        tp72.setNextFocusableComponent(tp73);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        jPanel60.add(tp72, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 11;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        panelPesados.add(jPanel60, gridBagConstraints);

        jPanel61.setLayout(new java.awt.GridBagLayout());

        tp80.setColumns(5);
        tp80.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tp80.setToolTipText("");
        tp80.setInputVerifier(intVerifier);
        tp80.setMinimumSize(new java.awt.Dimension(50, 19));
        tp80.setName(""); // NOI18N
        tp80.setNextFocusableComponent(tp81);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        jPanel61.add(tp80, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        panelPesados.add(jPanel61, gridBagConstraints);

        jPanel62.setLayout(new java.awt.GridBagLayout());

        tp88.setColumns(5);
        tp88.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tp88.setToolTipText("");
        tp88.setInputVerifier(intVerifier);
        tp88.setMinimumSize(new java.awt.Dimension(50, 19));
        tp88.setName(""); // NOI18N
        tp88.setNextFocusableComponent(tp89);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        jPanel62.add(tp88, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 13;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        panelPesados.add(jPanel62, gridBagConstraints);

        jPanel63.setLayout(new java.awt.GridBagLayout());

        tp96.setColumns(5);
        tp96.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tp96.setToolTipText("");
        tp96.setInputVerifier(intVerifier);
        tp96.setMinimumSize(new java.awt.Dimension(50, 19));
        tp96.setName(""); // NOI18N
        tp96.setNextFocusableComponent(tp97);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        jPanel63.add(tp96, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 14;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        panelPesados.add(jPanel63, gridBagConstraints);

        jPanel64.setLayout(new java.awt.GridBagLayout());

        tp104.setColumns(5);
        tp104.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tp104.setToolTipText("");
        tp104.setInputVerifier(intVerifier);
        tp104.setMinimumSize(new java.awt.Dimension(50, 19));
        tp104.setName(""); // NOI18N
        tp104.setNextFocusableComponent(tp105);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        jPanel64.add(tp104, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 15;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        panelPesados.add(jPanel64, gridBagConstraints);

        jPanel65.setLayout(new java.awt.GridBagLayout());

        tp112.setColumns(5);
        tp112.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tp112.setToolTipText("");
        tp112.setInputVerifier(intVerifier);
        tp112.setMinimumSize(new java.awt.Dimension(50, 19));
        tp112.setName(""); // NOI18N
        tp112.setNextFocusableComponent(tp113);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        jPanel65.add(tp112, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 16;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        panelPesados.add(jPanel65, gridBagConstraints);

        jPanel66.setLayout(new java.awt.GridBagLayout());

        tp120.setColumns(5);
        tp120.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tp120.setToolTipText("");
        tp120.setInputVerifier(intVerifier);
        tp120.setMinimumSize(new java.awt.Dimension(50, 19));
        tp120.setName(""); // NOI18N
        tp120.setNextFocusableComponent(tp121);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        jPanel66.add(tp120, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 17;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        panelPesados.add(jPanel66, gridBagConstraints);

        jPanel67.setLayout(new java.awt.GridBagLayout());

        tp128.setColumns(5);
        tp128.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tp128.setToolTipText("");
        tp128.setInputVerifier(intVerifier);
        tp128.setMinimumSize(new java.awt.Dimension(50, 19));
        tp128.setName(""); // NOI18N
        tp128.setNextFocusableComponent(tp129);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        jPanel67.add(tp128, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 18;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        panelPesados.add(jPanel67, gridBagConstraints);

        jPanel68.setLayout(new java.awt.GridBagLayout());

        tp2.setColumns(5);
        tp2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tp2.setToolTipText("");
        tp2.setInputVerifier(intVerifier);
        tp2.setMinimumSize(new java.awt.Dimension(50, 19));
        tp2.setName(""); // NOI18N
        tp2.setNextFocusableComponent(tp3);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        jPanel68.add(tp2, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        panelPesados.add(jPanel68, gridBagConstraints);

        jPanel69.setLayout(new java.awt.GridBagLayout());

        tp10.setColumns(5);
        tp10.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tp10.setToolTipText("");
        tp10.setInputVerifier(intVerifier);
        tp10.setMinimumSize(new java.awt.Dimension(50, 19));
        tp10.setName(""); // NOI18N
        tp10.setNextFocusableComponent(tp11);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        jPanel69.add(tp10, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        panelPesados.add(jPanel69, gridBagConstraints);

        jPanel70.setLayout(new java.awt.GridBagLayout());

        tp18.setColumns(5);
        tp18.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tp18.setToolTipText("");
        tp18.setInputVerifier(intVerifier);
        tp18.setMinimumSize(new java.awt.Dimension(50, 19));
        tp18.setName(""); // NOI18N
        tp18.setNextFocusableComponent(tp19);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        jPanel70.add(tp18, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        panelPesados.add(jPanel70, gridBagConstraints);

        jPanel71.setLayout(new java.awt.GridBagLayout());

        tp26.setColumns(5);
        tp26.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tp26.setToolTipText("");
        tp26.setInputVerifier(intVerifier);
        tp26.setMinimumSize(new java.awt.Dimension(50, 19));
        tp26.setName(""); // NOI18N
        tp26.setNextFocusableComponent(tp27);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        jPanel71.add(tp26, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        panelPesados.add(jPanel71, gridBagConstraints);

        jPanel72.setLayout(new java.awt.GridBagLayout());

        tp34.setColumns(5);
        tp34.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tp34.setToolTipText("");
        tp34.setInputVerifier(intVerifier);
        tp34.setMinimumSize(new java.awt.Dimension(50, 19));
        tp34.setName(""); // NOI18N
        tp34.setNextFocusableComponent(tp35);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        jPanel72.add(tp34, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        panelPesados.add(jPanel72, gridBagConstraints);

        jPanel73.setLayout(new java.awt.GridBagLayout());

        tp42.setColumns(5);
        tp42.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tp42.setToolTipText("");
        tp42.setInputVerifier(intVerifier);
        tp42.setMinimumSize(new java.awt.Dimension(50, 19));
        tp42.setName(""); // NOI18N
        tp42.setNextFocusableComponent(tp43);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        jPanel73.add(tp42, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        panelPesados.add(jPanel73, gridBagConstraints);

        jPanel74.setLayout(new java.awt.GridBagLayout());

        tp50.setColumns(5);
        tp50.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tp50.setToolTipText("");
        tp50.setInputVerifier(intVerifier);
        tp50.setMinimumSize(new java.awt.Dimension(50, 19));
        tp50.setName(""); // NOI18N
        tp50.setNextFocusableComponent(tp51);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        jPanel74.add(tp50, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        panelPesados.add(jPanel74, gridBagConstraints);

        jPanel75.setLayout(new java.awt.GridBagLayout());

        tp58.setColumns(5);
        tp58.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tp58.setToolTipText("");
        tp58.setInputVerifier(intVerifier);
        tp58.setMinimumSize(new java.awt.Dimension(50, 19));
        tp58.setName(""); // NOI18N
        tp58.setNextFocusableComponent(tp59);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        jPanel75.add(tp58, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        panelPesados.add(jPanel75, gridBagConstraints);

        jPanel76.setLayout(new java.awt.GridBagLayout());

        tp66.setColumns(5);
        tp66.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tp66.setToolTipText("");
        tp66.setInputVerifier(intVerifier);
        tp66.setMinimumSize(new java.awt.Dimension(50, 19));
        tp66.setName(""); // NOI18N
        tp66.setNextFocusableComponent(tp67);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        jPanel76.add(tp66, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        panelPesados.add(jPanel76, gridBagConstraints);

        jPanel77.setLayout(new java.awt.GridBagLayout());

        tp74.setColumns(5);
        tp74.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tp74.setToolTipText("");
        tp74.setInputVerifier(intVerifier);
        tp74.setMinimumSize(new java.awt.Dimension(50, 19));
        tp74.setName(""); // NOI18N
        tp74.setNextFocusableComponent(tp75);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        jPanel77.add(tp74, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 11;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        panelPesados.add(jPanel77, gridBagConstraints);

        jPanel78.setLayout(new java.awt.GridBagLayout());

        tp82.setColumns(5);
        tp82.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tp82.setToolTipText("");
        tp82.setInputVerifier(intVerifier);
        tp82.setMinimumSize(new java.awt.Dimension(50, 19));
        tp82.setName(""); // NOI18N
        tp82.setNextFocusableComponent(tp83);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        jPanel78.add(tp82, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        panelPesados.add(jPanel78, gridBagConstraints);

        jPanel79.setLayout(new java.awt.GridBagLayout());

        tp90.setColumns(5);
        tp90.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tp90.setToolTipText("");
        tp90.setInputVerifier(intVerifier);
        tp90.setMinimumSize(new java.awt.Dimension(50, 19));
        tp90.setName(""); // NOI18N
        tp90.setNextFocusableComponent(tp91);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        jPanel79.add(tp90, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 13;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        panelPesados.add(jPanel79, gridBagConstraints);

        jPanel80.setLayout(new java.awt.GridBagLayout());

        tp98.setColumns(5);
        tp98.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tp98.setToolTipText("");
        tp98.setInputVerifier(intVerifier);
        tp98.setMinimumSize(new java.awt.Dimension(50, 19));
        tp98.setName(""); // NOI18N
        tp98.setNextFocusableComponent(tp99);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        jPanel80.add(tp98, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 14;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        panelPesados.add(jPanel80, gridBagConstraints);

        jPanel81.setLayout(new java.awt.GridBagLayout());

        tp106.setColumns(5);
        tp106.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tp106.setToolTipText("");
        tp106.setInputVerifier(intVerifier);
        tp106.setMinimumSize(new java.awt.Dimension(50, 19));
        tp106.setName(""); // NOI18N
        tp106.setNextFocusableComponent(tp107);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        jPanel81.add(tp106, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 15;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        panelPesados.add(jPanel81, gridBagConstraints);

        jPanel82.setLayout(new java.awt.GridBagLayout());

        tp114.setColumns(5);
        tp114.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tp114.setToolTipText("");
        tp114.setInputVerifier(intVerifier);
        tp114.setMinimumSize(new java.awt.Dimension(50, 19));
        tp114.setName(""); // NOI18N
        tp114.setNextFocusableComponent(tp115);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        jPanel82.add(tp114, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 16;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        panelPesados.add(jPanel82, gridBagConstraints);

        jPanel83.setLayout(new java.awt.GridBagLayout());

        tp122.setColumns(5);
        tp122.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tp122.setToolTipText("");
        tp122.setInputVerifier(intVerifier);
        tp122.setMinimumSize(new java.awt.Dimension(50, 19));
        tp122.setName(""); // NOI18N
        tp122.setNextFocusableComponent(tp123);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        jPanel83.add(tp122, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 17;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        panelPesados.add(jPanel83, gridBagConstraints);

        jPanel84.setLayout(new java.awt.GridBagLayout());

        tp130.setColumns(5);
        tp130.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tp130.setToolTipText("");
        tp130.setInputVerifier(intVerifier);
        tp130.setMinimumSize(new java.awt.Dimension(50, 19));
        tp130.setName(""); // NOI18N
        tp130.setNextFocusableComponent(tp131);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        jPanel84.add(tp130, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 18;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        panelPesados.add(jPanel84, gridBagConstraints);

        jPanel85.setLayout(new java.awt.GridBagLayout());

        tp4.setColumns(5);
        tp4.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tp4.setToolTipText("");
        tp4.setInputVerifier(intVerifier);
        tp4.setMinimumSize(new java.awt.Dimension(50, 19));
        tp4.setName(""); // NOI18N
        tp4.setNextFocusableComponent(tp5);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        jPanel85.add(tp4, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        panelPesados.add(jPanel85, gridBagConstraints);

        jPanel86.setLayout(new java.awt.GridBagLayout());

        tp12.setColumns(5);
        tp12.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tp12.setToolTipText("");
        tp12.setInputVerifier(intVerifier);
        tp12.setMinimumSize(new java.awt.Dimension(50, 19));
        tp12.setName(""); // NOI18N
        tp12.setNextFocusableComponent(tp13);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        jPanel86.add(tp12, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        panelPesados.add(jPanel86, gridBagConstraints);

        jPanel87.setLayout(new java.awt.GridBagLayout());

        tp20.setColumns(5);
        tp20.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tp20.setToolTipText("");
        tp20.setInputVerifier(intVerifier);
        tp20.setMinimumSize(new java.awt.Dimension(50, 19));
        tp20.setName(""); // NOI18N
        tp20.setNextFocusableComponent(tp21);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        jPanel87.add(tp20, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        panelPesados.add(jPanel87, gridBagConstraints);

        jPanel88.setLayout(new java.awt.GridBagLayout());

        tp28.setColumns(5);
        tp28.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tp28.setToolTipText("");
        tp28.setInputVerifier(intVerifier);
        tp28.setMinimumSize(new java.awt.Dimension(50, 19));
        tp28.setName(""); // NOI18N
        tp28.setNextFocusableComponent(tp29);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        jPanel88.add(tp28, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        panelPesados.add(jPanel88, gridBagConstraints);

        jPanel89.setLayout(new java.awt.GridBagLayout());

        tp36.setColumns(5);
        tp36.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tp36.setToolTipText("");
        tp36.setInputVerifier(intVerifier);
        tp36.setMinimumSize(new java.awt.Dimension(50, 19));
        tp36.setName(""); // NOI18N
        tp36.setNextFocusableComponent(tp37);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        jPanel89.add(tp36, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        panelPesados.add(jPanel89, gridBagConstraints);

        jPanel90.setLayout(new java.awt.GridBagLayout());

        tp44.setColumns(5);
        tp44.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tp44.setToolTipText("");
        tp44.setInputVerifier(intVerifier);
        tp44.setMinimumSize(new java.awt.Dimension(50, 19));
        tp44.setName(""); // NOI18N
        tp44.setNextFocusableComponent(tp45);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        jPanel90.add(tp44, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        panelPesados.add(jPanel90, gridBagConstraints);

        jPanel91.setLayout(new java.awt.GridBagLayout());

        tp52.setColumns(5);
        tp52.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tp52.setToolTipText("");
        tp52.setInputVerifier(intVerifier);
        tp52.setMinimumSize(new java.awt.Dimension(50, 19));
        tp52.setName(""); // NOI18N
        tp52.setNextFocusableComponent(tp53);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        jPanel91.add(tp52, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        panelPesados.add(jPanel91, gridBagConstraints);

        jPanel92.setLayout(new java.awt.GridBagLayout());

        tp60.setColumns(5);
        tp60.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tp60.setToolTipText("");
        tp60.setInputVerifier(intVerifier);
        tp60.setMinimumSize(new java.awt.Dimension(50, 19));
        tp60.setName(""); // NOI18N
        tp60.setNextFocusableComponent(tp61);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        jPanel92.add(tp60, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        panelPesados.add(jPanel92, gridBagConstraints);

        jPanel93.setLayout(new java.awt.GridBagLayout());

        tp68.setColumns(5);
        tp68.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tp68.setToolTipText("");
        tp68.setInputVerifier(intVerifier);
        tp68.setMinimumSize(new java.awt.Dimension(50, 19));
        tp68.setName(""); // NOI18N
        tp68.setNextFocusableComponent(tp69);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        jPanel93.add(tp68, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        panelPesados.add(jPanel93, gridBagConstraints);

        jPanel94.setLayout(new java.awt.GridBagLayout());

        tp76.setColumns(5);
        tp76.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tp76.setToolTipText("");
        tp76.setInputVerifier(intVerifier);
        tp76.setMinimumSize(new java.awt.Dimension(50, 19));
        tp76.setName(""); // NOI18N
        tp76.setNextFocusableComponent(tp77);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        jPanel94.add(tp76, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 11;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        panelPesados.add(jPanel94, gridBagConstraints);

        jPanel95.setLayout(new java.awt.GridBagLayout());

        tp84.setColumns(5);
        tp84.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tp84.setToolTipText("");
        tp84.setInputVerifier(intVerifier);
        tp84.setMinimumSize(new java.awt.Dimension(50, 19));
        tp84.setName(""); // NOI18N
        tp84.setNextFocusableComponent(tp85);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        jPanel95.add(tp84, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        panelPesados.add(jPanel95, gridBagConstraints);

        jPanel96.setLayout(new java.awt.GridBagLayout());

        tp92.setColumns(5);
        tp92.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tp92.setToolTipText("");
        tp92.setInputVerifier(intVerifier);
        tp92.setMinimumSize(new java.awt.Dimension(50, 19));
        tp92.setName(""); // NOI18N
        tp92.setNextFocusableComponent(tp93);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        jPanel96.add(tp92, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 13;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        panelPesados.add(jPanel96, gridBagConstraints);

        jPanel97.setLayout(new java.awt.GridBagLayout());

        tp100.setColumns(5);
        tp100.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tp100.setToolTipText("");
        tp100.setInputVerifier(intVerifier);
        tp100.setMinimumSize(new java.awt.Dimension(50, 19));
        tp100.setName(""); // NOI18N
        tp100.setNextFocusableComponent(tp101);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        jPanel97.add(tp100, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 14;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        panelPesados.add(jPanel97, gridBagConstraints);

        jPanel98.setLayout(new java.awt.GridBagLayout());

        tp108.setColumns(5);
        tp108.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tp108.setToolTipText("");
        tp108.setInputVerifier(intVerifier);
        tp108.setMinimumSize(new java.awt.Dimension(50, 19));
        tp108.setName(""); // NOI18N
        tp108.setNextFocusableComponent(tp109);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        jPanel98.add(tp108, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 15;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        panelPesados.add(jPanel98, gridBagConstraints);

        jPanel99.setLayout(new java.awt.GridBagLayout());

        tp116.setColumns(5);
        tp116.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tp116.setToolTipText("");
        tp116.setInputVerifier(intVerifier);
        tp116.setMinimumSize(new java.awt.Dimension(50, 19));
        tp116.setName(""); // NOI18N
        tp116.setNextFocusableComponent(tp117);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        jPanel99.add(tp116, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 16;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        panelPesados.add(jPanel99, gridBagConstraints);

        jPanel100.setLayout(new java.awt.GridBagLayout());

        tp124.setColumns(5);
        tp124.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tp124.setToolTipText("");
        tp124.setInputVerifier(intVerifier);
        tp124.setMinimumSize(new java.awt.Dimension(50, 19));
        tp124.setName(""); // NOI18N
        tp124.setNextFocusableComponent(tp125);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        jPanel100.add(tp124, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 17;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        panelPesados.add(jPanel100, gridBagConstraints);

        jPanel101.setLayout(new java.awt.GridBagLayout());

        tp132.setColumns(5);
        tp132.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tp132.setToolTipText("");
        tp132.setInputVerifier(intVerifier);
        tp132.setMinimumSize(new java.awt.Dimension(50, 19));
        tp132.setName(""); // NOI18N
        tp132.setNextFocusableComponent(tp133);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        jPanel101.add(tp132, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 18;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        panelPesados.add(jPanel101, gridBagConstraints);

        jPanel102.setLayout(new java.awt.GridBagLayout());

        tp6.setColumns(5);
        tp6.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tp6.setToolTipText("");
        tp6.setInputVerifier(intVerifier);
        tp6.setMinimumSize(new java.awt.Dimension(50, 19));
        tp6.setName(""); // NOI18N
        tp6.setNextFocusableComponent(tp7);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        jPanel102.add(tp6, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        panelPesados.add(jPanel102, gridBagConstraints);

        jPanel103.setLayout(new java.awt.GridBagLayout());

        tp14.setColumns(5);
        tp14.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tp14.setToolTipText("");
        tp14.setInputVerifier(intVerifier);
        tp14.setMinimumSize(new java.awt.Dimension(50, 19));
        tp14.setName(""); // NOI18N
        tp14.setNextFocusableComponent(tp15);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        jPanel103.add(tp14, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        panelPesados.add(jPanel103, gridBagConstraints);

        jPanel104.setLayout(new java.awt.GridBagLayout());

        tp22.setColumns(5);
        tp22.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tp22.setToolTipText("");
        tp22.setInputVerifier(intVerifier);
        tp22.setMinimumSize(new java.awt.Dimension(50, 19));
        tp22.setName(""); // NOI18N
        tp22.setNextFocusableComponent(tp23);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        jPanel104.add(tp22, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        panelPesados.add(jPanel104, gridBagConstraints);

        jPanel105.setLayout(new java.awt.GridBagLayout());

        tp30.setColumns(5);
        tp30.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tp30.setToolTipText("");
        tp30.setInputVerifier(intVerifier);
        tp30.setMinimumSize(new java.awt.Dimension(50, 19));
        tp30.setName(""); // NOI18N
        tp30.setNextFocusableComponent(tp31);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        jPanel105.add(tp30, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        panelPesados.add(jPanel105, gridBagConstraints);

        jPanel106.setLayout(new java.awt.GridBagLayout());

        tp38.setColumns(5);
        tp38.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tp38.setToolTipText("");
        tp38.setInputVerifier(intVerifier);
        tp38.setMinimumSize(new java.awt.Dimension(50, 19));
        tp38.setName(""); // NOI18N
        tp38.setNextFocusableComponent(tp39);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        jPanel106.add(tp38, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        panelPesados.add(jPanel106, gridBagConstraints);

        jPanel107.setLayout(new java.awt.GridBagLayout());

        tp46.setColumns(5);
        tp46.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tp46.setToolTipText("");
        tp46.setInputVerifier(intVerifier);
        tp46.setMinimumSize(new java.awt.Dimension(50, 19));
        tp46.setName(""); // NOI18N
        tp46.setNextFocusableComponent(tp47);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        jPanel107.add(tp46, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        panelPesados.add(jPanel107, gridBagConstraints);

        jPanel108.setLayout(new java.awt.GridBagLayout());

        tp54.setColumns(5);
        tp54.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tp54.setToolTipText("");
        tp54.setInputVerifier(intVerifier);
        tp54.setMinimumSize(new java.awt.Dimension(50, 19));
        tp54.setName(""); // NOI18N
        tp54.setNextFocusableComponent(tp55);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        jPanel108.add(tp54, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        panelPesados.add(jPanel108, gridBagConstraints);

        jPanel109.setLayout(new java.awt.GridBagLayout());

        tp62.setColumns(5);
        tp62.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tp62.setToolTipText("");
        tp62.setInputVerifier(intVerifier);
        tp62.setMinimumSize(new java.awt.Dimension(50, 19));
        tp62.setName(""); // NOI18N
        tp62.setNextFocusableComponent(tp63);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        jPanel109.add(tp62, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        panelPesados.add(jPanel109, gridBagConstraints);

        jPanel110.setLayout(new java.awt.GridBagLayout());

        tp70.setColumns(5);
        tp70.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tp70.setToolTipText("");
        tp70.setInputVerifier(intVerifier);
        tp70.setMinimumSize(new java.awt.Dimension(50, 19));
        tp70.setName(""); // NOI18N
        tp70.setNextFocusableComponent(tp71);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        jPanel110.add(tp70, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        panelPesados.add(jPanel110, gridBagConstraints);

        jPanel111.setLayout(new java.awt.GridBagLayout());

        tp78.setColumns(5);
        tp78.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tp78.setToolTipText("");
        tp78.setInputVerifier(intVerifier);
        tp78.setMinimumSize(new java.awt.Dimension(50, 19));
        tp78.setName(""); // NOI18N
        tp78.setNextFocusableComponent(tp79);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        jPanel111.add(tp78, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 11;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        panelPesados.add(jPanel111, gridBagConstraints);

        jPanel112.setLayout(new java.awt.GridBagLayout());

        tp86.setColumns(5);
        tp86.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tp86.setToolTipText("");
        tp86.setInputVerifier(intVerifier);
        tp86.setMinimumSize(new java.awt.Dimension(50, 19));
        tp86.setName(""); // NOI18N
        tp86.setNextFocusableComponent(tp87);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        jPanel112.add(tp86, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        panelPesados.add(jPanel112, gridBagConstraints);

        jPanel113.setLayout(new java.awt.GridBagLayout());

        tp94.setColumns(5);
        tp94.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tp94.setToolTipText("");
        tp94.setInputVerifier(intVerifier);
        tp94.setMinimumSize(new java.awt.Dimension(50, 19));
        tp94.setName(""); // NOI18N
        tp94.setNextFocusableComponent(tp95);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        jPanel113.add(tp94, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 13;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        panelPesados.add(jPanel113, gridBagConstraints);

        jPanel114.setLayout(new java.awt.GridBagLayout());

        tp102.setColumns(5);
        tp102.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tp102.setToolTipText("");
        tp102.setInputVerifier(intVerifier);
        tp102.setMinimumSize(new java.awt.Dimension(50, 19));
        tp102.setName(""); // NOI18N
        tp102.setNextFocusableComponent(tp103);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        jPanel114.add(tp102, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 14;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        panelPesados.add(jPanel114, gridBagConstraints);

        jPanel115.setLayout(new java.awt.GridBagLayout());

        tp110.setColumns(5);
        tp110.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tp110.setToolTipText("");
        tp110.setInputVerifier(intVerifier);
        tp110.setMinimumSize(new java.awt.Dimension(50, 19));
        tp110.setName(""); // NOI18N
        tp110.setNextFocusableComponent(tp111);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        jPanel115.add(tp110, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 15;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        panelPesados.add(jPanel115, gridBagConstraints);

        jPanel116.setLayout(new java.awt.GridBagLayout());

        tp118.setColumns(5);
        tp118.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tp118.setToolTipText("");
        tp118.setInputVerifier(intVerifier);
        tp118.setMinimumSize(new java.awt.Dimension(50, 19));
        tp118.setName(""); // NOI18N
        tp118.setNextFocusableComponent(tp119);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        jPanel116.add(tp118, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 16;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        panelPesados.add(jPanel116, gridBagConstraints);

        jPanel117.setLayout(new java.awt.GridBagLayout());

        tp126.setColumns(5);
        tp126.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tp126.setToolTipText("");
        tp126.setInputVerifier(intVerifier);
        tp126.setMinimumSize(new java.awt.Dimension(50, 19));
        tp126.setName(""); // NOI18N
        tp126.setNextFocusableComponent(tp127);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        jPanel117.add(tp126, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 17;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        panelPesados.add(jPanel117, gridBagConstraints);

        jPanel118.setLayout(new java.awt.GridBagLayout());

        tp134.setColumns(5);
        tp134.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tp134.setToolTipText("");
        tp134.setInputVerifier(intVerifier);
        tp134.setMinimumSize(new java.awt.Dimension(50, 19));
        tp134.setName(""); // NOI18N
        tp134.setNextFocusableComponent(tp135);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        jPanel118.add(tp134, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 18;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        panelPesados.add(jPanel118, gridBagConstraints);

        jScrollPane4.setViewportView(panelPesados);

        lblPesquisador1.setText("Pesquisador_2:");

        txtPesquisador2.setNextFocusableComponent(tp0);

        javax.swing.GroupLayout tab_pesadosLayout = new javax.swing.GroupLayout(tab_pesados);
        tab_pesados.setLayout(tab_pesadosLayout);
        tab_pesadosLayout.setHorizontalGroup(
            tab_pesadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab_pesadosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblPesquisador1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtPesquisador2, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 1295, Short.MAX_VALUE)
        );
        tab_pesadosLayout.setVerticalGroup(
            tab_pesadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tab_pesadosLayout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addGroup(tab_pesadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPesquisador2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPesquisador1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 584, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Caminhões Pesados", tab_pesados);

        btnSalvarForms.setText("Gravar Formulários");
        btnSalvarForms.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarFormsActionPerformed(evt);
            }
        });

        jLabel34.setText("Trecho:");

        lblTrecho_dados.setText("------------------------------------------------------------------------------------------");

        javax.swing.GroupLayout pnl_volumetricaLayout = new javax.swing.GroupLayout(pnl_volumetrica);
        pnl_volumetrica.setLayout(pnl_volumetricaLayout);
        pnl_volumetricaLayout.setHorizontalGroup(
            pnl_volumetricaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_volumetricaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnl_volumetricaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblPosto)
                    .addComponent(lblSentido))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnl_volumetricaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnl_volumetricaLayout.createSequentialGroup()
                        .addComponent(rdo_SentidoAB)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(rdo_SentidoBA))
                    .addComponent(lblPosto_dados))
                .addGap(21, 21, 21)
                .addGroup(pnl_volumetricaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblPista)
                    .addComponent(jLabel34))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnl_volumetricaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(pnl_volumetricaLayout.createSequentialGroup()
                        .addComponent(rdo_PistaSimples)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rdo_PistaDupla)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblHora)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cmbHora, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblTrecho_dados))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnl_volumetricaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblData, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblLocal, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnl_volumetricaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(data, javax.swing.GroupLayout.DEFAULT_SIZE, 234, Short.MAX_VALUE)
                    .addComponent(txtLocal))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnSalvarForms, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        pnl_volumetricaLayout.setVerticalGroup(
            pnl_volumetricaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_volumetricaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnl_volumetricaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblLocal, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtLocal, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPosto_dados)
                    .addComponent(jLabel34)
                    .addComponent(lblTrecho_dados)
                    .addComponent(lblPosto))
                .addGap(10, 10, 10)
                .addGroup(pnl_volumetricaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdo_SentidoBA)
                    .addComponent(rdo_SentidoAB)
                    .addComponent(lblPista)
                    .addComponent(rdo_PistaSimples)
                    .addComponent(rdo_PistaDupla)
                    .addComponent(lblData)
                    .addComponent(data, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSalvarForms)
                    .addComponent(lblSentido)
                    .addComponent(lblHora)
                    .addComponent(cmbHora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1))
        );

        jTabbedPane1.getAccessibleContext().setAccessibleName("tab_leves");
        jTabbedPane1.getAccessibleContext().setAccessibleDescription("");

        jTabbedPane2.addTab("Formulário de pesquisa volumétrica", pnl_volumetrica);

        jLabel1.setText("Porta");

        txtAreaLog.setEditable(false);
        txtAreaLog.setColumns(20);
        txtAreaLog.setLineWrap(true);
        txtAreaLog.setRows(5);
        txtAreaLog.setWrapStyleWord(true);
        jScrollPane1.setViewportView(txtAreaLog);
        txtAreaLog.setEditable(false);

        txtPorta.setEditable(false);

        jLabel2.setText("Registro das operações do servidor");

        javax.swing.GroupLayout pnl_servidorLayout = new javax.swing.GroupLayout(pnl_servidor);
        pnl_servidor.setLayout(pnl_servidorLayout);
        pnl_servidorLayout.setHorizontalGroup(
            pnl_servidorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(pnl_servidorLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnl_servidorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(pnl_servidorLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(4, 4, 4)
                        .addComponent(txtPorta))
                    .addComponent(jLabel2))
                .addContainerGap(1035, Short.MAX_VALUE))
        );
        pnl_servidorLayout.setVerticalGroup(
            pnl_servidorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_servidorLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnl_servidorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPorta, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 654, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Servidor Web", pnl_servidor);

        jLabel3.setText("Captura de Dados");

        btnInDados.setText("Importar dados do Coletor");
        btnInDados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInDadosActionPerformed(evt);
            }
        });

        btnExportAllVol.setText("Enviar todos os dados");
        btnExportAllVol.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExportAllVolActionPerformed(evt);
            }
        });

        btnVolNotSent.setText("Enviar dados ainda não enviados");
        btnVolNotSent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVolNotSentActionPerformed(evt);
            }
        });

        jLabel4.setText("Envio de Dados Pesquisa Volumétrica");

        jLabel35.setText("Envio de Dados Pesquisa OD");

        btnODNotSent.setText("Enviar dados ainda não enviados");
        btnODNotSent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnODNotSentActionPerformed(evt);
            }
        });

        btnODexportAll.setText("Enviar todos os dados");
        btnODexportAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnODexportAllActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel35, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnODexportAll)
                            .addComponent(btnODNotSent)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(btnVolNotSent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnExportAllVol)
                                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(0, 89, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addComponent(btnVolNotSent)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnExportAllVol)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 93, Short.MAX_VALUE)
                .addComponent(jLabel35)
                .addGap(18, 18, 18)
                .addComponent(btnODNotSent)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnODexportAll)
                .addGap(315, 315, 315))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(btnInDados))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 238, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(116, 116, 116))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(btnInDados)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 95, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnl_envioLayout = new javax.swing.GroupLayout(pnl_envio);
        pnl_envio.setLayout(pnl_envioLayout);
        pnl_envioLayout.setHorizontalGroup(
            pnl_envioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_envioLayout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(315, 315, 315))
        );
        pnl_envioLayout.setVerticalGroup(
            pnl_envioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTabbedPane2.addTab("captura e envio de dados", pnl_envio);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane2)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane2)
        );

        jTabbedPane2.getAccessibleContext().setAccessibleName("tabna1");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnInDadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInDadosActionPerformed
        int returnVal = dadosFileChooser.showOpenDialog(this);
		if (returnVal == dadosFileChooser.APPROVE_OPTION) {
			File file = dadosFileChooser.getSelectedFile();
			try {
			  ImportedDB db = new ImportedDB(file.getAbsolutePath());
			  db.importData();
			} catch (IOException ex) {
			  System.out.println("Problema accessando arquivo "+file.getAbsolutePath());
			} catch (Exception e){
				System.out.println("Problema importando dados: " + e.getMessage());
			}
		} else {
			System.out.println("File access cancelled by user.");
		}
    }//GEN-LAST:event_btnInDadosActionPerformed

    private void cmbHoraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbHoraActionPerformed
        int hora_inicial;
		if (cmbHora.getSelectedIndex() != -1){
			try{
				hora_inicial = Integer.parseInt((String) cmbHora.getSelectedItem());
			} catch (Exception e){
				JOptionPane.showMessageDialog(null, "Erro configurando a hora inicial da pesquisa: \n" + e.getMessage());
				return;
			}

			String hora1 = String.valueOf(hora_inicial) + ":00 a " + String.valueOf(hora_inicial+1) + ":00";
			String hora2 = String.valueOf(hora_inicial+1) + ":00 a " + String.valueOf(hora_inicial+2) + ":00";

			pesados_hora1.setText(hora1);
			leves_hora1.setText(hora1);
			pesados_hora2.setText(hora2);
			leves_hora2.setText(hora2);
		}
    }//GEN-LAST:event_cmbHoraActionPerformed

    private void btnSalvarFormsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarFormsActionPerformed
        String posto = Concentrador.posto;
		String pista;
		String date;
		String sentido;
		int hora_inicial;
		String pesquisador1;
		String pesquisador2;
		String local;
		myDB database = null;
		
		try{
			database = new myDB();
		}catch(Exception e){
			JOptionPane.showMessageDialog(Janela.this, "Erro ao conectar com o banco de dados:\n" + e.getMessage(), 
					"Erro de conexão com o banco de dados.", JOptionPane.ERROR_MESSAGE);
		}
		//validate pista
		if(!rdo_PistaDupla.isSelected() && !rdo_PistaSimples.isSelected()){
			JOptionPane.showMessageDialog(Janela.this, "O tipo de pista precisa ser selecionado.", 
					"Tipo de pista não selecionado.", JOptionPane.ERROR_MESSAGE);
			return;
		}else if(rdo_PistaDupla.isSelected()){
			pista = "D";
		} else {
			pista = "S";
		}
		
		//valida e formata data
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if (data.getDate() == null){
			JOptionPane.showMessageDialog(Janela.this, "A data do formulário precisa ser preenchida.", 
					"Data não preenchida.", JOptionPane.ERROR_MESSAGE);
			return;
		} else {
			date = sdf.format(data.getDate());
		}
		
		//valida sentido
		if(!rdo_SentidoAB.isSelected() && !rdo_SentidoBA.isSelected()){
			JOptionPane.showMessageDialog(Janela.this, "O sentido precisa ser selecionado.", "Sentido da pista.", JOptionPane.ERROR_MESSAGE);
			return;
		} else if (rdo_SentidoAB.isSelected()){
			sentido = "AB";
		} else {
			sentido = "BA";
		}
		
		//valida hora inicial
		if(cmbHora.getSelectedItem() == null){
			JOptionPane.showMessageDialog(Janela.this, "Selecione a hora inicial.", "Hora inicial.", JOptionPane.ERROR_MESSAGE);
			return;
		} else {
			hora_inicial = Integer.parseInt(cmbHora.getSelectedItem().toString());
		}
		
		
		//valida pesquisador
		if(txtPesquisador1.getText().trim().isEmpty()){
			JOptionPane.showMessageDialog(Janela.this, "A identificação do pesquisador_1 não foi preenchida.", "Pesquisador.", JOptionPane.ERROR_MESSAGE);
			return;
		} else {
			pesquisador1 = txtPesquisador1.getText();
		}
		
		//valida pesquisador
		if(txtPesquisador2.getText().trim().isEmpty()){
			JOptionPane.showMessageDialog(Janela.this, "A identificação do pesquisador_2 não foi preenchida.", "Pesquisador.", JOptionPane.ERROR_MESSAGE);
			return;
		} else {
			pesquisador2 = txtPesquisador2.getText();
		}
		//valida local
		if(txtLocal.getText().trim().isEmpty()){
			JOptionPane.showMessageDialog(Janela.this, "O local da pesquisa precisa ser preenchido.", "Preenchimento do local da pesquisa.", JOptionPane.ERROR_MESSAGE);
			return;
		} else {
			local = txtLocal.getText();
		}
		
		//montando o registro para gravação
		PVregister reg = new PVregister();
		reg.data = date;
		reg.sentido = sentido;
		reg.pista = pista;
		reg.posto = Integer.parseInt(posto);
		reg.local = local;
		reg.sentido = sentido;
		reg.pesquisador1 = pesquisador1;
		reg.pesquisador2 = pesquisador2;
		reg.hora = hora_inicial;
		
		//reg fields for tipes
		for (String s : volFieldsNames){
			try{
				PVregister.class.getField(s.toLowerCase()).set(reg, buildValues(s));
			} catch(Exception e){
				JOptionPane.showMessageDialog(Janela.this, "Erro gravando dados:\n"+e.getMessage(), "Erro na gravação dos dados.", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
		}
		
		
		try{
			int alreadyInDataBase = database.verifyPV(reg);
			if (alreadyInDataBase != 0){
				int returnedValue = JOptionPane.showConfirmDialog(Janela.this, 
						"Já existe um registro no Banco de Dados com o mesmo posto, sentido, data e hora.\nVocê deseja sobrescrever os dados já gravados?",
						"Dados já existentes.", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if(returnedValue == JOptionPane.YES_OPTION){
					database.updatePV(reg, alreadyInDataBase);
					clearForm();
				}
				return;//exit and don't save
			}

			database.inputPV(reg);
			clearForm();
			JOptionPane.showMessageDialog(Janela.this, "Registro gravado com sucesso!", 
				"Registro gravado.", JOptionPane.INFORMATION_MESSAGE);

			
			
		}catch(Exception e){
			JOptionPane.showMessageDialog(Janela.this, "Erro ao conectar com o banco de dados:\n" + e.getMessage(), 
					"Erro de conexão com o banco de dados.", JOptionPane.ERROR_MESSAGE);
		}
		
		return;
    }//GEN-LAST:event_btnSalvarFormsActionPerformed

    private void btnExportAllVolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportAllVolActionPerformed
        exporterFileChooser.setSelectedFile(new File("todos_volumetrica.json"));
		int returnVal = exporterFileChooser.showSaveDialog(this);
		
		if(returnVal == exporterFileChooser.APPROVE_OPTION){
			JSONExporter exporter = new JSONExporter(exporterFileChooser.getSelectedFile(), JSONExporter.DbTable.PV);
			exporter.export(JSONExporter.WhatToExport.ALL);
		}
		
    }//GEN-LAST:event_btnExportAllVolActionPerformed

    private void btnODexportAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnODexportAllActionPerformed
        exporterFileChooser.setSelectedFile(new File("todos_od.json"));
		int returnVal = exporterFileChooser.showSaveDialog(this);
		
		if(returnVal == exporterFileChooser.APPROVE_OPTION){
			JSONExporter exporter = new JSONExporter(exporterFileChooser.getSelectedFile(), JSONExporter.DbTable.OD);
			exporter.export(JSONExporter.WhatToExport.ALL);
		}
    }//GEN-LAST:event_btnODexportAllActionPerformed

    private void btnVolNotSentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVolNotSentActionPerformed
        exporterFileChooser.setSelectedFile(new File("novos_volumetrica.json"));
		int returnVal = exporterFileChooser.showSaveDialog(this);
		
		if(returnVal == exporterFileChooser.APPROVE_OPTION){
			JSONExporter exporter = new JSONExporter(exporterFileChooser.getSelectedFile(), JSONExporter.DbTable.PV);
			exporter.export(JSONExporter.WhatToExport.NOT_SENT);
		}
    }//GEN-LAST:event_btnVolNotSentActionPerformed

    private void btnODNotSentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnODNotSentActionPerformed
		exporterFileChooser.setSelectedFile(new File("novos_od.json"));
		int returnVal = exporterFileChooser.showSaveDialog(this);
		
		if(returnVal == exporterFileChooser.APPROVE_OPTION){
			JSONExporter exporter = new JSONExporter(exporterFileChooser.getSelectedFile(), JSONExporter.DbTable.OD);
			exporter.export(JSONExporter.WhatToExport.NOT_SENT);
		}
    }//GEN-LAST:event_btnODNotSentActionPerformed
	
	private void clearForm(){
		data.setDate(null);
		txtPesquisador1.setText(null);
		txtPesquisador2.setText(null);
		txtLocal.setText(null);
		pesados_hora1.setText(null);
		pesados_hora2.setText(null);
		leves_hora1.setText(null);
		leves_hora2.setText(null);
		grpPista.clearSelection();
		grpSentido.clearSelection();
		cmbHora.setSelectedIndex(-1);
	}
	
	private String buildValues(String field){
		String r = "{\"Veículo\": \"" + field + "\", \"Valores\": \"";
		JTextField[] inputFields = fieldsMap.get(field);
		int h1 = 0;
		
		int h2 = 0;
		
		for (int i = 0; i < inputFields.length; i++){
			r += inputFields[i].getText();
			if (i+1 != inputFields.length){
				r+=",";
			}
			if (i <= 3){
				h1 += Integer.parseInt(inputFields[i].getText());
			}else{
				h2 += Integer.parseInt(inputFields[i].getText());
			}
		}
		
		r += "\", \"Hora1\": " + String.valueOf(h1) + ", \"Hora2\": " + String.valueOf(h2) + "}";
		
		return r;
	}
	
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Janela.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Janela.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Janela.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Janela.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Janela().setVisible(true);
            }
        });
    }
    
    private DefaultTableCellRenderer numeros;
    private ImagemRenderer imagens;
    
    private InputVerifier intVerifier;
	
	

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnExportAllVol;
    private javax.swing.JButton btnInDados;
    private javax.swing.JButton btnODNotSent;
    private javax.swing.JButton btnODexportAll;
    private javax.swing.JButton btnSalvarForms;
    private javax.swing.JButton btnVolNotSent;
    private javax.swing.JComboBox<String> cmbHora;
    private javax.swing.JFileChooser dadosFileChooser;
    private org.jdesktop.swingx.JXDatePicker data;
    private javax.swing.JFileChooser exporterFileChooser;
    private javax.swing.ButtonGroup grpPista;
    private javax.swing.ButtonGroup grpSentido;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel100;
    private javax.swing.JPanel jPanel101;
    private javax.swing.JPanel jPanel102;
    private javax.swing.JPanel jPanel103;
    private javax.swing.JPanel jPanel104;
    private javax.swing.JPanel jPanel105;
    private javax.swing.JPanel jPanel106;
    private javax.swing.JPanel jPanel107;
    private javax.swing.JPanel jPanel108;
    private javax.swing.JPanel jPanel109;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel110;
    private javax.swing.JPanel jPanel111;
    private javax.swing.JPanel jPanel112;
    private javax.swing.JPanel jPanel113;
    private javax.swing.JPanel jPanel114;
    private javax.swing.JPanel jPanel115;
    private javax.swing.JPanel jPanel116;
    private javax.swing.JPanel jPanel117;
    private javax.swing.JPanel jPanel118;
    private javax.swing.JPanel jPanel119;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel30;
    private javax.swing.JPanel jPanel31;
    private javax.swing.JPanel jPanel32;
    private javax.swing.JPanel jPanel33;
    private javax.swing.JPanel jPanel34;
    private javax.swing.JPanel jPanel35;
    private javax.swing.JPanel jPanel36;
    private javax.swing.JPanel jPanel37;
    private javax.swing.JPanel jPanel38;
    private javax.swing.JPanel jPanel39;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel40;
    private javax.swing.JPanel jPanel41;
    private javax.swing.JPanel jPanel42;
    private javax.swing.JPanel jPanel43;
    private javax.swing.JPanel jPanel44;
    private javax.swing.JPanel jPanel45;
    private javax.swing.JPanel jPanel46;
    private javax.swing.JPanel jPanel47;
    private javax.swing.JPanel jPanel48;
    private javax.swing.JPanel jPanel49;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel50;
    private javax.swing.JPanel jPanel51;
    private javax.swing.JPanel jPanel52;
    private javax.swing.JPanel jPanel53;
    private javax.swing.JPanel jPanel54;
    private javax.swing.JPanel jPanel55;
    private javax.swing.JPanel jPanel56;
    private javax.swing.JPanel jPanel57;
    private javax.swing.JPanel jPanel58;
    private javax.swing.JPanel jPanel59;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel60;
    private javax.swing.JPanel jPanel61;
    private javax.swing.JPanel jPanel62;
    private javax.swing.JPanel jPanel63;
    private javax.swing.JPanel jPanel64;
    private javax.swing.JPanel jPanel65;
    private javax.swing.JPanel jPanel66;
    private javax.swing.JPanel jPanel67;
    private javax.swing.JPanel jPanel68;
    private javax.swing.JPanel jPanel69;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel70;
    private javax.swing.JPanel jPanel71;
    private javax.swing.JPanel jPanel72;
    private javax.swing.JPanel jPanel73;
    private javax.swing.JPanel jPanel74;
    private javax.swing.JPanel jPanel75;
    private javax.swing.JPanel jPanel76;
    private javax.swing.JPanel jPanel77;
    private javax.swing.JPanel jPanel78;
    private javax.swing.JPanel jPanel79;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel80;
    private javax.swing.JPanel jPanel81;
    private javax.swing.JPanel jPanel82;
    private javax.swing.JPanel jPanel83;
    private javax.swing.JPanel jPanel84;
    private javax.swing.JPanel jPanel85;
    private javax.swing.JPanel jPanel86;
    private javax.swing.JPanel jPanel87;
    private javax.swing.JPanel jPanel88;
    private javax.swing.JPanel jPanel89;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPanel jPanel90;
    private javax.swing.JPanel jPanel91;
    private javax.swing.JPanel jPanel92;
    private javax.swing.JPanel jPanel93;
    private javax.swing.JPanel jPanel94;
    private javax.swing.JPanel jPanel95;
    private javax.swing.JPanel jPanel96;
    private javax.swing.JPanel jPanel97;
    private javax.swing.JPanel jPanel98;
    private javax.swing.JPanel jPanel99;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JLabel lbl2C;
    private javax.swing.JLabel lbl2C2;
    private javax.swing.JLabel lbl2C3;
    private javax.swing.JLabel lbl2CB;
    private javax.swing.JLabel lbl2S1;
    private javax.swing.JLabel lbl2S2;
    private javax.swing.JLabel lbl2S3;
    private javax.swing.JLabel lbl3C;
    private javax.swing.JLabel lbl3C2;
    private javax.swing.JLabel lbl3C3;
    private javax.swing.JLabel lbl3CB;
    private javax.swing.JLabel lbl3D;
    private javax.swing.JLabel lbl3D4;
    private javax.swing.JLabel lbl3M6;
    private javax.swing.JLabel lbl3Q4;
    private javax.swing.JLabel lbl3S1;
    private javax.swing.JLabel lbl3S2;
    private javax.swing.JLabel lbl3S3;
    private javax.swing.JLabel lbl3T4;
    private javax.swing.JLabel lbl3T6;
    private javax.swing.JLabel lbl3T6B;
    private javax.swing.JLabel lbl3V5;
    private javax.swing.JLabel lbl4C;
    private javax.swing.JLabel lbl4CB;
    private javax.swing.JLabel lbl4CD;
    private javax.swing.JLabel lblData;
    private javax.swing.JLabel lblHora;
    private javax.swing.JLabel lblLocal;
    private javax.swing.JLabel lblM;
    private javax.swing.JLabel lblP1;
    private javax.swing.JLabel lblP2;
    private javax.swing.JLabel lblP3;
    private javax.swing.JLabel lblPesquisador;
    private javax.swing.JLabel lblPesquisador1;
    private javax.swing.JLabel lblPista;
    private javax.swing.JLabel lblPosto;
    public javax.swing.JLabel lblPosto_dados;
    private javax.swing.JLabel lblSentido;
    public javax.swing.JLabel lblTrecho_dados;
    private javax.swing.JTextField lev15_1;
    private javax.swing.JTextField lev15_2;
    private javax.swing.JTextField lev30_1;
    private javax.swing.JTextField lev30_2;
    private javax.swing.JTextField lev45_1;
    private javax.swing.JTextField lev45_2;
    private javax.swing.JTextField lev60_1;
    private javax.swing.JTextField lev60_2;
    private javax.swing.JTextField leves_hora1;
    private javax.swing.JTextField leves_hora2;
    private javax.swing.JPanel panelLeves;
    private javax.swing.JPanel panelPesados;
    private javax.swing.JTextField pes15_1;
    private javax.swing.JTextField pes15_2;
    private javax.swing.JTextField pes30_1;
    private javax.swing.JTextField pes30_2;
    private javax.swing.JTextField pes45_1;
    private javax.swing.JTextField pes60_1;
    private javax.swing.JTextField pesados_hora1;
    private javax.swing.JTextField pesados_hora2;
    private javax.swing.JTextField peso45_2;
    private javax.swing.JTextField peso60_2;
    private javax.swing.JPanel pnl_envio;
    private javax.swing.JPanel pnl_servidor;
    private javax.swing.JPanel pnl_volumetrica;
    private javax.swing.JRadioButton rdo_PistaDupla;
    private javax.swing.JRadioButton rdo_PistaSimples;
    private javax.swing.JRadioButton rdo_SentidoAB;
    private javax.swing.JRadioButton rdo_SentidoBA;
    private javax.swing.JPanel tab_leves;
    private javax.swing.JPanel tab_pesados;
    private javax.swing.JTextField tl0;
    private javax.swing.JTextField tl1;
    private javax.swing.JTextField tl10;
    private javax.swing.JTextField tl11;
    private javax.swing.JTextField tl12;
    private javax.swing.JTextField tl13;
    private javax.swing.JTextField tl14;
    private javax.swing.JTextField tl15;
    private javax.swing.JTextField tl16;
    private javax.swing.JTextField tl17;
    private javax.swing.JTextField tl18;
    private javax.swing.JTextField tl19;
    private javax.swing.JTextField tl2;
    private javax.swing.JTextField tl20;
    private javax.swing.JTextField tl21;
    private javax.swing.JTextField tl22;
    private javax.swing.JTextField tl23;
    private javax.swing.JTextField tl24;
    private javax.swing.JTextField tl25;
    private javax.swing.JTextField tl26;
    private javax.swing.JTextField tl27;
    private javax.swing.JTextField tl28;
    private javax.swing.JTextField tl29;
    private javax.swing.JTextField tl3;
    private javax.swing.JTextField tl30;
    private javax.swing.JTextField tl31;
    private javax.swing.JTextField tl32;
    private javax.swing.JTextField tl33;
    private javax.swing.JTextField tl34;
    private javax.swing.JTextField tl35;
    private javax.swing.JTextField tl36;
    private javax.swing.JTextField tl37;
    private javax.swing.JTextField tl38;
    private javax.swing.JTextField tl39;
    private javax.swing.JTextField tl4;
    private javax.swing.JTextField tl40;
    private javax.swing.JTextField tl41;
    private javax.swing.JTextField tl42;
    private javax.swing.JTextField tl43;
    private javax.swing.JTextField tl44;
    private javax.swing.JTextField tl45;
    private javax.swing.JTextField tl46;
    private javax.swing.JTextField tl47;
    private javax.swing.JTextField tl48;
    private javax.swing.JTextField tl49;
    private javax.swing.JTextField tl5;
    private javax.swing.JTextField tl50;
    private javax.swing.JTextField tl51;
    private javax.swing.JTextField tl52;
    private javax.swing.JTextField tl53;
    private javax.swing.JTextField tl54;
    private javax.swing.JTextField tl55;
    private javax.swing.JTextField tl56;
    private javax.swing.JTextField tl57;
    private javax.swing.JTextField tl58;
    private javax.swing.JTextField tl59;
    private javax.swing.JTextField tl6;
    private javax.swing.JTextField tl60;
    private javax.swing.JTextField tl61;
    private javax.swing.JTextField tl62;
    private javax.swing.JTextField tl63;
    private javax.swing.JTextField tl64;
    private javax.swing.JTextField tl65;
    private javax.swing.JTextField tl66;
    private javax.swing.JTextField tl67;
    private javax.swing.JTextField tl68;
    private javax.swing.JTextField tl69;
    private javax.swing.JTextField tl7;
    private javax.swing.JTextField tl70;
    private javax.swing.JTextField tl71;
    private javax.swing.JTextField tl72;
    private javax.swing.JTextField tl73;
    private javax.swing.JTextField tl74;
    private javax.swing.JTextField tl75;
    private javax.swing.JTextField tl76;
    private javax.swing.JTextField tl77;
    private javax.swing.JTextField tl78;
    private javax.swing.JTextField tl79;
    private javax.swing.JTextField tl8;
    private javax.swing.JTextField tl80;
    private javax.swing.JTextField tl81;
    private javax.swing.JTextField tl82;
    private javax.swing.JTextField tl83;
    private javax.swing.JTextField tl84;
    private javax.swing.JTextField tl85;
    private javax.swing.JTextField tl86;
    private javax.swing.JTextField tl87;
    private javax.swing.JTextField tl88;
    private javax.swing.JTextField tl89;
    private javax.swing.JTextField tl9;
    private javax.swing.JTextField tl90;
    private javax.swing.JTextField tl91;
    private javax.swing.JTextField tl92;
    private javax.swing.JTextField tl93;
    private javax.swing.JTextField tl94;
    private javax.swing.JTextField tl95;
    private javax.swing.JTextField tp0;
    private javax.swing.JTextField tp1;
    private javax.swing.JTextField tp10;
    private javax.swing.JTextField tp100;
    private javax.swing.JTextField tp101;
    private javax.swing.JTextField tp102;
    private javax.swing.JTextField tp103;
    private javax.swing.JTextField tp104;
    private javax.swing.JTextField tp105;
    private javax.swing.JTextField tp106;
    private javax.swing.JTextField tp107;
    private javax.swing.JTextField tp108;
    private javax.swing.JTextField tp109;
    private javax.swing.JTextField tp11;
    private javax.swing.JTextField tp110;
    private javax.swing.JTextField tp111;
    private javax.swing.JTextField tp112;
    private javax.swing.JTextField tp113;
    private javax.swing.JTextField tp114;
    private javax.swing.JTextField tp115;
    private javax.swing.JTextField tp116;
    private javax.swing.JTextField tp117;
    private javax.swing.JTextField tp118;
    private javax.swing.JTextField tp119;
    private javax.swing.JTextField tp12;
    private javax.swing.JTextField tp120;
    private javax.swing.JTextField tp121;
    private javax.swing.JTextField tp122;
    private javax.swing.JTextField tp123;
    private javax.swing.JTextField tp124;
    private javax.swing.JTextField tp125;
    private javax.swing.JTextField tp126;
    private javax.swing.JTextField tp127;
    private javax.swing.JTextField tp128;
    private javax.swing.JTextField tp129;
    private javax.swing.JTextField tp13;
    private javax.swing.JTextField tp130;
    private javax.swing.JTextField tp131;
    private javax.swing.JTextField tp132;
    private javax.swing.JTextField tp133;
    private javax.swing.JTextField tp134;
    private javax.swing.JTextField tp135;
    private javax.swing.JTextField tp14;
    private javax.swing.JTextField tp15;
    private javax.swing.JTextField tp16;
    private javax.swing.JTextField tp17;
    private javax.swing.JTextField tp18;
    private javax.swing.JTextField tp19;
    private javax.swing.JTextField tp2;
    private javax.swing.JTextField tp20;
    private javax.swing.JTextField tp21;
    private javax.swing.JTextField tp22;
    private javax.swing.JTextField tp23;
    private javax.swing.JTextField tp24;
    private javax.swing.JTextField tp25;
    private javax.swing.JTextField tp26;
    private javax.swing.JTextField tp27;
    private javax.swing.JTextField tp28;
    private javax.swing.JTextField tp29;
    private javax.swing.JTextField tp3;
    private javax.swing.JTextField tp30;
    private javax.swing.JTextField tp31;
    private javax.swing.JTextField tp32;
    private javax.swing.JTextField tp33;
    private javax.swing.JTextField tp34;
    private javax.swing.JTextField tp35;
    private javax.swing.JTextField tp36;
    private javax.swing.JTextField tp37;
    private javax.swing.JTextField tp38;
    private javax.swing.JTextField tp39;
    private javax.swing.JTextField tp4;
    private javax.swing.JTextField tp40;
    private javax.swing.JTextField tp41;
    private javax.swing.JTextField tp42;
    private javax.swing.JTextField tp43;
    private javax.swing.JTextField tp44;
    private javax.swing.JTextField tp45;
    private javax.swing.JTextField tp46;
    private javax.swing.JTextField tp47;
    private javax.swing.JTextField tp48;
    private javax.swing.JTextField tp49;
    private javax.swing.JTextField tp5;
    private javax.swing.JTextField tp50;
    private javax.swing.JTextField tp51;
    private javax.swing.JTextField tp52;
    private javax.swing.JTextField tp53;
    private javax.swing.JTextField tp54;
    private javax.swing.JTextField tp55;
    private javax.swing.JTextField tp56;
    private javax.swing.JTextField tp57;
    private javax.swing.JTextField tp58;
    private javax.swing.JTextField tp59;
    private javax.swing.JTextField tp6;
    private javax.swing.JTextField tp60;
    private javax.swing.JTextField tp61;
    private javax.swing.JTextField tp62;
    private javax.swing.JTextField tp63;
    private javax.swing.JTextField tp64;
    private javax.swing.JTextField tp65;
    private javax.swing.JTextField tp66;
    private javax.swing.JTextField tp67;
    private javax.swing.JTextField tp68;
    private javax.swing.JTextField tp69;
    private javax.swing.JTextField tp7;
    private javax.swing.JTextField tp70;
    private javax.swing.JTextField tp71;
    private javax.swing.JTextField tp72;
    private javax.swing.JTextField tp73;
    private javax.swing.JTextField tp74;
    private javax.swing.JTextField tp75;
    private javax.swing.JTextField tp76;
    private javax.swing.JTextField tp77;
    private javax.swing.JTextField tp78;
    private javax.swing.JTextField tp79;
    private javax.swing.JTextField tp8;
    private javax.swing.JTextField tp80;
    private javax.swing.JTextField tp81;
    private javax.swing.JTextField tp82;
    private javax.swing.JTextField tp83;
    private javax.swing.JTextField tp84;
    private javax.swing.JTextField tp85;
    private javax.swing.JTextField tp86;
    private javax.swing.JTextField tp87;
    private javax.swing.JTextField tp88;
    private javax.swing.JTextField tp89;
    private javax.swing.JTextField tp9;
    private javax.swing.JTextField tp90;
    private javax.swing.JTextField tp91;
    private javax.swing.JTextField tp92;
    private javax.swing.JTextField tp93;
    private javax.swing.JTextField tp94;
    private javax.swing.JTextField tp95;
    private javax.swing.JTextField tp96;
    private javax.swing.JTextField tp97;
    private javax.swing.JTextField tp98;
    private javax.swing.JTextField tp99;
    public javax.swing.JTextArea txtAreaLog;
    private javax.swing.JTextField txtLocal;
    private javax.swing.JTextField txtPesquisador1;
    private javax.swing.JTextField txtPesquisador2;
    public javax.swing.JTextField txtPorta;
    // End of variables declaration//GEN-END:variables
	
	
	
	private String[] volFieldsNames = {"P1","P3","P2","M","O1","O2","O3","C1","C2","C3","C4","C5",
		"S1","S2","S3","S4","S5","S6","SE1","SE2","SE3","SE4","SE5","R1","R2","R3","R4","R5","R6"};
	
	private HashMap<String, JTextField[]> fieldsMap;
}

class ImagemRenderer extends DefaultTableCellRenderer{
    public ImagemRenderer(){
        super();
        setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
        //setBackground(new Color(178,178,178));
    }

    public void setValue(Object value){
        if (value == null){
            setText("");
        } else {
            setIcon((ImageIcon)value);
        }

    }
    }

class SQLiteFilter extends FileFilter{

	@Override
	public boolean accept(File f) {
		return f.isDirectory() || f.getAbsolutePath().endsWith(".db");
	}

	@Override
	public String getDescription() {
		return "Dados pesquisa OD (*.db)";
	}
	
}

class JsonFilter extends FileFilter{

	@Override
	public boolean accept(File f) {
		return f.isDirectory() || f.getAbsolutePath().endsWith(".json");
	}

	@Override
	public String getDescription() {
		return "Arquivos JSON (*.json)";
	}
	
}