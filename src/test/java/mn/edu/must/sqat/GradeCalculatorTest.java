package mn.edu.must.sqat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class GradeCalculatorTest {

    private GradeCalculator calc;

    @BeforeEach
    void setUp() {
        // Arrange: Метод бүрийн өмнө шинэ объект бэлдэнэ
        calc = new GradeCalculator();
    }

    @Test
    @DisplayName("90 оноо A үсгэн үнэлгээтэй байх ёстой.")
    void ninetyIsExactlyA() {
        // Act
        String grade = calc.letterGrade(90.0);
        // Assert
        assertEquals("A", grade);
    }

    @Test
    @DisplayName("89.99 оноо B үсгэн үнэлгээтэй байх ёстой.")
    void boundaryBelowA() {
        // Act
        String grade = calc.letterGrade(89.99);
        // Assert
        assertEquals("B", grade);
    }

    @Test
    @DisplayName("Хязгаарын 0 ба 100 үнэлгээний үсгэн дүн зөв тооцоологдох ёстой")
    void boundaryMinAndMaxScores() {
        // Act & Assert
        assertEquals("F", calc.letterGrade(0.0));
        assertEquals("A", calc.letterGrade(100.0));
    }

    @Test
    @DisplayName("Үнэлгээний оноо нь 0-ээс бага эсвэл 100-аас их үед IllegalArgumentException шидэх ёстой")
    void invalidScoreThrowsException() {
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> calc.letterGrade(-1.0));
        assertThrows(IllegalArgumentException.class, () -> calc.letterGrade(101.0));
    }

    @ParameterizedTest
    @DisplayName("Үсгэн үнэлгээний ердийн болон хязгаарын утгуудыг шалгах")
    @CsvSource({
        "95, A",
        "90, A",
        "89.99, B",
        "85, B",
        "75, C",
        "65, D",
        "59.99, F",
        "30, F",
        "0, F"
    })
    void letterGradeBoundariesParameterized(double score, String expectedGrade) {
        // Act
        String actualGrade = calc.letterGrade(score);
        // Assert
        assertEquals(expectedGrade, actualGrade);
    }

    @Test
    @DisplayName("Нийлбэр утгын хязгаар бүтэн онооны утгуудаар нийлбэр 100 гарах ёстой")
    void totalScoreValidInputs() {
        // Act
        double total = calc.totalScore(10, 40, 10, 10, 30);
        // Assert
        assertEquals(100.0, total);
    }

    @Test
    @DisplayName("Үнэлгээний бүрэлдэхүүн хэсгүүдийн утга нь зөвхөн зөв хүрээнд байх ёстой")
    void totalScoreInvalidInputsThrowException() {
        // Сөрөг оролт (att = -5)
        assertThrows(IllegalArgumentException.class, 
            () -> calc.totalScore(-5, 40, 10, 10, 30));

        // Хэтэрсэн оролт (lab = 41)
        assertThrows(IllegalArgumentException.class, 
            () -> calc.totalScore(10, 41, 10, 10, 30));
    }

    @ParameterizedTest
    @DisplayName("Нийт үнэлгээний оноо шалгах (үнэлгээний задаргааны төрөл бүрийн зөв утга)")
    @CsvSource({
        "10, 40, 10, 10, 30, 100.0",
        "0, 0, 0, 0, 0, 0.0",
        "8, 32, 8, 8, 24, 80.0",
        "5.5, 20.0, 5.0, 5.0, 15.0, 50.5"
    })
    void totalScoreParameterized(double att, double lab, double q1, double q2, double exam, double expectedTotal) {
        // Act
        double actualTotal = calc.totalScore(att, lab, q1, q2, exam);
        // Assert
        assertEquals(expectedTotal, actualTotal);
    }
}