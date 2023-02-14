package extensionRenamer;
import javax.swing.*;
import java.awt.event.*;
import java.io.File;
import java.awt.*;
import java.util.*;


public class DemoJFileChooser extends JPanel implements ActionListener {
   JButton chooseDirectoryBtn;
   JButton renameBtn;
   JTextField extensionField1;
   JTextField extensionField2;
   JLabel extension1Label;
   JLabel extension2Label;
   
   JFileChooser chooser;
   String choosertitle;
   String extension1="png";
   String extension2="jpeg";

   public File directory;
  public DemoJFileChooser() {
    chooseDirectoryBtn = new JButton("Choose Directory");
    renameBtn=new JButton("Rename");
    extensionField1=new JTextField(15);
    extension1Label=new JLabel("From Extension: ");
    extension2Label=new JLabel("To Label");
    extensionField2=new JTextField(15);
    extensionField1.addKeyListener(new KeyAdapter() {
		
		@Override
		public void keyTyped(KeyEvent e){
			if(!extensionField1.getText().contains(".")) {
    			extension1="."+extensionField1.getText()+e.getKeyChar();
    		}
    		else {
    			extension1=extensionField1.getText()+e.getKeyChar();
    		}
			System.out.println(extension1);
		}
	});
    extensionField2.addKeyListener(new KeyAdapter() {
    	@Override
    	public void keyTyped(KeyEvent e) {
    		// TODO Auto-generated method stub
    		super.keyTyped(e);
    		if(!extensionField2.getText().contains(".")) {
    			extension2="."+extensionField2.getText()+e.getKeyChar();
    		}
    		else {
    			extension2=extensionField2.getText()+e.getKeyChar();
    			
    		}
    		System.out.println(extension2);
    	}
    	
	});
    
    
    renameBtn.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			for(File i : directory.listFiles()) {
				boolean flag=i.getName().contains(extension1);
				if(flag) {
					i.renameTo(new File(directory.getAbsolutePath()+"\\"+i.getName().replace(extension1, extension2)));
				}
				else continue;
			}
			
		}
    	
    });
    chooseDirectoryBtn.addActionListener(this);
    add(renameBtn);
    add(chooseDirectoryBtn);
    add(extension1Label);
    add(extensionField1);
    add(extension2Label);
    add(extensionField2);
   }

  public void actionPerformed(ActionEvent e) {            
    chooser = new JFileChooser(); 
    chooser.setCurrentDirectory(new java.io.File("."));
    chooser.setDialogTitle(choosertitle);
    chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
    //
    // disable the "All files" option.
    //
    chooser.setAcceptAllFileFilterUsed(false);
    //    
    if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) { 
      System.out.println("getCurrentDirectory(): " 
         +  chooser.getCurrentDirectory());
      System.out.println("getSelectedFile() : " 
         +  chooser.getSelectedFile());
      
      directory=chooser.getSelectedFile();
      System.out.println(directory.getPath());
      System.out.println(directory.getAbsolutePath()+"\\");
      }
    
    else {
      System.out.println("No Selection ");
      }
     }

  public Dimension getPreferredSize(){
    return new Dimension(200, 200);
    }

  public static void main(String s[]) {
    JFrame frame = new JFrame("");
    DemoJFileChooser panel = new DemoJFileChooser();
    frame.addWindowListener(
      new WindowAdapter() {
        public void windowClosing(WindowEvent e) {
          System.exit(0);
          }
        }
      );
    frame.getContentPane().add(panel,"Center");
    frame.setSize(panel.getPreferredSize());
    frame.setVisible(true);
    }
}