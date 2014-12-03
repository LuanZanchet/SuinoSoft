package br.edu.unoesc.projetofinal.desktop;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import br.edu.unoesc.projetofinal.dao.FornecedorDAO;
import br.edu.unoesc.projetofinal.dao.NotaCompraDAO;
import br.edu.unoesc.projetofinal.dao.factory.DaoFactory;
import br.edu.unoesc.projetofinal.model.Fornecedor;
import br.edu.unoesc.projetofinal.model.NotaCompra;

public class NotaCompra_Inclusao extends JFrame {

	private JLabel jlbNotaCompra = new JLabel("Nota da Compra");
	private JLabel jlbDataNota = new JLabel("Data da compra");
	private JLabel jlbValor = new JLabel("Valor");
	private JLabel jlbNumero = new JLabel("Número");
	private JTextField jtfNumero = new JTextField();
	private JLabel jlbFornecedor = new JLabel("Fornecedor");
	private JLabel jlbFormaPagamento = new JLabel("Forma de Pagamento");
	private JRadioButton jrbVista = new JRadioButton("À Vista"), jrbPrazo = new JRadioButton("À Prazo");
	private JTextField jtfValor = new JTextField();
	private JComboBox<String> jcbFornecedor = new JComboBox<>();
	private JTextField jtfDataNota = new JTextField();
	private ButtonGroup btgEscolha = new ButtonGroup();
	private JButton jbtCadastrarFornCli = new JButton("Cadastrar"), jbtSair = new JButton("Sair");
	private FornecedorDAO fornecedorDAO = DaoFactory.get().fornecedorDao();
	private NotaCompraDAO notaDao = DaoFactory.get().notaCompraDao();

	private void posicionaObjeto(JComponent obj, int x, int y, int w, int h) {
		obj.setBounds(x, y, w, h);
		getContentPane().add(obj);
	}

	public NotaCompra_Inclusao(final DefaultTableModel dtmDados) {
		setLayout(null);

		jlbNotaCompra.setFont(new Font("Arial", Font.BOLD, 24));
		jlbNotaCompra.setForeground(Color.DARK_GRAY);
		posicionaObjeto(jlbNotaCompra, 95, 45, 500, 25);
		posicionaObjeto(jlbNumero, 95, 105, 100, 25);
		posicionaObjeto(jlbDataNota, 50, 145, 100, 25);
		posicionaObjeto(jlbFornecedor, 75, 185, 100, 25);
		posicionaObjeto(jlbFormaPagamento, 20, 265, 150, 25);
		posicionaObjeto(jlbValor, 110, 225, 100, 25);
		posicionaObjeto(jtfNumero, 160, 105, 210, 25);
		posicionaObjeto(jtfDataNota, 160, 145, 210, 25);
		posicionaObjeto(jcbFornecedor, 160, 185, 210, 25);
		posicionaObjeto(jtfValor, 160, 225, 210, 25);
		posicionaObjeto(jrbVista, 160, 265, 100, 25);
		posicionaObjeto(jrbPrazo, 270, 265, 100, 25);
		posicionaObjeto(jbtCadastrarFornCli, 90, 325, 100, 30);
		posicionaObjeto(jbtSair, 230, 325, 80, 20);

		for (Fornecedor fornecedor : fornecedorDAO.listarTodos()) {
			jcbFornecedor.addItem(fornecedor.getNome());
		}

		btgEscolha.add(jrbPrazo);
		btgEscolha.add(jrbVista);

		jbtCadastrarFornCli.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (jtfNumero.getText().isEmpty() || jtfDataNota.getText().isEmpty() || jtfValor.getText().isEmpty()
						|| (!jrbPrazo.isSelected() && !jrbVista.isSelected())) {
					JOptionPane.showMessageDialog(null, "Existem informações não preenchidas no formulário");
				} else {
					int aux = 0;
					for (NotaCompra nota : notaDao.listarTodos()) {
						if (nota.getNumero().equals(Long.valueOf(jtfNumero.getText()))) {
							aux = 1;
							break;
						}
					}
					if (aux == 0) {
						NotaCompra nota = new NotaCompra();
						nota.setNumero(Long.valueOf(jtfNumero.getText()));
						nota.setData(Date.valueOf(jtfDataNota.getText()));
						nota.setFornecedor(fornecedorDAO.listarTodos().get(jcbFornecedor.getSelectedIndex()));
						nota.setValor(Double.valueOf(jtfValor.getText()));
						if(jrbPrazo.isSelected()){
							nota.setFormaPagamento("À Prazo");
						}
						if(jrbVista.isSelected()){
							nota.setFormaPagamento("À Vista");
						}
						notaDao.store(nota);
						JOptionPane.showMessageDialog(null, "Nota Cadastrada com Sucesso");
						dtmDados.setRowCount(1);
						int linha=1;
						for(NotaCompra nota1:notaDao.listarTodos()){
							dtmDados.setRowCount(dtmDados.getRowCount()+1);
							dtmDados.setValueAt(nota1.getCodigo(), linha, 0);
							dtmDados.setValueAt(nota1.getNumero(), linha, 1);
							dtmDados.setValueAt(nota1.getData(), linha, 2);
							dtmDados.setValueAt(nota1.getFornecedor().getNome(), linha, 3);
							dtmDados.setValueAt(nota1.getValor(), linha, 4);
							dtmDados.setValueAt(nota1.getFormaPagamento(), linha, 5);
							linha++;
						}
						dispose();
					}
					if (aux == 1) {
						JOptionPane.showMessageDialog(null, "Já existe uma nota cadastrada com esse número");
					}
				}
			}
		});

		jbtSair.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				dispose();

			}
		});

		setTitle("Nota de Compra");
		setSize(400, 415);
		setVisible(true);
		this.setResizable(false);
		setLocationRelativeTo(null);
		this.getContentPane().setBackground(Color.lightGray);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

	}
}