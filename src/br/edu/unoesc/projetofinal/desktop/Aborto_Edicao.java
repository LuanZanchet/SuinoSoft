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

import br.edu.unoesc.projetofinal.dao.AbortoDAO;
import br.edu.unoesc.projetofinal.dao.MatrizDAO;
import br.edu.unoesc.projetofinal.dao.factory.DaoFactory;
import br.edu.unoesc.projetofinal.model.Aborto;
import br.edu.unoesc.projetofinal.model.Matriz;

public class Aborto_Edicao extends JFrame {
	private JLabel jlbAborto = new JLabel("Cadastro de Aborto ");
	private JLabel jlbMatriz = new JLabel("Matriz");
	private JLabel jlbData = new JLabel("Data do aborto");
	private JTextField jtfMatriz = new JTextField();
	private JTextField jtfData = new JTextField();
	private JButton jbtCadastrar = new JButton("Cadastrar");
	private JButton jbtSair = new JButton("Sair");
	private JTextField jtfObservacao = new JTextField();
	private JLabel jlbObservacao = new JLabel("Observação");
	private AbortoDAO abortoDao = DaoFactory.get().abortoDao();
	private Aborto aborto = new Aborto();
	private MatrizDAO matrizDao = DaoFactory.get().matrizDao();
	private JTextField jtfArmazenaValor = new JTextField();

	private void posicionaObjeto(JComponent obj, int x, int y, int w, int h) {
		obj.setBounds(x, y, w, h);
		getContentPane().add(obj);
	}

	public void setValor(Integer posicao) {
		jtfMatriz.setText(abortoDao.listarTodos().get(posicao).getMatriz().getCodigo().toString());
		jtfData.setText(abortoDao.listarTodos().get(posicao).getData().toString());
		jtfObservacao.setText(abortoDao.listarTodos().get(posicao).getObservacao());
		jtfArmazenaValor.setText(posicao.toString());
	}

	public Aborto_Edicao(final DefaultTableModel dtmDados) {
		setLayout(null);
		jlbAborto.setFont(new Font("Arial", Font.BOLD, 18));
		jlbAborto.setForeground(Color.DARK_GRAY);
		posicionaObjeto(jlbAborto, 90, 15, 500, 25);
		posicionaObjeto(jlbMatriz, 90, 75, 150, 25);
		posicionaObjeto(jtfMatriz, 130, 75, 200, 25);
		posicionaObjeto(jlbData, 40, 105, 110, 25);
		posicionaObjeto(jtfData, 130, 105, 200, 25);
		posicionaObjeto(jlbObservacao, 50, 135, 100, 25);
		posicionaObjeto(jtfObservacao, 130, 135, 200, 25);
		posicionaObjeto(jbtCadastrar, 50, 195, 150, 35);
		posicionaObjeto(jbtSair, 220, 195, 120, 20);

		jtfMatriz.setEditable(false);

		jtfMatriz.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent arg0) {
				if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
					int auxiliar = 0;
					for (Matriz matriz : matrizDao.listarTodos()) {
						if (matriz.getBrinco().equals(Long.valueOf(jtfMatriz.getText()))) {
							auxiliar = 2;
							if (matriz.getStatus().equals("Gestante")) {
								auxiliar = 1;
								aborto.setMatriz(matriz);
								break;
							}
						}
					}
					if (auxiliar == 0) {
						JOptionPane.showMessageDialog(null, "Nenhuma matriz encontrada");
						jtfMatriz.setText(null);
					}
					if (auxiliar == 2) {
						JOptionPane.showMessageDialog(null, "Matriz não gestante");
						jtfMatriz.setText(null);
					}
					if (auxiliar == 1) {
						jtfData.requestFocus();
					}
				}
			}
		});
		jbtCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (jtfData.getText().isEmpty() || jtfObservacao.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Existem campos deixados em branco no formulário");
				} else {
					Aborto aborto = new Aborto();
					aborto = abortoDao.listarTodos().get(Integer.valueOf(jtfArmazenaValor.getText()));
					aborto.getMatriz().setStatus("Vazia");
					matrizDao.alter(aborto.getMatriz());
					aborto.setData(Date.valueOf(jtfData.getText()));
					aborto.setObservacao(jtfObservacao.getText());
					abortoDao.alter(aborto);
					JOptionPane.showMessageDialog(null, "Aborto Editado com Sucesso");
					dtmDados.setRowCount(1);
					int linha = 1;
					for (Aborto aborto1 : abortoDao.listarTodos()) {
						dtmDados.setRowCount(dtmDados.getRowCount() + 1);
						dtmDados.setValueAt(aborto1.getMatriz().getBrinco(), linha, 0);
						dtmDados.setValueAt(aborto1.getData(), linha, 1);
						dtmDados.setValueAt(aborto1.getObservacao(), linha, 2);
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
		setTitle("Aborto Inclusao");
		setSize(420, 300);
		setVisible(true);
		this.setResizable(false);
		setLocationRelativeTo(null);
		this.getContentPane().setBackground(Color.lightGray);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
}
