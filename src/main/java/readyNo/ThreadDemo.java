package readyNo;

public class ThreadDemo implements Runnable {
    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
    System.out.println("here !!!");
    }
}
class Test{
    public static void main(String[] args) throws InterruptedException {
        ThreadDemo threadDemo=new ThreadDemo();
        Thread thread=new Thread(threadDemo);
        thread.start();

        ThreadDemoTwo threadDemoTwo=new ThreadDemoTwo();
        //threadDemoTwo.start();
        //threadDemoTwo.start(); метод старт вызыв только 1 раз

        Thread t    =new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("here!");
            }
        });
        t.start();
        threadDemoTwo.start();
        t.join();

    }
}

class ThreadDemoTwo extends Thread{

    @Override
    public void run() {
        System.out.println("I am here");
    }
}
