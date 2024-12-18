public class Principal {
    public static void main(String[] args) {
        
        Fil juan = new Fil("Juan");
        Fil pepe = new Fil("Pepe");
        
        
        juan.start();
        pepe.start();
        
        
        try {
            juan.join();
            pepe.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        System.out.println("Termina thread main");
    }
}
