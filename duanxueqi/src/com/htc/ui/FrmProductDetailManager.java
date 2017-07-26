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

import com.htc.control.ProductDetailManager;
import com.htc.control.ProductManager;
import com.htc.control.RawManager;
import com.htc.model.BeanProduct;
import com.htc.model.BeanProductDetail;
import com.htc.model.BeanRaw;
import com.htc.util.BaseException;

public class FrmProductDetailManager extends JDialog implements ActionListener {
	private JPanel toolBar = new JPanel();
	private Button btnAdd = new Button("���ԭ��������");
	private Button btnModify = new Button("�޸�ԭ��������");
	private Button btnDelete = new Button("ɾ��ԭ��������");
	private Map<String, BeanProduct> productMap_name = new HashMap<String, BeanProduct>();
	private Map<Integer, BeanProduct> productMap_id = new HashMap<Integer, BeanProduct>();
	private Map<Integer, BeanRaw> rawMap_name = new HashMap<Integer, BeanRaw>();
	private JComboBox cmbProduct = null;
	private Button btnSearch = new Button("��ѯ");
	private Object tblTitle[] = { "��ƷID", "ԭ����ID", "����",};
	private Object tblData[][];
	List<BeanProductDetail> bp;
	DefaultTableModel tablmod = new DefaultTableModel();
	private JTable dataTable = new JTable(tablmod);

	private void reloadTable() {
		try {
			int n = this.cmbProduct.getSelectedIndex();
			int ptId = 0;
			if (n >= 0) {
				String rtname = this.cmbProduct.getSelectedItem().toString();
				BeanProduct rt = this.productMap_name.get(rtname);
				if (rt != null)
					ptId = rt.getProductID();
			}
			bp = (new ProductDetailManager()).loadProductDetail(ptId);
			tblData = new Object[bp.size()][4];
			for (int i = 0; i < bp.size(); i++) {
				tblData[i][0] = bp.get(i).getProductID();
				tblData[i][1] = bp.get(i).getRawID();
				tblData[i][2] = bp.get(i).getQuantity();
			}
			tablmod.setDataVector(tblData, tblTitle);
			this.dataTable.validate();
			this.dataTable.repaint();
		} catch (BaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public FrmProductDetailManager(Frame f, String s, boolean b) {
		super(f, s, b);
		List<BeanProduct> types = null;
		try {
			types = (new ProductManager()).loadAllProduct();
			String[] strTypes = new String[types.size() + 1];
			strTypes[0] = "";
			for (int i = 0; i < types.size(); i++) {
				productMap_name.put(types.get(i).getProductName(), types.get(i));
				productMap_id.put(types.get(i).getProductID(), types.get(i));
				strTypes[i + 1] = types.get(i).getProductName();
			}
			cmbProduct = new JComboBox(strTypes);
			List<BeanRaw> types1 = (new RawManager()).loadAllRaw();
			for (int i = 0; i < types1.size(); i++) {
				rawMap_name.put(types1.get(i).getRawID(), types1.get(i));
			}
		} catch (BaseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		toolBar.setLayout(new FlowLayout(FlowLayout.LEFT));
		toolBar.add(btnAdd);
		toolBar.add(btnModify);
		toolBar.add(this.btnDelete);
		toolBar.add(cmbProduct);
		this.getContentPane().add(toolBar, BorderLayout.NORTH);
		// ��ȡ��������
		this.reloadTable();
		this.getContentPane().add(new JScrollPane(this.dataTable), BorderLayout.CENTER);

		// ��Ļ������ʾ
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
			FrmProductDetailManager_Add dlg = new FrmProductDetailManager_Add(this, "�������ϸ��", true, this.productMap_id, this.rawMap_name);
			dlg.setVisible(true);
			if (dlg.getProductDetail() != null) {// ˢ�±��
				this.reloadTable();
			}
		} 
		else if (e.getSource() == this.btnModify) {
			int i = this.dataTable.getSelectedRow();
			if (i < 0) {
				JOptionPane.showMessageDialog(null, "��ѡ���Ʒ���", "��ʾ", JOptionPane.ERROR_MESSAGE);
				return;
			}
			BeanProductDetail bpt = this.bp.get(i);
			FrmProductDetailManager_Modify dlg = new FrmProductDetailManager_Modify(this, "�޸Ĳ�Ʒ", true, bpt);
			dlg.setVisible(true);
			if (dlg.getProductDetail() != null) {// ˢ�±��
				this.reloadTable();
			}
		}
//		else if (e.getSource() == this.btnDelete) {
//			int i = this.dataTable.getSelectedRow();
//			if (i < 0) {
//				JOptionPane.showMessageDialog(null, "��ѡ���Ʒ���", "��ʾ", JOptionPane.ERROR_MESSAGE);
//				return;
//			}
//			BeanProduct bpt = this.bp.get(i);
//			FrmProductManager_Del dlg = new FrmProductManager_Del(this, "�޸Ĳ�Ʒ", true,bpt);
//			dlg.setVisible(true);
//			if (dlg.getProduct() != null) {// ˢ�±��
//				this.reloadTable();
//			}
//		}
//		else if (e.getSource() == this.btnSearch) {
//			this.reloadTable();
//		}
	}
}
