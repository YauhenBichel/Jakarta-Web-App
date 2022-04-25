package View;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SingleHolidayRequestPanel extends JPanel {

private JFrame parentFrame;
private JPanel parentPanel;

private JLabel lblFullName;
private JLabel lblApplicationFrom;
private JLabel lblFullName_1_1;
private JLabel lblFullName_1_1_1;
private JLabel fromInputLabel;
private JLabel fullNameInputLabel;
private JLabel toInputLabel;
private JLabel constrainstsInputLabel;

private JButton acceptButton;
private JButton denyButton;

	public SingleHolidayRequestPanel(JFrame parentFrame, JPanel parentPanel) 
	{
		this.parentFrame = parentFrame;
		this.parentPanel = parentPanel;
		
		initialiseComponents();
		createEvents();
	}


	private void initialiseComponents() 
	{
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		add(panel, BorderLayout.NORTH);
		
		JLabel holidayRequestLabel = new JLabel("Holiday Request");
		holidayRequestLabel.setFont(new Font("Tahoma", Font.BOLD, 21));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(holidayRequestLabel)
					.addContainerGap(442, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(holidayRequestLabel))
		);
		panel.setLayout(gl_panel);
		
		JPanel panel_1 = new JPanel();
		add(panel_1, BorderLayout.SOUTH);
		
		JLabel lblFullName_1_1_1_1 = new JLabel("Decision");
		lblFullName_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
	    acceptButton = new JButton("Accept");
	    denyButton = new JButton("Deny");
	    
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblFullName_1_1_1_1)
					.addGap(151)
					.addComponent(acceptButton, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(denyButton, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)
					.addGap(184))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(5)
					.addComponent(lblFullName_1_1_1_1))
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(5)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(acceptButton)
						.addComponent(denyButton)))
		);
		panel_1.setLayout(gl_panel_1);
		
		JPanel panel_2 = new JPanel();
		add(panel_2, BorderLayout.CENTER);
		
		lblFullName = new JLabel("Full Name");
		lblFullName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		lblApplicationFrom = new JLabel("From");
		lblApplicationFrom.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
	    lblFullName_1_1 = new JLabel("To");
		lblFullName_1_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
	    lblFullName_1_1_1 = new JLabel("Constraints");
		lblFullName_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		fromInputLabel = new JLabel("3may2022");
		fromInputLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		fullNameInputLabel = new JLabel("Danzel Washington");
		fullNameInputLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		toInputLabel = new JLabel("30mar2022");
		toInputLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		constrainstsInputLabel = new JLabel("\"<html>\"+ myString +\"</html>\"Peak Season\r\nStaff Shortage\r\n");
		constrainstsInputLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addGap(145)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addComponent(lblFullName)
						.addComponent(lblApplicationFrom)
						.addComponent(lblFullName_1_1)
						.addComponent(lblFullName_1_1_1))
					.addGap(103)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.TRAILING)
						.addComponent(constrainstsInputLabel, GroupLayout.PREFERRED_SIZE, 190, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING, false)
							.addComponent(fromInputLabel, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(toInputLabel, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE)
							.addComponent(fullNameInputLabel, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 153, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addComponent(lblFullName)
						.addComponent(fullNameInputLabel, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblApplicationFrom)
						.addComponent(fromInputLabel, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblFullName_1_1)
						.addComponent(toInputLabel, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_2.createSequentialGroup()
							.addGap(18)
							.addComponent(lblFullName_1_1_1))
						.addGroup(gl_panel_2.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(constrainstsInputLabel, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(113, Short.MAX_VALUE))
		);
		panel_2.setLayout(gl_panel_2);
	}

	private void createEvents() 
	{
		acceptButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				AllRequestsPanel allRequestsPanel = new AllRequestsPanel(parentFrame, parentPanel);
				parentPanel.removeAll();
				parentPanel.add(allRequestsPanel, BorderLayout.CENTER);
				parentFrame.revalidate();
			}
		});
		
	}
}
