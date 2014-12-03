package br.edu.unoesc.projetofinal.desktop;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import br.edu.unoesc.projetofinal.dao.CoberturaDAO;
import br.edu.unoesc.projetofinal.dao.FuncionarioDAO;
import br.edu.unoesc.projetofinal.dao.MachoDAO;
import br.edu.unoesc.projetofinal.dao.MatrizDAO;
import br.edu.unoesc.projetofinal.dao.MontaMachoDao;
import br.edu.unoesc.projetofinal.dao.MontaSemenDAO;
import br.edu.unoesc.projetofinal.dao.SemenDAO;
import br.edu.unoesc.projetofinal.dao.factory.DaoFactory;
import br.edu.unoesc.projetofinal.model.Cobertura;
import br.edu.unoesc.projetofinal.model.Funcionario;
import br.edu.unoesc.projetofinal.model.Macho;
import br.edu.unoesc.projetofinal.model.Matriz;
import br.edu.unoesc.projetofinal.model.MontaMacho;
import br.edu.unoesc.projetofinal.model.MontaSemen;
import br.edu.unoesc.projetofinal.model.Semen;

public class Cobertura_Inclusao extends JFrame {
	private JLabel jlbMonta1 = new JLabel(" 1ª Monta");
	private JLabel jlbMonta2 = new JLabel(" 2ª Monta");
	private JLabel jlbMonta3 = new JLabel(" 3ª Monta");
	private JLabel jlbMonta4 = new JLabel(" 4ª Monta");
	private JLabel jlbMatriz = new JLabel("Matriz");
	private JLabel jlbData = new JLabel("Data");
	private JLabel jlbMachoSemen = new JLabel("Macho/Sêmen");
	private JLabel jlbFuncionario = new JLabel("Funcionário");
	private JLabel jlbHora = new JLabel("Hora");
	private JLabel jlbPrevisaoDoParto = new JLabel("Previsão do Parto");
	private JCheckBox jcbInseminacaoArtificial = new JCheckBox("Inseminação Artificial");
	private JTextField jtfMatriz = new JTextField();
	private JTextField jtfData = new JTextField();
	private JTextField jtfMachoSemen = new JTextField();
	private JComboBox<String> jcbFuncionario = new JComboBox<>();
	private JTextField jtfHora = new JTextField();
	private JTextField jtfPrevisaoDoParto = new JTextField();
	private JButton jbtContinuar = new JButton("Proxima Monta");
	private JButton jbtSair = new JButton("Finalizar e Sair");
	private FuncionarioDAO funcionarioDao = DaoFactory.get().funcionarioDao();
	private Integer contMonta = 0;
	private MatrizDAO matrizDao = DaoFactory.get().matrizDao();
	private MachoDAO machoDao = DaoFactory.get().machoDao();
	private SemenDAO semenDao = DaoFactory.get().semenDao();
	private List machoSemen = new ArrayList();
	private Cobertura cobertura = new Cobertura();
	private CoberturaDAO coberturaDao = DaoFactory.get().coberturaDao();
	private JLabel jlbRepeticao = new JLabel();
	private MontaSemenDAO montaSemenDao = DaoFactory.get().montaSemenDao();
	private MontaMachoDao montaMachoDao = DaoFactory.get().montaMachoDao();
	private MontaMacho montaMacho = new MontaMacho();
	private MontaSemen montaSemen = new MontaSemen();
	private Integer aux = 0;

	private void posicionaObjeto(JComponent obj, int x, int y, int w, int h) {
		obj.setBounds(x, y, w, h);
		getContentPane().add(obj);
	}

