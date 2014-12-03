package br.edu.unoesc.projetofinal.desktop;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
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
import br.edu.unoesc.projetofinal.dao.LactacaoDAO;
import br.edu.unoesc.projetofinal.dao.MatrizDAO;
import br.edu.unoesc.projetofinal.dao.MorteMaternidadeDAO;
import br.edu.unoesc.projetofinal.dao.factory.DaoFactory;
import br.edu.unoesc.projetofinal.model.Causa;
import br.edu.unoesc.projetofinal.model.Lactacao;
import br.edu.unoesc.projetofinal.model.Matriz;
import br.edu.unoesc.projetofinal.model.MorteLeitaoMaternidade;

public class Morte_Leitao_Maternidade extends JFrame {

	private JLabel jlbMorte = new JLabel("Morte de Leitão Maternidade ");
	private JLabel jlbMatriz = new JLabel("Matriz ");
	private JLabel jlbCausa = new JLabel("Causa ");
	private JLabel jlbData = new JLabel("Data ");
	private JLabel jlbQuantidadeLeitao = new JLabel("Quantidade");
	private JTextField jtfMatriz = new JTextField();
	private JComboBox<String> jcbCausa = new JComboBox<String>();
	private JTextField jtfData = new JTextField();
	private JTextField jtfQuantidadeLeitao = new JTextField();
	private JButton jbtCadastrar = new JButton("Cadastrar");
	private JButton jbtSair = new JButton("Sair");
	private CausaDAO causaDao=DaoFactory.get().causaDao();
	private MorteLeitaoMaternidade morte=new MorteLeitaoMaternidade();
	private MorteMaternidadeDAO morteDao=DaoFactory.get().morteMaternidadeDao();
	private MatrizDAO matrizDao=DaoFactory.get().matrizDao();
	private LactacaoDAO lactacaoDao=DaoFactory.get().lactacaoDao();

	private void posicionaObjeto(JComponent obj, int x, int y, int w, int h) {
		obj.setBounds(x, y, w, h);
		getContentPane().add(obj);
	}

	public Morte_Leitao_Maternidade(final DefaultTableModel dtmDados) {
		setLayout(null);

		jlbMorte.setFont(new Font("Arial", Font.BOLD, 18));
		jlbMorte.setForeground(Color.DARK_GRAY);

		posicionaObjeto(jlbMorte, 90, 15, 500, 25);
		posicionaObjeto(jlbMatriz, 85, 75, 150, 25);
		posicionaObjeto(jtfMatriz, 130, 75, 200, 25);
		posicionaObjeto(jlbData, 95, 105, 110, 25);
		posicionaObjeto(jtfData, 130, 105, 200, 25);
		posicionaObjeto(jlbQuantidadeLeitao, 55, 135, 200, 25);
		posicionaObjeto(jtfQuantidadeLeitao, 130, 135, 200, 25);
		posicionaObjeto(jlbCausa, 85, 165, 200, 25);
		posicionaObjeto(jcbCausa, 130, 165, 200, 25);
		posicionaObjeto(jbtCadastrar, 30, 225, 150, 35);
		posicionaObjeto(jbtSair, 200, 225, 120, 20);
		
		for(Causa causa:causaDao.listarTodos()){
			jcbCausa.addItem(causa.getNome());
		}
		
		jtfMatriz.addKeyListener(new KeyAdapter() {
	
			public void keyPressed(KeyEvent arg0) {
				if(arg0.getKeyCode()==KeyEvent.VK_ENTER){
					int aux=0;
					for(Matriz matriz:matrizDao.listarTodos()){
						if(matriz.getBrinco().equals(Long.valueOf(jtfMatriz.getText()))){
							aux=2;
							if(matriz.getStatus().equals("Lactante")){
								aux=1;
								morte.setMatriz(matriz);
								break;
							}
						}
					}
					if(aux==0){
						JOptionPane.showMessageDialog(null, "Nenhuma matriz encontrada");
						jtfMatriz.setText(null);
					}
					if(aux==2){
						JOptionPane.showMessageDialog(null, "Matriz Não Lactante");
						jtfMatriz.setText(null);
					}
					if(aux==1){
						jtfData.requestFocus();
					}
				}
			}
		});
		
		jbtCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(jtfData.getText().isEmpty()||jtfQuantidadeLeitao.getText().isEmpty()){
					JOptionPane.showMessageDialog(null, "Existem campos deixados em branco no formulário");
				}
				else{
					morte.setData(Date.valueOf(jtfData.getText()));
					morte.setQuantidade(Integer.valueOf(jtfQuantidadeLeitao.getText()));
					morte.setCausa(causaDao.listarTodos().get(jcbCausa.getSelectedIndex()));
					Lactacao lactacao=new Lactacao();
					lactacao=morte.getMatriz().getLactacoes().get(morte.getMatriz().getLactacoes().size()-1);
					lactacao.setQuantMortos(lactacao.getQuantMortos()+Integer.valueOf(jtfQuantidadeLeitao.getText()));
					lactacao.setQuantAtual(lactacao.getQuantAtual()-Integer.valueOf(jtfQuantidadeLeitao.getText()));
					lactacaoDao.alter(lactacao);
					morteDao.store(morte);
					JOptionPane.showMessageDialog(null, "Morte cadastrada com Sucesso");
					dtmDados.setRowCount(1);
					int linha=1;
					for(MorteLeitaoMaternidade morte1:morteDao.listarTodos()){
						dtmDados.setRowCount(dtmDados.getRowCount()+1);
						dtmDados.setValueAt(morte1.getMatriz().getBrinco(), linha, 0);
						dtmDados.setValueAt(morte1.getData(), linha, 1);
						dtmDados.setValueAt(morte1.getQuantidade(), linha, 2);
						dtmDados.setValueAt(morte1.getCausa().getNome(), linha, 3);
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

		setTitle("Morte de Leitão Maternidade");
		setSize(420, 330);
		setVisible(true);
		this.setResizable(false);
		setLocationRelativeTo(null);
		this.getContentPane().setBackground(Color.lightGray);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
}