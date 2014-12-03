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

import br.edu.unoesc.projetofinal.dao.TarefaDAO;
import br.edu.unoesc.projetofinal.dao.factory.DaoFactory;
import br.edu.unoesc.projetofinal.model.Tarefa;

public class TarefasGranja_Inclusao extends JFrame {

	private JLabel jlbForneCli = new JLabel("Tarefa da Granja");
	private JLabel jlbDataTarefa = new JLabel("Data da Tarefa");
	private JLabel jlbDescracao = new JLabel("Descrição");
	private JTextField jtfDataTarefa = new JTextField();
	private JTextField jtfDescricao = new JTextField();
	private TarefaDAO tarefaDao = DaoFactory.get().tareDao();
	private JButton jbtCadastrarFornCli = new JButton("Cadastrar"), jbtSair = new JButton("Sair");

	private void posicionaObjeto(JComponent obj, int x, int y, int w, int h) {
		obj.setBounds(x, y, w, h);
		getContentPane().add(obj);
	}

	TarefasGranja_Inclusao(final DefaultTableModel dtmDados) {
		setLayout(null);

		jlbForneCli.setFont(new Font("Arial", Font.BOLD, 24));
		jlbForneCli.setForeground(Color.DARK_GRAY);

		posicionaObjeto(jlbForneCli, 100, 15, 500, 25);
		posicionaObjeto(jlbDataTarefa, 60, 70, 200, 25);
		posicionaObjeto(jlbDescracao, 80, 125, 500, 25);
		posicionaObjeto(jtfDataTarefa, 150, 70, 200, 25);
		posicionaObjeto(jtfDescricao, 150, 125, 200, 25);
		posicionaObjeto(jbtCadastrarFornCli, 90, 205, 100, 30);
		posicionaObjeto(jbtSair, 230, 205, 80, 20);
		
		jbtCadastrarFornCli.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(jtfDataTarefa.getText().isEmpty()||jtfDescricao.getText().isEmpty()){
					JOptionPane.showMessageDialog(null, "Digite todas as informações requeridas para cadastrar a tarefa");
				}
				else{
					Tarefa tarefa=new Tarefa();
					tarefa.setDataTarefa(Date.valueOf(jtfDataTarefa.getText()));
					tarefa.setDescricao(jtfDescricao.getText());
					tarefaDao.store(tarefa);
					dtmDados.setRowCount(1);
					int linha=1;
					linha=1;
					for(Tarefa tarefa1:tarefaDao.listarTodos()){
						dtmDados.setRowCount(dtmDados.getRowCount()+1);
						dtmDados.setValueAt(tarefa1.getCodigo(), linha, 0);
						dtmDados.setValueAt(tarefa1.getDataTarefa(), linha, 1);
						dtmDados.setValueAt(tarefa1.getDescricao(), linha, 2);
						linha++;
					}
					JOptionPane.showMessageDialog(null, "Tarefa Cadastrada com Sucesso");
					dispose();
				}
			}
		});
		
		jbtSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});

		setTitle("Tarefa Granja");
		setSize(400, 295);
		setVisible(true);
		this.setResizable(false);
		setLocationRelativeTo(null);
		this.getContentPane().setBackground(Color.lightGray);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
}