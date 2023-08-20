package service;

import common.Location;
import factory.ConfigFactory;
import model.Order;
import model.Product;
import model.Task;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputValidator {
    private static final String locationPattern = "Location: (.+)";

    private static final String productPattern = "(.+) at (.+)";

    public Task createTask(){
        Task task = new Task();
        String inputString = getInputString();
        Order order = getOrder(inputString);
        task.setConfig(ConfigFactory.createConfig(order.getLocation().toString()));
        task.setOrder(order);
        return task;
    }


    public String getInputString(){
        Scanner sc= new Scanner(System.in);
        System.out.print("Input: ");
        return sc.nextLine();
    }

    // Location: NY, 2 pencils at 2.99, 1 shirt at 29.99
    public Order getOrder(String inputString){
        String[] list = inputString.split(",");
        if(list.length<2){
            throw new IllegalArgumentException("The input string missing product part");
        }
        Location location = getLocationFromString(list[0]);
        Order order = new Order(location);
        for (int i = 1; i < list.length; i++) {
            Product product = getProductFromString(list[i]);
            order.addPurchasedProducts(product);
        }
        return order;
    }

    private Location getLocationFromString(String inputString){
        Pattern pattern = Pattern.compile(locationPattern);
        Matcher matcher = pattern.matcher(inputString);
        if (matcher.find()) {
            String match = matcher.group(1);
            try {
                return Location.valueOf(match);
            }catch (IllegalArgumentException e){
                throw new IllegalArgumentException("The input location is not support");
            }
        } else {
            throw new IllegalArgumentException("The input format for location can't match the not pattern");
        }
    }

    public Product getProductFromString(String inputString){
        Integer quantity = processProductFromString(inputString);
        // Remove the first number from the string
        String remainingString = inputString.replaceFirst(quantity.toString(), "").trim();
        Pattern pattern = Pattern.compile(productPattern);
        Matcher matcher = pattern.matcher(remainingString);
        if (matcher.matches()) {
            try{
                String productName = matcher.group(1).trim();
                Double price = Double.valueOf(matcher.group(2).trim());
                return new Product(productName, quantity, price);
            }catch (IllegalArgumentException e){
                throw new IllegalArgumentException("The input string for product part is not correct");
            }
        } else {
            throw new IllegalArgumentException("The input format for product can't match the not pattern");
        }
    }

    public Integer processProductFromString(String inputString)
    {
        String[] strings = inputString.trim().split(" ");

        try{
            return Integer.valueOf(strings[0]);
        }catch (NumberFormatException e){
            throw new NumberFormatException("The input format for product's quantity can't match the not pattern");
        }

    }


}
