package upiPaymentSystem;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.InputMismatchException;

public class Main extends AtmPinVerification{
    Scanner scanner = new Scanner(System.in);
    public static int upiPin = 7932;

    private static String bankName;
    private double balance;

    public Main(){
        balance = 1000000.0;
        bankName = "State Bank of India";
    }
    public double getBalance(){
        return balance;
    }


    public static String getBankName() {
        return bankName;
    }

    void sendMoney() throws InputMismatchException{
        while(true) {
            System.out.println("Send Money to: ");
            System.out.println("1. Mobile\n2. UPI ID\n3. Saved Beneficiary\n4. IFSC\\AC no.\n5. Back");
            System.out.print("Enter here: ");
//          int opt = scanner.nextInt();
            String input = scanner.nextLine();
            int opt = Integer.parseInt(input);
            long sendToNumber;

            if (opt == 1) {
                System.out.print("Enter no.: ");
                sendToNumber = scanner.nextLong();
                if (Long.toString(sendToNumber).length() == 10) {
                    System.out.print("Enter Amount: ");
                    double amt = scanner.nextDouble();
                    if (amt > 0 && balance-amt > 0) {
                        System.out.print("Enter UPI PIN: ");
                        int pin = scanner.nextInt();
                        if (pin == upiPin) {
                            balance -= amt;
//                            transactions(amt);
                            System.out.println("Money Sent to " + sendToNumber);
                            break;
                        } else {
                           throw new InvalidPin();
                        }
                    } else if(balance-amt < 0){
                        throw new InsufficientBalanceException();
                    }

                } else {
//                    throw new LessThanZeroException();
                    System.out.println("Invalid Number!");
                    break;
                }
            } else if (opt == 2) {
                System.out.print("Enter UPI ID: ");
                String upiId = scanner.next();
                //              String input = upiId; // Replace this with the user's input
                String pattern = "[a-z0-9]+@[a-z]+"; //to match demo@upi format
                Pattern regexPattern = Pattern.compile(pattern);
                Matcher matcher = regexPattern.matcher(upiId);

                if (matcher.matches()) {
                    System.out.print("Enter amount: ");
                    double amt = scanner.nextInt();
                    if (amt > 0) {
                        System.out.print("Enter UPI Pin: ");
                        int pin = scanner.nextInt();
                        if (pin == upiPin) {
                            balance -= amt;
                            System.out.println("Money sent to " + upiId);
                            break;
                        } else {
                            System.out.println("Invalid  UPI Pin!");
                        }

                    } else {
                        System.out.println("Invalid Input!");
                    }

                } else {
                    System.out.println("The input string does not match the format: demo@upi");
                }
            } else if (opt == 3) {
                System.out.println("We are working on it");

            } else if (opt == 4) {
                System.out.print("Enter Account number: ");
                long accountNo = scanner.nextLong();
                System.out.print("Enter Amount: ");
                double amt = scanner.nextDouble();
                if (amt > 0) {
                    System.out.print("Enter UPI Pin: ");
                    int pin = scanner.nextInt();
                    if (pin == upiPin) {
                        balance -= amt;
                        System.out.println("Money sent to " + accountNo);
                    } else {
                        System.out.println("Invalid  UPI Pin!");
                    }
                } else {
                    System.out.println("Invalid Input!");
                }
            } else if (opt == 5) {
                return;
            } else {
                break;
            }
        }

    }


