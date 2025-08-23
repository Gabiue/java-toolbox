package dates;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Locale;

public class DateTimeFormatting {

    public static void main(String[] args) {
        LocalDateTime agora = LocalDateTime.of(2024, 8, 23, 14, 30, 45);
        LocalDate data = agora.toLocalDate();
        LocalTime hora = agora.toLocalTime();

        System.out.println("=== FORMATADORES PADRÃO ===");

        // Formatadores ISO (padrão)
        System.out.println("ISO_LOCAL_DATE: " + data.format(DateTimeFormatter.ISO_LOCAL_DATE));         // 2024-08-23
        System.out.println("ISO_LOCAL_TIME: " + hora.format(DateTimeFormatter.ISO_LOCAL_TIME));         // 14:30:45
        System.out.println("ISO_LOCAL_DATE_TIME: " + agora.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)); // 2024-08-23T14:30:45

        // Formatadores básicos
        System.out.println("BASIC_ISO_DATE: " + data.format(DateTimeFormatter.BASIC_ISO_DATE));         // 20240823

        System.out.println("\n=== FORMATADORES CUSTOMIZADOS ===");

        // Formatos brasileiros
        DateTimeFormatter formatoBR = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter dataHoraBR = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        DateTimeFormatter completo = DateTimeFormatter.ofPattern("dd/MM/yyyy 'às' HH:mm:ss");

        System.out.println("Data BR: " + data.format(formatoBR));           // 23/08/2024
        System.out.println("Data/Hora BR: " + agora.format(dataHoraBR));    // 23/08/2024 14:30
        System.out.println("Completo: " + agora.format(completo));          // 23/08/2024 às 14:30:45

        // Formatos americanos
        DateTimeFormatter formatoUS = DateTimeFormatter.ofPattern("MM-dd-yyyy");
        DateTimeFormatter horaUS = DateTimeFormatter.ofPattern("hh:mm a"); // 12h com AM/PM

        System.out.println("Data US: " + data.format(formatoUS));           // 08-23-2024
        System.out.println("Hora 12h: " + hora.format(horaUS));            // 02:30 PM

        System.out.println("\n=== FORMATAÇÃO TEXTUAL ===");

        // Com nomes por extenso
        DateTimeFormatter nomeCompleto = DateTimeFormatter.ofPattern("EEEE, dd 'de' MMMM 'de' yyyy");
        DateTimeFormatter nomeResumido = DateTimeFormatter.ofPattern("EEE, dd/MMM/yy");

        System.out.println("Nome completo: " + data.format(nomeCompleto));  // sexta-feira, 23 de agosto de 2024
        System.out.println("Nome resumido: " + data.format(nomeResumido));  // sex., 23/ago/24

        // Diferentes idiomas
        DateTimeFormatter ingles = DateTimeFormatter.ofPattern("EEEE, MMMM dd, yyyy", Locale.ENGLISH);
        DateTimeFormatter espanhol = DateTimeFormatter.ofPattern("EEEE, dd 'de' MMMM 'de' yyyy", Locale.forLanguageTag("es"));

        System.out.println("Inglês: " + data.format(ingles));              // Friday, August 23, 2024
        System.out.println("Espanhol: " + data.format(espanhol));          // viernes, 23 de agosto de 2024

        System.out.println("\n=== PARSING (STRING → DATE) ===");

        // Parse com diferentes formatos
        String dataBR = "25/12/2024";
        String dataUS = "12-25-2024";
        String dataTexto = "sexta-feira, 25 de dezembro de 2024";

        LocalDate parseBR = LocalDate.parse(dataBR, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        LocalDate parseUS = LocalDate.parse(dataUS, DateTimeFormatter.ofPattern("MM-dd-yyyy"));
        LocalDate parseTexto = LocalDate.parse(dataTexto, nomeCompleto);

        System.out.println("Parse BR: " + parseBR);          // 2024-12-25
        System.out.println("Parse US: " + parseUS);          // 2024-12-25  
        System.out.println("Parse texto: " + parseTexto);    // 2024-12-25

        System.out.println("\n=== MÉTODOS ÚTEIS ===");

        // Dia da semana e mês por extenso (sem formatador)
        System.out.println("Dia da semana: " + data.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.getDefault())); // sexta-feira
        System.out.println("Mês: " + data.getMonth().getDisplayName(TextStyle.FULL, Locale.getDefault()));              // agosto
        System.out.println("Mês abreviado: " + data.getMonth().getDisplayName(TextStyle.SHORT, Locale.getDefault()));   // ago

        System.out.println("\n=== EXEMPLO REAL: SISTEMA DE NOTIFICAÇÕES ===");

        // Diferentes formatos para diferentes contextos
        LocalDateTime reuniao = LocalDateTime.of(2024, 8, 30, 14, 30);

        // Para SMS (curto)
        String formatoSMS = reuniao.format(DateTimeFormatter.ofPattern("dd/MM HH:mm"));
        System.out.println("SMS: Reunião em " + formatoSMS);  // SMS: Reunião em 30/08 14:30

        // Para email (formal)
        String formatoEmail = reuniao.format(DateTimeFormatter.ofPattern("EEEE, dd 'de' MMMM 'às' HH:mm"));
        System.out.println("Email: " + formatoEmail);  // Email: sexta-feira, 30 de agosto às 14:30

        // Para relatório (completo)
        String formatoRelatorio = reuniao.format(DateTimeFormatter.ofPattern("dd/MM/yyyy - HH:mm:ss"));
        System.out.println("Relatório: " + formatoRelatorio);  // Relatório: 30/08/2024 - 14:30:00

        // Para interface web (amigável)
        String formatoWeb = reuniao.format(DateTimeFormatter.ofPattern("EEE, dd/MMM 'às' HH:mm"));
        System.out.println("Web: " + formatoWeb);  // Web: sex., 30/ago às 14:30

        // Validação de diferentes formatos de entrada
        String[] datasEntrada = {
                "23/08/2024",
                "08-23-2024",
                "2024-08-23",
                "23 ago 2024"
        };

        DateTimeFormatter[] formatadores = {
                DateTimeFormatter.ofPattern("dd/MM/yyyy"),
                DateTimeFormatter.ofPattern("MM-dd-yyyy"),
                DateTimeFormatter.ISO_LOCAL_DATE,
                DateTimeFormatter.ofPattern("dd MMM yyyy")
        };

        System.out.println("\n--- Parsing Flexível ---");
        for (int i = 0; i < datasEntrada.length; i++) {
            try {
                LocalDate parsed = LocalDate.parse(datasEntrada[i], formatadores[i]);
                System.out.println("✅ " + datasEntrada[i] + " → " + parsed);
            } catch (Exception e) {
                System.out.println("❌ Erro ao parsear: " + datasEntrada[i]);
            }
        }
    }
}