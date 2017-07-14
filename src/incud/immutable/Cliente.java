package incud.immutable;

public final class Cliente {  
	
    public final String codiceFiscale;
     
    public final String nome;
     
    public final String cognome;
     
    public final String nickname;
     
    public final String password;
     
    public final String citta;
     
    public final String telefono;
     
    public final String cellulare;
	
    public static Builder newBuilder() {
        return new Builder();
    }

    private Cliente(Builder builder) {
        codiceFiscale = builder.codiceFiscale;
        nome = builder.nome;
        cognome = builder.cognome;
        nickname = builder.nickname;
        password = builder.password;
        citta = builder.citta;
        telefono = builder.telefono;
        cellulare = builder.cellulare;
    }
    
    public static class Builder {

        private Builder() { }
                
        private String codiceFiscale;
         
        private String nome;
         
        private String cognome;
         
        private String nickname;
         
        private String password;
         
        private String citta;
         
        private String telefono;
         
        private String cellulare;
        
        public Builder addCodiceFiscale(String cf) {
            codiceFiscale = cf;
            return this;
        }
         
        public Builder addNome(String name) {
            nome = name;
            return this;
        }
         
        public Builder addCognome(String surname) {
            cognome = surname;
            return this;
        }
         
        public Builder addNickname(String nick) {
            nickname = nick;
            return this;
        }
         
        public Builder addPassword(String pw) {
           password = pw;
           return this;
        }
         
        public Builder addCitta(String city) {
            citta = city;
            return this;
        }
         
        public Builder addTelefono(String phone) {
        	telefono = phone;
            return this;
        }
         
        public Builder addCellulare(String phone) {
            cellulare = phone;
            return this;
        }
        
        public Cliente build() {
            return new Cliente(this);
        }

    }

}