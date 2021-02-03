package tictactoe.checkers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class StatusChecker {

    public String getMovesString(String[][] gameBord) {
        StringBuilder sb = new StringBuilder();
        for (String[] strings : gameBord) {
            for (String string : strings) {
                sb.append(string);
            }
        }
        return sb.toString();
    }

    public boolean isDraw(String moves) {
        return !xWin(moves) && !oWin(moves);
    }

    public boolean oWin(String moves) {
        final String oRegex =
                "(OOO.{6}|.{3}OOO.{3}|.{6}OOO|.{2}O.O.O.{2}|O.{3}O.{3}O|O.{2}O.{2}O.{2}|.{2}O.{2}O.{2}O|.O.{2}O.{2}O.)";
        Pattern pattern = Pattern.compile(oRegex);
        Matcher matcher = pattern.matcher(moves);
        while (matcher.find()) {
            if (matcher.group(1) != null) {
                return true;
            }
        }
        return false;
    }

    public boolean xWin(String moves) {
        final String xRegex =
                "(XXX.{6}|.{3}XXX.{3}|.{6}XXX|.{2}X.X.X.|X.{3}X.{3}X|X.{2}X.{2}X.{2}|.{2}X.{2}X.{2}X|.X.{2}X.{2}X.)";
        Pattern pattern = Pattern.compile(xRegex);
        Matcher matcher = pattern.matcher(moves);
        while (matcher.find()) {
            if (matcher.group(1) != null) {
                return true;
            }
        }
        return false;
    }
}
