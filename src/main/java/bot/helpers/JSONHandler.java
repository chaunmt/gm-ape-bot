package bot.helpers;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class JSONHandler
{
    public static ArrayList<String> getArr(String link, String[] keys)
    { return getArr(getJSONArr(link, keys)); }

    public static ArrayList<String> getArr(String link, String[] keys, String objName)
    { return getArr(getJSONArr(link, keys), objName); }

    public static ArrayList<String> getArr(JSONArray jsonArr)
    {
        ArrayList<String> arr = new ArrayList<>();
        for (int i = 0; i < jsonArr.length(); i++)
            arr.add(jsonArr.get(i).toString());

        return arr;
    }

    public static ArrayList<String> getArr(JSONArray jsonArr, String objName)
    {
        ArrayList<String> arr = new ArrayList<>();
        for (int i = 0; i < jsonArr.length(); i++)
            arr.add(jsonArr.getJSONObject(i).get(objName).toString());

        return arr;
    }

    public static JSONArray getJSONArr(String link, String[] keys)
    {
        JSONObject obj = getJSONObj(link);
        return getJSONArr(obj, keys);
    }

    public static JSONArray getJSONArr(JSONObject obj, String[] keys)
    {
        for (int i = 0; i < keys.length - 1; i++)
            obj = obj.getJSONObject(keys[i]);

        return obj.getJSONArray(keys[keys.length - 1]);
    }

    public static JSONObject getJSONObj(String link)
    {
        try
        {
            JSONParser parser = new JSONParser();
            String str = parser.parse(new FileReader(link)).toString();
            return new JSONObject(str);
        }
        catch (IOException | org.json.simple.parser.ParseException e)
        {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}