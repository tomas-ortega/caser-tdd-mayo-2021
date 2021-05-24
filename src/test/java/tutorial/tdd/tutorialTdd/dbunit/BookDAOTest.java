package tutorial.tdd.tutorialTdd.dbunit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.dbunit.Assertion;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.database.QueryDataSet;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.dataset.xml.FlatXmlProducer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.xml.sax.InputSource;

import tutorial.tdd.tutorialTdd.bs.BookBS;


public class BookDAOTest {
	private static IDataSet initialDataSet;
	private static IDatabaseConnection databaseConnection;
	
	private BookBS bookBs = new BookBS();
	
	@BeforeAll
	static void getConnection() {
		databaseConnection = UtilsDbUnit.getConnection();
		initialDataSet = UtilsDbUnit.getInitialDataSet();
	}
	
	@BeforeEach
	void setupOperation() {
		UtilsDbUnit.importData(databaseConnection);
	}
	
	@Test
	@DisplayName("Test check rowCount initial DataSet")
	void testDatosCargados() throws Exception {
		int rowCount = 0;
		
		assertNotNull(initialDataSet);
		
		rowCount = initialDataSet.getTable("book").getRowCount();
		
		assertEquals(2, rowCount);
	}
	
	@Test
	@DisplayName("Test read all rows from book table")
	public void testReadAllRows() throws Exception {
		QueryDataSet queryDataSet = new QueryDataSet(BookDAOTest.databaseConnection);
		queryDataSet.addTable("book", "SELECT * FROM book");
		
		Assertion.assertEquals(BookDAOTest.initialDataSet, queryDataSet);
	}
	
	@Test
	@DisplayName("Test delete a book with id 1")
	public void deleteBook() throws Exception {
		IDataSet expectedDataSet = new FlatXmlDataSet(
				new FlatXmlProducer(new InputSource(UtilsDbUnit.testDeleteBookWithId1)));
		
		bookBs.deleteBook(1);
		
		String[] table = { "book" };
		IDataSet realDataSet = BookDAOTest.databaseConnection.createDataSet(table);
		
		Assertion.assertEquals(expectedDataSet, realDataSet);
	}
	
	@AfterEach
	void setupTearDown() {
		UtilsDbUnit.deleteData(BookDAOTest.databaseConnection);
	}
}
