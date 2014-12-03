package br.edu.unoesc.projetofinal.desktop;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import br.edu.unoesc.projetofinal.dao.CausaDAO;
import br.edu.unoesc.projetofinal.dao.factory.DaoFactory;
import br.edu.unoesc.projetofinal.model.Causa;

public class Causa_Inclusao extends JFrame {
	private JLabel jlbCausa = new JLabel("Causa Morte/Descarte");
	private JLabel jlbNome = new JLabel("Nome");
	private JTextField jtfNomeCausa = new JTextField();	
	private JButton jbtCadastrarFornCli = new JButton("Cadastrar"),
			jbtSair = new JButton("Sair");
	private CausaDAO causaDao=DaoFactory.get().causaDao();
	
	private void posicionaObjeto(JComponent obj, int x, int y, int w, int h) {
		obj.setBounds(x, y, w, h);
		getContentPane().add(obj);
	}

	 public Causa_Inclusao(final DefaultTableModel dtmDados) {
		setLayout(null);

		jlbCausa.setFont(new Font("Arial", Font.BOLD, 24));
		jlbCausa.setForeground(Color.DARK_GRAY);
		
		posicionaObjeto(jlbCausa, 85, 45, 500, 25);
		posicionaObjeto(jlbNome, 100, 105, 100, 25);
		posicionaObjeto(jtfNomeCausa, 140, 105, 150, 25);
		posicionaObjeto(jbtCadastrarFornCli, 90, 195, 100, 30);
		posicionaObjeto(jbtSair, 230, 195, 80, 20);
		
		jbtCadastrarFornCli.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(jtfNomeCausa.getText().isEmpty()){
					JOptionPane.showMessageDialog(null, "Digite o nome da Causa! ");
				}
				else{
					int aux=0;
					for(Causa causa:causaDao.listarTodos()){
						if(causa.getNome().equalsIgnoreCase((jtfNomeCausa.getText()))){
							aux=1;
							break;
						}
					}
					if(aux==0){
						Causa causa=new Causa();
						causa.setNome(jtfNomeCausa.getText());
						causaDao.store(causa);
						dtmDados.setRowCount(1);
						int linha=1;
						for(Causa causa1:causaDao.listarTodos()){
							dtmDados.setRowCount(dtmDados.getRowCount()+1);
							dtmDados.setValueAt(causa1.getCodigo(), linha, 0);
							dtmDados.setValueAt(causa1.getNome(), linha, 1);
							linha++;
						}
						JOptionPane.showMessageDialog(null, "Causa Cadastrada com Sucesso!");
						dispose();
					}
					if(aux==1){
						JOptionPane.showMessageDialog(null, "Já Existe uma Causa Cadastrada com esse Nome!");
					}
				}
			}
		});
		
		jbtSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});

		setTitle("Causa Morte");
		setSize(400, 285);
		setVisible(true);
		this.setResizable(false);
		setLocationRelativeTo(null);
		this.getContentPane().setBackground(Color.lightGray);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

	}
}
