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
import javax.swing.table.DefaultTableModel;

import br.edu.unoesc.projetofinal.dao.CompradorDAO;
import br.edu.unoesc.projetofinal.dao.EnderecoDAO;
import br.edu.unoesc.projetofinal.dao.factory.DaoFactory;
import br.edu.unoesc.projetofinal.model.Comprador;
import br.edu.unoesc.projetofinal.model.Endereco;

public class Comprador_Edicao extends JFrame {

	private JLabel jlbForneCli = new JLabel("Comprador");
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
	private JButton jbtCadastrarFornCli = new JButton("Editar"), jbtSair = new JButton("Sair");
	private CompradorDAO compradorDao = DaoFactory.get().compradorDao();
	private EnderecoDAO enderecoDao = DaoFactory.get().enderecoDao();
	private JTextField jtfGuardaValor = new JTextField();

	private void posicionaObjeto(JComponent obj, int x, int y, int w, int h) {
		obj.setBounds(x, y, w, h);
		getContentPane().add(obj);
	}

	public void setValor(Integer posicao) {
		jtfNomeFornCli.setText(compradorDao.listarTodos().get(posicao).getNome());
		jtfCpfCnpjForCLi.setText(compradorDao.listarTodos().get(posicao).getCpfCnpj().toString());
		jtfTelFornCli.setText(compradorDao.listarTodos().get(posicao).getTelefone().toString());
		jtfEnderecoFornCli.setText(compradorDao.listarTodos().get(posicao).getEndereco().getCidade());
		jtfEstado.setText(compradorDao.listarTodos().get(posicao).getEndereco().getUf());
		if (compradorDao.listarTodos().get(posicao).getTipo().equals("Pessoa Jurídica")) {
			jrbPessoaJuridica.setSelected(true);
		}
		if (compradorDao.listarTodos().get(posicao).getTipo().equals("Pessoa Física")) {
			jrbPessoaFisica.setSelected(true);
		}
		jtfGuardaValor.setText(posicao.toString());
	}

	public Comprador_Edicao(final DefaultTableModel dtmDados) {
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
					JOptionPane.showMessageDialog(null, "Informações sobre o Comprador foram deixadas em branco!");
				} else {
					Integer aux = 0;
					for (Comprador comprador : compradorDao.listarTodos()) {
						if (comprador.getNome().equalsIgnoreCase(jtfNomeFornCli.getText())) {
							aux = 1;
							if (comprador.getNome()
									.equalsIgnoreCase(
											compradorDao.listarTodos().get(Integer.valueOf(jtfGuardaValor.getText()))
													.getNome())) {
								aux = 0;
							}
							break;
						}
					}
					if (aux == 0) {
						Comprador comprador = new Comprador();
						comprador = compradorDao.listarTodos().get(Integer.valueOf(jtfGuardaValor.getText()));
						comprador.setNome(jtfNomeFornCli.getText());
						if (jrbPessoaFisica.isSelected()) {
							comprador.setTipo("Pessoa Física");
						}
						if (jrbPessoaJuridica.isSelected()) {
							comprador.setTipo("Pessoa Jurídica");
						}
						comprador.setCpfCnpj(Long.valueOf(jtfCpfCnpjForCLi.getText()));
						comprador.setTelefone(Long.valueOf(jtfTelFornCli.getText()));
						int aux1 = 0;
						for (Endereco endereco : enderecoDao.listarTodos()) {
							if (endereco.getCidade().equals(jtfEnderecoFornCli.getText())
									&& endereco.getUf().equals(jtfEstado.getText())) {
								aux1 = 1;
								comprador.setEndereco(endereco);
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
									comprador.setEndereco(endereco2);
									break;
								}
							}
						}
						compradorDao.alter(comprador);
						int linha = 1;
						dtmDados.setRowCount(1);
						for (Comprador comprador1 : compradorDao.listarTodos()) {
							dtmDados.setRowCount(dtmDados.getRowCount() + 1);
							dtmDados.setValueAt(comprador1.getCodigo(), linha, 0);
							dtmDados.setValueAt(comprador1.getNome(), linha, 1);
							dtmDados.setValueAt(comprador1.getTipo(), linha, 2);
							dtmDados.setValueAt(comprador1.getCpfCnpj(), linha, 3);
							dtmDados.setValueAt(comprador1.getEndereco().getCidade() + "/"
									+ comprador.getEndereco().getUf(), linha, 4);
							dtmDados.setValueAt(comprador1.getTelefone(), linha, 5);
							linha++;
						}
						JOptionPane.showMessageDialog(null, "Comprador alterado com Sucesso!");
						dispose();
					}
					if (aux == 1) {
						JOptionPane.showMessageDialog(null, "Já existe um comprador cadastrado com esse nome!");
					}
				}
			}
		});

		jbtSair.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				dispose();

			}
		});
		setTitle("Comprador");
		setSize(400, 380);
		setVisible(true);
		this.setResizable(false);
		setLocationRelativeTo(null);
		this.getContentPane().setBackground(Color.lightGray);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
}