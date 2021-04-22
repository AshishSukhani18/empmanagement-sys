import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import org.hibernate.*;
import org.hibernate.cfg.*;
import java.io.*;
import java.util.*;

class AddFrame extends JFrame
{

	Container c;
	JLabel lblEmpId,lblName,lblSalary;
	JTextField txtEmpId, txtName,txtSalary;
	JButton btnSave,btnBack;
	
	AddFrame()
	{
	 c=getContentPane();
	 c.setLayout(new FlowLayout());
	 
	 lblEmpId=new JLabel("EmpID");
	 lblName=new JLabel("Name");
         lblSalary=new JLabel("Salary");
	 
	 txtEmpId=new JTextField(20);
	 txtName=new JTextField(20);
         txtSalary=new JTextField(20);
        ImageIcon SaveImage = new ImageIcon(new ImageIcon("save.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
	ImageIcon BackImage = new ImageIcon(new ImageIcon("back.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
	 
	 
	 btnSave=new JButton("SAVE",SaveImage);
	 btnBack=new JButton("BACK",BackImage);
	 
	 c.add(lblEmpId); 
	 c.add(txtEmpId); 
	 c.add(lblName);
	 c.add(txtName);
         c.add(lblSalary);
	 c.add(txtSalary); 
	 c.add(btnSave);
	 c.add(btnBack);
	 

	 btnBack.addActionListener(new ActionListener()
	 {
		public void actionPerformed(ActionEvent ae)
		{
			MainFrame a=new MainFrame();
			dispose();
		}	 
	 });






	btnSave.addActionListener(new ActionListener()
	{
		public void actionPerformed(ActionEvent ae)
		{
			
		Configuration cfg= new Configuration();
		cfg.configure("hibernate.cfg.xml");
		SessionFactory sfact = cfg.buildSessionFactory();
		Session session = sfact.openSession();
		Transaction t =null;
				Transaction ti =null;
		try
		{
	                t= session.beginTransaction();
			        Employee e =new Employee();
                           
                    String str = (txtEmpId.getText());
					
						 
					  if(txtEmpId.getText().equals(""))
						   {
                             JOptionPane.showMessageDialog(null,"Please Enter Employee id ");
							 txtEmpId.requestFocus();
                             return;							 
						  }	
						  
						  int value= Integer.parseInt(str); 
						  if(value<0){
							  JOptionPane.showMessageDialog(null,"Id should be positive");
							   txtEmpId.setText("");
							   txtEmpId.requestFocus();
							 return;
						  }
					
							 
                       String str2=(txtName.getText());
                                
					if(txtName.getText().equals(""))
					{ JOptionPane.showMessageDialog(null,"Please Enter the name");
					  txtName.requestFocus();
					  return;
							    }
							 
                          String str1 = (txtSalary.getText());					 
						  if(txtSalary.getText().equals(""))
						  {JOptionPane.showMessageDialog(null,"Please Enter the salary");
							 txtSalary.requestFocus();
							 return;
						   }
						   
						 else if(!txtSalary.getText().matches("[0-9]+"))
						   {
                             JOptionPane.showMessageDialog(null,"Salary  should  only have Digits");
							  txtSalary.setText("");
							  txtSalary.requestFocus();
                             return;							 
						  }	
						  
						  double value1 = Double.parseDouble(str1); 
						   if(str2.length()<2){
							  JOptionPane.showMessageDialog(null,"Name cannot be of less than 2 characters");
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
						 
						 else if(value1<8000){
							  JOptionPane.showMessageDialog(null,"Salary cannot be less than 8000");
						       txtSalary.setText("");
							   txtSalary.requestFocus();
						       return;
						  }
						
						

						                                                    
          else{
			e.setName(txtName.getText());
            e.setSalary(value1);
			e.setEmpid(value);
			session.save(e);
			t.commit();
			JOptionPane.showMessageDialog(null,"1 Record inserted");
			
            System.out.println("\n \n \t \t \t \t \t record inserted \t \t \t \t \t \n \n");
		}	
		}
		



		catch(NumberFormatException e)
		{  
			t.rollback();
			 JOptionPane.showMessageDialog(null," Id should  only have Digits");
			 txtEmpId.setText("");
			txtEmpId.requestFocus();
		}

        catch(org.hibernate.exception.ConstraintViolationException eb)
		{
			t.rollback();
			JOptionPane.showMessageDialog(null,"Record already exist");
			System.out.println("\n \n \t \t \t \t \t Record already exist \t \t \t \t \t \n \n");
			txtEmpId.setText("");
			txtName.setText("");
			txtSalary.setText("");
		}
		
		
		catch(Exception ei)
		{
			t.rollback();
			JOptionPane.showMessageDialog(null,ei);
		}
		
		finally
		{
			session.close();
		}
		}
		
	});
	
		setTitle("Add Employee");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500,400);
		txtEmpId.requestFocus();
		getContentPane();
		setVisible(true);
	}
}