package PreferenceManagers;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Scanner;

import frontEnd.SlogoView;

import jgame.JGColor;

public class ColorManager {
	ResourceBundle myResources;
 private Map<Integer,JGColor> colorMap=new HashMap<Integer,JGColor>();
 public static final String DEFAULT_COLORPATH="PreferenceManagers.ColorPrefs";
 public ColorManager(){
	 readColorFile(new File(DEFAULT_COLORPATH));
 }
 
 public void addNewColor(int index,int r, int g, int b){
	 colorMap.put(index, new JGColor(r,g,b));
 }
 public JGColor getColorByIndex(int color){
	 if(colorMap.get(color)==null)
		 SlogoView.showError(SlogoView.viewStats(),"No color defined at this index. Reverting to default");
		 //SlogoView.showError(SlogoView.viewStats(), myResources.getString("NoColorDefined"));
	 return colorMap.get(color);
 }
 public void readColorFile(File file) {
	 colorMap.clear();
	try {
		Scanner scanner=new Scanner(file);
		while(scanner.hasNext()){
			addNewColor(scanner.nextInt(),scanner.nextInt(),scanner.nextInt(),scanner.nextInt());
			if(scanner.hasNext())
			scanner.nextLine();
		}
		scanner.close();
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		SlogoView.showError(SlogoView.viewStats(), e.getMessage());
	}
	
 }
}
