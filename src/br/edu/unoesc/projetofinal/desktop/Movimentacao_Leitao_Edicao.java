package br.edu.unoesc.projetofinal.desktop;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import br.edu.unoesc.projetofinal.dao.LactacaoDAO;
import br.edu.unoesc.projetofinal.dao.MovimentacaoDeLeitaoDAO;
import br.edu.unoesc.projetofinal.dao.factory.DaoFactory;
import br.edu.unoesc.projetofinal.model.Lactacao;
import br.edu.unoesc.projetofinal.model.Matriz;
import br.edu.unoesc.projetofinal.model.MovimentacaoDeLeitao;

public class Movimentacao_Leitao_Edicao extends JFrame {

	private JLabel jlbMovimentacao = new JLabel("Movimentação de Leitão ");
	private JLabel jlbMatrizDoadora = new JLabel("Matriz Doadora");
	private JLabel jlbMatrizReceptora = new JLabel("Matriz Receptora");
	private JLabel jlbData = new JLabel("Data ");
	private JLabel jlbQuantidadeLeitao = new JLabel("Animais Transferidos");
	private JTextField jtfMatrizDoadora = new JTextField();
	private JTextField jtfMatrizReceptora = new JTextField();
	private JTextField jtfData = new JTextField();
	private JTextField jtfQuantidadeLeitao = new JTextField();
	private JButton jbtCadastrar = new JButton("Alterar");
	private JButton jbtSair = new JButton("Sair");
	private MovimentacaoDeLeitaoDAO movimentacaoDao = DaoFactory.get().movimentacaoDeLeitaoDao();
	private MovimentacaoDeLeitao movimentacao = new MovimentacaoDeLeitao();
	private JTextField jtfGuardaValor = new JTextField();
	private LactacaoDAO lactacaoDao=DaoFactory.get().lactacaoDao();

	private void posicionaObjeto(JComponent obj, int x, int y, int w, int h) {
		obj.setBounds(x, y, w, h);
		getContentPane().add(obj);
	}

	public void setValor(Integer posicao) {
		jtfMatrizDoadora.setText(movimentacaoDao.listarTodos().get(posicao).getMatrizDoadora().getBrinco().toString());
		jtfMatrizReceptora.setText(movimentacaoDao.listarTodos().get(posicao).getMatrizReceptora().getBrinco()
				.toString());
		jtfData.setText(movimentacaoDao.listarTodos().get(posicao).getData().toString());
		jtfQuantidadeLeitao.setText(movimentacaoDao.listarTodos().get(posicao).getQuantidadeLeitao().toString());
		jtfGuardaValor.setText(posicao.toString());
	}

	public Movimentacao_Leitao_Edicao(final DefaultTableModel dtmDados) {
		setLayout(null);

		jlbMovimentacao.setFont(new Font("Arial", Font.BOLD, 18));
		jlbMovimentacao.setForeground(Color.DARK_GRAY);
		
		jtfMatrizDoadora.setEditable(false);
		jtfMatrizReceptora.setEditable(false);
		
		posicionaObjeto(jlbMovimentacao, 90, 15, 500, 25);
		posicionaObjeto(jlbMatrizDoadora, 35, 75, 150, 25);
		posicionaObjeto(jtfMatrizDoadora, 130, 75, 200, 25);
		posicionaObjeto(jlbMatrizReceptora, 25, 105, 150, 25);
		posicionaObjeto(jtfMatrizReceptora, 130, 105, 200, 25);
		posicionaObjeto(jlbData, 95, 135, 110, 25);
		posicionaObjeto(jtfData, 130, 135, 200, 25);
		posicionaObjeto(jlbQuantidadeLeitao, 5, 165, 200, 25);
		posicionaObjeto(jtfQuantidadeLeitao, 130, 165, 200, 25);
		posicionaObjeto(jbtCadastrar, 30, 225, 150, 35);
		posicionaObjeto(jbtSair, 200, 225, 120, 20);

		jbtCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (jtfData.getText().isEmpty() || jtfQuantidadeLeitao.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Existem campos em branco no formulário");
				} else {
					movimentacao = movimentacaoDao.listarTodos().get(Integer.valueOf(jtfGuardaValor.getText()));
					
					Matriz matrizDoadora=new Matriz();
					matrizDoadora=movimentacao.getMatrizDoadora();
					Lactacao lactacaoDoadora=new Lactacao();
					lactacaoDoadora=matrizDoadora.getLactacoes().get(matrizDoadora.getLactacoes().size()-1);
					
					Matriz matrizReceptora=new Matriz();
					matrizReceptora=movimentacao.getMatrizReceptora();
					Lactacao lactacaoReceptora=new Lactacao();
					lactacaoReceptora=matrizReceptora.getLactacoes().get(matrizReceptora.getLactacoes().size()-1);
					
					lactacaoDoadora.setQuantAtual(lactacaoDoadora.getQuantAtual()+movimentacao.getQuantidadeLeitao());
					lactacaoDoadora.setQuantDoados(lactacaoDoadora.getQuantDoados()-movimentacao.getQuantidadeLeitao());
					
					lactacaoReceptora.setQuantAtual(lactacaoReceptora.getQuantAtual()-movimentacao.getQuantidadeLeitao());
					lactacaoReceptora.setQuantRecebidos(lactacaoReceptora.getQuantRecebidos()-movimentacao.getQuantidadeLeitao());
					
					movimentacao.setQuantidadeLeitao(Integer.valueOf(jtfQuantidadeLeitao.getText()));
					
					lactacaoDoadora.setQuantAtual(lactacaoDoadora.getQuantAtual()-movimentacao.getQuantidadeLeitao());
					lactacaoDoadora.setQuantDoados(lactacaoDoadora.getQuantDoados()+movimentacao.getQuantidadeLeitao());
					
					lactacaoReceptora.setQuantAtual(lactacaoReceptora.getQuantAtual()+movimentacao.getQuantidadeLeitao());
					lactacaoReceptora.setQuantRecebidos(lactacaoReceptora.getQuantRecebidos()+movimentacao.getQuantidadeLeitao());
					
					lactacaoDao.alter(lactacaoReceptora);
					lactacaoDao.alter(lactacaoDoadora);
					
					movimentacao.setData(Date.valueOf(jtfData.getText()));
					movimentacaoDao.alter(movimentacao);

					JOptionPane.showMessageDialog(null, "Movimentação Alterada com Sucesso");
					int linha = 1;
					dtmDados.setRowCount(1);
					for (MovimentacaoDeLeitao movimentacao : movimentacaoDao.listarTodos()) {
						dtmDados.setRowCount(dtmDados.getRowCount() + 1);
						dtmDados.setValueAt(movimentacao.getMatrizDoadora().getBrinco(), linha, 0);
						dtmDados.setValueAt(movimentacao.getMatrizReceptora().getBrinco(), linha, 1);
						dtmDados.setValueAt(movimentacao.getData(), linha, 2);
						dtmDados.setValueAt(movimentacao.getQuantidadeLeitao(), linha, 3);
						linha++;
					}
					dispose();
				}
			}
		});

		jbtSair.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				dispose();

			}
		});

		setTitle("Movimentacao de Leitão");
		setSize(420, 330);
		setVisible(true);
		this.setResizable(false);
		setLocationRelativeTo(null);
		this.getContentPane().setBackground(Color.lightGray);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
}