package com.htc.ui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.htc.control.CustomerManager;
import com.htc.model.BeanCustomer;
import com.htc.util.BaseException;

public class FrmCustomerManager_Add extends JDialog implements ActionListener {
	private BeanCustomer bc = null;

	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	private Button btnOk = new Button("确定");
	private Button btnCancel = new Button("取消");
	private JLabel labelName = new JLabel("客户名称:");
	private JLabel labelAddress = new JLabel("客户地址:");
	private JLabel labelcon = new JLabel("联系人:");

	private JTextField edtName = new JTextField(20);
	private JTextField edtAddress = new JTextField(20);
	private JTextField edtcon = new JTextField(20);

	private JPanel namePane = new JPanel();
	private JPanel addressPane = new JPanel();
	private JPanel conPane = new JPanel();

	public FrmCustomerManager_Add(JDialog f, String s, boolean b) {
		super(f, s, b);
		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		toolBar.add(btnOk);
		toolBar.add(btnCancel);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		namePane.add(labelName);
		namePane.add(edtName);
		addressPane.add(labelAddress);
		addressPane.add(edtAddress);
		conPane.add(labelcon);
		conPane.add(edtcon);
		namePane.setSize(250, 100);
		addressPane.setSize(250, 100);
		conPane.setSize(250, 100);
		workPane.setLayout(new BoxLayout(workPane, BoxLayout.Y_AXIS));
		workPane.add(namePane);
		workPane.add(addressPane);
		workPane.add(conPane);
		namePane.setAlignmentX(Component.LEFT_ALIGNMENT);
		addressPane.setAlignmentX(Component.LEFT_ALIGNMENT);
		conPane.setAlignmentX(Component.LEFT_ALIGNMENT);
		this.getContentPane().add(workPane, BorderLayout.CENTER);
		this.setSize(350, 200);
		// 屏幕居中显示
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((int) (width - this.getWidth()) / 2, (int) (height - this.getHeight()) / 2);

		this.validate();
		this.btnOk.addActionListener(this);
		this.btnCancel.addActionListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.btnCancel) {
			this.setVisible(false);
			return;
		} else if (e.getSource() == this.btnOk) {

			bc = new BeanCustomer();
			bc.setCustomerName(edtName.getText());
			bc.setCustomerAddress(edtAddress.getText());
			bc.setContactPerson(edtcon.getText());
			try {
				CustomerManager rm = new CustomerManager();
				rm.creatCustomer(bc);
				this.setVisible(false);
			} catch (BaseException e1) {
				this.bc = null;
				JOptionPane.showMessageDialog(null, e1.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	public BeanCustomer getCustomer() {
		return bc;
	}

}
