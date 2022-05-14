package com.example.demo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class ProductIO {
	private static ArrayList<Product> products = null;

	public static ArrayList<Product> getProducts(String path) {
		ArrayList<Product> products = new ArrayList<>();
		try {
			File file = new File(path);
			BufferedReader in = new BufferedReader(new FileReader(file));
			String currentLine;
			while ((currentLine = in.readLine()) != null) {
				String[] productData = currentLine.split("\\|");
				Product p = new Product();
				p.setCode(productData[0]);
				p.setDescription(productData[1]);
				p.setPrice(Double.parseDouble(productData[2]));
				products.add(p);
			}
			in.close();
			return products;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static Product getProduct(String productCode, String path) {
		try {
			Product p = new Product();
			File file = new File(path);
			BufferedReader in = new BufferedReader(new FileReader(file));
			String currentLine;
			while ((currentLine = in.readLine()) != null) {
				String[] productData = currentLine.split("\\|");
				if (productCode.equalsIgnoreCase(productData[0])) {
					p.setCode(productData[0]);
					p.setDescription(productData[1]);
					p.setPrice(Double.parseDouble(productData[2]));
					break;
				}
			}
			in.close();
			return p;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static boolean exists(String productCode, String path) {
		products = getProducts(path);
		for (Product p : products) {
			if (productCode != null && productCode.equalsIgnoreCase(p.getCode())) {
				return true;
			}
		}
		return false;
	}

	public static void saveProducts(ArrayList<Product> products, String path) {
		try {
			File file = new File(path);
			PrintWriter out = new PrintWriter(new FileWriter(file));
			for (Product p : products) {
				out.println(p.getCode() + "|" + p.getDescription() + "|" + p.getPrice());
			}
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void insert(Product product, String path) {
		products = getProducts(path);
		products.add(product);
		saveProducts(products, path);
	}

	public static void update(Product product, String path) {
		products = getProducts(path);
		for (int i = 0; i < products.size(); i++) {
			Product p = products.get(i);
			if (product.getCode() != null && product.getCode().equalsIgnoreCase(p.getCode())) {
				products.set(i, product);
			}
		}
		saveProducts(products, path);
	}

	public static void delete(Product product, String path) {
		products = getProducts(path);
		for (int i = 0; i < products.size(); i++) {
			Product p = products.get(i);
			if (product != null && product.getCode().equalsIgnoreCase(p.getCode())) {
				products.remove(i);
			}
		}
		saveProducts(products, path);
	}
}
