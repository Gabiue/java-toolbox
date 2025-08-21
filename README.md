# Java Toolbox

Repositório para **dominar a biblioteca padrão do Java** (java.base e módulos úteis), com exemplos minimalistas, exercícios, testes automatizados e referências.

> Filosofia: *1 classe/utilidade por arquivo + 1 teste por comportamento*, exemplos curtos e focados, sem frameworks externos (apenas JUnit para testes).

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
│  │  │  └─ ComparatorsAndRecords.java ⏳
│  │  ├─ streams/
│  │  │  ├─ StreamBasics.java
│  │  │  ├─ CollectorsRecipes.java
│  │  │  └─ OptionalPatterns.java
│  │  ├─ dates/
│  │  │  ├─ LocalDateTimeBasics.java
│  │  │  ├─ DurationPeriod.java
│  │  │  └─ DateTimeFormatting.java
│  │  ├─ io_nio/
│  │  │  ├─ FilesAndPaths.java
│  │  │  └─ ReadWriteSmallFiles.java
│  │  ├─ concurrency/
│  │  │  ├─ ThreadsBasics.java
│  │  │  └─ ExecutorsAndFutures.java
│  │  ├─ net_http/
│  │  │  └─ HttpClientBasics.java
│  │  ├─ lang/
│  │  │  ├─ ObjectsAndRecords.java
│  │  │  ├─ PatternMatching.java
│  │  │  └─ SwitchExpressions.java
│  │  └─ util/
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

### ✅ Strings (4/4)
- ✅ StringBasics.java
- ✅ StringSearching.java
- ✅ StringBuilderExamples.java
- ✅ RegexBasics.java

### ✅ Regex (4/4)
- ✅ RegexBasics.java
- ✅ RegexGroups.java
- ✅ RegexReplace.java
- ✅ RegexValidation.java

### ✅ Numbers (4/4)
- ✅ IntegerUtils.java
- ✅ LongUtils.java
- ✅ BigDecimalUtils.java
- ✅ MathAndRandom.java

### ✅ Collections (4/5)
- ✅ ListsBasics.java
- ✅ SetsBasics.java
- ✅ MapsBasics.java
- ✅ QueueBasics.java
- ⏳ ComparatorsAndRecords.java

### ⏳ Streams (0/3)
- ⬜ StreamBasics.java
- ⬜ CollectorsRecipes.java
- ⬜ OptionalPatterns.java

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

**Progresso Total**: 16/32 classes implementadas (50%!) 🎉