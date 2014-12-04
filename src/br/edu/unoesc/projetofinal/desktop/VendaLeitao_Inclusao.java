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

import br.edu.unoesc.projetofinal.dao.CompradorDAO;
import br.edu.unoesc.projetofinal.dao.LoteDAO;
import br.edu.unoesc.projetofinal.dao.NotaVendaDAO;
import br.edu.unoesc.projetofinal.dao.VendaLeitaoDAO;
import br.edu.unoesc.projetofinal.dao.factory.DaoFactory;
import br.edu.unoesc.projetofinal.model.Comprador;
import br.edu.unoesc.projetofinal.model.Lote;
import br.edu.unoesc.projetofinal.model.Nota;
import br.edu.unoesc.projetofinal.model.VendaLeitao;

public class VendaLeitao_Inclusao extends JFrame {

	private JLabel jlbVendaLeitao = new JLabel("Venda Leitão");
	private JLabel jlbComprador = new JLabel("Comprador ");
	private JLabel jlbValor = new JLabel("Valor");
	private JLabel jlbData = new JLabel("Data ");
	private JLabel jlbQuantidade = new JLabel("Quantidade");
	private JLabel jlbPesoTotal = new JLabel("Peso Total ");
	private JLabel jlbPesoMedio = new JLabel("Peso Medio ");
	private JLabel jlbLote = new JLabel("Lote ");
	private JLabel jlbNota = new JLabel("Nota ");
	private JLabel jlbGta = new JLabel("Gta ");
	private JComboBox<Long> jcbLote = new JComboBox<>();
	private JComboBox<String> jcbComprador = new JComboBox<>();
	private JTextField jtfValor = new JTextField();
	private JTextField jtfPesoMedio = new JTextField();
	private JComboBox<Long> jcbNota = new JComboBox<>();
	private JTextField jtfGta = new JTextField();
	private JTextField jtfData = new JTextField();
	private JTextField jtfQuantidade = new JTextField();
	private JTextField jtfPesoTotal = new JTextField();
	private JButton jbtCadastrar = new JButton("Cadastrar");
	private JButton jbtSair = new JButton("Sair");

	private NotaVendaDAO notavendaDao = DaoFactory.get().notaVendaDao();
	private LoteDAO loteDAO = DaoFactory.get().loteDao();
	private CompradorDAO compradorDAO = DaoFactory.get().compradorDao();
	private VendaLeitaoDAO vendaleitaoDAO = DaoFactory.get().vendaLeitaoDao();

	private void posicionaObjeto(JComponent obj, int x, int y, int w, int h) {
		obj.setBounds(x, y, w, h);
		getContentPane().add(obj);
	}

