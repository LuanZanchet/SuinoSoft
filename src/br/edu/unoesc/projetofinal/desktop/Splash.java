package br.edu.unoesc.projetofinal.desktop;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Splash extends JFrame {
	private JLabel jlbSplash;
	
	public Splash(){
		setLayout(null);
		
		jlbSplash=new JLabel(new ImageIcon("Splash.png"));
		jlbSplash.setBounds(0, 0, 300, 300);
		getContentPane().add(jlbSplash);
		
		setSize(300, 300);
		setUndecorated(true);
		setVisible(true);
		setLocationRelativeTo(null);
		
		Thread.currentThread();
		try {
			Thread.sleep(5000);
			dispose();
			new Tela_Inicial();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		new Splash();
	}
}