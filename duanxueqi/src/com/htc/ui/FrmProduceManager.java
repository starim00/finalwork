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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.htc.control.ProduceManager;
import com.htc.control.ProductManager;
import com.htc.model.BeanProduce;
import com.htc.model.BeanProduct;
import com.htc.util.BaseException;

public class FrmProduceManager extends JDialog implements ActionListener {
	private JPanel toolBar = new JPanel();
	private Button btnAdd = new Button("生产");
	private Map<Integer, BeanProduct> productMap_id = new HashMap<Integer, BeanProduct>();
	private JComboBox cmbProduct = null;
	private JTextField edtUp = new JTextField(10);
	private JLabel empty = new JLabel("-");
	private JTextField edtDown = new JTextField(10);
	private Button btnSearch = new Button("查询");
	private Object tblTitle[] = { "生产ID", "产品ID", "生产日期", "数量" };
	private Object tblData[][];
	List<BeanProduce> bp;
	DefaultTableModel tablmod = new DefaultTableModel();
	private JTable dataTable = new JTable(tablmod);

	private void reloadTable() {
		try {
			int n = this.cmbProduct.getSelectedIndex();
			int ptId = 0;
			if (n > 0) {
				Integer rtname = new Integer(this.cmbProduct.getSelectedItem().toString());
				BeanProduct rt = this.productMap_id.get(rtname);
				if (rt != null)
					ptId = rt.getProductID();
			}
			if (edtUp.getText().equals("") && edtDown.getText().equals("")) {
				if (ptId != 0)
					bp = new ProduceManager().loadByProductID(ptId);
				else
					bp = new ProduceManager().loadAllProduce();
			} else if (!(edtUp.getText().equals("") || edtDown.getText().equals(""))) {
				DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				Date up, down;
				try {
					DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
					up = format1.parse(edtUp.getText());
					down = format1.parse(edtDown.getText());
					if (ptId != 0)
						bp = new ProduceManager().loadByDate(up, down);
					else
						bp = new ProduceManager().loadByDate(up, down, ptId);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "时间格式错误,示例:yyyy-mm-dd", "错误", JOptionPane.ERROR_MESSAGE);
				}
			} else {
				JOptionPane.showMessageDialog(null, "请输入正确的时间", "错误", JOptionPane.ERROR_MESSAGE);
				bp = new ProduceManager().loadAllProduce();
			}

			tblData = new Object[bp.size()][4];
			for (int i = 0; i < bp.size(); i++) {
				tblData[i][0] = bp.get(i).getProduceID();
				tblData[i][1] = bp.get(i).getProductID();
				tblData[i][2] = bp.get(i).getDate();
				tblData[i][3] = bp.get(i).getQuantity();
			}
			tablmod.setDataVector(tblData, tblTitle);
			this.dataTable.validate();
			this.dataTable.repaint();
		} catch (BaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public FrmProduceManager(Frame f, String s, boolean b) {
		super(f, s, b);
		List<BeanProduct> types = null;
		try {
			types = (new ProductManager()).loadAllProduct();
			String[] strTypes = new String[types.size() + 1];
			strTypes[0] = "";
			for (int i = 0; i < types.size(); i++) {
				productMap_id.put(types.get(i).getProductID(), types.get(i));
				strTypes[i + 1] = Integer.toString(types.get(i).getProductID());
			}
			cmbProduct = new JComboBox(strTypes);
		} catch (BaseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		toolBar.setLayout(new FlowLayout(FlowLayout.LEFT));
		toolBar.add(btnAdd);
		toolBar.add(edtDown);
		toolBar.add(empty);
		toolBar.add(edtUp);
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
			FrmProduceManager_Add dlg = new FrmProduceManager_Add(this, "添加产品类别", true, this.productMap_id);
			dlg.setVisible(true);
			if (dlg.getProduce() != null) {// 刷新表格
				this.reloadTable();
			}
		} else if (e.getSource() == this.btnSearch) {
			this.reloadTable();
		}
	}
}
