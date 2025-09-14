package br.senac.sp.appleacademyapi.student;

public enum Program {
    
    TADS("Téc. Análise e Desenvolvimento de Sistemas"),
    BSI("Bel. em Sistemas de Informação"),
    BCC("Bel. em Ciência da Computação"),
    BGD("Bel. em Design Gráfico");

    Program(String description) {
        this.description = description;
    }

    private String description;

    public String getDescription() {
        return description;
    }
}
