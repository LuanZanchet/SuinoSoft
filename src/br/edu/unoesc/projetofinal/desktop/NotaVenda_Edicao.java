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

import br.edu.unoesc.projetofinal.dao.CompradorDAO;
import br.edu.unoesc.projetofinal.dao.NotaVendaDAO;
import br.edu.unoesc.projetofinal.dao.factory.DaoFactory;
import br.edu.unoesc.projetofinal.model.Comprador;
import br.edu.unoesc.projetofinal.model.NotaVenda;

public class NotaVenda_Edicao extends JFrame {

	private JLabel jlbNotaCompra = new JLabel("Nota da Venda");
	private JLabel jlbDataNota = new JLabel("Data da venda");
	private JLabel jlbValor = new JLabel("Valor");
	private JLabel jlbNumero = new JLabel("Número");
	private JTextField jtfNumero = new JTextField();
	private JLabel jlbFornecedor = new JLabel("Comprador");
	private JLabel jlbFormaPagamento = new JLabel("Forma de Pagamento");
	private JRadioButton jrbVista = new JRadioButton("À Vista"), jrbPrazo = new JRadioButton("À Prazo");
	private JTextField jtfValor = new JTextField();
	private JComboBox<String> jcbFornecedor = new JComboBox<>();
	private JTextField jtfDataNota = new JTextField();
	private ButtonGroup btgEscolha = new ButtonGroup();
	private JButton jbtCadastrarFornCli = new JButton("Alterar"), jbtSair = new JButton("Sair");
	private CompradorDAO compradorDAO = DaoFactory.get().compradorDao();
	private NotaVendaDAO notaDao = DaoFactory.get().notaVendaDao();
	private JTextField jtfGuardaValor=new JTextField();

	private void posicionaObjeto(JComponent obj, int x, int y, int w, int h) {
		obj.setBounds(x, y, w, h);
		getContentPane().add(obj);
	}
	
	public void setValor(Integer posicao){
		jtfNumero.setText(notaDao.listarTodos().get(posicao).getNumero().toString());
		jtfDataNota.setText(notaDao.listarTodos().get(posicao).getData().toString());
		jtfValor.setText(notaDao.listarTodos().get(posicao).getValor().toString());
		if(notaDao.listarTodos().get(posicao).getFormaPagamento().equals("À Vista")){
			jrbVista.setSelected(true);
		}
		if(notaDao.listarTodos().get(posicao).getFormaPagamento().equals("À Prazo")){
			jrbPrazo.setSelected(true);
		}
		jcbFornecedor.setSelectedItem(notaDao.listarTodos().get(posicao).getComprador().getNome());
		jtfGuardaValor.setText(posicao.toString());
	}
	
	public NotaVenda_Edicao(final DefaultTableModel dtmDados) {
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

		for (Comprador comprador : compradorDAO.listarTodos()) {
			jcbFornecedor.addItem(comprador.getNome());
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
					for (NotaVenda nota : notaDao.listarTodos()) {
						if (nota.getNumero().equals(Long.valueOf(jtfNumero.getText()))) {
							aux = 1;
							if(nota.getNumero().equals(notaDao.listarTodos().get(Integer.valueOf(jtfGuardaValor.getText())).getNumero())){
								aux=0;
							}
							break;
						}
					}
					if (aux == 0) {
						NotaVenda nota = new NotaVenda();
						nota=notaDao.listarTodos().get(Integer.valueOf(jtfGuardaValor.getText()));
						nota.setNumero(Long.valueOf(jtfNumero.getText()));
						nota.setData(Date.valueOf(jtfDataNota.getText()));
						nota.setComprador(compradorDAO.listarTodos().get(jcbFornecedor.getSelectedIndex()));
						nota.setValor(Double.valueOf(jtfValor.getText()));
						if(jrbPrazo.isSelected()){
							nota.setFormaPagamento("À Prazo");
						}
						if(jrbVista.isSelected()){
							nota.setFormaPagamento("À Vista");
						}
						notaDao.alter(nota);
						JOptionPane.showMessageDialog(null, "Nota editada com Sucesso");
						dtmDados.setRowCount(1);
						int linha=1;
						for(NotaVenda nota1:notaDao.listarTodos()){
							dtmDados.setRowCount(dtmDados.getRowCount()+1);
							dtmDados.setValueAt(nota1.getCodigo(), linha, 0);
							dtmDados.setValueAt(nota1.getNumero(), linha, 1);
							dtmDados.setValueAt(nota1.getData(), linha, 2);
							dtmDados.setValueAt(nota1.getComprador().getNome(), linha, 3);
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

		setTitle("Nota de Compra Edição");
		setSize(400, 415);
		setVisible(true);
		this.setResizable(false);
		setLocationRelativeTo(null);
		this.getContentPane().setBackground(Color.lightGray);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

	}
}