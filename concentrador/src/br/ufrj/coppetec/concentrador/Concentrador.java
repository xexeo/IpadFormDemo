/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrj.coppetec.concentrador;

/**
 *
 * @author ludes
 */
public class Concentrador {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        StackTraceElement[] trace = Thread.currentThread().getStackTrace();
        System.out.printf("%s.%s()%n", trace[trace.length-1].getClassName(), trace[trace.length-1].getMethodName());
        Janela janela = new Janela();
        janela.setVisible(true);
    }
    
}
