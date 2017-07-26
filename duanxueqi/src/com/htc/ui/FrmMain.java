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
	private JMenu menu_Produce = new JMenu("产品管理");
	private JMenu menu_Stock = new JMenu("生产库存管理");
	private JMenuItem menuItem_SupplierManager = new JMenuItem("供货商管理");
	private JMenuItem menuItem_RawManager = new JMenuItem("原材料管理");
	private JMenuItem menuItem_ProductTypeManager = new JMenuItem("产品类别管理");
	private JMenuItem menuItem_ProductManager = new JMenuItem("产品管理");
	private JMenuItem menuItem_CustomerManager = new JMenuItem("客户管理");
	private JMenuItem menuItem_ProductDetail = new JMenuItem("产品生产细节管理");

	private JMenuItem menuItem_RawOrder = new JMenuItem("原材料订单");
	private JMenuItem menuItem_ProductOrder = new JMenuItem("产品订单");
	private JMenuItem menuItem_Produce = new JMenuItem("生产");
	private JMenuItem menuItem_Storage = new JMenuItem("出入库记录");

	public FrmMain() {
		this.setExtendedState(Frame.MAXIMIZED_BOTH);
		this.setTitle("生产管理系统");
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
			FrmSupplierManager dlg=new FrmSupplierManager(this,"用户管理",true);
			dlg.setVisible(true);
		}
		else if(e.getSource()==this.menuItem_RawManager){
			FrmRawManager dlg=new FrmRawManager(this,"原材料管理",true);
			dlg.setVisible(true);
		}
		else if(e.getSource()==this.menuItem_ProductTypeManager){
			FrmProductTypeManager dlg = new FrmProductTypeManager(this, "产品类别管理", true);
			dlg.setVisible(true);
		}
		else if(e.getSource()==this.menuItem_ProductManager){
			FrmProductManager dlg = new FrmProductManager(this, "产品管理", true);
			dlg.setVisible(true);
		}
		else if(e.getSource()==this.menuItem_CustomerManager){
			FrmCustomerManager dlg = new FrmCustomerManager(this, "产品管理", true);
			dlg.setVisible(true);
		}
		else if(e.getSource()==this.menuItem_ProductDetail){
			FrmProductDetailManager dlg = new FrmProductDetailManager(this, "产品管理", true);
			dlg.setVisible(true);
		}
	}
}
