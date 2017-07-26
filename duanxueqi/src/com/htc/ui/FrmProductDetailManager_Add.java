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

import com.htc.control.ProductDetailManager;
import com.htc.model.BeanProduct;
import com.htc.model.BeanProductDetail;
import com.htc.model.BeanRaw;
import com.htc.util.BaseException;

public class FrmProductDetailManager_Add extends JDialog implements ActionListener {
	private BeanProductDetail bpd = null;
	
	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	private Button btnOk = new Button("确定");
	private Button btnCancel = new Button("取消");
	private JLabel labelProduct = new JLabel(" 产品名称:");
	private JLabel labelRaw = new JLabel(" 原材料名称:");
	private JLabel labelInt = new JLabel("数量");

	private Map<Integer,BeanProduct> productMap_name=null;
	private Map<Integer,BeanRaw> rawMap_name=null;
	private JComboBox cmbProduct=null;
	private JComboBox cmbRaw=null;
	private JTextField edtInt = new JTextField(20);

	private JPanel rawPane = new JPanel();
	private JPanel productPane = new JPanel();
	private JPanel IntPane = new JPanel();
	
	public FrmProductDetailManager_Add(JDialog f, String s, boolean b ,Map<Integer, BeanProduct> ptMap, Map<Integer, BeanRaw> rMap) {
		super(f, s, b);
		this.productMap_name=ptMap;
		this.rawMap_name=rMap;
		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		toolBar.add(btnOk);
		toolBar.add(btnCancel);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		productPane.add(labelProduct);
		String[] strTypes=new String[this.productMap_name.size()+1];
		strTypes[0]="";
		java.util.Iterator<BeanProduct> itRt=this.productMap_name.values().iterator();
		int i=1;
		while(itRt.hasNext()){
			strTypes[i]=itRt.next().getProductName();
			i++;
		}
		cmbProduct=new JComboBox(strTypes);
		productPane.add(cmbProduct);
		rawPane.add(labelRaw);
		String[] strTypes1=new String[this.rawMap_name.size()+1];
		strTypes1[0]="";
		java.util.Iterator<BeanRaw> itRt1=this.rawMap_name.values().iterator();
		i=1;
		while(itRt1.hasNext()){
			strTypes1[i]=itRt1.next().getRawName();
			i++;
		}
		cmbRaw=new JComboBox(strTypes1);
		rawPane.add(cmbRaw);
		
		IntPane.add(labelInt);
		IntPane.add(edtInt);
		productPane.setSize(250,100);
		rawPane.setSize(250,100);
		IntPane.setSize(250,100);
		workPane.setLayout(new BoxLayout(workPane, BoxLayout.Y_AXIS));
		workPane.add(productPane);
		workPane.add(rawPane);
		workPane.add(IntPane);
		productPane.setAlignmentX(Component.LEFT_ALIGNMENT);
		rawPane.setAlignmentX(Component.LEFT_ALIGNMENT);
		IntPane.setAlignmentX(Component.LEFT_ALIGNMENT);
		this.getContentPane().add(workPane, BorderLayout.CENTER);
		this.setSize(350,250);
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
			bpd=new BeanProductDetail();
			String ptName=this.cmbProduct.getSelectedItem().toString();
			BeanProduct bp = this.productMap_name.get(ptName);
			if(bp==null){
				JOptionPane.showMessageDialog(null, "请选择产品","错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
			bpd.setProductID(bp.getProductID());
			String rtName=this.cmbRaw.getSelectedItem().toString();
			BeanRaw br = this.rawMap_name.get(rtName);
			if(br==null){
				JOptionPane.showMessageDialog(null, "请选择原材料","错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
			bpd.setRawID(br.getRawID());
			try {
				ProductDetailManager pdm = new ProductDetailManager();
				pdm.createProductDetail(bpd);
				this.setVisible(false);
			} catch (BaseException e1) {
				this.bpd=null;
				JOptionPane.showMessageDialog(null, e1.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	public BeanProductDetail getProductDetail() {
		return bpd;
	}

}
