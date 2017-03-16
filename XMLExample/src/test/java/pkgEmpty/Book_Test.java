package pkgEmpty;

import java.io.File;
import java.util.Date;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.junit.Test;

import pkgLibrary.Book;
import pkgLibrary.Catalog;

public class Book_Test {

	@Test
	public void test() {
		
		Book book = new Book();
		book.setAuthor("Snoopy");
		book.setPrice(25);
		book.setCost(15);
		book.setDescription("Piloting planes has never been made easier");
		book.setGenre("Non-Fiction");
		book.setId("bk900");
		Date d = new Date();
		book.setPublish_date(d);
		book.setTitle("The Flying Ace");
		
		Book book2 = new Book("bk900", "Snoopy", "The Flying Ace", "Non-Fiction", 25, d, "Piloting planes has never been made easier", 10);
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
