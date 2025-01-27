import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.util.Base64;
import java.util.Scanner;
import java.util.Random;

public class GeradorDeSenhas {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        
        System.out.println("=== GERADOR DE SENHAS ===");
        System.out.print("Digite o tamanho da senha: ");
        int tamanhoSenhaUsuario = scanner.nextInt();
        System.out.print("Incluir caracteres especiais? (s/n): ");
        boolean incluirEspeciais = scanner.next().equalsIgnoreCase("s");
        System.out.print("Incluir números? (s/n): ");
        boolean incluirNumeros = scanner.next().equalsIgnoreCase("s");
        System.out.print("Incluir letras maiúsculas? (s/n): ");
        boolean incluirMaiusculas = scanner.next().equalsIgnoreCase("s");


        String senhaUsuario = gerarSenha(tamanhoSenhaUsuario, incluirEspeciais, incluirNumeros, incluirMaiusculas, random);
        System.out.println("\nSenha gerada para o usuário: " + senhaUsuario);


        String senhaCriptografia = gerarSenha(16, true, true, true, random); // Senha de 16 caracteres
        System.out.println("Senha gerada para criptografar o arquivo: " + senhaCriptografia);


        String nomeArquivo = "senha_" + gerarNomeAleatorio(8) + ".txt";
        System.out.println("Arquivo gerado: " + nomeArquivo);


        salvarArquivo(nomeArquivo, senhaUsuario);


        String nomeArquivoCriptografado = nomeArquivo.replace(".txt", "_criptografado.txt");
        criptografarArquivo(nomeArquivo, nomeArquivoCriptografado, senhaCriptografia);


        String caminhoArquivo = new File(nomeArquivoCriptografado).getAbsolutePath();
        System.out.println("\nArquivo criptografado salvo em: " + caminhoArquivo);
        System.out.println("Senha para descriptografar o arquivo: " + senhaCriptografia);


        System.out.print("\nDeseja descriptografar um arquivo agora? (s/n): ");
        if (scanner.next().equalsIgnoreCase("s")) {

            File pastaAtual = new File(".");
            File[] arquivosCriptografados = pastaAtual.listFiles((dir, nome) -> nome.endsWith("_criptografado.txt"));

            if (arquivosCriptografados == null || arquivosCriptografados.length == 0) {
                System.out.println("Nenhum arquivo criptografado encontrado.");
            } else {
                System.out.println("\nArquivos criptografados disponíveis:");
                for (int i = 0; i < arquivosCriptografados.length; i++) {
                    System.out.println((i + 1) + ". " + arquivosCriptografados[i].getName());
                }


                System.out.print("Digite o número do arquivo que deseja descriptografar: ");
                int escolha = scanner.nextInt();

                if (escolha > 0 && escolha <= arquivosCriptografados.length) {
                    File arquivoSelecionado = arquivosCriptografados[escolha - 1];


                    System.out.print("Digite a senha para descriptografar o arquivo: ");
                    String senhaDigitada = scanner.next();


                    String senhaDescriptografada = descriptografarArquivo(arquivoSelecionado.getPath(), senhaDigitada);
                    if (senhaDescriptografada != null) {
                        System.out.println("Senha descriptografada: " + senhaDescriptografada);
                    } else {
                        System.out.println("Senha incorreta ou erro ao descriptografar o arquivo.");
                    }
                } else {
                    System.out.println("Escolha inválida.");
                }
            }
        }

        scanner.close();
    }


    public static String gerarSenha(int tamanho, boolean incluirEspeciais, boolean incluirNumeros, boolean incluirMaiusculas, Random random) {
        String caracteres = "abcdefghijklmnopqrstuvwxyz";
        if (incluirMaiusculas) caracteres += "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        if (incluirNumeros) caracteres += "0123456789";
        if (incluirEspeciais) caracteres += "!@#$%^&*()_+-=[]{}|;:,.<>?";

        StringBuilder senha = new StringBuilder();

        for (int i = 0; i < tamanho; i++) {
            int indice = random.nextInt(caracteres.length());
            senha.append(caracteres.charAt(indice));
        }

        return senha.toString();
    }


    public static String gerarNomeAleatorio(int tamanho) {
        String caracteres = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuilder nome = new StringBuilder();

        for (int i = 0; i < tamanho; i++) {
            int indice = random.nextInt(caracteres.length());
            nome.append(caracteres.charAt(indice));
        }

        return nome.toString();
    }


    public static void salvarArquivo(String nomeArquivo, String conteudo) {
        try (FileWriter writer = new FileWriter(nomeArquivo)) {
            writer.write(conteudo);
        } catch (Exception e) {
            System.out.println("Erro ao salvar o arquivo.");
            e.printStackTrace();
        }
    }

    public static void criptografarArquivo(String nomeArquivo, String nomeArquivoCriptografado, String senhaCriptografia) {
        try {

            String conteudo = new String(Files.readAllBytes(new File(nomeArquivo).toPath()));


            SecretKeySpec chaveAES = gerarChaveAES(senhaCriptografia);


            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, chaveAES);
            byte[] conteudoCriptografado = cipher.doFinal(conteudo.getBytes());


            Files.write(new File(nomeArquivoCriptografado).toPath(), conteudoCriptografado);


            new File(nomeArquivo).delete();
        } catch (Exception e) {
            System.out.println("Erro ao criptografar o arquivo.");
            e.printStackTrace();
        }
    }


    public static String descriptografarArquivo(String caminhoArquivoCriptografado, String senhaCriptografia) {
        try {

            byte[] conteudoCriptografado = Files.readAllBytes(new File(caminhoArquivoCriptografado).toPath());


            SecretKeySpec chaveAES = gerarChaveAES(senhaCriptografia);


            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, chaveAES);
            byte[] conteudoDescriptografado = cipher.doFinal(conteudoCriptografado);

            return new String(conteudoDescriptografado);
        } catch (Exception e) {
            System.out.println("Erro ao descriptografar o arquivo.");
            e.printStackTrace();
            return null;
        }
    }


    public static SecretKeySpec gerarChaveAES(String senha) {
        try {
            byte[] chaveBytes = senha.getBytes();
            byte[] chaveAES = new byte[16];
            System.arraycopy(chaveBytes, 0, chaveAES, 0, Math.min(chaveBytes.length, 16));
            return new SecretKeySpec(chaveAES, "AES");
        } catch (Exception e) {
            System.out.println("Erro ao gerar a chave AES.");
            e.printStackTrace();
            return null;
        }
    }
}
