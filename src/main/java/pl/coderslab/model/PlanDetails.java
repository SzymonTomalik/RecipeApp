package pl.coderslab.model;

public class PlanDetails {
    private int id;
    private String dayName;
    private int recipePlanId;
    private String mealName;
    private String recipeName;
    private String recipeDescription;

    public int getRecipePlanId() {
        return recipePlanId;
    }

    public void setRecipePlanId(int recipePlanId) {
        this.recipePlanId = recipePlanId;
    }

    public PlanDetails() {

    }

    public PlanDetails(String dayName, String mealName, String recipeName, String recipeDescription) {
        this.dayName = dayName;
        this.mealName = mealName;
        this.recipeName = recipeName;
        this.recipeDescription = recipeDescription;
    }

    public String getDayName() {
        return dayName;
    }

    public void setDayName(String dayName) {
        this.dayName = dayName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMealName() {
        return mealName;
    }

    public void setMealName(String mealName) {
        this.mealName = mealName;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public String getRecipeDescription() {
        return recipeDescription;
    }

    public void setRecipeDescription(String recipeDescription) {
        this.recipeDescription = recipeDescription;
    }
}
