package comunicacao;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;


public class Conexao{
    private ServerSocket serverSocket;
    private String endereco;
    private int porta;
    
    public static void main(String[] args) {
        
    }
    
    public ServerSocket getSocket(){
        return serverSocket;

    }
    
    private void criarServerSocket(int porta) throws IOException {
        //cria um serverSocket se tiver em só uma placa de rede
        serverSocket = new ServerSocket(porta);
    }
    
    private void criarServerSocket(int endereco, int porta) throws IOException {
        //cria um serverSocket se tiver mais de uma placa de rede
        serverSocket = new ServerSocket(endereco, porta);

    }
    
        private Socket esperandoConexao() throws IOException {
        //Faz o serverSocket esperar uma conexão, só da o retorno quando a conexão não é estabelecida
        Socket socket = serverSocket.accept();
        return socket;
    }
    
    private void conectar() throws IOException{
        System.out.println("O cliente vai rodar na mesma placa de rede? S/N");
        Scanner s = new Scanner(System.in);
        String resposta = s.nextLine();
        
        if(resposta == "s" || resposta == "S"){
            System.out.println("Qual a porta?");
            s = new Scanner(System.in);
            String porta = s.nextLine();   
            int result = Integer.parseInt(resposta);	
            criarServerSocket(result);
            
        }else if(resposta == "n" || resposta == "N"){
            System.out.println("Qual o endereço?");
            s = new Scanner(System.in);
            String sendereco = s.nextLine();   
            int endereco = Integer.parseInt(resposta);	 
            
            System.out.println("Qual a porta?");
            s = new Scanner(System.in);
            String sporta = s.nextLine();   
            int porta = Integer.parseInt(resposta);	
            
            criarServerSocket(endereco, porta);
        }
        
    }
        
    
}
