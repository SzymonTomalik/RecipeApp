package pl.coderslab.dao;

import org.mindrot.jbcrypt.BCrypt;
import pl.coderslab.model.Plan;

import pl.coderslab.model.Recipe;

import pl.coderslab.model.PlanDetails;

import pl.coderslab.utils.DbUtil;

import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class PlanDao {
    // SQL QUERY
    private static final String CREATE_PLAN_QUERY = "INSERT INTO plan(name,description,created,admin_id) VALUES (?,?,NOW(),?);";
    private static final String DELETE_PLAN_QUERY = "DELETE FROM plan where id = ?;";
    private static final String FIND_ALL_PLANS_QUERY = "SELECT * FROM plan;";
    private static final String READ_PLAN_QUERY = "SELECT * from plan where id = ?;";
    private static final String UPDATE_PLAN_QUERY = "UPDATE	plan SET name = ? , description = ?, created = ?, admin_id = ? WHERE	id = ?;";
    private static final String AMOUNT_PLANS_BY_ADMIN_ID = "SELECT  COUNT(*) from plan WHERE  admin_id = ?";
    private static final String SELECT_ALL_PLANS_SORTED = "SELECT * FROM plan WHERE admin_id=? ORDER BY created DESC ;";
    private static final String DETAILS_OF_RECENT_PLAN_BY_ADMIN_ID = "SELECT day_name.name as day_name, meal_name,  recipe.name as recipe_name, recipe.description as recipe_description, recipe_id\n" +
            "FROM `recipe_plan`\n" +
            "         JOIN day_name on day_name.id=day_name_id\n" +
            "         JOIN recipe on recipe.id=recipe_id WHERE\n" +
            "        recipe_plan.plan_id =  (SELECT MAX(id) from plan WHERE admin_id = ?)\n" +
            "ORDER by day_name.display_order, recipe_plan.display_order;";
    private static final String NAME_OF_RECENT_PLAN = "SELECT name FROM plan WHERE plan.id = (SELECT MAX(id) from plan WHERE admin_id = ?);";
    private static final String DETAILS_OF_PLAN_BY_PLAN_ID = "SELECT recipe_plan.id as recipe_plan_id, day_name.name as day_name, meal_name, recipe.name as recipe_name, recipe.description as recipe_description, recipe_id\n" +
            "FROM `recipe_plan`\n" +
            "         JOIN day_name on day_name.id=day_name_id\n" +
            "         JOIN recipe on recipe.id=recipe_id WHERE plan_id = ? -- zamiast 6 należy wstawić id planu do pobrania --\n" +
            "ORDER by day_name.display_order, recipe_plan.display_order;";

    private static final String DELETE_RECIPES_FROM_PLAN = "DELETE FROM recipe_plan WHERE plan_id =?;";

    //Get plan by id
    public Plan read(Integer planId) {
        Plan plan = new Plan();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(READ_PLAN_QUERY)
        ) {
            statement.setInt(1, planId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    plan.setId(resultSet.getInt("id"));
                    plan.setName(resultSet.getString("name"));
                    plan.setDescription(resultSet.getString("description"));
                    plan.setCreated(resultSet.getString("created"));
                    plan.setAdminId(resultSet.getInt("admin_id"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return plan;
    }

    //Return all plans

    public List<Plan> findAll() {
        List<Plan> planList = new ArrayList<>();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_PLANS_QUERY);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Plan planToAdd = new Plan();
                planToAdd.setId(resultSet.getInt("id"));
                planToAdd.setName(resultSet.getString("name"));
                planToAdd.setDescription(resultSet.getString("description"));
                planToAdd.setCreated(resultSet.getString("created"));
                planToAdd.setAdminId(resultSet.getInt("admin_id"));
                planList.add(planToAdd);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return planList;
    }

    //Update plan
    public void update(Plan plan) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_PLAN_QUERY)) {
            statement.setInt(5, plan.getId());
            statement.setString(1, plan.getName());
            statement.setString(2, plan.getDescription());
            statement.setString(3, plan.getCreated());
            statement.setInt(4, plan.getAdminId());
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Create Plan
    public Plan create(Plan plan) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement insertStm = connection.prepareStatement(CREATE_PLAN_QUERY,
                     PreparedStatement.RETURN_GENERATED_KEYS)) {
            insertStm.setString(1, plan.getName());
            insertStm.setString(2, plan.getDescription());
            insertStm.setInt(3, plan.getAdminId());
            int result = insertStm.executeUpdate();

            if (result != 1) {
                throw new RuntimeException("Execute update returned " + result);
            }

            try (ResultSet generatedKeys = insertStm.getGeneratedKeys()) {
                if (generatedKeys.first()) {
                    plan.setId(generatedKeys.getInt(1));
                    return plan;
                } else {
                    throw new RuntimeException("Generated key was not found");
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //Remove plan by id
    public boolean delete(Integer planId) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_PLAN_QUERY)) {
            statement.setInt(1, planId);
            statement.executeUpdate();
            if (!statement.execute()) {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    public int amountOfPlans(int adminId) {
        int result = 0;
        try (Connection connection = DbUtil.getConnection()) {
            PreparedStatement findCount = connection.prepareStatement(AMOUNT_PLANS_BY_ADMIN_ID);
            findCount.setInt(1, adminId);
            try (ResultSet resultSet = findCount.executeQuery()) {
                if (resultSet.next()) {
                    result = resultSet.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }


    public List<Plan> findAllByDate(int id) {
        List<Plan> planList = new ArrayList<>();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ALL_PLANS_SORTED)) {

            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Plan planToAdd = new Plan();
                planToAdd.setId(resultSet.getInt("id"));
                planToAdd.setName(resultSet.getString("name"));
                planToAdd.setDescription(resultSet.getString("description"));
                planToAdd.setCreated(resultSet.getString("created"));
                planToAdd.setAdminId(resultSet.getInt("admin_id"));
                planList.add(planToAdd);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return planList;
    }

    public HashMap<String, List<PlanDetails>> detailsOfRecentPlan(int adminId) {
        HashMap<String, List<PlanDetails>> planDetailsMap = new HashMap<>();
        List<PlanDetails> planDetailsList = new ArrayList<>();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(DETAILS_OF_RECENT_PLAN_BY_ADMIN_ID)) {
            statement.setInt(1, adminId);
            ResultSet resultSet = statement.executeQuery();
            String valueOfdayName = "";
            while (resultSet.next()) {
                String tmp = resultSet.getString("day_name");
                if (!valueOfdayName.equals(tmp)) {
                    planDetailsList = new ArrayList<>();
                }
                PlanDetails planToAdd = new PlanDetails();
//                planToAdd.setDayName(resultSet.getString("day_name"));
                planToAdd.setMealName(resultSet.getString("meal_name"));
                planToAdd.setRecipeName(resultSet.getString("recipe_name"));
                planToAdd.setRecipeDescription(resultSet.getString("recipe_description"));
                planToAdd.setId(resultSet.getInt("recipe_id"));
                planDetailsList.add(planToAdd);
                planDetailsMap.put(tmp, planDetailsList);
                valueOfdayName = tmp;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return planDetailsMap;
    }

    public String getNameOfRecentPlan(int adminId) {
        String result = "";
        try (Connection connection = DbUtil.getConnection()) {
            PreparedStatement findCount = connection.prepareStatement(NAME_OF_RECENT_PLAN);
            findCount.setInt(1, adminId);
            try (ResultSet resultSet = findCount.executeQuery()) {
                if (resultSet.next()) {
                    result = resultSet.getString(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public Map<String, List<PlanDetails>> detailsOfPlan(int planId) {
        Map<String, List<PlanDetails>> planDetailsMap = new LinkedHashMap<>();
        List<PlanDetails> planDetailsList = new ArrayList<>();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(DETAILS_OF_PLAN_BY_PLAN_ID)) {
            statement.setInt(1, planId);
            ResultSet resultSet = statement.executeQuery();
            String valueOfdayName = "";
            while (resultSet.next()) {
                String tmp = resultSet.getString("day_name");
                if (!valueOfdayName.equals(tmp)) {
                    planDetailsList = new ArrayList<>();
                }
                PlanDetails planToAdd = new PlanDetails();
//                planToAdd.setDayName(resultSet.getString("day_name"));
                planToAdd.setRecipePlanId(resultSet.getInt("recipe_plan_id"));
                planToAdd.setMealName(resultSet.getString("meal_name"));
                planToAdd.setRecipeName(resultSet.getString("recipe_name"));
                planToAdd.setRecipeDescription(resultSet.getString("recipe_description"));
                planToAdd.setId(resultSet.getInt("recipe_id"));
                planDetailsList.add(planToAdd);
                planDetailsMap.put(tmp, planDetailsList);
                valueOfdayName = tmp;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return planDetailsMap;
    }

    public boolean deleteRecipesFromPlan(int planId) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_RECIPES_FROM_PLAN)) {
            statement.setInt(1, planId);
            statement.executeUpdate();
            if (!statement.execute()) {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }


}

