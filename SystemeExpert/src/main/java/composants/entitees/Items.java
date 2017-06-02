package composants.entitees;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Items")
public class Items {

	private List<Item> items;

	public List<Item> getItems() {
		return items;
	}

	public Item getItem(int id){
		return items.get(id);
	}

	@XmlElement(name = "Item")
	public void setItems(List<Item> items) {
		this.items = items;
	}


	public String findResponse(String q) {
		int i = 0;
		int matchCount = 0;
		List<Integer> assocScores = new ArrayList<>();
		Item itemResponse = null;

		while (i < items.size()) {
			itemResponse = items.get(i);
			List<String> arrayExpert = new ArrayList<>(Arrays.asList(itemResponse.getQuestion().split(",")));
			arrayExpert.addAll(new ArrayList<>(Arrays.asList(itemResponse.getResponse().split(" "))));
			replace(arrayExpert);

			List<String> arrayUtilisateur = new ArrayList<>(Arrays.asList(q.split(" ")));
			replace(arrayUtilisateur);

			for(String value1 : arrayExpert){
				for(String value2 : arrayUtilisateur){
					if(StringUtils.stripAccents(value1).equals(StringUtils.stripAccents(value2))){ ++matchCount; break; }
				}
			}
			assocScores.add(matchCount);
			i++;
		}

		int maxIndex = 0;
		for (int id = 1; id < assocScores.size(); id++) {
			int newnumber = assocScores.get(id);
			if ((newnumber > assocScores.get(maxIndex))) {
				maxIndex = id;
			}
		}

		return items.get(maxIndex).getResponse();
	}

	public static void replace(List<String> strings)
	{
		ListIterator<String> iterator = strings.listIterator();
		while (iterator.hasNext())
		{
			iterator.set(iterator.next().toLowerCase());
		}
	}
}
