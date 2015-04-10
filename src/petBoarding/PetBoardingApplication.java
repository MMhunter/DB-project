package petBoarding;
import java.awt.BorderLayout;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Label;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * @author Hang
 * Main Application
 */
@SuppressWarnings("serial")
public class PetBoardingApplication extends JFrame{
	
	public PetBoardingApplication(){
		super();
		init();
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*
		PetBoardingApplication app = new PetBoardingApplication();
		app.setSize(960,720); 
		app.setVisible(true);
		app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		System.out.println("Welcome to Pet Boarding!");
		
		//Configure DataBase Connection Here
		PetBoardingDB.setDatabaseURL("jdbc:mysql://localhost/petBoarding");
		PetBoardingDB.setDatabaseUserName("root");
		PetBoardingDB.setDatabaseUserPassword("WS_MMhunter");
		PetBoardingDB.get(); //Connect to Database
		
		//Close Connection on Closing
		app.addWindowListener(new WindowAdapter(){
			  public void windowClosing(WindowEvent we){
				  PetBoardingDB.get().disconnectMysql();
			  }
		});
		*/
		JFrame app = new PetBoardingApplication();
		System.out.print(app.getClass().getSimpleName());
	}
	
	/**
	 * Initialization of Application Layout
	 */
	private void init(){
		//Set Title
		this.setTitle("Pet Boarding Info System");
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		
		//Add Panel Containing Operation Functions
		panel.add(new OperationsPanel());
		
		//OutPut Label
		panel.add(new Label("RESULT:"));
		
		//Add Output Console Panel
		CapturePane capturePane = new CapturePane();
		capturePane.setPreferredSize(new Dimension(600, 200));
		capturePane.setMinimumSize(new Dimension(Integer.MAX_VALUE, 200));
		capturePane.setMaximumSize(new Dimension(Integer.MAX_VALUE, 200));
		panel.add(capturePane);
		add(panel);
		PrintStream ps = System.out;
        System.setOut(new PrintStream(new StreamCapturer("PBOUT", capturePane, ps)));
        
	}
	
	/**
	 * @author Hang
	 *  CapurePanel is used to capture OutputStream to System.out and Display in the textArea in this panel
	 */
	public class CapturePane extends JPanel implements Consumer {

        private JTextArea output;

        public CapturePane() {
            setLayout(new BorderLayout());
            output = new JTextArea();
            add(new JScrollPane(output));
        }

        @Override
        public void appendText(final String text) {
            if (EventQueue.isDispatchThread()) {
                output.append(text);
                output.setCaretPosition(output.getText().length());
            } else {

                EventQueue.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        appendText(text);
                    }
                });

            }
        }        
    }
	public interface Consumer {        
        public void appendText(String text);        
    }
	
	public class StreamCapturer extends OutputStream {

        private StringBuilder buffer;
        private String prefix;
        private Consumer consumer;
        private PrintStream old;

        public StreamCapturer(String prefix, Consumer consumer, PrintStream old) {
            this.prefix = prefix;
            buffer = new StringBuilder(128);
            buffer.append("[").append(prefix).append("] ");
            this.old = old;
            this.consumer = consumer;
        }

        @Override
        public void write(int b) throws IOException {
            char c = (char) b;
            String value = Character.toString(c);
            buffer.append(value);
            if (value.equals("\n")) {
                consumer.appendText(buffer.toString());
                buffer.delete(0, buffer.length());
                buffer.append("[").append(prefix).append("] ");
            }
            old.print(c);
        }        
    }    

}
