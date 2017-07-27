package com.htc.ui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.htc.control.ProductTypeManager;
import com.htc.control.RawManager;
import com.htc.control.SupplierManager;
import com.htc.model.BeanProductType;
import com.htc.model.BeanRaw;
import com.htc.model.BeanSupplier;
import com.htc.util.BaseException;

public class FrmProductTypeManager extends JDialog implements ActionListener {
	private JPanel toolBar = new JPanel();
	private Button btnAdd = new Button("添加产品类别");
	private Button btnModify = new Button("修改产品类别");
	private Button btnDelete = new Button("删除产品类别");
	private Object tblTitle[] = { "产品类别ID", "产品类别名称" };
	private Object tblData[][];
	List<BeanProductType> br;
	DefaultTableModel tablmod = new DefaultTableModel();
	private JTable dataTable = new JTable(tablmod);

	private void reloadTable() {
		try {
			br = (new ProductTypeManager()).loadAllProductType();
			tblData = new Object[br.size()][2];
			for (int i = 0; i < br.size(); i++) {
				tblData[i][0] = br.get(i).getProductTypeID();
				tblData[i][1] = br.get(i).getProductTypeName();
			}
			tablmod.setDataVector(tblData, tblTitle);
			this.dataTable.validate();
			this.dataTable.repaint();
		} catch (BaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public FrmProductTypeManager(Frame f, String s, boolean b) {
		super(f, s, b);
		toolBar.setLayout(new FlowLayout(FlowLayout.LEFT));
		toolBar.add(btnAdd);
		toolBar.add(btnModify);
		toolBar.add(this.btnDelete);
		this.getContentPane().add(toolBar, BorderLayout.NORTH);
		// 提取现有数据
		this.reloadTable();
		this.getContentPane().add(new JScrollPane(this.dataTable), BorderLayout.CENTER);

		// 屏幕居中显示
		this.setSize(800, 600);
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((int) (width - this.getWidth()) / 2, (int) (height - this.getHeight()) / 2);

		this.validate();

		this.btnAdd.addActionListener(this);
		this.btnModify.addActionListener(this);
		this.btnDelete.addActionListener(this);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				// System.exit(0);
			}
		});
		this.dataTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int i = FrmProductTypeManager.this.dataTable.getSelectedRow();
				if (i < 0) {
					return;
				}
				FrmProductTypeManager.this.loadInfo(FrmProductTypeManager.this.br.get(i));
			}

		});
	}

	protected void loadInfo(BeanProductType beanProductType) {
		// TODO Auto-generated method stub
		FrmProductTypeManager_info dlg = new FrmProductTypeManager_info(this, "添加供货商", true,beanProductType);
		dlg.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == this.btnAdd) {
			FrmProductTypeManager_Add dlg = new FrmProductTypeManager_Add(this, "添加产品类别", true);
			dlg.setVisible(true);
			if (dlg.getProductType() != null) {// 刷新表格
				this.reloadTable();
			}
		} else if (e.getSource() == this.btnModify) {
			int i = this.dataTable.getSelectedRow();
			if (i < 0) {
				JOptionPane.showMessageDialog(null, "请选择产品类别", "提示", JOptionPane.ERROR_MESSAGE);
				return;
			}
			BeanProductType bpt = this.br.get(i);
			FrmProductTypeManager_Modify dlg = new FrmProductTypeManager_Modify(this, "修改产品类别", true, bpt);
			dlg.setVisible(true);
			if (dlg.getProductType() != null) {// 刷新表格
				this.reloadTable();
			}
		} else if (e.getSource() == this.btnDelete) {
			int i = this.dataTable.getSelectedRow();
			if (i < 0) {
				JOptionPane.showMessageDialog(null, "请选择供货商", "提示", JOptionPane.ERROR_MESSAGE);
				return;
			}
			BeanProductType bpt = this.br.get(i);
			if (JOptionPane.showConfirmDialog(this, "确定删除" + bpt.getProductTypeName() + "吗？", "确认",
					JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				try {
					(new ProductTypeManager()).deleteProductType(bpt);
					this.reloadTable();
				} catch (BaseException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
				}

			}
		}
	}
}
