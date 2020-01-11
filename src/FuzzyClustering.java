import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
 public class FuzzyClustering {
    public ArrayList<ArrayList<Integer>> data;
    public ArrayList<ArrayList<Float>> clusterCenters;
    private float u[][];
    private float u_pre[][];
    private int clusterCount;
    private int iteration;
    private int dimension;
    private float fuzziness;
    private double epsilon;
    public double finalError;
    private ArrayList<Element[]> avatars;
	private int numberOfData;
	private int idDistance;
	 
    public FuzzyClustering(){
        data = new ArrayList<>();
        clusterCenters = new ArrayList<>();
        avatars=new ArrayList();
        fuzziness = 2;
        epsilon = 0.01;
    }
    public void setDimension(int d)
    {
    	this.dimension=d;
    }
    public void run(int clusterNumber, ArrayList<ArrayList<Integer>> data,int idD,float m){
        this.clusterCount = clusterNumber;
        this.fuzziness=m;
        this.idDistance=idD;
        this.data = data;

         
        assignInitialMembership();
       //System.out.println("initialisation des centres");
        int iter=0;
     
        while(true) {
        	iter++;
        	 
        	 calculateClusterCenters();
             
            //3
           // showC();
            //2 calculate cluster centers
        	updateMembershipValues();
        	//  showU ();
        	
 
            //4
          
            finalError = checkConvergence();
            if(finalError <= epsilon)
                break;
        }
        System.out.println("BPC  "+ValidationIndices.bezdekPartitionCoefficient(u));
        System.out.println("XB  "+ValidationIndices.compactnessAndSeparationMetric(data, u, clusterCenters, fuzziness));
        System.out.println("PE    "+ValidationIndices.partitionEntropyIndex(u));
        System.out.println("ITER    "+iter);
        //showU ();
       /* getAvatarCluster();
        showAvatars(4);*/
    }
    public void initData (int numberOfData,int dimension)
    {
        this.dimension=dimension;
        this.numberOfData=numberOfData;
        System.out.println("Data matrix");

    	for(int i=0;i<this.numberOfData;i++)
    	{
            ArrayList<Integer> tmp = new ArrayList<>();
            Random random = new Random();

            
    		for(int k=0;k<dimension;k++)
    		{
    			tmp.add(random.nextInt(2));
    			System.out.print(tmp.get(k)+" ");

    		}
    		System.out.println();
    		this.data.add(tmp);
    	}
    	
    }
    public void assignInitialcenters()
    {
    	  u = new float[data.size()][clusterCount];
          u_pre = new float[data.size()][clusterCount];
     	for(int i=0;i<this.dimension;i++)
    	{
            ArrayList<Float> tmp = new ArrayList<>();
            Random random = new Random();

            
    		for(int k=0;k<dimension;k++)
    		{
    			tmp.add((float)random.nextInt(2));
    			System.out.print(tmp.get(k)+" ");

    		}
    		System.out.println();
    		this.clusterCenters.add(tmp);
    	}
     	
       
    	
    }
    public void showU ()
    {
     for (int i=0;i<data.size();i++)
     {
    	 for (int k=0;k<clusterCount;k++)
    	 {
    		 System.out.print(u[i][k]+"  ");
    	 }
    	 System.out.println();
     }
    }
    public void showC ()
    {
    	System.out.println(clusterCenters.size()+"  "+clusterCenters.get(0).size());
     for (int i=0;i<clusterCount;i++)
     {
    	 for (int k=0;k<clusterCount;k++)
    	 {
    		 System.out.print(clusterCenters.get(i).get(k)+"  ");
    	 }
    	 System.out.println();
     }
    }
    /**
     * in this function we generate random data with specific option
     * @param numberOfData
     * @param dimension
     * @param minRange
     * @param maxRange
     */
  /*  public void createRandomData(int numberOfData, int dimension, int minRange, int maxRange, int clusterCount){
        this.dimension = dimension;
        ArrayList<ArrayList<Integer>> centroids = new ArrayList<>();
        centroids.add(new ArrayList<Integer>());
        int[] numberOfDataInEachArea = new int[clusterCount];
        int range = maxRange - minRange + 1;
        int step = range / (clusterCount + 1);
        for (int i = 1; i <= clusterCount; i++) {
            centroids.get(0).add(minRange + i * step);
        }

        for (int i = 0; i < dimension - 1; i++) {
            centroids.add((ArrayList<Integer>) centroids.get(0).clone());
        }
        double variance = (centroids.get(0).get(1) - centroids.get(0).get(0))/ 2.5;
        for (int i = 0; i < dimension; i++) {
            Collections.shuffle(centroids.get(i));
        }
        Random r = new Random();
        int sum = 0;
        for (int i = 0; i < clusterCount; i++) {
            int rg = r.nextInt(50) + 10;
            numberOfDataInEachArea[i] = (rg);
            sum += rg;
        }
        for (int i = 0; i < clusterCount; i++)
            numberOfDataInEachArea[i] = (int)((((double)numberOfDataInEachArea[i]) / sum) * numberOfData);

        Random fRandom = new Random();
        for (int i = 0; i < clusterCount; i++) {
            for (int j = 0; j < numberOfDataInEachArea[i]; j++) {
                ArrayList<F> tmp = new ArrayList<>();
                for (int k = 0; k < dimension; k++) {
                    tmp.add((float)(centroids.get(k).get(i) + fRandom.nextGaussian() * variance));
                }
                data.add(tmp);
            }
        }
    }*/

    /**
     * this function generate membership value for each data
     */
    private void assignInitialMembership(){
    	//System.out.println(data.size());
        u = new float[data.size()][clusterCount];
        u_pre = new float[data.size()][clusterCount];
        Random r = new Random();
        for (int i = 0; i < data.size(); i++) {
            float sum = 0;
            for (int j = 0; j < clusterCount; j++) {
                u[i][j] = r.nextFloat() * 10 + 1;
                sum += u[i][j];
            }
            for (int j = 0; j < clusterCount; j++) {
                u[i][j] = u[i][j] / sum;
            }
        }
    }

    /**
     * in this function we calculate value of each cluster
     */
    private void calculateClusterCenters(){
    	clusterCenters.clear();
        for (int i = 0; i < clusterCount; i++) {
            ArrayList<Float> tmp = new ArrayList<>();
            for (int j = 0; j < dimension; j++) {
                float cluster_ij;
                float sum1 = 0;
                float sum2 = 0;
                for (int k = 0; k < data.size(); k++) {
                    double tt = Math.pow(u[k][i], fuzziness);
                    sum1 += tt * data.get(k).get(j);
                    sum2 += tt;
                }
                cluster_ij = sum1/sum2;
                tmp.add(cluster_ij);
            }
            clusterCenters.add(tmp);
        }
    }
    
    public void getAvatarCluster ()
    {
    	for (int i=0;i<clusterCount;i++)
    	{
    		Element [] tmp=new Element[data.size()];
    		for (int j=0;j<data.size();j++)
    		{
    			tmp[j]=new Element(j,u[j][i]);
    			 
    			
    		}
    		Arrays.sort(tmp,Collections.reverseOrder());
    		avatars.add(tmp);
    	}
    	
    }
    public void showAvatars(int p)
    {
    	for (int i=0;i<clusterCount;i++)
    	{
    		Element [] tmp=avatars.get(i);
    		for (int j=0;j<p;j++)
    		{
    			System.out.print (" id= "+tmp[j].getId());
    			System.out .print(" W="+tmp[j].getW());
    			
    		}
    		System.out.println();
    		 
    	}
    	
    }

    /**
     * in this function we will update membership value
     */
    private void updateMembershipValues(){
        for (int i = 0; i < data.size(); i++) {
            for (int j = 0; j < clusterCount; j++) {
                u_pre[i][j] = u[i][j];
                float sum = 0;
                float upper = Distances.select(idDistance,data.get(i), clusterCenters.get(j));
                for (int k = 0; k < clusterCount; k++) {
                    float lower = Distances.select(idDistance,data.get(i), clusterCenters.get(k));
                    sum += Math.pow((upper/lower), 2/(fuzziness -1));
                }
                u[i][j] = 1/sum;
            }
        }
    }

    /**
     * get norm 2 of two point
     * @param p1
     * @param p2
     * @return
     */
   
    /**
     * we calculate norm 2 of ||U - U_pre||
     * @return
     */
    private double checkConvergence(){
        double sum = 0;
        for (int i = 0; i < data.size(); i++) {
            for (int j = 0; j < clusterCount; j++) {
                sum += Math.pow(u[i][j] - u_pre[i][j], 2);
            }
        }
        return Math.sqrt(sum);
    }

    /**
     * write random generated data to file for visualizing
     * @throws IOException
     */
    public void writeDataToFile(ArrayList<ArrayList<Integer>> inpData, String fileName) throws IOException {

        FileWriter fileWriter = new FileWriter("./" + fileName + ".csv");
        PrintWriter printWriter = new PrintWriter(fileWriter);

        for (int i = 0; i < inpData.size(); i++) {
             String res = "";
            for (int j = 0; j < inpData.get(i).size(); j++) {
                if(j == inpData.get(i).size() - 1)
                    res += inpData.get(i).get(j);
                else
                    res += inpData.get(i).get(j) +",";
            }
            printWriter.println(res);
        }
        printWriter.close();
    }

}
