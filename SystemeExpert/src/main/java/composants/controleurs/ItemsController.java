package composants.controleurs;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import composants.entitees.Item;
import composants.entitees.Items;

@RestController
@RequestMapping("/services/items")
public class ItemsController {

	@Autowired
	private Items items;
	
	@RequestMapping(method = RequestMethod.GET)
	public List<Item> getall() {
		return items.getItems();
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public Item getItem(@PathVariable int id) {
		return items.getItem(id);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public Item newItem(@RequestParam(name="keywords") String keywords, @RequestParam(name="response") String response){
		Item i = new Item(keywords,response);
		items.getItems().add(i);
		return i;
	}
}
