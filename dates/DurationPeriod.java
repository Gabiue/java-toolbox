package dates;
import java.time.*;
import java.time.format.DateTimeFormatter;

public class DurationPeriod {
    public static void main(String[] args) {
        System.out.println("=== DURATION vs PERIOD ===");

        // DURATION - Para TEMPO preciso (horas, minutos, segundos)
        LocalDateTime inicio = LocalDateTime.of(2024, 8, 23, 14, 30, 0);
        LocalDateTime fim = LocalDateTime.of(2024, 8, 23, 16, 45, 30);

        Duration duracao = Duration.between(inicio, fim);
        System.out.println("Duration (tempo exato): " + duracao); // PT2H15M30S

        // PERIOD - Para DATAS (anos, meses, dias)
        LocalDate nascimento = LocalDate.of(1990, 5, 15);
        LocalDate hoje = LocalDate.of(2024, 8, 23);

        Period periodo = Period.between(nascimento, hoje);
        System.out.println("Period (período): " + periodo); // P34Y3M8D

        // Resumo da diferença:
        System.out.println("\n--- Resumo ---");
        System.out.println("Duration = tempo EXATO (horas/min/seg)");
        System.out.println("Period = tempo CALENDÁRIO (anos/meses/dias)");

        System.out.println("\n=== FORMAS DE CRIAR DURATION E PERIOD ===");

        // DURATION - Diferentes formas de criar
        System.out.println("--- Duration ---");

        // Entre duas datas/horas
        LocalTime inicio9h = LocalTime.of(9, 0);
        LocalTime fim17h = LocalTime.of(17, 0);
        Duration jornadaTrabalho = Duration.between(inicio9h, fim17h);
        System.out.println("Jornada trabalho: " + jornadaTrabalho); // PT8H

        // Criação direta
        Duration duasHoras = Duration.ofHours(2);
        Duration trintaMinutos = Duration.ofMinutes(30);
        Duration quinzeSegundos = Duration.ofSeconds(15);
        Duration umDia = Duration.ofDays(1);

        System.out.println("2 horas: " + duasHoras);           // PT2H
        System.out.println("30 minutos: " + trintaMinutos);    // PT30M
        System.out.println("15 segundos: " + quinzeSegundos); // PT15S
        System.out.println("1 dia: " + umDia);                // PT24H

        // PERIOD - Diferentes formas de criar
        System.out.println("\n--- Period ---");

        // Entre duas datas
        LocalDate formatura = LocalDate.of(2020, 12, 15);
        LocalDate agoraData = LocalDate.now();
        Period tempoFormado = Period.between(formatura, agoraData);
        System.out.println("Tempo de formado: " + tempoFormado);

        // Criação direta
        Period umAno = Period.ofYears(1);
        Period seisMeses = Period.ofMonths(6);
        Period quinteDias = Period.ofDays(15);
        Period completo = Period.of(2, 6, 10); // 2 anos, 6 meses, 10 dias

        System.out.println("1 ano: " + umAno);           // P1Y
        System.out.println("6 meses: " + seisMeses);     // P6M
        System.out.println("15 dias: " + quinteDias);    // P15D
        System.out.println("Completo: " + completo);     // P2Y6M10D

        System.out.println("\n=== MÉTODOS DE DURATION ===");

        Duration filmeLongo = Duration.ofHours(2).plusMinutes(45).plusSeconds(30); // 2h45m30s
        System.out.println("Filme: " + filmeLongo); // PT2H45M30S

        // EXTRAIR informações
        System.out.println("--- Extrair informações ---");
        System.out.println("Total em horas: " + filmeLongo.toHours());       // 2
        System.out.println("Total em minutos: " + filmeLongo.toMinutes());    // 165 (2*60 + 45)
        System.out.println("Total em segundos: " + filmeLongo.getSeconds());  // 9930
        System.out.println("Só os minutos: " + filmeLongo.toMinutesPart());   // 45 (parte dos minutos)
        System.out.println("Só os segundos: " + filmeLongo.toSecondsPart());  // 30 (parte dos segundos)

        // OPERAÇÕES matemáticas
        System.out.println("\n--- Operações ---");
        Duration intervalo = Duration.ofMinutes(15);
        Duration filmeComIntervalo = filmeLongo.plus(intervalo);
        Duration filmeSemCreditos = filmeLongo.minus(Duration.ofMinutes(5));
        Duration filmeX2 = filmeLongo.multipliedBy(2);
        Duration meioFilme = filmeLongo.dividedBy(2);

        System.out.println("Filme original: " + filmeLongo);         // PT2H45M30S
        System.out.println("+ intervalo: " + filmeComIntervalo);     // PT3H30S
        System.out.println("- créditos: " + filmeSemCreditos);       // PT2H40M30S
        System.out.println("x2 velocidade: " + filmeX2);            // PT5H31M
        System.out.println("metade: " + meioFilme);                 // PT1H22M45S

        // COMPARAÇÕES
        System.out.println("\n--- Comparações ---");
        Duration umaHora = Duration.ofHours(1);
        System.out.println("Filme > 1 hora? " + (filmeLongo.compareTo(umaHora) > 0)); // true
        System.out.println("É negativo? " + filmeLongo.isNegative());    // false
        System.out.println("É zero? " + filmeLongo.isZero());           // false

        System.out.println("\n=== MÉTODOS DE PERIOD ===");

        Period idadePessoa = Period.of(25, 7, 12); // 25 anos, 7 meses, 12 dias
        System.out.println("Idade: " + idadePessoa); // P25Y7M12D

        // EXTRAIR informações
        System.out.println("--- Extrair informações ---");
        System.out.println("Anos: " + idadePessoa.getYears());     // 25
        System.out.println("Meses: " + idadePessoa.getMonths());   // 7
        System.out.println("Dias: " + idadePessoa.getDays());      // 12

        // Total em meses/dias (aproximado!)
        System.out.println("Total em meses (aprox): " + idadePessoa.toTotalMonths()); // 25*12 + 7 = 307

        // OPERAÇÕES matemáticas
        System.out.println("\n--- Operações ---");
        Period maisUmAno = idadePessoa.plusYears(1);
        Period menosSeisMeses = idadePessoa.minusMonths(6);
        Period dobroIdade = idadePessoa.multipliedBy(2);

        System.out.println("Idade original: " + idadePessoa);    // P25Y7M12D
        System.out.println("+ 1 ano: " + maisUmAno);           // P26Y7M12D
        System.out.println("- 6 meses: " + menosSeisMeses);    // P25Y1M12D
        System.out.println("x2: " + dobroIdade);               // P50Y14M24D

        // NORMALIZAÇÃO (ajustar valores)
        Period desorganizado = Period.of(1, 15, 35); // 1 ano, 15 meses, 35 dias
        Period normalizado = desorganizado.normalized();

        System.out.println("Desorganizado: " + desorganizado); // P1Y15M35D
        System.out.println("Normalizado: " + normalizado);     // P2Y3M35D (15 meses = +1 ano +3 meses)

        // VERIFICAÇÕES
        System.out.println("\n--- Verificações ---");
        System.out.println("É negativo? " + idadePessoa.isNegative()); // false
        System.out.println("É zero? " + idadePessoa.isZero());          // false

        Period periodoVazio = Period.ZERO;
        System.out.println("Período zero: " + periodoVazio.isZero());   // true

        System.out.println("\n=== EXEMPLO REAL: SISTEMA DE RH ===");

        // Cenário: Sistema de uma empresa calculando dados dos funcionários
        LocalDate dataAdmissao = LocalDate.of(2020, 3, 15);
        LocalTime entradaManha = LocalTime.of(8, 0);
        LocalTime saidaTarde = LocalTime.of(17, 30);

        System.out.println("=== DADOS DO FUNCIONÁRIO ===");
        System.out.println("Admissão: " + dataAdmissao.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        System.out.println("Hoje: " + hoje.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));

        // 1. TEMPO DE EMPRESA (Period)
        Period tempoEmpresa = Period.between(dataAdmissao, hoje);
        System.out.println("\n--- Tempo de Empresa ---");
        System.out.println("Período completo: " + tempoEmpresa);
        System.out.println(String.format("Tempo de casa: %d anos, %d meses e %d dias",
                tempoEmpresa.getYears(), tempoEmpresa.getMonths(), tempoEmpresa.getDays()));

        long totalMesesEmpresa = tempoEmpresa.toTotalMonths();
        System.out.println("Total em meses: " + totalMesesEmpresa + " meses");

        // 2. CÁLCULO DE FÉRIAS (a cada 12 meses = 30 dias de férias)
        long feriasAcumuladas = (totalMesesEmpresa / 12) * 30;
        System.out.println("Férias acumuladas: " + feriasAcumuladas + " dias");

        // 3. JORNADA DE TRABALHO DIÁRIA (Duration)
        Duration jornadaDiaria = Duration.between(entradaManha, saidaTarde);
        System.out.println("\n--- Jornada de Trabalho ---");
        System.out.println("Horário: " + entradaManha + " às " + saidaTarde);
        System.out.println("Jornada diária: " + jornadaDiaria.toHours() + "h " +
                jornadaDiaria.toMinutesPart() + "min");

        // 4. CÁLCULO SEMANAL E MENSAL
        Duration jornadaSemanal = jornadaDiaria.multipliedBy(5); // 5 dias úteis
        Duration jornadaMensal = jornadaSemanal.multipliedBy(4); // ~4 semanas

        System.out.println("Horas semanais: " + jornadaSemanal.toHours() + "h");
        System.out.println("Horas mensais: " + jornadaMensal.toHours() + "h");

        // 5. PRÓXIMA AVALIAÇÃO (anual)
        LocalDate proximaAvaliacao = dataAdmissao.plusYears((int)tempoEmpresa.getYears() + 1);
        Period tempoParaAvaliacao = Period.between(hoje, proximaAvaliacao);

        System.out.println("\n--- Próxima Avaliação ---");
        System.out.println("Data da próxima avaliação: " +
                proximaAvaliacao.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));

        if (tempoParaAvaliacao.isNegative()) {
            System.out.println("⚠️ Avaliação está atrasada!");
        } else {
            System.out.println("Tempo restante: " + tempoParaAvaliacao.getMonths() +
                    " meses e " + tempoParaAvaliacao.getDays() + " dias");
        }

        // 6. APOSENTADORIA (estimativa - 35 anos de contribuição)
        Period tempoParaAposentadoria = Period.ofYears(35).minus(tempoEmpresa);
        LocalDate dataAposentadoria = hoje.plus(tempoParaAposentadoria);

        System.out.println("\n--- Estimativa Aposentadoria ---");
        System.out.println("Faltam: " + tempoParaAposentadoria.getYears() + " anos, " +
                tempoParaAposentadoria.getMonths() + " meses");
        System.out.println("Data estimada: " +
                dataAposentadoria.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
    }
}

