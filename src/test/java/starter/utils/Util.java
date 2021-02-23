package starter.utils;

import org.jetbrains.annotations.NotNull;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileNotFoundException;
import org.json.simple.parser.ParseException;

import java.util.HashMap;
import java.util.Scanner;

public final class Util {

    public static JSONObject getJsonFromFile() throws ParseException {
        String data = "";
        try {
            File myObj = new File("src/test/resources/userData.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                data = data.concat(myReader.nextLine());
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        JSONParser parser = new JSONParser();
        return (JSONObject) parser.parse(data);
    }

    public static String getFieldValueReceipt(@NotNull HashMap<String,String> receipt, String field){
        String[] fieldValue = receipt.get(field).split(" ");
        boolean next = false;
        int count=0;
        String value = "";
        while (count<fieldValue.length) {
            if (next) {
                value = fieldValue[count].trim();
            }
            if (fieldValue[count].trim().equals(":")) {
                next = true;
            }
            count++;
        }
        return value;
    }

    public static int formatAmount(@NotNull String amountReceipt){
        String[] sAmount = amountReceipt.split(" ");
        int count = 0;
        int amount = 0;
        while (count<sAmount.length) {
            try {
                amount = +Integer.parseInt(sAmount[count].trim());
                break;
            } catch (NumberFormatException e) {
                ++count;
            }
        }
        return amount;
    }

}