    void requestMoney() throws InputMismatchException{
        while(true){
            System.out.print("1. UPI ID\n2. Mobile NO.\n3. Back\nEnter here: ");
//          int opt = scanner.nextInt();
            String input = scanner.nextLine();
            int opt = Integer.parseInt(input);

            if(opt==1){
                System.out.print("Enter UPI ID: ");
                String upiId = scanner.next();
                String pattern = "[a-z0-9]+@[a-z]+"; //to match demo@upi format
                Pattern regexPattern = Pattern.compile(pattern);
                Matcher matcher = regexPattern.matcher(upiId);

                if(matcher.matches()){
                    System.out.print("Enter UPI Pin: ");
                    int pin = scanner.nextInt();
                    if(pin == upiPin){
                        System.out.println("Request sent to "+upiId);
                        break;
                    }
                    else{
                        System.out.println("Invalid Pin");
                    }
                }else{
                    System.out.println("Invalid UPI ID");
                }


            }
            else if(opt==2){
                System.out.print("Enter number: ");
                long sendToNumber = scanner.nextLong();
                if(Long.toString(sendToNumber).length()==10){
                    System.out.print("Enter UPI Pin: ");
                    int pin = scanner.nextInt();
                    if(pin == upiPin){
                        System.out.println("Request sent to "+sendToNumber);
                        break;
                    }else{
                        System.out.println("Invalid PIN");
                    }
                }else{
                    System.out.println("Invalid Number");
                }
            }
            else if(opt==3){
                return;
            }
            else{
                System.out.println("Invalid Input");
            }
        }

    }
    void checkBalance() throws InputMismatchException{

            System.out.print("Enter UPI Pin: ");
            int pin = scanner.nextInt();
            if (pin == upiPin) {
                System.out.print("Total Balance: ");
                System.out.println(getBalance());

            } else {
                throw new InvalidPin();
            }
    }
    void myProfile() throws InputMismatchException{
        while(true) {
            System.out.print("1. Change Bank Account\n2. Change Language\n3. My Details\n4. UPI ID");
            System.out.print("\n5. Manage Beneficiary\n6. I am a Merchant\n7. De Register\n8. Back\nEnter here: ");
//          int opt = scanner.nextInt();
            String input = scanner.nextLine();
            int opt = Integer.parseInt(input);
            switch (opt) {
                case 1:
                    System.out.println("Choose bank:\n1. State bank of India\n2. Post Payments bank of India");
                    System.out.print("Enter here: ");
                    int bname = scanner.nextInt();
                    if(bname == 1){
                        bankName = "State Bank of India";
                        System.out.println("Bank Changed to " + bankName);
                        break;
                    }
                    else if(bname==2){
                        bankName = "Post Payments Bank of India";
                        System.out.println("Bank Changed to " + bankName);
                        break;
                    }
                    else{
                        System.out.println("Bank not found!");
                    }
                case 8:
                    return ;
                default:
                    System.out.println("Currently not available this feature");
                    break;

            }
        }


    }
    void pendingRequest(){
        System.out.println("Sorry for inconvenience. We are still working on this");

    }
    void transactions(){
        System.out.println("Sorry for inconvenience. We are still working on this");


    }
    void upiPin(){
        System.out.println("Sorry for inconvenience. We are still working on this");

    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Main obj = new Main();
//        System.out.println("Welcome...!");
        obj.pinVerify();
        while(true) {

                System.out.println("Select Option: " + getBankName());
                System.out.println("1. Send Money\n2. Request Money\n3. Check Balance");
                System.out.println("4. My Profile\n5. Pending Request\n6. Transactions");
                System.out.println("7. UPI PIN\n8. Exit");
                System.out.print("Enter here: ");
                try{
//                  int opt = scanner.nextInt();
                    String input = scanner.nextLine();
                    int opt = Integer.parseInt(input);


                    switch (opt) {
                        case 1:
                            obj.sendMoney();
                            break;
                        case 2:
                            obj.requestMoney();
                            break;
                        case 3:
                            obj.checkBalance();
                            break;
                        case 4:
                            obj.myProfile();
                            break;
                        case 5:
                            obj.pendingRequest();
                            break;
                        case 6:
                            obj.transactions();
                            break;
                        case 7:
                            obj.upiPin();
                            break;
                        case 8:
                            System.out.println("Thank you for using our UPI Service");
                            return;
                        default:
                            System.out.println("Invalid Input");
                            break;
                    }
                } catch (InsufficientBalanceException e) {
                    System.out.println("Insufficient Balance..!!");
                } catch (LessThanZeroException e) {
                    System.out.println("We can't send zero or less than zero Amount.");
                } catch (InputMismatchException e) {
                    System.out.println("Invalid Input...! Please Enter a valid number.");
                    scanner.nextLine();
                } catch (Exception e) {
                    System.out.println("Enter a valid number.");
                }
        }
    }
}
