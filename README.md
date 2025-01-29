# MiniProjetosJava

# GeradorDeSenhas - Documentação Completa

## 📌 Visão Geral
Um programa Java que gera senhas seguras, criptografa arquivos com AES-128 e permite descriptografá-los posteriormente. Ideal para aprender sobre criptografia e gerenciamento de senhas.

---

## 🛠 Como Executar
### Pré-requisitos:
- **Java JDK 8+** ([Download aqui](https://www.oracle.com/java/technologies/javase-jdk8-downloads.html))
- Terminal/CMD/PowerShell

### Passos:
1. Salve o código em um arquivo chamado `GeradorDeSenhas.java`.
2. **Compile:**
      javac GeradorDeSenhas.java

3. Execute
 java GeradorDeSenhas

🎮 Funcionalidades Explicadas
1. Geração de Senhas
Personalização:

Tamanho da senha (ex: 12 caracteres).

Inclusão de caracteres especiais, números e letras maiúsculas.

Exemplo de Uso:


=== GERADOR DE SENHAS ===
Digite o tamanho da senha: 14
Incluir caracteres especiais? (s/n): s
Incluir números? (s/n): s
Incluir letras maiúsculas? (s/n): s

Senha gerada para o usuário: T7m$kL9@qZ!vR2
2. Criptografia de Arquivos
Arquivos Gerados:

senha_XXXX.txt: Senha do usuário em texto claro.

senha_XXXX_criptografado.txt: Versão criptografada (AES).

Processo Automático:

O arquivo original é excluído após a criptografia.

Nome do arquivo inclui um sufixo aleatório (ex: senha_aB3dEf7G_criptografado.txt).

3. Descriptografia de Arquivos
Passo a Passo:

Selecione o arquivo criptografado na lista.

Digite a senha correta.

A senha original é exibida se a descriptografia for bem-sucedida.

🔧 Estrutura do Código
Classes e Métodos Principais
Método	Função
gerarSenha()	Cria senhas com base nos critérios do usuário
criptografarArquivo()	Codifica arquivos usando AES-128
descriptografarArquivo()	Decodifica arquivos com a senha correta
gerarChaveAES()	Deriva uma chave AES de 16 bytes a partir da senha

   📂 Exemplo de Saída do Programa

=== GERADOR DE SENHAS ===
Digite o tamanho da senha: 10
Incluir caracteres especiais? (s/n): s
Incluir números? (s/n): s
Incluir letras maiúsculas? (s/n): s

Senha gerada para o usuário: pA$5jK9!mL
Senha gerada para criptografar o arquivo: rT8#vF3qZxW!eD2c
Arquivo gerado: senha_xYz12AbC.txt

Arquivo criptografado salvo em: C:\projeto\senha_xYz12AbC_criptografado.txt
Senha para descriptografar o arquivo: rT8#vF3qZxW!eD2c

Deseja descriptografar um arquivo agora? (s/n): s

Arquivos criptografados disponíveis:
1. senha_xYz12AbC_criptografado.txt

Digite o número do arquivo que deseja descriptografar: 1
Digite a senha para descriptografar o arquivo: rT8#vF3qZxW!eD2c

Senha descriptografada: pA$5jK9!mL

MIT License

Copyright (c) 2025 [Liriel]

Permissão é concedida, gratuitamente, para qualquer pessoa obter uma cópia deste software 

🚀 Roadmap (Melhorias Futuras)
Adicionar interface gráfica com JavaFX.

Implementar verificação de força da senha.

Suportar múltiplos algoritmos de criptografia (ex: ChaCha20).

Adicionar autenticação de dois fatores para descriptografia.
  
  
   
  

   
