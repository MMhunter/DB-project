package petBoarding;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

/**
 * @author Hang
 * Panel Containing Operation and Description
 * Displayed on the top half of Application.
 */
@SuppressWarnings("serial")
public class OperationsPanel extends JPanel {
	
	private BasicMenuPanel basicMenu;
	private SecondMenuPanel secondMenu;
	
	public OperationsPanel(){
		this.setLayout(new BorderLayout());
		basicMenu = new BasicMenuPanel();
		secondMenu = new SecondMenuPanel(MenuView.BASIC);
		this.add(basicMenu,BorderLayout.NORTH);
		this.add(secondMenu);
		
		//Click Listeners, Change Second Level Menu when Clicked First Level Menu buttons on the top
		basicMenu.query.addActionListener(new ActionListener()
		{
			@Override
		    public void actionPerformed(ActionEvent e)
		    {
				System.out.println("------------------------------------------------");
		        System.out.println("STARTING QUERY OPERATION");
		        System.out.println("------------------------------------------------");
		        reCreateSecond(MenuView.QUERY);
		    }
		});
		basicMenu.insert.addActionListener(new ActionListener()
		{
			@Override
		    public void actionPerformed(ActionEvent e)
		    {
				System.out.println("------------------------------------------------");
		        System.out.println("STARTING INSERT OPERATION");
		        System.out.println("------------------------------------------------");
		        reCreateSecond(MenuView.INSERT);
		    }
		});
		basicMenu.update.addActionListener(new ActionListener()
		{
			@Override
		    public void actionPerformed(ActionEvent e)
		    {
				System.out.println("------------------------------------------------");
		        System.out.println("STARTING UPDATE OPERATION");
		        System.out.println("------------------------------------------------");
		        reCreateSecond(MenuView.UPDATE);
		    }
		});
		basicMenu.delete.addActionListener(new ActionListener()
		{
			@Override
		    public void actionPerformed(ActionEvent e)
		    {
				System.out.println("------------------------------------------------");
		        System.out.println("STARTING DELETE OPERATION");
		        System.out.println("------------------------------------------------");
		        reCreateSecond(MenuView.DELETE);
		    }
		});
		
	}
	
	/**
	 * @param status The Current Operation Type to display
	 * Redraw Second-Level Menu
	 */
	public void reCreateSecond(MenuView status){
		BorderLayout layout = (BorderLayout) getLayout();
		remove(layout.getLayoutComponent(BorderLayout.CENTER));
		secondMenu = new SecondMenuPanel(status);
		this.add(secondMenu);
		revalidate();
		repaint();
	}
}
