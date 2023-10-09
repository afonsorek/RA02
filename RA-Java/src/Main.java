import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        //Define the array of values to insert in the random method
        int[] numValues = { 100, 500, 1000, 10000, 20000, 1000000};
        int minValue = 1;
        int maxValue = 1000000;

        List<Double> BinaryInsertions = new ArrayList<>();
        List<Double> AVLInsertions = new ArrayList<>();

        List<Double> BinarySearchs = new ArrayList<>();
        List<Double> AVLSearchs = new ArrayList<>();

        List<Double> BinaryRemoves = new ArrayList<>();
        List<Double> AVLRemoves = new ArrayList<>();

        //Number of tests to get average
        for (int tests = 0; tests < 10; tests++){
            //The main loop for each number of insertions on the list
            for (int n : numValues) {
                List<Integer> randomValues = new ArrayList<>();
                Random rand = new Random();

                //Randomly populates the randomValues list
                for (int i = 0; i < n; i++) {
                    //Creates a random int between 1 and 1000000
                    int randomValue = rand.nextInt((maxValue - minValue) + 1) + minValue;
                    randomValues.add(randomValue);
                }

                //Creates two new trees
                BinaryTree binaryTree = new BinaryTree();
                AVLTree avlTree = new AVLTree();

                // Insert calls

                //Binary tree
                //Gets the start time of the procedure in int ms values
                double startTimeBinaryTreeInsert = System.currentTimeMillis();

                //Add the random values of the list
                for (int value : randomValues) {
                    binaryTree.insert(value);
                }

                //Gets the end time of the procedure in int ms values
                double endTimeBinaryTreeInsert = System.currentTimeMillis();
                //Calculates the time of the procedure (end time - start time)
                double executionTimeBinaryTreeInsert = endTimeBinaryTreeInsert - startTimeBinaryTreeInsert;

                //AVL
                //Gets the start time of the procedure in int ms values
                double startTimeAVLTreeInsert = System.currentTimeMillis();

                //Add the random values of the list
                for (int value : randomValues) {
                    avlTree.insert(value);
                }

                //Gets the end time of the procedure in int ms values
                double endTimeAVLTreeInsert = System.currentTimeMillis();
                //Calculates the time of the procedure (end time - start time)
                double executionTimeAVLTreeInsert = endTimeAVLTreeInsert - startTimeAVLTreeInsert;

                //-----------------------------------------------------------------------------------------//

                // Search
                // Same process from the insertion, gets the time, calls the method, and calculates the total time

                //Binary
                double startTimeBinaryTreeSearch = System.currentTimeMillis();

                for (int value : randomValues) {
                    binaryTree.search(value);
                }

                double endTimeBinaryTreeSearch = System.currentTimeMillis();
                double executionTimeBinaryTreeSearch = endTimeBinaryTreeSearch - startTimeBinaryTreeSearch;

                //AVL
                double startTimeAVLTreeSearch = System.currentTimeMillis();

                for (int value : randomValues) {
                    avlTree.search(value);
                }

                double endTimeAVLTreeSearch = System.currentTimeMillis();
                double executionTimeAVLTreeSearch = endTimeAVLTreeSearch - startTimeAVLTreeSearch;

                //-----------------------------------------------------------------------------------------//

                //Remove

                //Binary
                double startTimeBinaryTreeRemove = System.currentTimeMillis();

                for (int value : randomValues) {
                    binaryTree.remove(value);
                }

                double endTimeBinaryTreeRemove = System.currentTimeMillis();
                double executionTimeBinaryTreeRemove = endTimeBinaryTreeRemove - startTimeBinaryTreeRemove;

                //AVL
                double startTimeAVLTreeRemove = System.currentTimeMillis();

                for (int value : randomValues) {
                    avlTree.delete(value);
                }

                double endTimeAVLTreeRemove = System.currentTimeMillis();
                double executionTimeAVLTreeRemove = endTimeAVLTreeRemove - startTimeAVLTreeRemove;

                //-----------------------------------------------------------------------------------------//

                System.out.println("=== Number of inserts: " + n + " ===");

                System.out.println("--------------------------------");
                System.out.println("Insert - Binary Tree: " + executionTimeBinaryTreeInsert + " ms");
                System.out.println("Insert - AVL Tree: " + executionTimeAVLTreeInsert + " ms");

                System.out.println("--------------------------------");
                System.out.println("Search - Binary Tree: " + executionTimeBinaryTreeSearch + " ms");
                System.out.println("Search - AVL Tree: " + executionTimeAVLTreeSearch + " ms");

                System.out.println("--------------------------------");
                System.out.println("Remove - Binary Tree: " + executionTimeBinaryTreeRemove + " ms");
                System.out.println("Remove - AVL Tree: " + executionTimeAVLTreeRemove + " ms\n");


                //If the value of insertions is greater or equal to 0, it adds to the array for the average sum
                if (n >= 0){
                    BinaryInsertions.add(executionTimeBinaryTreeInsert);
                    AVLInsertions.add(executionTimeAVLTreeInsert);

                    BinarySearchs.add(executionTimeBinaryTreeSearch);
                    AVLSearchs.add(executionTimeAVLTreeSearch);

                    BinaryRemoves.add(executionTimeBinaryTreeRemove);
                    AVLRemoves.add(executionTimeAVLTreeRemove);
                }
            }

        }

        //Gets the average of each operation
        double avgInsertBinary = 0;
        for(double avg : BinaryInsertions){
            avgInsertBinary += avg;
        };
        avgInsertBinary = (avgInsertBinary/BinaryInsertions.size());

        double avgInsertAVL = 0;
        for(double avg : AVLInsertions){
            avgInsertAVL += avg;
        };
        avgInsertAVL = (avgInsertAVL/AVLInsertions.size());

        double avgSearchBinary = 0;
        for(double avg : BinarySearchs){
            avgSearchBinary += avg;
        };
        avgSearchBinary = (avgSearchBinary/BinarySearchs.size());

        double avgSearchAVL = 0;
        for(double avg : AVLSearchs){
            avgSearchAVL += avg;
        };
        avgSearchAVL = (avgSearchAVL/AVLSearchs.size());

        double avgRemoveBinary = 0;
        for(double avg : BinaryRemoves){
            avgRemoveBinary += avg;
        };
        avgRemoveBinary = (avgRemoveBinary/BinaryRemoves.size());

        double avgRemoveAVL = 0;
        for(double avg : AVLRemoves){
            avgRemoveAVL += avg;
        };
        avgRemoveAVL = (avgRemoveAVL/AVLRemoves.size());


        System.out.println("=== Average Time of Procedures ===");

        System.out.println("--------------------------------");
        System.out.println("Insert time - Binary Tree: " + avgInsertBinary + " ms");
        System.out.println("Insert time - AVL Tree: " + avgInsertAVL + " ms");

        System.out.println("--------------------------------");
        System.out.println("Search time - Binary Tree: " + avgSearchBinary + " ms");
        System.out.println("Search time - AVL Tree: " + avgSearchAVL + " ms");

        System.out.println("--------------------------------");
        System.out.println("Remove time - Binary Tree: " + avgRemoveBinary + " ms");
        System.out.println("Remove time - AVL Tree: " + avgRemoveAVL + " ms\n");
    }


}