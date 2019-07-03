package comunicacao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;


public class Conexao{
    private ServerSocket serverSocket;
    
        public void rodar() throws IOException {
            Conexao server = new Conexao();
            server.conectar();
            Socket socket = server.esperandoConexao();

            server.tratarConexao(socket);
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
        System.out.println("conexão estabelecida com o cliente");
        return socket;
    }
    
    private void conectar() throws IOException{
        boolean entrou = false;

        do{
            System.out.println("O cliente vai rodar na mesma placa de rede? S/N");
            System.out.println("Você pode trocar a opção digitando a resposta errada.");
            Scanner s = new Scanner(System.in);
            String resposta = s.nextLine();

            if(resposta.equals("s") || resposta.equals("S")){
                System.out.println("Qual a porta?");
                s = new Scanner(System.in);
                String sporta = s.nextLine();   
                int porta = Integer.parseInt(sporta);

                criarServerSocket(porta);

                entrou = true;

            }else if(resposta.equals("n") || resposta.equals("N")){
                System.out.println("Qual o endereço?");
                s = new Scanner(System.in);
                String sendereco = s.nextLine();   
                int endereco = Integer.parseInt(sendereco);	 

                System.out.println("Qual a porta?");
                s = new Scanner(System.in);
                String sporta = s.nextLine();   
                int porta = Integer.parseInt(sporta);	

                criarServerSocket(endereco, porta);

                entrou = true;
            }else{
                System.out.println("Resposta incorreta");
            }
            
        }while(!entrou);
    }
     
    //criar streams de entrada e saída
    private void tratarConexao(Socket socket){
        
        try{
            BufferedReader in = new BufferedReader( new InputStreamReader(socket.getInputStream(), "UTF8"));
            System.out.println(in);
            //socket.getInputStream();
            socket.getOutputStream();
        }catch(IOException e){
            
        }

    }

}
