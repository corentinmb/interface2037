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
		System.out.println(items.toString());
		int i = 0;
		List<Integer> assocScores = new ArrayList<>();
		Item itemResponse = null;

		while (i < items.size()) {
			int matchCount = 0;
			itemResponse = items.get(i);
			List<String> arrayExpert = new ArrayList<>(Arrays.asList(itemResponse.getQuestion().split(",")));
			//arrayExpert.addAll(new ArrayList<>(Arrays.asList(itemResponse.getResponse().split(" "))));
			replace(arrayExpert);

			List<String> arrayUtilisateur = new ArrayList<>(Arrays.asList(q.split(" ")));
			replace(arrayUtilisateur);

			for(String value1 : arrayExpert){
				for(String value2 : arrayUtilisateur){
					if(StringUtils.stripAccents(value1).equals(StringUtils.stripAccents(value2)) ||
							StringUtils.stripAccents(value2).contains(StringUtils.stripAccents(value1))){ ++matchCount; break; }
				}
			}
			assocScores.add(matchCount);
			i++;
		}

		int maxIndex = assocScores.get(0);
		for (int id = 1; id < assocScores.size(); id++) { //size -1
			int newnumber = assocScores.get(id);
			if ((newnumber > assocScores.get(maxIndex)) && newnumber > 1) {
				maxIndex = id;
			}
		}
		if(maxIndex == 0 && assocScores.get(maxIndex) <= 1)
			maxIndex = -1;

		if(maxIndex != -1)
			return items.get(maxIndex).getResponse();
		else
			return null;
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
