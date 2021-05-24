package tutorial.tdd.tutorialTdd.bs;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.sql.Connection;

import static org.mockito.Mockito.lenient;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import tutorial.tdd.tutorialTdd.dao.BookDAO;
import tutorial.tdd.tutorialTdd.domain.BookDTO;

@ExtendWith(MockitoExtension.class)
public class BookBSTest {
	
	@Mock
	private Connection connection;
	
	@Mock
	private BookDAO bookDAO;
	
	@InjectMocks
	private BookBS bookBS;
	
	private static BookBSTestHelper bsTestHelper;
	
	@BeforeAll
	static void setupClass() {
		bsTestHelper = new BookBSTestHelper();
	}
	
	@BeforeEach
	void setUpMocks() throws Exception {
		this.bookBS.setDAO(this.bookDAO);
		this.bookBS.setConnection(this.connection);
		initializeMockSearchBookFoundWithFullData();
	}
	

	@Test
	@Disabled
	void searchBookNotFound() throws Exception {
		BookDTO bookFound = null;
		
		bookFound = this.bookBS.searchBook(bsTestHelper.getBookNotFound());
		
		assertNull(bookFound);
	}
	
	@Test
	void searchBookFoundWithFullData() throws Exception {
		BookDTO bookFound = null;
		
		bookFound = this.bookBS.searchBook(bsTestHelper.getBookFoundWithFullData());
		
		assertTrue(bookFound.getId().equals(bsTestHelper.getBookFoundWithFullData().getId()));
		assertTrue(bookFound.getPages().equals(bsTestHelper.getBookFoundWithFullData().getPages()));
	}
	
	private void initializeMockSearchBookFoundWithFullData() throws Exception {
		when(this.bookDAO.searchBook(bsTestHelper.getBookFoundWithFullData(), this.connection))
			.thenReturn(bsTestHelper.getBookFoundWithFullData());
	}
}









