package PreferenceManagers;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Scanner;

import frontEnd.SlogoView;

public class ImageManager {
private Map<Integer,String> imageMap=new HashMap<Integer,String>();
private static final String DEFAULT_IMAGEMAP="src/PreferenceManagers/ImagePrefs";
ResourceBundle myResources;
public ImageManager(){
	readImageFile(new File(DEFAULT_IMAGEMAP));
}

public void addImage(int id, String filePath){
	imageMap.put(id, filePath);
}
public String getPathByIndex(int id){
	if(imageMap.get(id)==null){
		SlogoView.showError(SlogoView.viewStats(), " no image defined at this index");
		return imageMap.get(0);
	}
	return imageMap.get(id);
}
public void readImageFile(File file){
	try {
		Scanner scanner = new Scanner(file);
		while(scanner.hasNext()){
			addImage(scanner.nextInt(), scanner.next());
			if(scanner.hasNext())
				scanner.nextLine();
		}
		scanner.close();
	} catch (FileNotFoundException e) {
		SlogoView.showError(SlogoView.viewStats(), "Bad File Name");
		e.printStackTrace();
	}
	
}
}
