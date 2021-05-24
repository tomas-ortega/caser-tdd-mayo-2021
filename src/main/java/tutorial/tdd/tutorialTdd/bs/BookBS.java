package tutorial.tdd.tutorialTdd.bs;

import java.sql.Connection;

import tutorial.tdd.tutorialTdd.dao.BookDAO;
import tutorial.tdd.tutorialTdd.domain.BookDTO;

public class BookBS {

	private BookDAO bookDAO = new BookDAO();
	private Connection connectionReference = null;

	public void setDAO(BookDAO bookDAO) {
		this.bookDAO = bookDAO;
	}
	
	public void setConnection(Connection connectionReference) {
		this.connectionReference = connectionReference;
	}
	
	public void newBook(BookDTO book) throws Exception {
		try {
			if (this.connectionReference == null) {
				this.connectionReference = DbConnection.getDbConnection();
			}
			
			this.bookDAO.newBook(book, this.connectionReference);
		} catch (Exception ex) {
			System.out.println(ex);
			throw ex;
		}
	}
	
	public BookDTO searchBook(BookDTO bookToSearch) throws Exception {
		BookDTO bookFound = null;

		try {
			if (this.connectionReference == null) {
				this.connectionReference = DbConnection.getDbConnection();
			}
			
			bookFound = this.bookDAO.searchBook(bookToSearch, connectionReference);
			
			return bookFound;
		} catch(Exception ex) {
			throw ex;
		}
	}
	
	public void deleteBook(Integer bookId) throws Exception {
		try {
			if (this.connectionReference == null) {
				this.connectionReference = DbConnection.getDbConnection();
			}
			
			this.bookDAO.deleteBook(bookId, connectionReference);
		} catch (Exception ex) {
			System.out.println(ex);
			throw ex;
		}
	}
	
	public void editBookEditorial(Integer bookId, String editorial) throws Exception {
		try {
			if (this.connectionReference == null) {
				this.connectionReference = DbConnection.getDbConnection();
			}
			
			this.bookDAO.modifyEditorialBook(bookId, editorial, connectionReference);
		} catch (Exception ex) {
			System.out.println(ex);
			throw ex;
		}
	}
}
