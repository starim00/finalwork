package com.htc.ui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.TextArea;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.htc.control.RawManager;
import com.htc.model.BeanProductType;
import com.htc.model.BeanRaw;
import com.htc.model.BeanSupplier;
import com.htc.util.BaseException;

public class FrmRawManager_Modify extends JDialog implements ActionListener {
	private BeanRaw br = null;

	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	private Button btnOk = new Button("确定");
	private Button btnCancel = new Button("取消");
	private JLabel labelName = new JLabel("原材料名称:");
	private JLabel labelPrice = new JLabel("原材料价格:");
	private JLabel labelSupplierID = new JLabel("供货商ID");
	private JLabel labelInt = new JLabel("　简介:");

	private JTextField edtName = new JTextField(20);
	private JTextField edtPrice = new JTextField(20);
	private Map<String, BeanSupplier> supMap_name = null;
	private JComboBox cmbSupplier = null;
	private JTextArea edtInt = new JTextArea(5, 15);

	private JPanel namePane = new JPanel();
	private JPanel pricePane = new JPanel();
	private JPanel supplierPane = new JPanel();
	private JPanel IntPane = new JPanel();

	public FrmRawManager_Modify(JDialog f, String s, boolean b, BeanRaw r,  Map<String, BeanSupplier> bsMap) {
		super(f, s, b);
		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		toolBar.add(btnOk);
		toolBar.add(btnCancel);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		br=r;
		supMap_name=bsMap;
		edtName.setText(r.getRawName());
		edtPrice.setText(Double.toString(r.getPrice()));
		String[] strTypes = new String[this.supMap_name.size() + 1];
		strTypes[0] = "";
		java.util.Iterator<BeanSupplier> itRt = this.supMap_name.values().iterator();
		int i = 1;
		int oldIndex = 0;
		while (itRt.hasNext()) {
			BeanSupplier pt = itRt.next();
			strTypes[i] = pt.getSupplierName();
			if (this.br.getSupplier() == pt.getSupplierID()) {
				oldIndex = i;
			}
			i++;
		}
		cmbSupplier = new JComboBox(strTypes);
		this.cmbSupplier.setSelectedIndex(oldIndex);
		edtInt.setLineWrap(true);
		edtInt.setText(r.getIntroduction());
		namePane.add(labelName);
		namePane.add(edtName);
		pricePane.add(labelPrice);
		pricePane.add(edtPrice);
		supplierPane.add(labelSupplierID);
		supplierPane.add(cmbSupplier);
		IntPane.add(labelInt);
		IntPane.add(edtInt);
		namePane.setSize(250, 100);
		pricePane.setSize(250, 100);
		supplierPane.setSize(250, 100);
		IntPane.setSize(250, 100);
		workPane.setLayout(new BoxLayout(workPane, BoxLayout.Y_AXIS));
		workPane.add(namePane);
		workPane.add(pricePane);
		workPane.add(supplierPane);
		workPane.add(IntPane);
		namePane.setAlignmentX(Component.LEFT_ALIGNMENT);
		pricePane.setAlignmentX(Component.LEFT_ALIGNMENT);
		supplierPane.setAlignmentX(Component.LEFT_ALIGNMENT);
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

			br.setRawName(edtName.getText());
			br.setPrice(Double.parseDouble(edtPrice.getText()));
			String ptName = this.cmbSupplier.getSelectedItem().toString();
			BeanSupplier bpt = this.supMap_name.get(ptName);
			if (bpt == null) {
				JOptionPane.showMessageDialog(null, "请选择供应商", "错误", JOptionPane.ERROR_MESSAGE);
				return;
			}
			br.setSupplier(bpt.getSupplierID());
			br.setIntroduction(edtInt.getText());
			try {
				RawManager rm = new RawManager();
				rm.modifyRaw(br);
				this.setVisible(false);
			} catch (BaseException e1) {
				this.br = null;
				JOptionPane.showMessageDialog(null, e1.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	public BeanRaw getRaw() {
		return br;
	}

}
