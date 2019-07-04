package comunicacao;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ThreadConexao{
    private Conexao c;
    
    
    public ThreadConexao() {
         
        new Thread() {
        @Override
        public void run() {
            try {
                c = new Conexao(); 
                c.rodar();
            } catch (IOException ex) {
                Logger.getLogger(ThreadConexao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        }.start();
        
    }
    
}
