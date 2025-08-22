# Java Toolbox

Repositório para **dominar a biblioteca padrão do Java** (java.base e módulos úteis), com exemplos minimalistas, exercícios, testes automatizados e referências.

> Filosofia: *1 classe/utilidade por arquivo + 1 teste por comportamento*, exemplos curtos e focados, sem frameworks externos.

---

## 📁 Estrutura

```
java-toolbox/
├─ pom.xml
├─ README.md
├─ docs/
│  └─ roadmap.md
├─ src/
│  ├─ main/java/com/kaue/toolbox/
│  │  ├─ strings/
│  │  │  ├─ StringBasics.java ✅
│  │  │  ├─ StringSearching.java ✅
│  │  │  ├─ StringBuilderExamples.java ✅
│  │  │  └─ RegexBasics.java ✅
│  │  ├─ regex/
│  │  │  ├─ RegexBasics.java ✅
│  │  │  ├─ RegexGroups.java ✅
│  │  │  ├─ RegexReplace.java ✅
│  │  │  └─ RegexValidation.java ✅
│  │  ├─ numbers/
│  │  │  ├─ IntegerUtils.java ✅
│  │  │  ├─ LongUtils.java ✅
│  │  │  ├─ BigDecimalUtils.java ✅
│  │  │  └─ MathAndRandom.java ✅
│  │  ├─ collections/
│  │  │  ├─ ListsBasics.java ✅
│  │  │  ├─ SetsBasics.java ✅
│  │  │  ├─ MapsBasics.java ✅
│  │  │  ├─ QueueBasics.java ✅
│  │  │  └─ ComparatorsAndRecords.java ✅
│  │  ├─ streams/
│  │  │  ├─ StreamBasics.java ✅
│  │  │  ├─ CollectorsRecipes.java ⏳
│  │  │  └─ OptionalPatterns.java ⏳
│  │  ├─ javapillars/
│  │  │  ├─ encapsulation/
│  │  │  │  ├─ EncapsulationSyntax.java ✅
│  │  │  │  ├─ BankAccountExample.java ✅
│  │  │  │  ├─ ProductExample.java ⏳
│  │  │  │  └─ UserExample.java ⏳
│  │  │  ├─ inheritance/ ⏳
│  │  │  ├─ polymorphism/ ⏳
│  │  │  └─ abstraction/ ⏳
│  │  ├─ dates/ ⏳
│  │  │  ├─ LocalDateTimeBasics.java
│  │  │  ├─ DurationPeriod.java
│  │  │  └─ DateTimeFormatting.java
│  │  ├─ io_nio/ ⏳
│  │  │  ├─ FilesAndPaths.java
│  │  │  └─ ReadWriteSmallFiles.java
│  │  ├─ concurrency/ ⏳
│  │  │  ├─ ThreadsBasics.java
│  │  │  └─ ExecutorsAndFutures.java
│  │  ├─ net_http/ ⏳
│  │  │  └─ HttpClientBasics.java
│  │  ├─ lang/ ⏳
│  │  │  ├─ ObjectsAndRecords.java
│  │  │  ├─ PatternMatching.java
│  │  │  └─ SwitchExpressions.java
│  │  └─ util/ ⏳
│  │     ├─ Preconditions.java
│  │     └─ Tuple.java
│  └─ test/java/com/kaue/toolbox/
│     ├─ strings/
│     │  ├─ StringBasicsTest.java
│     │  ├─ StringSearchingTest.java
│     │  ├─ StringBuilderExamplesTest.java
│     │  └─ RegexBasicsTest.java
│     ├─ numbers/
│     │  └─ BigDecimalBasicsTest.java
│     └─ collections/
│        └─ ListsBasicsTest.java
└─ .editorconfig
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

### 🔄 Streams (1/3) - EM PROGRESSO
- ✅ StreamBasics.java
- ⏳ CollectorsRecipes.java
- ⏳ OptionalPatterns.java

### 🔄 Java Pillars (2/12) - EM PROGRESSO
- **Encapsulation (2/4):**
    - ✅ EncapsulationSyntax.java
    - ✅ BankAccountExample.java
    - ⏳ ProductExample.java
    - ⏳ UserExample.java
- **Inheritance (0/3):** ⏳
- **Polymorphism (0/3):** ⏳
- **Abstraction (0/2):** ⏳

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

**MÓDULO EM PROGRESSO:** Java Pillars - Encapsulation
- 📍 **Próximo:** Finalizar exemplos de encapsulamento (ProductExample, UserExample)
- 📍 **Depois:** Inheritance, Polymorphism, Abstraction
- 📍 **Paralelo:** Continuar Streams (CollectorsRecipes, OptionalPatterns)

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
java src/main/java/com/kaue/toolbox/strings/StringBasics.java

# Executar testes (quando implementados)
mvn test
```

## 🎉 Destaques Recentes

- ✅ **Módulo Collections FINALIZADO** com exemplos práticos
- ✅ **StreamBasics implementado** com filter, map, collect, exemplos reais
- ✅ **Java Pillars iniciado** com foco em encapsulamento
- ✅ **BankAccountExample completo** demonstrando encapsulamento na prática

**Progresso Total**: 19/37 classes implementadas (51%!) 🚀

---

> **Metodologia**: Construção gradual, questionamentos, explicações detalhadas, conexão entre conceitos, exemplos práticos que funcionam no mundo real.