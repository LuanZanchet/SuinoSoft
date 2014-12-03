package br.edu.unoesc.projetofinal.desktop;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;

import br.edu.unoesc.projetofinal.dao.EnderecoDAO;
import br.edu.unoesc.projetofinal.dao.FornecedorDAO;
import br.edu.unoesc.projetofinal.dao.factory.DaoFactory;
import br.edu.unoesc.projetofinal.model.Endereco;
import br.edu.unoesc.projetofinal.model.Fornecedor;

class Fornecedor_Inclusao extends JFrame {

	private JLabel jlbForneCli = new JLabel("Fornecedor");
	private JLabel jlbNomeFornCli = new JLabel("Nome");
	private JLabel jlbTipo = new JLabel("Tipo");
	private JLabel jlbCpfCnpj = new JLabel("CPF/CNPJ");
	private JLabel jlbEndecoForCli = new JLabel("Cidade");
	private JLabel jlbEstado = new JLabel("Estado");
	private JLabel jlbTelForCli = new JLabel("Telefone");
	private JTextField jtfNomeFornCli = new JTextField();
	private JRadioButton jrbPessoaFisica = new JRadioButton("Pessoa Física");
	private JRadioButton jrbPessoaJuridica = new JRadioButton("Pessoa Jurídica");
	private JTextField jtfCpfCnpjForCLi = new JTextField();
	private JTextField jtfEnderecoFornCli = new JTextField();
	private JTextField jtfTelFornCli = new JTextField();
	private JTextField jtfEstado = new JTextField();
	private JButton jbtCadastrarFornCli = new JButton("Cadastrar"), jbtSair = new JButton("Sair");
	private FornecedorDAO fornecedorDao = DaoFactory.get().fornecedorDao();
	private EnderecoDAO enderecoDao = DaoFactory.get().enderecoDao();

	private void posicionaObjeto(JComponent obj, int x, int y, int w, int h) {
		obj.setBounds(x, y, w, h);
		getContentPane().add(obj);
	}

	public Fornecedor_Inclusao(final DefaultTableModel dtmDados) {
		setLayout(null);

		jlbForneCli.setFont(new Font("Arial", Font.BOLD, 24));
		jlbForneCli.setForeground(Color.DARK_GRAY);

		posicionaObjeto(jlbForneCli, 100, 15, 500, 25);
		posicionaObjeto(jlbNomeFornCli, 70, 55, 200, 25);
		posicionaObjeto(jlbTipo, 80, 85, 150, 25);
		posicionaObjeto(jlbCpfCnpj, 50, 115, 150, 25);
		posicionaObjeto(jlbTelForCli, 55, 145, 150, 25);
		posicionaObjeto(jlbEndecoForCli, 65, 175, 150, 25);
		posicionaObjeto(jrbPessoaFisica, 115, 85, 110, 25);
		posicionaObjeto(jrbPessoaJuridica, 230, 85, 120, 25);
		posicionaObjeto(jtfNomeFornCli, 115, 55, 235, 25);
		posicionaObjeto(jtfCpfCnpjForCLi, 115, 115, 235, 25);
		posicionaObjeto(jtfTelFornCli, 115, 145, 235, 25);
		posicionaObjeto(jtfEnderecoFornCli, 115, 175, 235, 25);
		posicionaObjeto(jlbEstado, 65, 205, 100, 25);
		posicionaObjeto(jtfEstado, 115, 205, 235, 25);

		ButtonGroup btgEscolha = new ButtonGroup();
		btgEscolha.add(jrbPessoaFisica);
		btgEscolha.add(jrbPessoaJuridica);

		jtfEnderecoFornCli.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				if (arg0.getClickCount() == 1) {
					jtfEnderecoFornCli.setText(null);
				}

			}
		});
		posicionaObjeto(jbtCadastrarFornCli, 90, 290, 100, 30);
		posicionaObjeto(jbtSair, 230, 290, 80, 20);

		jbtCadastrarFornCli.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (jtfNomeFornCli.getText().isEmpty() || jtfEnderecoFornCli.getText().isEmpty()
						|| jtfCpfCnpjForCLi.getText().isEmpty() || jtfEstado.getText().isEmpty()
						|| jtfTelFornCli.getText().isEmpty()
						|| (!jrbPessoaFisica.isSelected() && !jrbPessoaJuridica.isSelected())) {
					JOptionPane.showMessageDialog(null, "Informações sobre o Fornecedor foram deixadas em branco!");
				} else {
					Integer aux = 0;
					for (Fornecedor fornecedor : fornecedorDao.listarTodos()) {
						if (fornecedor.getNome().equalsIgnoreCase(jtfNomeFornCli.getText())) {
							aux = 1;
							break;
						}
					}
					if (aux == 0) {
						Fornecedor fornecedor = new Fornecedor();
						fornecedor.setNome(jtfNomeFornCli.getText());
						if (jrbPessoaFisica.isSelected()) {
							fornecedor.setTipo("Pessoa Física");
						}
						if (jrbPessoaJuridica.isSelected()) {
							fornecedor.setTipo("Pessoa Jurídica");
						}
						fornecedor.setCpfCnpj(Long.valueOf(jtfCpfCnpjForCLi.getText()));
						fornecedor.setTelefone(Long.valueOf(jtfTelFornCli.getText()));
						int aux1 = 0;
						for (Endereco endereco : enderecoDao.listarTodos()) {
							if (endereco.getCidade().equals(jtfEnderecoFornCli.getText())
									&& endereco.getUf().equals(jtfEstado.getText())) {
								aux1 = 1;
								fornecedor.setEndereco(endereco);
								break;
							}
						}
						if (aux1 == 0) {
							Endereco endereco = new Endereco();
							endereco.setCidade(jtfEnderecoFornCli.getText());
							endereco.setUf(jtfEstado.getText());
							enderecoDao.store(endereco);
							for (Endereco endereco2 : enderecoDao.listarTodos()) {
								if (endereco2.getCidade().equals(jtfEnderecoFornCli.getText())
										&& endereco2.getUf().equals(jtfEstado.getText())) {
									fornecedor.setEndereco(endereco2);
									break;
								}
							}
						}
						fornecedorDao.store(fornecedor);
						int linha = 1;
						dtmDados.setRowCount(1);
						for (Fornecedor fornecedor1 : fornecedorDao.listarTodos()) {
							dtmDados.setRowCount(dtmDados.getRowCount() + 1);
							dtmDados.setValueAt(fornecedor1.getCodigo(), linha, 0);
							dtmDados.setValueAt(fornecedor1.getNome(), linha, 1);
							dtmDados.setValueAt(fornecedor1.getTipo(), linha, 2);
							dtmDados.setValueAt(fornecedor1.getCpfCnpj(), linha, 3);
							dtmDados.setValueAt(fornecedor1.getEndereco().getCidade() + "/"
									+ fornecedor.getEndereco().getUf(), linha, 4);
							dtmDados.setValueAt(fornecedor1.getTelefone(), linha, 5);
							linha++;
						}
						JOptionPane.showMessageDialog(null, "Fornecedor cadastrado com Sucesso!");
						dispose();
					}
					if (aux == 1) {
						JOptionPane.showMessageDialog(null, "Já existe um fornecedor cadastrado com esse nome!");
					}
				}
			}
		});

		jbtSair.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				dispose();

			}
		});
		setTitle("Fornecedor");
		setSize(400, 380);
		setVisible(true);
		this.setResizable(false);
		setLocationRelativeTo(null);
		this.getContentPane().setBackground(Color.lightGray);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	public static void main(String[] args) {
		new Fornecedor_Inclusao(null);
	}
}