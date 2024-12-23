package users;
import java.util.Scanner;

import database.DatabaaseeActions;
import enums.UserType;

/**
 * 
 */
public abstract class Admin extends Employee {

    public Admin(int id,
                    String username,
                    String password,
                    String name
    ) {
        super(id, username, password, name);
    }

    public void createUser() {

        System.out.println("Please choose UserType: ");
        System.out.println("1 - Admin");
        System.out.println("2 - Manager");
        System.out.println("3 - Teacher");
        System.out.println("4 - Student");
        System.out.println("5 - TechSupport");
        System.out.println("0 - Exit");
        String userType ;
        Scanner sc = new Scanner(System.in);
        String action = sc.nextLine();
        switch (action) {
            case "1":
                userType = UserType.ADMIN.toString();
                System.out.println("Enter the username of the user: ");
                String userNameAdmin = sc.nextLine();

                System.out.println("Enter the password of the user: ");
                String passwordAdmin = sc.nextLine();

                System.out.println("Enter the name of the user: ");
                String nameAdmin = sc.nextLine();

                DatabaaseeActions.save("Users", userNameAdmin, passwordAdmin, nameAdmin, userType);
                sc.close();
                break;
            case "2":
                userType = UserType.MANAGER.toString();
                System.out.println("Enter the username of the user: ");
                String userNameManager = sc.nextLine();
                System.out.println("Enter the password of the user: ");
                String passwordManager = sc.nextLine();
                System.out.println("Enter the name of the user: ");
                String nameManager = sc.nextLine();
                DatabaaseeActions.save("Managers", userNameManager, passwordManager, nameManager, userType);
                break;
            case "3":
                userType = UserType.TEACHER.toString();
                System.out.println("Enter the username of the user: ");

                String userNameTeacher = sc.nextLine();
                System.out.println("Enter the password of the user: ");

                String passwordTeacher = sc.nextLine();
                System.out.println("Enter the name of the user: ");

                String nameTeacher = sc.nextLine();
                DatabaaseeActions.save("Teachers", userNameTeacher, passwordTeacher, nameTeacher, userType);

                break;
            case "4":
                userType = UserType.STUDENT.toString();
                System.out.println("Enter the username of the user: ");

                String userNameStudent = sc.nextLine();
                System.out.println("Enter the password of the user: ");

                String passwordStudent = sc.nextLine();
                System.out.println("Enter the name of the user: ");

                String nameStudent = sc.nextLine();
                DatabaaseeActions.save("Students", userNameStudent, passwordStudent, nameStudent, userType);
                break;
            case "5":
                userType = UserType.TECHSUPPORT.toString();
                System.out.println("Enter the username of the user: ");
                String userNameTechSupport = sc.nextLine();
                System.out.println("Enter the password of the user: ");
                String passwordTechSupport = sc.nextLine();
                System.out.println("Enter the name of the user: ");
                String nameTechSupport = sc.nextLine();
                DatabaaseeActions.save("TechSupports", userNameTechSupport, passwordTechSupport, nameTechSupport, userType);

            case "0":
                break;
        }
        sc.close();
    }


//    public void createUser() {
//        Scanner sc = new Scanner(System.in);
//        DatabaaseeActions.save("Users", new Object());
//
//
//
//        System.out.println("Enter the username of the user: ");
//
//        Scanner scanner = new Scanner(System.in);
//
//        String userNameManager = scanner.nextLine();
//        System.out.println("Enter the password of the user: ");
//        String passwordManager = scanner.nextLine();
//        System.out.println("Enter the name of the user: ");
//        String nameManager = scanner.nextLine();
//        DatabaaseeActions.save("Managers", userNameManager, passwordManager, nameManager);
//        scanner.close();
//    }


    public void updateUser(User updatedUser) {
        DatabaaseeActions.update("Users", updatedUser);
    }


    public void removeUser(User user) {
        DatabaaseeActions.delete("Users", user.getId());

    }


    public void seeLogFiles() {
        System.out.println(DatabaaseeActions.findAll("Log_files"));
    }

//    public void addResearcher() {
//        // TODO implement here
//    }

//    /**
//     *
//     */
//    public void changeInfo() {
//        // TODO implement here
//    }

    /**
     * 
     */
    public void seeAllUsers() {

        System.out.println(DatabaaseeActions.findAll("Users"));
    }

    /**
     *
     */
    public void viewMenu() {
        System.out.println("Menuuuuuu");
    }

    /**
     *
     */
//    public void changeInfo() {
//
//
//    }

}