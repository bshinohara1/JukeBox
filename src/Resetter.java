import java.time.LocalDate;
import java.time.LocalDateTime;

public class Resetter {
	
	LocalDateTime dates;
	LocalDate cur;
	
	
	public Resetter(){
		cur = LocalDate.now();
		dates = cur.atStartOfDay();
		//cur = LocalDate.now().
		System.out.print(dates);
	}
	public void checkday(){
		
	}

}
