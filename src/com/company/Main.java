package com.company;

import java.io.*;
import java.time.LocalDateTime;

public class Main {

    public static void main(String[] args) {
        StringBuilder builder = new StringBuilder();

        String[] dirGames = {"src", "res", "savegames", "temp"};
        String[] dirStr = {"main", "test"};
        String[] dirRes = {"drawables", "vectors", "icons"};
        String[] dirMain = {"Main.java", "Utils.java"};
        String[] dirTemp = {"temp.txt"};

        createDirectory("D://Games/", dirGames, builder);
        createDirectory("D://Games/src/", dirStr, builder);
        createDirectory("D://Games/res/", dirRes, builder);

        createFile("D://Games/src/main", dirMain, builder);
        createFile("D://Games/temp", dirTemp, builder);

        try (FileOutputStream fos = new FileOutputStream("D://Games/temp/temp.txt")) {
            byte[] bytes = builder.toString().getBytes();
            fos.write(bytes, 0, bytes.length);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public static StringBuilder createDirectory(String way, String names[], StringBuilder builder) {
        for (String s : names) {
            File dir = new File(way + s);
            LocalDateTime missedCallTime = LocalDateTime.now();
            if (dir.mkdir())
                builder.append("Каталог " + dir + " создан " + missedCallTime + "\n");
            ;
        }
        return builder;
    }


    public static StringBuilder createFile(String way, String names[], StringBuilder builder) {
        for (String s : names) {
            File file = new File(way, s);
            LocalDateTime missedCallTime = LocalDateTime.now();
            try {
                if (file.createNewFile())
                    builder.append("Файл " + file.getName() + " создан в каталоге " + way + " " + missedCallTime + "\n");
            } catch (IOException ex) {
                builder.append("Ошибка! Файл " + file + " не был создан" + "\n");
            }
        }
        return builder;
    }

}

