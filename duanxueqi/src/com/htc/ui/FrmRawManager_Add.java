package com.htc.ui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.TextArea;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
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
import com.htc.control.StockManager;
import com.htc.model.BeanProduct;
import com.htc.model.BeanProductType;
import com.htc.model.BeanRaw;
import com.htc.model.BeanRawStock;
import com.htc.model.BeanSupplier;
import com.htc.util.BaseException;

public class FrmRawManager_Add extends JDialog implements ActionListener {
	private BeanRaw br = null;
	private BeanRawStock rs = null;

	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	private Button btnOk = new Button("确定");
	private Button btnCancel = new Button("取消");
	private JLabel labelName = new JLabel("原材料名称:");
	private JLabel labelPrice = new JLabel("原材料价格:");
	private JLabel labelSupplierID = new JLabel("供货商ID");
	private JLabel labelStockAddress = new JLabel("库存地址:");
	private JLabel labelInt = new JLabel("　简介:");

	private JTextField edtName = new JTextField(20);
	private JTextField edtPrice = new JTextField(20);
	private JTextField edtStock = new JTextField(20);
	private Map<String, BeanSupplier> supMap_name = null;
	private JComboBox cmbSupplier = null;
	private JTextArea edtInt = new JTextArea(3, 35);

	private JPanel namePane = new JPanel();
	private JPanel pricePane = new JPanel();
	private JPanel supplierPane = new JPanel();
	private JPanel stockPane = new JPanel();
	private JPanel IntPane = new JPanel();

	public FrmRawManager_Add(JDialog f, String s, boolean b, Map<String, BeanSupplier> bsMap) {
		super(f, s, b);
		this.supMap_name = bsMap;
		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		toolBar.add(btnOk);
		toolBar.add(btnCancel);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		edtInt.setLineWrap(true);
		namePane.add(labelName);
		namePane.add(edtName);
		pricePane.add(labelPrice);
		pricePane.add(edtPrice);
		supplierPane.add(labelSupplierID);
		String[] strTypes = new String[this.supMap_name.size() + 1];
		strTypes[0] = "";
		java.util.Iterator<BeanSupplier> itRt = this.supMap_name.values().iterator();
		int i = 1;
		while (itRt.hasNext()) {
			strTypes[i] = itRt.next().getSupplierName();
			i++;
		}
		cmbSupplier = new JComboBox(strTypes);
		supplierPane.add(cmbSupplier);
		stockPane.add(labelStockAddress);
		stockPane.add(edtStock);
		IntPane.add(labelInt);
		IntPane.add(edtInt);
		namePane.setSize(250, 100);
		pricePane.setSize(250, 100);
		supplierPane.setSize(250, 100);
		IntPane.setSize(250, 100);
		stockPane.setSize(250, 100);
		workPane.setLayout(new BoxLayout(workPane, BoxLayout.Y_AXIS));
		workPane.add(namePane);
		workPane.add(pricePane);
		workPane.add(supplierPane);
		workPane.add(stockPane);
		workPane.add(IntPane);
		namePane.setAlignmentX(Component.LEFT_ALIGNMENT);
		pricePane.setAlignmentX(Component.LEFT_ALIGNMENT);
		supplierPane.setAlignmentX(Component.LEFT_ALIGNMENT);
		stockPane.setAlignmentX(Component.LEFT_ALIGNMENT);
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

			br = new BeanRaw();
			rs = new BeanRawStock();
			br.setRawName(edtName.getText());
			br.setPrice(Double.parseDouble(edtPrice.getText()));
			String ptName = this.cmbSupplier.getSelectedItem().toString();
			BeanSupplier bpt = this.supMap_name.get(ptName);
			if (bpt == null) {
				JOptionPane.showMessageDialog(null, "请选择供应商", "错误", JOptionPane.ERROR_MESSAGE);
				return;
			}
			br.setSupplier(bpt.getSupplierID());
			;
			br.setIntroduction(edtInt.getText());
			try {
				RawManager rm = new RawManager();
				rm.createRaw(br);
				List<BeanRaw> l = rm.loadAllRaw();
				rs.setRawID(l.get(l.size() - 1).getRawID());
				rs.setStockAddress(edtStock.getText());
				rs.setStockQuantity(0);
				new StockManager().createRawStock(rs);
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
