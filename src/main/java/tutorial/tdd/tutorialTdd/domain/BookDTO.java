package tutorial.tdd.tutorialTdd.domain;

import java.io.Serializable;
import java.util.Objects;

public class BookDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String title;
	private String author;
	private String editorial;
	private Integer pages;
	
	public BookDTO(Integer id, String title, String author, String editorial, Integer pages) {
		this.id = id;
		this.title = title;
		this.author = author;
		this.editorial = editorial;
		this.pages = pages;
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getAuthor() {
		return author;
	}
	
	public void setAuthor(String author) {
		this.author = author;
	}
	
	public String getEditorial() {
		return editorial;
	}
	
	public void setEditorial(String editorial) {
		this.editorial = editorial;
	}
	
	public Integer getPages() {
		return pages;
	}
	
	public void setPages(Integer pages) {
		this.pages = pages;
	}
	
	@Override
	public int hashCode() {
		int hash = 5;
		hash = 19 * hash + Objects.hashCode(this.id);
		
		return hash;
	}
	
	@Override
	public boolean equals(Object otherObject) {
		boolean sameBook = false;
		
		if (otherObject instanceof BookDTO) {
			BookDTO otherBook = (BookDTO) otherObject;
			
			if (this.id.equals(otherBook.id)) {
				sameBook = true;
			}
		}
		
		return sameBook;
	}
}






