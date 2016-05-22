package kolacerfx.Data;

import java.text.ParseException;
import java.util.Arrays;
import java.util.Scanner;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Stepan
 */
public class DataParser {
	
	public static DataParser FinfoParser(){
		return new DataParser("•", ":");
	}
	
	private final String titleEnder, rowSplitter;
	
	public DataParser(String headerEnder, String rowSplitter){
		this.titleEnder = headerEnder;
		this.rowSplitter = rowSplitter;
		
	}
	
	public final DataSet parse(String input){
		try(Scanner sc = new Scanner(input)){
			String title = parseTitle(sc.nextLine());
			ObservableList<DataItem> items = FXCollections.observableArrayList();
					
            for(int lineNumber = 2; sc.hasNextLine(); lineNumber++){
                try{
					DataItem item = parseItem(sc.nextLine());
					items.add(item);
				} catch (ParseException e){
					System.err.format("Parse error on line %d: %s\n", lineNumber, e);
				}
            }
            return new DataSet(items, title);
		} catch(Exception e){
			System.err.println("Parse error: " + e);
			return null;
		}
	}
	
	protected String parseTitle(String line){
		return line.split(titleEnder)[0].trim();
	}
	
	protected DataItem parseItem(String line) throws ParseException{
		String[] segments = line.split(rowSplitter);
        if(segments.length<2){
            throw new ParseException(String.format("No row splitting string (%s)found", rowSplitter), -1);
        }
		
		String nazev = String.join(rowSplitter, Arrays.copyOfRange(segments, 0, segments.length - 1));
        
		Scanner countScanner = new Scanner(segments[segments.length - 1]);
		
        int count = countScanner.nextInt();
        
        return new DataItem(nazev, count);
	}
	
}
