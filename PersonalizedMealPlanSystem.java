import java.util.ArrayList;
import java.util.List;

interface MealPlan {
    String getPlanName();
}

class VegetarianMeal implements MealPlan {
    public String getPlanName() {
        return "Vegetarian Meal Plan";
    }
}

class VeganMeal implements MealPlan {
    public String getPlanName() {
        return "Vegan Meal Plan";
    }
}

class KetoMeal implements MealPlan {
    public String getPlanName() {
        return "Keto Meal Plan";
    }
}

class HighProteinMeal implements MealPlan {
    public String getPlanName() {
        return "High-Protein Meal Plan";
    }
}

class Meal<T extends MealPlan> {
    private T plan;

    public Meal(T plan) {
        this.plan = plan;
    }

    public T getPlan() {
        return plan;
    }
}

class MealPlanner {
    public static <T extends MealPlan> Meal<T> generateMealPlan(T plan) {
        return new Meal<>(plan);
    }

    public static void displayAll(List<? extends MealPlan> plans) {
        for (MealPlan plan : plans) {
            System.out.println(plan.getPlanName());
        }
    }
}

public class PersonalizedMealPlanSystem {
    public static void main(String[] args) {
        VegetarianMeal vegetarian = new VegetarianMeal();
        VeganMeal vegan = new VeganMeal();
        KetoMeal keto = new KetoMeal();
        HighProteinMeal highProtein = new HighProteinMeal();

        Meal<VegetarianMeal> vegetarianPlan = MealPlanner.generateMealPlan(vegetarian);
        Meal<VeganMeal> veganPlan = MealPlanner.generateMealPlan(vegan);
        Meal<KetoMeal> ketoPlan = MealPlanner.generateMealPlan(keto);
        Meal<HighProteinMeal> highProteinPlan = MealPlanner.generateMealPlan(highProtein);

        System.out.println(vegetarianPlan.getPlan().getPlanName());
        System.out.println(veganPlan.getPlan().getPlanName());
        System.out.println(ketoPlan.getPlan().getPlanName());
        System.out.println(highProteinPlan.getPlan().getPlanName());

        List<MealPlan> allPlans = new ArrayList<>();
        allPlans.add(vegetarian);
        allPlans.add(vegan);
        allPlans.add(keto);
        allPlans.add(highProtein);

        MealPlanner.displayAll(allPlans);
    }
}