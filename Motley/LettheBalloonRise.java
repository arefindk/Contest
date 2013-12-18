import java.util.HashMap;
import java.util.Scanner;


public class LettheBalloonRise {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int cs;
		String color,max ;
		max="";
		Scanner s = new Scanner(System.in);
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		
		while(s.hasNextInt()){
			cs= s.nextInt();
			
			if(cs==0) break;
			else{
				for(int i = 0; i<cs; i++){
					color=s.next();
					max=color;
					if(map.containsKey(color)){
						if(map.get(color)+1 > map.get(max)) max=color;
						map.put(color, map.get(color) + 1);
					}
					else{
						map.put(color, 1);
					}
				}
				
				System.out.println(max);
			}
		}
		
		s.close();
	}

}
