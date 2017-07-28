package com.htc.ui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseWheelEvent;

import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import com.htc.control.StockManager;
import com.htc.model.BeanProduct;
import com.htc.model.BeanProductStock;
import com.htc.util.BaseException;

public class FrmProductManager_info extends JDialog implements ActionListener {

	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	private Button btnCancel = new Button("确定");
	private JLabel labelName = new JLabel(" 产品名称:");
	private JLabel labelAddress = new JLabel(" 价   格:");
	private JLabel labelContactPerson = new JLabel("产品类型");
	private JLabel labelStock = new JLabel("库存");
	private JLabel labelStockAddress = new JLabel("库存地址");
	private JLabel labelInt = new JLabel("　　简介:");

	private JLabel edtName = new JLabel();
	private JLabel edtAddress = new JLabel();
	private JLabel edtContact = new JLabel();
	private JLabel edtStock = new JLabel();
	private JLabel edtStockAddress = new JLabel();
	private JTextArea edtInt = new JTextArea();

	private JPanel namePane = new JPanel();
	private JPanel AddressPane = new JPanel();
	private JPanel ContactPane = new JPanel();
	private JPanel StoPane = new JPanel();
	private JPanel stad = new JPanel();
	private JPanel IntPane = new JPanel();

	public FrmProductManager_info(JDialog f, String s, boolean b,BeanProduct bs) {
		super(f, s, b);
		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		toolBar.add(btnCancel);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		edtName.setText(bs.getProductName());
		edtAddress.setText(Double.toString(bs.getProductPrice()));
		edtContact.setText(Integer.toString(bs.getProductTypeID()));
		edtInt.setLineWrap(true);
		edtInt.setEditable(false);
		edtInt.setText(bs.getIntroduction());
		try {
			BeanProductStock ps = new StockManager().searchProductStock(bs.getProductID());
			edtStock.setText(Integer.toString(ps.getStockQuantity()));
			edtStockAddress.setText(ps.getStockAddress());
		} catch (BaseException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
		}
		namePane.add(labelName);
		namePane.add(edtName);
		AddressPane.add(labelAddress);
		AddressPane.add(edtAddress);
		ContactPane.add(labelContactPerson);
		ContactPane.add(edtContact);
		StoPane.add(labelStock);
		StoPane.add(edtStock);
		stad.add(labelStockAddress);
		stad.add(edtStockAddress);
		IntPane.add(labelInt);
		IntPane.add(edtInt);
		namePane.setSize(250, 100);
		AddressPane.setSize(250, 100);
		ContactPane.setSize(250, 100);
		StoPane.setSize(250, 100);
		IntPane.setSize(250, 100);
		workPane.setLayout(new BoxLayout(workPane, BoxLayout.Y_AXIS));
		workPane.add(namePane);
		workPane.add(AddressPane);
		workPane.add(ContactPane);
		workPane.add(StoPane);
		workPane.add(stad);
		workPane.add(IntPane);
		namePane.setAlignmentX(Component.LEFT_ALIGNMENT);
		AddressPane.setAlignmentX(Component.LEFT_ALIGNMENT);
		ContactPane.setAlignmentX(Component.LEFT_ALIGNMENT);
		StoPane.setAlignmentX(Component.LEFT_ALIGNMENT);
		stad.setAlignmentX(Component.LEFT_ALIGNMENT);
		IntPane.setAlignmentX(Component.LEFT_ALIGNMENT);
		this.getContentPane().add(workPane, BorderLayout.CENTER);
		this.setSize(350, 300);
		// 屏幕居中显示
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((int) (width - this.getWidth()) / 2, (int) (height - this.getHeight()) / 2);

		this.validate();
		this.btnCancel.addActionListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.btnCancel) {
			this.setVisible(false);
			return;
		}
	}
}
