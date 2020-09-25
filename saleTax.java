import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files
import java.lang.Math;
import java.math.BigDecimal;
import java.text.DecimalFormat;

public class saleTax{
    
    public static void main (String[] args){
        String[] input = {"input1.txt","input2.txt","input3.txt"};
        for (int in=0;in<3;in++){
        double sale = 0;
        double total = 0;
        System.out.println();
        System.out.println("Output"+(in+1));
       try {
      File myObj = new File(input[in]);
      Scanner myReader = new Scanner(myObj);
      while (myReader.hasNextLine()) {
        String data = myReader.nextLine();
        String[] val = makeString(data);
        double tax = calculateTax(val);
        sale = sale + tax;
        double finalprice = finalPrice(val[val.length-1],tax);
        total = total + finalprice;
        outputFormat(val,finalprice);
      }
      myReader.close();
      System.out.println("Sale Taxes: " + df2.format(sale));
      System.out.println("Total: " + df2.format(total));
    } catch (FileNotFoundException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
        }
    }


public static String[] makeString(String data){
        String[] words = data.split(" "); 
        return words;
    }

public static boolean importProduct(String[]data){
    boolean importitem = false;
    for (int i=1;i<data.length-1;i++){
        int var =data[i].compareTo("imported");
        if(var == 0){
           importitem = true;
        }
    }
    return importitem;
}

public static boolean goodsProduct(String[]data){
    boolean goodsitem = false;
    String[] goods = {"chocolates","pills","book"};
    for (int i=1;i<data.length-1;i++){
        for(int j=0;j<goods.length;j++){
        int var =data[i].compareTo(goods[j]);
        if(var == 0){
           goodsitem = true;
        }
    }
    }
    return goodsitem;
}

public static double calculateTax(String[] data){
    double cat = 0;
    boolean imp = importProduct(data);
    boolean goods = goodsProduct(data);
    double price = Double.parseDouble(data[data.length-1]);
    
    if (imp && goods){
        cat = price * 0.05;//5% tax
    }
    else if(imp){
        cat = price * 0.15; //15% tax
    }
    else if(goods){
        cat = 0; //0% tax
    }
    else{
        cat =price * 0.10; //10% tax
    }

    return decimalPlace(cat);
    
}
 public static double finalPrice(String rate,double tax){
     double price = Double.parseDouble(rate);
     return (price+tax);
 }

public static void outputFormat(String[] data,double finalPrice){
    data[data.length-1]=df2.format(finalPrice);
    data[data.length-2]=":";
    for (int i=0;i<data.length;i++){
       System.out.print(data[i]+" ");
    }
    System.out.println();
}

private static DecimalFormat df2 = new DecimalFormat("#.##");

public static double decimalPlace(double value){
return  (float) Math.ceil(value / 0.05f) * 0.05f;
}

} 