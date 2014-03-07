package DrawingPanel;

import javax.swing.JPanel;
import javax.swing.JTextArea;

import backEnd.Managers.UserCommandManager;

public class FunctionViewer extends JPanel {
private static UserCommandManager ucManage;
private static JTextArea funViewer;
public FunctionViewer(UserCommandManager ucMan){
	ucManage=ucMan;
	createViewer(this);
}

private void createViewer(JPanel homePanel){
	funViewer=new JTextArea(20,10);
	homePanel.add(funViewer);
}
public static void updateViewer(){
	String s="";
	for(String str:ucManage.getCommandParameterMap().keySet()){
		s+=str+"    "+ucManage.getCommandParameterMap().get(str)+"";
		s+="\n";
		funViewer.setText(s);
	}
		
}
}
