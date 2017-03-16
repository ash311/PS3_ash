package pkgEmpty;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.junit.Test;

import pkgLibrary.*;

import pkgLibrary.Catalog;

public class Catalog_Test {

	@Test (expected = BookException.class)
	public void testGetBook() throws BookException {
		
		Catalog cat = ReadXMLFile();
		cat.GetBook("bk001");
	}
	
	@Test
	public void testGetBook2() throws BookException {
		
		Catalog cat = ReadXMLFile();
		cat.GetBook("bk101");
	}
	
	@Test (expected = BookException.class)
	public void testAddBook() throws BookException {
		
		Catalog cat = ReadXMLFile();
		Book b = new Book("bk101");
		cat.AddBook(0, b);
	}
	
	@Test
	public void testAddBook2() throws BookException {
		
		Catalog cat = ReadXMLFile();
		Book b = new Book("bk001");
		cat.AddBook(0, b);;
	}
	

	private static void WriteXMLFile(Catalog cat) {
		try {

			String basePath = new File("").getAbsolutePath();
			basePath = basePath + "//src//main//resources//XMLFiles//Books.xml";
			File file = new File(basePath);

			JAXBContext jaxbContext = JAXBContext.newInstance(Catalog.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			jaxbMarshaller.marshal(cat, file); //test
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
