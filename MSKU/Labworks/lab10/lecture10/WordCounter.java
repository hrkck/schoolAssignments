package lecture10;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class WordCounter {
	
	Map<String,Integer> wordMap = new HashMap<>();

	
	public void countWords(String filename) throws IOException{
		BufferedReader br = new BufferedReader(new FileReader(filename));
		String sCurrentLine;
		while ((sCurrentLine = br.readLine()) != null) {
			System.out.println(sCurrentLine);
			processString(sCurrentLine);
		}
		br.close();

	}


	private void processString(String sCurrentLine) {
		  StringTokenizer st = new StringTokenizer(sCurrentLine, " .!?:'\"");
		  while (st.hasMoreTokens()){
			  String word = st.nextToken();
			  System.out.println("processing " + word);
			  if (wordMap.containsKey(word)){
				  int occurrence =wordMap.get(word);
				  occurrence++;
				  wordMap.put(word, occurrence);
			  }else{
				  wordMap.put(word, 1);
			  }	      
		  }
	}


	public Map<String, Integer> getWordMap() {
		return wordMap;
	}

}
