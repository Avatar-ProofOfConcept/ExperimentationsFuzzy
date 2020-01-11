import java.util.ArrayList;


public class ValidationIndices {
	
	 
	/**
     * Computes the <b>partition coefficient</b> as proposed by Bezdek in
     * <i>"Bezdek JC. Cluster validity with fuzzy sets. J Cybern 1974;3:58–73"</i>
     * It considers the average sum of the squared fuzzy membership values
     * Better clustering partitions induce higher values of the coefficient
     * @param U The float matrix containing the fuzzy memberships, result of the fuzzy
     * clustering algorithm
     * @return the computed partition coefficient as a double
     */
    public static double bezdekPartitionCoefficient(float [][] U){

        double sum = 0;
        for(int i = 0; i < U.length; i++)
        {
            for(int c = 0; c < U[0].length; c++)
            {
                sum += Math.pow(U[i][c], 2);
            }

        }
        sum = sum / U.length;

        return sum;
    }

    /**
     * Computes the <b>compactness and separation metric</b> as proposed by Xie and
     * Ben in <i>Xie XL, Beni GA. Validity measure for fuzzy clustering. IEEE Trans
     * Pattern Anal Mach Intell 1991;3:841–6</i>.
     * The numerator determines the objective function J and represents a form of
     * intracluster distance wherease the denominator computes the intercluster
     * distance considering the minimum distance between the cluster centroids.
     * The optimal clustering is achieved when the metric is minimized.
     * @param X the data matrix
     * @param U the cluster fuzzy membership matrix
     * @param V the cluster centroid matrix
     * @param m the fuzzyness parameter
     * @return the computed metric as a double
     */
    public static double compactnessAndSeparationMetric(ArrayList<ArrayList<Integer>> X, float [][] U,
    		ArrayList<ArrayList<Float>> V, double m){
        
        double numerator = 0, denominator = 0, min = Double.MAX_VALUE;

        for(int i = 0; i < U.length; i++)
        {
            for(int c = 0; c < V.size(); c++)
            {
                numerator += Math.pow(U[i][c], m) * Distances.DistanceEuclidien(X.get(i), V.get(c));
            }
        }

        for(int c = 0; c < V.size(); c++)
        {
            for(int k = 0; k < V.size(); k++)
            {
                if(k != c)
                {
                    double distance = Distances.DistanceEuclidien2(V.get(c), V.get(k));
                    if(distance < min)
                    {
                        min = distance;
                    }
                }
            }
        }


        denominator = X.size() * min;

        return numerator / denominator;
    }

    /**
     * Computes the <b>partition entropy Index</b> as a mean of esteeming cluster
     * validity as proposed in <i>Bezdek JC. Mathematical models for systematic
     * and taxonomy. In: proceedings of eigth international conference on numerical
     * taxonomy,San Francisco; 1975, p. 143–66</i>.
     * It computes the entropy based on the cluster membership values. Minimizing
     * it will produce better clustering partitions.
     * @param U the cluster fuzzy membership matrix
     * @return the computed index as a double
     */
    public static double partitionEntropyIndex(float [][] U){

        double sum = 0;
        double log2 = Math.log(2);
        for(int i = 0; i < U.length; i++)
        {
            for(int c = 0; c < U[0].length; c++)
            {
                sum += U[i][c] * (Math.log(U[i][c] + Double.MIN_VALUE) / log2);
            }

        }

        return - sum / U.length;
    }
}
