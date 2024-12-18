public class Fil extends Thread {
    private String nom; 
    
    
    public Fil(String nom) {
        this.nom = nom;
    }
    
    
    @Override
    public void run() {
        for (int i = 1; i <= 9; i++) {
            System.out.println(nom + " " + i);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Termina el fil " + nom);
    }
}
