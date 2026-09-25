package mn.edu.must.sqat;

public class GradeCalculator {

    public String letterGrade(double score) {
        if (score < 0 || score > 100) {
            throw new IllegalArgumentException("Үнэлгээний оноо 0-100 хооронд байх ёстой.");
        }
        if (score > 90) return "A";
        if (score >= 80) return "B";
        if (score >= 70) return "C";
        if (score >= 60) return "D";
        return "F";
    }

    public double totalScore(double att, double lab, double quiz1, double quiz2, double exam) {
        if (att < 0 || att > 10 ||
            lab < 0 || lab > 40 ||
            quiz1 < 0 || quiz1 > 10 ||
            quiz2 < 0 || quiz2 > 10 ||
            exam < 0 || exam > 30) {
            throw new IllegalArgumentException("Үнэлгээний оноо : att (0-10), lab (0-40), quiz1 (0-10), quiz2 (0-10), exam (0-30) хооронд байх ёстой.");
        }
        return att + lab + quiz1 + quiz2 + exam;
    }
}