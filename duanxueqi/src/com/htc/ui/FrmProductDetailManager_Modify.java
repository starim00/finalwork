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

import com.htc.control.ProductDetailManager;
import com.htc.model.BeanProductDetail;
import com.htc.util.BaseException;

public class FrmProductDetailManager_Modify extends JDialog implements ActionListener {
	private BeanProductDetail bpd = null;

	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	private Button btnOk = new Button("确定");
	private Button btnCancel = new Button("取消");
	private JLabel labelProduct = new JLabel(" 产品名称:");
	private JLabel labelRaw = new JLabel(" 原材料名称:");
	private JLabel labelInt = new JLabel("数量");

	private JLabel cmbProduct = new JLabel();
	private JLabel cmbRaw = new JLabel();
	private JTextField edtInt = new JTextField(20);

	private JPanel rawPane = new JPanel();
	private JPanel productPane = new JPanel();
	private JPanel IntPane = new JPanel();

	public FrmProductDetailManager_Modify(JDialog f, String s, boolean b, BeanProductDetail bpd) {
		super(f, s, b);
		this.bpd = bpd;
		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		toolBar.add(btnOk);
		toolBar.add(btnCancel);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		productPane.add(labelProduct);
		productPane.add(cmbProduct);
		cmbProduct.setText(Integer.toString(bpd.getProductID()));
		rawPane.add(labelRaw);
		rawPane.add(cmbRaw);
		cmbRaw.setText(Integer.toString(bpd.getRawID()));
		IntPane.add(labelInt);
		IntPane.add(edtInt);
		productPane.setSize(250, 100);
		rawPane.setSize(250, 100);
		IntPane.setSize(250, 100);
		workPane.setLayout(new BoxLayout(workPane, BoxLayout.Y_AXIS));
		workPane.add(productPane);
		workPane.add(rawPane);
		workPane.add(IntPane);
		productPane.setAlignmentX(Component.LEFT_ALIGNMENT);
		rawPane.setAlignmentX(Component.LEFT_ALIGNMENT);
		IntPane.setAlignmentX(Component.LEFT_ALIGNMENT);
		this.getContentPane().add(workPane, BorderLayout.CENTER);
		this.setSize(350, 250);
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
			bpd.setQuantity(Integer.parseInt(edtInt.getText()));
			try {
				ProductDetailManager pdm = new ProductDetailManager();
				pdm.modifyProductDetail(bpd);
				this.setVisible(false);
			} catch (BaseException e1) {
				this.bpd = null;
				JOptionPane.showMessageDialog(null, e1.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	public BeanProductDetail getProductDetail() {
		return bpd;
	}

}
