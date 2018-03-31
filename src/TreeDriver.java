import java.io.*;
import java.util.Scanner;

/**
 * Suraj Sharma
 * Id # 109606910
 * HomeWork 4 - Trees
 * <p/>
 * This class contains the main method for the program which runs and displays the method.
 * I have commented out a few print statments just in case you want to check my program.
 * Also it works for the sample file professor has provided (without comments).
 * Haven't checked for any other file.
 * <p/>
 * Creative Ideas:
 * I have added an option of handling multiple trees in my program.
 * Which helps me load and save multiple trees.
 * This can be very helpful for the customer service program.
 * Since, one company ones many different items.
 * For instance, a company owns a mobile phone and washing machine both.
 * Then it can load two different trees in one , namely phone and washing machine.
 * and both their customers can be handled.
 * My program also goes back just in case you have come a step further.
 * I have also added the counter which tells me the number of customers being helped.
 */
public class TreeDriver {

    public static final int SIZE = 10;

    public static void main(String[] args) {


        Tree[] collection = new Tree[SIZE];

        int position = 0;


        Tree tree = new Tree();
        TreeNode cursor = new TreeNode();

        Scanner s = new Scanner(System.in);

        String input = "";
        String fileName = "example.txt";
        int id = 1;


        while (!input.equalsIgnoreCase("q")) {

            System.out.println("Current tree: " + tree.getName());
            System.out.println();

            System.out.println("Menu Option: ");
            System.out.println("L - Load a Tree.");
            System.out.println("H - Begin a Help Session.");
            System.out.println("T - Traverse the Tree in preorder.");
            System.out.println("C - To Change the current Tree");
            System.out.println("P - To Print All Trees.");
            System.out.println("Q - Quit");

            System.out.print("Your Choice: ");
            input = s.next();
            System.out.println();

            if (input.equalsIgnoreCase("L")) {
                try {

                    tree = new Tree();
                    System.out.print("Please Enter the name of the tree: ");
                    String name = s.next();
                    tree.setName(name);

                    System.out.print("Please Enter the file name: ");
                    fileName = s.next();

                    File file = new File(fileName);
                    Scanner sc = new Scanner(file);

                    String numberOfNodes = "0";
                    int number = 1;
                    String parentLabel = "";
                    String label = "";
                    String message = "";
                    String prompt = "";


                    label = sc.nextLine();
                    prompt = sc.nextLine();
                    message = sc.nextLine();

                    cursor = new TreeNode(label, prompt, message);
                    tree.setRoot(cursor);

                    int count = 0;

                    while ((sc.hasNext())) {

                        // System.out.println("count: "+ String.valueOf(count));
                        if (numberOfNodes.compareTo(String.valueOf(count)) == 0) {
                            parentLabel = sc.next();

                            numberOfNodes = sc.next();
                            count = 0;
                        }

//                        System.out.println("Parent " +parentLabel);
//                        System.out.println(numberOfNodes+ ":numberoFNodes");

                        label = sc.next();
                        sc.nextLine();
//                        System.out.println(label+ " label");
                        prompt = sc.nextLine();
//                        System.out.println("prompt " +prompt);
                        message = sc.nextLine();
//                        System.out.println("message "+message);
//                        System.out.println();

                        count++;


                        try {
                            boolean answer = tree.addNode(label, prompt, message, parentLabel);
//                            if (answer)
//                                System.out.println("added");
//                            else
//                                System.out.println("not added");
                        } catch (NodeFullException e) {
                            System.out.println(e.getMessage());
                        }

                    }
                    sc.close();

                    collection[position] = tree;
                    position++;

                } catch (FileNotFoundException ex) {
                    System.out.println(
                            "Unable to open file '" +
                                    fileName + "'");
                }

            } else if (input.equalsIgnoreCase("H")) {
                System.out.println("number of customers helped today: " + id);
                id++;

                if (tree.getRoot() != null) {
                    cursor = tree.beginSession(cursor);


                    while (!cursor.isLeaf()) {
                        System.out.println();
                        System.out.print("Your choice: ");
                        int answer = s.nextInt();
                        System.out.println();

                        if (answer == 0)
                            break;
                        if (answer > 10)
                            System.out.println("Input cannot be greater than 10");
                        else
                            cursor = tree.session(answer, cursor);
                    }


                    System.out.println("Thank you!");

                } else {
                    System.out.println("Please load the tree before starting the session!");
                }

            } else if (input.equalsIgnoreCase("T"))
                tree.preOrder();
            else if (input.equalsIgnoreCase("C")) {
                System.out.print("Please Enter the name of the tree: ");
                String search = s.next();

                for (int i = 0; i < position; i++) {
                    if (collection[i].getName().equalsIgnoreCase(search)) {
                        tree = collection[i];
                        System.out.println("Your current Tree is now " + tree.getName());
                        break;
                    } else if (i == position - 1)
                        System.out.println("The name you have entered is not present");
                }


            } else if (input.equalsIgnoreCase("P")) {
                System.out.println();
                System.out.println("Here is the List of all the trees");
                for (int i = 0; i < position; i++)
                    System.out.println(i + 1 + " " + collection[i].getName());

            } else if (input.equalsIgnoreCase("Q")) {

            } else {
                System.out.println("Please enter a valid input!!");
            }
            System.out.println();
        }


    }

}

