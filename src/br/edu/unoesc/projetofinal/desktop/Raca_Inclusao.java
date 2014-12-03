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

import br.edu.unoesc.projetofinal.dao.RacaDAO;
import br.edu.unoesc.projetofinal.dao.factory.DaoFactory;
import br.edu.unoesc.projetofinal.model.Raca;

public class Raca_Inclusao extends JFrame {

	private JLabel jlbUsuario = new JLabel("Nova Raça");
	private JLabel jlbNome = new JLabel("Nome");
	private JTextField jtfNomeRaca = new JTextField();
	private JButton jbtCadastrarFornCli = new JButton("Cadastrar"), jbtSair = new JButton("Sair");
	private RacaDAO racaDao=DaoFactory.get().racaDao();
	
	private void posicionaObjeto(JComponent obj, int x, int y, int w, int h) {
		obj.setBounds(x, y, w, h);
		getContentPane().add(obj);
	}

	public Raca_Inclusao(final DefaultTableModel dtmDados) {
		setLayout(null);

		jlbUsuario.setFont(new Font("Arial", Font.BOLD, 24));
		jlbUsuario.setForeground(Color.DARK_GRAY);
		
		posicionaObjeto(jlbUsuario, 135, 45, 500, 25);
		posicionaObjeto(jlbNome, 75, 105, 100, 25);
		posicionaObjeto(jtfNomeRaca, 130, 105, 150, 25);
		posicionaObjeto(jbtCadastrarFornCli, 90, 165, 100, 30);
		posicionaObjeto(jbtSair, 230, 165, 80, 20);
		
		jbtCadastrarFornCli.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int aux=0;
				if(jtfNomeRaca.getText().isEmpty()){
					JOptionPane.showMessageDialog(null, "Digite o nome da Raça");
				}
				else{
					for(Raca raca:racaDao.listarTodos()){
						if(raca.getNome().equals(jtfNomeRaca.getText())){
							aux=1;
							break;
						}
					}
					if(aux==0){
						Raca raca=new Raca();
						raca.setNome(jtfNomeRaca.getText());
						racaDao.store(raca);
						int linha=1;
						dtmDados.setRowCount(1);
						for (Raca raca1 : racaDao.listarTodos()) {
							dtmDados.setRowCount(dtmDados.getRowCount() + 1);
							dtmDados.setValueAt(raca1.getCodigo(), linha, 0);
							dtmDados.setValueAt(raca1.getNome(), linha, 1);
							linha++;
						}
						JOptionPane.showMessageDialog(null, "Raça Cadastrada com Sucesso!");
						dispose();
					}
					if(aux==1){
						JOptionPane.showMessageDialog(null, "Já existe uma Raça Cadastrada com esse Nome!");
					}
				}
			}
		});
		
		jbtSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();

			}
		});

		setTitle("Raça");
		setSize(400, 245);
		setVisible(true);
		this.setResizable(false);
		setLocationRelativeTo(null);
		this.getContentPane().setBackground(Color.lightGray);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

	}

}