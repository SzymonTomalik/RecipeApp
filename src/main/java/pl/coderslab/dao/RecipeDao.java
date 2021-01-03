package pl.coderslab.dao;

import pl.coderslab.exception.NotFoundException;
import pl.coderslab.model.Admins;
import pl.coderslab.model.Recipe;
import pl.coderslab.utils.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RecipeDao {

    private static final String CREATE_RECIPE_QUERY = "INSERT INTO recipe(name,ingredients,description,created,updated,preparation_time,preparation,admin_id) VALUES (?,?,?,NOW(),NOW(),?,?,?);";
    private static final String DELETE_RECIPE_QUERY = "DELETE FROM recipe where id = ?;";
    private static final String FIND_ALL_RECIPE_QUERY = "SELECT * FROM recipe;";
    private static final String READ_RECIPE_QUERY = "SELECT * from recipe where id = ?;";
    private static final String UPDATE_RECIPE_QUERY = "UPDATE recipe SET name = ? , ingredients = ?, description = ?, updated =NOW(), preparation_time =?, preparation =?,admin_id =? WHERE	id = ?;";
    private static final String SELECT_RECIPE_BY_ADMIN_ID = "SELECT  COUNT(*) from recipe WHERE  admin_id = ?";


    /**
     * Create recipe
     *
     * @param recipe
     * @return
     */


    public Recipe create(Recipe recipe) {

        try (Connection connection = DbUtil.getConnection();

             PreparedStatement createRecipe = connection.prepareStatement(CREATE_RECIPE_QUERY,
                     PreparedStatement.RETURN_GENERATED_KEYS)) {
            createRecipe.setString(1, recipe.getName());
            createRecipe.setString(2, recipe.getIngredients());
            createRecipe.setString(3, recipe.getDescription());
            createRecipe.setInt(4, recipe.getPreparationTime());
            createRecipe.setString(5, recipe.getPreparation());
            createRecipe.setInt(6, recipe.getAdminId());
            int result = createRecipe.executeUpdate();
            System.out.println("Recipe create complete");
            if (result != 1) {
                throw new RuntimeException("Execute update returned " + result);
            }
            try (ResultSet generatedKeys = createRecipe.getGeneratedKeys()) {
                if (generatedKeys.first()) {
                    recipe.setID(generatedKeys.getInt(1));
                    return recipe;
                } else {
                    throw new RuntimeException("Generated key was not found");
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Get recipe by id
     *
     * @param recipeId
     * @return
     */
    public Recipe read(int recipeId) {
        Recipe recipe = new Recipe();
        try (Connection connection = DbUtil.getConnection();
                /*Connection connection = DbUtil2.connect("scrumlab");*/
             PreparedStatement readRecipe = connection.prepareStatement(READ_RECIPE_QUERY)) {
            readRecipe.setInt(1, recipeId);
            try (ResultSet resultSet = readRecipe.executeQuery()) {
                while (resultSet.next()) {
                    recipe.setID(resultSet.getInt("id"));
                    recipe.setPreparationTime(resultSet.getInt("preparation_time"));
                    recipe.setAdminId(resultSet.getInt("admin_id"));
                    recipe.setName(resultSet.getString("name"));
                    recipe.setIngredients(resultSet.getString("ingredients"));
                    recipe.setDescription(resultSet.getString("description"));
                    recipe.setCreated(resultSet.getString("created"));
                    recipe.setUpdated(resultSet.getString("updated"));
                    recipe.setPreparation(resultSet.getString("preparation"));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return recipe;
    }

    /**
     * Update recipe
     *
     * @param recipe
     */

    public void update(Recipe recipe) {

        try (Connection connection = DbUtil.getConnection();
             PreparedStatement updateRecipe = connection.prepareStatement(UPDATE_RECIPE_QUERY)) {

            updateRecipe.setString(1, recipe.getName());
            updateRecipe.setString(2, recipe.getIngredients());
            updateRecipe.setString(3, recipe.getDescription());
            updateRecipe.setInt(4, recipe.getPreparationTime());
            updateRecipe.setString(5, recipe.getPreparation());
            updateRecipe.setInt(6, recipe.getAdminId());
            updateRecipe.setInt(7, recipe.getID());
            updateRecipe.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Delete recipe by id
     *
     * @param recipeId
     */

    public boolean delete(int recipeId) {
        try (Connection connection = DbUtil.getConnection();
                /*Connection connection = DbUtil2.connect("scrumlab");*/
             PreparedStatement deleteRecipe = connection.prepareStatement(DELETE_RECIPE_QUERY)) {
            deleteRecipe.setInt(1, recipeId);
            deleteRecipe.executeUpdate();
            /*     boolean deleted = deleteRecipe.execute();*/
            if (!deleteRecipe.execute()) {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }


    /**
     * Find all recipes
     */

    public List<Recipe> findAll() {
        List<Recipe> recipes = new ArrayList<>();

        try (Connection connection = DbUtil.getConnection();
                /*Connection connection = DbUtil2.connect("scrumlab");*/
             PreparedStatement findAll = connection.prepareStatement(FIND_ALL_RECIPE_QUERY);


             ResultSet resultSet = findAll.executeQuery()) {

            while ((resultSet.next())) {
                Recipe recipeToAdd = new Recipe();
                recipeToAdd.setID(resultSet.getInt("id"));
                recipeToAdd.setPreparationTime(resultSet.getInt("preparation_time"));
                recipeToAdd.setAdminId(resultSet.getInt("admin_id"));
                recipeToAdd.setName(resultSet.getString("name"));
                recipeToAdd.setIngredients(resultSet.getString("ingredients"));
                recipeToAdd.setDescription(resultSet.getString("description"));
                recipeToAdd.setCreated(resultSet.getString("created"));
                recipeToAdd.setUpdated(resultSet.getString("updated"));
                recipeToAdd.setPreparation(resultSet.getString("preparation"));
                recipes.add(recipeToAdd);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return recipes;
    }


    public int amountOfRecipes(int adminId) {
        int result = 0;
        try (Connection connection = DbUtil.getConnection()) {
            PreparedStatement findCount = connection.prepareStatement(SELECT_RECIPE_BY_ADMIN_ID);
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

}
