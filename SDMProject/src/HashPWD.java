
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.util.JSON;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Random;
import org.apache.commons.codec.binary.Base64;


/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Srikant
 */

public class HashPWD {
    
    public static String[] pwds = new String[10];
    
    private static final int iterations = 10*1024;
    
    private static final int saltLen = 32;
    
    private static final int desiredKeyLen = 256;
    
    public void parsePWD() throws FileNotFoundException, IOException, ParseException, NoSuchAlgorithmException, InvalidKeySpecException, Exception {                
        
        MongoClient mongo = new MongoClient("localhost" , 27017);
        
        DB db = mongo.getDB("projDB");
        
        DBCollection coll = db.getCollection("UserInfo");
        
        int i = 0;
        
        JSONParser parser = new JSONParser();
        
        JSONArray jsonArr = (JSONArray) parser.parse(new FileReader("C:\\SecureDB RIT\\Project\\UserInfo\\UserInfo.json"));
        
        for(Object item : jsonArr) {
            
            JSONObject password = (JSONObject) item;
            
            pwds[i] = (String) password.get("password");
            
           String hashedpwd = getSaltedHash(pwds[i]);     
           
           password.put("password", hashedpwd);                      
           
           DBObject dbobj = (DBObject) JSON.parse(password.toJSONString());
           
           coll.insert(dbobj);
           
           i++;
                        
        }
    }
    
    
    public static String getSaltedHash(String password) throws Exception {
        
        byte[] salt = SecureRandom.getInstance("SHA1PRNG").generateSeed(saltLen);

        return Base64.encodeBase64String(salt) + "$" + hash(password, salt);
        
    }


    private static String hash(String password, byte[] salt) throws Exception {
        
        if (password == null || password.length() == 0)
        
            throw new IllegalArgumentException("Empty passwords are not supported.");
        
        SecretKeyFactory f = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        
        SecretKey key = f.generateSecret(new PBEKeySpec(password.toCharArray(), salt, iterations, desiredKeyLen));
        
        return Base64.encodeBase64String(key.getEncoded());
    }
    
    
    
    public static void main(String[] args) throws FileNotFoundException, IOException, ParseException, NoSuchAlgorithmException, InvalidKeySpecException, Exception {
        
        HashPWD obj = new HashPWD();
        
        obj.parsePWD();
        
    }
    
}
