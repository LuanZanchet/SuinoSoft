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
import javax.swing.JTree;
import javax.swing.table.DefaultTableModel;

import br.edu.unoesc.projetofinal.dao.VacinaDAO;
import br.edu.unoesc.projetofinal.dao.factory.DaoFactory;
import br.edu.unoesc.projetofinal.model.Vacina;

public class VacinaMedicamento_Edicao extends JFrame {
	private JLabel jlbVaciMedi = new JLabel("Editar Vacina/Medicamento");
	private JLabel jlbVacina = new JLabel("Nome");
	private JTextField jtfVacinaVaciMedi = new JTextField();
	private JButton jbtCadastrarFornCli = new JButton("Editar"), jbtSair = new JButton("Sair");
	private VacinaDAO vacinaDao = DaoFactory.get().vacinaDao();
	private JTextField jtfArmazenaValor=new JTextField();
	
	private void posicionaObjeto(JComponent obj, int x, int y, int w, int h) {
		obj.setBounds(x, y, w, h);
		getContentPane().add(obj);
	}

	public void setValor(Integer posicao){
		jtfVacinaVaciMedi.setText(vacinaDao.listarTodos().get(posicao).getNome());
		jtfArmazenaValor.setText(posicao.toString());
	}
	
	public VacinaMedicamento_Edicao(final DefaultTableModel dtDados) {
		setLayout(null);

		jlbVaciMedi.setFont(new Font("Arial", Font.BOLD, 24));
		jlbVaciMedi.setForeground(Color.DARK_GRAY);
		posicionaObjeto(jlbVaciMedi, 85, 15, 500, 25);

		posicionaObjeto(jlbVacina, 110, 105, 200, 25);
		posicionaObjeto(jtfVacinaVaciMedi, 160, 105, 150, 25);
		posicionaObjeto(jbtCadastrarFornCli, 90, 260, 100, 30);
		posicionaObjeto(jbtSair, 230, 260, 80, 20);

		jbtCadastrarFornCli.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (jtfVacinaVaciMedi.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Digite o nome da Vacina/Medicamento!");
				} else {
					int aux = 0;
					for (Vacina vacina : vacinaDao.listarTodos()) {
						if (vacina.getNome().equals(jtfVacinaVaciMedi.getText())) {
							aux = 1;
						}
					}
					if (aux == 0) {
						Vacina vacina = new Vacina();
						vacina=vacinaDao.listarTodos().get(Integer.valueOf(jtfArmazenaValor.getText()));
						vacina.setNome(jtfVacinaVaciMedi.getText());
						vacinaDao.alter(vacina);
						int linha=1;
						dtDados.setRowCount(1);
						for(Vacina vacina1:vacinaDao.listarTodos()){
							dtDados.setRowCount(dtDados.getRowCount()+1);
							dtDados.setValueAt(vacina1.getCodigo(), linha, 0);
							dtDados.setValueAt(vacina1.getNome(), linha, 1);
							linha++;
						}
						JOptionPane.showMessageDialog(null, "Vacina/Medicamento cadastrado com Sucesso!");
						dispose();
					}
					if (aux == 1) {
						JOptionPane.showMessageDialog(null,
								"Já Existe uma Vacina/Medicamento cadastrada com esse nome!");
					}
				}
			}
		});

		jbtSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});

		setTitle("Vacina Medicamento");
		setSize(400, 350);
		setVisible(true);
		this.setResizable(false);
		setLocationRelativeTo(null);
		this.getContentPane().setBackground(Color.lightGray);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

	}
}
