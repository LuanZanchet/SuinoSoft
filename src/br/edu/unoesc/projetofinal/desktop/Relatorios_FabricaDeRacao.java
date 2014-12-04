package br.edu.unoesc.projetofinal.desktop;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

import br.edu.unoesc.projetofinal.jdbc.dao.ConexaoUtil;
import br.edu.unoesc.projetofinal.jdbc.relatorio.RelatorioUtil;

public class Relatorios_FabricaDeRacao extends JFrame {
	private JTree jtrCompraRacao = new JTree(new DefaultMutableTreeNode("Lista de Compra de Rações"));
	private JTree jtrCadastroRacao = new JTree(new DefaultMutableTreeNode("Lista de Cadastro de Rações"));
	public Relatorios_FabricaDeRacao() {
		setLayout(null);
		jtrCompraRacao.setBounds(60, 30, 200, 25);
		getContentPane().add(jtrCompraRacao);
		jtrCadastroRacao.setBounds(60, 70, 250, 25);
		getContentPane().add(jtrCadastroRacao);
		jtrCompraRacao.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				if (arg0.getClickCount() == 1) {
					relatorio();

				}

			}
		});

		jtrCadastroRacao.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				if (arg0.getClickCount() == 1) {
					relatorio();

				}

			}
		});
		
		
		
		setTitle("Relatório");
		setSize(300, 300);
		setVisible(true);
		this.setResizable(false);
		setLocationRelativeTo(null);
		this.getContentPane().setBackground(Color.white);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	public static void main(String[] args) {
		new Relatorios_FabricaDeRacao();
	}
	public void relatorio() {
		dispose();
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("codigo", Integer.valueOf(1));
		RelatorioUtil relatorio = new RelatorioUtil();
		if (jtrCadastroRacao.isRowSelected(0)) {
			relatorio.CompileViewReport("src/relatorios/Racao.jrxml", ConexaoUtil.getConexao(), parametros);
		}
		if (jtrCompraRacao.isRowSelected(0)) {
			relatorio.CompileViewReport("src/relatorios/CompraRacao.jrxml", ConexaoUtil.getConexao(), parametros);
		}
	}
}
