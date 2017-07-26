package com.htc.ui;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;


public class FrmMain extends JFrame implements ActionListener {
	private JMenuBar menubar = new JMenuBar();;
	private JMenu menu_Produce = new JMenu("��Ʒ����");
	private JMenu menu_Stock = new JMenu("����������");
	private JMenuItem menuItem_SupplierManager = new JMenuItem("�����̹���");
	private JMenuItem menuItem_RawManager = new JMenuItem("ԭ���Ϲ���");
	private JMenuItem menuItem_ProductTypeManager = new JMenuItem("��Ʒ������");
	private JMenuItem menuItem_ProductManager = new JMenuItem("��Ʒ����");
	private JMenuItem menuItem_CustomerManager = new JMenuItem("�ͻ�����");
	private JMenuItem menuItem_ProductDetail = new JMenuItem("��Ʒ����ϸ�ڹ���");

	private JMenuItem menuItem_RawOrder = new JMenuItem("ԭ���϶���");
	private JMenuItem menuItem_ProductOrder = new JMenuItem("��Ʒ����");
	private JMenuItem menuItem_Produce = new JMenuItem("����");
	private JMenuItem menuItem_Storage = new JMenuItem("������¼");

	public FrmMain() {
		this.setExtendedState(Frame.MAXIMIZED_BOTH);
		this.setTitle("��������ϵͳ");
		menu_Produce.add(menuItem_SupplierManager);
		menuItem_SupplierManager.addActionListener(this);
		menu_Produce.add(menuItem_RawManager);
		menuItem_RawManager.addActionListener(this);
		menu_Produce.add(menuItem_ProductTypeManager);
		menuItem_ProductTypeManager.addActionListener(this);
		menu_Produce.add(menuItem_ProductManager);
		menuItem_ProductManager.addActionListener(this);
		menu_Produce.add(menuItem_CustomerManager);
		menuItem_CustomerManager.addActionListener(this);
		menu_Produce.add(menuItem_ProductDetail);
		menuItem_ProductDetail.addActionListener(this);
		menubar.add(menu_Produce);
		menu_Stock.add(menuItem_RawOrder);
		menuItem_RawOrder.addActionListener(this);
		menu_Stock.add(menuItem_ProductOrder);
		menuItem_ProductOrder.addActionListener(this);
		menu_Stock.add(menuItem_Produce);
		menuItem_Produce.addActionListener(this);
		menu_Stock.add(menuItem_Storage);
		menuItem_Storage.addActionListener(this);
		menubar.add(menu_Stock);
		this.setJMenuBar(menubar);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==this.menuItem_SupplierManager){
			FrmSupplierManager dlg=new FrmSupplierManager(this,"�û�����",true);
			dlg.setVisible(true);
		}
		else if(e.getSource()==this.menuItem_RawManager){
			FrmRawManager dlg=new FrmRawManager(this,"ԭ���Ϲ���",true);
			dlg.setVisible(true);
		}
		else if(e.getSource()==this.menuItem_ProductTypeManager){
			FrmProductTypeManager dlg = new FrmProductTypeManager(this, "��Ʒ������", true);
			dlg.setVisible(true);
		}
		else if(e.getSource()==this.menuItem_ProductManager){
			FrmProductManager dlg = new FrmProductManager(this, "��Ʒ����", true);
			dlg.setVisible(true);
		}
		else if(e.getSource()==this.menuItem_CustomerManager){
			FrmCustomerManager dlg = new FrmCustomerManager(this, "��Ʒ����", true);
			dlg.setVisible(true);
		}
		else if(e.getSource()==this.menuItem_ProductDetail){
			FrmProductDetailManager dlg = new FrmProductDetailManager(this, "��Ʒ����", true);
			dlg.setVisible(true);
		}
	}
}
