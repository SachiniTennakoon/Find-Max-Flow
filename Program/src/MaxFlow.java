//Name:Sachini Tennakoon
//IIT Num:2018519
//UOW Numw1742227
//Sorce:https://www.geeksforgeeks.org/ford-fulkerson-algorithm-for-maximum-flow-problem/


import java.lang.*;
import java.util.LinkedList;

class MaxFlow {
    static int V;   //Number of vertices in graph


    static  String[] arrayPath=new String[]{"0","1","2","3","4","5"}; //Store all the nodes to find the augmenting path

    public MaxFlow(int v) {   //constructer
        this.V = v;
    }

    /* Returns true if there is a path from source 's' to sink
          't' in residual graph. Also fills parent[] to store the
          path */
    boolean bfs(int rGraph[][], int s, int t, int parent[]) {
        // Create a visited array and mark all vertices as not 
        // visited 
        boolean visited[] = new boolean[V];
        for (int i = 0; i < V; ++i)
            visited[i] = false;

        // Create a queue, enqueue source vertex and mark 
        // source vertex as visited 
        LinkedList<Integer> queue = new LinkedList<Integer>();
        queue.add(s);
        visited[s] = true;
        parent[s] = -1;

        // Standard BFS Loop 
        while (queue.size() != 0) {  //Check the size is not 0
            int u = queue.poll();  //remove the 1st vertex from the queue

            for (int v = 0; v < V; v++) {
                if (visited[v] == false && rGraph[u][v] > 0) {
                    queue.add(v);  //add v to queue
                    parent[v] = u;  //add u to parent array
                    visited[v] = true; //Mark v as visited
                }
            }
        }

        // If we reached sink in BFS starting from source, then 
        // return true, else false 
        return (visited[t] == true);

    }

    // Returns tne maximum flow from s to t in the given graph 
    int fordFulkerson(int graph[][], int s, int t) {
        int u, v;

        // Create a residual graph and fill the residual graph 
        // with given capacities in the original graph as 
        // residual capacities in residual graph 

        // Residual graph where rGraph[i][j] indicates 
        // residual capacity of edge from i to j (if there 
        // is an edge. If rGraph[i][j] is 0, then there is 
        // not) 
        int rGraph[][] = new int[V][V];

        for (u = 0; u < V; u++)
            for (v = 0; v < V; v++)
                rGraph[u][v] = graph[u][v];

        // This array is filled by BFS and to store path 
        int parent[] = new int[V];

        int max_flow = 0;  // There is no flow initially 

        // Augment the flow while tere is path from source 
        // to sink


        while (bfs(rGraph, s, t, parent)) {
            // Find minimum residual capacity of the edhes 
            // along the path filled by BFS. Or we can say 
            // find the maximum flow through the path found.
            String pathString="";
            int path_flow = Integer.MAX_VALUE;
//            int[][] pathArray = new int[1][1];
            for (v = t; v != s; v = parent[v]) {
                u = parent[v];

                pathString= "---> " +arrayPath[v] +pathString;  //add the path to pathstring




                path_flow = Math.min(path_flow, rGraph[u][v]); //Check the minimum


            }

            pathString="0"+pathString; //add 0 becuase source is not read in the array
            System.out.println("Augmentng path :["+pathString + "] Flow : " + path_flow);

            // update residual capacities of the edges and
            // reverse edges along the path
            for (v = t; v != s; v = parent[v]) {
                u = parent[v];
                rGraph[u][v] -= path_flow;
                rGraph[v][u] += path_flow;
            }

            // Add path flow to overall flow
            max_flow += path_flow;
        }

        // Return the overall flow
        return max_flow;
    }


