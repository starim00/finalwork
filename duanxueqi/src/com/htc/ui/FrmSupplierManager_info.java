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
import javax.swing.JPanel;
import javax.swing.JTextArea;

import com.htc.model.BeanSupplier;

public class FrmSupplierManager_info extends JDialog implements ActionListener {

	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	private Button btnCancel = new Button("确定");
	private JLabel labelName = new JLabel(" 供货商名称:");
	private JLabel labelAddress = new JLabel(" 供货商地址:");
	private JLabel labelContactPerson = new JLabel("联  系  人:");
	private JLabel labelTelephone = new JLabel("电话  号码:");
	private JLabel labelInt = new JLabel("　　简介:");

	private JLabel edtName = new JLabel();
	private JLabel edtAddress = new JLabel();
	private JLabel edtContact = new JLabel();
	private JLabel edtTelephone = new JLabel();
	private JTextArea edtInt = new JTextArea();

	private JPanel namePane = new JPanel();
	private JPanel AddressPane = new JPanel();
	private JPanel ContactPane = new JPanel();
	private JPanel TelPane = new JPanel();
	private JPanel IntPane = new JPanel();

	public FrmSupplierManager_info(JDialog f, String s, boolean b,BeanSupplier bs) {
		super(f, s, b);
		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		toolBar.add(btnCancel);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		edtName.setText(bs.getSupplierName());
		edtAddress.setText(bs.getSupplierAddress());
		edtContact.setText(bs.getContactPerson());
		edtTelephone.setText(Long.toString(bs.getTelephone()));
		edtInt.setLineWrap(true);
		edtInt.setEditable(false);
		edtInt.setText(bs.getIntroduction());
		namePane.add(labelName);
		namePane.add(edtName);
		AddressPane.add(labelAddress);
		AddressPane.add(edtAddress);
		ContactPane.add(labelContactPerson);
		ContactPane.add(edtContact);
		TelPane.add(labelTelephone);
		TelPane.add(edtTelephone);
		IntPane.add(labelInt);
		IntPane.add(edtInt);
		namePane.setSize(250, 100);
		AddressPane.setSize(250, 100);
		ContactPane.setSize(250, 100);
		TelPane.setSize(250, 100);
		IntPane.setSize(250, 100);
		workPane.setLayout(new BoxLayout(workPane, BoxLayout.Y_AXIS));
		workPane.add(namePane);
		workPane.add(AddressPane);
		workPane.add(ContactPane);
		workPane.add(TelPane);
		workPane.add(IntPane);
		namePane.setAlignmentX(Component.LEFT_ALIGNMENT);
		AddressPane.setAlignmentX(Component.LEFT_ALIGNMENT);
		ContactPane.setAlignmentX(Component.LEFT_ALIGNMENT);
		TelPane.setAlignmentX(Component.LEFT_ALIGNMENT);
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
