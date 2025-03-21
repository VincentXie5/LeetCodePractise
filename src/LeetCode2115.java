import java.util.*;

public class LeetCode2115 {


    // 采用递归，每一层判断recipe可否被制作出来，如果可以，则在recipes去掉，并将其加入到Supplies,然后递归调用findAllRecipes
    public List<String> findAllRecipes(String[] recipes, List<List<String>> ingredients, String[] supplies) {
        List<String> result = new ArrayList<>();
        List<String> nextRecipes = new ArrayList<>();
        List<List<String>> nextIngredients = new ArrayList<>();
        List<String> nextSupplies = new ArrayList<>(Arrays.asList(supplies));
        for (int i = 0; i < recipes.length; i++) {
            List<String> ingredient = ingredients.get(i);
            if (new HashSet<>(Arrays.asList(supplies)).containsAll(ingredient)) {
                nextSupplies.add(recipes[i]);
                result.add(recipes[i]);
            } else {
                nextRecipes.add(recipes[i]);
                nextIngredients.add(ingredient);
            }
        }
        if (nextRecipes.size() == recipes.length) {
            return new ArrayList<>();
        }
        result.addAll(findAllRecipes(nextRecipes.toArray(new String[0]), nextIngredients, nextSupplies.toArray(new String[0])));
        return result;
    }
}
