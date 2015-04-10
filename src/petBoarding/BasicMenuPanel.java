package petBoarding;

import java.awt.Label;

import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * @author Hang
 * Basic Top Menu Containing QUERY,INSERT,UPDATE,DELETE buttons.
 */
@SuppressWarnings("serial")
public class BasicMenuPanel extends JPanel {
	JButton query;
	JButton insert;
	JButton update;
	JButton delete;
	public BasicMenuPanel(){
		query=new JButton("QUERY");
		insert=new JButton("INSERT");
		update=new JButton("UPDATE");
		delete=new JButton("DELETE");
		add(new Label("Choose An Operation: "));
		add(query);
		add(insert);
		add(update);
		add(delete);
	}
}
