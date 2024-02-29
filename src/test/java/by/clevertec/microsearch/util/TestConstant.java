package by.clevertec.microsearch.util;

import java.time.LocalDateTime;
import lombok.experimental.UtilityClass;

@UtilityClass
public class TestConstant {

    public static final long ID_ONE = 1L;
    public static final LocalDateTime LOCAL_DATE_TIME = LocalDateTime.of(2024, 2, 25, 12, 12, 12);
    public static final String NEWS_TITLE = "Название новости";
    public static final String NEWS_TEXT = "Тут должно быть много слов по тексту новости";
    public static final String USERNAME = "Username";


    public static final long INVALID_ID = 999L;


    @UtilityClass
    public class ExceptionMessages {

        public static final String PREFIX_NOT_FOUND_CUSTOM_MESSAGE = " with id ";
        public static final String POSTFIX_NOT_FOUND_CUSTOM_MESSAGE = " does not exist";
    }

    @UtilityClass
    public class Attributes {

        public static final String ONE = "one";
        public static final String TWO = "two";
        public static final String THREE = "three";
        public static final String PAGE = "page";
        public static final String SIZE = "size";
    }

    @UtilityClass
    public class Path {

        public static final String COMMENTS_URL = "/comments/";
        public static final String COMMENTS = "/comments";
        public static final String COMMENTS_NEWS_URL = "/comments/news/";
        public static final String COMMENTS_NEWS = "/comments/news";

    }


}

