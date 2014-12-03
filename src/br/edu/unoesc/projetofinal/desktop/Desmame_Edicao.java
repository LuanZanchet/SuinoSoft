package br.edu.unoesc.projetofinal.desktop;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Date;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import br.edu.unoesc.projetofinal.dao.DesmameDAO;
import br.edu.unoesc.projetofinal.dao.LoteDAO;
import br.edu.unoesc.projetofinal.dao.factory.DaoFactory;
import br.edu.unoesc.projetofinal.model.Desmame;
import br.edu.unoesc.projetofinal.model.Lote;

public class Desmame_Edicao extends JFrame {
	private JLabel jlbDesmame = new JLabel(" Inclusão de Desmame");
	private JLabel jlbMatriz = new JLabel("Matriz");
	private JLabel jlbPrevisao = new JLabel("Previsão");
	private JLabel jlbData = new JLabel("Data");
	private JLabel jlbPesoTotal = new JLabel("Peso Total");
	private JLabel jlbPesoMedio = new JLabel("Peso Medio");
	private JLabel jlbLote = new JLabel("Lote");
	private JLabel jlbObsLote = new JLabel("Observação Lote");
	private JLabel jlbObsDesmame = new JLabel("Obs Desmame");
	private JLabel jlbInformacoes = new JLabel("Informações Leitões");
	private JLabel jlbVivos = new JLabel("Vivos");
	private JLabel jlbRecebidos = new JLabel("Recebidos");
	private JLabel jlbMortos = new JLabel("Mortos");
	private JLabel jlbDoados = new JLabel("Doados");
	private JTextField jtfVivos = new JTextField();
	private JTextField jtfMatriz = new JTextField();
	private JTextField jtfPrevisao = new JTextField();
	private JTextField jtfData = new JTextField();
	private JTextField jtfPesoTotal = new JTextField();
	private JTextField jtfPesoMedio = new JTextField();
	private JTextField jtfLote = new JTextField();
	private JTextField jtfObsLote = new JTextField();
	private JTextField jtfObsDesmame = new JTextField();
	private JTextField jtfRecebidos = new JTextField();
	private JTextField jtfMortos = new JTextField();
	private JTextField jtfDoados = new JTextField();
	private JButton jbtCadastrar = new JButton("Cadastrar");
	private JButton jbtSair = new JButton("Sair");
	private DesmameDAO desmameDao = DaoFactory.get().desmameDao();
	private Desmame desmame = new Desmame();
	private LoteDAO loteDao = DaoFactory.get().loteDao();
	private JTextField jtfGuardaValor = new JTextField();

	private void posicionaObjeto(JComponent obj, int x, int y, int w, int h) {
		obj.setBounds(x, y, w, h);
		getContentPane().add(obj);
	}

	public void setValor(Integer posicao) {
		jtfGuardaValor.setText(posicao.toString());
		jtfMatriz.setText(desmameDao.listarTodos().get(posicao).getMatriz().getBrinco().toString());
		jtfPrevisao.setText(desmameDao.listarTodos().get(posicao).getPrevisaoDesmame().toString());
		jtfData.setText(desmameDao.listarTodos().get(posicao).getData().toString());
		jtfPesoMedio.setText(desmameDao.listarTodos().get(posicao).getPesoMedio().toString());
		jtfPesoTotal.setText(desmameDao.listarTodos().get(posicao).getPesoTotal().toString());
		jtfLote.setText(desmameDao.listarTodos().get(posicao).getLote().getNumero().toString());
		jtfObsLote.setText(desmameDao.listarTodos().get(posicao).getObsLote());
		jtfObsDesmame.setText(desmameDao.listarTodos().get(posicao).getObsDesmame());
		jtfVivos.setText(desmameDao.listarTodos().get(posicao).getQuantidade().toString());
		desmame = desmameDao.listarTodos().get(Integer.valueOf(posicao));
	}

