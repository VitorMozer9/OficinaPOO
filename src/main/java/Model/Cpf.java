package Model;

/**
 * Representa um CPF e fornece um método para validação do código e um método para pseudoanonimização de cpf.
 */
public class Cpf {
    private String numeroCPF;
    
/**
* Construtor da classe {@code Cpf}.
* Inicializa o CPF com código especificado.
* 
* @param numeroCPF código do CPF no formato XXX.XXX.XXX-XX
*/    
    public Cpf(String numeroCPF){
        this.numeroCPF = numeroCPF;
    }
    
/**
 * Pega o cpf da pessoa.
 * 
 * @return numeroCPF pessoa.
 */     
    public String getNumeroCpf(){
        return numeroCPF;
    }
    
/**
 * Determina o número de Cpf da pessoa.
 * 
 * @param numeroCPF Telefone novo da pessoa.
 */  
    public void setNumeroCpf(String numeroCPF){
        this.numeroCPF = numeroCPF;
    }
    
/**
* Verifica se um códifo ce CPF é válido.
* Utiliza o cálculo de dígitos verificadores para validar o CPF.
* 
* @param codigoCpf Código do CPF a ser validado.
* @return true se o cpf for válido e false caso seja inválido
*/
    public static boolean validaCPF(String codigoCpf){
        int soma, resto, primeiroDigito, segundoDigito;       
        codigoCpf = codigoCpf.replaceAll("[^\\d]", ""); 
        
        if (codigoCpf == null || codigoCpf.length() != 11){
            return false;
        }
        
        soma = 0;
        for (int i = 0; i < 9; i++){
            soma = soma + Character.getNumericValue(codigoCpf.charAt(i)) * (10 - i);
        }
        
        resto = 11 - (soma % 11);
        primeiroDigito = (resto >= 10) ? 0 : resto;
        
        if (primeiroDigito != Character.getNumericValue(codigoCpf.charAt(9))) { 
            return false;
        }
        
        soma = 0;
        for(int i = 0; i < 10; i++){
            soma = soma + Character.getNumericValue(codigoCpf.charAt(i)) * (11 - i);    
        }
        
        resto = 11 - (soma % 11);
        segundoDigito = (resto >= 10) ? 0 : resto;
        
        if (segundoDigito != Character.getNumericValue(codigoCpf.charAt(10))) { 
            return false;
        }
        
        return true;
        
    }
    
/**
* Retorna o CPF pseudo-anonimizado no formato "***.XXX.XXX-**". 
* 
* Por meio do Override no método toString de Object. 
* @return CPF com os primeiros e últimos dígitos anonimizados.
*/       
    @Override
    public String toString(){
        
        String[] codigoSeparado = numeroCPF.split("[-,.]");
        
        return "***." + codigoSeparado[1] + "." + codigoSeparado[2] + "-**";
    }
    
}
