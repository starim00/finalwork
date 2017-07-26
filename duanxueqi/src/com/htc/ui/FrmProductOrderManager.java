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
import javax.swing.table.DefaultTableModel;

import com.htc.control.OrderManager;
import com.htc.control.ProductManager;
import com.htc.control.RawManager;
import com.htc.model.BeanProduct;
import com.htc.model.BeanProductOrder;
import com.htc.model.BeanRaw;
import com.htc.model.BeanRawOrder;
import com.htc.util.BaseException;

public class FrmProductOrderManager extends JDialog implements ActionListener {
	private JPanel toolBar = new JPanel();
	private Button btnAdd = new Button("��Ӷ���");
	private Button btnModify = new Button("�޸Ķ���");
	private Button btnDone = new Button("��ɶ���");
	private Button btnDelete = new Button("ɾ������");
	private Map<Integer, BeanProduct> productMap_name = new HashMap<Integer, BeanProduct>();
	private JComboBox cmbRaw = null;
	private Button btnSearch = new Button("��ѯ");
	private Object tblTitle[] = { "��Ʒ����ID", "��ƷID", "�ͻ�ID", "����", "�۸�", "������" };
	private Object tblData[][];
	List<BeanProductOrder> bpo;
	DefaultTableModel tablmod = new DefaultTableModel();
	private JTable dataTable = new JTable(tablmod);

	private void reloadTable() {
		try {
			int n = this.cmbRaw.getSelectedIndex();
			int ptId = 0;
			if (n > 0) {
				Integer rtname = new Integer(this.cmbRaw.getSelectedItem().toString());
				BeanProduct br = this.productMap_name.get(rtname);
				if (br != null)
					ptId = br.getProductID();
			}
			bpo = (new OrderManager()).loadAllProductOrder(ptId);
			tblData = new Object[bpo.size()][5];
			for (int i = 0; i < bpo.size(); i++) {
				tblData[i][0] = bpo.get(i).getProductOrderID();
				tblData[i][1] = bpo.get(i).getProductID();
				tblData[i][2] = bpo.get(i).getQuantity();
				BeanProduct r = new ProductManager().searchByID(bpo.get(i).getProductID());
				tblData[i][3] = r.getProductPrice();
				if (bpo.get(i).isDone())
					tblData[i][4] = "�����";
				else
					tblData[i][4] = "δ���";
			}
			tablmod.setDataVector(tblData, tblTitle);
			this.dataTable.validate();
			this.dataTable.repaint();
		} catch (BaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public FrmProductOrderManager(Frame f, String s, boolean b) {
		super(f, s, b);
		List<BeanProduct> types = null;
		try {
			types = (new ProductManager()).loadAllProduct();
			String[] strTypes = new String[types.size() + 1];
			strTypes[0] = "";
			for (int i = 0; i < types.size(); i++) {
				productMap_name.put(types.get(i).getProductID(), types.get(i));
				strTypes[i + 1] = Integer.toString(types.get(i).getProductID());
			}
			cmbRaw = new JComboBox(strTypes);
		} catch (BaseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		toolBar.setLayout(new FlowLayout(FlowLayout.LEFT));
		toolBar.add(btnAdd);
		toolBar.add(btnModify);
		toolBar.add(this.btnDelete);
		toolBar.add(btnDone);
		toolBar.add(cmbRaw);
		toolBar.add(btnSearch);
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
		this.btnDone.addActionListener(this);
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
			FrmProductOrderManager_Add dlg = new FrmProductOrderManager_Add(this, "��Ӷ���", true, productMap_name);
			dlg.setVisible(true);
			if (dlg.getProductOrder() != null) {// ˢ�±��
				this.reloadTable();
			}
		} else if (e.getSource() == this.btnModify) {
			int i = this.dataTable.getSelectedRow();
			if (i < 0) {
				JOptionPane.showMessageDialog(null, "��ѡ�񶩵�", "��ʾ", JOptionPane.ERROR_MESSAGE);
				return;
			}
			BeanProductOrder b = this.bpo.get(i);
			FrmProductOrderManager_Modify dlg = new FrmProductOrderManager_Modify(this, "�޸Ķ���", true, productMap_name, b);
			dlg.setVisible(true);
			if (dlg.getProductOrder() != null) {// ˢ�±��
				this.reloadTable();
			}
		} else if (e.getSource() == this.btnDelete) {
			int i = this.dataTable.getSelectedRow();
			if (i < 0) {
				JOptionPane.showMessageDialog(null, "��ѡ�񶩵�", "��ʾ", JOptionPane.ERROR_MESSAGE);
				return;
			}
			BeanProductOrder b = this.bpo.get(i);
			if (JOptionPane.showConfirmDialog(this, "ȷ��ɾ��" + b.getProductOrderID() + "��", "ȷ��",
					JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				try {
					(new OrderManager()).deleteProductOrder(b);
					this.reloadTable();
				} catch (BaseException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "����", JOptionPane.ERROR_MESSAGE);
				}

			}
		} else if (e.getSource() == this.btnDone) {
			int i = this.dataTable.getSelectedRow();
			if (i < 0) {
				JOptionPane.showMessageDialog(null, "��ѡ�񶩵�", "��ʾ", JOptionPane.ERROR_MESSAGE);
				return;
			}
			BeanProductOrder b = this.bpo.get(i);
			try {
				(new OrderManager()).doneProductOrder(b);
				this.reloadTable();
			} catch (BaseException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "����", JOptionPane.ERROR_MESSAGE);
			}
		} else if (e.getSource() == this.btnSearch) {
			this.reloadTable();
		}
	}
}
