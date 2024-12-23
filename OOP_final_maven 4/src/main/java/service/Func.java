package service;
import java.util.Scanner;
import java.util.List;
import java.util.InputMismatchException;
import java.util.Vector;


public class Func {
    static Scanner scanner = new Scanner(System.in);
    private List list;

    public String getStringInput(String message) {
        System.out.println(message);
        return scanner.nextLine();
    }
    public int getIntInput(String message) {
        System.out.println(message);
        return Integer.parseInt(scanner.nextLine());
    }
    public void printList(Vector<ResearchJournal> journals) {
        new Func().printList(journals); // Use the Func class's method
    }
    public int validateChoice(int n) {
        Scanner s = new Scanner(System.in);
        int choice;
        while (true) {
            try {
                choice = s.nextInt();
                if (0 <= choice && choice <= n) {
                    break;
                } else {
                    System.out.println("Please enter from 0 to " + n);
                }
            } catch (InputMismatchException e) {
                System.out.println("Please enter a number from 0 to " + (n));
                s.nextLine();
            }
        }
        return choice;
    }

}