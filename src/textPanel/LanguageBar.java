package textPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JPanel;

import backEnd.Managers.LanguageManager;

@SuppressWarnings("serial")
public class LanguageBar extends JPanel {
	LanguageManager languageManager;
	public LanguageBar(LanguageManager langManage){
		super();
		languageManager=langManage;
		makeLangList(this);
	}
	private void makeLangList(JPanel homePanel){
		final JComboBox<String> languagesList=new JComboBox<String>((removeFileNames(new File("src/resources").list())));
		homePanel.add(languagesList);
		languagesList.setSelectedIndex(1);
		languagesList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{       
				languageManager.setLanguage((String) languagesList.getSelectedItem());  
			}
		});  
	}
	private String[] removeFileNames(String[] s){
		List<String> langList=new ArrayList<String>();
		for(int i=0;i<s.length;i++){
			if(s[i].contains(".properties")){
				String[] str=s[i].split(".properties");
				langList.add(str[0]);
			}
		}
		String[] st=new String[0];
		//This toArray thing is actually just the worst thing ever. WHY CANT IT JUST RETURN A T[] AUTOMATICALLY?!?!?!?!?!?!?
		return langList.toArray(st);
	}
}
