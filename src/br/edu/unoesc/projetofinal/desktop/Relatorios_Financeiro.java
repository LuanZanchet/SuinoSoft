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

public class Relatorios_Financeiro extends JFrame {
	private JTree jtrNotaCompra = new JTree(new DefaultMutableTreeNode("Lista de Notas de Compra"));
	private JTree jtrNotaVenda = new JTree(new DefaultMutableTreeNode("Lista de Notas de Venda"));

	public Relatorios_Financeiro() {
		setLayout(null);
		jtrNotaCompra.setBounds(60, 30, 200, 25);
		getContentPane().add(jtrNotaCompra);
		jtrNotaVenda.setBounds(60, 70, 200, 25);
		getContentPane().add(jtrNotaVenda);
		jtrNotaCompra.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				if (arg0.getClickCount() == 1) {
					relatorio();

				}

			}
		});

		jtrNotaVenda.addMouseListener(new MouseAdapter() {
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

	public void relatorio() {
		dispose();
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("codigo", Integer.valueOf(1));
		RelatorioUtil relatorio = new RelatorioUtil();
		if (jtrNotaCompra.isRowSelected(0)) {
			relatorio.CompileViewReport("src/relatorios/NotasCompras.jrxml", ConexaoUtil.getConexao(), parametros);
		}
		if (jtrNotaVenda.isRowSelected(0)) {
			relatorio.CompileViewReport("src/relatorios/NotasVendas.jrxml", ConexaoUtil.getConexao(), parametros);
		}
	}

	public static void main(String[] args) {
		new Relatorios_Financeiro();
	}
}
