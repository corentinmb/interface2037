package composants.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import composants.items.Item;
import composants.items.Items;

@RestController
@RequestMapping("/services/items")
public class ItemsWS {

	@Autowired
	private Items items;
	
	@RequestMapping(path="/getall", method = RequestMethod.GET)
	public List<Item> getall() {
		return items.getItem();
	}
	
	@RequestMapping(path="/getresponse", method = RequestMethod.POST)
	public String getItem(@RequestParam(name="question") String question){
		//String question = "Comment t'appelles-tu ?";
		Item item = items.getItem().stream()
			.filter(i -> i.getQuestion().equals(question))
			.findFirst().get();
		return item.getResponse();
	}
}