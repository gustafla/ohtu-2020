package statistics;

import statistics.matcher.Matcher;
import statistics.matcher.All;

public class QueryBuilder {
    Matcher matcher;

    public QueryBuilder() {
        matcher = new All();
    }

    public Matcher build() {
        return matcher;
    }
}
