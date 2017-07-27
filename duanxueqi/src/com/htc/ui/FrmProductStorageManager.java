package com.htc.ui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.htc.control.ProductManager;
import com.htc.control.StorageManager;
import com.htc.model.BeanProduct;
import com.htc.model.BeanProductStorage;
import com.htc.util.BaseException;

public class FrmProductStorageManager extends JDialog implements ActionListener {
	private JPanel toolBar = new JPanel();
	private Map<Integer, BeanProduct> proMap_id = new HashMap<Integer, BeanProduct>();
	private JComboBox cmbPro = null;
	private Button btnSearch = new Button("查询");
	private Object tblTitle[] = { "出入库记录编号", "产品ID", "日期", "出入库", "数量" };
	private Object tblData[][];
	List<BeanProductStorage> br;
	DefaultTableModel tablmod = new DefaultTableModel();
	private JTable dataTable = new JTable(tablmod);

	private void reloadTable() {
		try {
			int n = this.cmbPro.getSelectedIndex();
			int ptId = 0;
			if (n >= 0) {
				String rtname = this.cmbPro.getSelectedItem().toString();
				BeanProduct rt = this.proMap_id.get(rtname);
				if (rt != null)
					ptId = rt.getProductID();
			}
			br = (new StorageManager()).loadAllProductStorage(ptId);
			tblData = new Object[br.size()][5];
			for (int i = 0; i < br.size(); i++) {
				tblData[i][0] = br.get(i).getProductStorageID();
				tblData[i][1] = br.get(i).getProductID();
				tblData[i][2] = br.get(i).getDate();
				if (br.get(i).getStorageQuantity() < 0)
					tblData[i][3] = "出库";
				else
					tblData[i][3] = "入库";
				tblData[i][4] = Math.abs(br.get(i).getStorageQuantity());
			}
			tablmod.setDataVector(tblData, tblTitle);
			this.dataTable.validate();
			this.dataTable.repaint();
		} catch (BaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public FrmProductStorageManager(Frame f, String s, boolean b) {
		super(f, s, b);
		List<BeanProduct> types = null;
		try {
			types = (new ProductManager()).loadAllProduct();
			String[] strTypes = new String[types.size() + 1];
			strTypes[0] = "";
			for (int i = 0; i < types.size(); i++) {
				proMap_id.put(types.get(i).getProductID(), types.get(i));
				strTypes[i + 1] = Integer.toString(types.get(i).getProductID());
			}
			cmbPro = new JComboBox(strTypes);
		} catch (BaseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		toolBar.setLayout(new FlowLayout(FlowLayout.LEFT));
		toolBar.add(cmbPro);
		toolBar.add(btnSearch);
		this.getContentPane().add(toolBar, BorderLayout.NORTH);
		// 提取现有数据
		this.reloadTable();
		this.getContentPane().add(new JScrollPane(this.dataTable), BorderLayout.CENTER);

		// 屏幕居中显示
		this.setSize(900, 600);
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((int) (width - this.getWidth()) / 2, (int) (height - this.getHeight()) / 2);

		this.validate();
		this.btnSearch.addActionListener(this);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				// System.exit(0);
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == this.btnSearch) {
			this.reloadTable();
		}
	}
}
