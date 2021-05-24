package tutorial.tdd.tutorialTdd;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.logging.Level;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlPasswordInput;
import com.gargoylesoftware.htmlunit.html.HtmlSpan;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;

public class AmazonUIHtmlUnitTest {
	
	@BeforeAll
	static void deactivateHtmlUnitLog() {
		java.util.logging.Logger.getLogger("com.gargoylesoftware").setLevel(Level.OFF);
	}

	@Test
	@Disabled
	void testContentWebPageHtmlUnitSourceForge() throws Exception {
		try {
			WebClient webClient = new WebClient();
			
			HtmlPage homePage = webClient.getPage("https://htmlunit.sourceforge.io");
			assertTrue(homePage.getTitleText().contains("Welcome"));
			
			String pageAsXml = homePage.asXml();
			assertTrue(pageAsXml.contains("<div class=\"container-fluid\">"));
			
			String pageAsText = homePage.asText();
			assertTrue(pageAsText.contains("Support for the HTTP and HTTPS protocols"));
		} catch (Exception ex) {
			throw ex;
		}
	}
	
	@Test
	void checkPriceBookAmazon() throws Exception {
		try {
			WebClient webClient = new WebClient();
			webClient.getOptions().setThrowExceptionOnScriptError(false);
			String bookPrice = null;
			
			//Obtener la página principal de Amazon
			HtmlPage homePage = webClient.getPage("https://www.amazon.es");
			
			//Obtener un formulario dentro de la página principal, que se llama site-search
			HtmlForm searchForm = homePage.getFormByName("site-search");
				
			//Obtener la referencia de la casilla de texto donde el usuario escribe
			HtmlTextInput inputTextToSearchBook = searchForm.getInputByName("field-keywords");
			
			//Obtener la referencia del botón para hacer clic y que comience la búsqueda
			HtmlSubmitInput inputButtonToSearch = searchForm.getInputByValue("Ir");
			//HtmlElement inputButtonToSearch = searchForm.getElementsByAttribute("input", "value", "Ir").get(0);
			
			//Insertar el texto de la búsqueda del libro
			inputTextToSearchBook.type("albert camus el extranjero");
			
			//El usuario hace un clic en el botón de búsqueda y se va a otra página
			HtmlPage bookSearchResultPage = inputButtonToSearch.click();

			//Obtener todos los elementos <a/> de la página, cuya propiedad class sea igual a "a-link-normal s-no-outline"
			List<HtmlAnchor> bookListLinkDetail = bookSearchResultPage.getByXPath("//a[contains(@class, 'a-link-normal s-no-outline')]");
			
			//Obtengo la página a la que me lleva el hacer clic en el primer libro de la lista
			HtmlPage singleBookDetail = bookListLinkDetail.get(0).click();
			
			//Obtengo el elemento html span que contiene el precio que quiero comprobar
			HtmlSpan elementWithPrice = (HtmlSpan) singleBookDetail.getByXPath("//span[contains(@id, 'price')]").get(0);
			bookPrice = elementWithPrice.getTextContent();
			
			//Otra forma de obtener el elemento html span que contiene el precio que quiero comprobar
			DomElement elementWithPriceDomElement = singleBookDetail.getFirstByXPath("//span[contains(@id, 'price')]");
			bookPrice = elementWithPriceDomElement.getChildNodes().get(0).getNodeValue();
			
			assertTrue(bookPrice.equals("9,54 €"));
			
		} catch (Exception ex) {
			throw ex;
		}
	}
}



















