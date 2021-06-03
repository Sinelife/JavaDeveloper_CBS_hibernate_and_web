package com.cbsystematics.edu.homework05.utils;

import com.cbsystematics.edu.homework05.entities.Student;

import java.util.Random;

public class RandomService {


    public static Student getRandomStudentData(int id) {
        Student student = getRandomStudentData();
        student.setId(id);
        return student;
    }

    public static Student getRandomStudentData() {
        Student student = new Student();
        student.setFirstName(getRandomWord(100));
        student.setLastName(getRandomWord(100));
        student.setAge(getRandomAge());
        student.setAverageMark(getRandomAverageMark());
        return student;
    }

    private static double getRandomAverageMark() {
        Random random = new Random();
        int num = random.nextInt(400) + 600;
        double res = (double) (num) / 10;
        return res;
    }

    private static int getRandomAge() {
        Random random = new Random();
        return random.nextInt(10) + 17;
    }

    private static String getRandomWord(int generatedWordsNum) {
        String[] randomStrings = generateRandomWords(generatedWordsNum);
        Random random = new Random();
        return randomStrings[random.nextInt(generatedWordsNum)];
    }

    private static String[] generateRandomWords(int numberOfWords) {
        String[] randomStrings = new String[numberOfWords];
        Random random = new Random();
        char[] word = new char[random.nextInt(8) + 3];
        word[0] = (char) ('A' + random.nextInt(26));
        for (int i = 0; i < numberOfWords; i++) {
            for (int j = 1; j < word.length; j++) {
                word[j] = (char) ('a' + random.nextInt(26));
            }
            randomStrings[i] = new String(word);
        }
        return randomStrings;
    }

}
