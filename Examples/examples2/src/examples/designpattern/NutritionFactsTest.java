/**
 * 
 */
package examples.designpattern;

/**
 * @author scirka
 *
 */
public class NutritionFactsTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		NutritionFacts cocaCola = new NutritionFacts.Builder(240, 8).calories(100).sodium(35).carbohydrate(27).build();
		System.out.println("Coca-cola nutition facts: " + cocaCola);
	}

}
