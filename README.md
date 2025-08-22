# Java Toolbox

Repositório para **dominar a biblioteca padrão do Java** (java.base e módulos úteis), com exemplos minimalistas, exercícios, testes automatizados e referências.

> Filosofia: *1 classe/utilidade por arquivo + 1 teste por comportamento*, exemplos curtos e focados, sem frameworks externos.

---

## 📁 Estrutura

```
java-toolbox/
├─ pom.xml
├─ README.md
├─ strings/
│  ├─ StringBasics.java ✅
│  ├─ StringSearching.java ✅
│  └─ StringBuilderExamples.java ✅
├─ regex/
│  ├─ RegexBasics.java ✅
│  ├─ RegexGroups.java ✅
│  ├─ RegexReplace.java ✅
│  └─ RegexValidation.java ✅
├─ numbers/
│  ├─ IntegerUtils.java ✅
│  ├─ LongUtils.java ✅
│  ├─ BigDecimalUtils.java ✅
│  └─ MathAndRandom.java ✅
├─ collections/
│  ├─ ListsBasics.java ✅
│  ├─ SetsBasics.java ✅
│  ├─ MapsBasics.java ✅
│  ├─ QueueBasics.java ✅
│  └─ ComparatorsAndRecords.java ✅
├─ streams/
│  ├─ StreamBasics.java ✅
│  ├─ CollectorsRecipes.java ✅
│  └─ OptionalPatterns.java ✅
├─ javapillars/
│  ├─ encapsulation/
│  │  ├─ EncapsulationSyntax.java ✅
│  │  ├─ BankAccountExample.java ✅
│  │  ├─ ProductExample.java ✅
│  │  └─ UserExample.java ✅
│  ├─ inheritance/
│  │  ├─ InheritanceSyntax.java ✅
│  │  ├─ AnimalExample.java ✅
│  │  └─ VehicleExample.java ✅
│  ├─ polymorphism/
│  │  ├─ PolymorphismSyntax.java ✅
│  │  └─ ShapeExample.java ✅
│  └─ abstraction/
│     ├─ AbstractionSyntax.java ✅
│     └─ PaymentExample.java ✅
├─ dates/ ⏳
│  ├─ LocalDateTimeBasics.java
│  ├─ DurationPeriod.java
│  └─ DateTimeFormatting.java
├─ io_nio/ ⏳
│  ├─ FilesAndPaths.java
│  └─ ReadWriteSmallFiles.java
├─ concurrency/ ⏳
│  ├─ ThreadsBasics.java
│  └─ ExecutorsAndFutures.java
├─ net_http/ ⏳
│  └─ HttpClientBasics.java
├─ lang/ ⏳
│  ├─ ObjectsAndRecords.java
│  ├─ PatternMatching.java
│  └─ SwitchExpressions.java
└─ util/ ⏳
   ├─ Preconditions.java
   └─ Tuple.java
```

## 🚀 Status do Projeto

### ✅ Strings (4/4) - COMPLETO
- ✅ StringBasics.java
- ✅ StringSearching.java
- ✅ StringBuilderExamples.java
- ✅ RegexBasics.java

### ✅ Regex (4/4) - COMPLETO
- ✅ RegexBasics.java
- ✅ RegexGroups.java
- ✅ RegexReplace.java
- ✅ RegexValidation.java

### ✅ Numbers (4/4) - COMPLETO
- ✅ IntegerUtils.java
- ✅ LongUtils.java
- ✅ BigDecimalUtils.java
- ✅ MathAndRandom.java

### ✅ Collections (5/5) - COMPLETO
- ✅ ListsBasics.java
- ✅ SetsBasics.java
- ✅ MapsBasics.java
- ✅ QueueBasics.java
- ✅ ComparatorsAndRecords.java

### ✅ Streams (3/3) - COMPLETO
- ✅ StreamBasics.java
- ✅ CollectorsRecipes.java
- ✅ OptionalPatterns.java

### ✅ Java Pillars (10/12) - QUASE COMPLETO!
- **✅ Encapsulation (4/4) - COMPLETO:**
    - ✅ EncapsulationSyntax.java
    - ✅ BankAccountExample.java
    - ✅ ProductExample.java
    - ✅ UserExample.java
- **✅ Inheritance (3/3) - COMPLETO:**
    - ✅ InheritanceSyntax.java
    - ✅ AnimalExample.java
    - ✅ VehicleExample.java
- **✅ Polymorphism (2/2) - COMPLETO:**
    - ✅ PolymorphismSyntax.java
    - ✅ ShapeExample.java
- **✅ Abstraction (2/2) - COMPLETO:**
    - ✅ AbstractionSyntax.java
    - ✅ PaymentExample.java

### ⏳ Dates (0/3)
- ⬜ LocalDateTimeBasics.java
- ⬜ DurationPeriod.java
- ⬜ DateTimeFormatting.java

