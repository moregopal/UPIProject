package upiPaymentSystem;

import java.util.Scanner;
public class AtmPinVerification {
    int Oripin = Main.upiPin;
    int count =1;
    void pinVerify() throws InvalidInputException {
        Scanner prtk = new Scanner(System.in);

        //int Opin = 3145;
        //int count =1;


        while(true) {
            //System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            if(count==5) {
                System.out.println("This is your last Try..!!");
            }
            System.out.print("Enter Pin: ");
            try {
                int pin = prtk.nextInt();


                if(pin == Oripin) {
                    System.out.println("Welcome..!!!");
                    break;
                }
                else if(count == 5) {
                    try{
                        throw new InvalidPin();
                    }
                    catch(InvalidPin e){
                        System.out.println("Invalid Pin..!! Try After Some Time");
                    }
                    break;
                }
                else {
                    count +=1;
                    try{
                        throw new InvalidPin();
                    }
                    catch(InvalidPin e){
                        System.out.println("Invalid Pin..!! Try Again...");
                    }
                    catch(InvalidInputException e) {
                        System.out.println("Invalid input...! Please enter a valid number.");
                    }
                }
            }
            catch(InvalidInputException e) {
                System.out.println("Invalid input...! Please enter a valid number.");
            }

        }
    }


    public void ChangePin() throws ChangeException {
        Scanner prtk = new Scanner(System.in);
        int Npin;
        int Upin;

        System.out.print("Enter Old Pin: ");
        Upin = prtk.nextInt();
        while(true) {
            if(Upin == Oripin) {
                System.out.print("Enter New Pin : ");
                Npin = prtk.nextInt();
                if(Npin == Oripin) {
                    System.out.println("Don't use Old Pin as New Pin..!!");
                    count+=1;
                }
                else {
                    try{
                        //Oripin = Npin;
                        System.out.print("Confirm Pin: ");
                        int Cpin = prtk.nextInt();
                        if(Cpin == Npin) {
                            System.out.println("Pin Changed!!");
                            Oripin = Cpin;
                            return;
                        }
                        else {
                            System.out.println("Not Match..!! Try again!!");
                        }
                    }
                    catch(ChangeException e){
                        System.out.println("Don't Use Alphanumeric keys..!!");
                    }
                }
            }
            else {
                System.out.println("Wrong Pin..!! Try Again!!");
                break;
            }
        }
    }
}
