package br.edu.unoesc.projetofinal.desktop;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Date;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import br.edu.unoesc.projetofinal.dao.MatrizDAO;
import br.edu.unoesc.projetofinal.dao.RepeticaoCioDAO;
import br.edu.unoesc.projetofinal.dao.factory.DaoFactory;
import br.edu.unoesc.projetofinal.model.Aborto;
import br.edu.unoesc.projetofinal.model.Matriz;
import br.edu.unoesc.projetofinal.model.RepeticaoCio;
public class RepeticaoDoCio_Edicao extends JFrame{
	private JLabel jlbObs = new JLabel("Só é necessario cadastrar Repetição de cio se ");
	private JLabel jlbObs2 = new JLabel("a matriz ainda não foi coberta novamente.");
	private JLabel jlbMatriz = new JLabel("Matriz");
	private JLabel jlbData = new JLabel("Data da Repetição");
	private JTextField jtfMatriz=new JTextField();
	private JTextField jtfData = new JTextField();
	private JButton jbtCadastrar = new JButton("Cadastrar");
	private JButton jbtSair =  new JButton("Sair");
	private JLabel jlbObservacao=new JLabel("Observação");
	private JTextField jtfObservacao=new JTextField();
	private RepeticaoCioDAO repeticaoDao=DaoFactory.get().repeticaoCioDao();
	private RepeticaoCio repeticao=new RepeticaoCio();
	private MatrizDAO matrizDao=DaoFactory.get().matrizDao();
	
	private JTextField jtfArmazenaValor = new JTextField();
	
	private void posicionaObjeto(JComponent obj, int x, int y, int w, int h) {
		obj.setBounds(x, y, w, h);
		getContentPane().add(obj);
	}
	
	public void setValor(Integer posicao) {
		jtfMatriz.setText(repeticaoDao.listarTodos().get(posicao).getMatriz().getCodigo().toString());
		jtfData.setText(repeticaoDao.listarTodos().get(posicao).getData().toString());
		jtfObservacao.setText(repeticaoDao.listarTodos().get(posicao).getObservacao());
		jtfArmazenaValor.setText(posicao.toString());
	}
	
	
	public RepeticaoDoCio_Edicao(final DefaultTableModel dtmDados){
		setLayout(null);
		
		jlbObs.setFont(new Font("Arial", Font.BOLD, 18));
		jlbObs.setForeground(Color.DARK_GRAY);
		jlbObs2.setFont(new Font("Arial", Font.BOLD, 18));
		jlbObs2.setForeground(Color.DARK_GRAY);
		posicionaObjeto(jlbObs, 10, 15, 500, 25);
		posicionaObjeto(jlbObs2, 10, 35, 500, 25);
		posicionaObjeto(jlbMatriz, 80, 95, 150, 25);
		posicionaObjeto(jtfMatriz, 130, 95, 200, 25);
		posicionaObjeto(jlbData , 15, 125, 110, 25);
		posicionaObjeto(jtfData, 130, 125, 200, 25);
		posicionaObjeto(jlbObservacao, 50, 155, 100, 25);
		posicionaObjeto(jtfObservacao, 130, 155, 200, 25);
		posicionaObjeto(jbtCadastrar, 30, 215, 150, 35);
		posicionaObjeto(jbtSair, 200, 215, 120, 20);
		
		jtfMatriz.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent arg0) {
				if(arg0.getKeyCode()==KeyEvent.VK_ENTER){
					int aux=0;
					for(Matriz matriz:matrizDao.listarTodos()){
						if(matriz.getBrinco().equals(Long.valueOf(jtfMatriz.getText()))){
							aux=2;
							if(matriz.getStatus().equals("Gestante")){
								aux=1;
								repeticao.setMatriz(matriz);
								break;
							}
						}
					}
					if(aux==0){
						JOptionPane.showMessageDialog(null, "Nenhuma matriz encontrada");
						jtfMatriz.setText(null);
					}
					if(aux==2){
						JOptionPane.showMessageDialog(null, "Matriz não gestante");
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
				if(jtfData.getText().isEmpty()||jtfObservacao.getText().isEmpty()){
					JOptionPane.showMessageDialog(null, "Existem campos pendentes no formulário");
				}
				else{
					
					
					RepeticaoCio repeticao1 = new RepeticaoCio(); 
					repeticao1= repeticaoDao.listarTodos().get(Integer.valueOf(jtfArmazenaValor.getText()));
					repeticao1.getMatriz().setStatus("Vazia");
					matrizDao.alter(repeticao1.getMatriz());
					repeticao1.setData(Date.valueOf(jtfData.getText()));
					repeticao1.setObservacao(jtfObservacao.getText());
					repeticaoDao.alter(repeticao);
					JOptionPane.showMessageDialog(null, "Repetição de cio Editada com Sucesso");
					int linha=1;
					dtmDados.setRowCount(1);
					for(RepeticaoCio repeticao:repeticaoDao.listarTodos()){
						dtmDados.setRowCount(dtmDados.getRowCount()+1);
						dtmDados.setValueAt(repeticao.getMatriz().getBrinco(), linha, 0);
						dtmDados.setValueAt(repeticao.getData(), linha, 1);
						dtmDados.setValueAt(repeticao.getObservacao(), linha, 2);
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
		setTitle("Repetição do cio Edição");
		setSize(420, 320);
		setVisible(true);
		this.setResizable(false);
		setLocationRelativeTo(null);
		this.getContentPane().setBackground(Color.lightGray);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	
}