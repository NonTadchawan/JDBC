package org.example;

import java.sql.SQLException;
import java.util.List;

public class App
{
    public static void main( String[] args ) throws Throwable {
        Repo a = new Repo();
        a.select("name = 'Non'");
        a.insert(14,"Nana");
        a.update("name = 'Bow'","name = 'Brown'");
        a.delete("id = 13");
    }
}
