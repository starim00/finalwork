package com.htc.ui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.TextArea;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.htc.control.SupplierManager;
import com.htc.model.BeanSupplier;
import com.htc.util.BaseException;

public class FrmSupplierManager_Add extends JDialog implements ActionListener {
	private BeanSupplier sup = null;

	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	private Button btnOk = new Button("确定");
	private Button btnCancel = new Button("取消");
	private JLabel labelName = new JLabel(" 供货商名称:");
	private JLabel labelAddress = new JLabel(" 供货商地址:");
	private JLabel labelContactPerson = new JLabel("联   系   人:");
	private JLabel labelTelephone = new JLabel("电话  号码:");
	private JLabel labelInt = new JLabel("　　简介:");

	private JTextField edtName = new JTextField(20);
	private JTextField edtAddress = new JTextField(20);
	private JTextField edtContact = new JTextField(20);
	private JTextField edtTelephone = new JTextField(20);
	private TextArea edtInt = new TextArea(3,35);

	private JPanel namePane = new JPanel();
	private JPanel AddressPane = new JPanel();
	private JPanel ContactPane = new JPanel();
	private JPanel TelPane = new JPanel();
	private JPanel IntPane = new JPanel();
	
	public FrmSupplierManager_Add(JDialog f, String s, boolean b) {
		super(f, s, b);
		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		toolBar.add(btnOk);
		toolBar.add(btnCancel);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
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
		namePane.setSize(250,100);
		AddressPane.setSize(250,100);
		ContactPane.setSize(250,100);
		TelPane.setSize(250,100);
		IntPane.setSize(250,100);
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
		this.setSize(350,300);
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
		if(e.getSource()==this.btnCancel) {
			this.setVisible(false);
			return;
		}
		else if(e.getSource()==this.btnOk){
			
			sup=new BeanSupplier();
			sup.setSupplierName(edtName.getText());
			sup.setSupplierAddress(edtAddress.getText());
			sup.setContactPerson(edtContact.getText());
			sup.setTelephone(Long.parseLong(edtTelephone.getText()));
			sup.setIntroduction(edtInt.getText());
			try {
				SupplierManager sm = new SupplierManager();
				sm.createSupplier(sup);
				this.setVisible(false);
			} catch (BaseException e1) {
				this.sup=null;
				JOptionPane.showMessageDialog(null, e1.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	public BeanSupplier getSup() {
		return sup;
	}

}
