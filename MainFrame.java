import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

class MainFrame extends JFrame
{
Container c;
JButton btnAdd, btnView,btnUpdate,btnDelete;

	MainFrame()
	{
		c=getContentPane();
		c.setLayout(new FlowLayout());
		
		btnAdd=new JButton(" ADD  ");
		btnView=new JButton(" VIEW ");
		btnUpdate=new JButton("UPDATE");
		btnDelete=new JButton("DELETE");
		
		c.add(btnAdd);
		c.add(btnView);
		c.add(btnUpdate);
		c.add(btnDelete);
		
		btnAdd.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				AddFrame a =new AddFrame();
				dispose();
			}
		});
		
		btnView.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				ViewFrame a=new ViewFrame();
				dispose();
			}
		});
		
		btnUpdate.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				UpdateFrame a =new UpdateFrame();
				dispose();
			}
		});
		
		
		btnDelete.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				DeleteFrame a =new DeleteFrame();
				dispose();
			}
		});
		
		setTitle("Employee Managment System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setSize(500,400);
		getContentPane();
		setVisible(true);
		
	}
	public static void main(String args[])
	{
		MainFrame f=new MainFrame();
	}
} 


