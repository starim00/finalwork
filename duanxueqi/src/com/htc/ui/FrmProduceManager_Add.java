package com.htc.ui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Component;
import java.awt.FlowLayout;
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
import javax.swing.JTextField;

import com.htc.control.ProduceManager;
import com.htc.model.BeanProduce;
import com.htc.model.BeanProduct;
import com.htc.util.BaseException;

public class FrmProduceManager_Add extends JDialog implements ActionListener {
	private BeanProduce bp = null;

	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	private Button btnOk = new Button("确定");
	private Button btnCancel = new Button("取消");
	private JLabel labelProductID = new JLabel(" 产品ID:");
	private JLabel labelQua = new JLabel(" 数量:");

	private Map<Integer, BeanProduct> productMap_id = null;
	private JComboBox cmbProduct = null;
	private JTextField edtQua = new JTextField(20);

	private JPanel productPane = new JPanel();
	private JPanel QuaPane = new JPanel();

	public FrmProduceManager_Add(JDialog f, String s, boolean b, Map<Integer, BeanProduct> ptMap) {
		super(f, s, b);
		this.productMap_id = ptMap;
		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		toolBar.add(btnOk);
		toolBar.add(btnCancel);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		productPane.add(labelProductID);

		String[] strTypes = new String[this.productMap_id.size() + 1];
		strTypes[0] = "";
		java.util.Iterator<BeanProduct> itRt = this.productMap_id.values().iterator();
		int i = 1;
		while (itRt.hasNext()) {
			strTypes[i] = Integer.toString(itRt.next().getProductID());
			i++;
		}
		cmbProduct = new JComboBox(strTypes);
		productPane.add(cmbProduct);
		QuaPane.add(labelQua);
		QuaPane.add(edtQua);
		productPane.setSize(250, 100);
		QuaPane.setSize(250, 100);
		workPane.setLayout(new BoxLayout(workPane, BoxLayout.Y_AXIS));
		workPane.add(productPane);
		workPane.add(QuaPane);
		productPane.setAlignmentX(Component.LEFT_ALIGNMENT);
		QuaPane.setAlignmentX(Component.LEFT_ALIGNMENT);
		this.getContentPane().add(workPane, BorderLayout.CENTER);
		this.setSize(350, 150);
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
			bp = new BeanProduce();
			Integer ptName = Integer.parseInt(this.cmbProduct.getSelectedItem().toString());
			BeanProduct bpt = this.productMap_id.get(ptName);
			if (bpt == null) {
				JOptionPane.showMessageDialog(null, "请选择产品", "错误", JOptionPane.ERROR_MESSAGE);
				return;
			}
			bp.setProductID(bpt.getProductID());
			bp.setQuantity(Integer.parseInt(edtQua.getText()));
			try {
				ProduceManager pm = new ProduceManager();
				pm.produce(bp.getProductID(), bp.getQuantity());
				this.setVisible(false);
			} catch (BaseException e1) {
				this.bp = null;
				JOptionPane.showMessageDialog(null, e1.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	public BeanProduce getProduce() {
		return bp;
	}

}
