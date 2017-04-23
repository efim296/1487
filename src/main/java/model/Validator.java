//package main.java.model;
//
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
///**
// * Created by admin on 23.04.2017.
// */
//public class Validator {
//
//
//    private static Pattern pattern;
//    private static Matcher matcher;
//
//    private static final String EMAIL_PATTERN =
//            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" +
//                    "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
//
//    public Validator() {
//        pattern = Pattern.compile(EMAIL_PATTERN);
//    }
//
//    public static boolean validate(final String hex) {
//        matcher = pattern.matcher(hex);
//
//        return matcher.matches();
//    }
//
//}
