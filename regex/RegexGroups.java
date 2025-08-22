
package regex;

import java.util.regex.*;

public class RegexGroups {
    public static void main(String[] args) {

        String nomeInvertido = "TAVARES, Kaue";
        String dataPt = "19/08/2025";
        String repetidas = "ola ola mundo mundo ola";

        // ---------------- CAPTURA E REORDENAÇÃO (SOBRENOME, Nome -> Nome SOBRENOME) ----------------
        System.out.println("--- GRUPOS: REORDENAÇÃO ---");
        String nomeNormal = nomeInvertido.replaceAll("^(\\p{Lu}+[\\p{Lu}\\p{Pd}]*),\\s+(\\p{L}+[\\p{L}\\p{Pd}]*)$", "$2 $1");
        System.out.println(nomeNormal); // Kaue SILVA
        System.out.println();

        // ---------------- CAPTURA DE DATA (dd/mm/yyyy) COM GROUPS POSICIONAIS ----------------
        System.out.println("--- GRUPOS: DATA (POSICIONAIS) ---");
        Pattern pData = Pattern.compile("^(\\d{2})/(\\d{2})/(\\d{4})$");
        Matcher mData = pData.matcher(dataPt);
        if (mData.matches()) {
            String dia = mData.group(1);
            String mes = mData.group(2);
            String ano = mData.group(3);
            System.out.println("dia=" + dia + " mes=" + mes + " ano=" + ano);
            System.out.println(dataPt.replaceAll("(\\d{2})/(\\d{2})/(\\d{4})", "$3-$2-$1"));
        }
        System.out.println();

        // ---------------- GRUPOS NOMEADOS (Java) ----------------
        System.out.println("--- GRUPOS: NOMEADOS ---");
        Pattern pNomeado = Pattern.compile("^(?<dia>\\d{2})/(?<mes>\\d{2})/(?<ano>\\d{4})$");
        Matcher mNomeado = pNomeado.matcher(dataPt);
        if (mNomeado.matches()) {
            System.out.println("dia=" + mNomeado.group("dia")
                    + " mes=" + mNomeado.group("mes")
                    + " ano=" + mNomeado.group("ano"));
        }
        System.out.println();

        // ---------------- BACKREFERENCE: PALAVRAS REPETIDAS ----------------
        System.out.println("--- BACKREFERENCE: REPETIDAS ---");
        // \b(\w+)\s+\1\b  -> encontra palavra seguida da MESMA palavra
        Pattern pDup = Pattern.compile("\\b(\\w+)\\s+\\1\\b");
        Matcher mDup = pDup.matcher(repetidas);
        while (mDup.find()) {
            System.out.println("repetida: " + mDup.group()); // ex.: "ola ola", "mundo mundo"
        }
        // remover duplicatas adjacentes mantendo 1 ocorrência
        String semAdjDuplicata = repetidas.replaceAll("\\b(\\w+)\\s+\\1\\b", "$1");
        System.out.println("sem duplicatas adjacentes: " + semAdjDuplicata);
        System.out.println();

        // ---------------- ALTERNÂNCIA E GRUPOS OPCIONAIS ----------------
        System.out.println("--- ALTERNÂNCIA / OPCIONAL ---");
        String tituloNome = "Dr. Ana\nMs Julia\nMr Carlos\nAna";
        Pattern pTitulo = Pattern.compile("(?m)^(?:(Mr|Ms|Dr)\\.?\\s+)?(\\w+)$");
        Matcher mTitulo = pTitulo.matcher(tituloNome);
        while (mTitulo.find()) {
            String titulo = mTitulo.group(1); // pode ser null
            String nome = mTitulo.group(2);
            System.out.println("titulo=" + (titulo == null ? "-" : titulo) + " | nome=" + nome);
        }
        System.out.println();

        // ---------------- REFERÊNCIA A GRUPO NA SUBSTITUIÇÃO ----------------
        System.out.println("--- $1, $2 NA SUBSTITUIÇÃO ---");
        String caminho = "/home/kaue/docs/arquivo.txt";
        String soArquivo = caminho.replaceAll("^.*/([^/]+)$", "$1");
        System.out.println(soArquivo); // arquivo.txt
    }
}
