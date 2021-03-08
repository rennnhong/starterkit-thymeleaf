package indi.rennnhong.staterkit.persistence;

import com.p6spy.engine.spy.appender.MessageFormattingStrategy;
import com.p6spy.engine.spy.appender.MultiLineFormat;
import org.hibernate.engine.jdbc.internal.BasicFormatterImpl;
import org.hibernate.engine.jdbc.internal.Formatter;

//public class PrettySqlMultiLineFormat extends MultiLineFormat {
////    private static final Formatter FORMATTER = new BasicFormatterImpl();
//
//    @Override
//    public String formatMessage(int connectionId, String now, long elapsed, String category, String prepared, String sql, String url) {
////        return super.formatMessage(connectionId, now, elapsed, category, FORMATTER.format(prepared), FORMATTER.format(sql),url);
//        return super.formatMessage(connectionId, now, elapsed, category, prepared, sql, url);
//    }
//}
public class PrettySqlMultiLineFormat implements MessageFormattingStrategy {
//    private static final Formatter FORMATTER = new BasicFormatterImpl();

    @Override
    public String formatMessage(int connectionId, String now, long elapsed, String category, String prepared, String sql, String url) {
//        return super.formatMessage(connectionId, now, elapsed, category, FORMATTER.format(prepared), FORMATTER.format(sql),url);
        return "#" + now + " | took " + elapsed + "ms | " + category + " | connection " + connectionId + "| url " + url + "\n" + prepared + "\n" + sql;
    }
}
