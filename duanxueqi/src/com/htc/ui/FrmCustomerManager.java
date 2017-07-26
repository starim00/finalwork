package com.htc.ui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.htc.control.CustomerManager;
import com.htc.model.BeanCustomer;
import com.htc.util.BaseException;

public class FrmCustomerManager extends JDialog implements ActionListener {
	private JPanel toolBar = new JPanel();
	private Button btnAdd = new Button("添加客户");
	private Button btnModify = new Button("修改客户");
	private Button btnDelete = new Button("删除客户");
	private Object tblTitle[]={"客户ID","客户名称","客户地址","联系人"};
	private Object tblData[][];
	List<BeanCustomer> bc;
	DefaultTableModel tablmod=new DefaultTableModel();
	private JTable dataTable=new JTable(tablmod);
	private void reloadTable(){
		try {
			bc=(new CustomerManager()).loadAllCustomer();
			tblData =new Object[bc.size()][4];
			for(int i=0;i<bc.size();i++){
				tblData[i][0]=bc.get(i).getCustomerID();
				tblData[i][1]=bc.get(i).getCustomerName();
				tblData[i][2]=bc.get(i).getCustomerAddress();
				tblData[i][3]=bc.get(i).getContactPerson();
			}
			tablmod.setDataVector(tblData,tblTitle);
			this.dataTable.validate();
			this.dataTable.repaint();
		} catch (BaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public FrmCustomerManager(Frame f, String s, boolean b) {
		super(f, s, b);
		toolBar.setLayout(new FlowLayout(FlowLayout.LEFT));
		toolBar.add(btnAdd);
		toolBar.add(btnModify);
		toolBar.add(this.btnDelete);
		this.getContentPane().add(toolBar, BorderLayout.NORTH);
		//提取现有数据
		this.reloadTable();
		this.getContentPane().add(new JScrollPane(this.dataTable), BorderLayout.CENTER);
		
		// 屏幕居中显示
		this.setSize(800, 600);
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((int) (width - this.getWidth()) / 2,
				(int) (height - this.getHeight()) / 2);

		this.validate();

		this.btnAdd.addActionListener(this);
		this.btnModify.addActionListener(this);
		this.btnDelete.addActionListener(this);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				//System.exit(0);
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==this.btnAdd){
			FrmCustomerManager_Add dlg=new FrmCustomerManager_Add(this,"添加客户",true);
			dlg.setVisible(true);
			if(dlg.getCustomer()!=null){//刷新表格
				this.reloadTable();
			}
		}
		else if(e.getSource()==this.btnModify){
			int i=this.dataTable.getSelectedRow();
			if(i<0) {
				JOptionPane.showMessageDialog(null,  "请选择客户","提示",JOptionPane.ERROR_MESSAGE);
				return;
			}
			BeanCustomer b=this.bc.get(i);
			FrmCustomerManager_Modify dlg=new FrmCustomerManager_Modify(this,"修改客户",true,b);
			dlg.setVisible(true);
			if(dlg.getCustomer()!=null){//刷新表格
				this.reloadTable();
			}
		}
		else if(e.getSource()==this.btnDelete){
			int i=this.dataTable.getSelectedRow();
			if(i<0) {
				JOptionPane.showMessageDialog(null,  "请选择客户","提示",JOptionPane.ERROR_MESSAGE);
				return;
			}
			BeanCustomer b=this.bc.get(i);
			FrmCustomerManager_Del dlg=new FrmCustomerManager_Del(this,"删除客户",true,b);
			dlg.setVisible(true);
			if(dlg.getCustomer()!=null){//刷新表格
				this.reloadTable();
			}
		}
	}
}
