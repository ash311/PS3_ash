package pkgLibrary;

import java.io.File;
import java.util.ArrayList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)

public class Catalog {

	@XmlAttribute
	int id;	
	
	@XmlElement(name="book")
	ArrayList<Book> books;
	
	
	public int getId() {
		return id;
	}
	

	public void setId(int id) {
		this.id = id;
	}
	public ArrayList<Book> getBooks() {
		return books;
	}
	

	public void setBooks(ArrayList<Book> books) {
		this.books = books;
	}
	

	public Book GetBook(String id) throws BookException{
		Book tempBook = null;
		
			for(Book bk : books){
				if(bk.getId().equals(id))
					tempBook= bk;
			}
		if (tempBook==null){
			tempBook=new Book(id);
			throw new BookException(tempBook);
		}
		else{
			return tempBook;
		}
		
	}
	
	public void AddBook(int id, Book book) {
		try{
			Catalog cat = ReadXMLFile();
			if (id == cat.getId()) {
			for(int i = 0; i < cat.getBooks().size(); i++){
				if(book.getId().equals(cat.getBooks().get(i).getId())) {
					System.out.println("Book already exists");
					throw new BookException(book);
				}
				
			}
			
			cat.getBooks().add(book);
			
			
			}
		}
		catch(BookException e){
			
		}
	}
	
	private static void WriteXMLFile(Catalog cat) {
		try {

			String basePath = new File("").getAbsolutePath();
			basePath = basePath + "//src//main//resources//XMLFiles//Books.xml";
			File file = new File(basePath);

			JAXBContext jaxbContext = JAXBContext.newInstance(Catalog.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			jaxbMarshaller.marshal(cat, file);
			jaxbMarshaller.marshal(cat, System.out);

		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}
	
	private static Catalog ReadXMLFile() {

		Catalog cat = null;

		String basePath = new File("").getAbsolutePath();
		basePath = basePath + "//src//main//resources//XMLFiles//Books.xml";
		File file = new File(basePath);


		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Catalog.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			cat = (Catalog) jaxbUnmarshaller.unmarshal(file);
		
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return cat;
	}
	
	
}
