package composants.items;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ItemsConfiguration {
	@Bean(name="items")
	public Items itemsloader() {
		try{
			String filename = "xmltoparse.xml";
			ClassLoader classLoader = getClass().getClassLoader();
			File file = new File(classLoader.getResource(filename).getFile());
			JAXBContext jaxbContext = JAXBContext.newInstance(Items.class);
	
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			//Customer customer = (Customer) jaxbUnmarshaller.unmarshal(file);
			Items items = (Items) jaxbUnmarshaller.unmarshal(file);
			return items;
		}catch(Exception e){
			System.out.println(e);
			return null;
		}
	}
}
