package com.todo.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.*;

import com.todo.dao.TodoItem;
import com.todo.dao.TodoList;

public class TodoUtil {
	
	public static void createItem(TodoList list) {
		String title, desc, category, due_date;
		Scanner sc = new Scanner(System.in);
		
		System.out.print("\n"
				+ "---------- Create item Section ----------\n"
				+ "Enter the title of the item to create: ");
		
		title = sc.next();
		if (list.isDuplicate(title)) {
			System.out.print("Title can't be duplicate. please change the title.");
			return;
		}
		System.out.print("Enter the category: ");
		category = sc.next();
		sc.nextLine();
		System.out.print("Enter the description: ");
		desc = sc.nextLine().trim();
		System.out.print("Enter the due_date: ");
		due_date = sc.nextLine().trim();
				
		TodoItem t = new TodoItem(title, desc, category, due_date);
		if(list.addItem(t) > 0)
			System.out.println("Item added.");
		sc.close();
	}

	public static void deleteItem(TodoList l) {
		Scanner sc = new Scanner(System.in);
		
		System.out.print("\n"
				+ "---------- Delete Item Section ----------\n"
				+ "Enter the number of the item to remove: ");
		
		int no = sc.nextInt();
		
		if (l.deleteItem(no) > 0) {
			System.out.println("Item deleted.");
		}
		sc.close();
	}


	public static void updateItem(TodoList l) {
		// cnt -> 현재 번호, no -> 입력받은 번호 (찾아야하는 번호), total -> 전체번호
		Scanner sc = new Scanner(System.in);
		String new_title, new_desc, new_category, new_due_date;
		
		System.out.print("\n"
				+ "---------- Edit Item Section ----------\n"
				+ "Enter the number of the item to update: ");
		
		int no = sc.nextInt();
		
//		sc.nextLine();
		System.out.print("Enter the new title of the item: ");
		new_title = sc.next().trim();
		
		System.out.print("Enter the new category: ");
		new_category = sc.next();
		
		sc.nextLine();
		System.out.print("Enter the new description: ");
		new_desc = sc.nextLine().trim();
		
		System.out.print("Enter the new due_date: ");
		new_due_date = sc.nextLine().trim();
		
		TodoItem t = new TodoItem(new_title, new_desc, new_category, new_due_date);
		t.setId(no);
		if (l.updateItem(t) > 0)
			System.out.println("Item updated.");
		sc.close();
	}
	
	public static void listAll(TodoList l) {
		int cnt = 0;
		System.out.println("\n"
				+ "---------- List " + l.getCount() + " item ----------");
		for (TodoItem item : l.getList()) {
			cnt++;
			System.out.println(cnt + ". " + item.toString());
		}
	}
	
	public static void saveList(TodoList l, String filename) {
		try {
			Writer w = new FileWriter(filename);
			for (TodoItem item : l.getList()) {
				w.write(item.toSaveString());
			}
			w.close();
			
			System.out.println("File saved.");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
//	public static void loadList(TodoList l, String filename) {
//		try {
//			BufferedReader br = new BufferedReader(new FileReader(filename));
//			String oneline;
//			int cnt = 0;
//			while((oneline = br.readLine()) != null) {
//				StringTokenizer st = new StringTokenizer(oneline, "##");
//				String category = st.nextToken();
//				String title = st.nextToken();
//				String description = st.nextToken();
//				String due_date = st.nextToken();
//				String current_date = st.nextToken();
//				TodoItem item = new TodoItem(title, description, category, due_date);
//				item.setCurrent_date(current_date);
//				l.addItem(item);
//				cnt++;				
//			}
//			br.close();
//			System.out.println("Read " + cnt + " items.");
//		} catch (FileNotFoundException e) {
//			//e.printStackTrace();
//			System.out.println("'"+ filename + "' Not found.");
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}

	public static void findList(TodoList l, String keyword) {
		int cnt = 0;
		
		for (TodoItem item : l.getList(keyword)) {
			cnt++;
			System.out.println(cnt + " " + item.toString());
		}
		System.out.println("Find "+ cnt +" items.");
	}

	public static void findCate(TodoList l, String cate_keyword) {
		int cnt = 0;
		
		for (TodoItem item : l.getListCategory(cate_keyword)) {
			cnt++;
			System.out.println(cnt + " " + item.toString());
		}
		System.out.println("\nFind "+ cnt +" items.");
	}

	public static void listCateAll(TodoList l) {
		int cnt = 0;
		for (String item : l.getCategories()) {
			System.out.print(item + " ");
			cnt++;
		}
		System.out.println("\n" + cnt + " categories.");
	}
	
	public static boolean isExistCategory(List<String> clist, String cate) {
		for (String c : clist) if (c.equals(cate)) return true;
		return false;
	}

	public static void listAll(TodoList l, String orderby, int ordering) {
		int cnt = 0;
		System.out.println("[total " + l.getCount() + " list]");
		for (TodoItem item : l.getOrderedList(orderby, ordering)) {
			cnt++;
			System.out.println(cnt + " " + item.toString());
		}
	}
}
