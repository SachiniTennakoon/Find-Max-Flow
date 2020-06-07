import java.io.*;
import java.util.Scanner;

public class Main {

    int vertices;
    int userVertice;
    int matrix[][] = new int[0][0];

    public int addVertice() {
        Scanner read = new Scanner(System.in);
        System.out.print("Enter the number of vertices"); //Method to return the  number  of vertices
        vertices = read.nextInt();
        return vertices;
    }


    public int[][] dataset() throws IOException {
        Scanner read = new Scanner(System.in);
        System.out.print("Enter the path to the dataset"); //Method return the path
        String path = read.nextLine();

        Scanner input = new Scanner(new File(path));
        int m = vertices;
        int n = vertices;
        int[][] a = new int[m][n];
        while (input.hasNextInt()) {
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++)
                    a[i][j] = input.nextInt();
            }

        }
        return a;
    }


    public int row() {
        Scanner read = new Scanner(System.in);
        System.out.print("Enter row number)");  //Method return the number of rows
        int num1 = read.nextInt();
        return num1;

    }

    public int column () {
        Scanner read = new Scanner(System.in);
        System.out.print("Enter column number"); //Method return the number of columns
        int num2 = read.nextInt();
        return num2;
    }

    public int newCapacity() {
        Scanner read = new Scanner(System.in);
        System.out.print("Enter the new capacity"); //Method return the new capacity
        int capacity = read.nextInt();
        return capacity;
    }



    public int getIntInput() {

        Scanner read = new Scanner(System.in);
        while (!read.hasNextInt()) {                                      //Check if the input is nt a integer
            System.out.println("Invalid index!! reenter");                  //if not an integer display a error message
            read.next();
        }
        return read.nextInt();
    }

//    public int getUserVertice() {
//        Scanner read = new Scanner(System.in);
//        System.out.print("Enter the number of vertices");
//        userVertice = read.nextInt();
//        return userVertice;
//    }

    // Function to read matrix
    public int[][] test() {
                                                                                //Method that let user to input data


        int[][] data = new int[vertices][vertices];
        Scanner in = new Scanner(System.in);
        System.out.println("enter the elementss for the Matrix");
        for (int row = 0; row < vertices; row++) {
            for (int col = 0; col < vertices; col++) {

                data[row][col] = in.nextInt();


            }
            System.out.println();
        }
        return data;


    }
}

