   //Name______________________________ Date_____________
import java.io.*;      //the File class
import java.util.*;    //the Scanner class
import javax.swing.JOptionPane;

public class Driver08
{
   public static void main(String[] args) throws Exception
   {
      Scanner infile = new Scanner( new File("words.txt") );
      int NUMITEMS = infile.nextInt();
      String[] array = new String[NUMITEMS];

      for (int x = 0; x < NUMITEMS; x++) {
         array[x] = infile.next();
      }
      infile.close();
   
         
      while(true){
         String myWord = JOptionPane.showInputDialog("Enter a word (Type -1 to quit)");
      
         if(myWord.equals("-1")){
            System.out.println("Bye!");
            break;     
         }
         int y=0;//1 = true, 0= false
         int index=0;
         for (int a = 0; a < NUMITEMS; a++) {
            if (myWord.equalsIgnoreCase(array[a])) {
               y = 1; index = a+1;
               break;
            }
            else{
               y=0;
               }
            }
          if(y==1)
            System.out.println("Yes, \"" + myWord + "\" is a word, #" + index + ".");
          else
            System.out.println("Sorry. " + myWord + " is not a word.");
         
                 
         
         
         
         }
      }
   }
