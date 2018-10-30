package com.grug.anyTech;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by feichen on 2018/5/29.
 */
public class ConvertN {

    public static void main(String[] args) {
        String text = "生命苦短，我用Python。\n hello \n";
        System.out.println(convert(text));
    }

    private static String convert(String test) {
        List<Character> characters = test.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.toList());
        for (int i = 0; i < characters.size(); i++) {
            Character character = characters.get(i);
            if (character == '\n') {
                characters.set(i, 'n');
                characters.add(i,'\\');
            }
        }

        return characters.stream()
                .map(character -> character.toString())
                .reduce((acc, e) -> acc + e)
                .get();
    }
}
