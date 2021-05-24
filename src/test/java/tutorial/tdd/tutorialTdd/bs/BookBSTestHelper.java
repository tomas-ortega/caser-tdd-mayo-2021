package tutorial.tdd.tutorialTdd.bs;

import tutorial.tdd.tutorialTdd.domain.BookDTO;

public class BookBSTestHelper {
	private BookDTO bookNotFound;
	private BookDTO bookFoundWithFullData;
	
	public BookBSTestHelper() {
		this.initBookNotFound();
		this.initBookFoundWithFullData();
	}
	
	private void initBookNotFound() {
		this.bookNotFound = null;
	}
	
	public BookDTO getBookNotFound() {
		return this.bookNotFound;
	}
	
	private void initBookFoundWithFullData() {
		this.bookFoundWithFullData = new BookDTO(2, 
												 "Los pilares de la tierra", 
												 "Ken Follet", 
												 "Planeta", 
												 498);
	}
	
	public BookDTO getBookFoundWithFullData() {
		return this.bookFoundWithFullData;
	}
}
