package textPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JPanel;

import backEnd.Managers.LanguageManager;

public class LanguageBar extends JPanel {
	LanguageManager languageManager;
public LanguageBar(LanguageManager langManage){
	super();
	languageManager=langManage;
	makeLangList(this);
}
	private void makeLangList(JPanel homePanel){
		final JComboBox languagesList=new JComboBox((removeFileNames(new File("src/resources").list())));
		homePanel.add(languagesList);
		languagesList.setSelectedIndex(1);
		languagesList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{       
				languageManager.setLanguage((String) languagesList.getSelectedItem());  
				System.out.println(languageManager.getLanguage());
			}
		});  
	}
	private Object[] removeFileNames(String[] s){
		List<String> langList=new ArrayList<String>();
		for(int i=0;i<s.length;i++){
			if(s[i].contains(".properties")){
			String[] str=s[i].split(".properties");
			System.out.println(str[0]);
			langList.add(str[0]);
			}
		}
		return langList.toArray();
	}
}
