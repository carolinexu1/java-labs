import java.util.Scanner;
public class Driver01
{
   public static final int NUMITEMS = 10;
   public static void main(String[] args)
   {
      double[] array = new double[NUMITEMS];
      Scanner keyboard = new Scanner(System.in);
      double sum = 0.0;
      double avg = 0.0;
      for(int x = 0; x < NUMITEMS; x++)
      {
         System.out.print("#" + (x+1) + ": ");
         array[x] = keyboard.nextDouble();
      }
      System.out.println("Sum: ");
      for(int x = 0; x<array.length; x++)
         sum = array[x] + sum;
      System.out.println("" + sum);
      
      System.out.println("Avg: ");
      for(int x = 0; x < array.length; x++);
         avg = sum / array.length;
         
      System.out.println("" + avg);
      
      System.out.println("Min: ");
      double min = array[0];
      for (int x = 0; x < array.length; x++)
      {
         if (array[x] < min)
         {
            min = array[x];
         }
      }
      System.out.println("" + min);
   
      System.out.println("Max: ");
      double max = array[0];
      for (int x = 0; x < array.length; x++)
      {
      
      max = Math.max(max, array[x]);
              }
      System.out.println("" + max);
   
              
   }
}