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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.htc.control.OrderManager;
import com.htc.control.RawManager;
import com.htc.model.BeanRaw;
import com.htc.model.BeanRawOrder;
import com.htc.util.BaseException;

public class FrmRawOrderManager extends JDialog implements ActionListener {
	private JPanel toolBar = new JPanel();
	private Button btnAdd = new Button("��Ӷ���");
	private Button btnModify = new Button("�޸Ķ���");
	private Button btnDone = new Button("��ɶ���");
	private Button btnDelete = new Button("ɾ������");
	private Map<Integer, BeanRaw> rawMap_name = new HashMap<Integer, BeanRaw>();
	private JComboBox cmbRaw = null;
	private Button btnSearch = new Button("��ѯ");
	private Object tblTitle[]={"ԭ���϶���ID","ԭ���϶���","����","�۸�","������"};
	private Object tblData[][];
	List<BeanRawOrder> bro;
	DefaultTableModel tablmod=new DefaultTableModel();
	private JTable dataTable=new JTable(tablmod);
	private void reloadTable(){
		try {
			int n = this.cmbRaw.getSelectedIndex();
			int ptId = 0;
			if (n > 0) {
				Integer rtname = new Integer(this.cmbRaw.getSelectedItem().toString());
				BeanRaw br = this.rawMap_name.get(rtname);
				if (br != null)
					ptId = br.getRawID();
			}
			bro=(new OrderManager()).loadAllRawOrder(ptId);
			tblData =new Object[bro.size()][5];
			for(int i=0;i<bro.size();i++){
				tblData[i][0]=bro.get(i).getRawOrderID();
				tblData[i][1]=bro.get(i).getRawID();
				tblData[i][2]=bro.get(i).getQuantity();
				BeanRaw r = new RawManager().searchByID(bro.get(i).getRawID());
				tblData[i][3]=r.getPrice();
				if(bro.get(i).isDone())
					tblData[i][4]="�����";
				else
					tblData[i][4]="δ���";
			}
			tablmod.setDataVector(tblData,tblTitle);
			this.dataTable.validate();
			this.dataTable.repaint();
		} catch (BaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public FrmRawOrderManager(Frame f, String s, boolean b) {
		super(f, s, b);
		List<BeanRaw> types = null;
		try {
			types = (new RawManager()).loadAllRaw();
			String[] strTypes = new String[types.size() + 1];
			strTypes[0] = "";
			for (int i = 0; i < types.size(); i++) {
				rawMap_name.put(types.get(i).getRawID(), types.get(i));
				strTypes[i + 1] = Integer.toString(types.get(i).getRawID());
			}
			cmbRaw = new JComboBox(strTypes);
		} catch (BaseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		toolBar.setLayout(new FlowLayout(FlowLayout.LEFT));
		toolBar.add(btnAdd);
		toolBar.add(btnModify);
		toolBar.add(this.btnDelete);
		toolBar.add(cmbRaw);
		toolBar.add(btnSearch);
		this.getContentPane().add(toolBar, BorderLayout.NORTH);
		//��ȡ��������
		this.reloadTable();
		this.getContentPane().add(new JScrollPane(this.dataTable), BorderLayout.CENTER);
		
		// ��Ļ������ʾ
		this.setSize(800, 600);
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((int) (width - this.getWidth()) / 2,
				(int) (height - this.getHeight()) / 2);

		this.validate();

		this.btnAdd.addActionListener(this);
		this.btnModify.addActionListener(this);
		this.btnDelete.addActionListener(this);
		this.btnSearch.addActionListener(this);
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
			FrmRawOrderManager_Add dlg=new FrmRawOrderManager_Add(this,"��ӹ�����",true,rawMap_name);
			dlg.setVisible(true);
			if(dlg.getRawOrder()!=null){//ˢ�±��
				this.reloadTable();
			}
		}
//		else if(e.getSource()==this.btnModify){
//			int i=this.dataTable.getSelectedRow();
//			if(i<0) {
//				JOptionPane.showMessageDialog(null,  "��ѡ�񹩻���","��ʾ",JOptionPane.ERROR_MESSAGE);
//				return;
//			}
//			BeanRaw b=this.bro.get(i);
//			FrmRawManager_Modify dlg=new FrmRawManager_Modify(this,"�޸Ĺ�����",true,b);
//			dlg.setVisible(true);
//			if(dlg.getRaw()!=null){//ˢ�±��
//				this.reloadTable();
//			}
//		}
//		else if(e.getSource()==this.btnDelete){
//			int i=this.dataTable.getSelectedRow();
//			if(i<0) {
//				JOptionPane.showMessageDialog(null,  "��ѡ�񹩻���","��ʾ",JOptionPane.ERROR_MESSAGE);
//				return;
//			}
//			BeanRaw b=this.bro.get(i);
//			FrmRawManager_Del dlg=new FrmRawManager_Del(this,"�޸Ĺ�����",true,b);
//			dlg.setVisible(true);
//			if(dlg.getRaw()!=null){//ˢ�±��
//				this.reloadTable();
//			}
//		}
//		else if (e.getSource() == this.btnSearch) {
//			this.reloadTable();
//		}
	}
}
