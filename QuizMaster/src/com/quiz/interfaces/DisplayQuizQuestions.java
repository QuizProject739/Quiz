package com.quiz.interfaces;

import java.sql.SQLException;
import java.util.List;

import com.quiz.Question;

public interface DisplayQuizQuestions {

    public List<Question> getQuestions() throws SQLException;
}
