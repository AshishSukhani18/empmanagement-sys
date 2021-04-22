import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import org.hibernate.*;
import org.hibernate.cfg.*;
import java.io.*;

import java.sql.*;


class DeleteFrame extends JFrame
{

	Container c;
        JLabel lblEid;
	JButton btnDelete,btnBack;
	JTextField txtEid;
	DeleteFrame(){
		c=getContentPane();
		c.setLayout(new FlowLayout());
		ImageIcon DeleteImage = new ImageIcon(new ImageIcon("delete.png").getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT));
	        ImageIcon BackImage = new ImageIcon(new ImageIcon("back.png").getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT));
	 
		btnDelete=new JButton(" Delete ",DeleteImage); 
		btnBack=new JButton(" Back ",BackImage); 
		lblEid = new JLabel("Emp_Id");
 		txtEid= new JTextField(20);
		c.add(lblEid);
		c.add(txtEid);
		c.add(btnDelete);
		c.add(btnBack);

                btnDelete.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent ae){
		
			Configuration cfg= new Configuration();
		    cfg.configure("hibernate.cfg.xml");
		
		SessionFactory sfact = cfg.buildSessionFactory();
		
		Session session = sfact.openSession();
		
		Transaction t =null;
		
		try
		{
			System.out.println("begin");
			t= session.beginTransaction();
			
			String str = (txtEid.getText());
			
			 if(txtEid.getText().equals(""))
			  {JOptionPane.showMessageDialog(null,"Please Enter Employee id ");	
			        txtEid.requestFocus();
				return;
			  }	
			  int value = Integer.parseInt(str); 
              if(value<0){
							  JOptionPane.showMessageDialog(null,"Id should be positive");
							  txtEid.setText("");
							  txtEid.requestFocus();
							  return;
						  }			  
           
			Employee e =(Employee)session.get(Employee.class,value);
			if(e != null)
			{
				
			
				session.delete(e);
				t.commit();
				JOptionPane.showMessageDialog(null,"Record Deleted");
				System.out.println("\n \n \t \t \t Record Deleted  \t \t \t  \n \n");
			}
			else
			{
				JOptionPane.showMessageDialog(null,"Record does not Exists");
				 txtEid.setText("");
				txtEid.requestFocus();
			}
			
		}
		catch(NumberFormatException e)
		{
			t.rollback();
			JOptionPane.showMessageDialog(null,"EmpId should  only have Digits");
			 txtEid.setText("");
			txtEid.requestFocus();
		}
		catch(Exception e)
		{
			t.rollback();
			System.out.println(e);
			
		}
		finally
		{
			session.close();
		}
}
		});

		btnBack.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent ae){
		MainFrame af = new MainFrame();
		dispose();}
		});
		setSize(500,400);
		getContentPane();
		setLocationRelativeTo(null);
		setTitle("Delete Employee");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                setVisible(true);
		
	}
}