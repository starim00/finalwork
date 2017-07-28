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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.htc.control.RawManager;
import com.htc.control.SupplierManager;
import com.htc.model.BeanRaw;
import com.htc.model.BeanSupplier;
import com.htc.util.BaseException;

public class FrmRawManager extends JDialog implements ActionListener {
	private JPanel toolBar = new JPanel();
	private Button btnAdd = new Button("添加产品");
	private Button btnModify = new Button("修改产品");
	private Button btnDelete = new Button("删除产品");
	private Map<String, BeanSupplier> supMap_name = new HashMap<String, BeanSupplier>();
	private JComboBox cmbSupplier = null;
	private Button btnSearch = new Button("查询");
	private Object tblTitle[] = { "原材料ID", "原材料名称", "价格", "供货商ID" };
	private Object tblData[][];
	List<BeanRaw> br;
	DefaultTableModel tablmod = new DefaultTableModel();
	private JTable dataTable = new JTable(tablmod);

	private void reloadTable() {
		try {
			int n = this.cmbSupplier.getSelectedIndex();
			int ptId = 0;
			if (n >= 0) {
				String rtname = this.cmbSupplier.getSelectedItem().toString();
				BeanSupplier rt = this.supMap_name.get(rtname);
				if (rt != null)
					ptId = rt.getSupplierID();
			}
			if (ptId == 0)
				br = (new RawManager()).loadAllRaw();
			else
				br = (new RawManager()).serchBySupplier(ptId);
			tblData = new Object[br.size()][4];
			for (int i = 0; i < br.size(); i++) {
				tblData[i][0] = br.get(i).getRawID();
				tblData[i][1] = br.get(i).getRawName();
				tblData[i][2] = br.get(i).getPrice();
				tblData[i][3] = br.get(i).getSupplier();
			}
			tablmod.setDataVector(tblData, tblTitle);
			this.dataTable.validate();
			this.dataTable.repaint();
		} catch (BaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public FrmRawManager(Frame f, String s, boolean b) {
		super(f, s, b);
		List<BeanSupplier> types = null;
		try {
			types = (new SupplierManager()).loadAllSupplier();
			String[] strTypes = new String[types.size() + 1];
			strTypes[0] = "";
			for (int i = 0; i < types.size(); i++) {
				supMap_name.put(types.get(i).getSupplierName(), types.get(i));
				strTypes[i + 1] = types.get(i).getSupplierName();
			}
			cmbSupplier = new JComboBox(strTypes);
		} catch (BaseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		toolBar.setLayout(new FlowLayout(FlowLayout.LEFT));
		toolBar.add(btnAdd);
		toolBar.add(btnModify);
		toolBar.add(this.btnDelete);
		toolBar.add(cmbSupplier);
		toolBar.add(btnSearch);
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
		this.btnSearch.addActionListener(this);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				// System.exit(0);
			}
		});
		this.dataTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int i = FrmRawManager.this.dataTable.getSelectedRow();
				if (i < 0) {
					return;
				}
				FrmRawManager.this.loadInfo(FrmRawManager.this.br.get(i));
			}

		});
	}

	protected void loadInfo(BeanRaw beanRaw) {
		// TODO Auto-generated method stub
		FrmRawManager_info dlg = new FrmRawManager_info(this, "添加供货商", true,beanRaw);
		dlg.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == this.btnAdd) {
			FrmRawManager_Add dlg = new FrmRawManager_Add(this, "添加原材料", true, supMap_name);
			dlg.setVisible(true);
			if (dlg.getRaw() != null) {// 刷新表格
				this.reloadTable();
			}
		} else if (e.getSource() == this.btnModify) {
			int i = this.dataTable.getSelectedRow();
			if (i < 0) {
				JOptionPane.showMessageDialog(null, "请选择原材料", "提示", JOptionPane.ERROR_MESSAGE);
				return;
			}
			BeanRaw b = this.br.get(i);
			FrmRawManager_Modify dlg = new FrmRawManager_Modify(this, "修改原材料", true, b,supMap_name);
			dlg.setVisible(true);
			if (dlg.getRaw() != null) {// 刷新表格
				this.reloadTable();
			}
		} else if (e.getSource() == this.btnDelete) {
			int i = this.dataTable.getSelectedRow();
			if (i < 0) {
				JOptionPane.showMessageDialog(null, "请选择原材料", "提示", JOptionPane.ERROR_MESSAGE);
				return;
			}
			BeanRaw b = this.br.get(i);
			FrmRawManager_Del dlg = new FrmRawManager_Del(this, "修改原材料", true, b);
			dlg.setVisible(true);
			if (dlg.getRaw() != null) {// 刷新表格
				this.reloadTable();
			}
		} else if (e.getSource() == this.btnSearch) {
			this.reloadTable();
		}
	}
}
