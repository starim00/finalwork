package com.htc.ui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Component;
import java.awt.FlowLayout;
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

import com.htc.control.ProductManager;
import com.htc.control.StockManager;
import com.htc.model.BeanProduct;
import com.htc.model.BeanProductStock;
import com.htc.model.BeanProductType;
import com.htc.util.BaseException;

public class FrmProductManager_Add extends JDialog implements ActionListener {
	private BeanProduct bp = null;
	private BeanProductStock bs = null;

	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	private Button btnOk = new Button("确定");
	private Button btnCancel = new Button("取消");
	private JLabel labelName = new JLabel(" 产品名称:");
	private JLabel labelPrice = new JLabel(" 产品价格:");
	private JLabel labelProductTypeID = new JLabel(" 产品类型");
	private JLabel labelInt = new JLabel("　 简介:");
	private JLabel labelStock = new JLabel(" 库存地址:");

	private JTextField edtName = new JTextField(20);
	private JTextField edtPrice = new JTextField(20);
	private Map<String, BeanProductType> productTypeMap_name = null;
	private JComboBox cmbProducttype = null;
	private JTextField edtStock = new JTextField(20);
	private JTextArea edtInt = new JTextArea(5, 15);

	private JPanel namePane = new JPanel();
	private JPanel pricePane = new JPanel();
	private JPanel productTypePane = new JPanel();
	private JPanel stockPane = new JPanel();
	private JPanel IntPane = new JPanel();

	public FrmProductManager_Add(JDialog f, String s, boolean b, Map<String, BeanProductType> ptMap) {
		super(f, s, b);
		this.productTypeMap_name = ptMap;
		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		toolBar.add(btnOk);
		toolBar.add(btnCancel);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		edtInt.setLineWrap(true);
		namePane.add(labelName);
		namePane.add(edtName);
		pricePane.add(labelPrice);
		pricePane.add(edtPrice);
		productTypePane.add(labelProductTypeID);

		String[] strTypes = new String[this.productTypeMap_name.size() + 1];
		strTypes[0] = "";
		java.util.Iterator<BeanProductType> itRt = this.productTypeMap_name.values().iterator();
		int i = 1;
		while (itRt.hasNext()) {
			strTypes[i] = itRt.next().getProductTypeName();
			i++;
		}
		cmbProducttype = new JComboBox(strTypes);
		productTypePane.add(cmbProducttype);
		stockPane.add(labelStock);
		stockPane.add(edtStock);
		IntPane.add(labelInt);
		IntPane.add(edtInt);
		namePane.setSize(250, 100);
		pricePane.setSize(250, 100);
		productTypePane.setSize(250, 100);
		IntPane.setSize(250, 100);
		workPane.setLayout(new BoxLayout(workPane, BoxLayout.Y_AXIS));
		workPane.add(namePane);
		workPane.add(pricePane);
		workPane.add(IntPane);
		workPane.add(stockPane);
		workPane.add(productTypePane);
		namePane.setAlignmentX(Component.LEFT_ALIGNMENT);
		pricePane.setAlignmentX(Component.LEFT_ALIGNMENT);
		productTypePane.setAlignmentX(Component.LEFT_ALIGNMENT);
		stockPane.setAlignmentX(Component.LEFT_ALIGNMENT);
		IntPane.setAlignmentX(Component.LEFT_ALIGNMENT);
		this.getContentPane().add(workPane, BorderLayout.CENTER);
		this.setSize(350, 320);
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
			bp = new BeanProduct();
			bs = new BeanProductStock();
			bp.setProductName(edtName.getText());
			try {
				bp.setProductPrice(Double.parseDouble(edtPrice.getText()));
			} catch (NumberFormatException e2) {
				// TODO: handle exception
				JOptionPane.showMessageDialog(null, "请输入正确的价格", "错误", JOptionPane.ERROR_MESSAGE);
				return;
			}
			String ptName = this.cmbProducttype.getSelectedItem().toString();
			BeanProductType bpt = this.productTypeMap_name.get(ptName);
			if (bpt == null) {
				JOptionPane.showMessageDialog(null, "请选择产品类别", "错误", JOptionPane.ERROR_MESSAGE);
				return;
			}
			bp.setProductTypeID(bpt.getProductTypeID());
			bp.setIntroduction(edtInt.getText());
			try {
				ProductManager pm = new ProductManager();
				pm.createProduct(bp);
				List<BeanProduct> l = pm.loadAllProduct();
				bs.setProductID(l.get(l.size() - 1).getProductID());
				bs.setStockAddress(edtStock.getText());
				bs.setStockQuantity(0);
				new StockManager().createProductStock(bs);
				this.setVisible(false);
			} catch (BaseException e1) {
				this.bp = null;
				JOptionPane.showMessageDialog(null, e1.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	public BeanProduct getProduct() {
		return bp;
	}

}
