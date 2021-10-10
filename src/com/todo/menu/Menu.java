package com.todo.menu;
public class Menu {

    public static void displaymenu() {
        System.out.println();
        System.out.println("---------- Command line list ----------");
        System.out.println("[add] Add a new item.");
        System.out.println("[del] Delete an existing item.");
        System.out.println("[edit] Update an item.");
        System.out.println("[find] Find an existing item.");
        System.out.println("[find_cate] Find an existing category.");
        System.out.println("[ls] List all items.");
        System.out.println("[ls_name] Sort the list by name.");
        System.out.println("[ls_name_desc] Sort the list by name.");
        System.out.println("[ls_date] Sort the list by date.");
        System.out.println("[ls_date_desc] Sort the list by date.");
        System.out.println("[ls_cate] List category.");
        System.out.println("[exit] Exit.");
    }
    
    public static void prompt() {
    	System.out.print("\nCommand > ");
    }
}