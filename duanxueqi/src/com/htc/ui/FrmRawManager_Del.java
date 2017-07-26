package com.htc.ui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Checkbox;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.htc.control.RawManager;
import com.htc.model.BeanRaw;
import com.htc.util.BaseException;

public class FrmRawManager_Del extends JDialog implements ActionListener {
	BeanRaw br = null;
	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	private Button btnOk = new Button("确定");
	private Button btnCancel = new Button("取消");
	private Checkbox isDel = new Checkbox();
	public FrmRawManager_Del(JDialog f, String s, boolean b,BeanRaw r) {
		// TODO Auto-generated constructor stub
		super(f, s, b);
		toolBar.add(btnOk);
		toolBar.add(btnCancel);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		isDel.setLabel("同时删除订单和出入库记录");
		workPane.add(isDel);
		this.getContentPane().add(workPane, BorderLayout.CENTER);
		br=r;
		this.setSize(350,150);
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
		// TODO Auto-generated method stub
		if(e.getSource()==this.btnCancel) {
			this.setVisible(false);
			return;
		}
		else if(e.getSource()==this.btnOk){
			try {
				RawManager rm = new RawManager();
				rm.deleteRaw(br, isDel.getState());
				this.setVisible(false);
			} catch (BaseException e1) {
				// TODO Auto-generated catch block
				this.br=null;
				JOptionPane.showMessageDialog(null, e1.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	public BeanRaw getRaw() {
		return br;
	}
}
