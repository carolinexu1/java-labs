// Name:J2-27
// Date:9/24
import java.util.*;
import java.io.*;
import java.text.DecimalFormat;
//here any additional imports that you may need
import java.lang.*;

public class Cemetery
{
   public static void main (String [] args)
   {
   
      File file = new File("cemetery_short.txt");
     //File file = new File("cemetery.txt");
      int numEntries = countEntries(file);
      Person[] cemetery = readIntoArray(file, numEntries); 
   
      //see what you have
   
      for (int i = 0; i < cemetery.length; i++) {
         System.out.println(cemetery[i]);
      }
   
      int min = locateMinAgePerson(cemetery);
      int max = locateMaxAgePerson(cemetery); 
      System.out.println("\nIn the St. Mary Magdelene Old Fish Cemetery --> ");
      System.out.println("Name of youngest person: " + cemetery[min].getName());
      System.out.println("Age of youngest person: " + cemetery[min].getAge());    
      System.out.println("Name of oldest person: " + cemetery[max].getName());
      System.out.println("Age of oldest person: " + cemetery[max].getAge()); 
      //you may create other testing cases here
      //comment them out when you submit your file to Grade-It
      
          
   }
   
   /* Counts and returns the number of entries in File f. 
      Returns 0 if the File f is not valid.
      Uses a try-catch block.   
      @param f -- the file object
   */
   public static int countEntries(File f) 
   {
      int count = 0;
      Scanner infile=null; 
      try
      {
         infile = new Scanner(f); 
      }
      
      catch(FileNotFoundException e)
      {
         count= 0;
      }
      while(infile.hasNext()){
         count++; 
         infile.nextLine();    
      } 
      infile.close();
      return count; 
   }

   /* Reads the data from file f (you may assume each line has same allignment).
      Fills the array with Person objects. If File f is not valid return null.
      @param f -- the file object 
      @param num -- the number of lines in the File f  
   */
   public static Person[] readIntoArray (File f, int num)
   {
      Person[] arr = new Person[num];
      try
      {
         Scanner infile = new Scanner(f);
         while(infile.hasNext()){
         
            for(int i =0; i < num; i++) {
               arr[i] = makeObjects(infile.nextLine());
            }
         }
      
      }
      catch(FileNotFoundException e)
      {
         arr=null;
      }
      return arr; 
   
   }
   /* A helper method that instantiates one Person object.
      @param entry -- one line of the input file.
      This method is made public for gradeit testing purposes.
      This method should not be used in any other class!!!
   */
   public static Person makeObjects(String entry)
   {
      String[] pArray = entry.split("\\s+");
      int forCount=0;
      boolean temp=false; 
      for(int i =0; i < pArray.length; i++) {
      
         if(Character.isDigit(pArray[i].charAt(0))&&temp==false){
            forCount = i;
            temp = true;
         }
      }
      String date = "";
      String name = "";
      String age = pArray[forCount + 3];
      for(int i = forCount; i < forCount + 3; i++) { 
         date = date + pArray[i] + " ";
      }
      for(int i = 0; i < forCount; i++) { 
         name = name + pArray[i] + " ";
      }
   
      Person p = new Person(name.trim(), date.trim(), age);
      return p;
   
   }  
   
   /* Finds and returns the location (the index) of the Person
      who is the youngest. (if the array is empty it returns -1)
      If there is a tie the lowest index is returned.
      @param arr -- an array of Person objects.
   */
   public static int locateMinAgePerson(Person[] arr)
   {
      int minIndex=0;
      for(int i = 0 ;i < arr.length; i++){
        // if(Double.parseDouble(arr[minIndex].getAge()) > Double.parseDouble(arr[i].getAge())){
         if(arr[minIndex].getAge() > arr[i].getAge()){
         
            minIndex = i;
         }
      }
      return minIndex;
   }   
   
   /* Finds and returns the location (the index) of the Person
      who is the oldest. (if the array is empty it returns -1)
      If there is a tie the lowest index is returned.
      @param arr -- an array of Person objects.
   */
   public static int locateMaxAgePerson(Person[] arr)
   {
      int maxIndex=0;
      for(int i = 0 ;i < arr.length; i++){
         //if(Double.parseDouble(arr[maxIndex].getAge()) < (Double.parseDouble(arr[i].getAge()))){
         if(arr[maxIndex].getAge() < arr[i].getAge()){
         
            maxIndex = i;
         }
      }
      return maxIndex;
   }        
} 

class Person
{
   //constant that can be used for formatting purposes
   private static final DecimalFormat df = new DecimalFormat("0.0000");
   /* private fields */
   private String name; 
   private String burialDate; 
   private String age;
      
   /* a three-arg constructor  
    @param name, burialDate may have leading or trailing spaces
    It creates a valid Person object in which each field has the leading and trailing
    spaces eliminated*/
   public Person(String name, String burialDate, String age)
   {
      this.name = name.trim();
      this.burialDate = burialDate.trim();
      this.setAge(age);
     
   }
   /* any necessary accessor methods (at least "double getAge()" and "String getName()" )
   make sure your get and/or set methods use the same data type as the field  */

   public double getAge(){
      return Double.parseDouble(this.age);
   }
   
   public String getName(){
      return name;
   }
   public void setAge(String a){
   
      Double newAgeNum =calculateAge(a);
      String newAge=Double.toString(newAgeNum);
      this.age=newAge;
   }
      
   
   
   /*handles the inconsistencies regarding age
     @param a = a string containing an age from file. Ex: "12", "12w", "12d"
     returns the age transformed into year with 4 decimals rounding
   */
   public double calculateAge(String a)
   {
      double aAge=0;
      double x=0.0;
      String nums = "0123456789";
      if((a.length() <= 1) && (a.contains("w")||a.contains("d")))
         x = Double.parseDouble(a.substring(0, a.length()));
      else if((a.length() > 1) && (a.contains("w")||a.contains("d")))
         x = Double.parseDouble(a.substring(0, a.length()-1));
      else
         x = Double.parseDouble(a);
   
      if(a.contains("w")) {
         aAge = x * 0.019165;
      }
      else if (a.contains("d")) {
         aAge = x * 0.00273973;
      }
      else  { 
         aAge = Double.parseDouble(x+"");
      }
      String newAge = df.format(aAge);
      return Double.parseDouble(newAge);         
      
   }
   public String toString(){
      String s = name + ", " + burialDate + ", " + age;
      return s;
   }  
}
/******************************************

 John William ALLARDYCE, 17 Mar 1844, 2.9
 Frederic Alex. ALLARDYCE, 21 Apr 1844, 0.17
 Philip AMIS, 03 Aug 1848, 1.0
 Thomas ANDERSON, 06 Jul 1845, 27.0
 Edward ANGEL, 20 Nov 1842, 22.0
 Lucy Ann COLEBACK, 23 Jul 1843, 0.2685
 Thomas William COLLEY, 08 Aug 1833, 0.011
 Joseph COLLIER, 03 Apr 1831, 58.0
 
 In the St. Mary Magdelene Old Fish Cemetery --> 
 Name of youngest person: Thomas William COLLEY
 Age of youngest person: 0.011
 Name of oldest person: Joseph COLLIER
 Age of oldest person: 58.0
 
 **************************************/