package composants.items;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Items")
public class Items {

	private List<Item> item;

	public List<Item> getItem() {
		return item;
	}

    @XmlElement(name = "Item")
	public void setItem(List<Item> item) {
		this.item = item;
	}


}
