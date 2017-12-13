import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.util.ArrayList;


public class MyJson {
    static Vehicle vehicleObject;
    static ArrayList<Vehicle> vehicleObjectList;

    public static void main(String[] args) throws IOException, JSONException {
        File file = new File("E:\\IdeaProjects\\Java_Assignment8\\src\\camino.txt");
        ArrayList<Vehicle> vehicles = readAndGetVehicles(file);
        String s = getJsonString(vehicles);
        writeToFile(s, file.getParent());
        createOriginalFileFromJSON(s, file.getParent());
    }


    private static ArrayList<Vehicle> readAndGetVehicles(File file) throws IOException {
        String line;
        FileReader freader = new FileReader(file);
        BufferedReader br = new BufferedReader(freader);
        vehicleObjectList = new ArrayList<>();
        line = br.readLine();
        while ((line = br.readLine()) != null) {
            String[] vehicleArray = line.split("~");
            vehicleObject = new Vehicle(vehicleArray);
            vehicleObjectList.add(vehicleObject);
        }
        return vehicleObjectList;
    }

    public static String getJsonString(ArrayList<Vehicle> vehicles) throws IOException {
        String indented = "";
        ObjectMapper mapper = new ObjectMapper();
        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        String jsonString = mapper.writeValueAsString(vehicles);
        System.out.println(jsonString);
        try {
            Object json = mapper.readValue(jsonString, Object.class);
            indented = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(json);
            System.out.println(indented);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonString;
    }

    public static void writeToFile(String inputToWrite, String filePath) throws IOException {
        File outputFile = new File(filePath + "\\output.txt");
        FileWriter fwriter = new FileWriter(outputFile);
        fwriter.write(inputToWrite);
    }

    public static void createOriginalFileFromJSON(String jsonString, String filePath) throws IOException, JSONException {
        File outputOriginal = new File(filePath + "\\outputOriginalFile.txt");
        FileWriter fwriter = new FileWriter(outputOriginal);

        JSONArray jsonArray = new JSONArray(jsonString);
        String carInfo = "?id~webId~category~year~make~model~trim~type~price~photo" + "\r\n";
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObj = jsonArray.getJSONObject(i);
            String carId = (String) jsonObj.get("id");
            String webId = (String) jsonObj.get("webId");
            String category = (String) jsonObj.get("category");
            int year = (int) jsonObj.get("year");
            String make = (String) jsonObj.get("make");
            String model = (String) jsonObj.get("model");
            String trim = (String) jsonObj.get("trim");
            String type = (String) jsonObj.get("type");
            Double price = (Double) jsonObj.get("price");
            String photo = (String) jsonObj.get("photo");
            carInfo = carInfo + "" + carId + "~" + webId + "~" + category + "~" + year + "~" + make + "~" + model + "~" + trim + "~" + type + "~" + price + "~" + photo + "\r\n";
        }

        fwriter.write(carInfo);

    }

}
