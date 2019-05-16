/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

public class HelloMongo {

    private final static String HOST = "localhost";
    private final static int PORT = 27017;
    private DB mongo = null;

    public static void main(String[] args) {
        HelloMongo helloMongo = new HelloMongo();
        System.out.println(helloMongo.Buscar("Admin", "Admin"));

    }

    public void Conectar() {

        try {
            // Connect to mongodb server on localhost
            MongoClient mongoClient = new MongoClient(HOST, PORT);

            mongo = mongoClient.getDB("Users");

            System.out.println("Successfully connected toMongoDB");

        } catch (Exception e) {

            System.err.println(e.getClass().getName() + ": "
                    + e.getMessage());
        }
    }

    public boolean Buscar(String user, String pass) {
        Conectar();
        DBCollection coll = mongo.getCollection("Users");
        DBObject query = new BasicDBObject("Username", user).append("password", pass);
        DBCursor cursor = coll.find(query);

        if (cursor.hasNext()) {
            System.out.println(cursor.next());
        } else {
            return false;
        }

        return true;
    }

}
