package musReader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class musfile {
	private FileReader fr = null;
	private FileReader fr2 = null;
	private BufferedReader br = null;
	private BufferedReader br2 = null;
	//actual important data fields
	public ArrayList<Float> beatList = null;
	public ArrayList<Float> pitchList = null;
	public ArrayList<Integer> LEDPattern = null;
  //constructor
  public musfile(String beatFilePath, String pitchFilePath){
	  beatList = new ArrayList<Float>();
		pitchList = new ArrayList<Float>();
		LEDPattern= new ArrayList<Integer>();
		try{
			fr = new FileReader(beatFilePath);
			br = new BufferedReader(fr);
			fr2 = new FileReader(pitchFilePath);
			br2 = new BufferedReader(fr2);
		}catch(Exception e){
			System.out.println(e);
		}
  }
  //getters
  public ArrayList<Float> getBeat(){
	  return beatList;
  }
 
  public ArrayList<Float> getPitch(){
	  return pitchList;
  }
  
  public ArrayList<Integer> getLEDPattern(){
	  return LEDPattern;
  }

  
 //mutators
	public void readBeat()
	{
		try{				
			while(true){
				String str = br.readLine();
				if (str == null) {break;}
				float beatf = Float.parseFloat(str);
				Float beatF = new Float(beatf);
				beatList. add(beatF);
			}
		}
		catch(Exception e){
			System.out.println(e);
		}
		/*finally{
			try{fr.close();}
			catch(Exception e){System.out.println(e);}
		}*/
	}
	
	public void readPitch()
	{
		try{				
			while(true){
				String str = br2.readLine();
				if (str == null) {break;}
				float pitchf = Float.parseFloat(str);
				Float pitchF = new Float(pitchf);
				pitchList.add(pitchF);
			}
		}
		catch(Exception e){
			System.out.println(e);
		}
		/*finally{
			try{fr.close();fr2.close();}
			catch(Exception e){System.out.println(e);}
		}*/
	}
	
	public void processBeat(){
		ArrayList<Float> result= new ArrayList<Float>();
		int i=1;
		for(float time: beatList){
	      if (beatList.indexOf(time)>i){
			while(i<beatList.size()){
				if (beatList.get(i)-time>1){
					result.add(beatList.get(i));
					time=beatList.get(i);
					break;
				}
				i++;
			}
	      }
		}
		beatList=result;
	}
  public void processPitch(){
	  int limit = pitchList.size()/172;
	  for(int i=0; i <limit; i++) {
	      float sum = 0;
	      for (int j=0; j<172; j++) {
	     	  sum = sum + pitchList.get(i*172+j);
	      }
	      float avg = sum/172;
	      if (avg<10){
	         LEDPattern.add(0);
	      }
	      else if (avg<200){
	         LEDPattern.add(1);
	      }
	      else {
	         LEDPattern.add(2);
	      }
	  }
   }
}
  
 
 