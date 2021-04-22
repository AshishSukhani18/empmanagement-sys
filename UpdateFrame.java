import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import org.hibernate.*;
import org.hibernate.cfg.*;
import java.io.*;
import java.sql.*;


class UpdateFrame extends JFrame
{

	Container c;
	JLabel lblEmpId,lblName,lblSalary;
	JTextField txtEmpId, txtName,txtSalary;
	JButton btnUpdate,btnBack;
	
	UpdateFrame()
	{
	 c=getContentPane();
	 c.setLayout(new FlowLayout());
	 
	 lblEmpId=new JLabel("EmpID");
	 lblName=new JLabel("Name");
         lblSalary=new JLabel("Salary");
	 
	 txtEmpId=new JTextField(20);
	 txtName=new JTextField(20);
         txtSalary=new JTextField(20);
    ImageIcon SaveImage = new ImageIcon(new ImageIcon("save.png").getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT));
	ImageIcon BackImage = new ImageIcon(new ImageIcon("back.png").getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT));
	 
	 
	 btnUpdate=new JButton("UPDATE",SaveImage);
	 btnBack=new JButton("BACK",BackImage);
	 
	 c.add(lblEmpId); c.add(txtEmpId); 
	 c.add(lblName); c.add(txtName);
     c.add(lblSalary); c.add(txtSalary); 
	 c.add(btnUpdate); c.add(btnBack);
	 
	 btnBack.addActionListener(new ActionListener()
	 {
		public void actionPerformed(ActionEvent ae)
		{
			MainFrame a=new MainFrame();
			dispose();
		}	 
	 });

	btnUpdate.addActionListener(new ActionListener()
	{
		public void actionPerformed(ActionEvent ae)
		{
		
		
		Configuration cfg= new Configuration();
		cfg.configure("hibernate.cfg.xml");
		
		
		SessionFactory sfact=cfg.buildSessionFactory();
		
		Session session=sfact.openSession();
		Transaction t = null;
		
		try
		{
			System.out.println("begin");
			
			t=session.beginTransaction();
			
			String str = (txtEmpId.getText());
			  if(txtEmpId.getText().equals(""))
			  {
			   JOptionPane.showMessageDialog(null,"Please Enter Employee id ");
			    txtEmpId.setText("");
			   txtEmpId.requestFocus();
               return;
			  }	
             
         if (!txtEmpId.getText().matches("[0-9]+")){
				JOptionPane.showMessageDialog(null," Id should  only have Digits");
			    txtEmpId.setText("");
			   txtEmpId.requestFocus();
               return; 	 
			 } 
			  
     int value = Integer.parseInt(str);	
			
		     if(value<0){
					    JOptionPane.showMessageDialog(null,"Id should be positive");
					    txtEmpId.setText("");
					    txtEmpId.requestFocus();
						return;
						  }
				 	  
						  
			Employee s=(Employee)session.get(Employee.class,value);
			if(s!=null)
			{
							
                String str2=(txtName.getText());
				if(txtName.getText().equals(""))
								{ JOptionPane.showMessageDialog(null,"Please Enter the name");
								  txtName.requestFocus();
								  return;
						    }
                else if(str2.length()<2)
				          {
							   JOptionPane.showMessageDialog(null,"Name cannot be small");
			                 txtName.setText("");
							 txtName.requestFocus();
							 return;
						  }

						  else if(!str2.matches("[a-zA-Z,]+"))
						  {
							 JOptionPane.showMessageDialog(null,"Name cannot integer");  
			                 txtName.setText(""); 
							 txtName.requestFocus();
							 return;
						  }	
                     else{ s.setName(str2);}


					 
				String str1 = (txtSalary.getText());
				if(txtSalary.getText().equals(""))
						  {JOptionPane.showMessageDialog(null,"Please Enter the salary");
							 txtSalary.requestFocus();
							 return;
						    }	
			    else if(!txtSalary.getText().matches("[0-9]+"))
						   {
                             JOptionPane.showMessageDialog(null,"Salary should only have Digits");
							  txtSalary.setText("");
							  txtSalary.requestFocus();
                             return;							 
						  }			
				double value1 = Double.parseDouble(str1);
                    if(value1<8000){
							  JOptionPane.showMessageDialog(null,"Salary cannot be less than 8000");
						     txtSalary.setText("");
							 txtSalary.requestFocus();
						    return;
						  }
				 s.setSalary(value1);
				 
				session.save(s);
				t.commit();
				JOptionPane.showMessageDialog(null,"Record updated");
				System.out.println("record updated");
			}
	else
			{
				JOptionPane.showMessageDialog(null,"Record does not exist");
							  txtEmpId.setText("");
			                 txtName.setText("");
						     txtSalary.setText("");
						    
			}
			
		
			
			System.out.println("end");
			
		}
		catch(NumberFormatException e)
		{
			t.rollback();
            JOptionPane.showMessageDialog(null,"Salary  should  only have Digits");
			txtSalary.setText("");
			txtSalary.requestFocus();
            return;							 
		}
		catch(Exception e)
		{
			System.out.println(e);
			
		}
		finally
		{
			session.close();
		}
		
		}
	});
	
	
		setTitle("Update Employee");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500,400);
		getContentPane();
		setVisible(true);
	}
}