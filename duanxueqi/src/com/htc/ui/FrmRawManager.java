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

import com.htc.control.RawManager;
import com.htc.model.BeanRaw;
import com.htc.util.BaseException;

public class FrmRawManager extends JDialog implements ActionListener {
	private JPanel toolBar = new JPanel();
	private Button btnAdd = new Button("添加产品");
	private Button btnModify = new Button("修改产品");
	private Button btnDelete = new Button("删除产品");
	private Object tblTitle[]={"产品ID","产品名称","价格","供货商ID"};
	private Object tblData[][];
	List<BeanRaw> br;
	DefaultTableModel tablmod=new DefaultTableModel();
	private JTable dataTable=new JTable(tablmod);
	private void reloadTable(){
		try {
			br=(new RawManager()).loadAllRaw();
			tblData =new Object[br.size()][4];
			for(int i=0;i<br.size();i++){
				tblData[i][0]=br.get(i).getRawID();
				tblData[i][1]=br.get(i).getRawName();
				tblData[i][2]=br.get(i).getPrice();
				tblData[i][3]=br.get(i).getSupplier();
			}
			tablmod.setDataVector(tblData,tblTitle);
			this.dataTable.validate();
			this.dataTable.repaint();
		} catch (BaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public FrmRawManager(Frame f, String s, boolean b) {
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
			FrmRawManager_AddRaw dlg=new FrmRawManager_AddRaw(this,"添加供货商",true);
			dlg.setVisible(true);
			if(dlg.getRaw()!=null){//刷新表格
				this.reloadTable();
			}
		}
		else if(e.getSource()==this.btnModify){
			int i=this.dataTable.getSelectedRow();
			if(i<0) {
				JOptionPane.showMessageDialog(null,  "请选择供货商","提示",JOptionPane.ERROR_MESSAGE);
				return;
			}
			BeanRaw b=this.br.get(i);
			FrmRawManager_Modify dlg=new FrmRawManager_Modify(this,"修改供货商",true,b);
			dlg.setVisible(true);
			if(dlg.getRaw()!=null){//刷新表格
				this.reloadTable();
			}
		}
		else if(e.getSource()==this.btnDelete){
			int i=this.dataTable.getSelectedRow();
			if(i<0) {
				JOptionPane.showMessageDialog(null,  "请选择供货商","提示",JOptionPane.ERROR_MESSAGE);
				return;
			}
			BeanRaw b=this.br.get(i);
			FrmRawManager_Del dlg=new FrmRawManager_Del(this,"修改供货商",true,b);
			dlg.setVisible(true);
			if(dlg.getRaw()!=null){//刷新表格
				this.reloadTable();
			}
		}
	}
}
