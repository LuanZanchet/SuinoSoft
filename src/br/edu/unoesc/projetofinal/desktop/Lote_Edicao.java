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
import javax.swing.table.DefaultTableModel;

import br.edu.unoesc.projetofinal.dao.LoteDAO;
import br.edu.unoesc.projetofinal.dao.factory.DaoFactory;
import br.edu.unoesc.projetofinal.model.Lote;

public class Lote_Edicao extends JFrame {

	private JLabel jlbLote = new JLabel("Editar Lote");
	private JLabel jlbNumero = new JLabel("Número");
	private JLabel jlbObservacao = new JLabel("Observação");
	private JTextField jtfQuantidadeLeitao = new JTextField();
	private JTextField jtfIdadeLeitaoLote = new JTextField();
	private JButton jbtCadastrarFornCli = new JButton("Editar"), jbtSair = new JButton("Sair");
	private LoteDAO loteDao=DaoFactory.get().loteDao();
	private JTextField jtfGuardaValor=new JTextField();
	
	private void posicionaObjeto(JComponent obj, int x, int y, int w, int h) {
		obj.setBounds(x, y, w, h);
		getContentPane().add(obj);
	}
	
	public void setValor(Integer posicao){
		jtfQuantidadeLeitao.setText(loteDao.listarTodos().get(posicao).getNumero().toString());
		jtfIdadeLeitaoLote.setText(loteDao.listarTodos().get(posicao).getObservacao());
		jtfGuardaValor.setText(posicao.toString());
	}
	
	public Lote_Edicao(final DefaultTableModel dtmDados) {
		setLayout(null);

		jlbLote.setFont(new Font("Arial", Font.BOLD, 24));
		jlbLote.setForeground(Color.DARK_GRAY);

		posicionaObjeto(jlbLote, 135, 45, 500, 25);
		posicionaObjeto(jlbNumero, 85, 105, 150, 25);
		posicionaObjeto(jlbObservacao, 60, 145, 100, 25);
		posicionaObjeto(jtfQuantidadeLeitao, 150, 105, 150, 25);
		posicionaObjeto(jtfIdadeLeitaoLote, 150, 145, 150, 25);
		posicionaObjeto(jbtCadastrarFornCli, 90, 245, 100, 30);
		posicionaObjeto(jbtSair, 230, 245, 80, 20);
		
		jbtCadastrarFornCli.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(jtfIdadeLeitaoLote.getText().isEmpty()||jtfQuantidadeLeitao.getText().isEmpty()){
					if(jtfIdadeLeitaoLote.getText().isEmpty()){
						JOptionPane.showMessageDialog(null, "Insira sua observação!");
					}
					if(jtfQuantidadeLeitao.getText().isEmpty()){
						JOptionPane.showMessageDialog(null, "Insira o número do Lote para cadastrar!");
					}
				}
				else{
					int aux=0;
					for(Lote lote:loteDao.listarTodos()){
						if(lote.getNumero().equals(jtfIdadeLeitaoLote)){
							aux=1;
							break;
						}
					}
					if(aux==0){
						Lote lote=new Lote();
						lote=loteDao.listarTodos().get(Integer.valueOf(jtfGuardaValor.getText()));
						lote.setNumero(Long.valueOf(jtfQuantidadeLeitao.getText()));
						lote.setObservacao(jtfIdadeLeitaoLote.getText());
						loteDao.alter(lote);
						int linha=1;
						dtmDados.setRowCount(1);
						for(Lote lote1:loteDao.listarTodos()){
							dtmDados.setRowCount(dtmDados.getRowCount()+1);
							dtmDados.setValueAt(lote1.getCodigo(), linha, 0);
							dtmDados.setValueAt(lote1.getNumero(), linha, 1);
							dtmDados.setValueAt(lote1.getQuantidadeLeitao(), linha, 2);
							dtmDados.setValueAt(lote1.getIdade(), linha, 3);
							dtmDados.setValueAt(lote1.getObservacao(), linha, 4);
							linha++;
						}
						JOptionPane.showMessageDialog(null, "Lote Editado com Sucesso!");
						dispose();
					}
					if(aux==1){
						JOptionPane.showMessageDialog(null, "Já existe um Lote cadastrado com esse Número");
					}
				}
			}
		});
		
		jbtSair.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				dispose();

			}
		});

		setTitle("Novo Lote");
		setSize(400, 335);
		setVisible(true);
		this.setResizable(false);
		setLocationRelativeTo(null);
		this.getContentPane().setBackground(Color.lightGray);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

	}
}
