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

import com.htc.control.SupplierManager;
import com.htc.model.BeanSupplier;
import com.htc.util.BaseException;

public class FrmSupplierManager extends JDialog implements ActionListener {
	private JPanel toolBar = new JPanel();
	private Button btnAdd = new Button("��ӹ�����");
	private Button btnModify = new Button("�޸Ĺ�����");
	private Button btnDelete = new Button("ɾ��������");
	private Object tblTitle[] = { "������ID", "����������", "������", "��ϵ��", "�绰" };
	private Object tblData[][];
	List<BeanSupplier> sups;
	DefaultTableModel tablmod = new DefaultTableModel();
	private JTable dataTable = new JTable(tablmod);

	private void reloadTable() {
		try {
			sups = (new SupplierManager()).loadAllSupplier();
			tblData = new Object[sups.size()][5];
			for (int i = 0; i < sups.size(); i++) {
				tblData[i][0] = sups.get(i).getSupplierID() + "";
				tblData[i][1] = sups.get(i).getSupplierName();
				tblData[i][2] = sups.get(i).getSupplierAddress();
				tblData[i][3] = sups.get(i).getContactPerson();
				tblData[i][4] = sups.get(i).getTelephone() + "";
			}
			tablmod.setDataVector(tblData, tblTitle);
			this.dataTable.validate();
			this.dataTable.repaint();
		} catch (BaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public FrmSupplierManager(Frame f, String s, boolean b) {
		super(f, s, b);
		toolBar.setLayout(new FlowLayout(FlowLayout.LEFT));
		toolBar.add(btnAdd);
		toolBar.add(btnModify);
		toolBar.add(this.btnDelete);
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
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				// System.exit(0);
			}
		});
		this.dataTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int i = FrmSupplierManager.this.dataTable.getSelectedRow();
				if (i < 0) {
					return;
				}
				FrmSupplierManager.this.loadInfo(FrmSupplierManager.this.sups.get(i));
			}

		});
	}

	public void loadInfo(BeanSupplier beanSupplier) {
		// TODO Auto-generated method stub
		FrmSupplierManager_info dlg = new FrmSupplierManager_info(this, "��ӹ�����", true,beanSupplier);
		dlg.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == this.btnAdd) {
			FrmSupplierManager_Add dlg = new FrmSupplierManager_Add(this, "��ӹ�����", true);
			dlg.setVisible(true);
			if (dlg.getSup() != null) {// ˢ�±��
				this.reloadTable();
			}
		} else if (e.getSource() == this.btnModify) {
			int i = this.dataTable.getSelectedRow();
			if (i < 0) {
				JOptionPane.showMessageDialog(null, "��ѡ�񹩻���", "��ʾ", JOptionPane.ERROR_MESSAGE);
				return;
			}
			BeanSupplier p = this.sups.get(i);
			FrmSupplierManager_Modify dlg = new FrmSupplierManager_Modify(this, "�޸Ĺ�����", true, p);
			dlg.setVisible(true);
			if (dlg.getSup() != null) {// ˢ�±��
				this.reloadTable();
			}
		} else if (e.getSource() == this.btnDelete) {
			int i = this.dataTable.getSelectedRow();
			if (i < 0) {
				JOptionPane.showMessageDialog(null, "��ѡ�񹩻���", "��ʾ", JOptionPane.ERROR_MESSAGE);
				return;
			}
			BeanSupplier bs = this.sups.get(i);
			if (JOptionPane.showConfirmDialog(this, "ȷ��ɾ��" + bs.getSupplierName() + "��", "ȷ��",
					JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				try {
					(new SupplierManager()).deleteSupplier(bs);
					this.reloadTable();
				} catch (BaseException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "����", JOptionPane.ERROR_MESSAGE);
				}

			}
		}
	}
}
