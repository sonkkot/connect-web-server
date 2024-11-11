package site.copi.presentation.util;

import java.io.File;
import java.util.*;

final class LogoReader {

    private static List<String> read(final String url) {
        final var file = new File(url);
        final var fileNameList = new ArrayList<>(Arrays.stream(Objects.requireNonNull(file.listFiles()))
            .map(e -> e.getName().split("\\.")[0])
            .toList());
        Collections.sort(fileNameList);

        return fileNameList;
    }

    private static void printLiTag(final List<String> list) {
        for (var s : list) {
            final var e = new StringBuilder()
                .append("<li class=\"project-stack\">")
                .append(System.lineSeparator())
                .append("    <h3 class=\"project-stack-title\">")
                .append(s.toUpperCase())
                .append("</h3>")
                .append(System.lineSeparator())
                .append("    <img class=\"project-stack-logo\" th:src=\"@{/images/logo/")
                .append(s)
                .append(".svg}\" src=\"/copi-server-presentation/src/main/resources/static/images/logo/")
                .append(s)
                .append(".svg\" alt=\"")
                .append(s)
                .append("logo\">")
                .append(System.lineSeparator())
                .append("</li>");
            System.out.println(e);
        }
    }

    private static void print(final List<String> list) {
        list.forEach(e -> System.out.println(e.toUpperCase()));
    }

    public static void main(String[] args) {
        final var read = LogoReader.read(null);
        LogoReader.print(read);
    }
}
