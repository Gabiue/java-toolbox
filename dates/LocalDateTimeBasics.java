package dates;

import java.time.*;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeBasics {
    public static void main(String[] args) {
        System.out.println("=== CRIAÇÃO DE DATAS ===");

        // 1. Usando .now()
        LocalDate hoje = LocalDate.now();
        LocalDateTime agora = LocalDateTime.now();
        LocalTime horaAtual = LocalTime.now();

        System.out.println("Hoje: " + hoje);           // 2025-08-23
        System.out.println("Agora: " + agora);         // 2025-08-23T14:30:45.123
        System.out.println("Hora atual: " + horaAtual); // 14:30:45.123

        LocalDate natal2024 = LocalDate.of(2024,12,24);
        LocalDate natalComEnum = LocalDate.of(2024, Month.DECEMBER, 24);

        LocalDateTime reuniao = LocalDateTime.of(2025, 8, 30, 14, 30);
        LocalTime almoco = LocalTime.of(12,0);

        System.out.println("Natal 2024: " + natal2024);
        System.out.println("Reunião: " + reuniao);
        System.out.println("Almoço: " + almoco);
        System.out.println("\n=== CRIAÇÃO A PARTIR DE STRINGS ===");

        // 3. Usando .parse() - converter String em data

        // Formato padrão ISO (mais comum)
        LocalDate dataISO = LocalDate.parse("2024-12-25");
        LocalDateTime dataHoraISO = LocalDateTime.parse("2024-12-25T14:30:00");
        LocalTime horaISO = LocalTime.parse("14:30:00");

        System.out.println("Data ISO: " + dataISO);
        System.out.println("DateTime ISO: " + dataHoraISO);
        System.out.println("Hora ISO: " + horaISO);

        // Formato customizado (brasileiro, americano, etc.)
        LocalDate dataBR = LocalDate.parse("25/12/2024", DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        LocalDate dataUS = LocalDate.parse("12-25-2024", DateTimeFormatter.ofPattern("MM-dd-yyyy"));
        LocalDateTime dataHoraBR = LocalDateTime.parse("25/12/2024 14:30",
                DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));

        System.out.println("Data BR: " + dataBR);
        System.out.println("Data US: " + dataUS);
        System.out.println("DateTime BR: " + dataHoraBR);

        System.out.println("\n=== DIFERENÇAS ENTRE AS CLASSES ===");

        // LocalDate - APENAS DATA (ano, mês, dia)
        LocalDate somenteData = LocalDate.of(2024, 12, 25);
        System.out.println("LocalDate: " + somenteData); // 2024-12-25

        // LocalTime - APENAS HORA (hora, minuto, segundo, nano)
        LocalTime somenteHora = LocalTime.of(14, 30, 45);
        System.out.println("LocalTime: " + somenteHora); // 14:30:45

        // LocalDateTime - DATA + HORA completas
        LocalDateTime dataEHora = LocalDateTime.of(2024, 12, 25, 14, 30, 45);
        System.out.println("LocalDateTime: " + dataEHora); // 2024-12-25T14:30:45

        // Combinando LocalDate + LocalTime = LocalDateTime
        LocalDate data = LocalDate.of(2024, 12, 25);
        LocalTime hora = LocalTime.of(14, 30);
        LocalDateTime combinado = data.atTime(hora);
        // ou: LocalDateTime combinado2 = hora.atDate(data);

        System.out.println("Combinado: " + combinado); // 2024-12-25T14:30:00

        // Separando LocalDateTime
        LocalDateTime completo = LocalDateTime.now();
        LocalDate apenasData = completo.toLocalDate();
        LocalTime apenasHora = completo.toLocalTime();

        System.out.println("Completo: " + completo);
        System.out.println("Só data: " + apenasData);
        System.out.println("Só hora: " + apenasHora);

        System.out.println("\n=== MÉTODOS DAS CLASSES ===");

        LocalDateTime dataHora = LocalDateTime.of(2024, 12, 25, 14, 30, 45);

        // MÉTODOS GET - Extrair informações
        System.out.println("--- GET (extrair) ---");
        System.out.println("Ano: " + data.getYear());           // 2024
        System.out.println("Mês: " + data.getMonthValue());     // 12 (número)
        System.out.println("Mês: " + data.getMonth());          // DECEMBER (enum)
        System.out.println("Dia: " + data.getDayOfMonth());     // 25
        System.out.println("Dia da semana: " + data.getDayOfWeek()); // WEDNESDAY

        System.out.println("Hora: " + hora.getHour());          // 14
        System.out.println("Minuto: " + hora.getMinute());      // 30
        System.out.println("Segundo: " + hora.getSecond());     // 45

        // MÉTODOS PLUS - Somar tempo (retorna nova instância!)
        System.out.println("\n--- PLUS (somar) ---");
        LocalDate dataFutura = data.plusDays(10);          // +10 dias
        LocalDate outroMes = data.plusMonths(2);           // +2 meses
        LocalDate outroAno = data.plusYears(1);            // +1 ano

        LocalTime horaDepois = hora.plusHours(2);          // +2 horas
        LocalTime minutosDepois = hora.plusMinutes(30);    // +30 minutos

        System.out.println("Data original: " + data);           // 2024-12-25
        System.out.println("+ 10 dias: " + dataFutura);        // 2025-01-04
        System.out.println("+ 2 meses: " + outroMes);          // 2025-02-25

        System.out.println("Hora original: " + hora);           // 14:30:45
        System.out.println("+ 2 horas: " + horaDepois);        // 16:30:45
        // MÉTODOS MINUS - Subtrair tempo (retorna nova instância!)
        System.out.println("\n--- MINUS (subtrair) ---");
        LocalDate dataPassada = data.minusDays(5);         // -5 dias
        LocalDate mesPassado = data.minusMonths(1);        // -1 mês
        LocalDate anoPassado = data.minusYears(1);         // -1 ano

        LocalTime horaAntes = hora.minusHours(3);          // -3 horas
        LocalTime minutosAntes = hora.minusMinutes(15);    // -15 minutos

        System.out.println("Data original: " + data);           // 2024-12-25
        System.out.println("- 5 dias: " + dataPassada);        // 2024-12-20
        System.out.println("- 1 mês: " + mesPassado);          // 2024-11-25

        System.out.println("Hora original: " + hora);           // 14:30:45
        System.out.println("- 3 horas: " + horaAntes);         // 11:30:45

        // MÉTODOS WITH - Alterar campos específicos (retorna nova instância!)
        System.out.println("\n--- WITH (alterar) ---");
        LocalDate mesmaDataOutroAno = data.withYear(2025);     // mudar só o ano
        LocalDate mesmaDataOutroMes = data.withMonth(6);       // mudar só o mês
        LocalDate mesmaDataOutroDia = data.withDayOfMonth(15); // mudar só o dia

        LocalTime mesmaHoraOutroMinuto = hora.withMinute(0);   // zerar minutos
        LocalTime mesmaHoraOutraHora = hora.withHour(9);       // mudar só a hora

        System.out.println("Data original: " + data);              // 2024-12-25
        System.out.println("Mesmo dia em 2025: " + mesmaDataOutroAno);  // 2025-12-25
        System.out.println("Mesmo dia em junho: " + mesmaDataOutroMes); // 2024-06-25

        System.out.println("Hora original: " + hora);              // 14:30:45
        System.out.println("14h em ponto: " + mesmaHoraOutroMinuto); // 14:00:45

        System.out.println("\n=== COMPARAÇÕES E VERIFICAÇÕES ===");

        LocalDate anoNovo2025 = LocalDate.of(2025, 1, 1);


        // COMPARAÇÕES - isBefore, isAfter, isEqual
        System.out.println("--- Comparações ---");
        System.out.println("Natal é antes do Ano Novo? " + natal2024.isBefore(anoNovo2025)); // true
        System.out.println("Ano Novo é depois do Natal? " + anoNovo2025.isAfter(natal2024)); // true
        System.out.println("Natal é igual hoje? " + natal2024.isEqual(hoje)); // false (provavelmente)

        // VERIFICAÇÕES ÚTEIS
        System.out.println("\n--- Verificações ---");
        LocalDate data2024 = LocalDate.of(2024, 2, 29);
        System.out.println("2024 é ano bissexto? " + data2024.isLeapYear()); // true

        // Verificar se é fim de semana
        LocalDate sabado = LocalDate.of(2024, 8, 24);
        LocalDate domingo = LocalDate.of(2024, 8, 25);
        LocalDate segunda = LocalDate.of(2024, 8, 26);

        System.out.println("Sábado é fim de semana? " +
                (sabado.getDayOfWeek() == DayOfWeek.SATURDAY || sabado.getDayOfWeek() == DayOfWeek.SUNDAY)); // true
        System.out.println("Segunda é fim de semana? " +
                (segunda.getDayOfWeek() == DayOfWeek.SATURDAY || segunda.getDayOfWeek() == DayOfWeek.SUNDAY)); // false

        // COMPARAÇÃO COM compareTo (como Comparable)
        System.out.println("\n--- compareTo ---");
        int comparacao = natal2024.compareTo(anoNovo2025);
        System.out.println("Natal compareTo Ano Novo: " + comparacao); // negativo (natal é anterior)

        if (comparacao < 0) System.out.println("Natal vem ANTES");
        else if (comparacao > 0) System.out.println("Natal vem DEPOIS");
        else System.out.println("São a MESMA data");

        System.out.println("\n=== EXEMPLO REAL: SISTEMA DE AGENDAMENTOS ===");

        // Cenário: Sistema de uma clínica médica
        System.out.println("Sistema iniciado em: " + agora.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));

        // 1. CRIANDO CONSULTAS
        LocalDateTime consulta1 = LocalDateTime.of(2024, 8, 30, 9, 0);    // 30/08 às 9h
        LocalDateTime consulta2 = LocalDateTime.of(2024, 8, 30, 14, 30);  // 30/08 às 14:30
        LocalDateTime consulta3 = LocalDateTime.of(2024, 9, 2, 10, 15);   // 02/09 às 10:15

        // 2. VERIFICAÇÕES PRÁTICAS
        System.out.println("\n--- Verificações do Sistema ---");

        // Consulta já passou?
        boolean consulta1Passou = consulta1.isBefore(agora);
        System.out.println("Consulta 1 já passou? " + consulta1Passou);

        // Quantos dias faltam para a consulta?
        if (consulta3.isAfter(agora)) {
            long diasRestantes = agora.toLocalDate().until(consulta3.toLocalDate()).getDays();
            System.out.println("Faltam " + diasRestantes + " dias para a consulta 3");
        }

        // 3. OPERAÇÕES DO DIA A DIA
        System.out.println("\n--- Operações Práticas ---");

        // Remarcar consulta para 1 semana depois
        LocalDateTime consultaRemarcada = consulta1.plusWeeks(1);
        System.out.println("Consulta 1 remarcada para: " +
                consultaRemarcada.format(DateTimeFormatter.ofPattern("dd/MM/yyyy 'às' HH:mm")));

        // Horário de funcionamento (9h às 18h)
        LocalTime abertura = LocalTime.of(9, 0);
        LocalTime fechamento = LocalTime.of(18, 0);
        LocalTime horarioConsulta = consulta2.toLocalTime();

        boolean dentroHorario = horarioConsulta.isAfter(abertura) && horarioConsulta.isBefore(fechamento);
        System.out.println("Consulta 2 está no horário de funcionamento? " + dentroHorario);

        // 4. RELATÓRIO SEMANAL
        System.out.println("\n--- Relatório da Semana ---");
        LocalDate inicioSemana = LocalDate.of(2024, 8, 26); // Segunda
        LocalDate fimSemana = inicioSemana.plusDays(6);     // Domingo

        System.out.println("Período: " +
                inicioSemana.format(DateTimeFormatter.ofPattern("dd/MM")) + " a " +
                fimSemana.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));

        // Consultas da semana
        boolean consulta1NaSemana = !consulta1.toLocalDate().isBefore(inicioSemana) &&
                !consulta1.toLocalDate().isAfter(fimSemana);

        System.out.println("Consulta 1 está na semana? " + consulta1NaSemana);
    }
}


