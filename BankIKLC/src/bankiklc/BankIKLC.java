/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package bankiklc;

import java.util.Date;
import java.util.Scanner;

/**
 *
 * 
 * @author LENOVO
 */

class Account {
    private String name;
    private String accountNumber;
    private double balance;
    private Date registrationDate;

    public Account(String name, String accountNumber, double balance, Date registrationDate) {
        this.name = name;
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}

public class BankIKLC {
    static final int MAX_ACCOUNTS = 100; // Maksimal jumlah akun yang dapat disimpan
    static Account[] accounts = new Account[MAX_ACCOUNTS];
    static int numAccounts = 0;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int choice;

        do {
            System.out.println("=================================");
            System.out.println("|| SELAMAT DATANG DI BANK IKLC ||");
            System.out.println("=================================");
            System.out.println("||   1. Registrasi Akun        ||");
            System.out.println("||   2. Kirim Akun             ||");
            System.out.println("||   3. Rekening Deposit       ||");
            System.out.println("||   4. Pengecekan Saldo       ||");
            System.out.println("||   5. Penarikan Saldo        ||");
            System.out.println("||   6. TopUp Uang Elektronik  ||");
            System.out.println("||   7. Keluar                 ||");
            System.out.println("=================================");
            System.out.print("Pilihan Anda: ");
            choice = input.nextInt();

            switch (choice) {
                case 1 : {
                    System.out.print("Nama: ");
                    String name = input.next();
                    Date registrationDate = new Date();
                    String accountNumber;
                    do {
                        accountNumber = generateAccountNumber();
                    } while (findAccount(accountNumber) != null);
                    System.out.print("Balance: ");
                    double balance = input.nextDouble();
                    Account account = new Account(name, accountNumber, balance, registrationDate);
                    accounts[numAccounts++] = account;
                            
                    System.out.println("Akun berhasil didaftarkan.");
                    System.out.println("Nomor akun: " + accountNumber);
                    System.out.println("Tanggal pendaftaran: " + registrationDate);
                }
                break;
                

                case 2 : { 
                    System.out.print("Masukkan nomor akun pengirim: ");
                    String senderAccountNumber = input.next();
                    System.out.print("Masukkan nomor akun penerima: ");
                    String receiverAccountNumber = input.next();
                    
                    Account senderAccount = findAccount(senderAccountNumber);
                    Account receiverAccount = findAccount(receiverAccountNumber);
                    
                    if (senderAccount == receiverAccount) {
                        System.out.println("Nomor akun tidak boleh sama");
                        System.out.print("Masukkan nomor akun penerima : ");
                        input.next();
                    }

                    if (senderAccount == null) {
                        System.out.println("Nomor akun pengirim tidak ditemukan.");
                        System.out.print("Masukkan nomor akun pengirim yang sesuai : ");
                        input.next();
                    }
                    if (receiverAccount == null) {
                        System.out.println("Nomor akun penerima tidak ditemukan.");
                        System.out.print("Masukkan nomor akun penerima yang sesuai : ");
                        input.next();
                    }
                    
                    System.out.print("Masukkan jumlah uang yang ingin dikirim: ");
                    double amount = input.nextDouble();
                    
                    if (senderAccount.getBalance() < amount) {
                        System.out.println("Saldo tidak mencukupi.");
                        System.out.print("Masukkan jumlah uang yang sesuai : ");
                        amount = input.nextDouble();
                    }
               
                    senderAccount.setBalance(senderAccount.getBalance() - amount);
                    receiverAccount.setBalance(receiverAccount.getBalance() + amount);
                    System.out.println("Transfer berhasil dilakukan.");
                }
                break;

                case 3 : {
                    System.out.print("Masukkan nomor akun: ");
                    String Accountnumber = input.next();
                    Account depositAccount = findAccount(Accountnumber);
                    
                    if (depositAccount == null) {
                        System.out.println("Nomor akun tidak ditemukan.");
                        break;
                    }
                    
                    System.out.print("Masukkan jumlah uang yang ingin disetor: ");
                    double depositAmount;
                    while (!input.hasNextDouble()) {
                        System.out.println("Jumlah uang harus angka.");
                        System.out.print("Masukkan jumlah uang yang ingin disetor: ");
                        input.next();
                    }
                    depositAmount = input.nextDouble();
                    
                    // Cek apakah jumlah uang yang dimasukkan adalah angka positif
                    if (depositAmount <= 0) {
                        System.out.println("Jumlah uang tidak valid.");
                        break;
                    }
                    depositAccount.setBalance(depositAccount.getBalance() + depositAmount);
                    System.out.println("Rekening berhasil di deposit.");
                }
                break;
 
                case 4 : {
                    System.out.print("Masukkan nomor akun: ");
                    String checkAccountNumber = input.next();
                    Account checkAccount = findAccount(checkAccountNumber);
                    if (checkAccount == null) {
                        System.out.println("Nomor akun tidak ditemukan.");
                        break;
                    }
                    System.out.println("Saldo akun " + checkAccountNumber + " adalah " + checkAccount.getBalance());
                }
                break;
                    
                case 5 : {
                    System.out.print("Masukkan nomor akun: ");
                    String withdrawAccountNumber = input.next();
                    Account withdrawAccount = findAccount(withdrawAccountNumber);
                    if (withdrawAccount == null) {
                        System.out.println("Nomor akun tidak ditemukan.");
                        break;
                    }
                    System.out.print("Masukkan jumlah uang yang ingin ditarik: ");
                    double withdrawAmount;
                    while (!input.hasNextDouble()) {
                        System.out.println("Jumlah uang harus angka.");
                        System.out.print("Masukkan jumlah uang yang ingin ditarik: ");
                        input.next();
                    }
                    withdrawAmount = input.nextDouble();
                    
                    // Cek apakah saldo mencukupi untuk melakukan penarikan
                    if (withdrawAccount.getBalance() < withdrawAmount) {
                        System.out.println("Saldo tidak mencukupi.");
                        break;
                    }
                    withdrawAccount.setBalance(withdrawAccount.getBalance() - withdrawAmount);
                    System.out.println("Penarikan berhasil dilakukan.");
                }
                break;
   
                case 6 : {
                    System.out.print("Masukkan nomor akun: ");
                    String topupAccountNumber = input.next();
                    Account topupAccount = findAccount(topupAccountNumber);
                    if (topupAccount == null) {
                        System.out.println("Nomor akun tidak ditemukan.");
                        break;
                    }
                    
                    int pilihan;
                    System.out.println("1. Dana");
                    System.out.println("2. Ovo");
                    System.out.println("3. LinkAja");
                    System.out.println("4. Gopay");
                    System.out.println("5. Shopeepay");
                    System.out.print("Pilihan Anda: ");
                    pilihan = input.nextInt();
                    if (pilihan <= 5){
                    System.out.print("Masukkan nomor handphone : ");
                    String nohp = input.next();
                    System.out.print("Masukkan jumlah uang yang ingin diisi: ");
                    double topupAmount;
                    while (!input.hasNextDouble()) {
                        System.out.println("Jumlah uang harus angka.");
                        System.out.print("Masukkan jumlah uang yang ingin diisi: ");
                        input.next();
                    }
                    topupAmount = input.nextDouble();
                    // Cek apakah jumlah uang yang dimasukkan adalah angka positif
                    if (topupAmount <= 0) {
                        System.out.println("Jumlah uang tidak valid.");
                        break;
                    }
                    topupAccount.setBalance(topupAccount.getBalance() - topupAmount);
                    System.out.println("Isi saldo berhasil dilakukan.");
                    break;
                    } else {
                        System.out.println("Pilihan tidak valid. Silahkan input menu yang tersedia");
                        System.out.print("Pilihan Anda: ");
                        input.next();
                    }
                }
                break;
                    
                case 7 : { 
                    System.out.println("===========================================");
                    System.out.println("Terima kasih telah menggunakan layanan kami");
                    System.out.println("===========================================");
                }   
                break;
                
                default : System.out.println("Pilihan tidak valid.");
            }
            System.out.println();
        } while (choice != 7);
    }
    
    static String generateAccountNumber() {
        int number = (int) (Math.random() * 900000) + 100000; // menghasilkan nomor acak 6 digit
        return String.valueOf(number);
    }

    static Account findAccount(String accountNumber) {
        for (int i = 0; i < numAccounts; i++) {
            if (accounts[i].getAccountNumber().equals(accountNumber)) {
                return accounts[i];
            }
        }
        return null;
    }
}
