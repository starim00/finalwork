package com.htc.ui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.htc.control.OrderManager;
import com.htc.model.BeanRaw;
import com.htc.model.BeanRawOrder;
import com.htc.util.BaseException;

public class FrmRawOrderManager_Add extends JDialog implements ItemListener, ActionListener {
	private BeanRawOrder br = null;

	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	private Button btnOk = new Button("确定");
	private Button btnCancel = new Button("取消");
	private JLabel labelRawID = new JLabel("原材料ID:");
	private JLabel labelQuantity = new JLabel("数量");
	private JLabel labelPrice = new JLabel("原材料价格:");

	private Map<Integer, BeanRaw> rawMap_name = null;
	private JComboBox cmbRaw = null;
	private JTextField edtQuantity = new JTextField(20);
	private JLabel edtPrice = new JLabel();
	private JPanel idPane = new JPanel();
	private JPanel pricePane = new JPanel();
	private JPanel quantityPane = new JPanel();

	public FrmRawOrderManager_Add(JDialog f, String s, boolean b, Map<Integer, BeanRaw> brMap) {
		super(f, s, b);
		this.rawMap_name = brMap;
		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		toolBar.add(btnOk);
		toolBar.add(btnCancel);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		idPane.add(labelRawID);
		String[] strTypes = new String[this.rawMap_name.size() + 1];
		strTypes[0] = "";
		java.util.Iterator<BeanRaw> itRt = this.rawMap_name.values().iterator();
		int i = 1;
		while (itRt.hasNext()) {
			strTypes[i] = Integer.toString(itRt.next().getRawID());
			i++;
		}
		cmbRaw = new JComboBox(strTypes);
		idPane.add(cmbRaw);
		pricePane.add(labelPrice);
		pricePane.add(edtPrice);

		quantityPane.add(labelQuantity);
		quantityPane.add(edtQuantity);
		idPane.setSize(250, 100);
		pricePane.setSize(250, 100);
		quantityPane.setSize(250, 100);
		workPane.setLayout(new BoxLayout(workPane, BoxLayout.Y_AXIS));
		workPane.add(idPane);
		workPane.add(pricePane);
		workPane.add(quantityPane);
		idPane.setAlignmentX(Component.LEFT_ALIGNMENT);
		pricePane.setAlignmentX(Component.LEFT_ALIGNMENT);
		quantityPane.setAlignmentX(Component.LEFT_ALIGNMENT);
		this.getContentPane().add(workPane, BorderLayout.CENTER);
		this.setSize(350, 250);
		// 屏幕居中显示
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((int) (width - this.getWidth()) / 2, (int) (height - this.getHeight()) / 2);

		this.validate();
		this.btnOk.addActionListener(this);
		this.btnCancel.addActionListener(this);
		cmbRaw.addItemListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.btnCancel) {
			this.setVisible(false);
			return;
		} else if (e.getSource() == this.btnOk) {

			br = new BeanRawOrder();
			Integer ptName = new Integer(this.cmbRaw.getSelectedItem().toString());
			BeanRaw brt = this.rawMap_name.get(ptName);
			if (brt == null) {
				JOptionPane.showMessageDialog(null, "请选择订单", "错误", JOptionPane.ERROR_MESSAGE);
				return;
			}
			br.setRawID(brt.getRawID());
			br.setQuantity(Integer.parseInt(edtQuantity.getText()));
			try {
				OrderManager rm = new OrderManager();
				rm.createRawOrder(br);
				this.setVisible(false);
			} catch (BaseException e1) {
				this.br = null;
				JOptionPane.showMessageDialog(null, e1.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	public BeanRawOrder getRawOrder() {
		return br;
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		if (e.getStateChange() == ItemEvent.SELECTED) {
			Integer ptName = new Integer(this.cmbRaw.getSelectedItem().toString());
			BeanRaw brt = this.rawMap_name.get(ptName);
			if (brt == null) {
				edtPrice.setText("0");
			} else {
				edtPrice.setText(Double.toString(brt.getPrice()));
			}
		}
	}

}
