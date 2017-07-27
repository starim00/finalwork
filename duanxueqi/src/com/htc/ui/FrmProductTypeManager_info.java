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

import com.htc.model.BeanProductType;
import com.htc.model.BeanSupplier;

public class FrmProductTypeManager_info extends JDialog implements ActionListener {

	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	private Button btnCancel = new Button("确定");
	private JLabel labelName = new JLabel(" 产品类型名称");
	private JLabel labelInt = new JLabel("　　简介:");

	private JLabel edtName = new JLabel();
	private JTextArea edtInt = new JTextArea();

	private JPanel namePane = new JPanel();
	private JPanel IntPane = new JPanel();

	public FrmProductTypeManager_info(JDialog f, String s, boolean b,BeanProductType bs) {
		super(f, s, b);
		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		toolBar.add(btnCancel);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		edtName.setText(bs.getProductTypeName());
		edtInt.setLineWrap(true);
		edtInt.setEditable(false);
		edtInt.setText(bs.getIntroduction());
		namePane.add(labelName);
		namePane.add(edtName);
		IntPane.add(labelInt);
		IntPane.add(edtInt);
		namePane.setSize(250, 100);
		IntPane.setSize(250, 100);
		workPane.setLayout(new BoxLayout(workPane, BoxLayout.Y_AXIS));
		workPane.add(namePane);
		workPane.add(IntPane);
		namePane.setAlignmentX(Component.LEFT_ALIGNMENT);
		IntPane.setAlignmentX(Component.LEFT_ALIGNMENT);
		this.getContentPane().add(workPane, BorderLayout.CENTER);
		this.setSize(350, 150);
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
