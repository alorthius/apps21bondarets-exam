package domain;

import json.*;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Andrii_Rodionov on 1/3/2017.
 */
public class Student extends BasicStudent {
    protected List<Tuple<String, Integer>> exams;

    @SafeVarargs
    public Student(String name, String surname, Integer year, Tuple<String, Integer>... exams) {
        this.name = name;
        this.surname = surname;
        this.year = year;
        this.exams = Arrays.asList(exams);
    }

    @Override
    public JsonObject toJsonObject() {
        JsonPair name = new JsonPair("name", new JsonString(this.name));
        JsonPair surname = new JsonPair("surname", new JsonString(this.surname));
        JsonPair year = new JsonPair("year", new JsonNumber(this.year));

        Json[] examsArr = new Json[this.exams.size()];
        for (int ind = 0; ind < this.exams.size(); ++ind) {
            Tuple<String, Integer> exam = this.exams.get(ind);

            JsonPair course = new JsonPair("course", new JsonString(exam.key));
            JsonPair mark = new JsonPair("mark", new JsonNumber(exam.value));
            JsonPair passed = new JsonPair("passed", new JsonBoolean(exam.value >= 3));

            examsArr[ind] = new JsonObject(course, mark, passed);
        }

        JsonPair examsPair = new JsonPair("exams", new JsonArray(examsArr));
        return new JsonObject(name, surname, year, examsPair);
    }
}