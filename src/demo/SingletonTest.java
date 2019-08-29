package demo;

import java.util.Vector;

public class SingletonTest {  
	  
    private static SingletonTest instance = null;  
    private Vector<Integer> properties = new Vector<>();  
  
    public Vector getProperties() {  
        return properties;  
    }  
  
    private SingletonTest() {  
    }  
  
    private static synchronized void syncInit() {  
        if (instance == null) {  
            instance = new SingletonTest();  
        }  
    }  
  
    public static SingletonTest getInstance() {  
        if (instance == null) {  
            syncInit();  
        }  
        return instance;  
    }  
  
    public void updateProperties() {  
        //SingletonTest shadow = new SingletonTest();  
        //properties = shadow.getProperties();  
    	properties.add(6);
    }  
    
    public static void main(String[] args) {
    	SingletonTest ins = SingletonTest.getInstance();
    	System.out.println(ins.properties);
    	ins.updateProperties();
    	System.out.println(ins.properties);
    }
}  
