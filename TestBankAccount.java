package session8;

class BankAccount {

    private volatile int balance;

    public BankAccount(int b){
        balance = b;
    }

    public BankAccount(){
        balance = 0;
    }


    synchronized public int getBalance(){
        return balance;
    }

    synchronized public int withdraw(int w)
    {
        int b = getBalance();
        if(w <= b){
            balance = balance-w;
            return w;
        }
        else
            return 0;
    }
}

class WithdrawAccount implements Runnable{

    private BankAccount acc;
    private int amount;

    public WithdrawAccount(){
        acc = null;
        amount = 0;
    }

    public WithdrawAccount(BankAccount acc,int amount){
        this.acc = acc;
        this.amount = amount;
    }

    public void run() {
        int w; 

        for(int i =0; i<20; i++){
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            System.out.println("Balance before "+Thread.currentThread().getName()+" withdrawl: "+acc.getBalance());
            w = acc.withdraw(amount);
            System.out.println("Balance after "+Thread.currentThread().getName()+" withdrawl: "+acc.getBalance());
            //System.out.println("amount with drawn by: "+Thread.currentThread().getName()+" "+w);

        }

    }

}

public class TestBankAccount{

    public static void main(String[] args) {

        BankAccount b = new BankAccount(1000);
        WithdrawAccount w = new WithdrawAccount(b,100);
        Thread wt1 = new Thread(w);
        wt1.setName("T1");

        Thread wt2 = new Thread(w);
        wt2.setName("T2");

        wt1.start();
        wt2.start();
    }
}





Sample Output:
sh-4.3$ javac TestBankAccount.java                                                                                                                                              
sh-4.3$ java TestBankAccount                                                                                                                                                    
Balance before T2 withdrawl: 1000                                                                                                                                               
Balance after T2 withdrawl: 900                                                                                                                                                 
Balance before T1 withdrawl: 1000                                                                                                                                               
Balance after T1 withdrawl: 800                                                                                                                                                 
Balance before T2 withdrawl: 800                                                                                                                                                
Balance after T2 withdrawl: 700                                                                                                                                                 
Balance before T1 withdrawl: 700                                                                                                                                                
Balance after T1 withdrawl: 600                                                                                                                                                 
Balance before T2 withdrawl: 600                                                                                                                                                
Balance after T2 withdrawl: 500    
Balance after T1 withdrawl: 400                                                                                                                                                 
Balance before T2 withdrawl: 400                                                                                                                                                
Balance after T2 withdrawl: 300 
Balance after T1 withdrawl: 200                                                                                                                                                 
Balance before T2 withdrawl: 200                                                                                                                                                
Balance after T2 withdrawl: 100                                                                                                                                                 
Balance before T1 withdrawl: 100                                                                                                                                                
Balance after T1 withdrawl: 0                                                                                                                                                   
Balance before T2 withdrawl: 0                                                                                                                                                  
Balance after T2 withdrawl: 0                                                                                                                                                   
Balance before T1 withdrawl: 0                                                                                                                                                  
Balance after T1 withdrawl: 0                                                                                                                                                   
Balance before T2 withdrawl: 0                                                                                                                                                  
Balance after T2 withdrawl: 0                                                                                                                                                   
Balance before T1 withdrawl: 0 
