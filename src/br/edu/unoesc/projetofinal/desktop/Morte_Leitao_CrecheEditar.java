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

import br.edu.unoesc.projetofinal.dao.CausaDAO;
import br.edu.unoesc.projetofinal.dao.LoteDAO;
import br.edu.unoesc.projetofinal.dao.MatrizDAO;
import br.edu.unoesc.projetofinal.dao.MorteLeitaoCrecheDAO;
import br.edu.unoesc.projetofinal.dao.factory.DaoFactory;
import br.edu.unoesc.projetofinal.model.Causa;
import br.edu.unoesc.projetofinal.model.CompraRacao;
import br.edu.unoesc.projetofinal.model.Fornecedor;
import br.edu.unoesc.projetofinal.model.Lote;
import br.edu.unoesc.projetofinal.model.Matriz;
import br.edu.unoesc.projetofinal.model.MorteLeitaoCreche;
import br.edu.unoesc.projetofinal.model.Nota;
import br.edu.unoesc.projetofinal.model.Racao;

public class Morte_Leitao_CrecheEditar extends JFrame {

	private JLabel jlbMorte = new JLabel("Morte de Leitão Creche ");
	private JLabel jlbLote = new JLabel("Lote ");
	private JLabel jlbCausa = new JLabel("Causa ");
	private JLabel jlbData = new JLabel("Data ");
	private JLabel jlbQuantidadeLeitao = new JLabel("Quantidade");
	private JComboBox<String> jcbLote = new JComboBox<String>();
	private JComboBox<String> jcbCausa = new JComboBox<String>();
	private JTextField jtfData = new JTextField();
	private JTextField jtfQuantidadeLeitao = new JTextField();
	private JButton jbtCadastrar = new JButton("Cadastrar");
	private JButton jbtSair = new JButton("Sair");

	// /////////////////////////////

	private CausaDAO causaDAO = DaoFactory.get().causaDao();
	private MorteLeitaoCrecheDAO morteleitaocrecheDAO = DaoFactory.get()
			.morteLeitaoCrecheDaO();
	private LoteDAO loteDAO = DaoFactory.get().loteDao();
	
	private JTextField jtfGuardaValorCompraR = new JTextField();
	
	public void setValor(Integer posicao) {
		jtfGuardaValorCompraR.setText(posicao.toString());
		jtfData.setText(morteleitaocrecheDAO.listarTodos().get(posicao).getData().toString());
		jtfQuantidadeLeitao.setText(morteleitaocrecheDAO.listarTodos().get(posicao).getQuantidade().toString());
		for(Causa causa: causaDAO.listarTodos()){
			if(causa.getNome().equals(morteleitaocrecheDAO.listarTodos().get(posicao).getCausa().getNome())){
				jcbCausa.setSelectedItem(causa.getNome());
			}
		}
		for(Lote lote: loteDAO.listarTodos()){
			if(lote.getNumero().equals(morteleitaocrecheDAO.listarTodos().get(posicao).getLote().getNumero())){
				jcbLote.setSelectedItem(lote.getNumero());
			}
		}
		
		jtfGuardaValorCompraR.setText(posicao.toString());
	}

	// ///////////////////////

	private void posicionaObjeto(JComponent obj, int x, int y, int w, int h) {
		obj.setBounds(x, y, w, h);
		getContentPane().add(obj);
	}

	public Morte_Leitao_CrecheEditar(final DefaultTableModel dtmDados) {
		setLayout(null);

		jlbMorte.setFont(new Font("Arial", Font.BOLD, 18));
		jlbMorte.setForeground(Color.DARK_GRAY);
		posicionaObjeto(jlbMorte, 90, 15, 500, 25);

		posicionaObjeto(jlbLote, 85, 75, 150, 25);
		posicionaObjeto(jcbLote, 130, 75, 200, 25);
		posicionaObjeto(jlbData, 95, 105, 110, 25);
		posicionaObjeto(jtfData, 130, 105, 200, 25);
		posicionaObjeto(jlbQuantidadeLeitao, 55, 135, 200, 25);
		posicionaObjeto(jtfQuantidadeLeitao, 130, 135, 200, 25);
		posicionaObjeto(jlbCausa, 85, 165, 200, 25);
		posicionaObjeto(jcbCausa, 130, 165, 200, 25);
		posicionaObjeto(jbtCadastrar, 30, 225, 150, 35);
		posicionaObjeto(jbtSair, 200, 225, 120, 20);

		jbtSair.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				dispose();

			}
		});

		// ////////////////////////////

		for (Causa causa : causaDAO.listarTodos()) {
			jcbCausa.addItem(causa.getNome());

		}

		for (Lote lote : loteDAO.listarTodos()) {
			jcbLote.addItem(String.valueOf(lote.getNumero()));

		}

		jbtCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (jtfData.getText().isEmpty()
						|| jtfQuantidadeLeitao.getText().isEmpty()) {
					JOptionPane
							.showMessageDialog(null,
									"Existem informações não preenchidas no formulário");
				} else {
					int aux = 0;
					for (MorteLeitaoCreche morteLeitao : morteleitaocrecheDAO.listarTodos()) {
						if (morteLeitao.getCausa().equals(jcbCausa.getSelectedItem())) {
							aux = 1;
							break;
						}

					}
					if (aux == 0) {
						
						MorteLeitaoCreche morteLeitao = new MorteLeitaoCreche();
						morteLeitao=morteleitaocrecheDAO.listarTodos().get(Integer.valueOf(jtfGuardaValorCompraR.getText()));
						morteLeitao.setQuantidade(Integer.valueOf(jtfQuantidadeLeitao.getText()));
						morteLeitao.setData(Date.valueOf(jtfData.getText()));
						morteLeitao.setCausa(causaDAO.listarTodos().get(jcbCausa.getSelectedIndex()));
						morteLeitao.setLote(loteDAO.listarTodos().get(jcbLote.getSelectedIndex()));

						morteleitaocrecheDAO.alter(morteLeitao);
						JOptionPane.showMessageDialog(null,"Morte de Leitão Alterada com Sucesso");
						dtmDados.setRowCount(1);
						int linha = 1;
						for (MorteLeitaoCreche morteLeitao2 : morteleitaocrecheDAO.listarTodos()) {
							dtmDados.setRowCount(dtmDados.getRowCount() + 1);
							dtmDados.setValueAt(morteLeitao2.getCodigo(),linha, 0);
							dtmDados.setValueAt(morteLeitao2.getLote().getNumero(), linha, 1);
							dtmDados.setValueAt(morteLeitao2.getData(), linha,2);
							dtmDados.setValueAt(morteLeitao2.getQuantidade(),linha, 3);
							dtmDados.setValueAt(morteLeitao2.getCausa().getNome(), linha, 4);
							

							linha++;
						}

					}
					if (aux == 1) {
						JOptionPane
								.showMessageDialog(null,
										"Já existe uma compra de ração cadastrada com essa nota");
					}
					dispose();
				}
			}
		});

		// ///////////////////////////

		setTitle("Morte de Leitão Creche");
		setSize(420, 330);
		setVisible(true);
		this.setResizable(false);
		setLocationRelativeTo(null);
		this.getContentPane().setBackground(Color.lightGray);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	public static void main(String[] args) {
		new Morte_Leitao_CrecheEditar(null);

	}

}
