package pl.coderslab.dao;

import pl.coderslab.exception.NotFoundException;
import pl.coderslab.model.RecipePlan;
import pl.coderslab.utils.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RecipePlanDao {
    private static final String ADD_RECIPE_PLAN_QUERY = "INSERT INTO recipe_plan (recipe_id, meal_name, display_order, day_name_id, plan_id) VALUES (?,?,?,?,?);";
    private static final String READ_RECIPE_PLAN_QUERY = "SELECT * FROM recipe_plan WHERE id = ?";
    private static final String DELETE_RECIPE_PLAN_QUERY = "DELETE FROM recipe_plan WHERE id = ?";
    private static final String DELETE_RECIPE_PLAN_BY_PLAN_ID_AND_RECIPE_ID_QUERY = "DELETE FROM recipe_plan WHERE recipe_id = ? AND plan_id=?;";
    private static final String READ_ID_QUERY = "SELECT id FROM recipe_plan WHERE recipe_id=? AND meal_name=? AND display_order=? AND day_name_id=? AND plan_id=?;";

    public RecipePlan create(RecipePlan recipePlan) {
        try (
                Connection connection = DbUtil.getConnection();
                PreparedStatement addRecipeToPlan = connection.prepareStatement(ADD_RECIPE_PLAN_QUERY)) {
            addRecipeToPlan.setInt(1, recipePlan.getRecipeId());
            addRecipeToPlan.setString(2, recipePlan.getMealName());
            addRecipeToPlan.setInt(3, recipePlan.getDisplayOrder());
            addRecipeToPlan.setInt(4, recipePlan.getDayNameId());
            addRecipeToPlan.setInt(5, recipePlan.getPlanId());
            addRecipeToPlan.executeUpdate();
            System.out.println("Recipe was added to plan");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public int readId(int recipeId, String mealName, int displayOrder, int dayId, int planId) {
        int recipePlanId = 0;
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement readId = connection.prepareStatement(READ_ID_QUERY)) {
            readId.setInt(1, recipeId);
            readId.setString(2, mealName);
            readId.setInt(3, displayOrder);
            readId.setInt(4, dayId);
            readId.setInt(5, planId);
            try (ResultSet resultSet = readId.executeQuery()) {
                if (resultSet.next()) {
                    recipePlanId = resultSet.getInt("id");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return recipePlanId;
    }


    public RecipePlan read(int recipePlanId) {
        RecipePlan recipePlan = new RecipePlan();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement readRecipePlan = connection.prepareStatement(READ_RECIPE_PLAN_QUERY)) {
            readRecipePlan.setInt(1, recipePlanId);
            try (ResultSet resultSet = readRecipePlan.executeQuery()) {
                if (resultSet.next()) {
                    recipePlan.setDayNameId(resultSet.getInt("day_name_id"));
                    recipePlan.setDisplayOrder(resultSet.getInt("display_order"));
                    recipePlan.setMealName(resultSet.getString("meal_name"));
                    recipePlan.setRecipeId(resultSet.getInt("recipe_id"));
                    recipePlan.setPlanId(resultSet.getInt("plan_id"));
                    recipePlan.setId(resultSet.getInt("id"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return recipePlan;
    }

    public void deleteDish(int recipePlanId) {
        try (Connection connection = DbUtil.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_RECIPE_PLAN_QUERY);
            preparedStatement.setInt(1, recipePlanId);
            preparedStatement.executeUpdate();
            boolean deleted = preparedStatement.execute();
            if (!deleted) {
                throw new NotFoundException("Recipe not found in Plan");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteInAllPlan(int recipeId, int planId) {
        try (Connection connection = DbUtil.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_RECIPE_PLAN_BY_PLAN_ID_AND_RECIPE_ID_QUERY);
            preparedStatement.setInt(1, recipeId);
            preparedStatement.setInt(2, planId);
            preparedStatement.executeUpdate();
            boolean deleted = preparedStatement.execute();
            if (!deleted) {
                throw new NotFoundException("Product not found");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
