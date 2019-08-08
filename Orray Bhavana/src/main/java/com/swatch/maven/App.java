package com.swatch.maven;
import java.io.FileNotFoundException;
//import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.text.DecimalFormat;
import java.util.Scanner;

import org.json.simple.JSONArray;
//import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
@SuppressWarnings("unused")
public class App 
{
	static Scanner b;
	public static boolean login(String email,String pswd) throws IOException, ParseException  {
		try {
		if(email.endsWith("@gmail.com")==true) {
		JSONParser parser = new JSONParser();
		String filename="users/"+email+".json";
		Reader reader = new FileReader(filename);
		Object jsonObj = parser.parse(reader);
		JSONObject jsonObject = (JSONObject) jsonObj;
		String name = (String) jsonObject.get("email");
		String password = (String) jsonObject.get("pswd");
		if(name.equals(email) && password.equals(hash(pswd))) {
			return true;
		}
		else 
			return false;
		}
		else
			return false;	
		}
		catch(FileNotFoundException e) {
			System.out.println("User doesn't exists");
			return false;
		}
	}
	@SuppressWarnings("unchecked")
	public static boolean reges(String name,String email,String phone,String pswd) {
		if(email.endsWith("@gmail.com") && phone.length()==10 && pswd.length()>=8) {
		JSONObject obj = new JSONObject();
		obj.put("name", name);
		obj.put("email",email);		
		obj.put("pswd",hash(pswd));
		obj.put("phone",phone);
		obj.put("Coupons","0.0");
		String filename="users/"+email+".json";
		try {
			FileWriter file = new FileWriter(filename);
			file.write(obj.toJSONString());
			file.flush();
			file.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;
		}
		else {
			return false;
		}
	}
    public static void main( String[] args ) 
    {
    	try {
    	b=new Scanner(System.in);
    	int i;
    	String ch;
    	do {
    	System.out.println("\n\n\n*-------------------*-------------------*");
        System.out.println("Welcome to our Application:\n\n");
        System.out.println("Select the option for the Further process:");
        System.out.println("1.Login\n2.Registration\nEnter the option:");
			i=b.nextInt();
			switch(i) {
			case 1:
				System.out.print("\n\n\nEnter Email:");
				String email1=b.next();
				System.out.print("Enter Password:");
				String password=b.next();
				if(login(email1,password)) {
					System.out.println("\t\t\t*****Logged in*****\t\t\t");
					wastage(email1);
				}
				else
					System.out.println("Not Registered");
				
				break;
			case 2:
				System.out.print("\n\n\nEnter Name:");
				String name=b.next();
				System.out.print("Enter Email:");
				String email=b.next();
				System.out.print("Enter Password:");
				String pswd=b.next();
				System.out.print("Enter Phone Number:");
				String phone=b.next();
				if(reges(name,email,phone,pswd)==true) {
					System.out.println("\nSuccessfully Registered");
					if(login(email,pswd)==true) {
						wastage(email);
					}
				}
				else
					System.out.println("\nEnter the Details correctly");
				break;
			}
			System.out.print("\nEnter yes to enter again:");
			ch=b.next();
    	}while(ch.equals("yes"));
    	System.out.println("\n\t\t\t*----------------*Thank You*-----------------*\n\n\n\n\n");
    b.close();
    	}
    	catch(Exception e) {
    		System.out.println(e);
    		System.out.println("\n\t\t\t******Stopped Execution/Interruption************\n");
    	}
    }
    public static String hash(String pswd) {
    	String passwordToHash = pswd;
        String generatedPassword = null;
        try {      
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(passwordToHash.getBytes());
            byte[] bytes = md.digest();
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        return generatedPassword;
    }
    @SuppressWarnings({ "unused", "unchecked" })
	public static void wastage(String email) throws Exception   {
    	
    	JSONParser parser = new JSONParser();
		String filename="users/"+email+".json";
		Reader reader = new FileReader(filename);
		Object jsonObj = parser.parse(reader);
		JSONObject jsonObject = (JSONObject) jsonObj;
		String name = (String) jsonObject.get("name");
    	String email1 = (String) jsonObject.get("email");
    	String phone = (String) jsonObject.get("phone");
    	double coupons = Double.parseDouble(jsonObject.get("Coupons").toString());
    	double total=0.0;
    	double temp=0.0;
    	int count=0;
    	JSONObject obj = new JSONObject();
    	JSONArray array1=new JSONArray();  
    	obj.put("name",name);
    	obj.put("email", email1);
    	obj.put("phone",phone);
    	array1.add(obj);
    	obj=new JSONObject();
    	//JSONArray array_complete=new JSONArray();
    	//JSONArray array3=new JSONArray();
    	//Map<String,Double> plastic=new HashMap<String,Double>();
    	//Map<String,Double> metal=new HashMap<String,Double>();
    	//Map<String,Double> glass=new HashMap<String,Double>();
    	int plastic_quant,metal_quant,glass_quant,i=0;
    	String plastic_type,metal_type,glass_type,plastic_brand,metal_brand,glass_brand;
    	System.out.println("Entered into Wastage section\n");
    	System.out.println("***Enter only lower case***");
    	System.out.println("\tEnter the quantity of the type you have (*** Enter 0 if that type not exists***)");
    	System.out.print("\tEnter the number of types of plastic wastes:");
    	i=b.nextInt();
    	b.nextLine();
    	count=i;
    	System.out.println("\n");
    	if(i!=0) {
    		//obj.put("Waste Type","Plastic");
    	do {
    		System.out.println("\n");
    		
    	System.out.print("\tEnter Type of Plastic:");
    	plastic_type=b.nextLine();
    	
    	//b.nextLine();
    	System.out.print("\tEnter Brand of Plastic:");
    	plastic_brand=b.nextLine();
    	System.out.print("\tEnter quantity of Plastic:");
    	plastic_quant=b.nextInt();
    	b.nextLine();
    	if(plastic_brand.equals("coke")) {
    	temp=plastic_quant*((double)20/(double)30);	
    	}
    	else if(plastic_brand.equals("pepsi")) {
    	 temp=plastic_quant*((double)30/(double)30);
    	}
    	else if(plastic_brand.equals("sprite")) {
       	 temp=plastic_quant*((double)15/(double)30);
       	}
    	else if(plastic_brand.equals("thumpsup")) {
       	 temp=plastic_quant*((double)30/(double)30);
       	}
    	else {
    	 temp=plastic_quant*((double)5/(double)30);
    	}
    	//plastic.put(plastic_brand, temp);
    	/*array1.add(plastic_type);
    	array1.add(plastic_brand);
    	array1.add(plastic_quant);
    	array1.add(temp);
    	obj.put(plastic_type+":",array1);*/
    	//array1=new JSONArray();
    	obj.put("Waste Type","Plastic");
    	obj.put("Type", plastic_type);
    	obj.put("Brand", plastic_brand);
    	obj.put("Quantity(gms)", plastic_quant);
    	obj.put("Coupons", temp);
    	array1.add(obj);
    	obj=new JSONObject();
    	total+=temp;
    	i--;
    	System.out.println("\n");
    	}while(i>0);
    	}
    	System.out.println("\n");
    	System.out.print("\tEnter the number of types of Metal wastes:");
    	i=b.nextInt();
    	b.nextLine();
    	count=i;
    	if(i!=0) {
    	do {
    		System.out.println("\n");
    		 	
    	System.out.print("\tEnter Type of Metal:");
    	metal_type=b.nextLine();
    	
    	//b.nextLine();
    	System.out.print("\tEnter Brand of Metal:");
    	metal_brand=b.nextLine();
    	System.out.print("\tEnter quantity of Metal:");
    	metal_quant=b.nextInt();
    	b.nextLine();
    	
    	if(metal_brand.equals("coke")) {
        	temp=metal_quant*((double)15/(double)30);	
        	}
        	else if(metal_brand.equals("pepsi")) {
        	 temp=metal_quant*((double)25/(double)30);
        	}
        	else if(metal_brand.equals("sprite")) {
           	 temp=metal_quant*((double)10/(double)30);
           	}
        	else if(metal_brand.equals("thumpsup")) {
           	 temp=metal_quant*((double)20/(double)30);
           	}
        	else {
        		temp=metal_quant*((double)5/(double)30);
        	}
        	//metal.put(metal_brand, temp);
        	/*array1.add(metal_brand);
        	array1.add(metal_quant);
        	array1.add(temp);
        	obj.put(metal_type+":",array1);
        	array1=new JSONArray();*/
    	obj.put("Waste Type","Metal");
    	obj.put("Type", metal_type);
    	obj.put("Brand", metal_brand);
    	obj.put("Quantity(gms)", metal_quant);
    	obj.put("Coupons", temp);
    	array1.add(obj);
    	obj=new JSONObject();
    	total+=temp;
    	i--;	
    	System.out.println("\n");
    	}while(i>0);
    	}
    	System.out.println("\n");
    	System.out.print("\tEnter the number of types of Glass wastes:");
    	i=b.nextInt();
    	b.nextLine();
    	count=i;
    	if(i!=0) {
    		//obj.put("Waste Type","Glass");
    	do{
    		System.out.println("\n");
    	
    	System.out.print("\tEnter Type of Glass:");
    	glass_type=b.nextLine();
    	
    	//b.nextLine();
    	System.out.print("\tEnter Brand of Glass:");
    	glass_brand=b.nextLine();
    	System.out.print("\tEnter quantity of Glass:");
    	glass_quant=b.nextInt();
    	b.nextLine();
    	if(glass_brand.equals("coke")) {
        	temp=glass_quant*((double)25/(double)30);	
        	}
        	else if(glass_brand.equals("pepsi")) {
        	 temp=glass_quant*((double)35/(double)30);
        	}
        	else if(glass_brand.equals("sprite")) {
           	 temp=glass_quant*((double)20/(double)30);
           	}
        	else if(glass_brand.equals("thumpsup")) {
           	 temp=glass_quant*((double)35/(double)30);
           	}
        	else {
        		temp=glass_quant*((double)5/(double)30);
        	}
        	//glass.put(glass_brand, temp);
        	/*array1.add(glass_brand);
        	array1.add(glass_quant);
        	array1.add(temp);
        	obj.put(glass_type, array1);
        	array1=new JSONArray();*/
    	obj.put("Waste Type","Glass");
    	obj.put("Type", glass_type);
    	obj.put("Brand", glass_brand);
    	obj.put("Quantity(gms)", glass_quant);
    	obj.put("Coupons", temp);
    	array1.add(obj);
    	obj=new JSONObject();
    	
    	total+=temp;
    	i--;
    	System.out.println("\n");
    	}while(i>0);
    	}
    	i=0;
    	DecimalFormat format = new DecimalFormat("##.00");
    	total=Double.parseDouble(format.format(total));
    	String randomcode=randomCode(10);
    	obj.put("Coupon Code",randomcode);
    	array1.add(obj);
    	obj=new JSONObject();
    	
    	jsonObject.put("Coupons", String.valueOf(coupons+total));
    	//System.out.println((String) jsonObject.get("Coupons"));
    	JSONObject obj1=new JSONObject();
    	obj.put("Total:"+total,array1);
    	String filename2="users/"+email+".json";
    	String filename1="reports/"+email+"_"+randomcode+".json";
		try {
			FileWriter file = new FileWriter(filename1);
			FileWriter file1=new FileWriter(filename2);
			file1.write(jsonObject.toString());
			file.write(obj.toJSONString());
			file1.flush();
			file1.close();
			file.flush();
			file.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
    	System.out.println("\n\n\t\t\t\tReport generated successfully :)");
    	System.out.println("\t\t\t*************Congratulations*************\n\t\t\tTotal Coupons:"+(total+coupons));
    	System.out.println("\t\t\t*****Logged Out*****\t\t\t");
    	//System.out.println(obj.toString());
    	
    }
    public static String randomCode(int codeLength){   
        char[] chars = "abcdefghijklmnopqrstuvwxyz1234567890".toCharArray();
           StringBuilder sb = new StringBuilder();
           Random random = new SecureRandom();
           for (int i = 0; i < codeLength; i++) {
               char c = chars[random.nextInt(chars.length)];
               sb.append(c);
           }
           String output = sb.toString();
           System.out.println("\n\t\t\tCoupon code:"+output);
           return output ;
       }
}