	public Desmame_Edicao(final DefaultTableModel dtmDados) {
		setLayout(null);

		jtfMatriz.setEditable(false);

		jlbDesmame.setFont(new Font("Arial", Font.BOLD, 18));
		jlbDesmame.setForeground(Color.DARK_GRAY);
		posicionaObjeto(jlbDesmame, 90, 15, 500, 25);
		jlbInformacoes.setFont(new Font("Arial", Font.BOLD, 18));
		jlbInformacoes.setForeground(Color.DARK_GRAY);
		posicionaObjeto(jlbInformacoes, 450, 15, 500, 25);

		jtfRecebidos.setEditable(false);
		jtfMortos.setEditable(false);
		jtfDoados.setEditable(false);
		jtfVivos.setEditable(false);

		posicionaObjeto(jlbMatriz, 85, 75, 150, 25);
		posicionaObjeto(jtfMatriz, 130, 75, 200, 25);
		posicionaObjeto(jlbPrevisao, 70, 105, 150, 25);
		posicionaObjeto(jtfPrevisao, 130, 105, 200, 25);
		posicionaObjeto(jlbData, 95, 135, 110, 25);
		posicionaObjeto(jtfData, 130, 135, 200, 25);
		posicionaObjeto(jlbVivos, 495, 45, 100, 25);
		posicionaObjeto(jtfVivos, 530, 45, 50, 25);
		posicionaObjeto(jlbMortos, 485, 75, 150, 25);
		posicionaObjeto(jtfMortos, 530, 75, 50, 25);
		posicionaObjeto(jlbDoados, 485, 105, 150, 25);
		posicionaObjeto(jtfDoados, 530, 105, 50, 25);
		posicionaObjeto(jlbRecebidos, 465, 135, 110, 25);
		posicionaObjeto(jtfRecebidos, 530, 135, 50, 25);
		posicionaObjeto(jlbPesoTotal, 125, 195, 200, 25);
		posicionaObjeto(jtfPesoTotal, 190, 195, 80, 25);
		posicionaObjeto(jlbPesoMedio, 325, 195, 200, 25);
		posicionaObjeto(jtfPesoMedio, 400, 195, 80, 25);
		posicionaObjeto(jlbLote, 95, 245, 90, 25);
		posicionaObjeto(jtfLote, 130, 245, 200, 25);
		posicionaObjeto(jlbObsLote, 25, 275, 150, 25);
		posicionaObjeto(jtfObsLote, 130, 275, 200, 25);
		posicionaObjeto(jlbObsDesmame, 35, 305, 150, 25);
		posicionaObjeto(jtfObsDesmame, 130, 305, 200, 25);
		posicionaObjeto(jbtCadastrar, 130, 355, 200, 50);
		posicionaObjeto(jbtSair, 380, 355, 120, 20);

		jtfLote.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent arg0) {
				if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
					int auxiliar = 0;
					for (Lote lote : loteDao.listarTodos()) {
						if (lote.getNumero().equals(Long.valueOf(jtfLote.getText()))) {
							auxiliar = 1;
							Lote loteReposicao = new Lote();
							loteReposicao = desmame.getLote();
							loteReposicao.setQuantidadeLeitao(loteReposicao.getQuantidadeLeitao()
									- desmame.getQuantidade());
							loteDao.alter(loteReposicao);
							desmame.setLote(lote);
							break;
						}
					}
					if (auxiliar == 0) {
						JOptionPane.showMessageDialog(null, "Nenhum lote encontrado");
						jtfLote.setText(null);
					} else {
						jtfObsLote.requestFocus();
					}
				}
			}
		});

		jbtCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (jtfData.getText().isEmpty() || jtfPesoMedio.getText().isEmpty() || jtfPesoTotal.getText().isEmpty()
						|| jtfObsLote.getText().isEmpty() || jtfObsDesmame.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Existem informações não preenchidas no formulário");
				} else {
					desmame.setData(Date.valueOf(jtfData.getText()));
					desmame.setPesoMedio(Double.valueOf(jtfPesoMedio.getText()));
					desmame.setPesoTotal(Double.valueOf(jtfPesoTotal.getText()));
					desmame.setObsLote(jtfObsLote.getText());
					desmame.setQuantidade(Integer.valueOf(jtfVivos.getText()));
					desmame.setObsDesmame(jtfObsDesmame.getText());
					desmame.setPrevisaoDesmame(Date.valueOf(jtfPrevisao.getText()));
					desmame.getLote().setQuantidadeLeitao(
							desmame.getLote().getQuantidadeLeitao() + Integer.valueOf(jtfVivos.getText()));
					loteDao.alter(desmame.getLote());
					desmameDao.alter(desmame);
					JOptionPane.showMessageDialog(null, "Desmame Alterado com Sucesso");
					int linha = 1;
					dtmDados.setRowCount(1);
					for (Desmame desmame : desmameDao.listarTodos()) {
						dtmDados.setRowCount(dtmDados.getRowCount() + 1);
						dtmDados.setValueAt(desmame.getMatriz().getBrinco(), linha, 0);
						dtmDados.setValueAt(desmame.getData(), linha, 1);
						dtmDados.setValueAt(desmame.getQuantidade(), linha, 2);
						dtmDados.setValueAt(desmame.getPesoTotal(), linha, 3);
						dtmDados.setValueAt(desmame.getLote().getNumero(), linha, 4);
						dtmDados.setValueAt(desmame.getObsDesmame(), linha, 5);
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

		setTitle("Desmame");
		setSize(700, 450);
		setVisible(true);
		this.setResizable(false);
		setLocationRelativeTo(null);
		this.getContentPane().setBackground(Color.lightGray);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

	}
}