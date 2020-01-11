import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        FuzzyClustering cmean = new FuzzyClustering();
        

        //get number of class from user
        System.out.println("Please input number of cluster that you want :");
        Scanner sc= new Scanner(System.in);
        String read1 = sc.nextLine();
        System.out.println("please input size of data set :");
        String read2 = sc.nextLine();

        //generate random data
        cmean.initData(Integer.parseInt(read2),Integer.parseInt(read1));
    

        //Distance Eucludienne
        cmean.run(Integer.parseInt(read1),cmean.data,0,1.5f);
        System.out.println("Clustering Finished!!!");
        cmean.run(Integer.parseInt(read1),cmean.data,0,2f);
        System.out.println("Clustering Finished!!!");
        cmean.run(Integer.parseInt(read1),cmean.data,0,2.5f);
        System.out.println("Clustering Finished!!!");
        cmean.run(Integer.parseInt(read1),cmean.data,0,3f);
        System.out.println("Clustering Finished!!!");
        cmean.run(Integer.parseInt(read1),cmean.data,0,3.5f);
        System.out.println("Clustering Finished!!!");
        cmean.run(Integer.parseInt(read1),cmean.data,0,4f);
        System.out.println("Clustering Finished!!!");
        cmean.run(Integer.parseInt(read1),cmean.data,0,4.5f);
        System.out.println("Clustering Finished!!!");
        cmean.run(Integer.parseInt(read1),cmean.data,0,5f);
        System.out.println("Clustering Finished!!!");
        System.out.println("*********************************");
        //Distance Euclidienne SD
        cmean.run(Integer.parseInt(read1),cmean.data,1,1.5f);
        System.out.println("Clustering Finished!!!");
        cmean.run(Integer.parseInt(read1),cmean.data,1,2f);
        System.out.println("Clustering Finished!!!");
        cmean.run(Integer.parseInt(read1),cmean.data,1,2.5f);
        System.out.println("Clustering Finished!!!");
        cmean.run(Integer.parseInt(read1),cmean.data,1,3f);
        System.out.println("Clustering Finished!!!");
        cmean.run(Integer.parseInt(read1),cmean.data,1,3.5f);
        System.out.println("Clustering Finished!!!");
        cmean.run(Integer.parseInt(read1),cmean.data,1,4f);
        System.out.println("Clustering Finished!!!");
        cmean.run(Integer.parseInt(read1),cmean.data,1,4.5f);
        System.out.println("Clustering Finished!!!");
        cmean.run(Integer.parseInt(read1),cmean.data,1,5f);
        System.out.println("Clustering Finished!!!");
        System.out.println("*********************************");
        //Distance Manhattan
        cmean.run(Integer.parseInt(read1),cmean.data,2,1.5f);
        System.out.println("Clustering Finished!!!");
        cmean.run(Integer.parseInt(read1),cmean.data,2,2f);
        System.out.println("Clustering Finished!!!");
        cmean.run(Integer.parseInt(read1),cmean.data,2,2.5f);
        System.out.println("Clustering Finished!!!");
        cmean.run(Integer.parseInt(read1),cmean.data,2,3f);
        System.out.println("Clustering Finished!!!");
        cmean.run(Integer.parseInt(read1),cmean.data,2,3.5f);
        System.out.println("Clustering Finished!!!");
        cmean.run(Integer.parseInt(read1),cmean.data,2,4f);
        System.out.println("Clustering Finished!!!");
        cmean.run(Integer.parseInt(read1),cmean.data,2,4.5f);
        System.out.println("Clustering Finished!!!");
        cmean.run(Integer.parseInt(read1),cmean.data,2,5f);
        System.out.println("Clustering Finished!!!");
        System.out.println("*********************************");
        //Distance Canberra
        cmean.run(Integer.parseInt(read1),cmean.data,3,1.5f);
        System.out.println("Clustering Finished!!!");
        cmean.run(Integer.parseInt(read1),cmean.data,3,2f);
        System.out.println("Clustering Finished!!!");
        cmean.run(Integer.parseInt(read1),cmean.data,3,2.5f);
        System.out.println("Clustering Finished!!!");
        cmean.run(Integer.parseInt(read1),cmean.data,3,3f);
        System.out.println("Clustering Finished!!!");
        cmean.run(Integer.parseInt(read1),cmean.data,3,3.5f);
        System.out.println("Clustering Finished!!!");
        cmean.run(Integer.parseInt(read1),cmean.data,3,4f);
        System.out.println("Clustering Finished!!!");
        cmean.run(Integer.parseInt(read1),cmean.data,3,4.5f);
        System.out.println("Clustering Finished!!!");
        cmean.run(Integer.parseInt(read1),cmean.data,3,5f);
        System.out.println("Clustering Finished!!!");
        System.out.println("*********************************");
        




        
       
    }
}