	public VendaLeitao_Inclusao(final DefaultTableModel dtmDados) {
		setLayout(null);

		jlbVendaLeitao.setFont(new Font("Arial", Font.BOLD, 18));
		jlbVendaLeitao.setForeground(Color.DARK_GRAY);
		posicionaObjeto(jlbVendaLeitao, 140, 15, 500, 25);

		posicionaObjeto(jlbComprador, 55, 75, 120, 25);
		posicionaObjeto(jcbComprador, 130, 75, 200, 25);
		posicionaObjeto(jlbValor, 90, 105, 110, 25);
		posicionaObjeto(jtfValor, 130, 105, 200, 25);
		posicionaObjeto(jlbData, 95, 135, 110, 25);
		posicionaObjeto(jtfData, 130, 135, 200, 25);
		posicionaObjeto(jlbQuantidade, 55, 165, 200, 25);
		posicionaObjeto(jtfQuantidade, 130, 165, 200, 25);
		posicionaObjeto(jlbPesoMedio, 55, 195, 200, 25);
		posicionaObjeto(jtfPesoMedio, 130, 195, 200, 25);
		posicionaObjeto(jlbPesoTotal, 60, 225, 200, 25);
		posicionaObjeto(jtfPesoTotal, 130, 225, 200, 25);
		posicionaObjeto(jlbLote, 95, 255, 200, 25);
		posicionaObjeto(jcbLote, 130, 255, 200, 25);
		posicionaObjeto(jlbGta, 100, 285, 200, 25);
		posicionaObjeto(jtfGta, 130, 285, 200, 25);
		posicionaObjeto(jlbNota, 95, 315, 200, 25);
		posicionaObjeto(jcbNota, 130, 315, 200, 25);
		posicionaObjeto(jbtCadastrar, 30, 375, 150, 35);
		posicionaObjeto(jbtSair, 200, 375, 120, 20);

		jbtSair.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				dispose();

			}
		});

		for (Nota nota : notavendaDao.listarTodos()) {
			jcbNota.addItem(nota.getNumero());
		}
		for (Comprador comprador : compradorDAO.listarTodos()) {
			jcbComprador.addItem(comprador.getNome());
		}
		for (Lote lote : loteDAO.listarTodos()) {
			jcbLote.addItem(lote.getNumero());
		}

		jbtCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (jtfData.getText().isEmpty() || jtfGta.getText().isEmpty() || jtfPesoMedio.getText().isEmpty()
						|| jtfPesoTotal.getText().isEmpty() || jtfQuantidade.getText().isEmpty()
						|| jtfValor.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Existem informações não preenchidas no formulário");
				} else {
					int aux = 0;
					for (VendaLeitao vendaLeitao : vendaleitaoDAO.listarTodos()) {
						if (vendaLeitao.getNota().equals(jcbNota.getSelectedItem())) {
							aux = 1;
							break;
						}

					}
					if (aux == 0) {
						VendaLeitao venda = new VendaLeitao();
						venda.setQuantidade(Integer.valueOf(jtfQuantidade.getText()));
						venda.setGta(Long.valueOf(jtfGta.getText()));
						venda.setLote(loteDAO.listarTodos().get(jcbLote.getSelectedIndex()));
						venda.setPesoMedio(Double.valueOf(jtfPesoMedio.getText()));
						venda.setPesoTotal(Double.valueOf(jtfPesoTotal.getText()));
						venda.setValor(Double.valueOf(jtfValor.getText()));
						venda.setComprador(compradorDAO.listarTodos().get(jcbComprador.getSelectedIndex()));
						venda.setNota(notavendaDao.listarTodos().get(jcbNota.getSelectedIndex()));
						venda.setData(Date.valueOf(jtfData.getText()));

						vendaleitaoDAO.store(venda);
						JOptionPane.showMessageDialog(null, "Venda Leitão Cadastrada com Sucesso");
						dtmDados.setRowCount(1);
						int linha = 1;
						for (VendaLeitao vendaLeitao : vendaleitaoDAO.listarTodos()) {
							dtmDados.setRowCount(dtmDados.getRowCount() + 1);
							dtmDados.setValueAt(vendaLeitao.getCodigo(), linha, 0);
							dtmDados.setValueAt(vendaLeitao.getLote().getNumero(), linha, 1);
							dtmDados.setValueAt(vendaLeitao.getValor(), linha, 2);
							dtmDados.setValueAt(vendaLeitao.getData(), linha, 3);
							dtmDados.setValueAt(vendaLeitao.getQuantidade(), linha, 4);
							dtmDados.setValueAt(vendaLeitao.getPesoMedio(), linha, 5);
							dtmDados.setValueAt(vendaLeitao.getPesoTotal(), linha, 6);
							dtmDados.setValueAt(vendaLeitao.getLote().getNumero(), linha, 7);
							dtmDados.setValueAt(vendaLeitao.getGta(), linha, 8);
							dtmDados.setValueAt(vendaLeitao.getNota().getNumero(), linha, 9);
							dtmDados.setValueAt(vendaLeitao.getComprador().getNome(), linha, 10);

							linha++;
						}

					}
					if (aux == 1) {
						JOptionPane.showMessageDialog(null, "Já existe este lote neste destino");
					}
					dispose();
				}
			}
		});

		setTitle("Venda Leitao");
		setSize(420, 500);
		setVisible(true);
		this.setResizable(false);
		setLocationRelativeTo(null);
		this.getContentPane().setBackground(Color.lightGray);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
}