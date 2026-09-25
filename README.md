# Лаборатори №4: Нэгжийн тест
Программ хангамжийн чанарын баталгаа ба тест (F.CSA313)

**B232270052 С. Азжаргал**

## Орчны хувилбар
### java --version
```text
openjdk version "17.0.20.1" 2026-08-18 LTS
OpenJDK Runtime Environment Microsoft-14940689 (build 17.0.20.1+1-LTS)
OpenJDK 64-Bit Server VM Microsoft-14940689 (build 17.0.20.1+1-LTS, mixed mode, sharing)
```
### mvn --version
```text
Apache Maven 3.9.16 (2bdd9fddda4b155ebf8000e807eb73fd829a51d5)
Maven home: C:\Program Files\apache-maven-3.9.16
Java version: 17.0.20.1, vendor: Microsoft, runtime: C:\Program Files\Microsoft\jdk-17.0.20.101-hotspot
Default locale: en_US, platform encoding: Cp1252
OS name: "windows 11", version: "10.0", arch: "amd64", family: "windows"
```

## Тестийн тоо ба үр дүн (results/mvn-test.txt)

`GradeCalculatorTest`-д 9 тестийн метод бичигдсэн: 7 энгийн `@Test` (`ninetyIsExactlyA`, `boundaryBelowA`, `boundaryBelowD`, `boundaryMinAndMaxScores`, `invalidScoreThrowsException`, `totalScoreValidInputs`, `totalScoreInvalidInputsThrowException`) болон 2 `@ParameterizedTest` (`letterGradeBoundariesParameterized` — 9 мөр `@CsvSource`, `totalScoreParameterized` — 4 мөр `@CsvSource`).

Параметрчилсэн тестүүд бүр мөр тус бүрээр тусад нь ажилладаг тул нийт гүйцэтгэсэн тестийн тоо:
7 + 9 + 4 = **20**, энэ нь `results/mvn-test.txt`-ийн сүүлийн мөрөнд буй `Tests run: 20, Failures: 0, Errors: 0, Skipped: 0` (**BUILD SUCCESS**) утгатай тохирч байна.

## Мутацийн нотолгоо (results/mvn-test-mutant.txt)

`letterGrade` методод "off-by-boundary" төрлийн мутаци (90 оноог "A" гэж тооцох `>=` нөхцөл өөрчлөгдсөн) хийхэд `mvn test` дараах байдлаар унасан: `Tests run: 20, Failures: 2, Errors: 0, Skipped: 0` — **BUILD FAILURE**.

Унасан тестүүд:
1. **`ninetyIsExactlyA`** — `expected: <A> but was: <B>` (score = 90.0)
2. **`letterGradeBoundariesParameterized[2]`** (CSV-ийн "90, A" мөр) — мөн адил `expected: <A> but was: <B>`

Мутаци засагдсаны дараа `results/mvn-test.txt` дахин ногоон (BUILD SUCCESS) болсон.

## Дүгнэлт

Алдааг `ninetyIsExactlyA` болон `letterGradeBoundariesParameterized` тестүүд хамтдаа илрүүлсэн бөгөөд хоёулаа яг 90 гэсэн хязгаарын утгыг шалгаж байв. Мутаци нь `letterGrade` методын дээд хязгаарын харьцуулалтыг өөрчилсний улмаас 90.01, 95, 100 зэрэг утгууд хэвээрээ зөв "A" гарсаар байсан ч яг 90.0 оноо гэнэт "B" болж хувирсан нь off-by-one/boundary алдаа юм. Хэрэв тестүүд зөвхөн "аюулгүй" утгуудыг (жишээ нь 89.99, 95) л шалгасан бол энэ алдаа огт илрэхгүй байх байсан үүнийг энэ мутаци тод харуулж байна. `boundaryBelowA` тест (89.99 → B) мутацид унаагүй нь мутаци зөвхөн дээд (>=90) харьцуулалтад нөлөөлж, доод (>=80) харьцуулалтад нөлөөлөөгүйг батлаж байна. Ингэснээр параметрчилсэн тестийн олон мөр дундаас яг алдаа гарсан мөрийг тодорхой заасан нь тестийн диагностик чадварыг сайн харуулж байна. Энэ нь хязгаарын утгыг ойролцоо утгаар бус, яг тэр цэг дээр нь шалгах шаардлагатайг баталгаажуулж байна.