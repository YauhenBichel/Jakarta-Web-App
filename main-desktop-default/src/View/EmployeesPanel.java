package View;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Utils.RetrieveDataHelper;

public class EmployeesPanel extends JPanel {
	
	private JFrame parentFrame;
	private JPanel parentPanel;
	private JTable employeesTable;
	
	public EmployeesPanel(JFrame parentFrame, JPanel parentPanel)  
	{
		this.parentFrame = parentFrame;
		this.parentPanel = parentPanel;
		
		initialiseComponents();
	}

	private void initialiseComponents() {
		RetrieveDataHelper helper = new RetrieveDataHelper();
		setLayout(new BorderLayout(0, 0));
		
		Object[] columnNames = {"First name", "Last name", "Role", "Department", "E-mail"};
//		DefaultTableModel model = new DefaultTableModel(new Object[0][0], columnNames);
		ArrayList results = helper.getAllEmployees();
		
		String[][] rowData = new String[results.size() / columnNames.length][columnNames.length];
        for (int i = 0; i < rowData.length; i++) {
            for (int j = 0; j < rowData[i].length; j++) {
                rowData[i][j] = (String) results.get(i * columnNames.length + j);
            }
        }
        employeesTable = new JTable(rowData, columnNames);
        employeesTable.setRowHeight(25);

        add(new JScrollPane(employeesTable));
        setVisible(true);
	}
}
