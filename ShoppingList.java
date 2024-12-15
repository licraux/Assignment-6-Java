import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;


/**
 * @author Federico M
*/

public class ShoppingList {

    private String lineofIngredient;
    private String lineofRecipe;
    private ArrayList<String> recipes;
    private ArrayList<String> ingredients;
    private Map<String, Integer> availableingrs;
    private Map<String, Integer> allRecipes;
    private Map<String, Integer> masterRecipe;
    private Map<String, Integer> finalshopping = new TreeMap<String, Integer>();
    Scanner input = new Scanner(System.in);


   public void scScanner(){

    ingredients = new ArrayList<>();
    availableingrs = new TreeMap<>();
    String [] splitingredients;

  
    while (input.hasNextLine()){
        lineofIngredient = input.nextLine();
        if (lineofIngredient.equals("RECIPE 1")){
            break;
        }
        if (lineofIngredient.equals("AVAILABLE")){
            continue;
        }
        splitingredients = lineofIngredient.split(" ");
        for (String ing : splitingredients){
            this.ingredients.add(ing);
        }
        int quantity = Integer.parseInt(this.ingredients.get(0));
        String inging = this.ingredients.get(2);
        if (this.ingredients.get(1).equals("kg")){
            quantity *= 1000;
        }
        availableingrs.put(inging, quantity);
        this.ingredients.clear();
        //System.out.println(availableingrs);
        
    }

    String []splitRecipe;
    recipes = new ArrayList<>();
    allRecipes = new HashMap<>();
    masterRecipe = new TreeMap<>();

    while (input.hasNextLine()){
        lineofRecipe = input.nextLine();
        
        if (lineofRecipe.equals("Shopping List:")){
            break;
        }
        if (lineofRecipe.contains("RECIPE")){
            allRecipes.clear();
            continue;
        }
        splitRecipe = lineofRecipe.split(" ");
        for (String recipe : splitRecipe){
            this.recipes.add(recipe);
        }
        int cantidad = Integer.parseInt(this.recipes.get(0));
        String reciprecip = this.recipes.get(2);
        if (this.recipes.get(1).equals("kg")){
            cantidad *= 1000;
        }
        allRecipes.put(reciprecip, cantidad);
        //System.out.println(allRecipes); 
        allRecipes.forEach((k, v) -> masterRecipe.merge(k, v, (v1, v2) -> v1 + v2));
        //System.out.println(masterRecipe);
        this.recipes.clear();
        allRecipes.clear();

        }
        printShoppingList();
        input.close();
    }

    private void printShoppingList(){
        for (Map.Entry<String, Integer> entry : masterRecipe.entrySet()){
            String newkey = entry.getKey();

            if (availableingrs.containsKey(newkey)){
                if (availableingrs.get(newkey) < entry.getValue()) {
                    Integer newvalue = entry.getValue() - availableingrs.get(newkey);
                    finalshopping.put(newkey, newvalue);
                }
            }
            else {
                Integer newvalue = entry.getValue();
                finalshopping.put(newkey, newvalue);
            }
        }
        System.out.println("Shopping List:");
        for (Map.Entry<String, Integer> entry : finalshopping.entrySet()) {
            System.out.println(entry.getValue() + " g " + entry.getKey());
        }
    }
}