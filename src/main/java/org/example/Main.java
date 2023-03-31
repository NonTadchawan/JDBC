package org.example;

public class Main {
    public static void main(String[] args) {
        Repo a = new Repo();
        a.open();
        System.out.println(a.select("id = 17"));
        a.insert(13, "Nani");
        a.update("id=18", "name='Jinny'");
        a.delete("id = 23");
        a.close();
    }
}
