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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.htc.control.ProductManager;
import com.htc.control.ProductTypeManager;
import com.htc.control.RawManager;
import com.htc.control.SupplierManager;
import com.htc.model.BeanProduct;
import com.htc.model.BeanProductType;
import com.htc.model.BeanRaw;
import com.htc.model.BeanSupplier;
import com.htc.util.BaseException;

public class FrmProductManager extends JDialog implements ActionListener {
	private JPanel toolBar = new JPanel();
	private Button btnAdd = new Button("添加产品");
	private Button btnModify = new Button("修改产品");
	private Button btnDelete = new Button("删除产品");
	private Map<String, BeanProductType> productTypeMap_name = new HashMap<String, BeanProductType>();
	private Map<Integer, BeanProductType> productTypeMap_id = new HashMap<Integer, BeanProductType>();
	private JComboBox cmbProducttype = null;
	private JTextField edtKeyword = new JTextField(10);
	private Button btnSearch = new Button("查询");
	private Object tblTitle[] = { "产品ID", "产品名称", "产品价格", "产品类别" };
	private Object tblData[][];
	List<BeanProduct> bp;
	DefaultTableModel tablmod = new DefaultTableModel();
	private JTable dataTable = new JTable(tablmod);

	private void reloadTable() {
		try {
			int n = this.cmbProducttype.getSelectedIndex();
			int ptId = 0;
			if (n >= 0) {
				String rtname = this.cmbProducttype.getSelectedItem().toString();
				BeanProductType rt = this.productTypeMap_name.get(rtname);
				if (rt != null)
					ptId = rt.getProductTypeID();
			}
			bp = (new ProductManager()).searchProduct(this.edtKeyword.getText(), ptId);
			tblData = new Object[bp.size()][4];
			for (int i = 0; i < bp.size(); i++) {
				tblData[i][0] = bp.get(i).getProductID();
				tblData[i][1] = bp.get(i).getProductName();
				tblData[i][2] = bp.get(i).getProductPrice();
				BeanProductType t = this.productTypeMap_id.get(bp.get(i).getProductTypeID());
				tblData[i][3] = t == null ? "" : t.getProductTypeName();
			}
			tablmod.setDataVector(tblData, tblTitle);
			this.dataTable.validate();
			this.dataTable.repaint();
		} catch (BaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public FrmProductManager(Frame f, String s, boolean b) {
		super(f, s, b);
		List<BeanProductType> types = null;
		try {
			types = (new ProductTypeManager()).loadAllProductType();
			String[] strTypes = new String[types.size() + 1];
			strTypes[0] = "";
			for (int i = 0; i < types.size(); i++) {
				productTypeMap_name.put(types.get(i).getProductTypeName(), types.get(i));
				productTypeMap_id.put(types.get(i).getProductTypeID(), types.get(i));
				strTypes[i + 1] = types.get(i).getProductTypeName();
			}
			cmbProducttype = new JComboBox(strTypes);
		} catch (BaseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		toolBar.setLayout(new FlowLayout(FlowLayout.LEFT));
		toolBar.add(btnAdd);
		toolBar.add(btnModify);
		toolBar.add(this.btnDelete);
		toolBar.add(cmbProducttype);
		toolBar.add(edtKeyword);
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
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == this.btnAdd) {
			FrmProductManager_Add dlg = new FrmProductManager_Add(this, "添加产品类别", true, this.productTypeMap_name);
			dlg.setVisible(true);
			if (dlg.getProduct() != null) {// 刷新表格
				this.reloadTable();
			}
		} else if (e.getSource() == this.btnModify) {
			int i = this.dataTable.getSelectedRow();
			if (i < 0) {
				JOptionPane.showMessageDialog(null, "请选择产品类别", "提示", JOptionPane.ERROR_MESSAGE);
				return;
			}
			BeanProduct bpt = this.bp.get(i);
			FrmProductManager_Modify dlg = new FrmProductManager_Modify(this, "修改产品", true, this.productTypeMap_name, bpt);
			dlg.setVisible(true);
			if (dlg.getProduct() != null) {// 刷新表格
				this.reloadTable();
			}
		} else if (e.getSource() == this.btnSearch) {
			this.reloadTable();
		}
	}
}
