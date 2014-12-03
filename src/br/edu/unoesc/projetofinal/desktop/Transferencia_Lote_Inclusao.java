package br.edu.unoesc.projetofinal.desktop;

import java.awt.Checkbox;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import br.edu.unoesc.projetofinal.dao.LoteDAO;
import br.edu.unoesc.projetofinal.dao.TransferenciaEntreLoteDAO;
import br.edu.unoesc.projetofinal.dao.factory.DaoFactory;
import br.edu.unoesc.projetofinal.model.Comprador;
import br.edu.unoesc.projetofinal.model.Lote;
import br.edu.unoesc.projetofinal.model.Nota;
import br.edu.unoesc.projetofinal.model.TransferenciaEntreLotes;

public class Transferencia_Lote_Inclusao extends JFrame {
	private JLabel jlbLotes = new JLabel("Transferencia de Lotes");

	private JLabel jlbLoteOrigem = new JLabel("Lote de Origem");
	private JLabel jlbLoteDestino = new JLabel("Lote de Destino");
	private JLabel jlbData = new JLabel("Data Transferencia");
	private JLabel jlbQuantidade = new JLabel("Quantidade");
	private JLabel jlbPesoTotal = new JLabel("PesoTotal");
	private JComboBox<Long> jcbLoteOrigem = new JComboBox<>();
	private JComboBox<Long> jcbLoteDestino = new JComboBox<>();
	private JTextField jtfData = new JTextField();
	private JTextField jtfQuantidade = new JTextField();
	private JTextField jtfPesoTotal = new JTextField();
	private JButton jbtCadastrar = new JButton("Cadastrar");
	private JButton jbtSair = new JButton("Sair");
	

	private LoteDAO loteDAO = DaoFactory.get().loteDao();
	private TransferenciaEntreLoteDAO tranfereLoteDAO = DaoFactory.get()
			.transferenciaEntreLotesDao();

	

	private void posicionaObjeto(JComponent obj, int x, int y, int w, int h) {
		obj.setBounds(x, y, w, h);
		getContentPane().add(obj);
	}

	public Transferencia_Lote_Inclusao(final DefaultTableModel dtmDados) {
		setLayout(null);

		jlbLotes.setFont(new Font("Arial", Font.BOLD, 24));
		jlbLotes.setForeground(Color.DARK_GRAY);
		posicionaObjeto(jlbLotes, 100, 15, 500, 25);
		posicionaObjeto(jlbLoteOrigem, 25, 75, 100, 25);
		posicionaObjeto(jcbLoteOrigem, 120, 75, 200, 25);
		posicionaObjeto(jlbLoteDestino, 25, 105, 100, 25);
		posicionaObjeto(jcbLoteDestino, 120, 105, 200, 25);
		posicionaObjeto(jlbData, 5, 135, 150, 25);
		posicionaObjeto(jtfData, 120, 135, 200, 25);
		posicionaObjeto(jlbQuantidade, 45, 165, 110, 25);
		posicionaObjeto(jtfQuantidade, 120, 165, 200, 25);
		posicionaObjeto(jlbPesoTotal, 55, 195, 110, 25);
		posicionaObjeto(jtfPesoTotal, 120, 195, 200, 25);

		posicionaObjeto(jbtCadastrar, 30, 255, 150, 35);
		posicionaObjeto(jbtSair, 200, 255, 120, 20);

		for (Lote lote : loteDAO.listarTodos()) {
			jcbLoteDestino.addItem(lote.getNumero());
			jcbLoteOrigem.addItem(lote.getNumero());
		}

		jbtCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (jtfData.getText().isEmpty()
						|| jtfQuantidade.getText().isEmpty()
						|| jtfPesoTotal.getText().isEmpty()) {
					JOptionPane
							.showMessageDialog(null,
									"Existem informações não preenchidas no formulário");
				} else {
					int aux = 0;
					for (TransferenciaEntreLotes transferencia : tranfereLoteDAO
							.listarTodos()) {

						if (transferencia.getLoteDestino().getNumero() == jcbLoteOrigem
								.getSelectedIndex()) {
							aux = 1;
							break;
						}

					}
					if (aux == 0) {
						TransferenciaEntreLotes transfere = new TransferenciaEntreLotes();

						transfere.setLoteOrigem(loteDAO.listarTodos().get(
								jcbLoteOrigem.getSelectedIndex()));
						transfere.setLoteDestino(loteDAO.listarTodos().get(
								jcbLoteDestino.getSelectedIndex()));
						transfere.setData(Date.valueOf(jtfData.getText()));
						transfere.setQuantidade(Integer.valueOf(jtfQuantidade
								.getText()));
						transfere.setPesoTotal(Double.valueOf(jtfPesoTotal
								.getText()));

						tranfereLoteDAO.store(transfere);
						JOptionPane.showMessageDialog(null,
								"Transferencia Cadastrada com Sucesso");
						dtmDados.setRowCount(1);
						int linha = 1;
						for (TransferenciaEntreLotes transferencia : tranfereLoteDAO
								.listarTodos()) {
							
							dtmDados.setRowCount(dtmDados.getRowCount() + 1);
							dtmDados.setValueAt(transferencia.getCodigo(),
									linha, 0);
							dtmDados.setValueAt(transferencia.getLoteOrigem()
									.getNumero(), linha, 1);
							dtmDados.setValueAt(transferencia.getLoteDestino()
									.getNumero(), linha, 2);
							dtmDados.setValueAt(transferencia.getData(), linha,
									3);
							dtmDados.setValueAt(transferencia.getQuantidade(),
									linha, 4);
							dtmDados.setValueAt(transferencia.getPesoTotal(),
									linha, 5);

							linha++;
						}

					}
					if (aux == 1) {
						JOptionPane
								.showMessageDialog(null,
										"Já existe este lote neste destino");
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

		setTitle("Transferencia de Lotes");
		setSize(400, 380);
		setVisible(true);
		this.setResizable(false);
		setLocationRelativeTo(null);
		this.getContentPane().setBackground(Color.lightGray);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

	}

	public static void main(String[] args) {
		new Transferencia_Lote_Inclusao(null);
	}

}
