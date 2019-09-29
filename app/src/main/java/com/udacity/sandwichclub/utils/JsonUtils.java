package com.udacity.sandwichclub.utils;

import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    private static final String TAG = JsonUtils.class.getSimpleName();

    public static Sandwich parseSandwichJson(String json) throws JSONException {
        Log.v(TAG, String.format("parseSandwichJson: json= %s", json));


        /* Sandwich "name" parent attribute on Json */
        final String JSON_NAME = "name";

        /* Sandwich "main name" attribute on Json */
        final String JSON_MAINNAME = "mainName";

        /* Sandwich "also known as" list attribute on Json */
        final String JSON_ALSOKNOWNAS = "alsoKnownAs";

        /* Sandwich "place of origin" attribute on Json */
        final String JSON_PLACEOFORIGIN = "placeOfOrigin";

        /* Sandwich "description" attribute on Json */
        final String JSON_DESCRIPTION = "description";

        /* Sandwich "image" URL attribute on Json */
        final String JSON_IMAGE = "image";

        /* Sandwich "ingredients" list attribute on Json */
        final String JSON_INGREDIENTS = "ingredients";

        Sandwich sandwich = new Sandwich();


        JSONObject sandwichJson = new JSONObject(json);

        // Get sandwich name JSON object to retrieve the main name and also known as names
        JSONObject sandwichNameJson = sandwichJson.getJSONObject(JSON_NAME);
        sandwich.setMainName(sandwichNameJson.getString(JSON_MAINNAME));

        List<String> alsoKnownAs = new ArrayList<>();
        JSONArray alsoKnownAsJSONArray = sandwichNameJson.getJSONArray(JSON_ALSOKNOWNAS);

        if (alsoKnownAsJSONArray != null) {
            for (int i = 0; i < alsoKnownAsJSONArray.length(); i++) {
                alsoKnownAs.add(alsoKnownAsJSONArray.getString(i));
            }
        }

        sandwich.setAlsoKnownAs(alsoKnownAs);

        //Get the others attributes from the JSON root
        sandwich.setPlaceOfOrigin(sandwichJson.getString(JSON_PLACEOFORIGIN));

        sandwich.setDescription(sandwichJson.getString(JSON_DESCRIPTION));

        sandwich.setImage(sandwichJson.getString(JSON_IMAGE));

        List<String> ingredients = new ArrayList<>();
        JSONArray ingredientsJSONArray = sandwichJson.getJSONArray(JSON_INGREDIENTS);

        if (ingredientsJSONArray != null) {
            for (int i = 0; i < ingredientsJSONArray.length(); i++) {
                ingredients.add(ingredientsJSONArray.getString(i));
            }
        }

        sandwich.setIngredients(ingredients);

        return sandwich;
    }
}
