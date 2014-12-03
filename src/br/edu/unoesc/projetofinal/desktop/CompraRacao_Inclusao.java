package br.edu.unoesc.projetofinal.desktop;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import br.edu.unoesc.projetofinal.dao.CompraRacaoDAO;
import br.edu.unoesc.projetofinal.dao.FornecedorDAO;
import br.edu.unoesc.projetofinal.dao.NotaCompraDAO;
import br.edu.unoesc.projetofinal.dao.RacaoDAO;
import br.edu.unoesc.projetofinal.dao.factory.DaoFactory;
import br.edu.unoesc.projetofinal.model.CompraRacao;
import br.edu.unoesc.projetofinal.model.Fornecedor;
import br.edu.unoesc.projetofinal.model.Nota;
import br.edu.unoesc.projetofinal.model.Racao;

public class CompraRacao_Inclusao extends JFrame {

	private JLabel jlbCompraracao = new JLabel("Compra de Ração");
	private JLabel jlbRacao = new JLabel("Ração");
	private JLabel jlbNota = new JLabel("Nota");
	private JLabel jlbFornecedor = new JLabel("Fornecedor");
	private JLabel jlbData = new JLabel("Data Compra");
	private JLabel jlbQuantidade = new JLabel("Quantidade");
	
	private JComboBox<String> jcbRacao = new JComboBox<>();
	private JComboBox<Long>  jcbNota = new JComboBox<>();
	private JComboBox<String>  jcbFornecedor = new JComboBox<>();
	private JTextField jtfDataRacao = new JTextField();
	private JTextField jtfQuantidadeRacao =  new JTextField();
	private FornecedorDAO fornecedorDAO = DaoFactory.get().fornecedorDao();
	private RacaoDAO racaoDAO = DaoFactory.get().racaoDao();
	private NotaCompraDAO notacompraDAO = DaoFactory.get().notaCompraDao();
	private CompraRacaoDAO compraRacaoDao = DaoFactory.get().compraRacaoDao();
 
	private JButton jbtCadastrarFornCli = new JButton("Cadastrar"),
			jbtSair = new JButton("Sair");

	private void posicionaObjeto(JComponent obj, int x, int y, int w, int h) {
		obj.setBounds(x, y, w, h);
		getContentPane().add(obj);
	}

	public CompraRacao_Inclusao(final DefaultTableModel dtmDados) {
		setLayout(null);

		jlbCompraracao.setFont(new Font("Arial", Font.BOLD, 24));
		jlbCompraracao.setForeground(Color.DARK_GRAY);
		posicionaObjeto(jlbCompraracao, 85, 45, 500, 25);

		posicionaObjeto(jlbRacao, 90, 105, 100, 25);
		posicionaObjeto(jlbData, 50, 145, 100, 25);
		posicionaObjeto(jlbFornecedor, 60, 185, 100, 25);
		posicionaObjeto(jlbNota, 95, 225, 100, 25);
		posicionaObjeto(jlbQuantidade, 60, 265, 100, 25);
		
		
		posicionaObjeto(jcbRacao, 140, 105, 150, 25);
		posicionaObjeto(jtfDataRacao, 140, 145, 150, 25);
		posicionaObjeto(jcbFornecedor, 140, 185, 150, 25);
		posicionaObjeto(jcbNota, 140, 225, 150, 25);
		posicionaObjeto(jtfQuantidadeRacao, 140, 265, 150, 25);

		
		
		

		posicionaObjeto(jbtCadastrarFornCli, 90, 325, 100, 30);
		posicionaObjeto(jbtSair, 230, 325, 80, 20);
		jbtSair.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				dispose();

			}
		});
		
		
		jcbRacao.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		for (Fornecedor fornecedor : fornecedorDAO.listarTodos()) {
			jcbFornecedor.addItem(fornecedor.getNome());
		}
		for (Racao racao : racaoDAO.listarTodos()) {
			jcbRacao.addItem(racao.getNome());
		}
		
		for (Nota nota : notacompraDAO.listarTodos()) {
			jcbNota.addItem(nota.getNumero());
		}
	
		
		
		
		jbtCadastrarFornCli.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if ( jtfDataRacao.getText().isEmpty() || jtfQuantidadeRacao.getText().isEmpty() ) {
					JOptionPane.showMessageDialog(null, "Existem informações não preenchidas no formulário");
				} else {
					int aux = 0;				
					for (CompraRacao racao : compraRacaoDao.listarTodos()) {
						if (racao.getRacao().equals(jcbRacao.getSelectedItem())) {
							aux = 1;
							break;
						}
						
					}
					if (aux == 0) {
						CompraRacao Racao = new CompraRacao();
						Racao.setQuantidade(Integer.valueOf(jtfQuantidadeRacao.getText()));
						Racao.setData(Date.valueOf(jtfDataRacao.getText()));
						Racao.setFornecedor(fornecedorDAO.listarTodos().get(jcbFornecedor.getSelectedIndex()));
						Racao.setNota(notacompraDAO.listarTodos().get(jcbNota.getSelectedIndex()));
						Racao.setRacao(racaoDAO.listarTodos().get(jcbRacao.getSelectedIndex()));
						
						compraRacaoDao.store(Racao);
						JOptionPane.showMessageDialog(null, "Compra Ração Cadastrada com Sucesso");
						dtmDados.setRowCount(1);
						int linha = 1;
						for (CompraRacao racao : compraRacaoDao.listarTodos()) {
							dtmDados.setRowCount(dtmDados.getRowCount() + 1);
							dtmDados.setValueAt(racao.getCodigo(), linha, 0);
							dtmDados.setValueAt(racao.getData(), linha, 1);
							dtmDados.setValueAt(racao.getFornecedor().getNome(), linha, 2);
							dtmDados.setValueAt(racao.getNota().getNumero(), linha, 3);
							dtmDados.setValueAt(racao.getRacao().getNome(), linha, 4);
							dtmDados.setValueAt(racao.getQuantidade(), linha, 5);
							linha++;
						}
						
					}
					if (aux == 1) {
						JOptionPane.showMessageDialog(null, "Já existe uma compra de ração cadastrada com essa nota");
					}
					dispose();
				}
			}
		});
		
		
		
		

		setTitle("Compra de Ração");
		
		setSize(400, 415);
		setVisible(true);
		this.setResizable(false);
		setLocationRelativeTo(null);
		this.getContentPane().setBackground(Color.lightGray);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
}
