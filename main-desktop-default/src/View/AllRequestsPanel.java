package View;

import javax.swing.JPanel;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import Utils.RetrieveDataHelper;

import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.awt.FlowLayout;
import javax.swing.SwingConstants;


public class AllRequestsPanel extends JPanel 
{
private JFrame parentFrame;
private JPanel parentPanel;

private JTable allRequestsTable;

	/**
	 * Create the panel.
	 */
	public AllRequestsPanel(JFrame parentFrame, JPanel parentPanel)  
	{
		this.parentFrame = parentFrame;
		this.parentPanel = parentPanel;
		
		initialiseComponents();
		createEvents();
	}
	
	public void initialiseComponents()
	{
		RetrieveDataHelper helper = new RetrieveDataHelper();
		setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane);
		
		allRequestsTable = new JTable();
		allRequestsTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				System.out.println("Row selected");
				
				SingleHolidayRequestPanel singleHolidayRequestPanel = new SingleHolidayRequestPanel(parentFrame, parentPanel);
				parentPanel.removeAll();
				parentPanel.add(singleHolidayRequestPanel, BorderLayout.CENTER);
				parentFrame.revalidate();
			}
		});
		
		scrollPane.setViewportView(allRequestsTable);

		Object[] columnNames = {"employeeId", "status", "startDate", "endDate"};
//		DefaultTableModel model = new DefaultTableModel(new Object[0][0], columnNames);
		ArrayList results = helper.getAllRequests();
		
		String[][] rowData = new String[results.size() / columnNames.length][columnNames.length];
        for (int i = 0; i < rowData.length; i++) {
            for (int j = 0; j < rowData[i].length; j++) {
                rowData[i][j] = (String) results.get(i * columnNames.length + j);
            }
        }
        allRequestsTable = new JTable(rowData, columnNames);
		allRequestsTable.setRowHeight(25);
        add(new JScrollPane(allRequestsTable));
        setVisible(true);
		allRequestsTable.getColumnModel().getColumn(0).setPreferredWidth(132);
		
		JPanel panel = new JPanel();
		add(panel, BorderLayout.NORTH);
		
		JButton sortNameButton = new JButton("Name");
		
		JButton sortDateButton = new JButton("Date");
		
		JButton sortDutyButton = new JButton("Duty");
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(506)
					.addComponent(sortNameButton)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(sortDateButton)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(sortDutyButton)
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(5)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(sortDutyButton, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
						.addComponent(sortDateButton, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
						.addComponent(sortNameButton, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)))
		);
		panel.setLayout(gl_panel);
	}
	
	public void createEvents()
	{
	}
}