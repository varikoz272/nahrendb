package com.varikoz272.nahrendb;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.varikoz272.nahrendb.input.MethodToken;
import com.varikoz272.nahrendb.input.TableToken;
import com.varikoz272.nahrendb.input.Tokenizer;
import com.varikoz272.nahrendb.input.ValueToken;
import com.varikoz272.nahrendb.input.VariableToken;

public class AppTest {

    @Test
    public void shouldAnswerWithTrue() {
        assertTrue(true);
    }

    @Test
    public void tokenizer1() {
        String input = "$mytable1 #name #age add \'mr john\' \'20\'";

        var tokens = Tokenizer.instance.split(input);

        assertTrue(tokens.size() == 6);

        assertTrue(tokens.get(0) instanceof TableToken);
        assertTrue(tokens.get(1) instanceof VariableToken);
        assertTrue(tokens.get(2) instanceof VariableToken);
        assertTrue(tokens.get(3) instanceof MethodToken);
        assertTrue(tokens.get(4) instanceof ValueToken);
        assertTrue(tokens.get(5) instanceof ValueToken);

        assertTrue(tokens.get(0).word.equals("mytable1"));
        assertTrue(tokens.get(1).word.equals("name"));
        assertTrue(tokens.get(2).word.equals("age"));
        assertTrue(tokens.get(3).word.equals("add"));
        assertTrue(tokens.get(4).word.equals("mr john"));
        assertTrue(tokens.get(5).word.equals("20"));
    }
}