    // Driver program to test above functions
    public static void main(String[] args) throws java.lang.Exception {

        Main main = new Main();

        int command = 0;
        System.out.println("*********************************");
        System.out.println("WELCOME TO CALCULATE THE MAX FLOW.");
        System.out.println("*********************************");

        while (command != 4) {
            System.out.println("*--------Main Menu--------*");
            System.out.println(" Select Option:");
            System.out.println("\t1) use the available dataset");
            System.out.println("\t2)Enter data manually");
            System.out.println("\t3)Enter the data set path");
            System.out.println("\t4)Exit Program");
            System.out.println("----------------------------");
            System.out.println(">");

            System.out.println("Please Enter the command:");
            command = main.getIntInput();
            while (command > 4 || command < 1) {                                //Check if the command is between 1 and 4 if not ask user to enter the command again
                System.out.println("Invalid command!! reenter");
                command = main.getIntInput();
            }


            int option = 0;

            int dataSet[][] = new int[0][0];
            int graph[][] = new int[0][0];
            int original[][] = new int[][]{
                    {0, 16, 13, 0, 0, 0},
                    {0, 0, 10, 12, 0, 0},
                    {0, 4, 0, 0, 14, 0},
                    {0, 0, 9, 0, 0, 20},
                    {0, 0, 0, 7, 0, 4},
                    {0, 0, 0, 0, 0, 0}
            };
            int vertice = 0;



            if (command == 1) {
                vertice = 6;
                dataSet = original;
                graph = dataSet;
                System.out.println("---GRAPH---");
                for (int i = 0; i < (vertice); i++) {
                    for (int j = 0; j < (vertice); j++) {
                        System.out.print(graph[i][j] + " ");
                    }
                    System.out.println();
                }
                System.out.println("------------");
                while (option != 4) {
                    System.out.println(" Select Option:");
                    System.out.println("\t1) add an edge");
                    System.out.println("\t2) delete an edge");
                    System.out.println("\t3) modify an edge");
                    System.out.println("\t4) No modification");
                    System.out.println("Please Enter the command:");
                    System.out.println(">");
                    option = main.getIntInput();
                    while (command > 4 || command < 1) {                                //Check if the command is between 1 and 4 if not ask user to enter the command again
                        System.out.println("Invalid command!! reenter");
                        command = main.getIntInput();
                    }
                    if (option == 1) {
                        int addRow = main.row();   // get the row number
                        int addColumn = main.column(); //get the column number
                        if (graph[addRow][addColumn] == 0) {  //check if the connection is 0
                            graph[addRow][addColumn] = main.newCapacity(); //add the new capacity
                            System.out.println("The new edge was added");
                        } else {
                            System.out.println("The edge is already added");
                        }

                    }
                    if (option == 2) {
                        int deleteRow = main.row();
                        int deleteColumn = main.column();
                        if (graph[deleteRow][deleteColumn] != 0) {  //chaeck the connection is not 0
                            graph[deleteRow][deleteColumn] = 0;
                            System.out.println("The edge is deleted");
                        } else {
                            System.out.println("The edge is already deleted");
                        }
                    }
                    if (option == 3) {
                        int modifyRow = main.row();
                        int modifyColumn = main.column();
                        int capacity =main.newCapacity();
                        graph[modifyRow][modifyColumn] = capacity;
                        System.out.println("Edge is modified");
                    }
                    if (option == 4) {
                        break;

                    }
                }
            }
                if (command == 2) {
                    vertice = main.addVertice();
                    dataSet = main.test();
                    graph = dataSet;
                    System.out.println("---GRAPH---");
                    for (int i = 0; i < (vertice); i++) {
                        for (int j = 0; j < (vertice); j++) {
                            System.out.print(graph[i][j] + " ");
                        }
                        System.out.println();
                    }

                    while (option != 4) {
                        System.out.println(" Select Option:");
                        System.out.println("\t1) add an edge");
                        System.out.println("\t2) delete an edge");
                        System.out.println("\t3) modify an edge");
                        System.out.println("\t4) No modification");
                        System.out.println("Please Enter the command:");
                        System.out.println(">");
                        option = main.getIntInput();
                        while (command > 4 || command < 1) {                                //Check if the command is between 1 and 4 if not ask user to enter the command again
                            System.out.println("Invalid command!! reenter");
                            command = main.getIntInput();
                        }
                        if (option == 1) {
                            int addRow = main.row();
                            int addColumn = main.column();
                            if (graph[addRow][addColumn] == 0) {
                                graph[addRow][addColumn] = main.newCapacity();
                                System.out.println("The new edge was added");
                            } else {
                                System.out.println("The edge is already added");
                            }

                        }
                        if (option == 2) {
                            int deleteRow = main.row();
                            int deleteColumn = main.column();
                            if (graph[deleteRow][deleteColumn] != 0) {
                                graph[deleteRow][deleteColumn] = 0;
                                System.out.println("The edge is deleted");
                            } else {
                                System.out.println("The edge is already deleted");
                            }
                        }
                        if (option == 3) {
                            int modifyRow = main.row();
                            int modifyColumn = main.column();
                            int capacity =main.newCapacity();
                            graph[modifyRow][modifyColumn] = capacity;
                            System.out.println("Edge is modified");
                        }
                        if (option == 4) {
                            break;

                        }
                    }

                }
                if (command == 3) {
                    vertice = main.addVertice();
                    dataSet = main.dataset();
                    graph = dataSet;
                    System.out.println("---GRAPH---");
                    for (int i = 0; i < (vertice); i++) {
                        for (int j = 0; j < (vertice); j++) {
                            System.out.print(graph[i][j] + " ");
                        }
                        System.out.println();
                    }
                    while (option != 4) {
                        System.out.println(" Select Option:");
                        System.out.println("\t1) add an edge");
                        System.out.println("\t2) delete an edge");
                        System.out.println("\t3) modify an edge");
                        System.out.println("\t4) No modification");
                        System.out.println("Please Enter the command:");
                        System.out.println(">");
                        option = main.getIntInput();
                        while (command > 4 || command < 1) {                                //Check if the command is between 1 and 4 if not ask user to enter the command again
                            System.out.println("Invalid command!! reenter");
                            command = main.getIntInput();
                        }
                        if (option == 1) {
                            int addRow = main.row();
                            int addColumn = main.column();
                            if (graph[addRow][addColumn] == 0) {
                                graph[addRow][addColumn] = main.newCapacity();
                                System.out.println("The new edge was added");
                            } else {
                                System.out.println("The edge is already added");
                            }

                        }
                        if (option == 2) {
                            int deleteRow = main.row();
                            int deleteColumn = main.column();
                            if (graph[deleteRow][deleteColumn] != 0) {
                                graph[deleteRow][deleteColumn] = 0;
                            } else {
                                System.out.println("The edge is already deleted");
                                System.out.println("The edge is deleted");
                            }
                        }
                        if (option == 3) {
                            int modifyRow = main.row();
                            int modifyColumn = main.column();
                            int capacity =main.newCapacity();
                            graph[modifyRow][modifyColumn] = capacity;
                            System.out.println("Edge is modified");
                        }
                        if (option == 4) {
                            break;
                        }
                    }
                }
                if (command == 4) {
                    break;
                }

                MaxFlow m = new MaxFlow(vertice);
                Stopwatch stopwatch = new Stopwatch(); //start stop watch
                long start = System.nanoTime(); //start time


                System.out.println("The maximum possible flow :" +
                        m.fordFulkerson(graph, 0, (vertice - 1)));

                long end = System.nanoTime();  //end time
                long elapsedTime = end - start;

                System.out.println("");
                System.out.println("Run time in seconds:" +stopwatch.elapsedTime() +" seconds"); //end stopwatch and get the run time
                System.out.println("Run time in nanoseconds: " +elapsedTime + " nanosecond");
            System.out.println("");

            }
            System.out.println("You choose to exit the program");


        }
    }
