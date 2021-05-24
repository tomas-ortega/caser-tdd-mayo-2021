package tutorial.tdd.tutorialTdd.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import tutorial.tdd.tutorialTdd.domain.BookDTO;

public class BookDAO {
	
	public void newBook(BookDTO book, Connection connectionReference) throws SQLException {
		StringBuilder insertSql = new StringBuilder();
		PreparedStatement pstm = null;
		try {
			insertSql.append("INSERT INTO ");
			insertSql.append("book");
			insertSql.append("(id,title,author,editorial,pages)");
			insertSql.append(" VALUES (?,?,?,?,?)");
			pstm = connectionReference.prepareStatement(insertSql.toString());
			pstm.setInt(1, book.getId());
			pstm.setString(2, book.getTitle());
			pstm.setString(3, book.getAuthor());
			pstm.setString(4, book.getEditorial());
			pstm.setInt(5, book.getPages());
			pstm.execute();
		} catch (SQLException e) {
			throw e;
		}
	}
	
	public void deleteBook(Integer idLibro, Connection connectionReference) throws SQLException {
		StringBuilder deleteSQL = null;
		PreparedStatement pstm = null;
		try {
			deleteSQL = new StringBuilder();
			deleteSQL.append("DELETE FROM book WHERE id = ?");
			pstm = connectionReference.prepareStatement(deleteSQL.toString());
			pstm.setInt(1, idLibro);
			pstm.execute();
		} catch (SQLException e) {
			throw e;
		}
	}
	
	public void modifyEditorialBook(Integer bookId, String editorial, Connection connectionReference)
			throws SQLException {
		StringBuilder updateSql = null;
		PreparedStatement pstm = null;
		try {
			updateSql = new StringBuilder();
			updateSql.append("UPDATE book SET editorial = ? WHERE id = ?");
			pstm = connectionReference.prepareStatement(updateSql.toString());
			pstm.setString(1, editorial);
			pstm.setInt(2, bookId);
			pstm.execute();
		} catch (SQLException e) {
			throw e;
		}
	}
	
	public BookDTO searchBook(BookDTO bookToSearch, Connection connectionReference) throws SQLException {
		StringBuilder selectSql = null;
		PreparedStatement pstm = null;
		ResultSet resultSet = null;
		BookDTO bookFound = null;
		
		try {
			selectSql = new StringBuilder();
			
			selectSql.append("SELECT ");
			selectSql.append("id,");
			selectSql.append("title,");
			selectSql.append("author,");
			selectSql.append("editorial,");
			selectSql.append("pages");
			
			selectSql.append(" FROM ");
			selectSql.append("book");
			
			selectSql.append(" WHERE ");
			selectSql.append("id = ?");
			
			pstm = connectionReference.prepareStatement(selectSql.toString());
			
			pstm.setInt(1, bookToSearch.getId());
			
			resultSet = pstm.executeQuery();
			
			while(resultSet.next()) {
				bookFound = new BookDTO(resultSet.getInt("id"), 
										resultSet.getString("title"), 
										resultSet.getString("author"), 
										resultSet.getString("editorial"), 
										resultSet.getInt("pages"));
			}
			
			return bookFound;
		} catch(SQLException ex) {
			throw ex;
		}
	}
}