	public Cobertura_Inclusao() {
		setLayout(null);

		jlbRepeticao.setForeground(Color.red);

		machoSemen.addAll(machoDao.listarTodos());
		machoSemen.addAll(semenDao.listarTodos());

		jlbMonta1.setFont(new Font("Arial", Font.BOLD, 24));
		jlbMonta1.setForeground(Color.DARK_GRAY);
		posicionaObjeto(jlbMonta1, 120, 15, 500, 25);
		posicionaObjeto(jlbMatriz, 75, 75, 100, 25);
		posicionaObjeto(jtfMatriz, 120, 75, 200, 25);
		posicionaObjeto(jlbRepeticao, 330, 75, 100, 25);
		posicionaObjeto(jlbData, 85, 105, 100, 25);
		posicionaObjeto(jtfData, 120, 105, 200, 25);
		posicionaObjeto(jlbMachoSemen, 30, 135, 150, 25);
		posicionaObjeto(jtfMachoSemen, 120, 135, 200, 25);
		posicionaObjeto(jlbFuncionario, 45, 165, 110, 25);
		posicionaObjeto(jcbFuncionario, 120, 165, 200, 25);
		posicionaObjeto(jlbHora, 85, 195, 110, 25);
		posicionaObjeto(jtfHora, 120, 195, 200, 25);
		posicionaObjeto(jlbPrevisaoDoParto, 10, 225, 150, 25);
		posicionaObjeto(jtfPrevisaoDoParto, 120, 225, 200, 25);
		posicionaObjeto(jcbInseminacaoArtificial, 100, 260, 220, 25);
		posicionaObjeto(jbtContinuar, 30, 320, 150, 35);
		posicionaObjeto(jbtSair, 200, 320, 120, 20);

		for (Funcionario funcionario : funcionarioDao.listarTodos()) {
			jcbFuncionario.addItem(funcionario.getNome());
		}

		jtfMatriz.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent arg0) {
				if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
					int aux = 0;
					for (Matriz matriz : matrizDao.listarTodos()) {
						if (matriz.getBrinco().equals(Long.valueOf(jtfMatriz.getText()))) {
							aux = 2;
							if (matriz.getStatus().equals("Vazia") || matriz.getStatus().equals("Gestante")) {
								aux = 1;
								cobertura.setMatriz(matriz);
								if (matriz.getStatus().equals("Gestante")) {
									jlbRepeticao.setText("Repetição de Cio");
								}
								coberturaDao.store(cobertura);
								break;
							}
						}
					}
					if (aux == 0) {
						JOptionPane.showMessageDialog(null, "Nenhuma Matriz encontrada");
						jlbRepeticao.setText(null);
						jtfMatriz.setText(null);
					}
					if (aux == 2) {
						JOptionPane.showMessageDialog(null, "Matriz não gestante/vazia");
						jlbRepeticao.setText(null);
						jtfMatriz.setText(null);
					}
					if (aux == 1) {
						jtfData.requestFocus();
					}
				}
			}
		});

		jtfMachoSemen.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent arg0) {
				if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
					for (int i = 0; i < machoSemen.size(); i++) {
						if (machoSemen.get(i) instanceof Macho) {
							Macho macho = new Macho();
							macho = (Macho) machoSemen.get(i);
							if (macho.getBrinco().equals(Long.valueOf(jtfMachoSemen.getText()))) {
								aux = 1;
								montaMacho.setMacho(macho);
								break;
							}
						}
						if (machoSemen.get(i) instanceof Semen) {
							Semen semen = new Semen();
							semen = (Semen) machoSemen.get(i);
							if (semen.getBrinco().equals(Long.valueOf(jtfMachoSemen.getText()))) {
								montaSemen.setSemen(semen);
								aux = 2;
								break;
							}
						}
					}
					if (aux == 0) {
						JOptionPane.showMessageDialog(null, "Macho/Semêm não encontrado");
						jtfMachoSemen.setText(null);
					}
					if (aux == 1) {
						jcbFuncionario.requestFocus();
					}
					if (aux == 2) {
						jcbFuncionario.requestFocus();
						jcbInseminacaoArtificial.setSelected(true);
					}
				}
			}
		});

		jbtContinuar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				if (jtfData.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Existem informações em branco no formulário");
				} else {
					for (Cobertura cobertura : coberturaDao.listarTodos()) {
						if (cobertura.getMatriz().getBrinco().equals(Long.valueOf(jtfMatriz.getText()))) {
							montaMacho.setCobertura(cobertura);
							montaSemen.setCobertura(cobertura);
						}
					}
					montaMacho.setData(Date.valueOf(jtfData.getText()));
					montaSemen.setData(Date.valueOf(jtfData.getText()));

					montaMacho.setFuncionario(funcionarioDao.listarTodos().get(jcbFuncionario.getSelectedIndex()));
					montaSemen.setFuncionario(funcionarioDao.listarTodos().get(jcbFuncionario.getSelectedIndex()));

					montaMacho.setTipo("Monta Natural");
					montaSemen.setTipo("Inseminação Artificial");

					if (aux == 1) {
						montaMachoDao.store(montaMacho);
					}
					if (aux == 2) {
						montaSemenDao.store(montaSemen);
					}

					if (contMonta == 0) {
						jlbMonta1.setVisible(false);
						jlbMonta2.setVisible(false);
						jlbMonta2.setFont(new Font("Arial", Font.BOLD, 24));
						jlbMonta2.setForeground(Color.DARK_GRAY);
						jtfMatriz.setEditable(false);
						posicionaObjeto(jlbMonta2, 120, 15, 500, 25);
						posicionaObjeto(jcbInseminacaoArtificial, 100, 230, 220, 25);
						posicionaObjeto(jbtContinuar, 30, 290, 150, 35);
						posicionaObjeto(jbtSair, 200, 290, 120, 20);
						setSize(450, 390);
						jlbPrevisaoDoParto.setVisible(false);
						jtfPrevisaoDoParto.setVisible(false);

						contMonta++;
					} else if (contMonta == 1) {
						jtfMatriz.setEditable(false);

						jlbMonta3.setFont(new Font("Arial", Font.BOLD, 24));
						jlbMonta3.setForeground(Color.DARK_GRAY);
						posicionaObjeto(jlbMonta3, 120, 15, 500, 25);
						contMonta++;

					} else if (contMonta == 2) {
						jlbMonta3.setVisible(false);
						jlbMonta4.setFont(new Font("Arial", Font.BOLD, 24));
						jlbMonta4.setForeground(Color.DARK_GRAY);
						posicionaObjeto(jlbMonta4, 120, 15, 500, 25);
						jbtContinuar.setVisible(false);
						posicionaObjeto(jbtSair, 100, 290, 170, 40);
					}
				}
			}
		});

		jbtSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (contMonta == 2) {
					for (Cobertura cobertura : coberturaDao.listarTodos()) {
						if (cobertura.getMatriz().getBrinco().equals(Long.valueOf(jtfMatriz.getText()))) {
							montaMacho.setCobertura(cobertura);
							montaSemen.setCobertura(cobertura);
						}
					}
					montaMacho.setData(Date.valueOf(jtfData.getText()));
					montaSemen.setData(Date.valueOf(jtfData.getText()));

					montaMacho.setFuncionario(funcionarioDao.listarTodos().get(jcbFuncionario.getSelectedIndex()));
					montaSemen.setFuncionario(funcionarioDao.listarTodos().get(jcbFuncionario.getSelectedIndex()));

					montaMacho.setTipo("Monta Natural");
					montaSemen.setTipo("Inseminação Artificial");

					if (aux == 1) {
						montaMachoDao.store(montaMacho);
					}
					if (aux == 2) {
						montaSemenDao.store(montaSemen);
					}
					JOptionPane.showMessageDialog(null, "Cobertura Cadastrada com Sucesso");
				}
				dispose();
			}
		});

		setTitle("Cobertura Inclusao");
		setSize(450, 420);
		setVisible(true);
		setResizable(false);
		setLocationRelativeTo(null);
		this.getContentPane().setBackground(Color.lightGray);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

	}

	public static void main(String[] args) {
		new Cobertura_Inclusao();
	}
}