### ⏳ I/O & NIO (0/2)
- ⬜ FilesAndPaths.java
- ⬜ ReadWriteSmallFiles.java

### ⏳ Concurrency (0/2)
- ⬜ ThreadsBasics.java
- ⬜ ExecutorsAndFutures.java

### ⏳ HTTP Client (0/1)
- ⬜ HttpClientBasics.java

### ⏳ Language Features (0/3)
- ⬜ ObjectsAndRecords.java
- ⬜ PatternMatching.java
- ⬜ SwitchExpressions.java

### ⏳ Utilities (0/2)
- ⬜ Preconditions.java
- ⬜ Tuple.java

### ⏳ Tests (0/10)
- ⬜ StringBasicsTest.java
- ⬜ StringSearchingTest.java
- ⬜ StringBuilderExamplesTest.java
- ⬜ RegexBasicsTest.java
- ⬜ BigDecimalBasicsTest.java
- ⬜ ListsBasicsTest.java
- ⬜ Demais testes...

## 🎯 Foco Atual

**MÓDULO CONCLUÍDO:** ✅ Java Pillars - Todos os 4 pilares implementados!
- 📍 **Próximo:** Finalizar Streams (CollectorsRecipes, OptionalPatterns)
- 📍 **Depois:** Dates, I/O & NIO, Concurrency
- 📍 **Paralelo:** Implementar testes unitários

## 🏆 Marcos Importantes

- 🎉 **JAVA PILLARS FINALIZADO!** Todos os 4 pilares da POO implementados com exemplos práticos
- 🎯 **STREAMS INICIADO** com exemplos reais de e-commerce e análise de dados
- 📚 **DOCUMENTAÇÃO RICA** com comentários detalhados e explicações didáticas
- 🔧 **SINTAXE COMPLETA** para cada conceito com manual de referência

## 📦 Dependências

```xml
<dependencies>
    <!-- Apenas JUnit para testes -->
    <dependency>
        <groupId>org.junit.jupiter</groupId>
        <artifactId>junit-jupiter</artifactId>
        <version>5.9.2</version>
        <scope>test</scope>
    </dependency>
</dependencies>
```

## 🏃‍♂️ Como Executar

```bash
# Executar exemplo específico
java strings/StringBasics.java

# Compilar projeto
mvn compile

# Executar testes (quando implementados)
mvn test
```

## 🎉 Destaques Recentes

- 🏆 **JAVA PILLARS FINALIZADO** - Todos os 4 pilares da POO com exemplos práticos complexos
- ✅ **ABSTRACTION COMPLETO** com sistema de pagamentos demonstrando interfaces múltiplas
- ✅ **POLYMORPHISM COMPLETO** com formas geométricas e métodos polimórficos
- ✅ **INHERITANCE COMPLETO** com hierarquias de animais e veículos
- ✅ **ENCAPSULATION COMPLETO** com exemplos de banco, produto e usuário
- 📊 **StreamBasics implementado** com exemplos de e-commerce, análise de idades e processamento de texto

**Progresso Total**: 31/37 classes implementadas (84%!) 🚀

## 📝 Nova Estrutura do Projeto

Este projeto foi reestruturado para máxima visibilidade no GitHub! 🎯

### ✨ Mudanças Realizadas:
- 📁 **Pastas na raiz**: `collections/`, `streams/`, `strings/`, etc. agora aparecem diretamente na página inicial
- 🔧 **Sem packages**: Arquivos Java sem declarações de package para simplicidade
- 🎯 **Melhor navegação**: Estrutura mais limpa e acessível no GitHub
- ⚡ **Maven configurado**: `sourceDirectory` apontando para a raiz do projeto

---

## 🎯 Próximas Implementações

1. **Streams** - CollectorsRecipes e OptionalPatterns
2. **Dates** - LocalDateTime, Duration/Period, Formatação
3. **I/O & NIO** - Files, Paths, leitura/escrita
4. **Concurrency** - Threads, Executors, Futures
5. **Testes Unitários** - Cobertura completa dos módulos

## 🔧 Características Técnicas

- **Java 17** como versão base
- **Exemplos práticos** com casos de uso reais
- **Documentação rica** com emojis e explicações detalhadas
- **Sintaxe completa** para cada conceito
- **Sem dependências externas** (exceto JUnit para testes)
- **Código limpo** seguindo boas práticas

---

> **Metodologia**: Construção gradual, questionamentos, explicações detalhadas, conexão entre conceitos, exemplos práticos que funcionam no mundo real.

**🤖 Nota sobre IA**: Este projeto utiliza IA para tornar os códigos mais comunicativos, verbosos e intuitivos através de comentários detalhados e documentação rica. Toda a lógica, estrutura e ideias de implementação são de autoria própria.