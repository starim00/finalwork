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

import com.htc.control.ProductTypeManager;
import com.htc.model.BeanProductType;
import com.htc.util.BaseException;

public class FrmProductTypeManager_Add extends JDialog implements ActionListener {
	private BeanProductType bpt = null;

	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	private Button btnOk = new Button("确定");
	private Button btnCancel = new Button("取消");
	private JLabel labelName = new JLabel("产品类型名称:");
	private JLabel labelInt = new JLabel("　简介:");

	private JTextField edtName = new JTextField(20);
	private TextArea edtInt = new TextArea(3,35);

	private JPanel namePane = new JPanel();
	private JPanel IntPane = new JPanel();
	
	public FrmProductTypeManager_Add(JDialog f, String s, boolean b) {
		super(f, s, b);
		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		toolBar.add(btnOk);
		toolBar.add(btnCancel);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		namePane.add(labelName);
		namePane.add(edtName);
		IntPane.add(labelInt);
		IntPane.add(edtInt);
		namePane.setSize(250,100);
		IntPane.setSize(250,100);
		workPane.setLayout(new BoxLayout(workPane, BoxLayout.Y_AXIS));
		workPane.add(namePane);
		workPane.add(IntPane);
		namePane.setAlignmentX(Component.LEFT_ALIGNMENT);
		IntPane.setAlignmentX(Component.LEFT_ALIGNMENT);
		this.getContentPane().add(workPane, BorderLayout.CENTER);
		this.setSize(350,200);
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
			
			bpt=new BeanProductType();
			bpt.setProductTypeName(edtName.getText());
			bpt.setIntroduction(edtInt.getText());
			try {
				ProductTypeManager ptm = new ProductTypeManager();
				ptm.createProductType(bpt);
				this.setVisible(false);
			} catch (BaseException e1) {
				this.bpt=null;
				JOptionPane.showMessageDialog(null, e1.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	public BeanProductType getProductType() {
		return bpt;
	}

}
